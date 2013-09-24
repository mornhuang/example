package org.crazyit.app.dao.impl;

import javax.sql.DataSource;
import java.sql.Connection;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.*;

import org.crazyit.app.dao.*;
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
public class NewsDaoImpl
	implements NewsDao
{
	private DataSource ds;
	public void setDs(DataSource ds)
	{
		this.ds = ds;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void insert(String title 
		, String content)
	{
		JdbcTemplate jt = new JdbcTemplate(ds);
		jt.update("insert into news_inf"
			+ " values(null , ? , ?)" 
			, title , content);
		//两次插入的数据违反唯一键约束
		jt.update("insert into news_inf"
			+ " values(null , ? , ?)" 
			, title , content);
		//如果增加事务控制，我们发现第一条记录也插不进去。
		//如果没有事务控制，则第一条记录可以被插入
	}
}