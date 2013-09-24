package lee;

import java.io.*;
import org.springframework.context.*;
import org.springframework.context.support.*;

import org.crazyit.app.service.*;
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
	public static void main(String[] args)throws Exception
	{
		ApplicationContext ctx = new
			ClassPathXmlApplicationContext("bean.xml");
		Chinese p = ctx.getBean("chinese" , Chinese.class);
		//打印出Chinese实例获得的ApplicationContext
		System.out.println(p.getContext());
		//比较两种方法获得的BeanFactory
		System.out.println(ctx == p.getContext());
	}
}