package com.scy.db.transaction;

import com.scy.core.StringUtil;
import com.scy.core.SystemUtil;
import com.scy.core.format.MessageUtil;
import com.scy.core.thread.ThreadLocalUtil;
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

    public static final String TRANSACTION_START_TIME = "transaction_start_time";

    public ForceMasterDataSourceTransactionManager(DataSource dataSource) {
        super(dataSource);

        super.setRollbackOnCommitFailure(Boolean.TRUE);
    }

    public static void setTransactionStartTime() {
        ThreadLocalUtil.put(TRANSACTION_START_TIME, SystemUtil.currentTimeMillis());
    }

    public static Long getTransactionStartTime() {
        return (Long) ThreadLocalUtil.get(TRANSACTION_START_TIME);
    }

    @Override
    protected void doBegin(@NonNull Object transaction, @NonNull TransactionDefinition definition) {
        log.info("transaction begin");

        setTransactionStartTime();

        // 事务环境下切换至主库
        ForceMasterHelper.forceMaster();

        super.doBegin(transaction, definition);
    }

    @Override
    protected void doCleanupAfterCompletion(@NonNull Object transaction) {
        super.doCleanupAfterCompletion(transaction);

        ForceMasterHelper.clearForceMaster();

        log.info(MessageUtil.format("transaction finish", StringUtil.COST, SystemUtil.currentTimeMillis() - getTransactionStartTime()));
    }
}
