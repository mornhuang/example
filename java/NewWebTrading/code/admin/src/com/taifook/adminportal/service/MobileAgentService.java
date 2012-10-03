package com.taifook.adminportal.service;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.taifook.adminportal.common.HibernateBase;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;
import com.taifook.adminportal.dao.MobileAgentDAO;
import com.taifook.adminportal.model.CsSetParameter;

/**
 * $Id: MobileAgentService.java,v 1.2 2010/11/09 04:31:52 kyzou Exp $
 * @Project:admin-portal
 * @File:MobileAgentService.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-26
 */
public class MobileAgentService extends HibernateBase implements MobileAgentDAO {
	
	public MobileAgentService(){
		super();
	}

	public Object findById(Serializable id) throws Exception{
		Object object = null;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			object = session.get(CsSetParameter.class, id);
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);
			throw new Exception(e);
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return object;
	}

	public Page findByPage(Object[] Paras, int pageNumber, int pageSize) throws Exception {
		boolean executeFlag=false;
		StringBuffer hql = new StringBuffer();
		StringBuffer filters = new StringBuffer("");
		if (Paras!=null && Paras.length > 0) {
			filters.append("where 1=1 ");
			for (int index = 0; index < Paras.length; index++) {
				filters.append(" and " + Paras[index]);
			}
		}
		hql.append(" from CsSetParameter " + filters);
		Session session = null;
		Transaction transaction = null;
		HibernatePage hibernatePage = null;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			int count = ((Integer) (session.createQuery("select count(*) "
					+ hql.toString())).uniqueResult()).intValue();
			Query theQuery = session.createQuery(hql.toString()
					+ " order by id.paramName asc, updateTime desc");			
			hibernatePage = new HibernatePage(theQuery, pageNumber, pageSize,count);
			session.flush();
			executeFlag=true;			
		} catch (Exception e) {			
			log.error("MobileAgentService-findByPage:findByPage is Exception!");
			log.error(e);
			hibernatePage = null;
			throw new Exception(e);
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return hibernatePage;
	}
	
	public List findAll() throws Exception {
		List result;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);			
			result = session.createQuery("from CsSetParameter").list();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			result = null;
			log.error(e);
			throw new Exception(e);
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}

	public boolean deleteAll() {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Query query=session.createQuery("delete from CsSetParameter ");
			query.executeUpdate();
			session.flush();
			executeFlag=true;			
		} catch (HibernateException e) {
			log.error("MobileAgentService-deleteAll:deleteAll is Exception");
			log.error(e.getMessage());			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return true;
	}

//	public void update(CsSetParameter obj,CsSetParameterKey id) {
//		StringBuffer hsql = new StringBuffer();
//		hsql.append("update CsSetParameter set id.paramValue='").append(obj.getId().getParamValue()).append("'");
//		hsql.append(",description='").append(obj.getDescription()).append("'");
//		hsql.append(",updateTime=sysdate ");
//		hsql.append(" where id.paramName='").append(id.getParamName()).append("'");
//		hsql.append(" and id.paramValue='").append(id.getParamValue()).append("'");
//		Session session = null;
//		Transaction transaction = null;
//		boolean executeFlag=false;
//		try {
//			session = this.openSession();
//			transaction = this.beginTransaction(session);			
//			Query query=session.createQuery(hsql.toString());
//			query.executeUpdate();
//			session.flush();
//			executeFlag=true;
//		} catch (HibernateException e) {
//			log.error("MobileAgentService-save is Exception");
//			log.error(e.getMessage());			
//		}finally{
//			this.endTransaction(session, transaction, executeFlag);
//		}
//	}
}
