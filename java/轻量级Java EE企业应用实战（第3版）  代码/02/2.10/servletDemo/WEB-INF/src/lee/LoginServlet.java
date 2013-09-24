package lee;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.PrintWriter;
import java.io.IOException;

import java.sql.*;

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
@WebServlet(name="login"
	, urlPatterns={"/login"})
public class LoginServlet extends HttpServlet
{
	//响应客户端请求的方法
	public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,java.io.IOException
	{
		String errMsg = "";
		//Servlet本身并不输出响应到客户端，因此必须将请求转发
		RequestDispatcher rd;
		//获取请求参数
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		try
		{
			//Servlet本身，并不执行任何的业务逻辑处理，它调用JavaBean处理用户请求
			DbDao dd = new DbDao("com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306/liuyan","root","32147");
			//查询结果集
			ResultSet rs = dd.query("select pass from user_table "
				+ "where name = ?", username);
			if (rs.next())
			{
				//用户名和密码匹配
				if (rs.getString("pass").equals(pass))
				{
					//获取session对象
					HttpSession session = request.getSession(true);
					//设置session属性，跟踪用户会话状态
					session.setAttribute("name" , username);
					//获取转发对象
					rd = request.getRequestDispatcher("/welcome.jsp");
					//转发请求
					rd.forward(request,response);
				}
				else
				{
					//用户名和密码不匹配时
					errMsg += "您的用户名密码不符合,请重新输入";
				}
			}
			else
			{
				//用户名不存在时
				errMsg += "您的用户名不存在,请先注册";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();			
		}
		//如果出错，转发到重新登录
		if (errMsg != null && !errMsg.equals(""))
		{
			rd = request.getRequestDispatcher("/login.jsp");		
			request.setAttribute("err" , errMsg);
			rd.forward(request,response);
		}
	}
}
