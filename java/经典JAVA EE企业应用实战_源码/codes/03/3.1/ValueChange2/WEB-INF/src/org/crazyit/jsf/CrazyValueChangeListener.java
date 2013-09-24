package org.crazyit.jsf;

import javax.faces.component.html.*;
import javax.faces.event.*;
import javax.faces.context.FacesContext;
import javax.faces.component.*;
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
public class CrazyValueChangeListener
	implements ValueChangeListener
{
	public void processValueChange(ValueChangeEvent ve) 
	{
		FacesContext context =
			FacesContext.getCurrentInstance();
		//获取JSF页面中<f:view.../>元素
		UIViewRoot viewRoot = context.getViewRoot();
		//通过ID获取<f:view.../>内的<h:form.../>子元素。
		UIComponent comp = viewRoot.findComponent("registForm");
		//通过ID获取<h:form.../>内的<h:outputText.../>子元素。
		UIOutput tip = (UIOutput)comp.findComponent("tip");
		//使用一个字符串数组模拟数据库中已存在的用户名
		String[] existNames = 
		{
			"crazyit.org",
			"leegang.org",
			"crazyit"
		};
		//获取用户新输入的值
		String name = ve.getNewValue().toString();
		for (int i = 0 ; i < existNames.length ; i++)
		{
			//如果用户输入的用户名是数据库中已存在用户名
			if (existNames[i].equals(name))
			{
				tip.setValue(name + "用户名已经存在！");
				//让tip组件显示出来。
				tip.setRendered(true);
				return;
			}
		}
		tip.setValue("恭喜您，"+ name + "用户名可用！");
		//让tip组件显示出来。
		tip.setRendered(true);
	}
}
