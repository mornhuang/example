package org.crazyit.common.hibernate3.interceptor;

import java.util.*;
import java.io.*;

import org.hibernate.*;
import org.hibernate.type.Type;

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
//通常采用采用继承EmptyInterceptor来实现拦截器
public class MyInterceptor extends EmptyInterceptor
{
	//记录修改次数
	private int updates;
	//记录创建次数
	private int creates;
	
	//当删除实体时，onDelete方法将被调用
	public void onDelete(Object entity , Serializable id , 
		Object[] state , String[] propertyNames , Type[] types)
	{
		// do nothing
	}

	//当把持久化实体的状态同步到数据库时，onFlushDirty方法被调用
	public boolean onFlushDirty(Object entity , Serializable id ,
		Object[] currentState, Object[] previousState,
		String[] propertyNames, Type[] types)
	{
		//每同步一次，修改的累加器加1
		updates++;
		for ( int i = 0; i < propertyNames.length; i++ )
		{
			if ( "lastUpdateTimestamp".equals( propertyNames[i] ) )
			{
				currentState[i] = new Date();
				return true;
			}
		}
		return false;
	}

	//当加载持久化实体时，onLoad方法被调用
	public boolean onLoad(Object entity , Serializable id ,
		Object[] state,String[] propertyNames,Type[] types)
	{
		for ( int i = 0; i < propertyNames.length ; i++ )
		{
			if ( "name".equals( propertyNames[i] ) )
			{
				//输出被装载实体的name属性值
				System.out.println( state[i] );
				return true;
			}
		}
		return false;
	}

	//保存持久化实例时候，调用该方法
	public boolean onSave(Object entity , Serializable id ,
		Object[] state,String[] propertyNames,Type[] types)
	{
		creates++;
		for ( int i = 0; i < propertyNames.length; i++ )
		{
			if ("createTimestamp".equals( propertyNames[i]))
			{
				state[i] = new Date();
				return true;
			}
		}
		return false;
	}

	//持久化所做修改同步完成后，调用postFlush方法
	public void postFlush(Iterator entities)
	{
		System.out.println("创建的次数： " 
			+ creates + ", 更新的次数： " + updates);
	}

	//在同步持久化所做修改之前，调用preFlush方法
	public void preFlush(Iterator entities)
	{
		// do nothing
	}

	//事务提交之前触发该方法
	public void beforeTransactionCompletion(Transaction tx) 
	{
		System.out.println("事务即将结束");
	}

	//事务提交之后触发该方法
	public void afterTransactionCompletion(Transaction tx) 
	{
		System.out.println("事务已经结束");
	}
}