package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.taifook.mcs.core.beans.msg.TradeListResponse;

/**
 * $Id: TradeListResponseModel.java,v 1.2 2011/01/05 09:32:37 kyzou Exp $
 * @Project:portal
 * @File:TradeListResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-6-26
 */
public class TradeListResponseModel extends AbstractResponseModel {
	private TradeListResponse response;
	private int pageSize;
	private int pageNo;
	private int prevPage;
	private int nextPage;
	private int currentPage;
	private int pageAmount;
	private int listSize;
	private int totalPage;

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

	public TradeListResponse getResponse() {
		return response;
	}

	public void setResponse(TradeListResponse response) {
		this.response = response;
	}
}
