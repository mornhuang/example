package com.itsz.sht.common.util;

import java.util.Comparator;

import com.itsz.sht.common.user.AcEnquiryInfo;
import com.taifook.mcs.core.beans.msg.AccountListDetail;
import com.taifook.mcs.core.beans.msg.TradingAccountInfo;

/**
 * $Id: OnlineAccountSorter.java,v 1.4 2011/04/22 10:07:26 pbxie Exp $
 * @Project:portal
 * @File:OnlineAccountSorter.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-9-11
 */
public class OnlineAccountSorter  implements Comparator {
    public int compare(Object o1, Object o2) {

        int result = 1;

        if (o1 instanceof AccountListDetail
                && o2 instanceof AccountListDetail) {

        	AccountListDetail t = (AccountListDetail) o1;
            if (t.getAccountId() != null && t.getAccountId().length() >= 2) {

                final String accountPrefix = t.getAccountId().substring(t.getAccountId().length() - 2, t.getAccountId().length());
                if ("30".equals(accountPrefix) || "33".equals(accountPrefix)) {
                    result = -1;
                }
                else {
                    result = 1;
                }
            }
            else {
                result = 1;
            }
        }else if (o1 instanceof TradingAccountInfo
                && o2 instanceof TradingAccountInfo) {

        	TradingAccountInfo t = (TradingAccountInfo) o1;
            if (t.getTradingAccount() != null && t.getTradingAccount().length() >= 2) {

                final String accountPrefix = t.getTradingAccount().substring(t.getTradingAccount().length() - 2, t.getTradingAccount().length());
                if ("30".equals(accountPrefix) || "33".equals(accountPrefix)) {
                    result = -1;
                }
                else {
                    result = 1;
                }
            }
            else {
                result = 1;
            }
        }else if (o1 instanceof AcEnquiryInfo 
                && o2 instanceof AcEnquiryInfo) {

        	AcEnquiryInfo t = (AcEnquiryInfo) o1;
            if (t.getAccountId() != null && t.getAccountId().length() >= 2) {

                final String accountPrefix = t.getAccountId().substring(t.getAccountId().length() - 2, t.getAccountId().length());
                if ("00".equals(accountPrefix) || "22".equals(accountPrefix)) {
                    result = -1;
                }
                else {
                    result = 1;
                }
            }
            else {
                result = 1;
            }
        }
        return result;
    }

    public boolean equals(Object obj) {
        return false;
    }
    
}
