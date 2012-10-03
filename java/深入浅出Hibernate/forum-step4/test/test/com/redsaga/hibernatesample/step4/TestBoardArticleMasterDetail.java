package test.com.redsaga.hibernatesample.step4;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;

import com.redsaga.hibernatesample.step4.Article;
import com.redsaga.hibernatesample.step4.Board;
import com.redsaga.hibernatesample.step4.ForumService;
import com.redsaga.hibernatesample.step4.ForumServiceFactory;
import com.redsaga.hibernatesample.step4.User;
import com.redsaga.hibernatesample.step4.base._BaseRootDAO;
import com.redsaga.hibernatesample.step4.dao.RootDAO;
import com.redsaga.hibernatesample.step4.dao.UserDAO;
import com.redsaga.hibernatesample.step4.dao._RootDAO;

import junit.framework.TestCase;

/**
 * @author cao
 */
public class TestBoardArticleMasterDetail extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestUserCRUD.class);
	}

	public void setUp() throws HibernateException
	{
		_BaseRootDAO.initialize();
	}

	public void testBoardArticleMasterDetail() throws HibernateException
	{
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		
		//创建用户
		User shark = new User();
		shark.setName("Shark");
		shark.setPwd("guessme");
		fs.saveUser(shark);
		
		//创建版面
		Board a = new Board();
		a.setCreateBy(shark);
		a.setName("A");
		fs.addBoard(a);
		
		//创建贴子
		Article article1 = new Article();
		article1.setTitle("article1");
		article1.setLastUpdateTime(new Date());
		article1.setLastUpdateBy(shark);
		article1.setCreateBy(shark);

		Article article2 = new Article();
		article2.setTitle("article2");
		article2.setCreateBy(shark);
		article2.setLastUpdateBy(shark);
		article2.setLastUpdateTime(new Date());
		
		//把帖子加入到版面
		fs.addNewPost(a,article1);
		fs.addNewPost(a,article2);
		
		Board a2 = fs.getBoard(a.getId());
		a2.getArticles();
		Session session = RootDAO.createSession();
		assertEquals("[article1][article2]",printArticleInBoard(a2));
		RootDAO.getInstance().closeSession();
		
		//删除版面
		fs.deleteBoard(a2);

		//确认相关的帖子也被级联删除了
		session = RootDAO.createSession();
		List shouldBeEmpty = session.createQuery("from Article where title='article1'").list();
		assertEquals(0,shouldBeEmpty.size());
		RootDAO.getInstance().closeSession();
		
		fs.deleteUser(shark);
	}

	protected String printArticleInBoard(Board board) throws HibernateException
	{
		Session session = RootDAO.createSession();
		session.lock(board,LockMode.READ);
		
		Iterator it = board.getArticles().iterator();
		String articles = "";
		while (it.hasNext())
		{
			Article article = (Article) it.next();
			session.lock(article,LockMode.READ);
			articles += "["+article.getTitle()+"]";
		}
		return articles;
	}
	
}
