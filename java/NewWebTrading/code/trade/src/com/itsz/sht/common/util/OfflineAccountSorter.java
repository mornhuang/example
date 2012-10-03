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
package com.itsz.sht.common.util;

import java.util.Comparator;

import com.itsz.sht.common.user.AcEnquiryInfo;


public class OfflineAccountSorter implements Comparator {

    public int compare(Object o1, Object o2) {

        int result = 1;

        if (o1 instanceof AcEnquiryInfo 
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
