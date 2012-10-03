package com.itsz.sht.dao.impl;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.UserProductAllotmentLogDao;
import com.itsz.sht.dao.hibernate.model.UserProductAllotmentLog;

public class UserProductAllotmentLogDaoImpl extends BaseDao<UserProductAllotmentLog> implements
		UserProductAllotmentLogDao {

	public UserProductAllotmentLogDaoImpl() {
		super(UserProductAllotmentLog.class);
	}

	@Override
	public void addUserProductAllotmentLog(
			UserProductAllotmentLog userProductAllotmentLog)
			throws DataAccessException {
		this.save(userProductAllotmentLog);
		
	}

	@Override
	public void deleteUserProductAllotmentLog(String userProductAllotmentLogId)
			throws DataAccessException {
		this.remove(userProductAllotmentLogId);
		
	}

	@Override
	public UserProductAllotmentLog getUserProductAllotmentLog(String userProductAllotmentLogId)
			throws DataAccessException {
		
		return this.get(userProductAllotmentLogId);
	}

	@Override
	public void updateUserProductAllotmentLog(
			UserProductAllotmentLog userProductAllotmentLog)
			throws DataAccessException {
		this.update(userProductAllotmentLog);
	}

}
