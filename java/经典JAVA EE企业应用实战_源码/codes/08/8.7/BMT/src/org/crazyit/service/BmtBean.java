package org.crazyit.service;

import java.util.*;
import javax.ejb.*;
import javax.annotation.*;
import javax.naming.*;

import java.sql.*;
import javax.sql.*;
import javax.transaction.*;

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
@Stateless(mappedName="Bmt")
@TransactionManagement(TransactionManagementType.BEAN)
public class BmtBean 
	implements Bmt
{
	private DataSource ds = null;
	@Resource
	private UserTransaction tx;
	public BmtBean()
		throws NamingException
	{
		Context ctx = new InitialContext();
		ds = (DataSource)ctx.lookup("javaee");
	}
	public void insert()
		throws Exception
	{
		try
		{
			//开始事务
			tx.begin();
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate("insert into user_table "
				+ "values(null, 'BMT插入的记录' , 'bbb')");
			System.out.println(result);
			//下面这条语句将引起失败
			stmt.executeUpdate("insert into user_table "
				+ "values(1, 'aaa' , 'bbb')");
			//提交事务
			tx.commit();
			stmt.close();
			conn.close();			
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			//回滚事务
			tx.rollback();
		}
	}
}
