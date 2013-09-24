package org.crazyit.app.action;

import com.opensymphony.xwork2.*;
import java.util.Date;
import java.util.Set;

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
public class LoginAction extends ActionSupport
{
	private Set users;
	private Date birth;
	//user属性的setter和getter方法
	public void setUsers(Set users)
	{
		this.users = users;
	}
	public Set getUsers()
	{
		return this.users;
	}
	//birth属性的setter和getter方法
	public void setBirth(Date birth)
	{
		this.birth = birth;
	}
	public Date getBirth()
	{
		return this.birth;
	}
	//没有提供execute()方法,
	//将直接使用ActionSupport的execute()方法
}