/*
 *  Copyright (c) 2004 Tai Fook Securities Group Limited.
 *  All rights reserved.
 *
 *  This file contains the valuable properties of Tai Fook Securities
 *  Group Limited, embodying substantial creative efforts and confidential
 *  information, ideas and expressions. No part of this file may be
 *  reproduced or distributed in any form or by any means, or stored
 *  in a data base or a retrieval system, without the prior written
 *  permission of Tai Fook Securities Group Limited.
 *
 */
package com.itsz.sht.struts.form;

import java.math.BigDecimal;
import java.util.List;


/**
 * @struts.form name="bocTransferForm"
 * 
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Taifook Securities Groups</p>
 * @author Henry Kwong
 * @version $Id: BOCTransferForm.java,v 1.3 2011/03/08 01:46:40 xli Exp $
 */
public class BOCTransferForm extends ITSZForm {
	private static final long serialVersionUID = 1L;
	private String accountId;
    private BigDecimal payAmount;
    private String transactionDateTime;
    private String locale;
	private String remarks;
	private List fromAccounts;
    private boolean disableFromAccount;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public String getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(String transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public List getFromAccounts() {
		return fromAccounts;
	}
	public void setFromAccounts(List fromAccounts) {
		this.fromAccounts = fromAccounts;
	}
	public boolean isDisableFromAccount() {
		return disableFromAccount;
	}
	public void setDisableFromAccount(boolean disableFromAccount) {
		this.disableFromAccount = disableFromAccount;
	}

}
