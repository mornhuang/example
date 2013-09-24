package org.crazyit.common.hibernate3;

import org.hibernate.event.def.DefaultLoadEventListener;
import org.hibernate.HibernateException;
import org.hibernate.event.LoadEvent;
import org.hibernate.event.LoadEventListener;

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
public class MyLoadListener extends DefaultLoadEventListener
{
	//在LoadEventListener接口仅仅定义了这个方法
	public void onLoad(LoadEvent event,
		LoadEventListener.LoadType loadType)
		throws HibernateException
	{
		System.out.println("自定义的load事件");
		System.out.println(event.getEntityClassName() 
			+ "==========" + event.getEntityId());
		super.onLoad(event, loadType);
	}
}