
package lee;
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
public class UserBean
{
	private String name;
	private String gender;

	//无参数的构造器
	public UserBean()
	{
	}
	//初始化全部属性的构造器
	public UserBean(String name , String gender)
	{
		this.name = name;
		this.gender = gender;
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

	//gender属性的setter和getter方法
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	public String getGender()
	{
		return this.gender;
	}
}