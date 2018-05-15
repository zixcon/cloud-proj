package com.cloud.frame.demo.provider.interceptor;

import com.cloud.frame.demo.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by wd on 2018/5/10.
 */
@Aspect
@Component
@Slf4j
public class LogMonitorAspect {


    @Pointcut("execution(* com.cloud.frame.demo.provider.controller.*.*(..))")
    private void apiLog() {
    }


    @Around("apiLog()")
    public Object doSeviceLog(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() + "()";
        StringBuilder args = new StringBuilder();
        Object[] objArgs = pjp.getArgs();
        if (objArgs.length > 0) {
            args.append("(");
            args.append(GsonUtil.getInstance().toJson(objArgs[0]));
            for (int i = 1; i < objArgs.length; i++) {
                args.append(",").append(GsonUtil.getInstance().toJson(objArgs[i]));
            }
            args.append(")");
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj;
        try {
            obj = pjp.proceed();
        } catch (Exception e) {
            log.error("error: ", e);
            throw e;
        } finally {
            stopWatch.stop();
            log.info("time:{}ms, method:{}, args:{}", stopWatch.getTotalTimeMillis(), method, args.toString());
        }
        return obj;
    }
}
