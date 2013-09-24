package lee;

import java.util.*;
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
public class TestBean
{
	//下面的三个属性都会直接与JSF标签绑定
	private String name;
	private Map<String , Double> books;
	private List<String> schools;
	//无参数的构造器
	public TestBean()
	{
	}
	//初始化全部属性的构造器
	public TestBean(String name 
		, Map<String , Double> books 
		, List<String> schools)
	{
		this.name = name;
		this.books = books;
		this.schools = schools;
	}

	//name属性的setter和getter方法
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return this.name;
	}

	//schools属性的setter和getter方法
	public void setSchools(List<String> schools)
	{
		this.schools = schools;
	}
	public List<String> getSchools()
	{
		return this.schools;
	}
	//books属性的setter和getter方法
	public void setBooks(Map<String , Double> books)
	{
		this.books = books;
	}
	public Map<String , Double> getBooks()
	{
		return this.books;
	}

}