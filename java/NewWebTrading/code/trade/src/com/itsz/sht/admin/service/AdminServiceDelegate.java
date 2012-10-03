package com.itsz.sht.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.admin.service.log.task.LogoutTask;
import com.itsz.sht.admin.service.log.task.MonitorLogTask;
import com.itsz.sht.admin.service.log.task.UserActionLogTask;
import com.itsz.sht.admin.service.thread.TaskManager;
import com.itsz.sht.admin.service.util.WebAdminCfgParser;
import com.itsz.sht.common.user.UserManagement;
import com.itsz.sht.common.user.UserObject;
import com.taifook.adminportal.common.HibernateBase;
import com.taifook.common.socket.ServerSocketService;

public class AdminServiceDelegate {
	
	private static Log log = LogFactory.getLog(AdminServiceDelegate.class);
	
	private static AdminServiceDelegate delegate;

	private AdminServiceDelegate() {

	}

	public void processUserActionLog(String loginName, String channelID,
			String channelType, String sessionID,String IP,String actionType) {
		UserActionLogTask logTask=new UserActionLogTask(loginName, channelID,channelType,sessionID,IP,actionType);		
		TaskManager.processTask(logTask);
	}

	public void processMonitorLog(String serverName, String status,
			String actionName, long startTime, long endTime) {
			MonitorLogTask logTask=new MonitorLogTask(serverName, status,actionName, startTime, endTime);
			TaskManager.processTask(logTask);
	}

	public void processUserLogoutLog(UserObject user) {		
		//UserObject user = (UserObject) session.getAttribute(Constants.USER);
		if (user != null) {			
			if(UserManagement.activeUser(user.getChannelType(),user.getLoginName())){
				return;
			}			
			LogoutTask logTask=new LogoutTask(user.getChannelType(), user.getLoginName());		
			TaskManager.processTask(logTask);
		}
	}

	public void processAppStartLog() {
		// �ѷ�����Ļ������?1
		ChannelMgrService service = ChannelMgrService.getInstance();
		service.increaseCurrentChannelServerActiveCount(1);
		// ����Monitor�йص�XML��������
		try {
			(new WebAdminCfgParser()).parser();
		} catch (Exception e) {
			log.error("error parse monitor xmlfile");
		}
	}

	public void processAppStopLog() {
		OnLineUserMgrService onLineUserservice;
		ChannelMgrService channelservice;
		// �ѷ�����Ļ�����1
		channelservice = ChannelMgrService.getInstance();
		channelservice.increaseCurrentChannelServerActiveCount(-1);
		if (channelservice.getCurrentChannelServerActiveCount() < 1) {
			onLineUserservice = OnLineUserMgrService.getInstance();
			onLineUserservice.deleteAll();
		}

		// �ر���admin��l����ݿ�l��
		HibernateBase.closeConnection();

		// ֹͣ��̨���ͬ���������
		try {
			ServerSocketService.getInstance().stopService();
		} catch (Exception e) {
			log.error("+++++++++stop SynData Server Exception, "+e.getMessage()+"+++++++++");
		}

		// ֹͣ��̨״̬��������߳�?
		SystemMonitorManager.cancelMonitor();
		// ֹͣ��̨��־�����߳�
		TaskManager.cancelThread();
	}

	public static AdminServiceDelegate getInstance() {
		if (delegate == null) {
			delegate = new AdminServiceDelegate();
		}
		return delegate;
	}

}
