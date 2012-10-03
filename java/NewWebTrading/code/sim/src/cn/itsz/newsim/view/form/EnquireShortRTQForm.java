package cn.itsz.newsim.view.form;

/**
 * $Id: EnquireShortRTQForm.java,v 1.1 2011/03/04 09:47:34 zxfan Exp $
 * @Project:portal
 * @File:EnquireShortRTQForm.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-4-13
 */
public class EnquireShortRTQForm extends BaseForm {
	private static final long serialVersionUID = 241893578022434542L;
	private String instrCode;
	private String quoteType;
	
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getQuoteType() {
		return quoteType;
	}
	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
}
