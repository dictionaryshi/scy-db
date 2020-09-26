package com.scy.db.transaction;

import com.scy.db.util.ForceMasterHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.NonNull;
import org.springframework.transaction.TransactionDefinition;

import javax.sql.DataSource;

/**
 * ForceMasterDataSourceTransactionManager
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/26.
 */
@Slf4j
public class ForceMasterDataSourceTransactionManager extends DataSourceTransactionManager {

    public ForceMasterDataSourceTransactionManager(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    protected void doBegin(@NonNull Object transaction, @NonNull TransactionDefinition definition) {
        // 事务环境下切换至主库
        ForceMasterHelper.forceMaster();

        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCleanupAfterCompletion(@NonNull Object transaction) {
        super.doCleanupAfterCompletion(transaction);

        ForceMasterHelper.clearForceMaster();
    }
}
