package org.crazyit.auction.eao.impl;

import java.util.*;

import javax.ejb.*;

import org.crazyit.auction.model.*;
import org.crazyit.auction.eao.*;

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
@Stateless(name="stateEao")
public class StateEaoImpl
	extends CrazyItEao implements StateEao  
{

	/**
	 * 查询全部物品状态
	 * @return 获得全部物品状态
	 */
	public List<State> findAll()
	{
		return getResultList(State.class 
			, "" , null);
	}
}