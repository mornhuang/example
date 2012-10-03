package cn.itsz.newsim.dao.hibernate.model;

public class ParameterModel {

	private String pname;//参数名称
	private String pvalue;//参数值
	private String pdesc;//参数描述
	
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
	
	
}
