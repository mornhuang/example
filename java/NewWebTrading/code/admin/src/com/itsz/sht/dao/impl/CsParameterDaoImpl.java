/**
 * 
 */
package com.itsz.sht.dao.impl;

import java.util.List;
import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.CsParameterDao;
import com.itsz.sht.dao.hibernate.model.CsParameter;

/**
 * @author cyzeng
 *
 */
public class CsParameterDaoImpl extends BaseDao<CsParameter> implements
		CsParameterDao {

	public CsParameterDaoImpl() {
		super(CsParameter.class);
	}
	
	/* (non-Javadoc)
	 * @see com.itsz.sht.dao.CsParameterDao#addCsParameter(com.itsz.sht.dao.hibernate.model.CsParameter)
	 */
	@Override
	public void addCsParameter(CsParameter csParameter)
			throws DataAccessException {
		this.save(csParameter);
	}

	/* (non-Javadoc)
	 * @see com.itsz.sht.dao.CsParameterDao#getAllCsParamter()
	 */
	@Override
	public List<CsParameter> getAllCsParamter() throws DataAccessException {
		// TODO Auto-generated method stub
		return this.list();
	}

	/* (non-Javadoc)
	 * @see com.itsz.sht.dao.CsParameterDao#getCsParameter(java.lang.String)
	 */
	@Override
	public CsParameter getCsParameter(String keyPk) throws DataAccessException {
		return this.get(keyPk);
	}

	/* (non-Javadoc)
	 * @see com.itsz.sht.dao.CsParameterDao#updateCsParameter(com.itsz.sht.dao.hibernate.model.CsParameter)
	 */
	@Override
	public void updateCsParameter(CsParameter csParameter)
			throws DataAccessException {
		this.update(csParameter);
	}

	@Override
	public void updateMisDayEndProcessingFlag(String misDayEndProcessingFlag) throws DataAccessException{
		String hql = "update CsParameter cp set value='" + misDayEndProcessingFlag + "' where keyPk='MisDayEndProcessingFlag'";
		this.deleteByHql(hql);
	}
}
