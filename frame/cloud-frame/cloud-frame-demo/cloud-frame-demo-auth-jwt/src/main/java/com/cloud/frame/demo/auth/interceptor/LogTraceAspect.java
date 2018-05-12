package com.cloud.frame.demo.auth.interceptor;

import com.cloud.frame.demo.auth.util.GsonUtils;
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
public class LogTraceAspect {


    @Pointcut("execution(* com.cloud.frame.demo.auth.service.*.*(..))")
    private void seviceLog() {
    }

    @Around("seviceLog()")
    public Object doSeviceLog(ProceedingJoinPoint pjp) throws Throwable {
        String method = pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName() + "()";
        StringBuilder args = new StringBuilder();
        Object[] objArgs = pjp.getArgs();
        if (objArgs.length > 0) {
            args.append("(");
            args.append(GsonUtils.getInstance().toJson(objArgs[0]));
            for (int i = 1; i < objArgs.length; i++) {
                args.append(",").append(GsonUtils.getInstance().toJson(objArgs[i]));
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
