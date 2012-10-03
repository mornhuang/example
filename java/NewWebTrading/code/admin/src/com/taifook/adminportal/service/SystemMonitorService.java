package com.taifook.adminportal.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.HibernateBase;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;
import com.taifook.adminportal.dao.SystemMonitorDAO;
import com.taifook.adminportal.model.CsBroadcast;
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
public class SystemMonitorService extends HibernateBase implements
		SystemMonitorDAO {

	public SystemMonitorService() {
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
			object = session.get(CsServicemonitor.class, id);
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
			result = session.createQuery("from CsServicemonitor").list();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			result = null;
			log.error("SystemMonitorService-findAll:createQuery is Exception");
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
			Query query=session.createQuery("delete from CsServicemonitor ");
			query.executeUpdate();
			session.flush();
			
/*			result = session.createQuery("from CsServicemonitor").list();
			Iterator it = result.iterator();
			while (it.hasNext()) {
				CsServicemonitor monitor = (CsServicemonitor) it.next();
				session.delete(monitor);
				session.flush();
			}*/
			executeFlag=true;
		} catch (HibernateException e) {
			//result = null;
			log.error("SystemMonitorService-deleteAll:deleteAll is Exception");
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
					.createQuery("delete from CsServicemonitor where seqno="
							+ key);
			query.executeUpdate();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log
					.error("SystemMonitorService-deleteByKey:deleteByKey is Exception");
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
			Query query = session.createQuery("delete from CsServicemonitor "
					+ whereStr.toString());
			query.executeUpdate();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
//			e.printStackTrace();
			log.error("SystemMonitorService-deleteByKey:deleteByKey is Exception");
			log.error(e.getMessage());			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return true;
	}

	public boolean save(CsServicemonitor monitor) {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			session.save(monitor);
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error("SystemMonitorService-saveUpdate:saveUpdate is Exception");
			log.error(e.getMessage());			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return true;
	}

	public boolean save(List monitors) {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Iterator it = monitors.iterator();
			while (it.hasNext()) {
				CsServicemonitor monitor = (CsServicemonitor) it.next();
				session.save(monitor);
				session.flush();
			}
			executeFlag=true;
		} catch (HibernateException e) {
			log.error("SystemMonitorService-saveUpdate:saveUpdate is Exception");
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

		hql.append("from CsServicemonitor " + filters);
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
					+ " order by accesstime desc,servicename");
			hibernatePage = new HibernatePage(theQuery, pageNumber, pageSize,
					count);
			session.flush();
			executeFlag=true;			
		} catch (Exception e) {			
			log.error("SystemMonitorService-findByPage:createQuery Exception!");
			log.error(e);
			hibernatePage = null;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return hibernatePage;
	}

	public List statisticsByTime(String time) {
		boolean executeFlag=false;
		List result = new ArrayList();
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Query query = session
					.createQuery("select monitor.servicename, monitor.status, monitor.accesstime, monitor.expendtime"
							+ "  from CsServicemonitor monitor "
							+ " where to_char(monitor.accesstime,'yyyy-mm-dd') like '"
							+ time
							+ "%' group by  monitor.servicename, monitor.status, monitor.accesstime,monitor.expendtime");
			session.flush();
			Iterator it = query.iterate();
			while (it.hasNext()) {
				result.add(it.next());
			}
			//session.flush();
			executeFlag=true;
//			System.out.println("query list size: " + result.size());
		} catch (Exception e) {
			log
					.error("UserActionService-statisticsByTime:statisticsByTime exception");
			log.error(e);			
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}

		return result;
	}

}
