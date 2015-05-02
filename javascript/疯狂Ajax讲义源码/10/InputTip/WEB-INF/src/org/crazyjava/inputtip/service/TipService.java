package org.crazyjava.inputtip.service;

import org.crazyjava.inputtip.dao.*;
import org.crazyjava.inputtip.model.*;

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
public interface TipService
{
	/**
	 * 根据品牌前缀来返回所有品牌的品牌名
	 * @param prefix 品牌前缀
	 * @return 该品牌前缀对应的所有品牌名
	 */
	public List<String> getBrandsByPrefix(String prefix);

	/**
	 * 根据品牌名返回该品牌下的型号名
	 * @param brand 品牌名
	 * @return 该品牌对应的全部型号名
	 */
	public List<String> getModelsByBrand(String brand);

	/**
	 * 根据模型名来返回该模型的描述
	 * @param model 模型名
	 * @return 该模型名对应的模型描述
	 */
	public String getDescByModel(String model);
}