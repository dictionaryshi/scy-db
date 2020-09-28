package com.scy.db.constant;

/**
 * DbConstant
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/25.
 */
public class DbConstant {

    private DbConstant() {
    }

    public static final String SELECT_LAST_INSERT_ID = "SELECT LAST_INSERT_ID()";

    public static final String DATA_SOURCE = "DataSource";

    public static final String DATA_SOURCE_MASTER = "DataSourceMaster";

    public static final String DATA_SOURCE_SLAVE = "DataSourceSlave";

    public static final String TRANSACTION_MANAGER = "TransactionManager";

    public static final String SQL_SESSION_FACTORY = "SqlSessionFactory";

    public static final String SQL_SESSION_TEMPLATE = "SqlSessionTemplate";

    public static final String MAPPER_SCANNER_CONFIGURER = "MapperScannerConfigurer";
}
