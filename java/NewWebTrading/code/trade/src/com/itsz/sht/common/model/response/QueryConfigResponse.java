package com.itsz.sht.common.model.response;

import java.util.Collection;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: QueryConfigResponse.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal
 * @File:QueryConfigResponse.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-3-26
 */
public class QueryConfigResponse extends AbstractResponseModel {
	private Collection configs;

	public Collection getConfigs() {
		return configs;
	}

	public void setConfigs(Collection configs) {
		this.configs = configs;
	}
}
