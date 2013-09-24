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
public class User
{
	private String name;
	private String pass;
	
	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	//pass属性的setter和getter方法
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getPass()
	{
		return this.pass;
	}
	
	public boolean equals(Object obj)
	{
		//待比较的两个对象是同一个对象，直接返回true
		if(this == obj)
		{
			return true;
		}
		//只有当obj是User对象
		if (obj != null && obj.getClass() == User.class)
		{
			User user = (User)obj;
			//两个对象的name属性相等即认为二者相等。
			if (this.getName().equals(user.getName()))
			{
				return true;
			}
		}
		return false;
	}
	//根据name属性来计算hashCode。
	public int hashCode()
	{
		return name.hashCode();
	}
}