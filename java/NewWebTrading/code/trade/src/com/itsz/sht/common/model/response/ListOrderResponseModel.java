package com.itsz.sht.common.model.response;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.OrderFilteringResponse;

/**
 * $Id: ListOrderResponseModel.java,v 1.4 2010/12/31 08:53:56 kyzou Exp $
 * @Project:portal
 * @File:ListOrderResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-8
 */
public class ListOrderResponseModel extends AbstractResponseModel {
	private BigDecimal mos;
	private String accountId;
	private OrderFilteringResponse orderFilteringResponse;
	private int listSize;
	private int totalPage;
	private Date updateTime;
	private int pageSize;
	private int pageNo;
	private int prevPage;
	private int nextPage;
	
	public OrderFilteringResponse getOrderFilteringResponse() {
		return orderFilteringResponse;
	}
	public void setOrderFilteringResponse(
			OrderFilteringResponse orderFilteringResponse) {
		this.orderFilteringResponse = orderFilteringResponse;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	private int currentPage;
	private int pageAmount;
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageAmount() {
		return pageAmount;
	}
	public void setPageAmount(int pageAmount) {
		this.pageAmount = pageAmount;
	}
	public BigDecimal getMos() {
		return mos;
	}
	public void setMos(BigDecimal mos) {
		this.mos = mos;
	}
	public int getListSize() {
		return listSize;
	}
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
}
