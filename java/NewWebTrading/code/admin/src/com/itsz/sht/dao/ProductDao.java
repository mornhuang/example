package com.itsz.sht.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.Product;

public interface ProductDao {
	
	public void addProduct(Product product) throws DataAccessException;
	public void upateProduct(Product product) throws DataAccessException;
	public void deleteProduct(String productId) throws DataAccessException;
	public Product getProduct(String productId) throws DataAccessException;
	public List<Product> getProductList()throws DataAccessException;
	public List<Product> findRTQProductList(String clientId) throws DataAccessException;
	public List<Product> findRTQProductListByClientCnDiscFlag(String cnDiscFlag) throws DataAccessException;
	public Product findRTQProduct(String clientId) throws DataAccessException;
	public List<Product> findProductListByClientCnDiscFlag(String cnDiscFlag) throws DataAccessException;
	
}

