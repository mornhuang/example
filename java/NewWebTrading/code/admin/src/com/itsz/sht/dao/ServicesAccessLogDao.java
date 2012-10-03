package com.itsz.sht.dao;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.RtqAccAsgnId;
import com.itsz.sht.dao.hibernate.model.ServicesAccessLog;

public interface ServicesAccessLogDao {

	public void addServiceAccessLog(ServicesAccessLog servicesAccessLog)throws DataAccessException;
	public void updateServiceAccessLog(ServicesAccessLog servicesAccessLog)throws DataAccessException;
	public void deleteServiceAccessLog(String servicesAccessLogId)throws DataAccessException;
	public ServicesAccessLog getServiceAccessLog(String servicesAccessLogId)throws DataAccessException;
	public ServicesAccessLog getServicesAccessLogByRtqAccAsgnId(RtqAccAsgnId id) throws DataAccessException;
}
