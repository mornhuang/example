package org.crazyit.service;

import javax.ejb.*;
import java.util.*;

import org.crazyit.model.*;
/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Local
public interface NewsService
{
	//处理添加消息
	int addNews(String title , String content);
	//获取系统中所有消息
	List<News> getAllNews();
}
