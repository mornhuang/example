package org.crazyit.hrsystem.vo;

import java.io.Serializable;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class AppBean implements Serializable
{
	private static final long serialVersionUID = 48L;

	private int id;
	private String emp;
	private String unAttend;
	private String toAttend;
	private String reason;

	//无参数的构造器
	public AppBean()
	{
	}
	//初始化全部属性的构造器
	public AppBean(int id , String emp , String unAttend 
		, String toAttend , String reason)
	{
		this.id = id;
		this.emp = emp;
		this.unAttend = unAttend;
		this.toAttend = toAttend;
		this.reason = reason;
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

	//emp属性的setter和getter方法
	public void setEmp(String emp)
	{
		this.emp = emp;
	}
	public String getEmp()
	{
		return this.emp;
	}

	//unAttend属性的setter和getter方法
	public void setUnAttend(String unAttend)
	{
		this.unAttend = unAttend;
	}
	public String getUnAttend()
	{
		return this.unAttend;
	}

	//toAttend属性的setter和getter方法
	public void setToAttend(String toAttend)
	{
		this.toAttend = toAttend;
	}
	public String getToAttend()
	{
		return this.toAttend;
	}

	//reason属性的setter和getter方法
	public void setReason(String reason)
	{
		this.reason = reason;
	}
	public String getReason()
	{
		return this.reason;
	}

}