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
public class ViewItemAction
{
	private int kindId;
	private String kind;
	private List<Item> items;
	//依赖注入业务逻辑组件（Session Bean）
	@EJB(beanName="auctionManager")
	private AuctionManager am;
	//kindId属性的setter和getter方法
	public void setKindId(int kindId)
	{
		this.kindId = kindId;
	}
	public int getKindId()
	{
		//直接获取请求参数
		Map<String , String> request = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getRequestParameterMap();
		return Integer.parseInt(request.get("kindId"));
	}
	//kind属性的setter和getter方法
	public void setKind(String kind)
	{
		this.kind = kind;
	}
	public String getKind()
	{
		return am.getKind(getKindId());
	}
	//items属性的setter和getter方法
	public void setItems(List<Item> items)
	{
		this.items = items;
	}
	public List<Item> getItems()
	{
		return am.getItemsByKind(getKindId());
	}
}