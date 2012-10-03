package com.itsz.sht.listener;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;

import com.itsz.sht.admin.broadcast.util.BroadcastInfo;
import com.itsz.sht.admin.broadcast.util.BroadcastManagement;
import com.itsz.sht.admin.broadcast.util.BroadcastParser;
import com.itsz.sht.admin.channel.util.ChannelInfoManagement;
import com.itsz.sht.admin.channel.util.ChannelParser;
import com.itsz.sht.admin.parameter.util.ParaInfo;
import com.itsz.sht.admin.parameter.util.ParaManagement;
import com.itsz.sht.admin.parameter.util.ParaParser;
import com.itsz.sht.admin.service.AdminServiceDelegate;
import com.itsz.sht.admin.service.util.ServiceManagement;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.LoggerFactory;
import com.itsz.sht.common.user.UserManagement;
import com.itsz.sht.common.user.UserObject;

/**
 * 
 * $Id: ContextSessionListener.java,v 1.1 2010/11/09 03:57:30 kyzou Exp $
 * @Project:portal
 * @File:ContextSessionListener.java
 * @Description:
 * @Author:
 * @Date:2007-7-12
 */
public class ContextSessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {
    
    Log commonInfo = LoggerFactory.getInstance().getCommonInfo();
    
	public void sessionCreated(HttpSessionEvent arg0) {

	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();		
		// ��admin��ݿ���ɾ��ǰ�����û�?		
		Object obj=session.getAttribute(Constants.USER);
		if(obj!=null && obj instanceof UserObject){
			UserObject user=(UserObject)obj;
			if(UserManagement.removeUser(user.getChannelType(),user.getLoginName(),user.getSessionID())){
		    	//��admin��ݿ���ɾ��ǰ�����û�?		
		    	AdminServiceDelegate.getInstance().processUserLogoutLog(user);
		    }			
		}
	}

	public void attributeAdded(HttpSessionBindingEvent arg0) {
		HttpSession session = arg0.getSession();
		String infoStr = "=======ContextSessionListener attributeAdded====sessionID="
						+ session.getId()
						+ "    attributeName="
						+ arg0.getName()
						+ "   attributeValue="
						+ session.getAttribute(arg0.getName());
        commonInfo.info(infoStr);
		if (arg0.getName().equals(Constants.AdminOperate)
				&& session.getAttribute(arg0.getName()) != null) {
			List paralist = (List) session.getAttribute(arg0.getName());
			operate(paralist);
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		HttpSession session = arg0.getSession();
		String infoStr ="=======ContextSessionListener attributeRemoved====sessionID="
						+ session.getId()
						+ "    attributeName="
						+ arg0.getName()
						+ "   attributeValue="
						+ arg0.getValue();
        commonInfo.info(infoStr);
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		HttpSession session = arg0.getSession();
		String infoStr = "=======ContextSessionListener attributeReplaced=====sessionID="
						+ session.getId()
						+ "    attributeName="
						+ arg0.getName()
						+ "   attributeValue="
						+ session.getAttribute(arg0.getName());
        commonInfo.info(infoStr);
		if (arg0.getName().equals(Constants.AdminOperate)
				&& session.getAttribute(arg0.getName()) != null) {
			List paralist = (List) session.getAttribute(arg0.getName());
			operate(paralist);
		}

	}

	private void operate(List paralist) {
		String operate = paralist.get(0).toString();
		if ("ChangePassword".equals(operate)) {
			String[] para = (String[]) paralist.get(1);
			try {
				ChannelParser cp = new ChannelParser();
				cp.changePassword(para[0], para[1]);
			} catch (Exception e) {
                commonInfo.error("error change password");
//				e.printStackTrace();
			}
		} else if ("AddBroadcast".equals(operate)) {
			BroadcastInfo broadcast = (BroadcastInfo) paralist.get(1);
			if (!BroadcastManagement.isBroadcastExist(broadcast.getId())) {
				BroadcastParser bp = null;
				try {
					bp = new BroadcastParser("en_US");
					bp.addBroadcast(broadcast);
					bp = new BroadcastParser("zh_CN");
					bp.addBroadcast(broadcast);
					bp = new BroadcastParser("zh_TW");
					bp.addBroadcast(broadcast);
					BroadcastManagement.addBroadcast(broadcast);
				} catch (Exception e) {
                    commonInfo.error("error add broadcast");
//					e.printStackTrace();
				}
			}
		} else if ("ModBroadcast".equals(operate)) {
			BroadcastInfo broadcast = (BroadcastInfo) paralist.get(1);
			BroadcastParser bp = null;
			try {
				bp = new BroadcastParser("en_US");
				bp.modBroadcast(broadcast);
				bp = new BroadcastParser("zh_CN");
				bp.modBroadcast(broadcast);
				bp = new BroadcastParser("zh_TW");
				bp.modBroadcast(broadcast);
				BroadcastManagement.modBroadcast(broadcast);
			} catch (Exception e) {
                commonInfo.error("error modify broadcast");
//				e.printStackTrace();
			}
		} else if ("DelBroadcast".equals(operate)) {
			String[] id = (String[]) paralist.get(1);
			for (int i = 0; i < id.length; i++) {
				try {
					BroadcastParser bp = new BroadcastParser("en_US");
					bp.removeBroadcast(id[i]);
					bp = new BroadcastParser("zh_CN");
					bp.removeBroadcast(id[i]);
					bp = new BroadcastParser("zh_TW");
					bp.removeBroadcast(id[i]);
					BroadcastManagement.removeBroadcast(id[i]);
				} catch (Exception e) {
                    commonInfo.error("error del broadcast");
//					e.printStackTrace();
				}
			}
		} else if ("StartChannel".equals(operate)) {
			String[] channelcode = (String[]) paralist.get(1);
			try {
				ChannelParser cp = new ChannelParser();
				cp.startChannel(channelcode);
				ChannelInfoManagement.startChannel(channelcode);
			} catch (Exception e) {
                commonInfo.error("error StartChannel");
//				e.printStackTrace();
			}
		} else if ("StopChannel".equals(operate)) {
			String[] channelcode = (String[]) paralist.get(1);
			try {
				ChannelParser cp = new ChannelParser();
				cp.stopChannel(channelcode);
				ChannelInfoManagement.stopChannel(channelcode);
			} catch (Exception e) {
                commonInfo.error("error StopChannel");
//				e.printStackTrace();
			}
		} else if ("setServiceState".equals(operate)) {
			String[] para = (String[]) paralist.get(1);
			ServiceManagement.setServiceState(para[0], para[1]);
		} else if ("AddParaInfo".equals(operate)) {
			ParaInfo pi = (ParaInfo) paralist.get(1);
			try {
				ParaParser pp = new ParaParser();
				pp.addParaInfo(pi);
				ParaManagement.putParaTable(pi.getKey(), pi.getValue());
			} catch (Exception e) {
                commonInfo.error("error parse parameter xmlfile");
//				e.printStackTrace();
			}
		} else if ("ModParaInfo".equals(operate)) {
			ParaInfo pi = (ParaInfo) paralist.get(1);
			try {
				ParaParser pp = new ParaParser();
				pp.modParaInfo(pi);
				ParaManagement.putParaTable(pi.getKey(), pi.getValue());
			} catch (Exception e) {
                commonInfo.error("error parse parameter xmlfile");
//				e.printStackTrace();
			}
		} else if ("DelParaInfo".equals(operate)) {
			String[] key = (String[]) paralist.get(1);
			for (int i = 0; i < key.length; i++) {
				try {
					ParaParser pp = new ParaParser();
					pp.delParaInfo(key[i]);
					ParaManagement.remove(key[i]);
				} catch (Exception e) {
                    commonInfo.error("error parse parameter xmlfile");
//					e.printStackTrace();
				}
			}
		}
	}
}
