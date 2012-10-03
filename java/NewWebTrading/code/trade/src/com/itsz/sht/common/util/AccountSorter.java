package com.itsz.sht.common.util;

import java.util.Comparator;

import com.itsz.sht.common.user.AcEnquiryInfo;
import com.taifook.mcs.core.beans.msg.AccountDetail;


public class AccountSorter implements Comparator {
	 public int compare(Object o1, Object o2) {

	        int result = -1;

	        if (o1 instanceof AccountDetail 
	                && o2 instanceof AccountDetail) {

	        	AccountDetail t = (AccountDetail) o1;
	        	AccountDetail t2 = (AccountDetail) o2;
	            if (t.getAccountId() != null && t.getAccountId().length() >= 2) {
	            	String t1p=t.getAccountId().substring(3, t.getAccountId().length() - 3);
	            	String t2p=t2.getAccountId().substring(3, t.getAccountId().length() - 3);
	               // final String accountPrefix = t.getAccountId().substring(t.getAccountId().length() - 2, t.getAccountId().length());
	                long t1l=Long.parseLong(t1p);
	                long t2l=Long.parseLong(t2p);
	                if (t1l<=t2l) {
	                    result = 1;
	                }
	                else {
	                    result = -1;
	                }
	            }
	            else {
	                result = -1;
	            }
	            /**
	            if (MCSConstants.AccountType.CASH_OFFLINE.equals(t.getAccountType())) {
	                result = -1;
	            }
	            else if (MCSConstants.AccountType.MARGIN_OFFLINE.equals(t.getAccountType())) {
	                result = -1;
	            }
	            else {
	                result = 1;
	            }
	            */
	        }
	        return result;
	    }

	    public boolean equals(Object obj) {
	        return false;
	    }

}
