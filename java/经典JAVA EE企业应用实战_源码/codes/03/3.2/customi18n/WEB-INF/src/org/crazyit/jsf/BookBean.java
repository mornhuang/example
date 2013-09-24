package org.crazyit.jsf;


import javax.faces.context.*;
import javax.servlet.http.*;
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
public class BookBean 
{
	private String name;
	private Double price;
	//无参数的构造器
	public BookBean()
	{
	}
	//初始化全部属性的构造器
	public BookBean(String name , Double price)
	{
		this.name = name;
		this.price = price;
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

	//price属性的setter和getter方法
	public void setPrice(Double price)
	{
		this.price = price;
	}
	public Double getPrice()
	{
		return this.price;
	}

	//编写处理导航的方法
	public String process()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		LocaleBean localeBean = (LocaleBean)fc.getExternalContext()
			.getSessionMap().get("localeBean");
		String[] langAndCountry = localeBean.getLocale().split("_");
		Locale current = new Locale(langAndCountry[0] , langAndCountry[1]);
		//使用ResourceBundle来加载国际化消息资源
		ResourceBundle rb = ResourceBundle.getBundle(
			"global" , current); 
		//下面几行代码用于测试ExternalContext的方法
		ExternalContext ec = fc.getExternalContext();
		if (name.equals("疯狂Java讲义")
			&& price == 99)
		{
			//将数据存入session范围
			ec.getSessionMap().put("website"
				, "crazyit.org");
			return "success";
		}
		else
		{
			ec.getSessionMap().put("tip" 
				, rb.getString("wrong"));
			return "failure";
		}
	}
}