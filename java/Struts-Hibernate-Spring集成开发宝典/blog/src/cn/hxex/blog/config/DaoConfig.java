package cn.hxex.blog.config;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import cn.hxex.blog.exception.BlogException;

/**
 * DAO配置信息类
 */
public class DaoConfig {

	private String id;
	private String type;

	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getType() 
	{
		return type;
	}
	public void setType(String type) 
	{
		this.type = type;
	}	
	
	private Object instance;

	/**
	 * 得到配置的对象的实例
	 */
	public Object getInstance() 
	{
		if( null!=instance ) return instance;
		
		try
		{
			Class cls = Class.forName( getType() );
			instance = cls.newInstance();
			return instance;
		}
		catch( Exception ex )
		{
			throw new BlogException( "Couldn't find class:" + getType() );
		}
	}
	
	public String toString()
	{
		return ReflectionToStringBuilder.toString( this );
	}

}
