package org.crazyit.jsf;


import java.util.*;
import javax.faces.component.*;
import javax.faces.context.*;
import javax.faces.application.*;
import javax.faces.validator.*;
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
	private String email;
	//定义绑定到托管Bean属性的校验器
	private Validator validator;
	//无参数的构造器
	public UserBean()
	{
	}
	//初始化全部属性的构造器
	public UserBean(String name , String email)
	{
		this.name = name;
		this.email = email;
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

	//email属性的setter和getter方法
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getEmail()
	{
		return this.email;
	}

	//validaor属性的setter和getter方法
	public void setValidator(Validator validaor)
	{
		this.validator = validator;
	}
	public Validator getValidator()
	{
		//以匿名内部类的形式创建一个Validator对象
		return new Validator()
		{
			//定义一个输入校验的方法
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
				if (!toValidate.toString().matches(
					"\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"))
				{
					throw new ValidatorException(
						new FacesMessage("Email校验失败"));
				}
			}
		};
	}
	//编写处理导航的方法
	public String add()
	{
		return "success";
	}
}