package org.crazyit.app.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.json.annotations.JSON;
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
public class JSONExample
{
	//模拟处理结果的属性
	private int[] ints = {10, 20};
	private Map<String , String> map
		= new HashMap<String , String>();
	private String customName = "顾客";
	//封装请求参数的三个属性
	private String field1;
	//'transient'修饰的属性不会被序列化
	private transient String field2;
	//没有setter和getter方法的字段不会被序列化
	private String field3;
	
	public String execute()
	{
		map.put("name", "疯狂Java讲义");
		return Action.SUCCESS;
	}

	//使用注释语法来改变该属性序列化后的属性名
	@JSON(name="newName")
	public Map getMap()
	{
		return this.map;
	}
	
	//customName属性的setter和getter方法
	public void setCustomName(String customName)
	{
		this.customName = customName;
	}
	public String getCustomName()
	{
		return this.customName;
	}

	//field1属性的setter和getter方法
	public void setField1(String field1)
	{
		this.field1 = field1;
	}
	public String getField1()
	{
		return this.field1;
	}

	//field2属性的setter和getter方法
	public void setField2(String field2)
	{
		this.field2 = field2;
	}
	public String getField2()
	{
		return this.field2;
	}

	//field3属性的setter和getter方法
	public void setField3(String field3)
	{
		this.field3 = field3;
	}
	public String getField3()
	{
		return this.field3;
	}
}