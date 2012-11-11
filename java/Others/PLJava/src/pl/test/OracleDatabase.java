package pl.test;

import java.sql.*;
 
/**
 * Insert the type's description here.
 * Creation date: (29.04.00 23:54:11)
 * @author: Artyom Rudoy
 */
public class OracleDatabase extends pl.sql.RelationalDatabase {
	private java.lang.String user = null;
	private java.lang.String password = null;
	private java.lang.String url;
	private long currentLowValue = 0;
	private long currentHighVaue = -1;
/**
 * MysqlDatabase constructor.
 */
public OracleDatabase()
{
	super();
}
/**
 * destroy method comment.
 */
public void destroy() {}
/**
 * getNewConnection method comment.
 */
public Connection getNewConnection() throws pl.PlException
{
	try
	{
		return java.sql.DriverManager.getConnection(url, user, password);
	}
	catch(java.sql.SQLException e)
	{
		throw new pl.PlException(e);
	}
}
/**
 * init method comment.
 */
public void init(java.util.Properties properties) throws pl.PlException
{
	// Try to load driver class
	String driver = (String)properties.get("driver");
	if(driver == null)
		throw new pl.PlException("Driver not specified for" + getName() + " persistent mechanism");
	try
	{
		DriverManager.registerDriver((Driver)Class.forName(driver).newInstance());
	}
	catch(ClassNotFoundException e)
	{
		throw new pl.PlException("Driver " + driver + " not found");
	}
	catch(Throwable e)
	{
		throw new pl.PlException(e);
	}

	// Get user, password and connection string from properties
	user = (String)properties.get("user");
	if(user == null)
		throw new pl.PlException("Username not specified for " + getName() + " persistent mechanism");
	password = (String)properties.get("password");
	if(password == null)
		throw new pl.PlException("Password not specified for " + getName() + " persistent mechanism");
	url = (String)properties.get("url");
	if(url == null)
		throw new pl.PlException("URL not specified for " + getName() + " persistent mechanism");
}
}
