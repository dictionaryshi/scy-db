package com.scy.db.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * DruidMonitorBO
 *
 * @author shichunyang
 * Created by shichunyang on 2020/9/28.
 */
@Getter
@Setter
@ToString
public class DruidMonitorBO {

    private String url;

    /**
     * 最小连接池数量
     */
    private int minIdle;

    /**
     * 最大连接池数量
     */
    private int maxActive;

    private int allCount;

    private int poolingCount;

    private int activeCount;
}
