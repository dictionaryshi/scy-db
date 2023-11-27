package com.scy.db.model;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class MoleTaskDO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 下一次执行时间
     */
    private Date nextExeTime;

    /**
     * 执行间隔
     */
    private Integer exeIntervalSec;

    /**
     * 执行次数
     */
    private Integer exeCount;

    /**
     * 最大执行次数
     */
    private Integer maxExeCount;

    /**
     * 任务状态
     */
    private Integer exeStatus;

    /**
     * 分库id
     */
    private Long sharedId;

    /**
     * 参数类型
     */
    private String paramTypeJson;

    /**
     * 类名
     */
    private String targetClassName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * spring bean名
     */
    private String beanName;

    /**
     * 环境
     */
    private String env;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 修改时间
     */
    private Date updatedAt;

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 参数
     */
    private String paramsJson;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNextExeTime(Date nextExeTime) {
        this.nextExeTime = nextExeTime;
    }

    public void setExeIntervalSec(Integer exeIntervalSec) {
        this.exeIntervalSec = exeIntervalSec;
    }

    public void setExeCount(Integer exeCount) {
        this.exeCount = exeCount;
    }

    public void setMaxExeCount(Integer maxExeCount) {
        this.maxExeCount = maxExeCount;
    }

    public void setExeStatus(Integer exeStatus) {
        this.exeStatus = exeStatus;
    }

    public void setSharedId(Long sharedId) {
        this.sharedId = sharedId;
    }

    public void setParamTypeJson(String paramTypeJson) {
        this.paramTypeJson = paramTypeJson == null ? null : paramTypeJson.trim();
    }

    public void setTargetClassName(String targetClassName) {
        this.targetClassName = targetClassName == null ? null : targetClassName.trim();
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName == null ? null : beanName.trim();
    }

    public void setEnv(String env) {
        this.env = env == null ? null : env.trim();
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    public void setParamsJson(String paramsJson) {
        this.paramsJson = paramsJson == null ? null : paramsJson.trim();
    }
}