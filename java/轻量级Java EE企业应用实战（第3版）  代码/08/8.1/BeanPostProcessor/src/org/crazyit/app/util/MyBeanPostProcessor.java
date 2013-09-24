package org.crazyit.app.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import org.crazyit.app.service.*;
import org.crazyit.app.service.impl.*;
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
public class MyBeanPostProcessor 
	implements BeanPostProcessor
{
	/**
	 * 对容器中的Bean实例进行后处理
	 * @param bean 需要进行后处理的原Bean实例
	 * @param beanName 需要进行后处理的Bean实例的名字
	 * @return 返回后处理完成后的Bean
	 */
	public Object postProcessBeforeInitialization
		(Object bean , String beanName)
	{
		System.out.println("Bean后处理器在初始化之前对" 
			+ beanName + "进行增强处理...");
		//返回的处理后的Bean实例，
		//该Bean实例就是容器中到时实际使用的Bean实例
		//该Bean实例甚至可与原Bean截然不同
		return bean;
	}
	public Object postProcessAfterInitialization
		(Object bean , String beanName)
	{
		System.out.println("Bean后处理器在初始化之后对" 
			+ beanName + "进行增强处理...");
		//如果该Bean是Chinese类的实例
		if (bean instanceof Chinese)
		{
			//修改其name属性值
			Chinese c = (Chinese)bean;
			c.setName("Struts 2权威指南");
		}
		return bean;
	}
}
