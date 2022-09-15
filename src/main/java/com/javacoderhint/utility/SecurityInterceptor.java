package com.javacoderhint.utility;

import javax.interceptor.Interceptor;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Interceptor
@LogRequestEvent
public class SecurityInterceptor implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext context) {
        System.out.println("context object  :" +  context.getMethod());
        System.out.println("context object  :" +  context.getUriInfo().getPath());
        System.out.println("context object   " +  context.getEntityStream().toString());
    }
}