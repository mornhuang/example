package cn.hxex.blog.test;

import junit.framework.TestCase;
import cn.hxex.blog.dao.DaoFactory;
import cn.hxex.blog.dao.IMessageDAO;
import cn.hxex.blog.dao.IUserDAO;

/**
 * DaoFactory单元测试对象
 */
public class TestDaoFactory extends TestCase {

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}
	
	public void testDaoFactory()
	{
		IUserDAO userDAO = (IUserDAO)DaoFactory.getDao( "userDao" );
		assertNotNull( "User DAO is NULL!", userDAO );
		
		IMessageDAO messageDAO = (IMessageDAO)DaoFactory.getDao( "messageDao" );
		assertNotNull( "Message DAO is NULL!", messageDAO );
	}

}
