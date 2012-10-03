package com.itsz.sht.dao.impl;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.ProductChangeLogDao;
import com.itsz.sht.dao.hibernate.model.ProductChangeLog;

public class ProductChangeLogDaoImpl extends BaseDao<ProductChangeLog> implements ProductChangeLogDao {

	public ProductChangeLogDaoImpl() {
		super(ProductChangeLog.class);

	}

	@Override
	public void addProductChangeLog(ProductChangeLog productChangeLog)
			throws DataAccessException {
		this.save(productChangeLog);
	}

	@Override
	public void deleteProductChangeLog(String productChangeLogId)
			throws DataAccessException {
	    this.remove(productChangeLogId);
	}

	@Override
	public ProductChangeLog getProductChangeLog(String productChangeLogId)
			throws DataAccessException {
		return this.get(productChangeLogId);
	}

	@Override
	public void updateProductChangeLog(ProductChangeLog productChangeLog)
			throws DataAccessException {
		this.update(productChangeLog);
		
	}
   
	
}
