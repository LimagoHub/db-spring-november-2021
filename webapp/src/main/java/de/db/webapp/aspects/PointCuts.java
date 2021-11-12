package de.db.webapp.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    @Pointcut(value = "execution(public * de.db.webapp.controllers.PersonenQueryController.*(..))")
    public void personenControllerMethods() {}


    @Pointcut(value = "within(@org.springframework.web.bind.annotation.RestController *)" )
    public void restControllerMethodes() {}
}
