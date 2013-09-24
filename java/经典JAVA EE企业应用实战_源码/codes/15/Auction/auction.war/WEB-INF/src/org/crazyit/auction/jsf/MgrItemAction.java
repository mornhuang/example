package org.crazyit.auction.jsf;

import java.util.*;
import javax.ejb.*;
import javax.faces.context.FacesContext;
import javax.faces.model.*;

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
public class MgrItemAction
{
	private List<Item> items;
	private List<SelectItem> kinds;
	//依赖注入业务逻辑组件（Session Bean）
	@EJB(beanName="auctionManager")
	private AuctionManager am;
	//items属性的setter和getter方法
	public void setItems(List<Item> items)
	{
		this.items = items;
	}
	public List<Item> getItems()
	{
		//在JSF中访问Session范围的数据
		Map<String , Object> session = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap();
		Integer userId = (Integer)session.get("userId");
		return am.getItemsByOwner(userId);
	}
	//kinds属性的setter和getter方法
	public void setKinds(List<SelectItem> kinds)
	{
		this.kinds = kinds;
	}
	public List<SelectItem> getKinds()
	{
		 List<Kind> kinds = am.getAllKind();
		 //将Kind对象的集合转换为SelectItem的集合。
		 List<SelectItem> kindItems = new ArrayList<SelectItem>();
		 for (int i = 0 ; i < kinds.size() ; i++)
		 {
			 kindItems.add(new SelectItem(kinds.get(i).getId().toString()
				 , kinds.get(i).getKindName().toString())); 
		 }
		 return kindItems;
	}
}