package com.redsaga.hibnatesample.step2;

import org.apache.log4j.Logger;

import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.LockMode;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;
import net.sf.hibernate.sql.InFragment;

import com.redsaga.hibnatesample.step2.base._BaseRootDAO;
import com.redsaga.hibnatesample.step2.dao.ArticleDAO;
import com.redsaga.hibnatesample.step2.dao.BoardDAO;
import com.redsaga.hibnatesample.step2.dao.RootDAO;
import com.redsaga.hibnatesample.step2.dao.UserDAO;
import com.redsaga.hibnatesample.step2.util.HibernateCallback;
import com.redsaga.hibnatesample.step2.util.HibernateTemplate;

public class HibernateForumService implements ForumService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(HibernateForumService.class);

	public void saveUser(final User u) {
		new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				UserDAO userDAO = UserDAO.getInstance();
				return userDAO.save(u);
			}
		});
	}

	public void deleteUser(final User u) {
		new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				UserDAO userDAO = UserDAO.getInstance();
				userDAO.delete(u);
				return null;
			}
		});
	}

	public void addBoard(final Board b) {
		new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				BoardDAO boardDAO = BoardDAO.getInstance();
				return boardDAO.save(b);
			}
		});
	}

	public void addChildBoard(final Board parent,final Board child) {
		new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				BoardDAO boardDAO = BoardDAO.getInstance();
				child.setParent(parent);
				parent.addToChildBoards(child);
				return boardDAO.save(child);
			}
		});
	}

	public void deleteBoard(final Board b) {
		new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				BoardDAO boardDAO = BoardDAO.getInstance();
				boardDAO.delete(b);
				return null;
			}
		});
	}

	public void addNewPost(final Board b, final Article a) {
		 new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				a.setBoard(b);
				b.addToArticles(a);
				ArticleDAO articleDAO = ArticleDAO.getInstance();
				articleDAO.save(a);
				return null;
			}
		});
	}

	public void replyPost(final Article a, final Article reply) {
		 logger.info("replyPost:"+a+", "+reply);
		 new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				reply.setParent(a);
				reply.setBoard(a.getBoard());
				a.addToChildPosts(reply);
//				a.getChildPosts().add(reply);
//				a.a`
				ArticleDAO articleDAO = ArticleDAO.getInstance();
				articleDAO.save(reply);
				return null;
			}
		});
	}

	public List getUserList() {
		throw new UnsupportedOperationException();
	}

	public List getBoardList() {
		return (List) new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				BoardDAO boardDAO = BoardDAO.getInstance();
				return boardDAO.find("from Board b where b.parent is null");
			}
		});
	}

	public List getBoardPostList(final Board b, final int pageSize, final int pageNo) {
		return (List) new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				Session session = RootDAO.createSession();
				List result = session.createQuery(
						"from Article a where a.board=:board order by a.rootId asc ,a.treeIndex asc ")
						.setEntity("board",b)
						.setFirstResult(pageSize * (pageNo-1))
						.setMaxResults(pageSize)
						.list();
				
				return result;
			}
		});
	}

	public User getUser(final Integer uid) {
		return (User) new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				UserDAO userDAO = UserDAO.getInstance();
				return userDAO.load(uid);
			}
		});
	}

	public Board getBoard(final Integer bid) {
		return (Board) new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				BoardDAO boardDAO = BoardDAO.getInstance();
				return boardDAO.load(bid);
			}
		});
	}

	public void readPost(Article a) {
		throw new UnsupportedOperationException();
	}

	public Article getPost(final Integer aid) {
		return (Article) new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				ArticleDAO articleDAO = ArticleDAO.getInstance();
				return articleDAO.load(aid);
			}
		});
	}

	public void deletePost(final Article a) {
		new HibernateTemplate().run(new HibernateCallback() {
			public Object execute() throws HibernateException {
				ArticleDAO articleDAO = ArticleDAO.getInstance();
				articleDAO.delete(a);
				return null;
			}
		});
	}
}