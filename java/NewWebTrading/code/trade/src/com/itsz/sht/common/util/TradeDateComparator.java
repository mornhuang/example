package com.itsz.sht.common.util;

import java.util.Comparator;
import com.taifook.mcs.core.beans.msg.TradeListInfo;

/**
 * $Id: TradeDateComparator.java,v 1.1 2010/11/09 03:57:30 kyzou Exp $
 * @Project:portal
 * @File:TradeDateComparator.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-7-23
 */
public class TradeDateComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        int result = -1;
        if (o1 instanceof TradeListInfo && o2 instanceof TradeListInfo) {
        	TradeListInfo t1 = (TradeListInfo) o1;
        	TradeListInfo t2 = (TradeListInfo) o2;

            if (t1.getBusinessDate() != null) {
                result = (t1.getBusinessDate().compareTo(t2.getBusinessDate())) * (-1);
            }

            /**
            if (t1.getMcsOrderId() != null) {
                result = (t1.getMcsOrderId().compareTo(t2.getMcsOrderId())) * (-1);
            }
            */
        }
        return result;
    }

    public boolean equals(Object o) {
        boolean result = false;
        return result;
    }

}
