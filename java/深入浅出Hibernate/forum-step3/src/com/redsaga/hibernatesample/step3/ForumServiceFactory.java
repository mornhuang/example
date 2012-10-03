/*
 * Created on 2005-2-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.redsaga.hibernatesample.step3;

/**
 * @author cao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ForumServiceFactory {      
	public static ForumService getHibernateForumService(){
		return new HibernateForumService();
	}
}
