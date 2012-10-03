package test.com.redsaga.hibernatesample.step2;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.LockMode;
import net.sf.hibernate.Session;

import com.redsaga.hibnatesample.step2.Article;
import com.redsaga.hibnatesample.step2.Board;
import com.redsaga.hibnatesample.step2.ForumService;
import com.redsaga.hibnatesample.step2.ForumServiceFactory;
import com.redsaga.hibnatesample.step2.User;
import com.redsaga.hibnatesample.step2.base._BaseRootDAO;
import com.redsaga.hibnatesample.step2.dao.RootDAO;
import com.redsaga.hibnatesample.step2.dao.UserDAO;
import com.redsaga.hibnatesample.step2.dao._RootDAO;

import junit.framework.TestCase;

/**
 * @author cao
 */
public class TestUserCRUD extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestUserCRUD.class);
	}

	public void setUp() throws HibernateException
	{
		_BaseRootDAO.initialize();
	}
	public void testSaveUser()
	{
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		//创建用户
		User shark = new User();
		shark.setName("Little Shark");
		shark.setPwd("guessme");
		
		fs.saveUser(shark);
		User shark2 = fs.getUser(shark.getId());
		assertEquals("Little Shark",shark2.getName());
		assertEquals("guessme",shark2.getPwd());
		
		//删除用户
		fs.deleteUser(shark);
	}
	
	
}
