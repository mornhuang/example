package org.crazyit.app.service;

import org.springframework.beans.factory.BeanNameAware;

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
public class Chinese 
	implements BeanNameAware
{
	//保存部署该Bean时指定的id属性
	private String beanName;
	public void setBeanName(String name) 
	{
		this.beanName = name;
	}
	public void info()
	{
		System.out.println("Chinese实现类"
			+ ", 部署该Bean时指定的id为" + beanName);
	}
}
