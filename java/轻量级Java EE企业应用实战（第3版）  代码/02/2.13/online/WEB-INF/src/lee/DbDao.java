package lee;

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
public class DbDao 
{
	private Connection conn;
	private String driver;
	private String url;
	private String username;
	private String pass;
	public DbDao()
	{
	}
	public DbDao(String driver , String url 
		, String username , String pass)
	{
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.pass = pass; 
	}
	//下面是各个成员属性的setter和getter方法
	public void setDriver(String driver) {
		this.driver = driver; 
	}
	public void setUrl(String url) {
		this.url = url; 
	}
	public void setUsername(String username) {
		this.username = username; 
	}
	public void setPass(String pass) {
		this.pass = pass; 
	}
	public String getDriver() {
		return (this.driver); 
	}
	public String getUrl() {
		return (this.url); 
	}
	public String getUsername() {
		return (this.username); 
	}
	public String getPass() {
		return (this.pass); 
	}
	//获取数据库连接
	public Connection getConnection() throws Exception
	{
		if (conn == null)
		{
			Class.forName(this.driver);
			conn = DriverManager.getConnection(url,username,
				this. pass);
		}
		return conn;
	}
	//插入记录
	public boolean insert(String sql , Object... args)
		throws Exception
	{
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length ; i++ )
		{
			pstmt.setObject( i + 1 , args[i]);
		}
		if (pstmt.executeUpdate() != 1)
		{
			return false;
		}
		pstmt.close();
		return true;
	}
	//执行查询
	public ResultSet query(String sql , boolean updatable , Object... args )
		throws Exception
	{
		PreparedStatement pstmt = null;
		if (updatable)
		{
			//创建可更新的PreparedStatement
			pstmt = getConnection().prepareStatement(sql
				, ResultSet.TYPE_SCROLL_INSENSITIVE
				, ResultSet.CONCUR_UPDATABLE);
		}
		else
		{
			pstmt = getConnection().prepareStatement(sql);
		}
		for (int i = 0; i < args.length ; i++ )
		{
			pstmt.setObject( i + 1 , args[i]);
		}
		return pstmt.executeQuery();
	}
	//执行修改
	public void modify(String sql , Object... args)
		throws Exception
	{
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length ; i++ )
		{
			pstmt.setObject(i + 1 , args[i]);
		}
		pstmt.executeUpdate();
		pstmt.close();
	}
	//关闭数据库连接的方法
	public void closeConn()
		throws Exception
	{
		if (conn != null && !conn.isClosed())
		{
			conn.close();
		}
	}
}