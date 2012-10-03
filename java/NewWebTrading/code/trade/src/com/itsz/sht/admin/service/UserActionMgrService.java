package com.itsz.sht.admin.service;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.model.CsUseractionlog;
import com.taifook.adminportal.service.UserActionService;

public class UserActionMgrService extends UserActionService {
	private static UserActionMgrService service=new UserActionMgrService();

	private UserActionMgrService() {

	}
	
	//�����û���Ϊlog��¼
	public void saveUserAction(CsUseractionlog actionlog) {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);				
			session.save(actionlog);
			session.flush();
			executeFlag=true;			
		} catch (HibernateException e) {
			log.error("UserActionService-findById:find Exception");
			log.error(e);			
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
	}

	// ��¼�û���¼ActionLog
	public void saveLoginAction(String userId, String Ip, Date accsessTime) {
		CsUseractionlog actionlog = new CsUseractionlog();
		actionlog.setUserid(userId);
		actionlog.setIp(Ip);
		actionlog.setActionid(Constants.LOGIN_ACTION);
		actionlog.setOperationtime(accsessTime);
		this.saveUserAction(actionlog);
	}

	public static UserActionMgrService getInstance() {
		if(service==null){
			service=new UserActionMgrService();
		}
		return service;
	}
	
	public static void main(String[] args){
		UserActionMgrService service=UserActionMgrService.getInstance();
		String[] actions=new String[]{Constants.LOGIN_ACTION,Constants.LOGOUT_ACTION,Constants.INPUT_ORDER_ACTION};
		for(int i=0;i<10;i++){			
			CsUseractionlog actionlog=new CsUseractionlog();
			actionlog.setUserid("admin");
			actionlog.setIp("127.0.0.1");
			actionlog.setActionid(Constants.INPUT_ORDER_ACTION);
			actionlog.setOperationtime(new Date());
			service.saveUserAction(actionlog);
		}
	}

}
