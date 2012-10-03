package com.itsz.sht.dao;

import java.util.ArrayList;

import com.itsz.sht.dto.ServiceParameter;
import com.taifook.mtss.web.common.exception.dao.DAOException;
import com.taifook.mtss.web.common.exception.dao.RecordNotFoundException;

/**
 * 
 * $Id: ServiceParamDAO.java,v 1.2 2010/11/12 04:41:07 kyzou Exp $
 * @Project:portal
 * @File:ServiceParamDAO.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-7-2
 */
public interface ServiceParamDAO {

	public ArrayList getServiceParams(String serviceID) throws DAOException, RecordNotFoundException;
	
	public ServiceParameter getServiceParam(String serviceID, String paramName) throws DAOException, RecordNotFoundException;
	
	public void removeServiceParam(String paramName, String serviceID) throws DAOException, RecordNotFoundException;
	
	public void removeServiceParamsByServiceID(String serviceID) throws DAOException, RecordNotFoundException;

	public void updateServiceParam(String paramName, String serviceID, ServiceParameter newServiceParam) throws DAOException, RecordNotFoundException;
	
	public void insertServiceParam(ServiceParameter newServiceParam) throws DAOException;

}
