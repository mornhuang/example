package com.taifook.adminportal.listeners;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.util.DiskFileUtil;

public class SessionListener implements HttpSessionListener {
	private Log log = null;
	
	public void sessionCreated(HttpSessionEvent arg0) {
		log = LogFactory.getLog(this.getClass());		
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		//ɾ���û���ʱĿ¼
		File tempFilePath=(File)arg0.getSession().getAttribute(Constants.TEMP_FILE_PATH);
		if (tempFilePath != null && tempFilePath.exists()) {
			DiskFileUtil.deepDeleteFilePath(tempFilePath);
			log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss  "))
					.format(new Date())
					+ " deleted user temp directory: "+tempFilePath.toString());
		}
	}
}
