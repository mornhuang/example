package com.itsz.sht.struts.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.itsz.sht.common.DateHelper;
import com.taifook.mcs.core.beans.msg.AccountDetail;

public class OrderTransactionHistoryForm extends ITSZForm {
	
	public static int LAST_DAY = 8;
	private List periodList;
	private List befDateList;
	private List endDateList;
	private List<AccountDetail> accountDetailCol;
	public List getAccountDetailCol() {
		return accountDetailCol;
	}

	public void setAccountDetailCol(List accountDetailCol) {
		this.accountDetailCol = accountDetailCol;
	}

	public List getPeriodList() {
		return periodList;
	}

	public void setPeriodList(List periodList) {
		this.periodList = periodList;
	}

	public List getBefDateList() {
		return befDateList;
	}

	public void setBefDateList(List befDateList) {
		this.befDateList = befDateList;
	}

	public List getEndDateList() {
		return endDateList;
	}

	public void setEndDateList(List endDateList) {
		this.endDateList = endDateList;
	}

	public OrderTransactionHistoryForm() {
		loadPeriods();
		this.befDateList = DateHelper.getBeforeOneWeekDate(LAST_DAY);
		this.endDateList = DateHelper.getBeforeOneWeekDate(LAST_DAY);
    }
	
	private void loadPeriods() {
		periodList = new ArrayList();
		for (int i = 1; i <=LAST_DAY-1; i++) {
			periodList.add(i);
		}
	}
}
