package test.com.redsaga.hibernatesample.step4;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;

import com.redsaga.hibernatesample.step4.*;
import com.redsaga.hibernatesample.step4.base._BaseRootDAO;
import com.redsaga.hibernatesample.step4.dao.RootDAO;
import com.redsaga.hibernatesample.step4.dao.UserDAO;
import com.redsaga.hibernatesample.step4.dao._RootDAO;

import junit.framework.TestCase;

/**
 * @author cao
 */
public class TestArticleVoteCast extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestUserCRUD.class);
    }

    public void setUp() throws HibernateException {
        _BaseRootDAO.initialize();
    }

    public void testArticleVoteCast() throws HibernateException {
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

//创建普通Article
        Article root = new Article();
        root.setTitle("testNormalArticle");
        root.setLastUpdateTime(new Date());
        root.setLastUpdateBy(shark);
        root.setCreateBy(shark);
        
        fs.addNewPost(board, root);

//创建投票
        Vote vote = new Vote();
        vote.setTitle("testVote");
        vote.setLastUpdateTime(new Date());
        vote.setLastUpdateBy(shark);
        vote.setCreateBy(shark);
        vote.setCount(new Integer(1));
        fs.addNewPost(board, vote);

        List articleList = fs.getBoardPostList(board, 2, 1);

        String articleTitles = "";
        String votes = "";
        for (int i = 0; i < articleList.size(); i++) {
            Article a = (Article) articleList.get(i);
            articleTitles += "[" + a.getTitle() + "]";
            if (a instanceof Vote) {
                Vote v = (Vote) a;
                votes += "[" + v.getTitle() + "](" + v.getCount() + ")";
            }
        }

        assertEquals("[testVote]" +
                "[testNormalArticle]", articleTitles);

        assertEquals("[testVote](1)", votes);

        fs.deleteBoard(board);
        fs.deleteUser(shark);

    }

}
