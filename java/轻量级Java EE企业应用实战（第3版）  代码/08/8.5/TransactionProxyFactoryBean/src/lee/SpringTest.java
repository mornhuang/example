package lee;

import org.springframework.context.support.*;
import org.springframework.context.*;

import org.crazyit.app.dao.*;
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
public class SpringTest
{
	public static void main(String[] args) 
	{
		//创建Spring容器
		ApplicationContext ctx = new 
			ClassPathXmlApplicationContext("bean.xml");
		//获取事务代理Bean
		NewsDao dao = (NewsDao)ctx
			.getBean("newsDaoTrans" , NewsDao.class);
		//执行插入操作
		dao.insert("疯狂Java" , "轻量级Java EE企业应用实战");
	}
}
