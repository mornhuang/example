package lee;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import java.sql.*;
import java.awt.event.*;

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
public class OnlineListener 
	implements ServletContextListener
{
	//超过该时间（10分钟）没有访问本站即认为用户已经离线
	public final int MAX_MILLIS = 10 * 60 * 1000;
	//应用启动时触发该方法
	public void contextInitialized(ServletContextEvent sce) 
	{
		//每5秒检查一次
		new javax.swing.Timer(1000 * 5 , new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					DbDao dd = new DbDao("com.mysql.jdbc.Driver"
						, "jdbc:mysql://localhost:3306/online_inf"
						, "root"
						, "32147");
					ResultSet rs = dd.query("select * from online_inf" , false);
					StringBuffer beRemove = new StringBuffer("(");
					while(rs.next())
					{
						//如果距离上次访问时间超过了指定时间
						if ((System.currentTimeMillis() - rs.getLong(5))
							> MAX_MILLIS)
						{
							//将需要被删除的session ID添加进来
							beRemove.append("'");
							beRemove.append(rs.getString(1));
							beRemove.append("' , ");
						}
					}
					//有需要删除的记录
					if (beRemove.length() > 3)
					{
						beRemove.setLength(beRemove.length() - 3);
						beRemove.append(")");
						//删除所有“超过指定时间未重新请求的记录”
						dd.modify("delete from online_inf where session_id in "
							+ beRemove.toString());
					}
					dd.closeConn();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}).start();
	}
	public void contextDestroyed(ServletContextEvent sce) 
	{
	}
}
