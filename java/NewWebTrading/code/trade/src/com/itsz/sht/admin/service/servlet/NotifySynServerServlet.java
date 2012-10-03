package com.itsz.sht.admin.service.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.admin.service.AdminServiceDelegate;
import com.taifook.common.socket.ServerSocketService;
import com.taifook.common.socket.SynDataCfgParser;


public class NotifySynServerServlet extends HttpServlet {
	
	public void init() throws ServletException{

	}
	
	public void init(ServletConfig cfg)throws ServletException{
		Log log =LogFactory.getLog(this.getClass());
		
		// �����й�socket�������ļ���Ϣ
		try {
			new SynDataCfgParser().parserNotifyServerCfg();
		} catch (Exception e) {
			log.error("parser SynData Server config Exception:"+ e.getMessage());
		}
		
		try{
			ServerSocketService.getInstance().start();
		}catch(Exception e){
			log.error("SynData Server Exception:"+ e.getMessage());
		}		

	}
	
	public void destory(){

	}
	

}
