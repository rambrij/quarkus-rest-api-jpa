package com.javacoderhint.utility;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Logger;

@Interceptor
@LogEvent
public class EmployeeServiceInterceptor {

    private Logger logger;

    @AroundInvoke
    public Object logEvent(InvocationContext invocationContext) throws Exception {
        logger = Logger.getLogger(invocationContext.getTarget().getClass().getName());
        System.out.println("log.........");
        logger.info("Method: " + invocationContext.getMethod().getName());
        logger.info("Arguments: " + invocationContext.getParameters());
        logger.info("Executing the called method");
        Object possibleReturn = invocationContext.proceed();
        logger.info("The object the method was invoked on:" + invocationContext.getTarget().getClass().getName());

        return possibleReturn;
    }

    @AroundInvoke
    public Object request(InvocationContext invocationContext) throws Exception {
        logger = Logger.getLogger(invocationContext.getTarget().getClass().getName());
        System.out.println("log.........");
        logger.info("Method: " + invocationContext.getMethod().getName());
        logger.info("Arguments: " + invocationContext.getParameters());
        logger.info("Executing the called method");
        Object possibleReturn = invocationContext.proceed();
        logger.info("The object the method was invoked on:" + invocationContext.getTarget().getClass().getName());

        return possibleReturn;
    }
}