package lee;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.sql.*;
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
public class IteratorTag extends SimpleTagSupport
{
	//标签属性，用于指定需要被迭代的集合
	private String collection;
	//标签属性，指定迭代集合元素，为集合元素指定的名称
	private String item;
	
	//collection属性的setter和getter方法
	public void setCollection(String collection)
	{
		this.collection = collection;
	}
	public String getCollection()
	{
		return this.collection;
	}
	//item属性的setter和getter方法
	public void setItem(String item)
	{
		this.item = item;
	}
	public String getItem()
	{
		return this.item;
	}
	//标签的处理方法，简单标签处理类只需要重写doTag方法
	public void doTag() throws JspException, IOException
	{
		//从page scope中获取属性名为collection的集合
		Collection itemList = (Collection)getJspContext().
			getAttribute(collection);
		//遍历集合
		for (Object s : itemList)
		{
			//将集合的元素设置到page 范围
			getJspContext().setAttribute(item, s );
			//输出标签体
			getJspBody().invoke(null);
		}
	}
}