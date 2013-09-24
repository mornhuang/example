package org.crazyit.jsf.util;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.text.MessageFormat;

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
public class MessageFactory
{
	//构造器私有，该类仅作为静态工厂类使用
	private MessageFactory() 
	{
	}
	//该方法用于为带占位符的字符串填充参数
	public static String substituteParams(Locale locale
		, String msgtext, Object[] params) 
	{
		String localizedStr = null;
		if ((params == null) || (msgtext == null)) 
		{
			return msgtext;
		}
		StringBuffer b = new StringBuffer(100);
		MessageFormat mf = new MessageFormat(msgtext);
		if (locale != null) 
		{
			mf.setLocale(locale);
			b.append(mf.format(params));
			localizedStr = b.toString();
		}
		return localizedStr;
	}
	public static FacesMessage getMessage(Locale locale
		, String key , Object[] params)
	{
		FacesMessage result = null;
		String summary = null;
		String detail = null;
		String bundleName = null;
		ResourceBundle bundle = null;
		//判断用户是否提供了消息资源包
		if ((bundleName = getApplication()
			.getMessageBundle()) != null) 
		{
			if ((bundle = ResourceBundle.getBundle(
				bundleName , locale, getCurrentLoader(bundleName))) != null) 
			{
				try 
				{
					summary = bundle.getString(key);
				} 
				catch (MissingResourceException e) 
				{
				}
			}
		}
		if (summary == null) 
		{
			bundle = ResourceBundle.getBundle(
				FacesMessage.FACES_MESSAGES , locale 
				, getCurrentLoader(bundleName));

			if (bundle == null) 
			{
				throw new NullPointerException();
			}
			try 
			{
				summary = bundle.getString(key);
			}
			catch (MissingResourceException e)
			{
			}
		}
		if (summary == null)
		{
			return null;
		}
		if ((summary == null) || (bundle == null)) 
		{
			throw new NullPointerException();
		}
		//为国际化消息填充参数
		summary = substituteParams(locale, summary, params);
		try 
		{
			detail = substituteParams(locale
				, bundle.getString(key + "_detail")
				, params);
		}
		catch (MissingResourceException e) 
		{
		}
		return new FacesMessage(summary , detail);
	}
	//----------下面是本工厂类提供的静态工厂方法----------
		//获取国际化消息，以数组形式为占位符
	public static FacesMessage getMessage(
		FacesContext context , String key,
		Object... params)
	{
		if ((context == null) || (key == null)) 
		{
			throw new NullPointerException(
				"One or more parameters could be null");
		}
		Locale locale = null;
		if ((context != null) && (context.getViewRoot() != null))
		{
			locale = context.getViewRoot()
				.getLocale();
		}
		else 
		{
			locale = Locale.getDefault();
		}
		if (locale == null)
		{
			throw new NullPointerException();
		}
		FacesMessage message = getMessage(locale, key, params);
		if (message != null) 
		{
			return message;
		}
		locale = Locale.getDefault();
		return getMessage(locale, key, params);
	}
	//直接使用当前Locale来获取国际化消息(0个占位符)
	public static FacesMessage getMessage(String key) 
	{
		return getMessage(getCurrentLocale(), key, null);
	}
	//直接使用当前Locale来获取国际化消息(多个占位符)
	public static FacesMessage getMessage(String key
		, Object... params) 
	{
		return getMessage(getCurrentLocale(), key, params);
	}
	//----------下面是为本工厂类提供服务的protected方法----------
	//获取应用相关的Application对象
	protected static Application getApplication()
	{
		return (FacesContext.getCurrentInstance().getApplication());
	}
	//获取当前的ClassLoader对象
	protected static ClassLoader getCurrentLoader(Object fallbackClass) 
	{
		ClassLoader loader = Thread.currentThread()
			.getContextClassLoader();
		if (loader == null)
		{
			loader = fallbackClass.getClass()
				.getClassLoader();
		}
		return loader;
	}
	//获取当前的Locale对象
	protected static Locale getCurrentLocale() 
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Locale locale = null;
		//如果应用Context已经初始化且当前Context的ViewRoot不为null
		if ((context != null) && (context.getViewRoot() != null)) 
		{
			locale = context.getViewRoot().getLocale();
			if (locale == null)
			{
				locale = Locale.getDefault();
			}
		}
		else 
		{
			locale = Locale.getDefault();
		}
		return locale;
	}
}