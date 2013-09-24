package org.crazyit.app.factory;

import org.springframework.beans.factory.FactoryBean;

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
//PersonFactory实现FactoryBean接口，将作为工厂Bean使用
public class PersonFactory
	implements FactoryBean<Person>
{
	Person p = null;
	//返回工厂Bean所生产的产品
	public Person getObject()
	{
		if (p == null)
		{
			p = new Chinese();
		}
		return p;
	}
	//获取工厂Bean所生产的产品的类型
	public Class<? extends Person> getObjectType()  
	{
		return Chinese.class;
	}
	//返回该工厂Bean所生成的产品是否为单例
	public boolean isSingleton() 
	{
		return true;
	}
}