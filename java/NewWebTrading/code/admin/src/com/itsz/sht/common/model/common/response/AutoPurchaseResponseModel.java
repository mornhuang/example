/**
 * 
 */
package com.itsz.sht.common.model.common.response;

import java.util.List;

import com.itsz.sht.common.model.common.AbstractResponseModel;
import com.itsz.sht.dao.hibernate.model.AutoPurchase;

/**
 * @author cyzeng
 *
 */
public class AutoPurchaseResponseModel extends AbstractResponseModel {
	
	private List<AutoPurchase> autoPurchaseList;

	public List<AutoPurchase> getAutoPurchaseList() {
		return autoPurchaseList;
	}

	public void setAutoPurchaseList(List<AutoPurchase> autoPurchaseList) {
		this.autoPurchaseList = autoPurchaseList;
	}
	
	
}
