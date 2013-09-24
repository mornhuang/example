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
public class AddBidAction
{
	//封装请求参数的属性
	private int itemId;
	private double bidPrice;
	private double maxPrice;
	private String vercode;
	private String tipInfo;
	//依赖注入业务逻辑组件（Session Bean）
	@EJB(beanName="auctionManager")
	private AuctionManager am;
	//处理用户竞价
	public String bidPro() throws Exception
	{
		//在JSF中访问Session范围的数据
		Map<String , Object> session = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap();
			//取出Session中的userId和刚刚生成的随机验证码
		Integer userId = (Integer)session.get("userId");
		String ver2 = (String)session.get("rand");
		session.put("rand" , null);
		//如果用户输入的验证码和Session中的随机验证码相同
		if (vercode.equalsIgnoreCase(ver2))
		{
			if(bidPrice <= getMaxPrice())
			{
				setTipInfo("您输入的竞价必须高于当前最高价！");
				return "input";
			}
			am.addBid(getItemId() , bidPrice ,userId);  
			setTipInfo("竞价成功！");
			return "success";
		}
		else
		{
			setTipInfo("验证码不匹配,请重新输入");
			return "input";
		}
	}
	//itemId的setter和getter方法
	public void setItemId(int itemId)
	{
		this.itemId = itemId;
	}
	public int getItemId()
	{
		Map<String , String> request = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getRequestParameterMap();
		return Integer.parseInt(request.get("itemId"));
	}
	//bidPrice属性的setter和getter方法
	public void setBidPrice(double bidPrice)
	{
		this.bidPrice = bidPrice;
	}
	public double getBidPrice()
	{
		return this.bidPrice;
	}
	//maxPrice的setter和getter方法
	public void setMaxPrice(double maxPrice)
	{
		this.maxPrice = maxPrice;
	}
	public double getMaxPrice()
	{
		Map<String , String> request = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getRequestParameterMap();
		return Double.parseDouble(request.get("maxPrice"));
	}
	//vercode的setter和getter方法
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