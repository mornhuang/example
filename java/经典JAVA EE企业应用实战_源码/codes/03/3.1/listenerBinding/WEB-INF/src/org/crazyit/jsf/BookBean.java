package org.crazyit.jsf;

import javax.faces.event.*;
import javax.faces.context.FacesContext;
import javax.faces.component.*;
import javax.faces.component.html.*;
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
	//绑定UI组件本身的属性

	private HtmlInputText price;
	private ActionListener listener;



	//无参数的构造器
	public BookBean()
	{
	}
	//初始化全部属性的构造器
	public BookBean(String name 
		, HtmlInputText price , ActionListener listener)
	{
		this.name = name;
		this.price = price;
		this.listener = listener;
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
	public void setPrice(HtmlInputText price)
	{
		this.price = price;
	}
	public HtmlInputText getPrice()
	{
		return this.price;
	}

	//listener属性的setter和getter方法
	public void setListener(ActionListener listener)
	{
		this.listener = listener;
	}
	public ActionListener getListener()
	{
		//采用匿名内部类语法实现一个ActionListener实例
		return new ActionListener()
		{
			//编写处理Action事件的方法
			public void processAction(ActionEvent event) 
			{
				//获取当前的FacesContext对象
				FacesContext context =
					FacesContext.getCurrentInstance();
				//获取JSF页面中<f:view.../>元素
				UIViewRoot viewRoot = context.getViewRoot();
				//通过ID获取<f:view.../>内的<h:form.../>子元素。
				UIComponent comp = viewRoot.findComponent("addForm");
				//通过ID获取<h:form.../>内的第一个<h:inputText.../>子元素。
				UIInput input = (UIInput)comp.findComponent("name");
				//通过ID获取<h:form.../>内的第二个<h:inputText.../>子元素。
				HtmlInputText price = (HtmlInputText)comp
					.findComponent("price");
				if (input.getValue().equals("疯狂Java讲义"))
				{
					price.setSize(60);
					price.setValue("99.0元");
					price.setStyle("background-color:#9999ff;"
						+ "font-weight:bold");
				}
			}
		};
	}
}