package com.itsz.sht.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.RtqAccountAssignmentLogDao;
import com.itsz.sht.dao.hibernate.model.RtqAccountAssignmentLog;

public class RtqAccountAssignmentLogDaoImpl extends BaseDao<RtqAccountAssignmentLog>implements RtqAccountAssignmentLogDao {

	public RtqAccountAssignmentLogDaoImpl() {
		super(RtqAccountAssignmentLog.class);
	
	}

	@Override
	public void addRtqAccountAssignmentLog(
			RtqAccountAssignmentLog rtqAccountAssignmentLog)
			throws DataAccessException {
		this.save(rtqAccountAssignmentLog);
	}

	@Override
	public void deleteRtqAccountAssignmentLog(String rtqAccountAssignmentLogId)
			throws DataAccessException {
		this.remove(rtqAccountAssignmentLogId);
		
	}

	@Override
	public RtqAccountAssignmentLog getRtqAccountAssignmentLog(String rtqAccountAssignmentLogId)
			throws DataAccessException {
	
		return  this.get(rtqAccountAssignmentLogId);
	}

	@Override
	public void updateRtqAccountAssignmentLog(
			RtqAccountAssignmentLog rtqAccountAssignmentLog)
			throws DataAccessException {
		this.update(rtqAccountAssignmentLog);
		
	}

	@Override
	public void addRtqAccountAssignmentLog(
			List<RtqAccountAssignmentLog> rtqAccountAssignmentLogList)
			throws DataAccessException {
	   this.saveBatch(rtqAccountAssignmentLogList);
		
	}

}
