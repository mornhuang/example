package com.itsz.sht.service.ccis;

import java.util.Calendar;

import org.apache.commons.collections.Predicate;

import com.taifook.mtss.ccis.webservice.impl.AccModel;
import com.taifook.mtss.ccis.webservice.impl.AllAccModel;

public class CCISAccountPredicate {
	/**
     * active account return true
     */
    public static final Predicate FLT_AC_ACTIVE = new Predicate() {
        public boolean evaluate(Object arg0) {
            return CCISAccountConstants.ACStatus.NORMAL.equals(getAcStatus(arg0));
        }
    };
    
    /**
     * stock account
     */
    public static final Predicate FLT_AC_STOCK = new Predicate() {
        public boolean evaluate(Object arg0) {
            return CCISAccountConstants.ACTypeDetail.SECURITIES
                    .equals(getSecPrefix(getAcCode(arg0)));
        }
    };

    /**
     * futures account
     */
    public static final Predicate FLT_AC_FUTURES = new Predicate() {
        public boolean evaluate(Object arg0) {
            return CCISAccountConstants.ACTypeDetail.FUTURES.equals(getSecPrefix(getAcCode(arg0)));
        }
    };
    /**
     * stock or futures account
     */
    public static final Predicate FLT_AC_STOCK_OR_FUTURES = new Predicate() {
        public boolean evaluate(Object arg0) {
            String prefix = getSecPrefix(getAcCode(arg0));
            
            return CCISAccountConstants.ACTypeDetail.SECURITIES.equals(prefix)
            	|| CCISAccountConstants.ACTypeDetail.FUTURES.equals(prefix);
        }
    };
    /**
     * active and closed in 2 months accounts
     */
    public static final Predicate FLT_AC_ACTIVE_OR_IA_IN2M = new Predicate() {
        public boolean evaluate(Object arg0) {
            String acStatus = getAcStatus(arg0);

            if (arg0 instanceof AccModel ) {
            	return false ;
            }else {

            	Calendar acCloseDate = ((AllAccModel) arg0).getClosDate();

            	Calendar c = Calendar.getInstance();
			
            	c.add(Calendar.MONTH, -2);
            	
            	if(CCISAccountConstants.ACStatus.CLOSED.equals(acStatus) && acCloseDate.before(c)){
            		return false;
            	}else {
            		return true;
            	}

            }

        }
    };
    /**
	 * get first char from account code, "01-" for futrues, "02-" for stock
	 * 
	 * @param acCode
	 * @return
	 */
    private static final String getSecPrefix(String acCode) {
        return acCode.substring(0, 3);
    }
    /**
     * get account code from a object
     * 
     * @param obj
     * @return
     */
    private static final String getAcCode(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof AccModel) { 
        	return ((AccModel) obj).getAcStmtId();
        } else if (obj instanceof AllAccModel) {
        	return ((AllAccModel)obj).getAcStmtId();
        } else {
        	return null;
        }        
    }

    private static final String getAcStatus(Object obj) {
    	if (obj instanceof AccModel) {
    		return ((AccModel) obj).getAcStat();
    	} else if (obj instanceof AllAccModel) {
    		return ((AllAccModel) obj).getAcStat();
    	} else {
    		return null;
    	}
    }
}
