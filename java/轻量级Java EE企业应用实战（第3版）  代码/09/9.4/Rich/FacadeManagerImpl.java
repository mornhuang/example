

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
public class FacadeManagerImpl 
	implements FacadeManager
{
	//业务逻辑对象依赖的DAO对象
	private CategoryDao categoryDao;
	private NewsDao newsDao;
	private NewsReviewDao newsReviewDao;
	private UserDAO userDao;
	//此处还应该增加依赖注入DAO对象必需的setter方法
	...
	//此处还应该增加其他业务逻辑方法
	...
	//下面是增加新闻回复的业务方法
	public NewsReview addNewsReview(Long newsId , String content)
	{
		//根据新闻id加载新闻
		News news = newsDao.getNews(newsId);
		//通过News的业务方法添加回复
		NewsReview review = news.addNewsReview(content);
		//此处必须显示持久化消息回复
		newsReviewDao.saveNewsReview(review);
		return review;
	}
}

