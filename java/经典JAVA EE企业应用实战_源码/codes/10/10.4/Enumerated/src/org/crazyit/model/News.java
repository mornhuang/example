package org.crazyit.model;

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
@Table(name="news_table")
public class News
{
	//消息类的标识属性
	@Id /* 用于修饰标识属性 */
	/* 指定该主键列的主键生成策略 */
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//消息标题
	/* @Column指定该Field映射的列信息，此处指定了列名、长度 */
	@Column(name="news_title" , length=50)
	private String title;
	//消息内容
	private String content;
	@Enumerated(EnumType.ORDINAL)
	@Column(name="happen_season")
	private Season happenSeason;
	//无参数的构造器
	public News()
	{
	}
	//初始化全部属性的构造器
	public News(int id , String title 
		, String content , Season happenSeason)
	{
		this.id = id;
		this.title = title;
		this.content = content;
		this.happenSeason = happenSeason;
	}

	//id属性的setter和getter方法
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return this.id;
	}

	//title属性的setter和getter方法
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return this.title;
	}

	//content属性的setter和getter方法
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getContent()
	{
		return this.content;
	}

	//happenSeason属性的setter和getter方法
	public void setHappenSeason(Season happenSeason)
	{
		this.happenSeason = happenSeason;
	}
	public Season getHappenSeason()
	{
		return this.happenSeason;
	}
}