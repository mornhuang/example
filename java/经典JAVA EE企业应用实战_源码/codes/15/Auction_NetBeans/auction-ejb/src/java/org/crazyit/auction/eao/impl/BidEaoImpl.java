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
@Stateless(name="bidEao")
public class BidEaoImpl
	extends CrazyItEao implements BidEao  
{

	/**
	 * 根据用户查找竞价
	 * @param id 用户id
	 * @return 用户对应的全部
	 * @return 用户对应的全部竞价
	 */
	public List<Bid> findByUser(Integer userId)
	{
		return getResultList(Bid.class 
			, "where o.bidUser.id = ?1"
			, null
			, userId);
	}
	/**
	 * 根据物品id，以及出价查询用户
	 * @param itemId 物品id;
	 * @param price 竞价的价格
	 * @return 对指定物品、指定竞价对应的用户
	 */
	public AuctionUser findUserByItemAndPrice(Integer itemId , Double price)
	{
		List<Bid> bidList = getResultList(Bid.class 
			, "where o.bidItem.id = ?1 and o.bidPrice = ?2"
			, null
			, itemId , price);
		//返回查询得到的第一个Bid对象关联的AuctionUser对象
		if (bidList!= null && bidList.size() >= 1)
		{
			return bidList.get(0).getBidUser();
		}
		return null;
	}
}