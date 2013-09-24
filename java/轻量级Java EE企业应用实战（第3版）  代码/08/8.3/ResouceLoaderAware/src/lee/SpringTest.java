package lee;

import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.core.io.*;

import org.dom4j.io.XPPReader;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.*;

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
	public static void main(String[] args)
	{
		//创建ApplicationContext容器
		ApplicationContext ctx = new 
			ClassPathXmlApplicationContext("bean.xml");
		//获取容器中名为test的Bean实例
		TestBean tb = ctx.getBean("test" , TestBean.class);
		//通过tb实例来获取ResourceLoader对象
		ResourceLoader rl = tb.getResourceLoader();
		//判断程序获得ResourceLoader和容器是否相同
		System.out.println(rl == ctx);
	}
}
