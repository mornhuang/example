package lee;

import javax.ejb.*;

import org.crazyit.service.*;
import javax.faces.context.*;
import javax.servlet.http.*;
import javax.naming.*;
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
//通过@EJB Annotation配置EJB引用。
@EJB(name="userService"
	, beanInterface=org.crazyit.service.UserService.class)
public class LoginBean
{
	//下面的三个属性都会直接与JSF标签绑定
	private String name;
	private String pass;
	private String info;
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

	//info属性的setter和getter方法
	public void setInfo(String info)
	{
		this.info = info;
	}
	public String getInfo()
	{
		return this.info;
	}
	//该方法被绑定到UI组件（按钮）的action属性
	public String valid()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession)ec.getSession(true);
		UserService us = (UserService)session.getAttribute("us");
		if (us == null)
		{
			try
			{
				Context ctx = new InitialContext();
				//通过EJB引用查找EJB
				us = (UserService)ctx.lookup("java:comp/env/userService");
				session.setAttribute("us" , us);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
		//调用Session Bean的方法来处理用户请求
		if (us.loginPro(name , pass))
		{
			//调用Session Bean的方法获取用户的登录信息
			setInfo(us.getLoginInfo());
			return "success";
		}
		setInfo("您的用户名和密码不符合");
		return "failure";
	}
}