package com.taifook.adminportal.job.task;

import java.util.TimerTask;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.parameter.ParameterManager;
import com.taifook.adminportal.service.OnLineUserService;

public class ClearOnlineUserTask extends TimerTask {

	private long livetime = 16 * 60;

	public ClearOnlineUserTask() {
		try {
			livetime = Long
					.parseLong(ParameterManager
							.getParameterValueByKey(Constants.ParamKey.DELETE_ONLINEUSER_LIVE_TIME));
		} catch (Exception e) {

		}
	}

	public synchronized void run() {
		new OnLineUserService().deletebyLiveTime(livetime * 60);
	}

}
