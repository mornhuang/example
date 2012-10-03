package com.itsz.sht.admin.service.thread;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import EDU.oswego.cs.dl.util.concurrent.Channel;
import EDU.oswego.cs.dl.util.concurrent.LinkedQueue;
import EDU.oswego.cs.dl.util.concurrent.PooledExecutor;

import com.itsz.sht.admin.common.Task;
import com.itsz.sht.admin.service.util.WebAdminCfgManager;

public class EduThreadPoolManager {
	
	private static Log log = LogFactory.getLog(EduThreadPoolManager.class);
	
	private static int miniThreadSize = 5;

	private static int maxThreadSize = 10;
	
	private static int aliveTime=900;

	private static Channel queue = null;

	private static PooledExecutor pool = null;

	static {
		init();
	}

	private static void init() {
		try {
			miniThreadSize = Integer.parseInt((String) WebAdminCfgManager
					.getInstance().getParamValue("threadpool-config",
							"miniThreadSize"));
		} catch (Exception e) {
			miniThreadSize =5;
		}

		try {
			maxThreadSize = Integer.parseInt((String) WebAdminCfgManager
					.getInstance().getParamValue("threadpool-config",
							"maxThreadSize"));
		} catch (Exception e) {
			maxThreadSize = 10;
		}
		
		try {
			aliveTime = Integer.parseInt((String) WebAdminCfgManager
					.getInstance().getParamValue("threadpool-config",
							"aliveTime"));
		} catch (Exception e) {
			aliveTime = 900;
		}
		
		queue = new LinkedQueue();
		pool = new PooledExecutor(queue);
		
		pool.setMinimumPoolSize(miniThreadSize);
		pool.setMaximumPoolSize(maxThreadSize);	
		pool.setKeepAliveTime(aliveTime*1000);

	}
	
	public static PooledExecutor getPool(){
		return pool;
	}
	
	public static void processTask(Task task) {
		try {			
			log.info("++++++++++++++will be add task to queue++++++++++++++++++");
			pool.execute(task);
			log.info("++++++++++++++add task to queue success++++++++++++++++++");		
		} catch (Exception e) {
			log.info("+++++++++++++process task is exception "+e.getMessage());
		}
	}
	
	public static void cancelThread() {
		pool.shutdownNow();
		log.info("++++++++++++++task manager shutdown++++++++++++++++++");
	}

}
