package com.taifook.adminportal.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itsz.common.util.OracleHibernateUtil;

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
public abstract class HibernateBase {

	protected static Log log = LogFactory.getLog(HibernateBase.class);

	static {
		init();
	}

	private static void init() {

	}

	protected Session openSession() throws HibernateException {
		return OracleHibernateUtil.currentSession();
	}

	protected Transaction beginTransaction(Session session)
			throws HibernateException {
		return session.beginTransaction();
	}

	protected void endTransaction(Session session, Transaction transaction,
			boolean commit) throws HibernateException {
		try {
			if (transaction != null) {
				if (commit) {
					log.info("transaction will commit......");
					transaction.commit();					
				} else {
					log.info("transaction will rollback......");
					transaction.rollback();
				}
			}			

		} catch (Exception e) {
			log.error("endTransaction is Exception: " + e.getMessage());
		}finally{
			OracleHibernateUtil.closeSession();
			
			if (session != null && session.isOpen()) {
				session.close();
			}
			session=null;
		}
	}

	public void save(Object object) throws Exception{
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			session.save(object);
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);
			throw new Exception(e);		
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
	}

	public void update(Object object)  throws Exception{
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			session.update(object);
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);
			throw new Exception(e);
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
	}

	public void saveOrUpdate(Object object)  throws Exception{
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			session.saveOrUpdate(object);
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);
			throw new Exception(e);
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
	}

	public void delete(Object object)  throws Exception{
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			session.delete(object);
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);
			throw new Exception(e);
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
	}

	public static void closeConnection() {
		OracleHibernateUtil.closeConnection();

	}

}
