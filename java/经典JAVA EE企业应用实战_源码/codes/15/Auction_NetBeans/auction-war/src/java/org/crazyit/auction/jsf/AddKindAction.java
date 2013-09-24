package org.crazyit.auction.jsf;

import java.util.*;
import javax.ejb.*;
import javax.faces.context.FacesContext;

import org.crazyit.auction.service.AuctionManager;
import org.crazyit.auction.exception.AuctionException;

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
public class AddKindAction
{
	private String name;
	private String desc;
	private String vercode;
	private String errInfo;
	//依赖注入业务逻辑组件（Session Bean）
	@EJB(beanName="auctionManager")
	private AuctionManager am;

	public String proAdd()throws Exception
	{
		//在JSF中访问Session范围的数据
		Map<String , Object> session = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap();
		String ver2 = (String)session.get("rand");
		session.put("rand" , null);
		if (vercode.equalsIgnoreCase(ver2))
		{				
			am.addKind(name , desc);
			setErrInfo("种类添加成功！");
			return "success";
		}
		else
		{
			setErrInfo("验证码不匹配,请重新输入");
			return "input";
		}
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
	//desc属性的setter和getter方法
	public void setDesc(String desc)
	{
		this.desc = desc;
	}
	public String getDesc()
	{
		 return this.desc;
	}
	//vercode属性的setter和getter方法
	public void setVercode(String vercode)
	{
		this.vercode = vercode;
	}
	public String getVercode()
	{
		 return this.vercode;
	}
	//errInfo的setter和getter方法
	public void setErrInfo(String errInfo)
	{
		this.errInfo = errInfo;
	}
	public String getErrInfo()
	{
		 return this.errInfo;
	}
}