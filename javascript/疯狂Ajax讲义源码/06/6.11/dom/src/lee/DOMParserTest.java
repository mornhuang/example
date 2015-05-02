package lee;

import org.w3c.dom.*;
import java.io.*;
import java.util.*;
import  org.apache.xerces.parsers.DOMParser;

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
public class DOMParserTest
{
	public static void main(String[] args)
	{
		DOMParserTest tp = new DOMParserTest();
		tp.parseXMLFile("student.xml");
	}
	/**
	 * 解析文档
	 * @param fileName
	 */
	public void parseXMLFile(String fileName)
	{
		try
		{
			//构造DOM解析器的实例
			DOMParser parser = new DOMParser();
			//开始解析文档，将XML文件转换成DOM树存入内存。
			parser.parse(fileName);
			//getDocument()获取Document对象
			Document doc = parser.getDocument();
			//获取root节点
			Element elmtInfo = doc.getDocumentElement();
			//getElementsByTagName()根据标签名获取子节点列表
			NodeList nlStudent = elmtInfo
				.getElementsByTagName("student");
			System.out.println("XML文件开始解析");
			//循环输出每一个学生成绩            
			for (int i = 0; i < nlStudent.getLength(); i++)
			{                
				//当前student元素
				Element elmtStudent = (Element) nlStudent.item(i);
				//利用父子关系获取子节点
				NodeList nlCurrent = elmtStudent
					.getElementsByTagName("name");
				//读取到姓名节点的值
				System.out.println("姓名:" + nlCurrent.item(0)
					.getFirstChild().getNodeValue());
				//利用父子关系获取子节点
				nlCurrent = elmtStudent.getElementsByTagName("sex");
				//读取到性别节点的值
				System.out.println("性别:" + nlCurrent.item(0)
					.getFirstChild().getNodeValue());
				//再次获取多个课程节点
				nlCurrent = elmtStudent.getElementsByTagName("lesson");
				//遍历每个课程节点。
				for (int j = 0; j < nlCurrent.getLength(); j++)
				{
					//获取第i个课程节点
					Element elmtLesson = (Element) nlCurrent.item(j);
					//获取lessonName节点的值
					NodeList nlLesson = elmtLesson
						.getElementsByTagName("lessonName");
					System.out.print(nlLesson.item(0)
						.getFirstChild().getNodeValue());
					System.out.print(":");
					//获取lessonScore节点的值
					nlLesson = elmtLesson
						.getElementsByTagName("lessonScore");
					System.out.print(nlLesson.item(0)
						.getFirstChild().getNodeValue());
					System.out.println();
				}
				System.out.println("---------------------");
			}
			System.out.println("XML文件解析结束");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}