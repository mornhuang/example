package org.crazyjava.inputtip.service.impl;

import org.crazyjava.inputtip.dao.*;
import org.crazyjava.inputtip.model.*;
import org.crazyjava.inputtip.service.*;

import java.util.*;
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
public class TipServiceImpl implements TipService
{
	//实现业务逻辑方法所依赖的两个DAO组件
	private BrandDao bd;
	private ModelDao md;
	//依赖注入两个DAO组件所必须的setter方法
	public void setBrandDao(BrandDao bd)
	{
		this.bd = bd;
	}
	public void setModelDao(ModelDao md)
	{
		this.md = md;
	}
	////根据品牌前缀返回所有以该前缀开始的名牌名
	public List<String> getBrandsByPrefix(String prefix)
	{
		//返回所有的品牌记录
		List<Brand> brands = bd.findAll();
		List<String> result = new ArrayList<String>();
		//遍历所有的品牌记录
		for (Brand brand : brands )
		{
			//如果品牌记录以前缀开始
			if (brand.getName().toUpperCase()
				.startsWith(prefix.toUpperCase()))
			{
				//将该品牌添加到结果集合里
				result.add(brand.getName());
			}
		}
		return result;
	}
	//根据品牌名返回该品牌的所有型号名字
	public List<String> getModelsByBrand(String brand)
	{
		List<String> result = new ArrayList<String>();
		//根据品牌返回所有的型号实例
		List<Model> mlist = md.findByBrand(brand);
		//遍历型号集合，将每个型号的名字添加到结果集合里
		for (Model model : mlist )
		{
			result.add(model.getName());
		}
		return result;
	}
	//根据型号名，返回型号描述
	public String getDescByModel(String model)
	{
		//根据型号名返回特定的型号实体
		Model m = md.findByModel(model);
		//如果型号不为空，则返回型号描述
		if (m != null)
		{
			return m.getDesc();
		}
		return null;
	}
}