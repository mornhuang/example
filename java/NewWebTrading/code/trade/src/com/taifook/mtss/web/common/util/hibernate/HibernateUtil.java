package com.taifook.mtss.web.common.util.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory;

	static {
		try {
			// Create the SessionFactory
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (HibernateException ex) {
//			ex.printStackTrace();
			throw new RuntimeException("Configuration problem: "
					+ ex.getMessage(), ex);
		} catch (Exception e) {
//			e.printStackTrace();
			throw new RuntimeException("Configuration problem: "
					+ e.getMessage(), e);
		}
	}

	public static final ThreadLocal session = new ThreadLocal();

	public static Session currentSession() throws HibernateException {
		Session s = (Session) session.get();

		// Open a new Session, if this Thread has none yet
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}

	public static void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		session.set(null);

		if (s != null)
			s.close();
	}
}
