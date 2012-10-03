package test.com.redsaga.hibernatesample.step3;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.LockMode;
import net.sf.hibernate.Session;

import com.redsaga.hibernatesample.step3.Article;
import com.redsaga.hibernatesample.step3.Board;
import com.redsaga.hibernatesample.step3.ForumService;
import com.redsaga.hibernatesample.step3.ForumServiceFactory;
import com.redsaga.hibernatesample.step3.User;
import com.redsaga.hibernatesample.step3.base._BaseRootDAO;
import com.redsaga.hibernatesample.step3.dao.RootDAO;
import com.redsaga.hibernatesample.step3.dao.UserDAO;
import com.redsaga.hibernatesample.step3.dao._RootDAO;

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

        User shark3 = fs.getUserByName("Little Shark");
        assertEquals("Little Shark",shark3.getName());
        assertEquals("guessme",shark3.getPwd());

        User shark4 = fs.getUserByName("Little Shark Not Here");
        assertNull(shark4);
        

		//删除用户
		fs.deleteUser(shark);
	}
	
	
}
