package com.itsz.sht.common.model.response;

import java.util.Locale;

import org.apache.struts.util.MessageResources;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.struts.eipo.dao.EIPOMasterEntry;

public class EIPOMasterDetailResponseModel extends AbstractResponseModel{
	
	private EIPOMasterEntry masterEntry;
	private Locale locale;
	private MessageResources mr;
	public EIPOMasterEntry getMasterEntry() {
		return masterEntry;
	}
	public void setMasterEntry(EIPOMasterEntry masterEntry) {
		this.masterEntry = masterEntry;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public MessageResources getMr() {
		return mr;
	}
	public void setMr(MessageResources mr) {
		this.mr = mr;
	}
}
