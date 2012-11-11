package cn.hxex.springcore.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggerAspect {

    @Pointcut("execution(public * *(..))")
    public void publicMethods() { }

    @Pointcut("execution(* cn.hxex.springcore.aspectj.LoggerAspect.*(..))")
    public void logObjectCalls() { }

    @Pointcut("publicMethods()&&!logObjectCalls()")
    public void loggableCalls() { }

	
    @Around("loggableCalls()")
	public Object aroundLogCalls(ProceedingJoinPoint joinPoint)
			throws Throwable {
		System.out.println("before invoke method:"
				+ joinPoint.getSignature().getName());
		Object object = joinPoint.proceed();
		System.out.println("after invoke method:"
				+ joinPoint.getSignature().getName());
		return object;
	}

}
