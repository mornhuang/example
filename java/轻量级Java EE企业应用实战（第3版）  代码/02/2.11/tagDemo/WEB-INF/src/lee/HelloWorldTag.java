package lee;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;
import java.io.*;

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
public class HelloWorldTag extends SimpleTagSupport
{
	//重写doTag方法，该方法在标签结束生成页面内容
	public void doTag()throws JspException,
		IOException
	{
		//获取页面输出流，并输出字符串
		getJspContext().getOut().write("Hello World "
			+ new java.util.Date());
	}
}
