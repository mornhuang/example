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
@Stateless
public class UserServiceBean 
	implements UserService
{
	public boolean loginPro(String name , String pass)
	{
		//正常情况下，业务逻辑组件应该调用EAO来访问数据库，
		//根据底层数据库的记录来确定登录处理的结果
		//由于现在还未正式介绍Session Facade EAO，故此处直接判断
		if (name.equals("crazyit") && pass.equals("leegang"))
		{
			return true;
		}
		return false;
	}
}
