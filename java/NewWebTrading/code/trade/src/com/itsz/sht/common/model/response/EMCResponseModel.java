package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: EMCResponseModel.java,v 1.2 2011/03/10 09:45:40 xlliu Exp $
 * @Project:NewWebTrading
 * @File:EMCResponseModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2011-1-26
 */
public class EMCResponseModel extends AbstractResponseModel {
	private int msgCounts;
	private String emcURL;

	public String getEmcURL() {
		return emcURL;
	}

	public void setEmcURL(String emcURL) {
		this.emcURL = emcURL;
	}

	public int getMsgCounts() {
		return msgCounts;
	}

	public void setMsgCounts(int msgCounts) {
		this.msgCounts = msgCounts;
	}
	
}
