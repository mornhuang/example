package org.crazyit.service;

import javax.ejb.*;

import java.util.*;
/**
 * Description:
 * <br/>利嫋: <a href="http://www.crazyit.org">決髄Java選男</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Remote
public interface HelloCrazyit
{
	String hello(String name);
	void crazyit();
	void exclude();
}
