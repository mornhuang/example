package org.crazyit.app.converter;

import java.util.Map;
import org.apache.struts2.util.StrutsTypeConverter;
import java.util.Set;
import java.util.HashSet;

import org.crazyit.app.domain.*;

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
public class UserConverter extends StrutsTypeConverter 
{
	public Object convertFromString(Map context
		, String[] values, Class toClass)
	{
		Set result = new HashSet();
		for (int i = 0; i < values.length ; i++ )
		{
			//创建一个User实例
			User user = new User();
			//只处理请求参数数组第一个数组元素，
			//并将该字符串以英文逗号分割成两个字符串
			String[] userValues = values[i].split(",");
			//为User实例的属性赋值
			user.setName(userValues[0]);
			user.setPass(userValues[1]);
			//将User实例添加到Set集合中
			result.add(user);
		}
		return result;
	}
	
	public String convertToString(Map context, Object o)
	{
		//如果待转换对象的类型是Set
		if (o.getClass() == Set.class)
		{
			Set users = (Set)o;
			String result = "[";
			for (Object obj : users )
			{
				User user = (User)obj;
				result += "<" + user.getName()
					+ "," + user.getPass() + ">";
			}
			return result + "]";
		}
		else
		{
			return "";
		}
	}
}