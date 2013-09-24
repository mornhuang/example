package lee;

import org.springframework.context.*;
import org.springframework.context.support.*;
import org.springframework.core.io.*;

import org.dom4j.io.*;
import org.dom4j.*;
import java.util.*;
/**
 * Description:
 * <br/>ÍøÕ¾: <a href="http://www.crazyit.org">·è¿ñJavaÁªÃË</a> 
 * <br/>Copyright (C), 2001-2012, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class SpringTest
{
	public static void main(String[] args) throws Exception
	{
//		ApplicationContext ctx = new 
//			FileSystemXmlApplicationContext("bean.xml");
//		ApplicationContext ctx = new 
//			FileSystemXmlApplicationContext("classpath:bean.xml");
//		ApplicationContext ctx = new 
//			FileSystemXmlApplicationContext("classpath*:bean.xml");
		ApplicationContext ctx = new 
			ClassPathXmlApplicationContext("bean*.xml");
//		ApplicationContext ctx = new 
//			FileSystemXmlApplicationContext("classpath*:bean*.xml");
//		ApplicationContext ctx = new 
//			FileSystemXmlApplicationContext("classpath:bean2.xml");
		System.out.println("----" + ctx);
		Resource r = ctx.getResource("book.xml");
		System.out.println(r.getClass());
		System.out.println(r.getDescription());
		SAXReader reader = new SAXReader();
		Document doc = reader.read(r.getInputStream());
		Element el = doc.getRootElement();
		List l = el.elements();
		for (Iterator it = l.iterator();it.hasNext() ; )
		{
			Element book = (Element)it.next();
			List ll = book.elements();
			for (Iterator it2 = ll.iterator();it2.hasNext() ; )
			{
				Element eee = (Element)it2.next();
				System.out.println(eee.getText());
			}
		}
	}
}
