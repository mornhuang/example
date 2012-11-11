package cn.hxex.blog.config;

import java.util.Hashtable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import cn.hxex.blog.exception.BlogException;

/**
 * 系统配置信息类
 */
public class BlogConfig {

	public static Hashtable beans;

	/**
	 * 构造函数
	 */
	public BlogConfig()
	{
		beans = new Hashtable();
	}

	/**
	 * 增加一个BeanConfig对象
	 * @param bean
	 */
	public void addDao(DaoConfig bean)
	{
		beans.put(bean.getId(), bean);
	}

	/**
	 * 得到一个DAO接口对象的实例
	 * @param name DAO接口对象的名称
	 * @return 指定DAO接口的实现类的实例
	 */
	public Object getDao(String name)
	{
		DaoConfig config = (DaoConfig) beans.get(name);

		if (config == null)
		{
			throw new BlogException("Couldn't find the dao: " + name);
		}

		return config.getInstance();
	}

	public String toString()
	{
		return ReflectionToStringBuilder.toString(this);
	}
}
