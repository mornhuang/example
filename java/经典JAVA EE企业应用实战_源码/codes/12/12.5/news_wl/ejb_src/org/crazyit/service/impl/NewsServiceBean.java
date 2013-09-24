package org.crazyit.service.impl;

import java.util.*;
import javax.ejb.*;

import org.crazyit.service.*;
import org.crazyit.eao.*;
import org.crazyit.model.*;

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
@Stateless
//定义容器管理事务的配置
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NewsServiceBean
	implements NewsService
{
	//依赖注入EAO组件
	@EJB(beanName="newsEao")
	private NewsEao newsEao;
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public int addNews(String title , String content)
	{
		News news = new News();
		news.setTitle(title);
		news.setContent(content);
		//调用EAO组件的方法执行持久化
		newsEao.save(news);
		return news.getId();
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<News> getAllNews()
	{
		//调用EAO的方法实现业务逻辑
		return newsEao.findAll();
	}
}
