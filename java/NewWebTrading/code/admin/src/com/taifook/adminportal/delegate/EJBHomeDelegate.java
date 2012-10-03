package com.taifook.adminportal.delegate;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import javax.ejb.EJBException;
import javax.ejb.EJBHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EJBHomeDelegate {

	private Map EJBHomes;

	private Context context=null;

	private static EJBHomeDelegate singleInstance;

	static Log log = LogFactory.getLog(EJBHomeDelegate.class);

	/**
	 * @param args
	 */
	private EJBHomeDelegate() {
/*		try {
			init();
		} catch (Exception e) {
			log.info((new SimpleDateFormat("yyyy-MM-dd")).format(new Date())
					+ "create EJBHomeDelegate fail...... " + e.getMessage());
		}*/
	}

	// initial
	private void init() throws Exception {
		EJBHomes = Collections.synchronizedMap(new HashMap());
		try {
			log.info((new SimpleDateFormat("yyyy-MM-dd")).format(new Date())
					+ "start reading jndi properties......");
			InputStream is = this.getClass().getResourceAsStream(
					"/jndi.properties");
			if (is != null) {
				Hashtable map = new Hashtable();
				Properties jndiParams = new Properties();
				jndiParams.load(is);
				map.put(Context.INITIAL_CONTEXT_FACTORY, jndiParams
						.getProperty(Context.INITIAL_CONTEXT_FACTORY));
				map.put(Context.PROVIDER_URL, jndiParams
						.getProperty(Context.PROVIDER_URL));
				log.info("INITIAL_CONTEXT_FACTORY:"
						+ map.get(Context.INITIAL_CONTEXT_FACTORY));
				log.info("PROVIDER_URL:" + map.get(Context.PROVIDER_URL));
				this.context = new InitialContext(map);
				log
						.info((new SimpleDateFormat("yyyy-MM-dd"))
								.format(new Date())
								+ "read jndi properties and create Context finished......");
			} else {
				this.context = null;
			}
		} catch (IOException ioe) {
			log.error((new SimpleDateFormat("yyyy-MM-dd")).format(new Date())
					+ "read jndi properties IOException");
			this.context = null;
			throw ioe;
		} catch (NamingException ne) {
			log.error((new SimpleDateFormat("yyyy-MM-dd")).format(new Date())
					+ "initial context throw NamingException");
			this.context = null;
			throw ne;
		}
	}

	public Context getContext() {
		return this.context;
	}

	// lookup ejbhome from the jndi
	public EJBHome lookUp(String ejbJNDIName, Class homeCls)
			throws NamingException, EJBException, Exception {
		Log log = LogFactory.getLog(this.getClass());
		EJBHome ejbHome = null;// (EJBHome) this.EJBHomes.get(homeCls);
		if (ejbHome == null) {
			try {
				try {
					ejbHome = (EJBHome) PortableRemoteObject.narrow(
							this.context.lookup(ejbJNDIName), homeCls);
				} catch (Exception e) {
					this.init();
					ejbHome = (EJBHome) PortableRemoteObject.narrow(
							this.context.lookup(ejbJNDIName), homeCls);
				}
				// this.EJBHomes.put(homeCls, ejbHome);
			} catch (NamingException e) {
				log
						.error((new SimpleDateFormat("yyyy-MM-dd"))
								.format(new Date())
								+ "look up ejb home NamingException: "
								+ e.getMessage());
				throw e;
			} catch (EJBException e) {
				log.error((new SimpleDateFormat("yyyy-MM-dd"))
						.format(new Date())
						+ "look up ejb home EJBException: " + e.getMessage());
				throw e;
			} catch (Exception e) {
				log.error((new SimpleDateFormat("yyyy-MM-dd"))
						.format(new Date())
						+ "look up ejb home Exception: " + e.getMessage());
				throw e;
			}
		}
		return ejbHome;
	}

	public static EJBHomeDelegate getInstance() {
		if (singleInstance == null) {
			singleInstance = new EJBHomeDelegate();
		}
		return singleInstance;
	}

}
