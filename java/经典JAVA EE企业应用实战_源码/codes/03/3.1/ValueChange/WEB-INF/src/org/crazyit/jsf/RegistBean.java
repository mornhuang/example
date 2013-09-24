package org.crazyit.jsf;

import javax.faces.component.html.*;
import javax.faces.event.*;
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
public class RegistBean 
{
	private String name;
	private String pass;
	//绑定UI组件本身的属性

	private HtmlOutputText tip;




	//无参数的构造器
	public RegistBean()
	{
	}
	//初始化全部属性的构造器
	public RegistBean(String name 
		, String pass , HtmlOutputText tip)
	{
		this.name = name;
		this.pass = pass;
		this.tip = tip;
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

	//pass属性的setter和getter方法
	public void setPass(String pass)
	{
		this.pass = pass;
	}
	public String getPass()
	{
		return this.pass;
	}

	//tip属性的setter和getter方法
	public void setTip(HtmlOutputText tip)
	{
		this.tip = tip;
	}
	public HtmlOutputText getTip()
	{
		return this.tip;
	}
	
	public void judgeName(ValueChangeEvent ve)
	{
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