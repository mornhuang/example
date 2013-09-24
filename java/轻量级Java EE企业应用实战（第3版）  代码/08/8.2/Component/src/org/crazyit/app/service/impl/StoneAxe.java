package org.crazyit.app.service.impl;

import org.springframework.stereotype.*;

import org.crazyit.app.service.*;
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
@Component
public class StoneAxe
	implements Axe
{
	public String chop()
	{
		return "Ê¯¸«¿³²ñºÃÂý";
	}
}