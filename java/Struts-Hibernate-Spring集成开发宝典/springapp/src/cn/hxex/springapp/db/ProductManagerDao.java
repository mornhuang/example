package cn.hxex.springapp.db;

import java.util.List;

import cn.hxex.springapp.bus.Product;

public interface ProductManagerDao {

	public List getProductList();
	
	public void addProduct( Product p );
}
