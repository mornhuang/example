package lee;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
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
@WebListener
public class GetConnListener implements ServletContextListener
{
	//应该启动时，该方法被调用。
	public void contextInitialized(ServletContextEvent sce)
	{
		try
		{
			//取得该应用的ServletContext实例
			ServletContext application = sce.getServletContext();
			//从配置参数中获取驱动
			String driver = application.getInitParameter("driver");
			//从配置参数中获取数据库url
			String url = application.getInitParameter("url");
			//从配置参数中获取用户名
			String user = application.getInitParameter("user");
			//从配置参数中获取密码
			String pass = application.getInitParameter("pass");
			//注册驱动
			Class.forName(driver);
			//获取数据库连接
			Connection conn = DriverManager.getConnection(url 
				, user , pass);
			//将数据库连接设置成application范围内的属性
			application.setAttribute("conn" , conn);
		}
		catch (Exception ex)
		{
			System.out.println("Listener中获取数据库连接出现异常"
				+ ex.getMessage());
		}
	}
	//应该关闭时，该方法被调用。
	public void contextDestroyed(ServletContextEvent sce)
	{
		//取得该应用的ServletContext实例
		ServletContext application = sce.getServletContext();
		Connection conn = (Connection)application.getAttribute("conn");
		//关闭数据库连接
		if (conn != null)
		{
			try
			{
				conn.close();
			}
			catch (SQLException ex)
			{
				ex.printStackTrace();
			}
		}
	}
}