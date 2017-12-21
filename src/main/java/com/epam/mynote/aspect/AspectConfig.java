package com.epam.mynote.aspect;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Log
@Component
@Aspect
@Configuration
public class AspectConfig {

    @Around("@annotation(LogForExecutionTime)")
    public Object aroundExecute(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis() - start;

        log.info( proceedingJoinPoint.getSignature() + " execution time = " + end + " ms");

        return proceed;
    }

    //Теперь ты можешь устанавливать над методами аннотацию @LogForExecutionTime - она будет исполняться



}
