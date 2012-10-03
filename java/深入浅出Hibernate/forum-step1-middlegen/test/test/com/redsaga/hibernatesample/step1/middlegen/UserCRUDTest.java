/*
 * Created on 2005-2-11
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package test.com.redsaga.hibernatesample.step1.middlegen;


import com.redsaga.hibernatesample.step1.middlegen.User;

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
public class UserCRUDTest extends TestCase {
    private Session session;

    protected void setUp() throws HibernateException{
        Configuration cfg = new Configuration().configure();
        session = cfg.buildSessionFactory().openSession();
    }

    protected void tearDown() throws Exception {
    	session.close();
    }
    
    public void testCRUD() throws HibernateException
    {
		User user = new User();
		user.setName("Someone");
		user.setPwd("guessme");
		session.save(user);
		
		User user2 = (User) session.load(User.class,user.getId());
		assertEquals("Someone",user2.getName());
		assertEquals("guessme",user2.getPwd());
		
		user2.setPwd("guessAgain");
		session.saveOrUpdate(user2);
		
		user = (User) session.load(user.getClass(),user.getId());
		assertEquals("guessAgain",user.getPwd());

		session.delete(user);
    	
    }

}
