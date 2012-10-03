package cn.hxex.blog.dao.hibernate;

import cn.hxex.blog.dao.IUserDAO;
import cn.hxex.blog.exception.BlogDAOException;
import cn.hxex.blog.hibernate.HibernateDAO;
import cn.hxex.blog.model.User;

/**
 * IUserDAO接口的Hibernate实现
 */
public class UserDAO extends HibernateDAO implements IUserDAO {

	/**
	 * 保存用户信息到数据库
	 * @param user 被保存的用户对象
	 */
	public void saveUser(User user)
	{
		if (user == null)
			return;

		User u = getUser(user.getName());
		if (u != null)
			throw new BlogDAOException("用户名已经存在，请使用其它用户名！");

		saveObject(user);
	}

	/**
	 * 得到用户对象
	 * @param username 用户的登录名
	 * @return 指定登录名的用户对象
	 */
	public User getUser(String username)
	{
		User u = (User) getObject("from User u where u.name = '" + username
				+ "'");
		return u;
	}

	/**
	 * 得到用户对象的信息
	 * @param id 用户的ID值
	 * @return 指定的用户信息
	 */
	public User getUserById(String id)
	{
		return (User) getObject(User.class, id);
	}

}
