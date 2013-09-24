package org.crazyit.taglib;

import javax.faces.context.FacesContext;
import javax.el.*;
import javax.faces.webapp.ValidatorELTag;
import javax.faces.validator.Validator;
import javax.servlet.jsp.JspException;
import org.crazyit.validator.RegexValidator;

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

public class RegexValidatorTag 
	extends ValidatorELTag 
{
	private static String validatorID = null;
	//为自定义标签定义一个属性
	private String pattern = null;
	public RegexValidatorTag() 
	{
		super();
		if (validatorID == null) 
		{
			validatorID = "regexValidator";
		}
	}
	public void setValidatorID(String validatorID) 
	{
		this.validatorID = validatorID;
	}
	//pattern属性的setter和getter方法
	public void setPattern(String pattern)
	{
		//将pattern字符串中斜线（/）替换成反斜线（\）
		this.pattern = pattern.replace("/" , "\\");
	}
	public String getPattern()
	{
		return this.pattern;
	}
	//ValidatorELTag子类必须重写的方法，
	//该方法用于创建实际执行校验的校验器
	protected Validator createValidator()
		throws JspException 
	{

		FacesContext facesContext = 
			FacesContext.getCurrentInstance();
		RegexValidator result = null;
		//根据已注册的输入校验器ID来创建输入校验器
		if (validatorID != null)
		{
			result = (RegexValidator)facesContext
				.getApplication()
				.createValidator(validatorID);
		}
		result.setPattern(pattern);
		return result;
	}
} 