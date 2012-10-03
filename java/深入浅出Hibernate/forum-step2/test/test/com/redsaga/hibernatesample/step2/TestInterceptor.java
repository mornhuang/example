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
public class TestInterceptor extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestUserCRUD.class);
	}

	public void setUp() throws HibernateException
	{
		_BaseRootDAO.initialize();
	}
	
	public void testArticleTree() throws HibernateException
	{
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		
		//准备用户
		User shark = new User();
		shark.setName("Shark");
		shark.setPwd("guessme");
		fs.saveUser(shark);
		
		//准备版面
		Board board = new Board();
		board.setCreateBy(shark);
		board.setName("A");
		fs.addBoard(board);
		
		//创建主贴
		Article root = new Article();
		root.setTitle("test");
		root.setLastUpdateTime(new Date());
		root.setLastUpdateBy(shark);
		root.setCreateBy(shark);
		fs.addNewPost(board,root);
		assertEquals(1,root.getNodeLevel());
		assertEquals("0001",root.getTreeIndex());

		//创建子贴
		Article child = new Article();
		child.setTitle("test");
		child.setLastUpdateTime(new Date());
		child.setLastUpdateBy(shark);
		child.setCreateBy(shark);
		fs.replyPost(root,child);
		assertEquals(2,child.getNodeLevel());
		assertEquals("0001.0001",child.getTreeIndex());

		//创建子贴2
		Article child2 = new Article();
		child2.setTitle("test");
		child2.setLastUpdateTime(new Date());
		child2.setLastUpdateBy(shark);
		child2.setCreateBy(shark);
		fs.replyPost(child,child2);
		
		assertEquals(3,child2.getNodeLevel());
		assertEquals("0001.0001.0001",child2.getTreeIndex());

		fs.deleteBoard(board);
		fs.deleteUser(shark);
	}

}
