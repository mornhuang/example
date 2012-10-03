package com.redsaga.hibernatesample.step4.action;

import java.util.List;

import com.redsaga.hibernatesample.step4.ForumService;
import com.redsaga.hibernatesample.step4.ForumServiceFactory;


public class ListBoardAction extends AbstractAction {

	private List boards;
	
	public String execute(){
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		boards = fs.getBoardList();
		set("boards",boards);
		return SUCCESS;
	}

	public List getBoards() {
		return boards;
	}
}
