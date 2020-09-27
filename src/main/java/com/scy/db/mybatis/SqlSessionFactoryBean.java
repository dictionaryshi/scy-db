package com.scy.db.mybatis;

import com.scy.core.ObjectUtil;
import com.scy.core.format.MessageUtil;
import com.scy.db.properties.DbProperties;
import com.scy.db.util.SqlSessionFactoryUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;

import javax.sql.DataSource;

/**
 * SqlSessionFactoryBean
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/27.
 */
@Slf4j
@Getter
@Setter
@ToString
public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory> {

    private SqlSessionFactory sqlSessionFactory;

    private DataSource dataSource;

    private DbProperties dbProperties;

    @Override
    public SqlSessionFactory getObject() throws Exception {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory(dataSource);
        Configuration configuration = sqlSessionFactory.getConfiguration();
        if (!ObjectUtil.isNull(dbProperties.getUseGeneratedKeys())) {
            log.info(MessageUtil.format("mybatis config", "useGeneratedKeys", dbProperties.getUseGeneratedKeys()));
            configuration.setUseGeneratedKeys(dbProperties.getUseGeneratedKeys());
        }
        if (!ObjectUtil.isNull(dbProperties.getDefaultStatementTimeout())) {
            log.info(MessageUtil.format("mybatis config", "defaultStatementTimeout", dbProperties.getDefaultStatementTimeout()));
            configuration.setDefaultStatementTimeout(dbProperties.getDefaultStatementTimeout());
        }
        this.sqlSessionFactory = sqlSessionFactory;
        return this.sqlSessionFactory;
    }

    @Override
    public Class<? extends SqlSessionFactory> getObjectType() {
        return this.sqlSessionFactory == null ? SqlSessionFactory.class : this.sqlSessionFactory.getClass();
    }
}
