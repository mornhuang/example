package com.itsz.sht.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.itsz.sht.dao.Dao;
public abstract class BaseDao<T> extends HibernateDaoSupport implements Dao<T> {

	private Class<T> m_class;

	protected static Log log = LogFactory.getLog(BaseDao.class);
	
	public BaseDao(Class<T> clazz) {
		this.m_class = clazz;
	}

	/**
	 * @see com.itsz.sht.dao#getObject(java.lang.Class, java.io.Serializable)
	 */
	@SuppressWarnings(value = { "unchecked" })
	public T get(Serializable id) throws DataAccessException {
		return (T) getHibernateTemplate().get(m_class, id);
	}

	/**
	 * @see com.itsz.sht.dao#getObjects(java.lang.Class)
	 */
	@SuppressWarnings(value = { "unchecked" })
	public List<T> list() throws DataAccessException {
		return (List<T>) getHibernateTemplate().loadAll(m_class);
	}

	@SuppressWarnings(value = { "unchecked" })
	public List<T> findByNamedQuery(String queryName)
			throws DataAccessException {
		return (List<T>) getHibernateTemplate().findByNamedQuery(queryName);
	}

	/**
	 * @see com.itsz.sht.dao#removeObject(java.lang.Class, java.io.Serializable)
	 */
	public void remove(Serializable id) throws DataAccessException {
		T obj = get(id);
		if (obj != null) {
			getHibernateTemplate().delete(obj);
			getHibernateTemplate().flush();
		}
	}

	/**
	 * @see com.itsz.sht.dao#saveObject(java.lang.Object)
	 */
	public void save(T o) throws DataAccessException {
		getHibernateTemplate().save(o);
		getHibernateTemplate().flush();
		
	}

	public void saveOrUpdateBatch(List<T> list) throws DataAccessException {
		Session session=null ;
		try {
			session = this.getHibernateTemplate().getSessionFactory().openSession();;
			Transaction tx = session.beginTransaction();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Object o = list.get(i);
					getHibernateTemplate().saveOrUpdate(o);
					if (i%50 == 0) {
						getHibernateTemplate().flush();
						getHibernateTemplate().clear();
					}
				}
			}
			tx.commit();
		} finally{
			session.close();   
		}
	}
	
	public void saveBatch(List<T> list) throws DataAccessException {
		Session session=null ;
		try {
			session = this.getHibernateTemplate().getSessionFactory().openSession();;
			Transaction tx = session.beginTransaction();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Object o = list.get(i);
					getHibernateTemplate().save(o);
					if (i%50 == 0) {
						getHibernateTemplate().flush();
						getHibernateTemplate().clear();
					}
				}
			}
			tx.commit();
		} finally{
			session.close();   
		}
	}
	
	public void updateBatch(List<T> list) throws DataAccessException {
		Session session=null ;
		try {
			session = this.getHibernateTemplate().getSessionFactory().openSession();;
			Transaction tx = session.beginTransaction();
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Object o = list.get(i);
					getHibernateTemplate().update(o);
					if (i%50 == 0) {
						getHibernateTemplate().flush();
						getHibernateTemplate().clear();
					}
				}
			}
			tx.commit();
		} finally {
			session.close();   
		}
	}
	/**
	 * @see com.itsz.sht.dao#updateObject(java.lang.Object)
	 */
	public void update(T o) throws DataAccessException {
		getHibernateTemplate().update(o);
		getHibernateTemplate().flush();
	}

	public String getSqlByName(String sqlMapName) {
		Session session=null ;
		String sql = "";
		try {
			session = this.getHibernateTemplate().getSessionFactory().openSession();
			sql = session.getNamedQuery(sqlMapName).getQueryString();
		}finally{
			session.close();
		}
		return sql;
	}
	
	@SuppressWarnings("unchecked")
	public List listBySql(String sql) throws DataAccessException {
		Session session=null ;
		List list = null;
		try {
			session = this.getHibernateTemplate().getSessionFactory().openSession();
			list = session.createSQLQuery(sql).list();
		}finally{
			session.close();
		}
		return list;
	}

	public void deleteByHql(String hql) throws DataAccessException{
		Session session=null ;
		try {
			session = this.getHibernateTemplate().getSessionFactory().openSession();
			session.createQuery(hql).executeUpdate();
		}finally{
			session.close();
		}
	}
	
	public void deleteBySql(String sql) throws DataAccessException{
		Session session=null ;
		try {
			session = this.getHibernateTemplate().getSessionFactory().openSession();
			session.createQuery(sql).executeUpdate();
		}finally{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List listByHql(String hql)throws DataAccessException{
		return getHibernateTemplate().find(hql);
	}
	public void persist(T o) throws DataAccessException {
		getHibernateTemplate().persist(o);
	}

}
