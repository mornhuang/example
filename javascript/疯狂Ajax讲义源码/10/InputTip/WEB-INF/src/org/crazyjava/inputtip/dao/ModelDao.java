package org.crazyjava.inputtip.dao;

import java.util.List;

import org.crazyjava.inputtip.model.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public interface ModelDao  
{
	/**
	 * 根据id查找型号
	 * @param id 需要查找的型号id
	 */
	 Model get(Integer id);
	
	/**
	 * 增加型号
	 * @param Model 需要增加的型号
	 */
	void save(Model model);

	/**
	 * 修改型号
	 * @param Model 需要修改的型号
	 */
	void update(Model model);

	/**
	 * 删除型号
	 * @param id 需要删除的型号id
	 */
	void delete(Integer id);

	/**
	 * 删除型号
	 * @param Model 需要删除的型号
	 */
	void delete(Model model);

	/**
	 * 查询全部型号
	 * @return 全部型号
	 */
	List<Model> findAll();

	/**
	 * 根据品牌查询型号
	 * @param brand 需要查询的品牌
	 * @return 该品牌对应的全部的型号
	 */
	List<Model> findByBrand(String brand);

	/**
	 * 根据型号查询型号
	 * @param model 需要查询的型号
	 * @return 该型号对应的全部的型号
	 */
	Model findByModel(String model);
}