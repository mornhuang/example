package com.itsz.sht.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.itsz.sht.dao.hibernate.model.CsParameter;

public interface CsParameterDao {
	public CsParameter getCsParameter(String keyPk) throws DataAccessException;
	public void addCsParameter(CsParameter csParameter) throws DataAccessException;
	public void updateCsParameter(CsParameter csParameter) throws DataAccessException;
	public void updateMisDayEndProcessingFlag(String misDayEndProcessingFlag) throws DataAccessException;
	public List<CsParameter> getAllCsParamter() throws DataAccessException;
}
