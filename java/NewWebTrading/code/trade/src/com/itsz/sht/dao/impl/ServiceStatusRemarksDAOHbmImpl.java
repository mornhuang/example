package com.itsz.sht.dao.impl;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itsz.sht.dao.ServiceStatusRemarksDAO;
import com.itsz.sht.dao.hibernate.model.StatusRemarks;
import com.itsz.sht.dao.hibernate.model.StatusRemarksPK;
import com.itsz.sht.dto.ServiceStatusRemarks;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;
import com.taifook.mtss.web.common.log.WebLogger;
import com.taifook.mtss.web.common.log.WebLoggerFactory;
import com.taifook.mtss.web.common.util.hibernate.HibernateUtil;

/**
 * 
 * $Id: ServiceStatusRemarksDAOHbmImpl.java,v 1.2 2010/11/12 04:41:01 kyzou Exp $
 * @Project:portal
 * @File:ServiceStatusRemarksDAOHbmImpl.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public class ServiceStatusRemarksDAOHbmImpl implements ServiceStatusRemarksDAO {

	public ServiceStatusRemarks getServiceStatusRemarks(String serviceID, String status, String lang) 
		throws DAOException, RecordNotFoundException {

		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
				
			Session session = HibernateUtil.currentSession();
			
			StatusRemarksPK statusRemarksPK = new StatusRemarksPK();
			statusRemarksPK.setServiceID(serviceID);
			statusRemarksPK.setStatus(status);
			statusRemarksPK.setLang(lang);
			
			StatusRemarks statusRemarks = (StatusRemarks) session.load(StatusRemarks.class, statusRemarksPK);
			
			ServiceStatusRemarks serviceStatusRemarks = new ServiceStatusRemarks();
			serviceStatusRemarks.setServiceID(statusRemarks.getId().getServiceID());
			serviceStatusRemarks.setStatus(statusRemarks.getId().getStatus());
			serviceStatusRemarks.setLang(statusRemarks.getId().getLang());
			serviceStatusRemarks.setRemarks(statusRemarks.getRemarks());
			serviceStatusRemarks.setLastModifiedDate(statusRemarks.getLastModifiedDate());
			serviceStatusRemarks.setUpdateSeqNum(statusRemarks.getUpdateSeqNum());
			serviceStatusRemarks.setUpdateToken(statusRemarks.getUpdateToken());

			
			// Close session
			HibernateUtil.closeSession();
			
			return serviceStatusRemarks;
			
		}
		catch (ObjectNotFoundException onfe) {
			logger.info("Service remarks for the status " + status + " and service ID " + serviceID + " and lang " + lang + " not found in the database!");
			throw new RecordNotFoundException("Service remarks for the status " + status + " and service ID " + serviceID + " and lang " + lang + " not found in the database!", onfe);
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
	
	public void updateServiceStatusRemarks(String serviceID, String status, String lang, ServiceStatusRemarks newServiceStatusRemarks) 
		throws DAOException, RecordNotFoundException {
		
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
System.out.println("lang: " + lang + ", remarks: " + newServiceStatusRemarks.getRemarks());

			Session session = HibernateUtil.currentSession();
	
			Transaction tx = session.beginTransaction();
			
			// Update the persistent object
			StatusRemarksPK statusRemarksPK = new StatusRemarksPK();
			statusRemarksPK.setServiceID(serviceID);
			statusRemarksPK.setStatus(status);
			statusRemarksPK.setLang(lang);
			
			StatusRemarks statusRemarks = (StatusRemarks) session.load(StatusRemarks.class, statusRemarksPK);
			statusRemarks.setRemarks(newServiceStatusRemarks.getRemarks());
			statusRemarks.setLastModifiedDate(new Date());
					
			session.update(statusRemarks);
			session.flush();
			
			tx.commit();
			
			// Close session
			HibernateUtil.closeSession();
			
		}
		catch (RuntimeException re) {
			logger.error("RuntimeException " + re.getMessage());
//			re.printStackTrace();
			throw new DAOException("RuntimeException " + re.getMessage(), re);
		}
		catch (Exception e) {
//            e.printStackTrace();
			logger.error("Exception " + e.getMessage());
			throw new DAOException("Exception " + e.getMessage(), e);			
		}
	}

	public void insertServiceStatusRemarks(ServiceStatusRemarks newServiceStatusRemarks) 
		throws DAOException {
		
		WebLogger logger = WebLoggerFactory.instance().getLogger(this);
		
		try {
			Session session = HibernateUtil.currentSession();
	
			Transaction tx = session.beginTransaction();
			
			// Update the persistent object
			StatusRemarksPK statusRemarksPK = new StatusRemarksPK();
			statusRemarksPK.setServiceID(newServiceStatusRemarks.getServiceID());
			statusRemarksPK.setStatus(newServiceStatusRemarks.getStatus());
			statusRemarksPK.setLang(newServiceStatusRemarks.getLang());
			
			StatusRemarks statusRemarks = new StatusRemarks();
			statusRemarks.setId(statusRemarksPK);
			statusRemarks.setRemarks(newServiceStatusRemarks.getRemarks());
			statusRemarks.setLastModifiedDate(new Date());
			
			session.save(statusRemarks);
			session.flush();
			
			tx.commit();
			
			// Close session
			HibernateUtil.closeSession();
			
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
	
}
