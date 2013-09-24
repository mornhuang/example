package org.crazyit.service;

import java.util.*;
import javax.ejb.*;

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
@Stateless(name="userService")
public class UserServiceBean 
	implements UserService
{
	//根据用户名、密码判断指定用户是否存在
	public boolean loginPro(String name , String pass)
	{
		//正常情况下，业务逻辑组件应该调用EAO来访问数据库，
		//根据底层数据库的记录来判断是否存在该用户,由于现在还未正式介绍
		//Session Facade EAO，故直接在这里判断了。
		if (name.equals("crazyit") && pass.equals("leegang"))
		{
			return true;
		}
		return false;
	}
}