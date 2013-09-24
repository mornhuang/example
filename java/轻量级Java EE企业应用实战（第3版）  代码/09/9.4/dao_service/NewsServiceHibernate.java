

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
// NewsServiceHibernate继承HibernateDaoSupport，
//实现NewsService接口，它既是DAO组件，也是业务逻辑组件
public class NewsServiceHibernate 
	extends HibernateDaoSupport implements NewsService
{
	//根据主键加载消息
	public News getNews(Long id) 
	{
		return (News)getHibernateTemplate()
			.get(News.class, id);
	}
	//保存新的消息
	public void saveNews(News news)
	{
		getHibernateTemplate().saveOrUpdate(news);
	}
	//根据主键删除消息
	public void removeNews(Long id)
	{
		getHibernateTemplate().delete(getNews(id));
	}
	//查找全部的消息
	public List findAll()
	{
		getHibernateTemplate().find("from News");
	}
	//下面是增加新闻回复的业务方法
	public NewsReview addNewsReview(Long newsId
		, String content)
	{
		//根据新闻id加载新闻
		News news = newsDao.getNews(newsId);
		//通过News的业务方法添加回复
		NewsReview review = news.addNewsReview(content);
		//此处必须显示持久化消息回复
		newsReviewService.saveNewsReview(review);
		return review;
	}
}

