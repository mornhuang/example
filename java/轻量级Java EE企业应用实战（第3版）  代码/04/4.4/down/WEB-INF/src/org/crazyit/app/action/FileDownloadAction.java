package org.crazyit.app.action;

import java.io.InputStream;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.*;

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
public class FileDownloadAction
	extends ActionSupport 
{
	//该属性可以在配置文件中动态指定该属性值
	private String inputPath;
	//依赖注入该属性值的setter方法
	public void setInputPath(String value)
	{
		inputPath = value;
	}
	/*
	定义一个返回InputStream的方法，
	该方法将作为被下载文件的入口，
	且需要配置stream类型结果时指定inputName参数，
	inputName参数的值就是方法去掉get前缀、首字母小写的字符串
	*/
	public InputStream getTargetFile() throws Exception 
	{
		//ServletContext提供getResourceAsStream()方法
		//返回指定文件对应的输入流 
		return ServletActionContext.getServletContext()
			.getResourceAsStream(inputPath);
	}
}
