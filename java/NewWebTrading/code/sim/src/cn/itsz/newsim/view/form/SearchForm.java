package cn.itsz.newsim.view.form;

public class SearchForm extends BaseForm {
	private String keyword;
	private Integer pageNo = 1;

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
	
}
