package lee;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;
import java.util.*;

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
public class DynaAttributesTag 
	extends SimpleTagSupport implements DynamicAttributes
{
	//保存每个属性名的集合
	private ArrayList<String> keys = new ArrayList<String>();
	//保存每个属性值的集合
	private ArrayList<Object> values = new ArrayList<Object>();

	@Override
	public void doTag() throws JspException, IOException
	{
		JspWriter out = getJspContext().getOut();
		//此处只是简单地输出每个属性
		out.println("<ol>");
		for( int i = 0; i < keys.size(); i++ )
		{
			String key = keys.get( i );
			Object value = values.get( i );
			out.println( "<li>" + key + " = " + value + "</li>" );
		}
		out.println("</ol>");
	}
	
	@Override
	public void setDynamicAttribute( String uri, String localName, 
		Object value ) 
		throws JspException
	{
		//添加属性名
		keys.add( localName );
		//添加属性值
		values.add( value );
	}
}