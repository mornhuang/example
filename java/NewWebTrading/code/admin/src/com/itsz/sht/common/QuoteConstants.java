package com.itsz.sht.common;

import java.util.Map;


public class QuoteConstants {
	
    public static class AUDIT_TRAIL {
        public final static String PERIOD_AM = "am";
        public final static String PERIOD_PM = "pm";
        public final static String PERIOD_ALL = "all";
        public final static String ACTION_START = "start";
        public final static String ACTION_END = "end" ;
        public final static String HONGKONGSTOCK = "hkstock";
        public final static String ISHARESTOCK = "isharestock";        
        
        public static String[] ISHARESTOCKCODE = new String[]{"04362","04363"} ;
        public static final String AUDIT_FEE = "FEE" , AUDIT_NON = "NON" ;
        public static String HKSTOCKAUDIT = AUDIT_NON ;
        public static String ISHARESTOCKAUDIT = AUDIT_FEE ;
    }
    
    public static class TRADING_DAY_TYPE{
        public final static String
            FULL_TRADING = "0",
            NON_TRADING  = "1",
            HALF_TRADING = "2",
            NON_SETTING = "9";
        public static String SEC_TRADING_TYPE = NON_TRADING ;
        public static String FUT_TRADING_TYPE = NON_TRADING ;
        public static String INITIAL_TRADING_TYPE = NON_TRADING ;
    }
    
    public static class TRADING_STATUS{
        public static final String TRADING_STATUS_TRUE = "A" ;
        public static final String TRADING_STATUS_FALSE = "I" ;
        public static String STOCK_TRADING_STATUS = TRADING_STATUS_FALSE;
        public static String FUTURES_INDEX_TRADING_STATUS = TRADING_STATUS_FALSE;
        
    }    
}