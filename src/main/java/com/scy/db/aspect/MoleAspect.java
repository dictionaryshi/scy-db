package com.scy.db.aspect;

import com.scy.core.thread.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

@Slf4j
@Order(MoleAspect.MOLE)
@Aspect
public class MoleAspect {

    public static final int MOLE = 24000;

    public static final String MOLE_ASPECT_SWITCH = "mole_aspect_switch";

    @Pointcut("@annotation(com.scy.db.annotation.Mole)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        if (isDisableMoleAspect()) {
            enableMoleAspect();
            return joinPoint.proceed();
        }

        return null;
    }

    public static void enableMoleAspect() {
        ThreadLocalUtil.put(MOLE_ASPECT_SWITCH, Boolean.TRUE);
    }

    public static void disableMoleAspect() {
        ThreadLocalUtil.put(MOLE_ASPECT_SWITCH, Boolean.FALSE);
    }

    public static boolean isDisableMoleAspect() {
        Boolean moleAspect = (Boolean) ThreadLocalUtil.get(MOLE_ASPECT_SWITCH);
        return Boolean.FALSE.equals(moleAspect);
    }
}
