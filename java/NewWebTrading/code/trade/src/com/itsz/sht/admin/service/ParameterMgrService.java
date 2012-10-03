package com.itsz.sht.admin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.model.CsParameter;
import com.taifook.adminportal.service.ParameterService;

public class ParameterMgrService extends ParameterService {

	private static ParameterMgrService service;

	private ParameterMgrService() {

	}

	/*
	 * �������еĲ�����Ϣ,����classid,key���а�asc���� ��������Ϊ:List
	 * List����Ϊ:com.taifook.adminportal.model.CsParameter����
	 */
	public List findByAllParameter() {
		List result = new ArrayList();
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			result = session.createQuery(
					"from CsParameter para order by para.classid,para.key")
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

	/*
	 * �������е�ClassId��Ϣ ��������Ϊ:List
	 * List����Ϊ:String����
	 */
	public List findClassIdByAll() {
		List result = new ArrayList();
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			result =session.createQuery(
					"select distinct para.classid from CsParameter para")
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

	/*
	 * ���classid���Ҳ�����Ϣ ��������Ϊ:List
	 * List����Ϊ:com.taifook.adminportal.model.CsParameter����
	 */
	public List findParameterByClassId(String classid) {
		List result = new ArrayList();
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Query query = session
					.createQuery("from CsParameter para where para.classid=?");
			query.setParameter(0, classid);
			result = query.list();
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

	/*
	 * ���key���Ҳ�����Ϣ ��������Ϊcom.taifook.adminportal.model.CsParameter����
	 * 
	 */
	public Object findParameterByKey(String key) {
		return super.findById(key);
	}

	/*
	 * ���key���Ҳ���ֵ��Ϣ ��������ΪString����
	 * 
	 */
	public String findParameterValueByKey(String key) {
		Object obj=findParameterByKey(key);
		if(obj==null){
			return null;
		}else{
			return ((CsParameter)obj).getValue();
		}
	}
	
	public void deleteParameterValueByKey(String key){
		CsParameter para=new CsParameter();
		para.setKey(key);
		this.delete(para);
	}

	// ������ʵ��
	public static ParameterMgrService getInstance() {
		if (service == null) {
			service = new ParameterMgrService();
		}
		return service;
	}
	
	public static void main(String[] args){
		ParameterMgrService service=ParameterMgrService.getInstance();
		System.out.println(service.findByAllParameter().size());
	}

}
