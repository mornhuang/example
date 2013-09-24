

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
	private UserDao userDao;

	//此处还应该增加依赖注入DAO对象必需的setter方法
	...
	//此处还应该增加其他业务逻辑方法
	...
	//下面是增加新闻回复的业务方法
	public NewsReview addNewsReview(Long newsId , String content)
	{
		//根据新闻id加载新闻
		News news = newsDao.getNews(newsId);
		//以默认构造器创建新闻回复
		NewsReview review = new NewsReview();
		//设置新闻与新闻回复之间的关联
		review.setNews(news);
		//设置新闻回复的内容
		review.setContent(content);
		//设置回复的回复时间
		review.setPostDate(new Date());
		//设置新闻回复的最后修改时间
		review.setLastModifyDate(new Date());
		//保存回复
		newsReviewDao.saveNewsReview(review);
		return review;
	}
}

