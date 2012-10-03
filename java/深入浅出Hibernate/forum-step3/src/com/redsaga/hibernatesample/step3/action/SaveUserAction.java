package com.redsaga.hibernatesample.step3.action;

import com.redsaga.hibernatesample.step3.ForumService;
import com.redsaga.hibernatesample.step3.ForumServiceFactory;
import com.redsaga.hibernatesample.step3.User;


public class SaveUserAction extends AbstractAction {

	private User user;
	
	public SaveUserAction() {
		user = new User();
	}

	public String execute() {
		ForumService fs = ForumServiceFactory.getHibernateForumService();
		fs.saveUser(user);
		set("user", user);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

}
