package cn.hxex.blog.test;

import java.sql.Timestamp;
import java.util.List;

import junit.framework.TestCase;
import cn.hxex.blog.dao.DaoFactory;
import cn.hxex.blog.dao.IMessageDAO;
import cn.hxex.blog.dao.IUserDAO;
import cn.hxex.blog.exception.BlogDAOException;
import cn.hxex.blog.hibernate.HibernateUtil;
import cn.hxex.blog.model.Message;
import cn.hxex.blog.model.User;

/**
 * MessageDAO测试方法
 */
public class TestMessageDAO extends TestCase {
	
	IMessageDAO messageDAO;
	IUserDAO userDAO;
	public static String messageId;
	public static String replyMessageId;
	
	protected void setUp() throws Exception 
	{
		super.setUp();
		messageDAO = (IMessageDAO)DaoFactory.getDao( "messageDao" );
		assertNotNull( "Message DAO is NULL!", messageDAO );
		userDAO = (IUserDAO)DaoFactory.getDao( "userDao" );
		assertNotNull( "User DAO is NULL!", userDAO );
		HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	}

	// 测试saveMessage()方法
	public void testSaveMessage()
	{
		User user = userDAO.getUser( "test" );
		
		Message message = new Message();
		message.setTitle( "title" );
		message.setContent( "content" );
		message.setUser( user );
		message.setPubdate( new Timestamp( System.currentTimeMillis() ) );
		messageDAO.saveMessage( message );
		
		assertNotNull( "add Message Failed!", message.getId() );
		messageId = message.getId();
	}
	
	// 测试getMessages()方法
	public void testGetMessages()
	{
		List list = messageDAO.getMessages();
		assertTrue( "Get Messages Success!", list.size()>0 );
	}
	
	// 测试getMessage()方法
	public void testGetMessage()
	{
		Message m = messageDAO.getMessage( messageId );
		assertNotNull( "Get Message Failed!", m );
	}
	
	// 测试updateMessage()方法
	public void testUpdateMessage()
	{
		Message m = messageDAO.getMessage( messageId );
		assertNotNull( "Couldn't find the message!", m );

		m.setTitle( "GGG" );
		m.setContent( "NNN" );
		messageDAO.saveMessage( m );
		
		Message m2 = messageDAO.getMessage( messageId );
		assertEquals( "Update Success!", m2.getTitle(), "GGG" );
		assertEquals( "Update Success!", m2.getContent(), "NNN" );
	}
	
	// 测试deleteMessage()方法
	public void testDeleteMessage()
	{
		Message m = messageDAO.getMessage( messageId );
		assertNotNull( "Couldn't find the message!", m );

		boolean result = false;
		try
		{
			messageDAO.deleteMessage( m.getId(), "sdf" );
		}
		catch( BlogDAOException ex )
		{
			result = true;
		}
		assertTrue( "User previlege is not Right!", result );
		
		Message m2 = messageDAO.getMessage( messageId );
		assertNotNull( "Deleted the Message!", m2 );
		
		result = false;
		messageDAO.deleteMessage( m.getId(), m.getUser().getId() );
		try
		{
			messageDAO.getMessage( messageId );
		}
		catch( Exception ex )
		{
			result = true;
		}
		assertTrue( "Delete Failed!", result );
	}

}
