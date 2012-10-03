package com.itsz.sht.common.model.common.request;

import java.util.Date;

import com.itsz.sht.common.model.common.AbstractRequestModel;

public class ListUserPaymentByManualRequestModel extends AbstractRequestModel{
	private Date startTime;
	private Date endTime;
	private int pageNumber;
	private int pageSize;
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
