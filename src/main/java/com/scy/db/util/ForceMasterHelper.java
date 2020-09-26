package com.scy.db.util;

import com.scy.core.thread.ThreadLocalUtil;
import com.scy.db.datasource.RoutingDataSource;

/**
 * ForceMasterHelper
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/26.
 */
public class ForceMasterHelper {

    private ForceMasterHelper() {
    }

    public static final String FORCE_MASTER = "force_master";

    public static void forceMaster() {
        // 选择主库
        RoutingDataSource.routingMaster();

        // 添加强制使用主库标识
        ThreadLocalUtil.put(FORCE_MASTER, Boolean.TRUE);
    }

    public static Boolean getForceMaster() {
        return (Boolean) ThreadLocalUtil.get(FORCE_MASTER);
    }

    public static void clearForceMaster() {
        ThreadLocalUtil.remove(FORCE_MASTER);
    }
}
