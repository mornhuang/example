package cn.itsz.newsim.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.exception.ITSZException;

public class AopBeanProcess {
	protected static final Log logger = LogFactory.getLog(AopBeanProcess.class);

	public Object doApplyProcessor(ProceedingJoinPoint joinPoint) {
		Object o = null;
		try {
			logger.info("Method " + joinPoint.toLongString() + " is call, param: " + joinPoint.getArgs());
			o = joinPoint.proceed();
		} catch (ITSZException ex) {
			ResultMessage response = new ResultMessage();
			response.setReturnCode(Constants.Status.WARN);
			response.setErrorCode(ex.getErrorCode());
			o = response;
		} catch (Throwable e) {
			logger.error("Error {}", e);
			ResultMessage response = new ResultMessage();
			response.setReturnCode(Constants.Status.FAILED);
			o = response;
		} finally {
			logger.info("reponse result: " + o);
		}
		return o;
	}
}
