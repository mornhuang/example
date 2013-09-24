package lee;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
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
public class QueryTag extends SimpleTagSupport
{
	//标签的属性
	private String driver;
	private String url;
	private String user;
	private String pass;
	private String sql;

	//driver属性的setter和getter方法
	public void setDriver(String driver)
	{
		this.driver = driver;
	}
	public String getDriver()
	{
		return this.driver;
	}
	
	//url属性的setter和getter方法
	public void setUrl(String url)
	{
		this.url = url;
	}
	public String getUrl()
	{
		return this.url;
	}
	
	//user属性的setter和getter方法
	public void setUser(String user)
	{
		this.user = user;
	}
	public String getUser()
	{
		return this.user;
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
	
	//sql属性的setter和getter方法
	public void setSql(String sql)
	{
		this.sql = sql;
	}
	public String getSql()
	{
		return this.sql;
	}	
	
	//conn属性的setter和getter方法
	public void setConn(Connection conn)
	{
		this.conn = conn;
	}
	public Connection getConn()
	{
		return this.conn;
	}
	
	//stmt属性的setter和getter方法
	public void setStmt(Statement stmt)
	{
		this.stmt = stmt;
	}
	public Statement getStmt()
	{
		return this.stmt;
	}	
	
	//rs属性的setter和getter方法
	public void setRs(ResultSet rs)
	{
		this.rs = rs;
	}
	public ResultSet getRs()
	{
		return this.rs;
	}
	
	//rsmd属性的setter和getter方法
	public void setRsmd(ResultSetMetaData rsmd)
	{
		this.rsmd = rsmd;
	}
	public ResultSetMetaData getRsmd()
	{
		return this.rsmd;
	}
	//执行数据库访问的对象 
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	private ResultSetMetaData rsmd = null;
	public void doTag()throws JspException,
		IOException
	{
	   	try
		{
			//注册驱动
			Class.forName(driver);
			//获取数据库连接
			conn = DriverManager.getConnection(url,user,pass);
			//创建Statement对象
			stmt = conn.createStatement();
			//执行查询
			rs = stmt.executeQuery(sql);
			rsmd = rs.getMetaData();
			//获取列数目
			int columnCount = rsmd.getColumnCount();
			//获取页面输出流
			Writer out = getJspContext().getOut();
			//在页面输出表格
			out.write("<table border='1' bgColor='#9999cc' width='400'>");
			//遍历结果集
			while (rs.next())
			{
				out.write("<tr>");
				//逐列输出查询到的数据
				for (int i = 1 ; i <= columnCount ; i++ )
				{
					out.write("<td>");
					out.write(rs.getString(i));
					out.write("</td>");
				}
				out.write("</tr>");
			}
		}
		catch(ClassNotFoundException cnfe)
		{
			cnfe.printStackTrace();
			throw new JspException("自定义标签错误" + cnfe.getMessage());
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			throw new JspException("自定义标签错误" + ex.getMessage());
		}
		finally
		{
			//关闭结果集
			try
			{
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			}
			catch (SQLException sqle)
			{
				sqle.printStackTrace();
			}
		}
	}
}