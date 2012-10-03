package com.itsz.sht.common;






public class UserProfileConstants {


    public static class Config {
        public static  String USER_PROFILE_CONFIG_FILE = "mgt-config.xml";
        public static  String MAX_SEARCH_RECORD = "max-search-record";
    }

    public static final int TF_AC_LENGTH_MIN = 5;
    public static final int TF_AC_LENGTH_MAX = 7;
    public static final int TF_AC_LENGTH = TF_AC_LENGTH_MAX;

    public static class Mis {
        public static class ACType {
            public static String PERSONAL_AC    = "1";
            public static String JOIN_AC        = "2";
            public static String COMPANY_AC     = "3";
        }

        public static class IndStaff {
            public static String NO             = "0";
            public static String YES            = "1";
        }

        public static class  Marital {
            public static String SINGLE         = "1";
            public static String MARRIED        = "2";
        }


        public static class  Sex {
            public static String Male           = "1";
            public static String Female         = "2";
        }

        public static class  Title {
            public static String MR             = "1";
            public static String MISS           = "2";
            public static String MRS            = "3";
            public static String DR             = "5";
        }


        public static class DocType {
            public static String ID_CARD     = "01";
            public static String PASSPORT    = "02";
            public static String BUS_REG     = "03";
            public static String CERT_INCORP = "04";
        }
    }

    public static class Occup {
        public static String OTHERS = "OTHERS";
    }


    public static class IDType {
        public static String ID_CARD = "ID CARD";
        public static String PASSPORT = "PASSPORT";
        public static String BUS_REG = "BUSINESS REGISTRATION";
        public static String CERT_INCORP = "CERT. INCORPORATION";
    }

    public static class Sex {
        public static String MALE     = "M";
        public static String FEMALE    = "F";
    }

    public static class Marital {
        public static String SINGLE     = "S";
        public static String DIVORCE    = "D";
        public static String MARRIED    = "M";
        public static String WIDOW      = "W";
    }

    public static class IndNewsMsg {
        public static String YES        = "Y";
        public static String NO         = "N";
    }

    public static class PreferLang {
        public static String ENGLISH                = "EN";
        public static String TRADITIONAL_CHINESE    = "B5";
        public static String SIMPLIFIED_CHINESE     = "GB";
    }


    public static class Staff {
        public static String YES= "Y";
        public static String NO = "N";
    }

    public static class Salutation {
        public static String MR    = "MR";
        public static String MS    = "MS";
        public static String MISS  = "MISS";
        public static String MRS   = "MRS";
        public static String DR    = "DR";
    }

    public static class PortalType {
        public static String USER = "es";
        public static String MANAGEMENT = "mgt";
    }

    public static class UsrType {
        public static String TF = "TF";
        public static String PUBLIC = "PUBLIC";
    }

    public static class BrFirm {
        //public static String TF = "TAIFOOK";
    	//Changed for CCIS Integration by gqwu 20061116
    	public static String TF = "TFS";
    }

    public static class SecuritiesACType {
        public static String FUTURES        = "01-";
        public static String SECURITIES     = "02-";
        public static String ONLINE_MARGIN  = "-33";
        public static String ONLINE_CASH    = "-30";
        public static String OFFLINE_MARGIN = "-00";
        public static String OFFLINE_CASH   = "-22";
    }

    public static class RTQType {
        public static String FUTURES        = "FUTURES";
        public static String SECURITIES     = "SECURITIES";
        public static String ALL            = "ALL";
    }

    public static class SMSRegionType {
        public static String HONGKONG       = "HONGKONG";
        public static String MAINLANDCHINA  = "MAINLANDCHINA";
        public static String OVERSEAS       = "OVERSEAS";
    }

    public static class SMSCountryCode {
        public static String HONGKONG       = "852";
        public static String MAINLANDCHINA  = "86";
    }

    public static class MobileProviderCountry{
        public static String HONGKONG       = "HK";
        public static String MAINLANDCHINA  = "CH";
    }

    public static class ACType {
        public static String FUTURES            = "FUTURES";
        public static String SECURITIES_ONLINE  = "SECURITIES_ONLINE";
        public static String SECURITIES_OFFLINE  = "SECURITIES_OFFLINE";
        public static String ACTYPE_ALL = "ALL";
    }

    /* created on 2004-9-27 by jrchen */
    public static class Web{
        /* Enquiry result User ID */
        public static String QUE_RESULT_USRID = "MGT_ENQUIRY_USERID";

        /* Enquiry result login ID */
        public static String QUE_RESULT_LGID  = "MGT_ENQUIRY_LOGINID";

        /* The User's Customer Code and User Type (be put in HashMap) */
        public static String QUE_RST_USRDATA  = "MGT_ENQUIRY_USERDATAMAP";
    }
  //added by bihh 2005-9-12
  public static final String PUBLICE_USER_ID_PRE="PUB";
}
