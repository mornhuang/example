package cn.itsz.newsim.view.form;


public class ParameterForm extends BaseForm {
	
	private static final long serialVersionUID = 1L;
	
	private String pname;// 参数名称
	private String pvalue;// 参数值
	private String pdesc;// 参数描述
	private int pageNo= 1;
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPvalue() {
		return pvalue;
	}
	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	
}
