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
public class TestArticleVoteOption extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(TestUserCRUD.class);
    }

    public void setUp() throws HibernateException {
        _BaseRootDAO.initialize();
    }

    public void testArticleVoteOption() throws HibernateException {
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

//创建投票
        Vote root = new Vote();
        root.setTitle("testVote");
        root.setLastUpdateTime(new Date());
        root.setLastUpdateBy(shark);
        root.setCreateBy(shark);
        root.setCount(new Integer(1));


// 创建投票项
        VoteOption vo1 = new VoteOption();
        vo1.setOptionText("X1");
        vo1.setPoll(root);
        vo1.setAgreeNumber(0);
        root.addToOptionSet(vo1);

        VoteOption vo2 = new VoteOption();
        vo2.setOptionText("X2");
        vo2.setPoll(root);
        vo2.setAgreeNumber(0);
        root.addToOptionSet(vo2);

        fs.addNewPost(board, root);
        System.out.println("root.getId()="+root.getId());

// 开始投票
        Article vote1 = new Article();
        vote1.setLastUpdateTime(new Date());
        vote1.setLastUpdateBy(shark);
        vote1.setCreateBy(shark);
        vote1.setTitle("dummy");
        fs.replyVote(root,vote1,vo1.getId().intValue());

        Article vote2 = new Article();
        vote2.setLastUpdateTime(new Date());
        vote2.setLastUpdateBy(shark);
        vote2.setCreateBy(shark);
        vote2.setTitle("dummy");
        fs.replyVote(root,vote2,vo1.getId().intValue());


        Article vote3 = new Article();
        vote3.setLastUpdateTime(new Date());
        vote3.setLastUpdateBy(shark);
        vote3.setCreateBy(shark);
        vote3.setTitle("dummy");
        fs.replyVote(root,vote3,vo2.getId().intValue());

// 获取投票结果
        Article a =fs.getPost(root.getId());
        System.out.println("a.getId()="+a.getId());

        Vote result = (Vote) fs.getPost(root.getId());

        Iterator it = result.getOptionSet().iterator();
        while (it.hasNext()) {
            VoteOption vo = (VoteOption) it.next();
            if (vo.getId().intValue()==vo1.getId().intValue())
                assertEquals(2,vo.getAgreeNumber());
            if (vo.getId().intValue()==vo2.getId().intValue())
                assertEquals(1,vo.getAgreeNumber());
        }

        fs.deleteBoard(board);
        fs.deleteUser(shark);
    }

}
