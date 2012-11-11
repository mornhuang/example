package cn.hxex.blog.dao;

import cn.hxex.blog.model.User;

/**
 * 操作User对象的DAO接口
 */
public interface IUserDAO {
	// 保存用户对象
	public void saveUser( User user );
	// 根据用户名得到用户信息
	public User getUser( String username );
	// 根据用户对象得到用户信息
	public User getUserById( String id );
}
