package org.crazyit.auction.jsf;

import java.util.*;
import javax.ejb.*;
import javax.faces.context.FacesContext;

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
public class ViewBidAction
{
	private List<Bid> bids;
	//依赖注入业务逻辑组件（Session Bean）
	@EJB(beanName="auctionManager")
	private AuctionManager am;
	//bids属性的setter和getter方法
	public void setBids(List<Bid> bids)
	{
		this.bids = bids;
	}
	public List<Bid> getBids()
	{
		//在JSF中访问Session范围的数据
		Map<String , Object> session = FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.getSessionMap();
		Integer userId = (Integer)session.get("userId");
		//调用业务逻辑方法初始化failItems属性
		return am.getBidByUser(userId);
	}
}