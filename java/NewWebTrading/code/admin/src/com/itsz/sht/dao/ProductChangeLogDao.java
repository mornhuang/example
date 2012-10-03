package com.itsz.sht.dao;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.ProductChangeLog;

public interface ProductChangeLogDao {

	public void addProductChangeLog(ProductChangeLog productChangeLog)throws DataAccessException;
	public void updateProductChangeLog(ProductChangeLog productChangeLog)throws DataAccessException;
	public void deleteProductChangeLog(String productChangeLogId)throws DataAccessException;
	public ProductChangeLog getProductChangeLog(String productChangeLogId)throws DataAccessException;
}
