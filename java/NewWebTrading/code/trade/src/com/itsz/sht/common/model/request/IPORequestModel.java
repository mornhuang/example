package com.itsz.sht.common.model.request;

import java.util.ArrayList;

import com.itsz.sht.common.model.AbstractRequestModel;
import com.itsz.sht.dto.ipo.IPORecord;

/**
 * $Id: IPORequestModel.java,v 1.2 2010/12/04 09:18:13 kyzou Exp $
 * @Project:NewWebTrading
 * @File:IPORequestModel.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-11-16
 */
public class IPORequestModel extends AbstractRequestModel {
    private String accountId;
    private boolean isAllIPO;
    private ArrayList ipoRecordList;
    private String ipoCode;
    private IPORecord ipoRecord;
    private long ipoMasterId;

    private String password;

    private String toURL;

    private String applyType;
    private String andIPOCode;
    private String andAccount;

    private String[] applyCodeStr;

    private int firstResult;
    private int currPage;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public boolean isAllIPO() {
		return isAllIPO;
	}
	public void setAllIPO(boolean isAllIPO) {
		this.isAllIPO = isAllIPO;
	}
	public ArrayList getIpoRecordList() {
		return ipoRecordList;
	}
	public void setIpoRecordList(ArrayList ipoRecordList) {
		this.ipoRecordList = ipoRecordList;
	}
	public String getIpoCode() {
		return ipoCode;
	}
	public void setIpoCode(String ipoCode) {
		this.ipoCode = ipoCode;
	}
	public IPORecord getIpoRecord() {
		return ipoRecord;
	}
	public void setIpoRecord(IPORecord ipoRecord) {
		this.ipoRecord = ipoRecord;
	}
	public long getIpoMasterId() {
		return ipoMasterId;
	}
	public void setIpoMasterId(long ipoMasterId) {
		this.ipoMasterId = ipoMasterId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToURL() {
		return toURL;
	}
	public void setToURL(String toURL) {
		this.toURL = toURL;
	}
	public String getApplyType() {
		return applyType;
	}
	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}
	public String getAndIPOCode() {
		return andIPOCode;
	}
	public void setAndIPOCode(String andIPOCode) {
		this.andIPOCode = andIPOCode;
	}
	public String getAndAccount() {
		return andAccount;
	}
	public void setAndAccount(String andAccount) {
		this.andAccount = andAccount;
	}
	public String[] getApplyCodeStr() {
		return applyCodeStr;
	}
	public void setApplyCodeStr(String[] applyCodeStr) {
		this.applyCodeStr = applyCodeStr;
	}
	public int getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
}
