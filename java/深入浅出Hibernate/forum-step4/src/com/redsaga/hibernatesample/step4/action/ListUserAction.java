package com.redsaga.hibernatesample.step4.action;

import java.util.List;

import com.redsaga.hibernatesample.step4.ForumService;
import com.redsaga.hibernatesample.step4.ForumServiceFactory;


public class ListUserAction extends AbstractAction {

	private List users;
	
	public String execute(){
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		users = fs.getUserList();
		set("users",users);
		return SUCCESS;
	}

	public List getUsers() {
		return users;
	}
}
