package com.scy.db.annotation;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mole {

    /**
     * 延迟执行时间
     */
    int delayTime() default 60;

    /**
     * 执行间隔
     */
    int intervalTime() default 30;

    /**
     * 最大执行次数
     */
    int maxExeCount() default 3;
}
