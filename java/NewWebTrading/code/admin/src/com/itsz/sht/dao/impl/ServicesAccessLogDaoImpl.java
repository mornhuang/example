package com.itsz.sht.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.ServicesAccessLogDao;
import com.itsz.sht.dao.hibernate.model.RtqAccAsgnId;
import com.itsz.sht.dao.hibernate.model.ServicesAccessLog;

public class ServicesAccessLogDaoImpl extends BaseDao<ServicesAccessLog> implements ServicesAccessLogDao {

	public ServicesAccessLogDaoImpl() {
		super(ServicesAccessLog.class);
	}

	@Override
	public void addServiceAccessLog(ServicesAccessLog servicesAccessLog)
			throws DataAccessException {
	 this.save(servicesAccessLog);
		
	}

	@Override
	public void deleteServiceAccessLog(String servicesAccessLogId)
			throws DataAccessException {
		this.remove(servicesAccessLogId);
		
	}

	@Override
	public ServicesAccessLog getServiceAccessLog(String servicesAccessLogId)
			throws DataAccessException {
	
		return this.get(servicesAccessLogId);
	}

	@Override
	public void updateServiceAccessLog(ServicesAccessLog servicesAccessLog)
			throws DataAccessException {
		this.update(servicesAccessLog);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServicesAccessLog getServicesAccessLogByRtqAccAsgnId(RtqAccAsgnId id)
			throws DataAccessException {
		ServicesAccessLog serAccLog = null;
		String hql = "from ServicesAccessLog lo where lo.actnDate=(select max(lo2.actnDate) from ServicesAccessLog lo2 where lo2.prodId='"
					+ id.getProdId() + "' and lo2.clntId='" 
					+ id.getClntId() + "' and lo2.rtqLognId='"
					+ id.getRtqLognId() + "') and lo.prodId='"
					+ id.getProdId() + "' and lo.clntId='" 
					+ id.getClntId() + "' and lo.rtqLognId='"
					+ id.getRtqLognId() + "' order by lo.actnDate desc";
		List<ServicesAccessLog> serAccLogList = this.listByHql(hql);
		if(serAccLogList!=null && serAccLogList.size()>0 ){
			serAccLog = serAccLogList.get(0);
		}
		return serAccLog;
	}

}
