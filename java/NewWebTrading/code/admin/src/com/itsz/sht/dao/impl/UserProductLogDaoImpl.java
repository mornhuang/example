package com.itsz.sht.dao.impl;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.UserProductLogDao;
import com.itsz.sht.dao.hibernate.model.UserProductLog;

public class UserProductLogDaoImpl extends BaseDao<UserProductLog> implements
		UserProductLogDao {

	public UserProductLogDaoImpl() {
		super(UserProductLog.class);
	}

	@Override
	public void addUserProductLog(UserProductLog userProductLog)
			throws DataAccessException {
		this.save(userProductLog);
		
	}

	@Override
	public void deleteUserProductLog(String userProductLogId)
			throws DataAccessException {
		this.remove(userProductLogId);
		
	}

	@Override
	public UserProductLog getUserProductLog(String userProductLogId)
			throws DataAccessException {
		return this.get(userProductLogId);
	}

	@Override
	public void updateUserProductLog(UserProductLog userProductLog)
			throws DataAccessException {
		this.update(userProductLog);
	}

}
