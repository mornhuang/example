package com.itsz.sht.struts.form;

import org.apache.struts.validator.ValidatorForm;

/**
 * $Id: ITSZForm.java,v 1.1 2010/11/23 04:14:17 qli2 Exp $
 * @Project:portal.head
 * @File:ITSZForm.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class ITSZForm extends ValidatorForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2507596502997043859L;
	protected String CLV;
	
	public String getCLV() {
		return CLV;
	}

	public void setCLV(String clv) {
		this.CLV = clv;
	}
	
}
