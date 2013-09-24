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
@Table(name="kind")
public class Kind
{
	//标识属性
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="kind_id")
	private Integer id;
	//种类名
	@Column(name="kind_name" , length=50
		, nullable=false)
	private String kindName;
	//种类描述
	@Column(name="kind_desc" , length=255
		, nullable=false)
	private String kindDesc;
	/*该种类下的所有物品
	  设置了mappedBy属性表明Kind实体不控制关联关系，
	  因此不能增加@JoinTable和@JoinColumn修饰*/
	@OneToMany(cascade=CascadeType.ALL , mappedBy="kind"
		, targetEntity=Item.class)
	private Set<Item> items = new HashSet<Item>();

	//无参数的构造器
	public Kind()
	{
	}
	//初始化全部基本属性的构造器
	public Kind(Integer id , String kindName , String kindDesc)
	{
		this.id = id;
		this.kindName = kindName;
		this.kindDesc = kindDesc;
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

	//kindName属性的setter和getter方法
	public void setKindName(String kindName)
	{
		this.kindName = kindName;
	}
	public String getKindName()
	{
		return this.kindName;
	}

	//kindDesc属性的setter和getter方法
	public void setKindDesc(String kindDesc)
	{
		this.kindDesc = kindDesc;
	}
	public String getKindDesc()
	{
		return this.kindDesc;
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