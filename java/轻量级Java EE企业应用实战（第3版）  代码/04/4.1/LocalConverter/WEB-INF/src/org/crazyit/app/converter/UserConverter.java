package org.crazyit.app.converter;

import java.util.Map;
import ognl.DefaultTypeConverter;

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
public class UserConverter extends DefaultTypeConverter 
{
	//类型转换器必须重写convertValue方法，该方法需要完成双向转换
	public Object convertValue(Map context
		, Object value, Class toType)
	{
		//当需要将字符串向User类型转换时
		if (toType == User.class )
		{
			//系统的请求参数是一个字符串数组
			String[] params = (String[])value;
			//创建一个User实例
			User user = new User();
			//只处理请求参数数组第一个数组元素，
			//并将该字符串以英文逗号分割成两个字符串
			String[] userValues = params[0].split(",");
			//为User实例赋值
			user.setName(userValues[0]);
			user.setPass(userValues[1]);
			//返回转换来的User实例
			return user;
		}
		else if (toType == String.class )
		{
			//将需要转换的值强制类型转换为User实例
			User user = (User) value;
			return "<" + user.getName() + ","
				+ user.getPass() + ">";
		} 
		return null ;
	} 
}