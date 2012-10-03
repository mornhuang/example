package com.taifook.adminportal.service;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.HibernateBase;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;
import com.taifook.adminportal.dao.ParameterDAO;
import com.taifook.adminportal.model.CsParameter;
import com.taifook.adminportal.model.CsServicemonitor;

/**
 * <p> * Title: admin_portal *
 * </p>
 * <p> * Description: *
 * </p>
 * <p> * Copyright: Copyright (c) 2006 *
 * </p>
 * <p> * Company: TaiFook itsz *
 * </p>
 * 
 * @author hsli
 * @version 1.0
 */
public class ParameterService extends HibernateBase implements ParameterDAO {
	public ParameterService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List findAll() {
		List result;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);			
			result = session.createQuery("from CsParameter").list();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			result = null;
			log.error(e);			
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}

	public boolean deleteAll() {
		//List result = null;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Query query=session.createQuery("delete from CsParameter ");
			query.executeUpdate();
			session.flush();
			
/*			result = session.createQuery("from CsParameter").list();
			Iterator it = result.iterator();
			while (it.hasNext()) {				
				CsParameter parameter = (CsParameter) it.next();
				session.delete(parameter);
				session.flush();
			}*/
			executeFlag=true;			
		} catch (HibernateException e) {
			//result = null;
			log.error("ParameterService-deleteAll:deleteAll is Exception");
			log.error(e.getMessage());			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		//result = null;	
		return true;
	}

	public Object findById(Serializable id) {
		Object object = null;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			object = session.get(CsParameter.class, id);
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);			
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return object;
	}

	/*
	 * public List loadChannels(String channels_status_pre){ List result;
	 * this.beginTransaction(); try { result=session.createQuery("from
	 * CsParameter parameter where parameter.key like
	 * '"+channels_status_pre+"%'").list(); } catch (HibernateException e) {
	 * log.equals(e); this.endTransaction(false); result=null; }
	 * this.endTransaction(true); return result; }
	 */

	public List loadChannels(String channelId) {
		List result;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			result = session.createQuery(
					"from CsParameter parameter where parameter.classid='"
							+ channelId + "'").list();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.equals(e);
			e.printStackTrace();			
			result = null;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}

	public Page findByPage(Object[] Paras, int pageNumber, int pageSize) {
		boolean executeFlag=false;
		StringBuffer hql = new StringBuffer();
		StringBuffer filters = new StringBuffer("");
		if (Paras!=null && Paras.length > 0) {
			filters.append("where 1=1 ");
			for (int index = 0; index < Paras.length; index++) {
				filters.append(" and " + Paras[index]);
			}
		}
//		System.out.println(filters);
		hql.append(" from CsParameter " + filters);
		Session session = null;
		Transaction transaction = null;
		HibernatePage hibernatePage = null;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			int count = ((Integer) (session.createQuery("select count(*) "
					+ hql.toString())).uniqueResult()).intValue();
			Query theQuery = session.createQuery(hql.toString()
					+ " order by key asc, updateTime desc");			
			hibernatePage = new HibernatePage(theQuery, pageNumber, pageSize,
					count);
			session.flush();
			executeFlag=true;			
		} catch (Exception e) {			
			log.error("ParameterService-findByPage:findByPage is Exception!");
			log.error(e);
			hibernatePage = null;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return hibernatePage;

	}

}
