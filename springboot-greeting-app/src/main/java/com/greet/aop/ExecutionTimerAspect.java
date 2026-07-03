package com.greet.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Aspect that logs the execution time of every method
 * within the service and controller layers.
 */
@Aspect
@Component
@Slf4j
public class ExecutionTimerAspect {

    @Around("execution(* com.greet.service..*(..)) || execution(* com.greet.controller..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info("{} executed in {} ms", joinPoint.getSignature().toShortString(), executionTime);

        return result;
    }
}
