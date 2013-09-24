package org.crazyit.service;

import java.util.*;
import javax.ejb.*;
import javax.annotation.*;
import javax.naming.*;

import java.sql.*;
import javax.sql.*;

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
@Stateless(mappedName="Cmt2")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class Cmt2Bean
	implements Cmt2
{
	@Resource(mappedName="javaee")
	private DataSource ds = null;
	@Resource
	private SessionContext sessCtx;
	public void insert()
	{
		try
		{
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate("insert into user_table "
				+ "values(null, '新用户' , 'bbb')");
			System.out.println(result);
			stmt.close();
			conn.close();			
		}
		catch (SQLException ex)
		{
			ex.printStackTrace();
			sessCtx.setRollbackOnly();
		}
	}
}
