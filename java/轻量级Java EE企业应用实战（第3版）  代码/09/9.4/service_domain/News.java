

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class News
{
	//此处省略了其他的属性
	...
	//此处省略了属性对应的setter和getter方法
	...
	//增加新闻回复的业务逻辑方法
	public NewsReview addNewsReview(String content)
	{
		//以默认构造器创建新闻回复实例
		NewsReview review = new NewsReview();
		//设置回复内容
		review.setContent(content);
		//设置回复的发布日期
		review.setPostDate(new Date());
		//设置回复的最后修改日期
		review.setLastModifyDate(new Date());
		//设置回复与消息的关联
		review.setNews(this);
		//直接调用newsReviewsDao完成消息回复的持久化
		newsReviewsDao.save(review);
		return review;
	}
	//此处省略了重写的hashCode、equals等方法
	...
}
