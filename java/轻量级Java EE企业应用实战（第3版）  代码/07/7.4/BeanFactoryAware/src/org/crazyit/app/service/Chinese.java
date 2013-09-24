package org.crazyit.app.service;

import org.springframework.context.*;
import org.springframework.beans.BeansException;
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
//定义Bean类，实现ApplicationContextAware接口
//该类将拥有访问容器的能力
public class Chinese
	implements ApplicationContextAware
{
	//将BeanFactory容器以成员变量保存
	private ApplicationContext ctx;

	/**
	 * 实现ApplicationContextAware接口必须实现的方法
	 * @param ctx 创建bean实例的ApplicationContext
	 */
	public void setApplicationContext(ApplicationContext ctx)
		throws BeansException
	{
		this.ctx = ctx;
	}
	//获得ApplicationContext的测试方法
	public ApplicationContext getContext()
	{
		return ctx;
	}
}
