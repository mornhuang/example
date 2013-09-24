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
@Table(name="auction_user")
public class AuctionUser
{
	//标识属性
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer id;
	//用户名属性
	@Column(name="username" , length=50)
	private String username;
	//密码属性
	@Column(name="userpass" , length=50)
	private String userpass;
	//电子邮件属性
	@Column(name="email" , length=100)
	private String email;

	/*根据属主关联的物品实体
	  设置了mappedBy属性表明AuctionUser实体不控制关联关系，
	  因此不能增加@JoinTable和@JoinColumn修饰*/
	@OneToMany(cascade=CascadeType.ALL , mappedBy="owner"
		, targetEntity=Item.class)
	private Set<Item> itemsByOwner = new HashSet<Item>();
	/*根据赢取者关联的物品实体
	  设置了mappedBy属性表明AuctionUser实体不控制关联关系，
	  因此不能增加@JoinTable和@JoinColumn修饰*/
	@OneToMany(cascade=CascadeType.ALL , mappedBy="winer"
		, targetEntity=Item.class)
	private Set<Item> itemsByWiner = new HashSet<Item>();
	/*该用户所参与的全部竞价
	  设置了mappedBy属性表明AuctionUser实体不控制关联关系，
	  因此不能增加@JoinTable和@JoinColumn修饰*/
	@OneToMany(cascade=CascadeType.ALL , mappedBy="bidUser"
		, targetEntity=Bid.class)
	private Set<Bid> bids = new HashSet<Bid>();

	//无参数的构造器
	public AuctionUser()
	{
	}
	//初始化全部基本属性的构造器
	public AuctionUser(Integer id , String username ,
		String userpass , String email)
	{
		this.id = id;
		this.username = username;
		this.userpass = userpass;
		this.email = email;
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

	//username属性的setter和getter方法
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getUsername()
	{
		return this.username;
	}

	//userpass属性的setter和getter方法
	public void setUserpass(String userpass)
	{
		this.userpass = userpass;
	}
	public String getUserpass()
	{
		return this.userpass;
	}

	//email属性的setter和getter方法
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return this.email;
	}

	//itemsByOwner属性的setter和getter方法
	public void setItemsByOwner(Set<Item> itemsByOwner)
	{
		this.itemsByOwner = itemsByOwner;
	}
	public Set<Item> getItemsByOwner()
	{
		return this.itemsByOwner;
	}

	//itemsByWiner属性的setter和getter方法
	public void setItemsByWiner(Set<Item> itemsByWiner)
	{
		this.itemsByWiner = itemsByWiner;
	}
	public Set<Item> getItemsByWiner()
	{
		return this.itemsByWiner;
	}

	//bids属性的setter和getter方法
	public void setBids(Set<Bid> bids)
	{
		this.bids = bids;
	}
	public Set<Bid> getBids()
	{
		return this.bids;
	}
}