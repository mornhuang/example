package org.crazyit.service;

import java.util.*;
import javax.ejb.*;

import javax.annotation.security.*;

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
@Stateless(mappedName="SecurityService" 
	, name="SecurityServiceEJB")
//声明两个角色
@DeclareRoles({"customer" , "admin"})
public class SecurityServiceBean
	implements SecurityService
{
	private static Map<String , Double> items
		= Collections.synchronizedMap(new HashMap<String ,Double>());
	static {
		items.put("疯狂Java讲义" , 99.0);
		items.put("轻量级Java EE企业应用实战" , 89.0);	
	}
	@PermitAll
	public Map<String , Double> getAllItem()
	{
		return items;
	}
	//只有拥有admin角色才能调用该方法
	@RolesAllowed("admin")
	public void addItem(String name , double price)
	{
		items.put(name, price);
	}
	//只有拥有customer角色才能调用该方法
	@RolesAllowed({"customer" , "admin"})
	public double buyItem(String name)
	{
		System.out.println("您选择购买" + name + "物品");
		return items.get(name);
	}
}
