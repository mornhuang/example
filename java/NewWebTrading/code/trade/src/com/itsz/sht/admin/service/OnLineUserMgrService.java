package com.itsz.sht.admin.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.model.CsOnlineuser;
import com.taifook.adminportal.model.CsOnlineuserKey;
import com.taifook.adminportal.service.OnLineUserService;

public class OnLineUserMgrService extends OnLineUserService {

	private static OnLineUserMgrService service=new OnLineUserMgrService();

	private OnLineUserMgrService() {

	}

	// ���浱ǰ�����û�
	public void saveOnlineuser(CsOnlineuser user) {
		super.saveOrUpdate(user);
	}

	public void saveOnlineuser(String userId, String channelId,
			String sessionId, Date loginTime) {
		CsOnlineuser user = new CsOnlineuser();
		CsOnlineuserKey key = new CsOnlineuserKey();
		key.setChannelcode(channelId);
		key.setUserid(userId);
		user.setId(key);
		user.setLogintime(loginTime);
		user.setSessionid(sessionId);
		this.saveOnlineuser(user);
	}

	public void saveOnlineuser(String userId, String channelType,
			String channelId, String sessionId, Date loginTime) {
		this.saveOnlineuser(userId, channelType + Constants.DELIMITER
				+ channelId, sessionId, loginTime);
	}

	// ��sessionIdɾ���Ӧ�������û�
	public boolean deleteBySessionId(String sessionId) {
		List result = null;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			result = session.createQuery("from CsOnlineuser where sessionid='"
					+ sessionId + "'").list();
			session.flush();
			Iterator it=result.iterator();
			while(it.hasNext()){
				Object obj=it.next();
				if(obj!=null){
					session.delete(obj);
					session.flush();
				}
			}
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);			
			result = null;
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}		
		return true;
	}

	// ��userIdɾ���Ӧ�������û�
	public boolean deleteByUserId(String userId) {
		List result = null;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			result = session.createQuery("from CsOnlineuser where userid='"
					+ userId + "'").list();
			session.flush();
			Iterator it=result.iterator();
			while(it.hasNext()){
				Object obj=it.next();
				if(obj!=null){
					session.delete(obj);
					session.flush();
				}
			}
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);			
			result = null;
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}		
		return true;
	}

	/*
	 * ������ʵ��
	 */
	public static OnLineUserMgrService getInstance() {
		if(service==null){
			service=new OnLineUserMgrService();
		}
		return service;
	}

	public static void main(String[] args) {
		OnLineUserMgrService service = OnLineUserMgrService.getInstance();

		for (int i = 0; i < 10; i++) {
			service.saveOnlineuser("admin"+i, "WEB", "WEB001",
					"ABCDREFDFDFDFERE000112002REFDFD"+i, (new Date()));
			try {
				Thread.sleep(i * 100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
