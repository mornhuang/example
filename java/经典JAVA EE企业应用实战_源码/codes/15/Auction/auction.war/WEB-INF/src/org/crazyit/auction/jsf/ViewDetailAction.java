package org.crazyit.auction.jsf;

import java.util.*;
import javax.ejb.*;

import javax.faces.context.*;

import org.crazyit.auction.service.AuctionManager;
import org.crazyit.auction.exception.AuctionException;
import org.crazyit.auction.model.*;

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
public class ViewDetailAction
{
	//封装用户请求参数的属性
	private int itemId;
	private Item item;
	//依赖注入业务逻辑组件（Session Bean）
	@EJB(beanName="auctionManager")
	private AuctionManager am;
	//itemId属性的setter和getter方法
	public void setItemId(int itemId)
	{
		this.itemId = itemId;
	}
	public int getItemId()
	{
		//获取请求参数
		Map<String , String> request = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getRequestParameterMap();
		return Integer.parseInt(request.get("itemId"));
	}
	//item属性的setter和getter方法
	public void setItem(Item item)
	{
		this.item = item;
	}
	public Item getItem()
	{
		return am.getItem(getItemId());
	}
}