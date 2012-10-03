package com.itsz.sht.struts.form;

/**
 * $Id: MarginRationsForm.java,v 1.1 2010/12/07 08:31:06 kyzou Exp $
 * @Project:NewWebTrading
 * @File:MarginRationsForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-7
 */
public class MarginRationsForm extends ITSZForm {
	private static final long serialVersionUID = 1L;
	private String instrumentCode;

	public String getInstrumentCode() {
		return instrumentCode;
	}

	public void setInstrumentCode(String instrumentCode) {
		this.instrumentCode = instrumentCode;
	}
}
