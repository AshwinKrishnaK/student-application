package com.example.student.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

@Aspect
public class LoggerAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggerAspect.class);

    @Around("execution(* com.example.student.service.*.*(..))")
    public Object loggerAspect(ProceedingJoinPoint joinPoint) throws Throwable{
        log.debug("{} Method has started!",joinPoint.getSignature().toString());
        Instant startTime = Instant.now();
        Object result = joinPoint.proceed();
        Instant endTime = Instant.now();
        var timeElapsed = Duration.between(endTime,startTime).toMillis();
        log.info("Time taken for the method {} is {}", joinPoint.getSignature().toString(), timeElapsed);
        log.debug("{} Method has Ended!", joinPoint.getSignature().toString());
        return result;
    }

    @AfterThrowing(value = "execution(* com.example.student.service.*.*(..))", throwing = "ex" )
    public void logException(JoinPoint joinPoint, Exception ex){
        log.error("Exception occurred in Method {} and Exception is {}", joinPoint.getSignature().toString(), ex.getMessage());
    }
}
