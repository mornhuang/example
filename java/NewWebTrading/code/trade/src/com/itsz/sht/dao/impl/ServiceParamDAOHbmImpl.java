package com.itsz.sht.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itsz.sht.dao.ServiceParamDAO;
import com.itsz.sht.dao.hibernate.model.Param;
import com.itsz.sht.dao.hibernate.model.ParamPK;
import com.itsz.sht.dto.ServiceParameter;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;
import com.taifook.mtss.web.common.util.hibernate.HibernateUtil;

/**
 * 
 * $Id: ServiceParamDAOHbmImpl.java,v 1.2 2010/11/12 04:41:01 kyzou Exp $
 * @Project:portal
 * @File:ServiceParamDAOHbmImpl.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class ServiceParamDAOHbmImpl implements ServiceParamDAO {

	
	public ArrayList getServiceParams(String serviceID) 
		throws DAOException, RecordNotFoundException {
		

		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
				
			Session session = HibernateUtil.currentSession();
			
			Query query = session.createQuery("select param from Param as param where param.id.serviceID = :id");
			query.setString("id", serviceID);
			Iterator it = query.iterate();
			
			ArrayList serviceParamList = new ArrayList();
			while (it.hasNext()) {
				ServiceParameter serviceParam = new ServiceParameter();
				Param param = (Param) it.next();
				
				serviceParam = new ServiceParameter();
				serviceParam.setParamName((param.getId()).getParamName());
				serviceParam.setParamValue(param.getParamValue());
				serviceParam.setServiceID((param.getId()).getServiceID());
				serviceParam.setDataType(param.getDataType());
				serviceParam.setLastModifiedDate(param.getLastModifiedDate());
				serviceParam.setUpdateToken(param.getUpdateToken());
				
				serviceParamList.add(serviceParam);
			}

			// Close session
			HibernateUtil.closeSession();
			
			if (serviceParamList != null && serviceParamList.size() > 0)
				return serviceParamList;
			else
				return null;
		}

		catch (ObjectNotFoundException onfe) {
			logger.info("Service param for service ID " + serviceID + " no found in the database!");
			throw new RecordNotFoundException("Service param for service ID " + serviceID + " no found in the database!", onfe);
		}
		catch (HibernateException he) {
			logger.error("HibernateException " + he.getMessage());
			throw new DAOException("HibernateException " + he.getMessage(), he);
		}
		catch (RuntimeException re) {
			logger.error("RuntimeException " + re.getMessage());
			throw new DAOException("RuntimeException " + re.getMessage(), re);
		}
		catch (Exception e) {
			logger.error("Exception " + e.getMessage());
			throw new DAOException("Exception " + e.getMessage(), e);			
		}
	}
	
	
	public void removeServiceParam(String paramName, String serviceID) 
		throws DAOException, RecordNotFoundException {
		
	
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
				
			Session session = HibernateUtil.currentSession();
			
			Transaction tx = session.beginTransaction();
			
			ParamPK paramPK = new ParamPK();
			paramPK.setParamName(paramName);
			paramPK.setServiceID(serviceID);
			Param param = (Param) session.load(Param.class, paramPK);
			session.delete(param);
			session.flush();
			
			tx.commit();
	
			// Close session
			HibernateUtil.closeSession();
			
		}

		catch (ObjectNotFoundException onfe) {
			logger.info("Service param for service ID " + serviceID + " no found in the database!");
			throw new RecordNotFoundException("Service param for service ID " + serviceID + " no found in the database!", onfe);
		}
		catch (HibernateException he) {
			logger.error("HibernateException " + he.getMessage());
			throw new DAOException("HibernateException " + he.getMessage(), he);
		}
		catch (RuntimeException re) {
			logger.error("RuntimeException " + re.getMessage());
			throw new DAOException("RuntimeException " + re.getMessage(), re);
		}
		catch (Exception e) {
			logger.error("Exception " + e.getMessage());
			throw new DAOException("Exception " + e.getMessage(), e);			
		}		
	}
	
	public ServiceParameter getServiceParam(String serviceID, String paramName) 
		throws DAOException, RecordNotFoundException {
	
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
				
			Session session = HibernateUtil.currentSession();
			
			ParamPK paramPK = new ParamPK();
			paramPK.setParamName(paramName);
			paramPK.setServiceID(serviceID);
			Param param = (Param) session.load(Param.class, paramPK);
			
			ServiceParameter serviceParam = new ServiceParameter();
			serviceParam.setParamName(param.getId().getParamName());
			serviceParam.setParamValue(param.getParamValue());
			serviceParam.setDataType(param.getDataType());
			serviceParam.setServiceID(param.getId().getServiceID());
			serviceParam.setUpdateToken(param.getUpdateToken());
			
			// Close session
			HibernateUtil.closeSession();
			
			return serviceParam;
			
		}
		catch (ObjectNotFoundException onfe) {
			logger.info("Service param " + paramName + " for service ID " + serviceID + " no found in the database!");
			throw new RecordNotFoundException("Service param " + paramName + " for service ID " + serviceID + " no found in the database!", onfe);
		}
		catch (HibernateException he) {
			logger.error("HibernateException " + he.getMessage());
			throw new DAOException("HibernateException " + he.getMessage(), he);
		}
		catch (RuntimeException re) {
			logger.error("RuntimeException " + re.getMessage());
			throw new DAOException("RuntimeException " + re.getMessage(), re);
		}
		catch (Exception e) {
			logger.error("Exception " + e.getMessage());
			throw new DAOException("Exception " + e.getMessage(), e);			
		}		
	}

	
	
	public void removeServiceParamsByServiceID(String serviceID) 
		throws DAOException, RecordNotFoundException {
		
	
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
				
			Session session = HibernateUtil.currentSession();
			
			Transaction tx = session.beginTransaction();
			
			Query query = session.createQuery("select param from Param as param where param.id.serviceID = :id");
			query.setString("id", serviceID);
			Iterator it = query.iterate();
			
			while (it.hasNext()) {
				Param param = (Param) it.next();
				session.delete(param);
			}
			session.flush();
	
			tx.commit();
			
			// Close session
			HibernateUtil.closeSession();
		}
		catch (ObjectNotFoundException onfe) {
			logger.info("Service param for service ID " + serviceID + " no found in the database!");
			throw new RecordNotFoundException("Service param for service ID " + serviceID + " no found in the database!", onfe);
		}
		catch (HibernateException he) {
			logger.error("HibernateException " + he.getMessage());
			throw new DAOException("HibernateException " + he.getMessage(), he);
		}
		catch (RuntimeException re) {
			logger.error("RuntimeException " + re.getMessage());
			throw new DAOException("RuntimeException " + re.getMessage(), re);
		}
		catch (Exception e) {
			logger.error("Exception " + e.getMessage());
			throw new DAOException("Exception " + e.getMessage(), e);			
		}		
	}

	public void updateServiceParam(String paramName, String serviceID, ServiceParameter newServiceParam) 
		throws DAOException, RecordNotFoundException {
		
	
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
			Session session = HibernateUtil.currentSession();
			
			Transaction tx = session.beginTransaction();
			
			ParamPK paramPK = new ParamPK();
			paramPK.setParamName(paramName);
			paramPK.setServiceID(serviceID);
			Param param = (Param) session.load(Param.class, paramPK);

			// Update the persistent class
			param.setParamValue(newServiceParam.getParamValue());
			param.setDataType(newServiceParam.getDataType());
			param.setLastModifiedDate(new Date());
			param.setUpdateSeqNum(1);
			param.setUpdateToken(newServiceParam.getUpdateToken());
						
			session.saveOrUpdate(param);
			session.flush();
	
			tx.commit();
			
			
			// Close session
			HibernateUtil.closeSession();
		}
		catch (ObjectNotFoundException onfe) {
			logger.info("Service param " + paramName + " for service ID " + serviceID + " no found in the database!");
			throw new RecordNotFoundException("Service param " + paramName + " for service ID " + serviceID + " no found in the database!", onfe);
		}
		catch (HibernateException he) {
			logger.error("HibernateException " + he.getMessage());
			throw new DAOException("HibernateException " + he.getMessage(), he);
		}
		catch (RuntimeException re) {
			logger.error("RuntimeException " + re.getMessage());
			throw new DAOException("RuntimeException " + re.getMessage(), re);
		}
		catch (Exception e) {
			logger.error("Exception " + e.getMessage());
			throw new DAOException("Exception " + e.getMessage(), e);			
		}		
	}

	
	public void insertServiceParam(ServiceParameter newServiceParam) 
		throws DAOException {
		
	
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
			Session session = HibernateUtil.currentSession();

			Transaction tx = session.beginTransaction();
			
			ParamPK paramPK = new ParamPK();
			paramPK.setParamName(newServiceParam.getParamName());
			paramPK.setServiceID(newServiceParam.getServiceID());
			
			Param param = new Param();
			param.setId(paramPK);
			param.setParamValue(newServiceParam.getParamValue());
			param.setDataType(newServiceParam.getDataType());
			param.setLastModifiedDate(new Date());
			param.setUpdateToken(newServiceParam.getUpdateToken());
						
			session.save(param);
			session.flush();
	
			tx.commit();
			
			// Close session
			HibernateUtil.closeSession();
		}
		catch (HibernateException he) {
			logger.error("HibernateException " + he.getMessage());
//			he.printStackTrace();
			throw new DAOException("HibernateException " + he.getMessage(), he);
		}
		catch (RuntimeException re) {
			logger.error("RuntimeException " + re.getMessage());
			throw new DAOException("RuntimeException " + re.getMessage(), re);
		}
		catch (Exception e) {
			logger.error("Exception " + e.getMessage());
			throw new DAOException("Exception " + e.getMessage(), e);			
		}		
	}
}
