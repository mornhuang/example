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
import com.taifook.adminportal.dao.BroadcastDAO;
import com.taifook.adminportal.model.CsBroadcast;
import com.taifook.adminportal.model.CsOnlineuser;

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
public class BroadcastService extends HibernateBase implements BroadcastDAO {

	public BroadcastService() {
		super();
	}

	public Object findById(Serializable id) {
		Object object = null;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			object = session.get(CsBroadcast.class, id);
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);			
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return object;
	}

	public List findAll() {
		List result;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			result = session.createQuery("from CsBroadcast").list();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			result = null;
			log.error("BroadcastService-findById:findById is Exception");
			log.error(e.getMessage());			
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}

	public boolean deleteAll() {
		// TODO Auto-generated method stub
		//List result = null;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Query query=session.createQuery("delete from CsBroadcast ");
			query.executeUpdate();
			session.flush();
			
/*			result = session.createQuery("from CsBroadcast").list();
			Iterator it = result.iterator();
			while (it.hasNext()) {
				CsBroadcast broadcast = (CsBroadcast) it.next();
				session.delete(broadcast);
				session.flush();
			}*/
			executeFlag=true;
		} catch (HibernateException e) {
			//result = null;
			log.error("BroadcastService-deleteAll:deleteAll is Exception");
			log.error(e.getMessage());			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		//result = null;
		return true;
	}

	public boolean deleteByKey(String key) {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Query query = session
					.createQuery("delete from CsBroadcast where seqno=" + key);
			query.executeUpdate();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error("BroadcastService-deleteByKey:deleteByKey is Exception");
			log.error(e.getMessage());			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return true;
	}

	public boolean deleteByKey(String[] keys) {
		boolean executeFlag=false;
		StringBuffer whereStr = new StringBuffer();
		for (int index = 0; index < keys.length; index++) {
			if (whereStr.toString().equals("")) {
				whereStr.append("where ");
				whereStr.append("seqno= ");
				whereStr.append(keys[index]);
			} else {
				whereStr.append(" or ");
				whereStr.append("seqno= ");
				whereStr.append(keys[index]);
			}
		}
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
//			System.out.println(whereStr.toString());
			Query query = session.createQuery("delete from CsBroadcast "
					+ whereStr.toString());
			query.executeUpdate();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
//			e.printStackTrace();
			log.error("BroadcastService-deleteByKey:deleteByKey is Exception");
			log.error(e.getMessage());			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return true;
	}

	public Page findByPage(Object[] Paras, int pageNumber, int pageSize) {
		boolean executeFlag=false;
		
		StringBuffer hql = new StringBuffer();
		StringBuffer filters = new StringBuffer("");
		if (Paras != null && Paras.length > 0) {
			filters.append("where 1=1 ");
			for (int index = 0; index < Paras.length; index++) {
				filters.append(" and " + Paras[index]);
			}
		}
		hql.append("from CsBroadcast " + filters);
		log.info("hql:" + hql);
		Session session = null;
		Transaction transaction = null;
		HibernatePage hibernatePage = null;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			int count = ((Integer) session.createQuery("select count(*)" + hql)
					.uniqueResult()).intValue();
			Query theQuery = session.createQuery(hql.toString()
					+ " order by lastupdatetime desc,seqno desc");
			hibernatePage = new HibernatePage(theQuery, pageNumber, pageSize,
					count);
			session.flush();
			executeFlag=true;
			log.info("hql:" + hql + "count:" + count);
		} catch (Exception e) {			
			log.error("BroadcastService-findByPage:createQuery Exception!");
			log.error(e);
			hibernatePage = null;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return hibernatePage;
	}

}
