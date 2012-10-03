package com.itsz.sht.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.service.MobileAgentService;

/**
 * $Id: MobileAgentMgrService.java,v 1.1 2010/11/09 03:57:15 kyzou Exp $
 * @Project:portal
 * @File:MobileAgentMgrService.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-27
 */
public class MobileAgentMgrService extends MobileAgentService {
	private static MobileAgentMgrService service;

	private MobileAgentMgrService() {

	}
	public List findByAllMobileAgent() {
		List result = new ArrayList();
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			result = session.createQuery(
					"from CsSetParameter where id.paramName='AgentBlackList' or id.paramName='AgentPcBlackList' or id.paramName='AgentWhiteList' order by id.paramName asc,updateTime desc")
					.list();
			session.flush();
			executeFlag=true;
		} catch (Exception e) {
			log.error(e);
			return null;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}
	
	public static MobileAgentMgrService getInstance() {
		if (service == null) {
			service = new MobileAgentMgrService();
		}
		return service;
	}
}
