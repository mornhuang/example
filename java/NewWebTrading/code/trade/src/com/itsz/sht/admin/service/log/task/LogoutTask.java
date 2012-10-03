package com.itsz.sht.admin.service.log.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.admin.common.AbstractTask;
import com.itsz.sht.admin.service.OnLineUserMgrService;
import com.taifook.adminportal.model.CsOnlineuserKey;

public class LogoutTask extends AbstractTask {
	
	private static Log log = LogFactory.getLog(UserActionLogTask.class);
	
	private String channelcode;
	private String userid;
	
	public LogoutTask(){
		
	}
	
	public LogoutTask(String channelcode, String userid){
		this.channelcode=channelcode;
		this.userid=userid;
	}	

	public String getChannelcode() {
		return channelcode;
	}

	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public void runTask(){			
			OnLineUserMgrService service = OnLineUserMgrService.getInstance();			
			CsOnlineuserKey key = new CsOnlineuserKey();
			//key.setChannelcode(user.getChannelType()+ com.taifook.adminportal.common.Constants.DELIMITER+ user.getChannelID());
			key.setChannelcode(this.channelcode);
			key.setUserid(this.userid);
			log.info(key.getChannelcode() + "xxxx" + key.getUserid());
			service.deletebyId(key);
		}
}
