package com.itsz.sht.dao;

import java.util.ArrayList;

import com.itsz.sht.dto.ServiceStatus;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;

/**
 * 
 * $Id: ServiceStatusDAO.java,v 1.2 2010/11/12 04:41:07 kyzou Exp $
 * @Project:portal
 * @File:ServiceStatusDAO.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public interface ServiceStatusDAO {

	public ArrayList getAllServiceStatus() throws DAOException, RecordNotFoundException;
	
	public ServiceStatus getServiceStatusByServiceID(String serviceID) throws DAOException, RecordNotFoundException;

	public ServiceStatus getServiceStatusByServiceName(String serviceName) throws DAOException, RecordNotFoundException;

	public void removeServiceStatusByServiceID(String serviceID) throws DAOException, RecordNotFoundException;
	
	public void updateServiceStatus(String serviceID, ServiceStatus newServiceStatus) throws DAOException, RecordNotFoundException;

	public void insertServiceStatus(ServiceStatus newServiceStatus) throws DAOException;

}
