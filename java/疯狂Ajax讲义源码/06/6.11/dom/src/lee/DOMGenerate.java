package lee;

import java.io.StringWriter;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyjava.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
public class DOMGenerate
{
	//主方法，程序的入口
	public static void main( String[] args ) 
	{
		try 
		{
			//创建DocumentFactory对象
			DocumentBuilderFactory dbf = 
				DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			//创建Document对象
			Document doc = db.newDocument();
			//创建根元素
			Element root = doc.createElement("Student");
			//创建name元素
			Element item = doc.createElement("name"); 
			//为name元素增加文本子节点
			item.appendChild(doc.createTextNode("张三"));
			//将name元素添加到根元素下
			root.appendChild(item);
			//创建age元素
			item = doc.createElement("age"); 
			//为age元素增加文本子元素
			item.appendChild(doc.createTextNode("28" ));
			//将age元素添加到根元素下
			root.appendChild(item);
			//创建high元素
			item = doc.createElement("high");
			//为high元素添加文本子元素
			item.appendChild(doc.createTextNode("1.72" ));
			//将high元素添加到根元素下
			root.appendChild(item);
			//创建score元素
			item = doc.createElement("score");
			//创建Java元素
			Element lesson = doc.createElement("Java");
			//为Java元素添加文本子元素。
			lesson.appendChild(doc.createTextNode("95"));
			//将Java元素添加到score元素
			item.appendChild( lesson );
			//创建Struts元素
			lesson = doc.createElement("Struts");
			//为Struts元素添加文本子元素。
			lesson.appendChild(doc.createTextNode("90"));
			//将Struts元素添加到score元素
			item.appendChild( lesson );
			//创建Hibernate元素
			lesson = doc.createElement("Hibernate");
			//为Hibernate元素添加文本子元素。
			lesson.appendChild(doc.createTextNode("90"));
			//将Hibernate元素添加到score元素
			item.appendChild( lesson );
			//将score元素添加到根元素下
			root.appendChild( item ); 
			//为文档指定根元素
			doc.appendChild( root );
			//指定输出格式
			OutputFormat format = new OutputFormat(doc
				, "GBK" , true);
			StringWriter stringOut = new StringWriter();  
			XMLSerializer serial = new XMLSerializer( stringOut, format );
			//将DOM树转换成字符串
			serial.asDOMSerializer(); 
			serial.serialize(doc.getDocumentElement());
			//创建文件输出流
			PrintStream ps = new PrintStream(new FileOutputStream("new.xml"));
			//输出XML文件
			ps.println(stringOut.toString());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}