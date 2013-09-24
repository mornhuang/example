package org.crazyit.app.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

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
public class MyBeanFactoryPostProcessor 
	implements BeanFactoryPostProcessor
{
	/**
	 * 重写该方法，对Spring进行后处理。
	 * @param beanFactory Spring容器本身
	 */
	public void postProcessBeanFactory(
		ConfigurableListableBeanFactory beanFactory)
		throws BeansException
	{
		System.out.println("程序对Spring所做的BeanFactory的初始化没有改变...");
		System.out.println("Spring容器是：" + beanFactory);
	}
}
