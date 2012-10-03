package com.itsz.sht.common.model.response.login;

import com.itsz.sht.common.model.AbstractResponseModel;

/**
 * $Id: RtqModel.java,v 1.1 2010/11/09 03:57:29 kyzou Exp $
 * @Project:portal.head
 * @File:RtqModel.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-24
 */
public class RtqModel extends AbstractResponseModel {

	private String category;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
