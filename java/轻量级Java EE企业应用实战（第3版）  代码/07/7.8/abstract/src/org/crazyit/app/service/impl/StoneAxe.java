package org.crazyit.app.service.impl;

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
public class StoneAxe
	implements Axe
{
	public StoneAxe()
	{
		System.out.println("Spring实例化依赖bean：StoneAxe实例...");
	}
	public String chop()
	{
		return "石斧砍柴好慢";
	}
}