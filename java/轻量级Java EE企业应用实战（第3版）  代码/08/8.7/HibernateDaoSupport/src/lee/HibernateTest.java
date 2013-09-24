package lee;

import org.springframework.context.*;
import org.springframework.context.support.*;

import org.crazyit.app.dao.*;
import org.crazyit.app.domain.*;
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
public class HibernateTest
{
	public static void main(String[] args)
	{
		//创建Spring容器
		ApplicationContext ctx = 
			new ClassPathXmlApplicationContext("bean.xml");
		//获取DAO组件
		PersonDao pdao = (PersonDao)ctx.getBean("personDao");
		//循环插入10条记录
		for (int i = 0 ; i < 10  ; i++ )
		{
			pdao.save(new Person(i + "" , i + 10));
		}
		//调用DAO组件的方法
		System.out.println(pdao.getPersonNumber());
	}
}