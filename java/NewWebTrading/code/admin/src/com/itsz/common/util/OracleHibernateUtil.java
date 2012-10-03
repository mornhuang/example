package com.itsz.common.util;

import java.net.URL;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Extended to support multiple datasource
 */
public class OracleHibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            URL url = OracleHibernateUtil.class.getClassLoader().getResource("oracle.cfg.xml");
//            System.out.println(url.getPath());
            // Create the SessionFactory
            sessionFactory = new Configuration().configure(url).buildSessionFactory();
        }
        catch (HibernateException ex) {
//            ex.printStackTrace();
            throw new RuntimeException("Configuration problem: " + ex.getMessage(), ex);
        }
        catch (Exception e) {
//            e.printStackTrace();
            throw new RuntimeException("Configuration problem: " + e.getMessage(), e);
        }
    }

    public static final ThreadLocal session = new ThreadLocal();

    public static Session currentSession() throws HibernateException {
        Session s = (Session) session.get();

        // Open a new Session, if this Thread has none yet
        if (s == null||!s.isOpen()) {
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
    
	public static void closeConnection() {
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
		}

	}
}

