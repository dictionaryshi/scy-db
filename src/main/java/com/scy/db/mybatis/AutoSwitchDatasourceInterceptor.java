package com.scy.db.mybatis;

import com.scy.core.ObjectUtil;
import com.scy.core.StringUtil;
import com.scy.core.format.MessageUtil;
import com.scy.db.constant.DbConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * AutoSwitchDatasourceInterceptor
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/25.
 */
@Slf4j
@Intercepts({
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        )
})
public class AutoSwitchDatasourceInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        boolean isLastInsert = ObjectUtil.equals(DbConstant.SELECT_LAST_INSERT_ID, boundSql.getSql());

        long start = System.currentTimeMillis();
        Object objectValue = invocation.proceed();
        long end = System.currentTimeMillis();
        if (isLastInsert) {
            return objectValue;
        }

        String id = mappedStatement.getId();
        log.info(MessageUtil.format("mybatis intercept",
                "id", id, "sql", boundSql.getSql(), StringUtil.COST, end - start,
                "params", parameter == null ? StringUtil.EMPTY : parameter.toString()));

        return objectValue;
    }
}
