/*
 * Created on 2005-2-10
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package test.com.redsaga.hibernatesample.step3;

import java.util.Date;

import com.redsaga.hibernatesample.step3.Board;
import com.redsaga.hibernatesample.step3.User;
import com.redsaga.hibernatesample.step3.base._BaseRootDAO;
import com.redsaga.hibernatesample.step3.dao.ArticleDAO;
import com.redsaga.hibernatesample.step3.dao.BoardDAO;
import com.redsaga.hibernatesample.step3.dao.UserDAO;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.cfg.Configuration;
import junit.framework.TestCase;

/**
 * @author cao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserDAOTest extends TestCase {
    private Session session;

    protected void setUp() throws HibernateException{
        //Configuration cfg = new Configuration().configure();
        //session = cfg.buildSessionFactory().openSession();
    }

    protected void tearDown() throws Exception {
    	//session.close();
    }
	public void testUserCRUD() throws HibernateException
	{
		_BaseRootDAO.initialize();
		
		UserDAO userDao = UserDAO.getInstance();
		User user = new User();
		user.setName("Someone");
		user.setPwd("guessme");
		userDao.save(user);
		
		User user2 = userDao.load(user.getId());
		assertEquals("Someone",user2.getName());
		assertEquals("guessme",user2.getPwd());
		
		user2.setPwd("guessAgain");
		userDao.saveOrUpdate(user2);
		
		user = userDao.load(user.getId());
		assertEquals("guessAgain",user.getPwd());

		userDao.delete(user);

		userDao.closeSession();
		
	}
}
