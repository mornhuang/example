package com.itsz.sht.dao.impl;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.DeftDebtAccChngLogDao;
import com.itsz.sht.dao.hibernate.model.DeftDebtAccChngLog;

public class DeftDebtAccChngLogDaoImpl  extends BaseDao<DeftDebtAccChngLog> implements DeftDebtAccChngLogDao{

	public DeftDebtAccChngLogDaoImpl() {
		super(DeftDebtAccChngLog.class);

	}

	@Override
	public void addDefDebtAccChgLog(DeftDebtAccChngLog deftDebtAccChngLog)
			throws DataAccessException {
		this.save(deftDebtAccChngLog);
		
	}

	@Override
	public void deteleDefDebtAccChgLog(String deftDebtAccChngLogId)
			throws DataAccessException {
		this.remove(deftDebtAccChngLogId);
		
	}

	@Override
	public DeftDebtAccChngLog getDefDebtAccChgLog(String deftDebtAccChngLogId)
			throws DataAccessException {
		
		return this.get(deftDebtAccChngLogId);
	}

	@Override
	public void updateDefDebtAccChgLog(DeftDebtAccChngLog deftDebtAccChngLog)
			throws DataAccessException {
		this.update(deftDebtAccChngLog);
		
	}

}
