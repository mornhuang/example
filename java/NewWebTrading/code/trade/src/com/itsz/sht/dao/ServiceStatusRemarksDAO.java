package com.itsz.sht.dao;

import com.itsz.sht.dto.ServiceStatusRemarks;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;

/**
 * 
 * $Id: ServiceStatusRemarksDAO.java,v 1.2 2010/11/12 04:41:07 kyzou Exp $
 * @Project:portal
 * @File:ServiceStatusRemarksDAO.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public interface ServiceStatusRemarksDAO {

	public ServiceStatusRemarks getServiceStatusRemarks(String serviceID, String status, String lang) throws DAOException, RecordNotFoundException;
	
	public void insertServiceStatusRemarks(ServiceStatusRemarks newServiceStatusRemarks) throws DAOException;
	
	public void updateServiceStatusRemarks(String serviceID, String status, String lang, ServiceStatusRemarks newServiceStatusRemarks) throws DAOException, RecordNotFoundException;
	
}
