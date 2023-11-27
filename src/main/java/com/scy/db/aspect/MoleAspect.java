package com.scy.db.aspect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.scy.core.db.SqlUtil;
import com.scy.core.format.DateUtil;
import com.scy.core.json.JsonUtil;
import com.scy.core.model.JoinPointBO;
import com.scy.core.spring.ApplicationContextUtil;
import com.scy.core.spring.JoinPointUtil;
import com.scy.core.thread.ThreadLocalUtil;
import com.scy.db.annotation.Mole;
import com.scy.db.mapper.MoleTaskDOMapper;
import com.scy.db.model.MoleTaskDO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

@Slf4j
@Order(MoleAspect.MOLE)
@Aspect
public class MoleAspect {

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
        moleTaskDO.setExeCount(1);
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

        return null;
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
