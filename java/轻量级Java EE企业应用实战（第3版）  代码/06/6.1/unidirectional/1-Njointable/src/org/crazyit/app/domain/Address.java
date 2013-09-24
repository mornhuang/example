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
	//标识属性
	private int addressId;
	//地址详细信息
	private String addressDetail;
	
	//无参数的构造器
	public Address()
	{
	}
	//初始化addressDetail属性的构造器
	public Address(String addressDetail)
	{
		this.addressDetail = addressDetail;
	}
	
	//addressId属性的setter和getter方法
	public void setAddressId(int addressId)
	{
		this.addressId = addressId;
	}
	public int getAddressId()
	{
		return this.addressId;
	}
	
	//addressDetail属性的setter和getter方法
	public void setAddressDetail(String addressDetail)
	{
		this.addressDetail = addressDetail;
	}
	public String getAddressDetail()
	{
		return this.addressDetail;
	}
}