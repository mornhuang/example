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
public class AddItemAction
{
	private String name;
	private String desc;
	private String remark;
	private double initPrice;
	private int avail;
	private int kind;
	private String vercode;
	private String tipInfo;
	//依赖注入业务逻辑组件（Session Bean）
	@EJB(beanName="auctionManager")
	private AuctionManager am;
	//处理用户请求的execute方法
	public String proAdd() throws Exception
	{
		//在JSF中访问Session范围的数据
		Map<String , Object> session = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap();
		String ver2 = (String)session.get("rand");
		//强制系统刚生成的随机验证码失效
		session.put("rand" , null);
		Integer userId = (Integer)session.get("userId");
		//如果用户输入的验证码与系统随机产生的验证码相同
		if (vercode.equalsIgnoreCase(ver2))
		{
			//根据用户选择有效时间选项，指定实际的有效时间
			switch(avail)
			{
				case 6 :
					avail = 7;
					break;
				case 7 :
					avail = 30;
					break;
				case 8 :
					avail = 365;
					break;
			}
			//添加物品
			am.addItem(name , desc , remark 
				, initPrice ,avail , kind, userId);
			setTipInfo("物品添加成功！");
			//将收集用户输入信息的表单域清空
			return "succss";
		}
		else
		{
			setTipInfo("验证码不匹配,请重新输入");
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
	//remark属性的setter和getter方法
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	public String getRemark()
	{
		 return this.remark;
	}
	//initPrice属性的setter和getter方法
	public void setInitPrice(double initPrice)
	{
		this.initPrice = initPrice;
	}
	public double getInitPrice()
	{
		 return this.initPrice;
	}
	//avail属性的setter和getter方法
	public void setAvail(int avail)
	{
		this.avail = avail;
	}
	public int getAvail()
	{
		 return this.avail;
	}
	//kind属性的setter和getter方法
	public void setKind(int kind)
	{
		this.kind = kind;
	}
	public int getKind()
	{
		 return this.kind;
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
	public void setTipInfo(String tipInfo)
	{
		this.tipInfo = tipInfo;
	}
	public String getTipInfo()
	{
		 return this.tipInfo;
	}
}