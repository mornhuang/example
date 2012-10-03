package cn.hxex.blog.dao.hibernate;

import java.util.Iterator;
import java.util.List;

import cn.hxex.blog.dao.IMessageDAO;
import cn.hxex.blog.exception.BlogDAOException;
import cn.hxex.blog.hibernate.HibernateDAO;
import cn.hxex.blog.model.Message;
import cn.hxex.blog.model.ReplyMessage;
import cn.hxex.blog.model.User;

public class MessageDAO extends HibernateDAO implements IMessageDAO {

	/**
	 * 保存留言信息
	 * 
	 * @param message
	 *            被保存的留言对象
	 */
	public void saveMessage(Message message)
	{
		super.saveObject(message);
	}

	/**
	 * 得到所有的留言信息
	 * 
	 * @return 返回所有的留言信息
	 */
	public List getMessages()
	{
		String hsql = "from Message";
		return super.getObjects(hsql);
	}

	/**
	 * 删除留言信息
	 * 
	 * @param id
	 *            要删除的留言信息的ID值
	 * @param userId
	 *            执行删除操作的用户ID
	 */
	public void deleteMessage(String id, String userId)
	{
		User user = (User)getObject( User.class, userId );
		if( user==null ) {
			throw new BlogDAOException( "Couldn't user!" );
		}
		
		Message m = null;
		
		Iterator it = user.getMessages().iterator();
		while( it.hasNext() ) {
			Message message = (Message)it.next();
			if( message.getId().equals( id ) ) {
				m = message;
			}
		}
		if( m!=null ) {
			deleteObject( m );
			user.getMessages().remove( m );
		}
	}

	/**
	 * 得到留言信息
	 * 
	 * @param id
	 *            留言的ID值
	 * @return 指定ID值的留言对象
	 */
	public Message getMessage(String id)
	{
		return (Message) getObject(Message.class, id);
	}

	/**
	 * 更新留言信息
	 * 
	 * @param message
	 *            欲更新的留言对象
	 */
	public void updateMessage(Message message)
	{
		updateObject(message);
	}

	/**
	 * 删除网络日志的评论
	 * 
	 * @param id 评论的ID值
	 * @param userId 删除的用户
	 */
	public void deleteReplyMessage(String id, String userId) {
		ReplyMessage reply = (ReplyMessage)getObject( ReplyMessage.class, id );
		if( reply==null ) {
			throw new BlogDAOException("找不到你所要删除的日志评论!");
		}
		
		Message message = reply.getMessage();
		Iterator it = message.getReplies().iterator();
		while( it.hasNext() ) {
			ReplyMessage r = (ReplyMessage)it.next();
			if( r.getId().equals( id ) ) {
				message.getReplies().remove( r );
			}
		}
	}
}
