package org.crazyit.app.util;

import org.apache.struts2.util.SubsetIteratorFilter;

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
//用户自定义的Decider类，实现了SubsetIteratorFilter.Decider接口
public class MyDecider 
	implements SubsetIteratorFilter.Decider
{
	//实现Decider接口必须实现的decide方法，
	//该方法决定集合中的元素是否被选入子集
	public boolean decide(Object element) throws Exception
	{
		String str = (String)element;
		//如果集合元素（字符串）中包含Java EE子串，即可被选入子集
		return str.indexOf("Java EE") > 0;
	}
}