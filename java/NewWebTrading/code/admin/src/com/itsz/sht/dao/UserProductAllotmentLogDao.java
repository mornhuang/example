package com.itsz.sht.dao;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.UserProductAllotmentLog;

public interface UserProductAllotmentLogDao {
	public void addUserProductAllotmentLog(UserProductAllotmentLog userProductAllotmentLog)throws DataAccessException;
	public void updateUserProductAllotmentLog(UserProductAllotmentLog userProductAllotmentLog)throws DataAccessException;
	public void deleteUserProductAllotmentLog(String userProductAllotmentLogId)throws DataAccessException;
	public UserProductAllotmentLog getUserProductAllotmentLog(String userProductAllotmentLogId)throws DataAccessException;

}
