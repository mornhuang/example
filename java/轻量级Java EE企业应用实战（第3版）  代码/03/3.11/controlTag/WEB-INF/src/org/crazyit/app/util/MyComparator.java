package org.crazyit.app.util;

import java.util.Comparator;

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
public class MyComparator implements Comparator
{
	//决定两个元素大小的方法
	public int compare(Object element1, Object element2)
	{
		//根据元素字符串长度来决定大小	
		return element1.toString().length()
			- element2.toString().length();
	}
}
