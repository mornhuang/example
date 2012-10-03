package cn.hxex.blog.dao;

import java.util.List;

import cn.hxex.blog.model.Message;

/**
 * 操作Message对象的DAO接口
 */
public interface IMessageDAO {

	// 保存留言信息
	public void saveMessage( Message message );
	// 修改留言信息
	public void updateMessage( Message message );
	// 得到所有的留言信息
	public List getMessages( );
	// 删除留言信息
	public void deleteMessage( String id, String userId );
	// 根据ID得到留言的信息
	public Message getMessage( String id );
	// 根据ID删除日志评论
	public void deleteReplyMessage( String id, String userId );

}
