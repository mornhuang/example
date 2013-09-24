package org.crazyit.eao;

import javax.ejb.*;
import java.util.*;

import org.crazyit.model.*;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Local
public interface NewsEao
{
	/**
	 * 根据id查找News实体
	 * @param id 需要查找的News的id
	 */ 
	News get(Integer id);
	/**
	 * 增加News实体
	 * @param news 需要增加的News实体
	 */	  
	void save(News news);
	/**
	 * 修改News实体
	 * @param news 需要修改的News实体
	 */
	void update(News news);
	/**
	 * 删除News实体
	 * @param id 需要删除的News的id
	 */ 
	void delete(Integer id);
	/**
	 * 删除News实体
	 * @param news 需要删除的News实体
	 */
	void delete(News news);
	/**
	 * 查询全部的News实体
	 * @return 获得全部的News实体
	 */ 
	List<News> findAll();
}
