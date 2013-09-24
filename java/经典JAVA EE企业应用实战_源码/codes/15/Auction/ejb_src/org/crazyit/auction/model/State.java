package org.crazyit.auction.model;

import javax.persistence.*;
import java.util.*;
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
@Table(name="state")
public class State
{
	//标识属性
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="state_id")
	private Integer id;
	//物品的状态名
	@Column(name="state_name" , length=10
		, nullable=false)
	private String stateName;
	/*该状态下的所有物品
	  设置了mappedBy属性表明State实体不控制关联关系，
	  因此不能增加@JoinTable和@JoinColumn修饰*/
	@OneToMany(cascade=CascadeType.ALL , mappedBy="itemState"
		, targetEntity=Item.class)
	private Set<Item> items = new HashSet<Item>();

	//无参数的构造器
	public State()
	{
	}
	//初始化全部基本属性的构造器
	public State(Integer id , String stateName)
	{
		this.id = id;
		this.stateName = stateName;
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

	//stateName属性的setter和getter方法
	public void setStateName(String stateName)
	{
		this.stateName = stateName;
	}
	public String getStateName()
	{
		return this.stateName;
	}

	//items属性的setter和getter方法
	public void setItems(Set<Item> items)
	{
		this.items = items;
	}
	public Set<Item> getItems()
	{
		return this.items;
	}
}