/*
 * Created on 2005-8-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.sht.admin.service.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.itsz.sht.common.Constants;
import com.itsz.sht.listener.SessionManagement;

/**
 * @author lmzhang
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ServiceManagement {

	private static HashMap serviceinfo;// ��̨������Ϣ

	// ��ʼ������״̬��Ĭ�����еķ���״̬����
	public static void init() {
		if (serviceinfo == null) {
			serviceinfo = new HashMap();
		}
		serviceinfo.put("eServices", "normal");
		serviceinfo.put("MCS", "normal");
		serviceinfo.put("RTQ", "normal");
	}

	// ��ȡ���з������Ϣ
	public static List getServiceInfo() {
		if (serviceinfo == null) {
			return null;
		}
		List list = new ArrayList();
		Iterator it = serviceinfo.keySet().iterator();
		while (it.hasNext()) {
			ServiceInfo si = new ServiceInfo();
			String key = (String) it.next();
			si.setName(key);
			si.setState(serviceinfo.get(key).toString());
			list.add(si);
		}
		return list;
	}

	// ����ĳ�����״̬
	public static void setServiceState(String service, String state) {
		if (serviceinfo == null) {
			return;
		}
		serviceinfo.put(service, state);
	}
	
	//�õ�ĳ�����״̬
	public static String getServiceState(String service) {
		if (serviceinfo.containsKey(service)) {
			return (String) serviceinfo.get(service);
		} else {
			return null;
		}
	}

	//
	public static void setEServExcept(HttpSession session) {
		String[] para = { "eServices", "exceptional" };
		List paralist = new ArrayList();
		paralist.add(0, "setServiceState");
		paralist.add(1, para);
		SessionManagement.setSessionAttribute(session, Constants.AdminOperate,
				paralist);
	}

	//
	public static void setEServNormal(HttpSession session) {
		String[] para = { "eServices", "normal" };
		List paralist = new ArrayList();
		paralist.add(0, "setServiceState");
		paralist.add(1, para);
		SessionManagement.setSessionAttribute(session, Constants.AdminOperate,
				paralist);
	}

	//
	public static void setMCSExcept(HttpSession session) {
		String[] para = { "MCS", "exceptional" };
		List paralist = new ArrayList();
		paralist.add(0, "setServiceState");
		paralist.add(1, para);
		SessionManagement.setSessionAttribute(session, Constants.AdminOperate,
				paralist);
	}

	//
	public static void setMCSNormal(HttpSession session) {
		String[] para = { "MCS", "normal" };
		List paralist = new ArrayList();
		paralist.add(0, "setServiceState");
		paralist.add(1, para);
		SessionManagement.setSessionAttribute(session, Constants.AdminOperate,
				paralist);
	}

	//
	public static void setRTQExcept(HttpSession session) {
		String[] para = { "RTQ", "exceptional" };
		List paralist = new ArrayList();
		paralist.add(0, "setServiceState");
		paralist.add(1, para);
		SessionManagement.setSessionAttribute(session, Constants.AdminOperate,
				paralist);
	}

	//
	public static void setRTQNormal(HttpSession session) {
		String[] para = { "RTQ", "normal" };
		List paralist = new ArrayList();
		paralist.add(0, "setServiceState");
		paralist.add(1, para);
		SessionManagement.setSessionAttribute(session, Constants.AdminOperate,
				paralist);
	}

}
