package org.crazyjava.inputtip.dao;

import java.util.List;
import org.crazyjava.inputtip.model.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, crazyjava.inputtip.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  crazyjava.inputtip.H.Lee kongcrazyjava.inputtip@163.com
 * @version  1.0
 */
public interface BrandDao  
{
	/**
	 * 根据id查找品牌
	 * @param id 需要查找的品牌id
	 */
	Brand get(Integer id);
	
	/**
	 * 增加品牌
	 * @param Brand 需要增加的品牌
	 */
	void save(Brand Brand);

	/**
	 * 修改品牌
	 * @param Brand 需要修改的品牌
	 */
	void update(Brand Brand);

	/**
	 * 删除品牌
	 * @param id 需要删除的品牌id
	 */
	void delete(Integer id);

	/**
	 * 删除品牌
	 * @param Brand 需要删除的品牌
	 */
	void delete(Brand Brand);

	/**
	 * 查询全部品牌
	 * @return 全部品牌
	 */
	List<Brand> findAll();
}