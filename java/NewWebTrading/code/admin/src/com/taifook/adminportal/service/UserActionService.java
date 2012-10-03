package com.taifook.adminportal.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.HibernateBase;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;
import com.taifook.adminportal.dao.UserActionDAO;
import com.taifook.adminportal.model.CsParameter;
import com.taifook.adminportal.model.CsUseractionlog;

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
public class UserActionService extends HibernateBase implements UserActionDAO {

	public UserActionService() {
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
			object = session.get(CsUseractionlog.class, id);
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error("UserActionService-findById:findById is Exception");
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
			result = session.createQuery("from CsUseractionlog order by operationtime desc").list();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			result = null;
			log.error("UserActionService-findAll:findAll is Exception");
			log.error(e.getMessage());			
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}
	
	public List findAll(Object[] Paras){
		boolean executeFlag=false;
		List result;
		StringBuffer hql = new StringBuffer();
		StringBuffer filters=new StringBuffer("");		
		if(Paras!=null && Paras.length>0){
			filters.append("where 1=1 ");
			for(int index=0;index<Paras.length;index++){
				filters.append(" and "+Paras[index]);
			}
		}
		
		hql.append("from CsUseractionlog "+filters+" order by operationtime desc, userid asc");
		
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			result = session.createQuery(hql.toString()).list();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			result = null;
			log.error("UserActionService-findAll:findAll is Exception");
			log.error(e.getMessage());			
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}

	public boolean deleteByKey(String key) {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Query query=session.createQuery("delete from CsUseractionlog where seqno="+key);
			query.executeUpdate();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error("UserActionService-deleteByKey:deleteByKey is Exception");
			log.error(e.getMessage());			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return true;
	}
	
	public boolean deleteByKey(String[] keys) {
		boolean executeFlag=false;
		StringBuffer whereStr=new StringBuffer();
		for(int index=0;index<keys.length;index++){
			if(whereStr.toString().equals("")){
				whereStr.append("where ");
				whereStr.append("seqno= ");
				whereStr.append(keys[index]);
			}else{
				whereStr.append(" or ");
				whereStr.append("seqno= ");
				whereStr.append(keys[index]);
			}
		}
		Session session = null;
		Transaction transaction = null;
		try {
//			System.out.println(whereStr.toString());
			session = this.openSession();
			transaction = this.beginTransaction(session);			
			Query query=session.createQuery("delete from CsUseractionlog "+whereStr.toString());
			query.executeUpdate();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
//			e.printStackTrace();
			log.error("UserActionService-deleteByKey:deleteByKey is Exception");
			log.error(e.getMessage());			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return true;
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
			Query query=session.createQuery("delete from CsUseractionlog ");
			query.executeUpdate();
			session.flush();
			
/*			result = session.createQuery("from CsUseractionlog").list();
			Iterator it = result.iterator();
			while (it.hasNext()) {				
				CsUseractionlog useractionlog= (CsUseractionlog) it.next();
				session.delete(useractionlog);
				session.flush();
			}*/
			executeFlag=true;
		} catch (HibernateException e) {
			//result = null;
			log.error("UserActionService-deleteAll:deleteAll is Exception");
			log.error(e.getMessage());			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		//result = null;	
		return true;
	}

	public Page findByPage(Object[] Paras, int pageNumber, int pageSize) {
		boolean executeFlag=false;
		StringBuffer hql = new StringBuffer();
		StringBuffer filters=new StringBuffer("");		
		if(Paras!=null && Paras.length>0){
			filters.append("where 1=1 ");
			for(int index=0;index<Paras.length;index++){
				filters.append(" and "+Paras[index]);
			}
		}
		
		hql.append("from CsUseractionlog "+filters);	
		
		log.info("hql:" + hql);
		Session session = null;
		Transaction transaction = null;
		HibernatePage hibernatePage = null;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			int count = ((Integer) session.createQuery("select count(*)" + hql)
					.uniqueResult()).intValue();
			Query theQuery = session
					.createQuery(hql.toString()
							+ " order by operationtime desc,userid asc");
			hibernatePage = new HibernatePage(theQuery, pageNumber, pageSize,
					count);
			session.flush();
			executeFlag=true;			
		} catch (Exception e) {			
			log.error("UserActionService-findByPage:findByPage is Exception!");
			log.error(e);
			hibernatePage = null;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return hibernatePage;
	}

	//return obj[][]array, first column is actionid, second column is operattime
	public List statisticsByTime(String time) {
		boolean executeFlag=false;
		List result=new ArrayList();
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Query query = session
					.createQuery("select useractionlog.actionid, useractionlog.operationtime,count(*) "
							+ "  from CsUseractionlog  useractionlog "
							+ " where to_char(useractionlog.operationtime,'yyyy-mm-dd') like '" + time
							+ "%' group by useractionlog.actionid, useractionlog.operationtime order by useractionlog.actionid, useractionlog.operationtime");
			session.flush();
			Iterator it=query.iterate();
			while(it.hasNext()){
				result.add(it.next());
			}		
			//session.flush();			
			executeFlag=true;
		} catch (Exception e) {
			log.error("UserActionService-statisticsByTime:statisticsByTime is exception");
			log.error(e);			
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}

		return result;
	}
		

}
