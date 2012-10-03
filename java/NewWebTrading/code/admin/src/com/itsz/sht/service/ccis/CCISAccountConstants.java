package com.itsz.sht.service.ccis;

public class CCISAccountConstants {
	public static class ACTypeDetail {
        public static final String FUTURES = "01-";
        public static final String SECURITIES = "02-";
        public static final String ONLINE_MARGIN = "-33";
        public static final String ONLINE_CASH = "-30";
        public static final String OFFLINE_MARGIN = "-00";
        public static final String OFFLINE_CASH = "-22";
    }

    public static class ACType {
        public static final String FUTURES = "FUTURES";
        public static final String SECURITIES_ONLINE = "SECURITIES_ONLINE";
        public static final String SECURITIES_OFFLINE = "SECURITIES_OFFLINE";
        public static final String ACTYPE_ALL = "ALL";
    }
    
    public static class ACStatus {
        public static final String ACTIVE = "1";
        public static final String CLOSED = "CLOSED";       
        public static final String NORMAL = "NORMAL";
    }
}
