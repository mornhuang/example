package cn.itsz.newsim.dto.response;

import java.util.Collection;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.response.entity.TradeInfo;

public class TradeListResponse extends ResultMessage {
	private Collection<TradeInfo> tradeListInfos;

	public Collection<TradeInfo> getTradeListInfos() {
		return tradeListInfos;
	}

	public void setTradeListInfos(Collection<TradeInfo> tradeListInfos) {
		this.tradeListInfos = tradeListInfos;
	}
	private int pageSize;
	private int pageNo;
	private int prevPage;
	private int nextPage;
	private int currentPage;
	private int pageAmount;
	private int listSize;
	private int totalPage;
	
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
}
