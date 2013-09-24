package lee;

import java.sql.*;

import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.*;

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
@WebServlet(name="testServlet"
	, urlPatterns={"/testServlet"}
	, initParams={
		@WebInitParam(name="driver", value="com.mysql.jdbc.Driver"),
		@WebInitParam(name="url", value="jdbc:mysql://localhost:3306/javaee"),
		@WebInitParam(name="user", value="root"),
		@WebInitParam(name="pass", value="32147")})
public class TestServlet extends HttpServlet
{
	//重写init方法，
	public void init(ServletConfig config)
		throws ServletException
	{
		//重写该方法，应该首先调用父类的init方法
		super.init(config);
	}
	//响应客户端请求的方法
	public void service(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,java.io.IOException
	{
		try
		{
			//获取ServletConfig对象
			ServletConfig config = getServletConfig();
			//通过ServletConfig对象获取配置参数：dirver
			String driver = config.getInitParameter("driver");
			//通过ServletConfig对象获取配置参数：url
			String url = config.getInitParameter("url");
			//通过ServletConfig对象获取配置参数：user
			String user = config.getInitParameter("user");
			//通过ServletConfig对象获取配置参数：pass
			String pass = config.getInitParameter("pass");
			//注册驱动
			Class.forName(driver);
			//获取数据库驱动
			Connection conn = DriverManager.getConnection(url,user,pass);
			//创建Statement对象
			Statement stmt = conn.createStatement();
			//执行查询，获取ResuletSet对象
			ResultSet rs = stmt.executeQuery("select * from news_inf");
			response.setContentType("text/html;charSet=gbk");
			//获取页面输出流
			PrintStream out = new PrintStream(response.getOutputStream());
			//输出HTML标签
			out.println("<html>");
			out.println("<head>");
			out.println("<title>访问Servlet初始化参数测试</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<table bgcolor=\"#9999dd\" border=\"1\"" +
				"width=\"480\">");
			//遍历结果集
			while(rs.next())
			{
				//输出结果集内容
				out.println("<tr>");
				out.println("<td>" + rs.getString(1) + "</td>");
				out.println("<td>" + rs.getString(2) + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		} 
	}
}
