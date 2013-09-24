package org.crazyit.validator;

import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.application.*;
import javax.faces.validator.*;

import org.crazyit.jsf.util.*;
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
public class RegexValidator
	implements Validator, StateHolder 
{
	public final static String CRAZYIT_REGEX_INVALID
		= "crazyit_regex_invalid";
	private boolean transientValue = false;
	//定义用于指定正则表达式的属性
	private String pattern;
	//pattern属性的setter和getter方法
	public void setPattern(String pattern)
	{
		this.pattern = pattern;
	}
	public String getPattern()
	{
		return this.pattern;
	}
	public void validate(FacesContext context,
		UIComponent component, Object toValidate) 
	{
		if (context == null || component == null)
		{
			throw new NullPointerException();
		}
		//设置只对输入组件起作用
		if (!(component instanceof UIInput))
		{
			return;
		}
		//要求被验证的值必须存在
		if (null == toValidate) 
		{
			return;
		}
		//如果被校验的值不匹配正则表达式
		if (!toValidate.toString().matches(pattern))
		{
			//使用国际化消息资源包中key为crazyit_regex的消息作为提示
			throw new ValidatorException(
				MessageFactory.getMessage(CRAZYIT_REGEX_INVALID
				, component.getId()
				, pattern));
		}
	}
	//实现该方法用于保存该校验器的状态
	public Object saveState(FacesContext context)
	{
		Object[] values = new Object[1];
		values[0] = pattern;
		return values;
	}
	//实现该方法用于恢复该校验器的状态
	public void restoreState(FacesContext context
		,Object state) 
	{
		Object[] values = (Object[])state;
		pattern = (String) values[0];
	}
	public boolean isTransient() 
	{
		return (this.transientValue);
	}
	public void setTransient(boolean transientValue) 
	{
		this.transientValue = transientValue;
	}
}