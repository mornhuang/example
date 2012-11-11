package cn.hxex.springcore.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggerBean {

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
