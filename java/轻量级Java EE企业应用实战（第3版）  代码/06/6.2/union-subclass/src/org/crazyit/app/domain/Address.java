package org.crazyit.app.domain;

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
public class Address
{
	//定义该Address的详细信息
	private String detail;
	//定义该Address的邮编信息
	private String zip;
	//定义该Address的国家信息
	private String country;	//无参数的构造器
	public Address()
	{
	}
	//初始化全部属性的构造器
	public Address(String detail , String zip , String country)
	{
		this.detail = detail;
		this.zip = zip;
		this.country = country;
	}
	
	//detail属性的setter和getter方法
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
	public String getDetail()
	{
		return this.detail;
	}
	
	//zip属性的setter和getter方法
	public void setZip(String zip)
	{
		this.zip = zip;
	}
	public String getZip()
	{
		return this.zip;
	}
	
	//country属性的setter和getter方法
	public void setCountry(String country)
	{
		this.country = country;
	}
	public String getCountry()
	{
		return this.country;
	}
}