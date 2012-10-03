package cn.itsz.newsim.common;

public abstract class Constants {
	public interface TradeSide {
		public final static String BUY = "BUY";
		public final static String SELL = "SELL";
	}

	public interface OrderSide {
		String BUY = "B";
		String SELL = "S";
	}

	public interface AttributeKey {
		public interface Session {
			String ADMIN = "admin";
			String USER = "user";
			String USERPROFILE = "userProfile";
			String DEFAULT_LOCALE = "org.apache.struts.action.LOCALE";
			String TOKEN = "NSIM_TOKEN";
		}

		public interface Action {
			String RESULT = "RESULT";
		}
	}

	public interface LoginChannel {
		String NSIM = "NSIM";
	}

	public interface OrderStatus {
		String QUEUING = "QUEUING";
		String COMPLETELY_FILLED = "COMPLETELY_FILLED";
		String REJECTED = "REJECTED";
		String CANCELLED = "CANCELLED";
	}

	public interface Forward {
		public interface Common {
			String FAILURE = "failure";
			String FAIL = "fail";
			String SUCCESS = "success";
			String SESSION_TIMEOUT = "sessionTimeout";
		}
	}

	public interface ErrorKey {
		String NSIM_00001 = "NSIM_00001";
		String NSIM_00002 = "NSIM_00002";
		String NSIM_00003 = "NSIM_00003"; //QS return exception
		String NSIM_00004 = "NSIM_00004"; //QS connection error
		String NSIM_00005 = "NSIM_00005"; //order instrCode check error
		String NSIM_00006 = "NSIM_00006"; //order price check error
		String NSIM_00007 = "NSIM_00007"; //order qty check error
		String NSIM_00008 = "NSIM_00008"; //order id no found 
		String NSIM_00009 = "NSIM_00009"; //Stock code no exist
		String NSIM_00010 = "NSIM_00010"; //password input error
		String NSIM_00011 = "NSIM_00011"; //stock code suspend
		String NSIM_00012 = "NSIM_00012"; //Over Trade Time
		String NSIM_00013 = "NSIM_00013"; //Order Status error
		String NSIM_00014 = "NSIM_00014"; //Sell space
		String NSIM_00015 = "NSIM_00015";
		String NSIM_00016 = "NSIM_00016";
		String NSIM_00017 = "NSIM_00017";
		String NSIM_00018 = "NSIM_00018";
		String NSIM_00019 = "NSIM_00019";
		String NSIM_00020 = "NSIM_00020";
		String WEB_051090 = "WEB051090";
		
	}

	public interface Lang {
		final String ENGLISH = "en_US";
		final String SIMPLIFIED_CHINESE = "zh_CN";
		final String TRADITIONAL_CHINESE = "zh_TW";
		final String LANGS = "zh_CN, zh_TW, en_US";
		final String COOKIE_LANG = "COOKIE_LANG";
		final String ENG = "ENG";
		final String BIG5 = "BIG5";
		final String GB = "GB";
	}

	public interface Prefix {
		final String ORDERID_PREFIX = "O";
	}

	public interface QS {
		//Delay Simple Quote
		String S_TYPE_DELAY = "SDSP";
		//Delay Detail Quote
		String S_TYPE_DETAIL_QUOT_DELAY = "SDDP";
		String S_BASIC_MARKET_PRICE ="SRFP";
		String RTQ_NULL_PRICE = "--";
		String QUOT_TYPE = "QUOT";
		String ENQ_TYPE="ENQ";
		String SEQN = "3";
		String SERVICETYPE = "0";
	}

	public interface Status {
		final String NORMAL = "NORMAL";
		final String WARN = "WARN";
		final String FAILED = "FAILED";
		final String SUCCESS = "SUCCESS";
	}
	
	public interface Sync {
		final String SYNC = "S";
		final String ASYNC = "A";
	}
	
	public interface Flag {
		String NEGATIVE = "N";
		String POSITIVE = "Y";
	}

	public interface Common {
		final String CHANNEL_ID = ".ChannelID";
		final String RESPONSE_RESULT = "message";
		final String defaultLocaleAttributeName = "org.apache.struts.action.LOCALE";
		static final String EXCEPTION_KEY = "org.apache.struts.action.EXCEPTION";
		final String RTQ_PROFILE = "RTQAppLoginInfoDTO";
		final String TAIFOOK_SECURITIES = "02";
		final String CHANNEL_CLV = "CHANNEL_CLV";
		final int COOKIE_MAX_AGE = 60 * 60 * 24 * 30;
		final String COOKIE_LANGUAGE = "language";
		final String CLV_NAME = "CLV";
		final String CHANGELANG_PAGE_PARAM = "page";
		final String LOGIN_PAGE = "loginPage";
		final String MAIN_PAGE = "mainPage";
		final String RISK_PAGE = "riskPage";

		final String TEL_HK = "HK_Service_Hotline_Tel";
		final String TEL_MAINLAND = "Mainland_Service_Hotline_Tel";

		final String SESSION_AGENT_ID = "agent_id";

		final String FORM_PARAM_SEPARATOR_CHAR = ",";
		final String CONFIG_PARAM_ALL = "ALL";
		public static String FUND_DEPOSIT_FILEPATH = "Fund_Deposit_FilePath";
	}

	public interface Language {
		public interface PatternA {
			final String ENGLISH = "EN";
			final String CHINESE_SIMPLIFIED = "GB";
			final String CHINESE_TRADITIONAL = "B5";
		}
	}
	
	public interface OrderRemark {
		final String NORMAL = "Normal";
		final String PROGRESS = "Progress";
		final String FAILED = "Failed";
		final String SUCCESS = "Success";
	}
	
	public interface Parameter {
		final String CLIENT_MONEY = "money";
		final String START_TIME = "stime";
		final String END_TIME = "etime";
	}
	
	public interface Channel {
		final String NWEB = "IWEB";
	}
}
