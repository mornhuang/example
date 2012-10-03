package cn.itsz.newsim.schedule;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.itsz.newsim.manage.OrderManager;

public class QuartzJob {

	private static Log logger = LogFactory.getLog(QuartzJob.class);
	@Autowired
	private OrderManager orderManager;

	public void execute() {
		logger.info("There are matching start...");
		orderManager.matching();
		logger.info("There are matching end...");
	}
	
	public void removeAllOrder() {
		logger.info("There are removeAllOrder start...");
		orderManager.removeAllOrder();
		logger.info("There are removeAllOrder end...");
	}
	
	public void removeQueuingOrder() {
		logger.info("There are removeQueuingOrder after trading time start...");
		orderManager.removeQueuingOrder();
		logger.info("There are removeQueuingOrder after trading time end...");
	}
}
