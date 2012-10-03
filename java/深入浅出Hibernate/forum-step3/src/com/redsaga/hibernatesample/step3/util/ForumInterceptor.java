package com.redsaga.hibernatesample.step3.util;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import com.redsaga.hibernatesample.step3.Article;
import com.redsaga.hibernatesample.step3.dao.ArticleDAO;

import net.sf.hibernate.CallbackException;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Interceptor;
import net.sf.hibernate.Session;
import net.sf.hibernate.type.Type;

public class ForumInterceptor implements Interceptor, Serializable {

	private static final Logger logger = Logger
			.getLogger(ForumInterceptor.class);
	private Session session;


	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
		logger.info("session set "+session);
	}
	public boolean onLoad(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {
		return false;
	}

	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) throws CallbackException {
		return false;
	}

	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {
		logger.info("onSave "+entity);
		if (entity instanceof Article)
		{
			((Article)entity).setLastUpdateTime(new Date());
			((Article)entity).calculateTreeIndex();
		}
		return false;
	}

	public void onDelete(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {

	}

	public void preFlush(Iterator entities) throws CallbackException {
	}

	public void postFlush(Iterator entities) throws CallbackException {
	}

	public Boolean isUnsaved(Object entity) {
		return null;
	}

	public int[] findDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		return null;
	}

	public Object instantiate(Class clazz, Serializable id)
			throws CallbackException {
		return null;
	}

}
