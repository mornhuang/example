package cn.itsz.newsim.dto.request;

import cn.itsz.newsim.dto.BaseDTO;

public class SearchRequest extends BaseDTO {
	private String keyword;
	private Integer pageNo = 1;
	private Integer pageSize = 5;

	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
