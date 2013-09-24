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
@NamedNativeQuery(name="news_query"
	, query="select id, news_title , content"
	+ " from news_table where id > ?"
	, resultSetMapping="news_mapping")
@SqlResultSetMapping(name="news_mapping",
	entities={
		@EntityResult(entityClass=org.crazyit.model.News.class 
			, fields={
		@FieldResult(name="id", column="id"),
		@FieldResult(name="title", column="news_title"),
		@FieldResult(name="content", column="content")})
	},
	columns={
		@ColumnResult(name="news_title"),
		@ColumnResult(name="content")}
)
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
	/* @Column指定该Field映射的列信息，此处指定允许为null */
	@Column(nullable=true)
	private String content;
	//构造器
	public News()
	{
	}
	//标识属性的setter和getter方法
	public void setId(int id) 
	{
		this.id = id; 
	}
	public int getId()
	{
		return (this.id); 
	}
	//消息标题的setter方法和getter方法
	public void setTitle(String title) 
	{
		this.title = title; 
	}
	public String getTitle() 
	{
		return (this.title); 
	}
	//消息内容的setter方法和getter方法
	public void setContent(String content)
	{
		this.content = content; 
	}
	public String getContent()
	{
		return (this.content); 
	}
}
