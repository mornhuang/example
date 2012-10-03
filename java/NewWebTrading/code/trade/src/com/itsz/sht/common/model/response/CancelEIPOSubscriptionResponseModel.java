package com.itsz.sht.common.model.response;

import java.util.Locale;

import org.apache.struts.util.MessageResources;

import com.itsz.eipo.webservice.IpoSubscriptionDto;
import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.struts.eipo.dao.EIPOMasterEntry;
import com.itsz.sht.struts.eipo.dao.EIPOSubscriptionDto;

public class CancelEIPOSubscriptionResponseModel extends AbstractResponseModel{
	
	private EIPOMasterEntry masterEntry;
	private EIPOSubscriptionDto eipoSubscriptionDto;
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
	public EIPOSubscriptionDto getEipoSubscriptionDto() {
		return eipoSubscriptionDto;
	}
	public void setEipoSubscriptionDto(EIPOSubscriptionDto eipoSubscriptionDto) {
		this.eipoSubscriptionDto = eipoSubscriptionDto;
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
