package com.redsaga.hibernatesample.step4.util;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;

import com.redsaga.hibernatesample.step4.Article;
import com.redsaga.hibernatesample.step4.dao.ArticleDAO;

import org.hibernate.*;
import org.hibernate.type.Type;

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

    public Boolean isTransient(Object o) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Boolean isUnsaved(Object entity) {
		return null;
	}

	public int[] findDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		return null;
	}

    public Object instantiate(String s, EntityMode entityMode, Serializable serializable) throws CallbackException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getEntityName(Object o) throws CallbackException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object getEntity(String s, Serializable serializable) throws CallbackException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void afterTransactionBegin(Transaction transaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void beforeTransactionCompletion(Transaction transaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void afterTransactionCompletion(Transaction transaction) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Object instantiate(Class clazz, Serializable id)
			throws CallbackException {
		return null;
	}

}
