package com.itsz.sht.common.user;

import java.util.ArrayList;
import java.util.List;

/**
 * @company : itsz
 * @author:bwzhang
 * @mail: bwzhang@itsz.cn
 * @see: AccountProcess (com.itsz.common.util.AccountProcess)
 * @description :Methods encapsulated as an object for Account separation
 * @review by: Even
 * @start date: 2006-5-16
 * @review date: 2006-6-1
 */

public class UserAccountInfo {
	private List fromAccountList; // ֧�ʻ�

	private List toAccountList; // ���ʻ�

	private List AccountList; // ֤ȯ����

	public UserAccountInfo() {
		this.fromAccountList = new ArrayList();
		this.toAccountList = new ArrayList();
		this.AccountList = new ArrayList();
	}

	public List getFromAccount() {
		return fromAccountList;
	}

	public void setFromAccount(List fromAccountList) {
		this.fromAccountList = fromAccountList;
	}

	public List getToAccount() {
		return toAccountList;
	}

	public void setToAccount(List toAccountList) {
		this.toAccountList = toAccountList;
	}

	public List getAccount() {
		return AccountList;
	}

	public void setAccount(List AccountList) {
		this.AccountList = AccountList;
	}


}