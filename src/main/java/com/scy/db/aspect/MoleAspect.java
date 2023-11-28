package com.scy.db.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.scy.core.db.SqlUtil;
import com.scy.core.exception.ExceptionUtil;
import com.scy.core.format.DateUtil;
import com.scy.core.json.JsonUtil;
import com.scy.core.model.JoinPointBO;
import com.scy.core.reflect.ClassUtil;
import com.scy.core.reflect.MethodUtil;
import com.scy.core.spring.ApplicationContextUtil;
import com.scy.core.spring.JoinPointUtil;
import com.scy.core.thread.ThreadLocalUtil;
import com.scy.core.thread.ThreadPoolUtil;
import com.scy.db.annotation.Mole;
import com.scy.db.constant.DbConstant;
import com.scy.db.mapper.MoleTaskDOMapper;
import com.scy.db.model.MoleTaskDO;
import com.scy.db.model.MoleTaskDOExample;
import com.scy.db.transaction.ForceMasterDataSourceTransactionManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Order(MoleAspect.MOLE)
@Aspect
public class MoleAspect {

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = ThreadPoolUtil.getThreadPool("mole-aspect", 5, 5, 5);

    public static final int MOLE = 24000;

    public static final String MOLE_ASPECT_SWITCH = "mole_aspect_switch";

    @Autowired
    private MoleTaskDOMapper moleTaskDOMapper;

    @Pointcut("@annotation(com.scy.db.annotation.Mole)")
    public void pointcut() {
    }

    @Around("pointcut() && @annotation(mole)")
    public Object around(ProceedingJoinPoint joinPoint, Mole mole) throws Throwable {
        if (isDisableMoleAspect()) {
            enableMoleAspect();
            return joinPoint.proceed();
        }

        MoleTaskDO moleTaskDO = new MoleTaskDO();
        moleTaskDO.setNextExeTime(new Date(System.currentTimeMillis() + (mole.delayTime() * DateUtil.SECOND)));
        moleTaskDO.setExeIntervalSec(mole.intervalTime());
        moleTaskDO.setExeCount(0);
        moleTaskDO.setMaxExeCount(mole.maxExeCount());
        moleTaskDO.setExeStatus(0);
        // 分库id
        moleTaskDO.setSharedId(0L);

        JoinPointBO joinPointBO = JoinPointUtil.getJoinPointBO(joinPoint);

        Class<?> targetClass = joinPointBO.getTargetClass();
        Method method = joinPointBO.getMethod();

        // 所有参数类型
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        List<String> paramTypeNames = Stream.of(genericParameterTypes).map(Type::getTypeName).collect(Collectors.toList());

        String paramTypeJson = JsonUtil.object2Json(paramTypeNames);
        moleTaskDO.setParamTypeJson(paramTypeJson);

        Object[] args = joinPointBO.getArgs();
        List<String> argList = Stream.of(args).map(JsonUtil::object2Json).collect(Collectors.toList());
        String paramsJson = JsonUtil.object2Json(argList);
        moleTaskDO.setParamsJson(paramsJson);

        String targetClassName = targetClass.getName();
        moleTaskDO.setTargetClassName(targetClassName);

        String methodName = method.getName();
        moleTaskDO.setMethodName(methodName);

        String beanName = ApplicationContextUtil.getBeanNamesForType(targetClass)[0];
        moleTaskDO.setBeanName(beanName);

        moleTaskDO.setEnv(ApplicationContextUtil.getProperty(ApplicationContextUtil.ACTIVE));
        moleTaskDO.setCreatedAt(new Date());
        moleTaskDO.setUpdatedAt(new Date());

        int result = moleTaskDOMapper.insertSelective(moleTaskDO);
        SqlUtil.checkOneRecord(result);

        boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
        if (synchronizationActive) {
            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronization() {
                        @Override
                        public void afterCommit() {
                            THREAD_POOL_EXECUTOR.execute(() -> execute(moleTaskDO));
                        }
                    }
            );
            return null;
        }

        execute(moleTaskDO);

        return null;
    }

    private void execute(MoleTaskDO moleTaskDO) {
        DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
        transactionDefinition.setPropagationBehavior(DefaultTransactionDefinition.PROPAGATION_REQUIRED);
        transactionDefinition.setIsolationLevel(DefaultTransactionDefinition.ISOLATION_READ_COMMITTED);
        transactionDefinition.setTimeout(DefaultTransactionDefinition.TIMEOUT_DEFAULT);
        transactionDefinition.setReadOnly(Boolean.FALSE);

        ForceMasterDataSourceTransactionManager dataSourceTransactionManager = ApplicationContextUtil.getBean("warehouse" + DbConstant.TRANSACTION_MANAGER, ForceMasterDataSourceTransactionManager.class);
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            MoleTaskDO updateMoleTaskDO = new MoleTaskDO();
            updateMoleTaskDO.setExeCount(moleTaskDO.getExeCount() + 1);
            updateMoleTaskDO.setExeStatus(1);
            updateMoleTaskDO.setUpdatedAt(DateUtil.getCurrentDate());

            MoleTaskDOExample example = new MoleTaskDOExample();
            MoleTaskDOExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(moleTaskDO.getId());
            criteria.andExeStatusEqualTo(0);
            int result = moleTaskDOMapper.updateByExampleSelective(updateMoleTaskDO, example);
            SqlUtil.checkOneRecord(result);

            List<String> paramTypeNames = JsonUtil.json2Object(moleTaskDO.getParamTypeJson(), new TypeReference<List<String>>() {
            });
            List<JavaType> paramJavaTypes = paramTypeNames.stream().map(JsonUtil::getJavaType).collect(Collectors.toList());
            String[] paramClassNames = paramJavaTypes.stream()
                    .map(paramJavaType -> paramJavaType.getRawClass().getName()).collect(Collectors.toList()).toArray(new String[]{});

            List<String> argList = JsonUtil.json2Object(moleTaskDO.getParamsJson(), new TypeReference<List<String>>() {
            });
            Object[] args = new Object[argList.size()];
            for (int i = 0; i < argList.size(); i++) {
                String param = argList.get(i);
                JavaType javaType = paramJavaTypes.get(i);
                args[i] = JsonUtil.json2Object(param, javaType);
            }

            Method userMethod = MethodUtil.getMethod(ClassUtil.resolveClass(moleTaskDO.getTargetClassName()), moleTaskDO.getMethodName(),
                    ClassUtil.resolveClass(paramClassNames));
            Object bean = ApplicationContextUtil.getBean(moleTaskDO.getBeanName());

            disableMoleAspect();

            try {
                userMethod.invoke(bean, args);
            } catch (Exception e) {
                e.printStackTrace();
                updateMoleTaskDO = new MoleTaskDO();
                updateMoleTaskDO.setNextExeTime(new Date(System.currentTimeMillis() + (moleTaskDO.getExeIntervalSec() * DateUtil.SECOND)));
                updateMoleTaskDO.setExeStatus(0);
                updateMoleTaskDO.setUpdatedAt(DateUtil.getCurrentDate());
                updateMoleTaskDO.setErrorMessage(ExceptionUtil.getExceptionMessage(e));

                example = new MoleTaskDOExample();
                criteria = example.createCriteria();
                criteria.andIdEqualTo(moleTaskDO.getId());
                criteria.andExeStatusEqualTo(1);
                result = moleTaskDOMapper.updateByExampleSelective(updateMoleTaskDO, example);
                SqlUtil.checkOneRecord(result);
                return;
            }

            moleTaskDOMapper.deleteByPrimaryKey(moleTaskDO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            dataSourceTransactionManager.rollback(transaction);
        } finally {
            dataSourceTransactionManager.commit(transaction);
        }
    }

    public static void enableMoleAspect() {
        ThreadLocalUtil.put(MOLE_ASPECT_SWITCH, Boolean.TRUE);
    }

    public static void disableMoleAspect() {
        ThreadLocalUtil.put(MOLE_ASPECT_SWITCH, Boolean.FALSE);
    }

    public static boolean isDisableMoleAspect() {
        Boolean moleAspect = (Boolean) ThreadLocalUtil.get(MOLE_ASPECT_SWITCH);
        return Boolean.FALSE.equals(moleAspect);
    }
}
