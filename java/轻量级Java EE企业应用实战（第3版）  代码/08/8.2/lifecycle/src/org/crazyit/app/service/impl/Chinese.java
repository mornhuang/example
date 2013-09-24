package org.crazyit.app.service.impl;

import org.springframework.stereotype.*;

import javax.annotation.*;

import org.crazyit.app.service.*;
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
@Component
public class Chinese
	implements Person
{
	//执行Field注入
	@Resource(name="steelAxe")
	private Axe axe;
	//实现Person接口的useAxe方法
	public void useAxe()
	{
		//调用axe的chop()方法，
		//表明Person对象依赖于axe对象
		System.out.println(axe.chop());
	}
	@PostConstruct
	public void init()
	{
		System.out.println("正在执行初始化的init方法...");
	}
	@PreDestroy
	public void close()
	{
		System.out.println("正在执行销毁之前的close方法...");
	}
}