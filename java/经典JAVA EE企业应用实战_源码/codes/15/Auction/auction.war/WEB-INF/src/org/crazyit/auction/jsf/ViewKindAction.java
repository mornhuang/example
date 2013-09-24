package org.crazyit.auction.jsf;

import java.util.*;
import javax.ejb.*;

import org.crazyit.auction.service.AuctionManager;
import org.crazyit.auction.exception.AuctionException;
import org.crazyit.auction.model.*;

/**
 * @author  crazyit.auction.H.lee kongcrazyit.auction@163.com
 * @version  1.0
 * <br>Copyright (C), 2005-2008, crazyit.auction.H.Lee
 * <br>This program is protected by copyright laws.
 * <br>Program Name:
 * <br>Date: 
 */
public class ViewKindAction
{
	private List<Kind> kinds;
	//依赖注入业务逻辑组件（Session Bean）
	@EJB(beanName="auctionManager")
	private AuctionManager am;
	//kinds属性的setter和getter方法
	public void setKinds(List<Kind> kinds)
	{
		//调用业务逻辑方法来初始化kinds属性
		this.kinds = am.getAllKind();
	}
	public List<Kind> getKinds()
	{
		 return this.kinds;
	}
}