

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
//NewsDaoHibernate继承HibernateDaoSupport，实现NewsDao接口
public class NewsDaoHibernate
	extends HibernateDaoSupport implements NewsDao
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
}
