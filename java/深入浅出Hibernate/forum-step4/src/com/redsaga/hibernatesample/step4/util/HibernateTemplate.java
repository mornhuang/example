package com.redsaga.hibernatesample.step4.util;

import org.apache.log4j.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.redsaga.hibernatesample.step4.dao.RootDAO;
import com.redsaga.hibernatesample.step4.dao.UserDAO;
import com.redsaga.hibernatesample.step4.dao._RootDAO;

public class HibernateTemplate {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(HibernateTemplate.class);

	public Object run(HibernateCallback callback) {
		Session session = null;
		Transaction tx = null;
		try {
			session = RootDAO.createSession();
			logger.debug("session="+session+",isopen="+session.isOpen());
			
			tx = session.beginTransaction();
			Object result = callback.execute();
			tx.commit();
			session.flush();
			return result;
		} catch (HibernateException e) {
			try {
				tx.rollback();
			} catch (Throwable t) {
			}
			throw new RuntimeException(e);
		}
		finally
		{
			try {
				RootDAO.getInstance().closeSession();
			} catch (HibernateException e1) {
				throw new RuntimeException(e1);
			}
		}
	}
}
