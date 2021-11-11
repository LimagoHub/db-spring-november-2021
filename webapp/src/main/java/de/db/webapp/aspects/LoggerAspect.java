package de.db.webapp.aspects;


import lombok.extern.slf4j.Slf4j;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Aspect

public class LoggerAspect {

    private Logger logger;

    public LoggerAspect(Logger logger) {
        this.logger = logger;
    }

    @Before("PointCuts.restControllerMethodes()")
    public void logAdvice(JoinPoint joinpoint) {

        String name = joinpoint.getSignature().getName();
        logger.warn("{} wurde gerufen.", name);
    }

    @AfterReturning(value = "PointCuts.personenControllerMethods()",returning = "retval")
    public void afterReturning(JoinPoint joinPoint, Object retval) {
        logger.warn("Rueckgabe: = {}", retval.toString());
    }
    @AfterThrowing(value = "execution(public * de.db.webapp.controllers.PersonenController.*(..))",throwing = "ex")
    public void afterReturning(JoinPoint joinPoint, Throwable ex) {
        logger.warn("Fehler: = {}", ex.getMessage());
    }

    @Around(value = "execution(public * de.db.webapp.controllers.PersonenController.*(..))")
    public Object timeMessung(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Instant start = Instant.now();

        Object result =  proceedingJoinPoint.proceed();

        Instant end = Instant.now();
        Duration duration  = Duration.between(start,end);
        logger.warn("Duration = {}", duration.toMillis());
        return result;
    }
}
