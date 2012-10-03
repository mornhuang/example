/*
 * @(#) Dao.java        1.00         2006-11-6
 * 
 * Copyright (c) 2006 JadeFortune Corporation. All Rights Reserved.
 *
 *
 */
package com.itsz.sht.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.dao.DataAccessException;

/**
 *@description  Data Access Object (Dao) interface. 
 *              This is an interface used to tag our Dao classes 
 *              and to provide common methods to all Daos.
 *
 *
 */
public interface Dao<T>
{

	/**
	 * Generic method used to get all objects of a particular type. This
	 * is the same as lookup up all rows in a table.
	 * 
	 * @param clazz the type of objects (a.k.a. while table) to get data from
	 * @return List of populated objects
	 * 
	 * method will always return a list instead of null
	 */
	public List<T> list() throws DataAccessException;

	public List<T> findByNamedQuery(String queryName) throws DataAccessException;

	/**
	 * Generic method to get an object based on class and identifier. An 
	 * ObjectRetrievalFailureException Runtime Exception is thrown if 
	 * nothing is found.
	 * 
	 * @param clazz model class to lookup
	 * @param id the identifier (primary key) of the class
	 * @return a populated object
	 * @see org.springframework.orm.ObjectRetrievalFailureException
	 */
	public T get(Serializable id) throws DataAccessException;

	/**
	 * Generic method to save an object 
	 * @param o the object to save
	 */
	public void save(T o) throws DataAccessException;

	/**
	 * Generic method to update an object 
	 * @param o the object to save
	 */
	public void update(T o) throws DataAccessException;

	/**
	 * Generic method to delete an object based on class and id
	 * @param clazz model class to lookup
	 * @param id the identifier (primary key) of the class
	 */
	public void remove(Serializable id) throws DataAccessException;
	
	public String getSqlByName(String sqlMapName) throws DataAccessException;

	@SuppressWarnings("unchecked")
	public List listBySql(String sql) throws DataAccessException;
	
	public void deleteByHql(String hql) throws DataAccessException;
	
	public void deleteBySql(String sql) throws DataAccessException;
	@SuppressWarnings("unchecked")
	public List listByHql(String sql) throws DataAccessException;
	
	public void saveOrUpdateBatch(List<T> list) throws DataAccessException;
	
	public void persist(T o) throws DataAccessException;
}
