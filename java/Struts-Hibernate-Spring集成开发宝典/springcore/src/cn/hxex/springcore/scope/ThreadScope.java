package cn.hxex.springcore.scope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 自定义的作用域对象
 */
public class ThreadScope implements Scope {

	// 保存所有Bean实例的容器
	static Map container = Collections.synchronizedMap( new HashMap() );
	
	/**
	 * 得到Bean的实例
	 */
	public Object get(String name, ObjectFactory factory) {
		
		Map beans = (Map)container.get( getThreadId() );
		if( beans==null ) {
			beans = new HashMap();
			container.put( getThreadId(), beans );
		}
		
		Object obj = beans.get( name );
		if( obj==null ) {
			obj = factory.getObject();
			beans.put( name, obj );
		}
		
		return obj;
	}

	/**
	 * 删除Bean的实例
	 */
	public Object remove(String name) {
		
		Map beans = (Map)container.get( getThreadId() );
		if( beans!=null ) {
			Object obj = beans.get( name );
			if( obj!=null ) {
				beans.remove( name );
				return obj;
			}
		}
		
		return null;
	}

	/**
	 * 得到当前进程的ID
	 */
	private Long getThreadId() {
		
		long id = Thread.currentThread().getId();
		return Long.valueOf( id );
		
	}
}
