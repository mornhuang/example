package com.itsz.sht.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.RtqApplication;

public interface RtqApplicationDao {

	public void addRtqApplication(RtqApplication rtqApplication) throws DataAccessException;
	public void updateRtqApplication(RtqApplication rtqApplication) throws DataAccessException;
	public void deleteRtqApplication(String productId) throws DataAccessException;
	public RtqApplication getRTQApplication(String productId) throws DataAccessException;
	public List<RtqApplication> listRTQApplication() throws DataAccessException;
	public void addBatchRtqApp(List<RtqApplication> list)throws DataAccessException;
}
