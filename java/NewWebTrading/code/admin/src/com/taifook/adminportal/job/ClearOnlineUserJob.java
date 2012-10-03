package com.taifook.adminportal.job;

import java.util.Timer;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.parameter.ParameterManager;
import com.taifook.adminportal.job.task.ClearOnlineUserTask;

public class ClearOnlineUserJob {

	private Timer runTimer;

	private long intervalTime = 1 * 60;
	
	private static final ClearOnlineUserJob instance=new ClearOnlineUserJob();

	private ClearOnlineUserJob() {
		try {
			intervalTime = Long
					.parseLong(ParameterManager
							.getParameterValueByKey(Constants.ParamKey.DELETE_ONLINEUSER_INTERVAL_TIME));
		} catch (Exception e) {

		}
	}

	public void start() {
		this.runTimer = new Timer();
		ClearOnlineUserTask task = new ClearOnlineUserTask();
		this.runTimer.schedule(task, 0, intervalTime * 60 * 1000);
	}

	public void stop() {
		if (this.runTimer != null) {
			this.runTimer.cancel();
			this.runTimer = null;
		}
	}
	
	public static ClearOnlineUserJob getInstance(){
		return instance;
	}

}
