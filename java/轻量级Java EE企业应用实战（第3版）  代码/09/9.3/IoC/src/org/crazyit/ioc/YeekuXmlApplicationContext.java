package org.crazyit.ioc;

import java.lang.reflect.*;
import java.util.*;
import java.io.*;
import org.dom4j.*;
import org.dom4j.io.*;

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
public class YeekuXmlApplicationContext
	implements ApplicationContext
{
	//保存容器中所有单例模式的Bean实例
	private Map<String , Object> objPool
		= Collections.synchronizedMap(new HashMap<String , Object>()); 
	//保存配置文件对应的Document对象
	private Document doc;
	//保存配置文件里的根元素
	private Element root;
	public YeekuXmlApplicationContext(String filePath)
		throws Exception
	{
		SAXReader reader = new SAXReader();
		doc = reader.read(new File(filePath));
		root = doc.getRootElement();
		initPool();
		initProp();
	}

	public Object getBean(String name)
		throws Exception
	{
		Object target = objPool.get(name);
		//对于singleton Bean，容器已经初始化了所有Bean实例
		if (target.getClass() != String.class)
		{
			return target;
		}
		else
		{
			String clazz = (String)target;
			//对于prototype并未注入属性值
			return Class.forName(clazz).newInstance();
		}
	}
	//初始化容器中所有singleton Bean
	private void initPool()
		throws Exception
	{
		//遍历配置文件里的每个<bean.../>元素
		for (Object obj : root.elements())
		{
			Element beanEle = (Element)obj;
			//取得<bean.../>元素的id属性
			String beanId = beanEle.attributeValue("id");
			//取得<bean.../>元素的class属性
			String beanClazz = beanEle.attributeValue("class");
			//取得<bean.../>元素的scope属性
			String beanScope = beanEle.attributeValue("scope");
			//如果<bean.../>元素的scope属性不存在，或为singleton
			if (beanScope == null ||
				beanScope.equals("singleton"))
			{
				//以默认构造器创建Bean实例，并将其放入objPool中
				objPool.put(beanId , Class.forName(beanClazz).newInstance());
			}
			else
			{
				//对于非singlton Bean，存放该Bean实现类的类名。
				objPool.put(beanId , beanClazz);
			}
		}
	}
	//初始化容器中singleton Bean的属性
	private void initProp()
		throws Exception
	{
		//遍历配置文件里的每个<bean.../>元素
		for (Object obj : root.elements())
		{
			Element beanEle = (Element)obj;
			//取得<bean.../>元素的id属性
			String beanId = beanEle.attributeValue("id");
			//取得<bean.../>元素的scope属性
			String beanScope = beanEle.attributeValue("scope");
			//如果<bean.../>元素的scope属性不存在，或为singleton
			if (beanScope == null ||
				beanScope.equals("singleton"))
			{
				//取出objPool的指定的Bean实例
				Object bean = objPool.get(beanId);
				//遍历<bean.../>元素的每个<property.../>子元素
				for (Object prop : beanEle.elements())
				{
					Element propEle = (Element)prop;
					//取得<property.../>元素的name属性	
					String propName = propEle.attributeValue("name");
					//取得<property.../>元素的value属性
					String propValue = propEle.attributeValue("value");
					//取得<property.../>元素的ref属性	
					String propRef = propEle.attributeValue("ref");
					//将属性名的首字母大写
					String propNameCamelize = propName.substring(0 , 1).toUpperCase()
						+ propName.substring(1 , propName.length());
					//如果<property.../>元素的value属性值存在
					if (propValue != null && propValue.length() > 0)
					{
						//获取设值注入所需的setter方法
						Method setter = bean.getClass().getMethod(
							"set" + propNameCamelize , String.class);
						//执行setter注入
						setter.invoke(bean , propValue);
					}
					if (propRef != null && propRef.length() > 0)
					{
						//取得需要被依赖注入的Bean实例
						Object target = objPool.get(propRef);
						//objPool池中不存在指定Bean实例
						if (target == null)
						{
							//此处还应处理Singleton Bean依赖prototype Bean的情形
						}
						//定义设值注入所需的setter方法
						Method setter = null;
						//遍历target对象所所实现的所有接口
						for (Class superInterface : target.getClass().getInterfaces())
						{
							try
							{
								//获取设值注入所需的setter方法
								setter = bean.getClass().getMethod(
									"set" + propNameCamelize , superInterface);
								//如果成功取得该接口对应的方法，直接跳出循环
								break;
							}
							catch (NoSuchMethodException ex)
							{
								//如果没有找到对应的setter方法，继续下次循环
								continue;
							}
						}
						//如果setter方法依然为null，
						//则直接取得target实现类对应的setter方法
						if (setter == null)
						{
							setter = bean.getClass().getMethod(
								"set" + propNameCamelize , target.getClass());
						}
						//执行setter注入
						setter.invoke(bean , target);
					}
				}
			}
		}
	}
}
