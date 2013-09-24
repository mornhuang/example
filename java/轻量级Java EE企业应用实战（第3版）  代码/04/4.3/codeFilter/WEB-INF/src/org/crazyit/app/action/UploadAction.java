package org.crazyit.app.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.File;
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
 
public class UploadAction extends ActionSupport
{
	//封装文件标题请求参数的属性
	private String title;
	//封装上传文件域的属性
	private File upload;
	//封装上传文件类型的属性
	private String uploadContentType;
	//封装上传文件名的属性
	private String uploadFileName;
	//直接在struts.xml文件中配置的属性
	private String savePath;
	//定义该Action允许上传的文件类型
	private String allowTypes;
	//allowTypes属性的setter和getter方法
	public String getAllowTypes()
	{
		return allowTypes;
	}
	public void setAllowTypes(String allowTypes)
	{
		this.allowTypes = allowTypes;
	}

	//接受struts.xml文件配置值的方法
	public void setSavePath(String value)
	{
		this.savePath = value;
	}
	//返回上传文件的保存位置
	private String getSavePath() throws Exception 
	{
		return ServletActionContext.getServletContext()
			.getRealPath(savePath);
	}

	//文件标题的setter和getter方法
	public void setTitle(String title) 
	{
		this.title = title; 
	}
	public String getTitle()
	{
		return (this.title); 
	}

	//上传文件对应文件内容的setter和getter方法
	public void setUpload(File upload) 
	{
		this.upload = upload; 
	}
	public File getUpload() 
	{
		return (this.upload); 
	}

	//上传文件的文件类型的setter和getter方法
	public void setUploadContentType(String uploadContentType) 
	{
		this.uploadContentType = uploadContentType; 
	}
	public String getUploadContentType()
	{
		return (this.uploadContentType); 
	}

	//上传文件的文件名的setter和getter方法
	public void setUploadFileName(String uploadFileName) 
	{
		this.uploadFileName = uploadFileName; 
	}
	public String getUploadFileName() 
	{
		return (this.uploadFileName); 
	}
	
	@Override
	public String execute() throws Exception
	{
		//以服务器的文件保存地址和原文件名建立上传文件输出流
		FileOutputStream fos = new FileOutputStream(getSavePath()
			+ "\\" + getUploadFileName());
		FileInputStream fis = new FileInputStream(getUpload());
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0)
		{
			fos.write(buffer , 0 , len);
		}
		fos.close();
		return SUCCESS;
	}

	/**
	 * 过滤文件类型
	 * @param types 系统所有允许上传的文件类型
	 * @return 如果上传文件的文件类型允许上传，
	 *		 返回null，否则返回error字符串
	 */
	public String filterTypes(String[] types)
	{
		//获取希望上传的文件类型
		String fileType = getUploadContentType();
		for (String type : types)
		{
			if (type.equals(fileType))
			{
				return null;
			}
		}
		return ERROR;
	}

//执行输入校验
public void validate()
{
	//将允许上传文件类型的字符串以英文逗号（,）
	//分解成字符串数组从而判断当前文件类型是否允许上传
	String filterResult = filterType(getAllowTypes().split(","));
	//如果当前文件类型不允许上传
	if (filterResult != null)
	{
		//添加FieldError
		addFieldError("upload" , "您要上传的文件类型不正确！");
	}
}
}