/*
 * Created on 2005-2-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.redsaga.hibnatesample.step2;

import java.util.List;

/**
 * @author cao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ForumService {
	public void saveUser(User u);
	public void deleteUser(User u);
	public void addBoard(Board b);
	public void addChildBoard(Board parent,Board child);
	public void deleteBoard(Board b);
	public void addNewPost(Board b,Article a);
	public void replyPost(Article a,Article reply);
	public void readPost(Article a);
	public void deletePost(Article a);
	
	public List getUserList();
	public List getBoardList();
	public List getBoardPostList(Board b,int pageSize,int pageNo);
	
	
	public User getUser(Integer uid);
	public Board getBoard(Integer bid);
	public Article getPost(Integer aid);
	
}
