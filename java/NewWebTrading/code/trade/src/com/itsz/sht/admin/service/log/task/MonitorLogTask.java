package com.itsz.sht.admin.service.log.task;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.admin.common.AbstractTask;
import com.itsz.sht.admin.service.SystemMonitorMgrService;
import com.taifook.adminportal.model.CsServicemonitor;

public class MonitorLogTask extends AbstractTask {
	private String serverName;

	private String status;

	private String actionName;

	private long startTime;

	private long endTime;
	protected static Log log = LogFactory.getLog(MonitorLogTask.class);
	
	public MonitorLogTask(){
		
	}

	public MonitorLogTask(String serverName, String status,String actionName, long startTime, long endTime){
		this.serverName=serverName;
		this.status=status;
		this.actionName=actionName;
		this.startTime=startTime;
		this.endTime=endTime;
	}
	
	public void runTask() {
		try {
			CsServicemonitor monitorInfo = new CsServicemonitor();
			monitorInfo.setIp(InetAddress.getLocalHost().getHostAddress());
			monitorInfo.setServicename(serverName);
			monitorInfo.setActionId(actionName);
			monitorInfo.setStatus(status);
			monitorInfo.setAccesstime(new Date(startTime));
			monitorInfo.setExpendtime(endTime - startTime);
			
			SystemMonitorMgrService.getInstance().saveServiceMonitor(monitorInfo);

		} catch (Exception e) {
			log.info(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
					.format(new Date())
					+ serverName + " log monitor to database fail!......");
		}
	}

	public static Log getLog() {
		return log;
	}

	public static void setLog(Log log) {
		MonitorLogTask.log = log;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
