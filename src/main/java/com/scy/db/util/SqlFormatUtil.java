package com.scy.db.util;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.util.JdbcConstants;

/**
 * SqlFormatUtil
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/25.
 */
public class SqlFormatUtil {

    private SqlFormatUtil() {
    }

    public static String format(String sql) {
        return SQLUtils.format(sql, JdbcConstants.MYSQL, SQLUtils.DEFAULT_LCASE_FORMAT_OPTION);
    }
}
