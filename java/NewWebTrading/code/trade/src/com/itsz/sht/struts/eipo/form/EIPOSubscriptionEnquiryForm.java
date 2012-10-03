package com.itsz.sht.struts.eipo.form;

import java.util.List;

import com.itsz.sht.struts.form.ITSZForm;

public class EIPOSubscriptionEnquiryForm extends ITSZForm {

	private static final long serialVersionUID = 1L;

	private int ipoSize;
	private List ipoList;


	/**
	 * @return the ipoSize
	 */
	public int getIpoSize() {
		return ipoSize;
	}
	/**
	 * @param ipoSize the ipoSize to set
	 */
	public void setIpoSize(int ipoSize) {
		this.ipoSize = ipoSize;
	}
	/**
	 * @return the ipoList
	 */
	public List getIpoList() {
		return ipoList;
	}
	/**
	 * @param ipoList the ipoList to set
	 */
	public void setIpoList(List ipoList) {
		this.ipoList = ipoList;
	}

	
}
