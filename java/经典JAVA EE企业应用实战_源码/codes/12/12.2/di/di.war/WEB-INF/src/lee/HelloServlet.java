package lee;

import javax.ejb.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

import org.crazyit.service.*;
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
public class HelloServlet extends HttpServlet
{
	//指定将内部标识名为hello的EJB注入到该Field。
	@EJB(beanName="hello")
	private Hello hello;
	public void service(HttpServletRequest request
		, HttpServletResponse response)
		throws IOException , ServletException
	{
		//获取请求参数
		String name = request.getParameter("name");
		if (name == null || name.equals(""))
		{
			name = "未登录用户";
		}
		//调用Session Bean的业务方法
		String resultStr = hello.hello(name);
		//创建页面输出流
		PrintStream ps = new PrintStream(response.getOutputStream());
		//将调用EJB的结果输出到客户端
		ps.println(resultStr);
	}
}