package lee;

import org.springframework.context.*;
import org.springframework.context.support.*;

import java.util.*;

import org.crazyit.app.service.*;
/**
 * Description:
 * <br/>利嫋: <a href="http://www.crazyit.org">決髄Java選男</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class SpELTest
{
	public static void main(String[] args)
	{
		ApplicationContext ctx = new 
			ClassPathXmlApplicationContext("bean.xml");
		Person author = ctx.getBean("author" , Person.class);
		System.out.println(author.getBooks());
		author.useAxe();

	}
}