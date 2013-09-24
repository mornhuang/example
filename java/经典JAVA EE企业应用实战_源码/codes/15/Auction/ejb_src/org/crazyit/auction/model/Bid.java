package org.crazyit.auction.model;

import java.util.*;
import javax.persistence.*;
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
@Entity
@Table(name="bid")
public class Bid
{
	//标识属性
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bid_id")
	private Integer id;
	//竞价的价格
	@Column(name="bid_price"
		, nullable=false)
	private double bidPrice;
	//竞价的日期
	@Column(name="bid_date"
		, nullable=false)
	@Temporal(TemporalType.DATE)
	private Date bidDate;
	//本次竞价所竞拍的物品
	@ManyToOne(fetch=FetchType.EAGER
		,targetEntity=Item.class , cascade=CascadeType.ALL)
	/* 使用@JoinColumn来配置外键列的信息 */
	@JoinColumn(name="item_id", nullable=false)
	private Item bidItem;
	//参与竞价的用户
	@ManyToOne(fetch=FetchType.EAGER
		,targetEntity=AuctionUser.class , cascade=CascadeType.ALL)
	/* 使用@JoinColumn来配置外键列的信息 */
	@JoinColumn(name="user_id", nullable=false)
	private AuctionUser bidUser;


	//无参数的构造器
	public Bid()
	{
	}
	//初始化全部基本属性的构造器
	public Bid(Integer id , double bidPrice , Date bidDate)
	{
		this.id = id;
		this.bidPrice = bidPrice;
		this.bidDate = bidDate;
	}

	//id属性的setter和getter方法
	public void setId(Integer id)
	{
		this.id = id;
	}
	public Integer getId()
	{
		return this.id;
	}

	//bidPrice属性的setter和getter方法
	public void setBidPrice(double bidPrice)
	{
		this.bidPrice = bidPrice;
	}
	public double getBidPrice()
	{
		return this.bidPrice;
	}

	//bidDate属性的setter和getter方法
	public void setBidDate(Date bidDate)
	{
		this.bidDate = bidDate;
	}
	public Date getBidDate()
	{
		return this.bidDate;
	}

	//bidItem属性的setter和getter方法
	public void setBidItem(Item bidItem)
	{
		this.bidItem = bidItem;
	}
	public Item getBidItem()
	{
		return this.bidItem;
	}

	//bidUser属性的setter和getter方法
	public void setBidUser(AuctionUser bidUser)
	{
		this.bidUser = bidUser;
	}
	public AuctionUser getBidUser()
	{
		return this.bidUser;
	}

	public int hashCode()
	{
		return bidUser.getUsername().hashCode()
			+ bidItem.hashCode() * 13 + (int)bidPrice * 19;
	}

	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj != null && obj.getClass() == Bid.class)
		{
			Bid bid = (Bid)obj;
			if (bid.getBidUser().getUsername().equals(bidUser.getUsername())
				&& bid.getBidItem().equals(this.getBidItem()) 
				&& bid.getBidPrice() == this.getBidPrice())
			{
				return true;
			}
		}
		return false;
	}
}