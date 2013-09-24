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
@Stateless(name="itemEao")
public class ItemEaoImpl
	extends CrazyItEao implements ItemEao  
{

	/**
	 * 根据产品分类，获取当前拍卖的全部商品
	 * @param kindId 种类id;
	 * @return 该类的全部产品
	 */
	public List<Item> findItemByKind(Integer kindId)
	{
		return getResultList(Item.class 
			, "where o.kind.id = ?1 and o.itemState.id = 1"
			, null
			, kindId);
	}

	/**
	 * 根据所有者查找处于拍卖中的物品
	 * @param useId 所有者Id;
	 * @return 指定用户处于拍卖中的全部物品
	 */
	public List<Item> findItemByOwner(Integer userId)
	{
		return getResultList(Item.class 
			, "where o.owner.id = ?1 and o.itemState.id = 1"
			, null
			, userId);
	}

	/**
	 * 根据赢取者查找物品
	 * @param userId 赢取者Id;
	 * @return 指定用户赢取的全部物品
	 */
	public List<Item> findItemByWiner(Integer userId)
	{
		return getResultList(Item.class 
			, "where o.winer.id = ?1 and o.itemState.id = 2"
			, null
			, userId);
	}

	/**
	 * 根据物品状态查找物品
	 * @param stateId 状态Id;
	 * @return 该状态下的全部物品
	 */
	public List<Item> findItemByState(Integer stateId)
	{
		return getResultList(Item.class 
			, "where o.itemState.id = ?1"
			, null
			, stateId);
	}
}