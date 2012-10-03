package com.itsz.sht.dao;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.UserProductLog;

public interface UserProductLogDao {

	public void addUserProductLog(UserProductLog userProductLog)throws DataAccessException;
	public void updateUserProductLog(UserProductLog userProductLog)throws DataAccessException;
	public void deleteUserProductLog(String userProductLogId)throws DataAccessException;
	public UserProductLog getUserProductLog(String userProductLogId)throws DataAccessException;
}
