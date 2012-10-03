package com.redsaga.hibnatesample.step2.util;

import org.apache.log4j.Logger;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;

import com.redsaga.hibnatesample.step2.dao.RootDAO;
import com.redsaga.hibnatesample.step2.dao.UserDAO;
import com.redsaga.hibnatesample.step2.dao._RootDAO;

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
