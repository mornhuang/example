package org.crazyit.service;

import java.util.*;
import javax.ejb.*;
import javax.annotation.*;

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
@Stateful(mappedName="Lifecycle")
public class LifecycleBean 
	implements Lifecycle
{
	private Map<String, Integer> buyInfo
		= new HashMap<String, Integer>();
	public LifecycleBean()
	{
		System.out.println("执行构造器创建Bean实例!");
	}
	public void addItem(String item)
	{
		//该物品已经购买过
		if (buyInfo.containsKey(item))
		{
			//购买数量加1
			buyInfo.put(item , 
				buyInfo.get(item) + 1);
		}
		else
		{
			//购买数量为1
			buyInfo.put(item , 1);
		}
	}
	public Map<String, Integer> showDetail()
	{
		return buyInfo;
	}
	//下面定义了4个简单的生命周期方法
	@Init
	public void init()
	{
		System.out.println("执行@Init修饰的方法!");
	}
	@PostConstruct
	public void postConstruct()
	{
		System.out.println("执行@PostConstruct修饰的方法!");
	}
	@PrePassivate
	public void prePassivate()
	{
		System.out.println("执行@PrePassivate修饰的方法!");
	}
	@PostActivate
	public void postActivate()
	{
		System.out.println("执行@PostActivate修饰的方法!");
	}
	@PreDestroy
	public void preDestroy()
	{
		System.out.println("执行@PreDestroy修饰的方法!");
	}
	//@Remove修饰的方法由客户机调用
	@Remove
	public void remove()
	{
		System.out.println("执行@Remove修饰的方法!");
	}
}
