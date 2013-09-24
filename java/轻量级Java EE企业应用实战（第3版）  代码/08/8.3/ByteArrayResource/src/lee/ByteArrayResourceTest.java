package lee;

import org.springframework.core.io.ByteArrayResource;
import org.dom4j.*;
import org.dom4j.io.*;
import java.util.*;

import java.util.*;
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
public class ByteArrayResourceTest 
{
	public static void main(String[] args) throws Exception
	{
		String file = "<?xml version='1.0' encoding='GBK'?>"
			+ "<计算机书籍列表><书><书名>疯狂Java讲义"
			+ "</书名><作者>李刚</作者></书><书><书名>"
			+ "轻量级Java EE企业应用实战</书名><作者>李刚"
			+ "</作者></书></计算机书籍列表>";
		byte[] fileBytes = file.getBytes();
		//以字节数组作为资源来创建Resource对象
		ByteArrayResource bar = new ByteArrayResource(fileBytes);
		//获取该资源的简单信息
		System.out.println(bar.getDescription());
		//创建Dom4j的解析器
		SAXReader reader = new SAXReader();
		Document doc = reader.read(bar.getInputStream());
		//获取根元素
		Element el = doc.getRootElement();
		List l = el.elements();
		//遍历根元素的全部子元素
		for (Iterator it = l.iterator();it.hasNext() ; )
		{
			//每个节点都是<书>节点
			Element book = (Element)it.next();
			List ll = book.elements();
			//遍历<书>节点的全部子节点
			for (Iterator it2 = ll.iterator();it2.hasNext() ; )
			{
				Element eee = (Element)it2.next();
				System.out.println(eee.getText());
			}
		}
	}
}
