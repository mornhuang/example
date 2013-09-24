package org.crazyit.jsf;

import javax.faces.event.ValueChangeEvent;
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
public class LocaleBean
{
	private String locale;

	//无参数的构造器
	public LocaleBean()
	{
	}
	//初始化全部属性的构造器
	public LocaleBean(String locale)
	{
		this.locale = locale;
	}

	//locale属性的setter和getter方法
	public void setLocale(String locale)
	{
		this.locale = locale;
	}
	public String getLocale()
	{
		return this.locale;
	}
	public void choose(ValueChangeEvent vce)
	{
		//将用户选择的值作为当前locale
		this.locale = (String)vce.getNewValue();
	}
}