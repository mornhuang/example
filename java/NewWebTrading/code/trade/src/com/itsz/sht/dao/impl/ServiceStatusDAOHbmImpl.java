package com.itsz.sht.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.itsz.sht.dao.ServiceStatusDAO;
import com.itsz.sht.dao.hibernate.model.Status;
import com.itsz.sht.dto.ServiceStatus;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;
import com.taifook.mtss.web.common.util.hibernate.HibernateUtil;

/**
 * 
 * $Id: ServiceStatusDAOHbmImpl.java,v 1.2 2010/11/12 04:41:01 kyzou Exp $
 * @Project:portal
 * @File:ServiceStatusDAOHbmImpl.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class ServiceStatusDAOHbmImpl implements ServiceStatusDAO {

	public ServiceStatus getServiceStatusByServiceID(String serviceID) 
		throws DAOException, RecordNotFoundException {
		
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
				
			Session session = HibernateUtil.currentSession();
	
			ServiceStatus serviceStatus = null;
			
			Query query = session.createQuery("select status from Status as status where status.serviceID = :id");
			query.setString("id", serviceID);
			Iterator it = query.iterate();
			
			if (it.hasNext()) {
				Status status = (Status) it.next();
				
				serviceStatus = new ServiceStatus();
				serviceStatus.setServiceID(status.getServiceID());
				serviceStatus.setServiceName(status.getServiceName());
				serviceStatus.setStatus(status.getStatus());
				serviceStatus.setValidPeriod(status.getValidPeriod());
				serviceStatus.setTimezone(status.getTimezone());
				serviceStatus.setLastModifiedDate(status.getLastModifiedDate());
				serviceStatus.setUpdateToken(status.getUpdateToken());				
			}
	
			// Close session
			HibernateUtil.closeSession();
			
			return serviceStatus;
		}
		catch (ObjectNotFoundException onfe) {
			logger.info("Service status for service ID " + serviceID + " no found in the database!");
			throw new RecordNotFoundException("Service status for service ID " + serviceID + " no found in the database!", onfe);
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
	
	public ServiceStatus getServiceStatusByServiceName(String serviceName) 
		throws DAOException, RecordNotFoundException {
		
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
				
			Session session = HibernateUtil.currentSession();

			ServiceStatus serviceStatus = null;
			
			Query query = session.createQuery("select status from Status as status where status.serviceName = :name");
			query.setString("name", serviceName);
			Iterator it = query.iterate();
			
			if (it.hasNext()) {
				Status status = (Status) it.next();
				
				serviceStatus = new ServiceStatus();
				serviceStatus.setServiceID(status.getServiceID());
				serviceStatus.setServiceName(status.getServiceName());
				serviceStatus.setStatus(status.getStatus());
				serviceStatus.setValidPeriod(status.getValidPeriod());
				serviceStatus.setTimezone(status.getTimezone());
				serviceStatus.setLastModifiedDate(status.getLastModifiedDate());
				serviceStatus.setUpdateToken(status.getUpdateToken());
			}

			// Close session
			HibernateUtil.closeSession();
			
			return serviceStatus;
		}
		catch (ObjectNotFoundException onfe) {
			logger.info("Service status for service " + serviceName + " no found in the database!");
			throw new RecordNotFoundException("Service status for service " + serviceName + " no found in the database!", onfe);
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
	
	public ArrayList getAllServiceStatus() 
		throws DAOException, RecordNotFoundException {
		
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
				
			Session session = HibernateUtil.currentSession();
	
			
			Query query = session.createQuery("select status from Status as status");
			Iterator it = query.iterate();
			ArrayList serviceStatusList = new ArrayList();
			
			while (it.hasNext()) {
				Status status = (Status) it.next();
				
				ServiceStatus serviceStatus = new ServiceStatus();
				serviceStatus.setServiceID(status.getServiceID());
				serviceStatus.setServiceName(status.getServiceName());
				serviceStatus.setStatus(status.getStatus());
				serviceStatus.setValidPeriod(status.getValidPeriod());
				serviceStatus.setTimezone(status.getTimezone());
				serviceStatus.setLastModifiedDate(status.getLastModifiedDate());
				serviceStatus.setUpdateToken(status.getUpdateToken());
				
				serviceStatusList.add(serviceStatus);
			}
	
			// Close session
			HibernateUtil.closeSession();
			
			return serviceStatusList;
		}
		catch (ObjectNotFoundException onfe) {
			logger.info("No service status found in the database!");
			throw new RecordNotFoundException("No service status found in the database!", onfe);
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
	
	public void removeServiceStatusByServiceID(String serviceID) 
		throws DAOException, RecordNotFoundException {
		
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
			Session session = HibernateUtil.currentSession();
	
			Transaction tx = session.beginTransaction();
			// Delete the persistent object
			Status status = (Status) session.load(Status.class, serviceID);
			session.delete(status);
			session.flush();
			tx.commit();
			
			// Close session
			HibernateUtil.closeSession();
			
		}
		catch (ObjectNotFoundException onfe) {
			logger.info("Service status for service ID " + serviceID + " no found in the database!");
			throw new RecordNotFoundException("Service status for service ID " + serviceID + " no found in the database!", onfe);
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


	public void updateServiceStatus(String serviceID, ServiceStatus newServiceStatus) 
		throws DAOException, RecordNotFoundException {
		
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
			Session session = HibernateUtil.currentSession();
	
			Transaction tx = session.beginTransaction();
			
			// Update the persistent object
			Status status = (Status) session.load(Status.class, serviceID);
			status.setServiceName(newServiceStatus.getServiceName());
			status.setStatus(newServiceStatus.getStatus());
			status.setValidPeriod(newServiceStatus.getValidPeriod());
			status.setTimezone(newServiceStatus.getTimezone());
			status.setLastModifiedDate(new Date());
			
			session.update(status);
			session.flush();
			
			tx.commit();
			
			// Close session
			HibernateUtil.closeSession();
			
		}
		catch (ObjectNotFoundException onfe) {
			logger.info("Service status for service ID " + serviceID + " no found in the database!");
			throw new RecordNotFoundException("Service status for service ID " + serviceID + " no found in the database!", onfe);
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

	public void insertServiceStatus(ServiceStatus newServiceStatus) 
		throws DAOException {
	
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
			Session session = HibernateUtil.currentSession();
	
			Transaction tx = session.beginTransaction();
			
			// Add the persistent object
			Status status = new Status();
			status.setServiceName(newServiceStatus.getServiceName());
			status.setStatus(newServiceStatus.getStatus());
			status.setValidPeriod(newServiceStatus.getValidPeriod());
			status.setTimezone(newServiceStatus.getTimezone());
			status.setLastModifiedDate(new Date());
			
			session.save(status);
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
