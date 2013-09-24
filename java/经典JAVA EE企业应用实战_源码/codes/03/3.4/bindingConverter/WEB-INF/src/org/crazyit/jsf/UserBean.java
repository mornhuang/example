package org.crazyit.jsf;

import javax.faces.convert.*;
import javax.faces.context.*;
import javax.faces.component.*;

import java.util.*;
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
public class UserBean 
{
	private String name;
	private Son son;
	private Converter converter;

	//无参数的构造器
	public UserBean()
	{
	}
	//初始化全部属性的构造器
	public UserBean(String name , Son son)
	{
		this.name = name;
		this.son = son;
	}

	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	//son属性的setter和getter方法
	public void setSon(Son son)
	{
		this.son = son;
	}
	public Son getSon()
	{
		return this.son;
	}

	//converter属性的setter和getter方法
	public void setConverter(Converter converter)
	{
		this.converter = converter;
	}
	public Converter getConverter()
	{
		//采用匿名内部类实现一个转换器
		return new Converter()
		{
			//实现从字符串类型向目标类型转换的方法
			public Object getAsObject(FacesContext context, 
				UIComponent component, String value)
				throws ConverterException 
			{
				try
				{
					String[] values = value.split(":");
					Son son = new Son(values[0] 
						, Double.parseDouble(values[1]) 
						, Integer.parseInt(values[2]));
					return son;
				}
				//捕获所有自定义异常
				catch (Exception ex)
				{
					ex.printStackTrace();
					//抛出ConverterException异常。
					throw new ConverterException("无法转换！");
				}
			}
			//实现从目标类型向字符串类型转换的方法
			public String getAsString(FacesContext context, 
				UIComponent component, Object value)
			{
				Son son = (Son)value;
				return "Son[name=" + son.getName()
					+ ", height=" + son.getHeight()
					+ ", age=" + son.getAge() + "]";
			}
		};
	}

	//编写处理导航的方法
	public String add()
	{
		return "success";
	}
}