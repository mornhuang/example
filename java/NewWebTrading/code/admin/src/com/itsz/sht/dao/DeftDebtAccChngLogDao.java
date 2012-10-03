package com.itsz.sht.dao;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.DeftDebtAccChngLog;


public interface DeftDebtAccChngLogDao {
	public void addDefDebtAccChgLog(DeftDebtAccChngLog deftDebtAccChngLog)throws DataAccessException;
	public void updateDefDebtAccChgLog(DeftDebtAccChngLog deftDebtAccChngLog)throws DataAccessException;
	public DeftDebtAccChngLog getDefDebtAccChgLog(String deftDebtAccChngLogId)throws DataAccessException;
	public void deteleDefDebtAccChgLog(String deftDebtAccChngLogId)throws DataAccessException;
    
}
