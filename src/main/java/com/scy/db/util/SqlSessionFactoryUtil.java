package com.scy.db.util;

import com.scy.core.ObjectUtil;
import com.scy.core.enums.ResponseCodeEnum;
import com.scy.core.exception.BusinessException;
import com.scy.db.mybatis.AutoSwitchDatasourceInterceptor;
import org.apache.ibatis.session.*;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;

import javax.sql.DataSource;

/**
 * SqlSessionFactoryUtil
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/25.
 */
public class SqlSessionFactoryUtil {

    private SqlSessionFactoryUtil() {
    }

    public static SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

        sqlSessionFactoryBean.setPlugins(new AutoSwitchDatasourceInterceptor());

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();

        if (ObjectUtil.isNull(sqlSessionFactory)) {
            throw new BusinessException(ResponseCodeEnum.SYSTEM_EXCEPTION.getCode(), "getSqlSessionFactory error");
        }
        setDefaultConfiguration(sqlSessionFactory);

        return sqlSessionFactory;
    }

    public static void setDefaultConfiguration(SqlSessionFactory sqlSessionFactory) {
        Configuration configuration = sqlSessionFactory.getConfiguration();

        // 全局性地开启或关闭所有映射器配置文件中已配置的任何缓存。
        configuration.setCacheEnabled(Boolean.FALSE);

        // 延迟加载的全局开关。当开启时, 所有关联对象都会延迟加载。
        configuration.setLazyLoadingEnabled(Boolean.FALSE);

        // 是否允许单个语句返回多结果集。
        configuration.setMultipleResultSetsEnabled(Boolean.TRUE);

        // 使用列标签代替列名。
        configuration.setUseColumnLabel(Boolean.TRUE);

        // 允许JDBC支持自动生成主键, 需要数据库驱动支持。如果设置为true, 将强制使用自动生成主键。
        configuration.setUseGeneratedKeys(Boolean.TRUE);

        // 指定MyBatis应如何自动映射列到字段或属性。 NONE表示关闭自动映射; PARTIAL只会自动映射没有定义嵌套结果映射的字段。 FULL会自动映射任何复杂的结果集(无论是否嵌套)。
        configuration.setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);

        // 配置默认的执行器。SIMPLE就是普通的执行器; REUSE执行器会重用预处理语句(PreparedStatement); BATCH执行器不仅重用语句还会执行批量更新。
        configuration.setDefaultExecutorType(ExecutorType.SIMPLE);

        // 设置超时时间, 它决定数据库驱动等待数据库响应的秒数。
        configuration.setDefaultStatementTimeout(50);

        // 是否开启驼峰命名自动映射
        configuration.setMapUnderscoreToCamelCase(Boolean.TRUE);

        // 默认值为SESSION, 会缓存一个会话中执行的所有查询。若设置值为STATEMENT, 本地缓存将仅用于执行语句, 对相同SqlSession的不同查询将不会进行缓存。
        configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);

        // 当返回行的所有列都是空时, MyBatis默认返回null。 当开启这个设置时, MyBatis会返回一个空实例。
        configuration.setReturnInstanceForEmptyRow(Boolean.FALSE);
    }
}
