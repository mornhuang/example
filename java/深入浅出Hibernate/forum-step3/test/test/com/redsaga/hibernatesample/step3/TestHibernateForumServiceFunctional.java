/*
 * Created on 2005-2-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package test.com.redsaga.hibernatesample.step3;

import java.util.Date;
import java.util.List;

import com.redsaga.hibernatesample.step3.Article;
import com.redsaga.hibernatesample.step3.Board;
import com.redsaga.hibernatesample.step3.ForumService;
import com.redsaga.hibernatesample.step3.ForumServiceFactory;
import com.redsaga.hibernatesample.step3.User;

import junit.framework.TestCase;

/**
 * @author cao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class TestHibernateForumServiceFunctional extends TestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestHibernateForumServiceFunctional.class);
	}

	/*
	 * @see TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		
		//创建用户
		User john = new User();
		john.setName("John Coner");
		john.setPwd("matrix");
		
		User shark = new User();
		shark.setName("Little Shark");
		shark.setPwd("guessme");
		
		fs.saveUser(john);
		fs.saveUser(shark);
		
		//创建版面
		Board waterPool = new Board();
		waterPool.setName("WaterPool");
		waterPool.setRemark("This is where the water goes");
		waterPool.setCreateBy(john);
		waterPool.setCreateTime(new Date());
		fs.addBoard(waterPool);
		
		Board greenWaterPool = new Board();
		greenWaterPool.setName("Green WaterPool");
		greenWaterPool.setRemark("green tea");
		greenWaterPool.setCreateBy(john);
		greenWaterPool.setCreateTime(new Date());
		greenWaterPool.setParent(waterPool);
		fs.addBoard(greenWaterPool);
		
		//创建帖子
		Article hello = new Article();
		hello.setTitle("Hello World!");
		hello.setBody("Just hello world.");
		hello.setCreateBy(john);
		hello.setCreateTime(new Date());
		
		Article niceToMeetYou = new Article();
		niceToMeetYou.setTitle("Nice to meet you!");
		niceToMeetYou.setBody("//hand ");
		niceToMeetYou.setCreateBy(shark);
		niceToMeetYou.setCreateTime(new Date());

		fs.addNewPost(greenWaterPool,hello);
		fs.addNewPost(waterPool,hello);
		
		fs.replyPost(hello,niceToMeetYou);
		
		//模拟点击
		fs.readPost(hello);
		fs.readPost(hello);
		
		fs.readPost(niceToMeetYou);
		
	}

	/*
	 * @see TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		List boards = fs.getBoardList();
		for (int i=0;i<boards.size();i++)
			fs.deleteBoard((Board)boards.get(i));
		List users = fs.getUserList();
		for (int i=0;i<users.size();i++)
			fs.deleteUser((User)users.get(i));
	}
	
	protected void testBasic()
	{
		
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		Board waterPool = fs.getBoard(new Integer(1));
		Board greenWaterPool = fs.getBoard(new Integer(2));
		Article hello = fs.getPost(new Integer(2));
		Article niceToMeetYou = fs.getPost(new Integer(3));
		
		//开始测试
		assertEquals(2,hello.getHits());
		assertEquals(1,niceToMeetYou.getHits());
		
		assertEquals(1,waterPool.getArticles().size());
		assertEquals(1,greenWaterPool.getArticles().size());
		
		Article helloInWaterpool = (Article) waterPool.getArticles().iterator().next();
		assertEquals(1,helloInWaterpool.getChildPosts().size());
		
	}
	
}
