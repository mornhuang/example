package com.itsz.sht.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.RtqAccountAssignmentLog;

public interface RtqAccountAssignmentLogDao {

	public void addRtqAccountAssignmentLog(RtqAccountAssignmentLog rtqAccountAssignmentLog) throws DataAccessException;
	public void updateRtqAccountAssignmentLog(RtqAccountAssignmentLog rtqAccountAssignmentLog) throws DataAccessException;
	public void deleteRtqAccountAssignmentLog(String rtqAccountAssignmentLogId) throws DataAccessException;
	public RtqAccountAssignmentLog getRtqAccountAssignmentLog(String rtqAccountAssignmentLogId) throws DataAccessException;
	public void addRtqAccountAssignmentLog(List<RtqAccountAssignmentLog> rtqAccountAssignmentLogList) throws DataAccessException;
}
