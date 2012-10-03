package com.redsaga.hibernatesample.step4;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.sql.InFragment;

import com.redsaga.hibernatesample.step4.base._BaseRootDAO;
import com.redsaga.hibernatesample.step4.dao.*;
import com.redsaga.hibernatesample.step4.util.HibernateCallback;
import com.redsaga.hibernatesample.step4.util.HibernateTemplate;

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
				userDAO.saveOrUpdate(u);
				return u;
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
                Session session = RootDAO.createSession();
                session.lock(b,LockMode.READ);
                b.addToArticles(a);
				ArticleDAO articleDAO = ArticleDAO.getInstance();
				articleDAO.save(a);
                a.setRootId(a.getId().intValue());
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
                reply.setRootId(a.getRootId());
                Session session = RootDAO.createSession();
                session.lock(a,LockMode.READ);

				a.addToChildPosts(reply);
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
				List l =  boardDAO.find("from Board b where b.parent is null");
                Session session = BoardDAO.createSession();
                for (int i=0;i<l.size();i++)
                {
                    Board b = (Board) l.get(i);
                    session.lock(b,LockMode.READ);
                    Set s =b.getChildBoards();
                    Iterator it  = s.iterator();
                    while (it.hasNext()  ) {
                        Board b2 = (Board) it.next();
                        session.lock(b2,LockMode.READ);
                        b2.getId();
                    }
                }
                //session.close();
                return l;
			}
		});
	}

    public List getBoardPostList(final Board b, final int pageSize, final int pageNo) {
        return (List) new HibernateTemplate().run(new HibernateCallback() {
            public Object execute() throws HibernateException {
                Session session = RootDAO.createSession();
                List result = session.createQuery(
                        "from Article a where a.board=:board order by a.rootId desc ,a.treeIndex asc ")
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

    public User getUserByName(final String name) {
        return (User) new HibernateTemplate().run(new HibernateCallback() {
            public Object execute() throws HibernateException {
                Session session = RootDAO.createSession();
                User result = (User) session.createQuery(
                        "from User u where u.name=:name ")
                        .setString("name",name)
                        .uniqueResult();
                return result;
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

    public void replyVote(final Vote root, final Article reply,final int voteFor) {
         logger.info("replyVote:"+root+", "+reply+", selection:"+voteFor);
         new HibernateTemplate().run(new HibernateCallback() {
            public Object execute() throws HibernateException {
                reply.setParent(root);
                reply.setBoard(root.getBoard());
                reply.setRootId(root.getRootId());
                Session session = RootDAO.createSession();
                session.lock(root,LockMode.READ);

                Iterator it = root.getOptionSet().iterator();
                while (it.hasNext()){
                    VoteOption vo = (VoteOption) it.next();
                    if (vo.getId().intValue()==voteFor){
                        vo.setAgreeNumber(vo.getAgreeNumber()+1);
                        break;
                    }
                }

                root.addToChildPosts(reply);
                ArticleDAO articleDAO = ArticleDAO.getInstance();
                articleDAO.save(reply);
                VoteDAO voteDAO = VoteDAO.getInstance();
                voteDAO.save(root);
                return null;
            }
        });
    }

}
