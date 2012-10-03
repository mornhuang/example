package com.itsz.sht.common.model.response;

import com.itsz.sht.common.model.AbstractResponseModel;
import com.itsz.sht.struts.eipo.dao.EIPOMasterEntry;

/**
 * $Id: EIPOSubResponseDetailModel.java,v 1.2 2011/05/06 01:31:36 lpchen Exp $
 * 
 * @Project:NewWebTrading
 * @File:EIPOSubResponseDetailModel.java
 * @Description:
 * @Author:Arthur Chen
 * @Date:2011-4-16
 */
public class EIPOSubResponseDetailModel extends AbstractResponseModel {

	private EIPOMasterEntry masterEntry;

	public EIPOMasterEntry getMasterEntry() {
		return masterEntry;
	}

	public void setMasterEntry(EIPOMasterEntry masterEntry) {
		this.masterEntry = masterEntry;
	}

}
