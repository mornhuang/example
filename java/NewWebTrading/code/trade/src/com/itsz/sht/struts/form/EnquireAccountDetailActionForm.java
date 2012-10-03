//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;


/** 
 * MyEclipse Struts
 * Creation date: 04-01-2005
 * 
 * XDoclet definition:
 * @struts:form name="EnquireAccountDetailActionForm"
 */
public class EnquireAccountDetailActionForm extends ITSZForm {

	// --------------------------------------------------------- Instance Variables

	/** accountID property */
	private String accountID;

	/** marketCode property */
	//private String marketCode;

	// --------------------------------------------------------- Methods

	
	/** 
	 * Method reset
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {

	}

	/** 
	 * Returns the accountID.
	 * @return String
	 */
	
	public String getAccountID() {
		return accountID;
	}

	/**
	 * Set the accountID.
	 * @param accountID The accountID to set
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	/** 
	 * Returns the marketCode.
	 * @return String
	 *//**
	public String getMarketCode() {
		return marketCode;
	}

	/** 
	 * Set the marketCode.
	 * @param marketCode The marketCode to set
	 *//**
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
**/
}