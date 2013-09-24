package lee;

import javax.persistence.*;

import org.crazyit.model.*;

import org.crazyit.util.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */

public class AddNewsBean
{
	//下面的三个属性都会直接与JSF标签绑定
	private String title;
	private String content;

	//title属性的setter和getter方法
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getTitle()
	{
		return this.title;
	}
	//content属性的setter和getter方法
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getContent()
	{
		return this.content;
	}
	public String add()
	{
		//创建实体对象，并设置属性
		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		//打开一个当前线程所关联的EntityManager
		EntityManager em = EntityManagerUtil.getEntityManager();
		//开始事务
		EntityManagerUtil.beginTransaction();
		//持久化保存News实体
		em.persist(news);
		//提交事务
		EntityManagerUtil.commit();
		//关闭EntityManager
		EntityManagerUtil.closeEntityManager();
		return "success";
	}
}