package org.crazyit.app.action;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.*;

import java.util.Map;
import java.io.InputStream;

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
public class AuthorityDownAction
	implements Action 
{
	private String inputPath;
	public void setInputPath(String value)
	{
		inputPath = value;
	}
	
	public InputStream getTargetFile() throws Exception 
	{
		//ServletContext提供getResourceAsStream()方法
		//返回指定文件对应的输入流 
		return ServletActionContext.getServletContext()
			.getResourceAsStream(inputPath);
	}
	
	public String execute() throws Exception
	{
		//取得ActionContext实例
		ActionContext ctx = ActionContext.getContext();
		//通过ActionContext访问用户的HttpSession
		Map session = ctx.getSession();
		String user = (String)session.get("user");
		//判断Session里的user是否通过检查
		if ( user !=  null && user.equals("crazyit.org"))
		{
			return SUCCESS;
		}
		ctx.put("tip"
			, "您还没有登录，或者登录的用户名不正确，请重新登录！");
		return LOGIN;
	}
}
