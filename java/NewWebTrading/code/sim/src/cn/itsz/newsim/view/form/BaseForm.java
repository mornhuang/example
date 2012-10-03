package cn.itsz.newsim.view.form;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.struts.action.ActionForm;

public class BaseForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String token;
	private String CLV;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getCLV() {
		return CLV;
	}

	public void setCLV(String cLV) {
		CLV = cLV;
	}
	
}
