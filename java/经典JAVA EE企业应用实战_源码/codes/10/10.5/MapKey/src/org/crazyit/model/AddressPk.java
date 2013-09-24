package org.crazyit.model;

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
public class AddressPk
	implements java.io.Serializable
{
	private String zip;
	private String phone;

	//无参数的构造器
	public AddressPk()
	{
	}
	//初始化全部属性的构造器
	public AddressPk(String zip , String phone)
	{
		this.zip = zip;
		this.phone = phone;
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

	//phone属性的setter和getter方法
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getPhone()
	{
		return this.phone;
	}
	//重写equals方法
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj.getClass() == AddressPk.class)
		{
			AddressPk target = (AddressPk)obj;
			if (target.getZip().equals(zip)
				&& target.getPhone().equals(phone))
			{
				return true;
			}
		}
		return false;
	}
	//重写hashCode方法
	public int hashCode()
	{
		return zip.hashCode() * 17
			+ phone.hashCode();
	}

}