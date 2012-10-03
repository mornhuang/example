package com.itsz.sht.common;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.itsz.sht.admin.parameter.util.MobileAgentManagement;
import com.itsz.sht.common.PropertyConfig;

/**
 * $Id: PortalUtil.java,v 1.4 2011/02/17 03:51:20 kyzou Exp $
 * @Project:portal.head
 * @File:PortalUtil.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-31
 */
public class PortalUtil {
	private static Map AgentDomain=new HashMap();
	private static String AgentServer_Id=null;
	private static int AgentRejId=0;
	private static Log log = LoggerFactory.getInstance().getCommonInfo();
	private static Log log_agent = LogFactory.getLog(Consts.Log.Info.AGENT);
	
	/**
	 * 
	 * @Author:kyzou
	 * @Time:2008-7-11 11:50:13
	 * @param channelType
	 * @param request
	 */	
	public static String filterBrowserType(HttpServletRequest request,String channelType) {
		if(!Consts.Channel.WMT.equals(channelType)){
			return Consts.Wmt.ILLEGALCLIENT.ALLOW_CLIENT;
		}
//		Enumeration<String> headerNames = request.getHeaderNames();
//		while(headerNames.hasMoreElements()){
//			String name  = headerNames.nextElement();
//			log.info("Client Type is ["+name + ": "+request.getHeader(name)+"]");
//		}
		String user_agent = request.getHeader("user-agent");
		if(user_agent==null) return Consts.Wmt.ILLEGALCLIENT.ALLOW_CLIENT;
		user_agent = user_agent.toUpperCase();
        if(MobileAgentManagement.isWhiteList(user_agent)){
        	log_agent.info("The Mobile  is in WhiteList, user_agent:="+user_agent);
			return Consts.Wmt.ILLEGALCLIENT.ALLOW_CLIENT;
		}
		if(MobileAgentManagement.isPcBlackList(user_agent)){
			log_agent.info("No support type,The Mobile is in PcBlackList, user_agent:="+user_agent);
			return Consts.Wmt.ILLEGALCLIENT.PC_CLIENT;
		}
        if(MobileAgentManagement.isBlackList(user_agent)){
        	log_agent.info("No support type,The Mobile is in BlackList, user_agent:="+user_agent);
			return Consts.Wmt.ILLEGALCLIENT.AGENT_BLACK;
		}
        return Consts.Wmt.ILLEGALCLIENT.ALLOW_CLIENT;
	}
	
	public static String getAgent_ClientPcUrl(String lang){
		String url = Consts.Wmt.ILLEGALCLIENT.DEFAULT_PC_CLIENT_URL;
		if(Consts.Global.Language.BIG5.equals(lang)){
			url = PropertyConfig.getProviderName(Consts.Wmt.ILLEGALCLIENT.AGENT_PCCLIENT_TW_URL);
		}
		if(Consts.Global.Language.ENG.equals(lang)){
			url = PropertyConfig.getProviderName(Consts.Wmt.ILLEGALCLIENT.AGENT_PCCLIENT_EN_URL);
		}
		if(Consts.Global.Language.GB.equals(lang)){
			url = PropertyConfig.getProviderName(Consts.Wmt.ILLEGALCLIENT.AGENT_PCCLIENT_CN_URL);
		}		
		if(StringUtils.isBlank(url)){
			log.info("Can't get AGENT_PCCLIENT_URL, Use DEFAULT_PC_CLIENT_URL:"+Consts.Wmt.ILLEGALCLIENT.DEFAULT_PC_CLIENT_URL);
			url = Consts.Wmt.ILLEGALCLIENT.DEFAULT_PC_CLIENT_URL;
		}
		return url;
	}
	
	private static void initAgentDomain(){
		AgentDomain.put("1", "1");
		AgentDomain.put("2", "2");
		AgentDomain.put("3", "3");
		AgentDomain.put("4", "4");
		AgentDomain.put("5", "5");
		AgentDomain.put("6", "6");
		AgentDomain.put("7", "7");
		AgentDomain.put("8", "8");
		AgentDomain.put("9", "9");
		AgentDomain.put("10", "A");
		AgentDomain.put("11", "B");
		AgentDomain.put("12", "C");
		AgentDomain.put("13", "D");
		AgentDomain.put("14", "E");
		AgentDomain.put("15", "F");
		AgentDomain.put("16", "G");
		AgentDomain.put("17", "H");
		AgentDomain.put("18", "I");
		AgentDomain.put("19", "J");
		AgentDomain.put("20", "K");
		AgentDomain.put("21", "L");
		AgentDomain.put("22", "M");
		AgentDomain.put("23", "N");
		AgentDomain.put("24", "O");
		AgentDomain.put("25", "P");
		AgentDomain.put("26", "Q");
		AgentDomain.put("27", "R");
		AgentDomain.put("28", "S");
		AgentDomain.put("29", "T");
		AgentDomain.put("30", "U");
		AgentDomain.put("31", "V");
	}
	
	private static String getAgentServerId(){
		DocumentBuilder builder;
		Document doc;
		Element root;
		DocumentBuilderFactory factory=null;
		String filename = "";
		String server_id = "1";
		try {
			log = LogFactory.getLog(PortalUtil.class);
			if (factory == null) {
				factory = DocumentBuilderFactory.newInstance();
			}
			builder = factory.newDocumentBuilder();
			filename = PortalUtil.class.getResource("/syndatasrvcfg.xml").getFile();
			doc = builder.parse(new File(filename));
			root = doc.getDocumentElement();
			NodeList nodesList = root.getElementsByTagName("socket-config");
			if (nodesList != null) {
				for (int i = 0; i < nodesList.getLength(); i++) {
					Node item = nodesList.item(i);
					server_id = getNode(item,"server_id").getFirstChild().getNodeValue();
				}
			}
		} catch (Exception e) {
			log.error((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"))
					.format(new Date())
					+ "======Processing Context configuration file URL file:"
					+ filename + " fail");
			log.error(e);
			server_id = "1";
		}
		return server_id;
	}
	
	private static Node getNode(Node parentNode, String tagName) {
		NodeList nodelist = parentNode.getChildNodes();
		if (nodelist == null)
			return null;
		for (int i = 0; i < nodelist.getLength(); i++) {
			Node child = nodelist.item(i);
			if (child.getNodeName().equals(tagName))
				return child;
		}
		return null;
	}
	
	public static String getAgentId(HttpServletRequest request){
		String key = (String)request.getSession().getAttribute(Consts.Global.Common.SESSION_AGENT_ID);
		if(StringUtils.isNotBlank(key)){
			return key;
		}
		if(AgentDomain.size()<=0){
			initAgentDomain();
		}
		String AgentDay = (String)AgentDomain.get(String.valueOf((new Date()).getDate()));
		if(AgentServer_Id==null){
			AgentServer_Id = (String)AgentDomain.get(getAgentServerId());
		}
		key = AgentServer_Id+AgentDay+getAgentRejId();
		request.getSession().setAttribute(Consts.Global.Common.SESSION_AGENT_ID, key);
		return key;
	}
	
	private synchronized static String getAgentRejId(){
		AgentRejId++;
		if(AgentRejId>9999){
			AgentRejId=1;
		}
		return StringUtils.leftPad(String.valueOf(AgentRejId),4,"0");
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-5-31 18:25:13
	 * @param channelType
	 */
	public static String getChannelId(String channelType){
		String channelId = "";
		if(StringUtils.isNotBlank(channelType)){
			if(Consts.Channel.NWEB.equals(channelType)){
				channelId = Consts.Channel.Id.NWEB;
			}else if(Consts.Channel.H3G.equals(channelType)){
				channelId = Consts.Channel.Id.H3G;
			}else if(Consts.Channel.STT.equals(channelType)){
				channelId = Consts.Channel.Id.STT;
			}
			else if(Consts.Channel.WMT.equals(channelType)){
				channelId = Consts.Channel.Id.WMT;
			}
		}
		return channelId;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-6-13 9:36:24
	 * @param lang language
	 * @return locale needed
	 */
	public static Locale lang2Locale(String lang) {
		Locale locale = Locale.TRADITIONAL_CHINESE;
		if(StringUtils.isNotBlank(lang)){
			if(Consts.Global.Language.ENG.equals(lang)){
				locale = Locale.US;
			}else if(Consts.Global.Language.GB.equals(lang)){
				locale = Locale.SIMPLIFIED_CHINESE;
			}
		}
		return locale;
	}
	
	public static Locale language2Locale(String lang) {
		Locale locale = Locale.TRADITIONAL_CHINESE;
		if(StringUtils.isNotBlank(lang)){
			if(Consts.Global.Language.PatternC.ENGLISH.equals(lang)){
				locale = Locale.US;
			}else if(Consts.Global.Language.PatternC.CHINESE_SIMPLIFIED.equals(lang)){
				locale = Locale.SIMPLIFIED_CHINESE;
			}
		}
		return locale;
	}
	
	public static Locale language2Locale3g(String lang) {
		Locale locale = Locale.TRADITIONAL_CHINESE;
		if(StringUtils.isNotBlank(lang)){
			if(Consts.Global.Language.PatternC.ENGLISH.equals(lang)){
				locale = Locale.US;
			}
		}
		return locale;
	}
	
	/**
	 * get locale from session
	 * if null get the default value
	 * for 3g channel
	 * @Author:Cimenon Saint
	 * @Time:2008-3-3 10:23:48
	 * @param session
	 * @return
	 */
	public static Locale getLocaleFromSession(HttpSession session) {
		Object locale = session.getAttribute(Globals.LOCALE_KEY);
		return (locale != null) ? (Locale)locale:Consts.Mobile.LocaleHelper.DEFAULT_LOCALE_VALUE;
	}
	
	/**
	 * @Author:Cimenon Saint
	 * @Time:2008-3-7 15:10:14
	 * @param session
	 * @return
	 */
	public static Locale localeManage(HttpSession session) {
		Object loc = session.getAttribute(Globals.LOCALE_KEY);
		Locale locale = Consts.Mobile.LocaleHelper.DEFAULT_LOCALE_VALUE;
		//if null or (not us and not traditional chinese)
		//change into default value::Consts.Mobile.LocaleHelper.DEFAULT_LOCALE_VALUE;
		if(loc == null 
				||(!locale.equals(Locale.US) 
						&& !locale.equals(Locale.TRADITIONAL_CHINESE))){
			locale = Consts.Mobile.LocaleHelper.DEFAULT_LOCALE_VALUE;
			session.setAttribute(Globals.LOCALE_KEY,locale);
		}else{
			locale = (Locale)loc;
		}
		return locale;
	}
	
	/**
	 * From ::: "zh_TW","en_US", "zh_CN"
	 * To ::: "B5", "EN", "GB" -- for eservice only
	 * @Author:Cimenon Saint
	 * @Time:2008-3-3 18:00:12
	 * @param abbrLang
	 * @return
	 */
	public static String coverLang(String abbrLang){
		String language = Consts.Global.Language.PatternA.CHINESE_TRADITIONAL;
		if(Consts.Global.Language.PatternC.CHINESE_SIMPLIFIED.equalsIgnoreCase(abbrLang)){
			language = Consts.Global.Language.PatternA.CHINESE_SIMPLIFIED;
		}else if(Consts.Global.Language.PatternC.ENGLISH.equalsIgnoreCase(abbrLang)){
			language = Consts.Global.Language.PatternA.ENGLISH;
		}
		return language;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2008-3-3 11:51:37
	 * @param request
	 * @return
	 */
	public static String getMobileLanguage(HttpServletRequest request){
		String language = Consts.Mobile.Language.DEFAULT_VALUE;
		if(StringUtils.isNotBlank(request.getParameter(Consts.Mobile.Language.REQUEST_KEY))){
			language = request.getParameter(Consts.Mobile.Language.REQUEST_KEY);
		}
		return language;
	}
	
	/**
	 * 
	 * @Author:jhu
	 * @Time:2007-7-3 11:28:41
	 * @param locale
	 * @return lang
	 */
    public static String locale2lang(Locale locale){
        if(locale==null){
            return "zh_TW";
        }
        if(Locale.US.toString().equals(locale.toString())){
            return "en_US";
        }
        if(Locale.SIMPLIFIED_CHINESE.toString().equals(locale.toString())){
            return "zh_CN";
        }
        return "zh_TW";
    }
    
    /**
     * en = 0, zh_TW = 1, zh_CN = 2
     * @Author:Cimenon Saint
     * @Time:2007-7-2 10:59:45
     * @param locale
     * @return
     */
    public static String getEFinetLanguage(Locale locale) {
        String lang = "0";
        if (locale != null) {
            if ("zh_TW".equals(locale.toString())) {
                lang = "1";
            }
            else if ("zh_CN".equals(locale.toString())) {
                lang = "2";
            }
        }
        return lang;
    }
    
    /**
     * 
     * public static final String TRADITIONAL_CHINESE = "C";
     * public static final String ENGLISH = "CE";
     * public static final String SIMPLIFIED_CHINESE = "CB";    
     * @Author:Cimenon Saint
     * @Time:2007-7-2 10:59:50
     * @param locale
     * @return
     */
    public static String getRTQLanguageCode(Locale locale) {

        String lang = "CE"; //default
        if (locale != null) {
            if ("zh_TW".equals(locale.toString())) {
                return "C";
            }
            else if ("zh_CN".equals(locale.toString())) {
                return "CB";
            }
        }
        return lang;
    }
    
    /**
     * get rtq delay time
     * 1.从数据库中读取
     * 2.如果不能读到则使用默认值
     * @Author:Cimenon Saint
     * @Time:2007-11-29 11:31:59
     * @return
     */
    public static String getRrqDelayTime(){
		String delayTime = null;
		String temp =PropertyConfig.getCommonProperty(Consts.RTQ.SIMPLE_RTQ_DELAY_TIME);
		if(StringUtils.isNotBlank(temp)){
			delayTime = temp;
		}else {
			delayTime = String.valueOf(Consts.Qs.DELAY_TIME);
		}
		return delayTime;
    }
    
    /**
     * get page size value
     * 1.从数据库中读取
     * 2.如果不能读到则使用默认值
     * @Author:Cimenon Saint
     * @Time:2008-5-8 下午02:45:27
     * @return
     */
    public static int getPageSize(){
    	int pageSize = 0;
    	String ps = PropertyConfig.getProviderName(Consts.Order.ListBook.PageSize.NAME);//data from admin portal
		if(StringUtils.isNotBlank(ps)){
			try{
				pageSize = Integer.parseInt(ps);
				if(pageSize <= 0){
					pageSize = Consts.Order.ListBook.PageSize.DEFAULTSIZE;//page size must great than 0; or default data;
				}
			}catch (Exception e) {
				pageSize = Consts.Order.ListBook.PageSize.DEFAULTSIZE;//page size must be digit default data;
			}
		}
    	return pageSize;
    }
    
    /**
     * hongkong ID regulate
     * @Author:Cimenon Saint
     * @Time:2007-7-2 10:01:33
     * @param hkid
     * @return
     */
    public static String regulateHKID(String hkid) {
        String result = null;
        if (hkid != null) {
            StringBuffer sb = new StringBuffer();
            if (hkid.length() > 0) {
                sb.append(hkid.substring(0, hkid.length() - 1));
                if (hkid.length() > 1) {
                    sb.append("(").append(hkid.substring(hkid.length() - 1, hkid.length())).append(")");
                }
            }
            result = sb.toString();
        }
        return result;
    }
    
    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-7-2 10:19:55
     * @param amount
     * @return
     */
    public static String formatAmt(BigDecimal amount) {
        try {
            String pattern = "##,##0.00";
            DecimalFormat declimalFormat = new DecimalFormat(pattern);
            String result=declimalFormat.format(amount.doubleValue());
            if(result.equals("-0.00")) result = "0.00";
            return result;
        } catch (Exception ex) {
            return "0.00";
        }

    }

    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-7-2 10:21:38
     * @param qty
     * @return
     */
    public static String formatQty(int qty) {
        try {
            String pattern = "##,##0";
            DecimalFormat declimalFormat = new DecimalFormat(pattern);
            String result = declimalFormat.format(qty);
            if(result.equals("-0")) result = "0";
            return result;
        } catch (Exception ex) {
            return "0";
        }

    }

    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-7-2 10:22:57
     * @param decimal
     * @return
     */
    public static String hold3Decimal(BigDecimal decimal) {
        try {
            String pattern = "0.000";
            DecimalFormat df = new DecimalFormat(pattern);
            String result = df.format(decimal);
            if(result.equals("-0.000")) result = "0.000";
            return result;
        } catch (Exception ex) {
            return "";
        }

    }
    
    public static BigDecimal formatPrice(BigDecimal price) {
        try {
            String pattern = "0.000";
            DecimalFormat df = new DecimalFormat(pattern);
            String result = df.format(price);
            if(result.equals("-0.000")) result = "0.000";
            return new BigDecimal(result);
        } catch (Exception ex) {
            return null;
        }

    }
    
    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-7-2 10:24:47
     * @param decimal
     * @return
     */
    public static String hold2Decimal(BigDecimal decimal) {
        try {
            String pattern="0.00";
            DecimalFormat df = new DecimalFormat(pattern);
            String result = df.format(decimal);
            if(result.equals("-0.00")) result = "0.00";
            return result;
        } catch (Exception ex) {
            return "";
        }
    }
    
    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-7-2 10:24:47
     * @param decimal
     * @return
     */
    public static BigDecimal hold2Decimal(double amt) {
        try {
            DecimalFormat formatter = new DecimalFormat("###.00");
            return new BigDecimal(formatter.format(amt));
        } catch (Exception ex) {
            return new BigDecimal(0.00);
        }
    }
    
    public static BigDecimal hold3Decimal(double amt) {
        try {
            DecimalFormat formatter = new DecimalFormat("###.000");
            return new BigDecimal(formatter.format(amt));
        } catch (Exception ex) {
            return new BigDecimal(0.000);
        }
    }
    
    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-9-25 下午04:07:57
     * @param array
     * @return
     */
	public static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < array.length; ++i) {
	     sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1,3));
	    }
	    return sb.toString();
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-9-25 下午04:08:02
	 * @param message
	 * @return
	 */
	public static String md5(String message) { 
		try {
		    MessageDigest md = MessageDigest.getInstance("MD5");
		    return hex(md.digest(message.getBytes("CP1252")));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		} 
		return null;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-11-13 下午03:37:38
	 * @param str
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static final String addFieldToCSV(String str, String fieldName,String value) {
		int i = str.lastIndexOf(")");
		StringBuffer buf = new StringBuffer(str);
		buf.insert(i, "," + fieldName + "='" + value + "'");
		return buf.toString();
	}

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-11-13 下午03:37:55
	 * @param csvID
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public static final String createCSV(String csvID, String fieldName,String value) {
		return csvID + "=(" + fieldName + "='" + value + "')";
	}
	
	/**
	 * get https root url
	 * 1.get from admin portal
	 * 2.if null,get default value
	 * @Author:Cimenon Saint
	 * @Time:2007-11-29 17:06:09
	 * @return
	 */
    public static String getHttpsRootUrl(){
		String url = null;
		String temp =PropertyConfig.getCommonProperty(Consts.Mobile.HTTPS_ROOT_URL);
		if(StringUtils.isNotBlank(temp)){
			url = temp;
		}else{
			url = Consts.Mobile.DEFAULT_HTTPS_ROOT_URL;
		}
		return url;
    }
    
    /**
     * 在ClassPath中查找配置文件，并返回文件的绝对路径
     * @param fileName 配置文件的名字－－相对路径
     * @return　返回文件的绝对路径
     * @throws MalformedURLException
     */
    public static String getFilePath(String fileName) {

        if (fileName == null)
            return null;

        URL url = PortalUtil.class.getClassLoader().getResource(fileName);
        if (url == null) {
            try {
                url = new URL("file", "localhost", fileName);
            } catch (MalformedURLException e) {
                return null;
            }
        }
        return url.getFile();
    }

    public static boolean isNotMachRulePassword(String password,String transactionProtection){
    	if(Consts.Global.Flag.POSITIVE.equals(transactionProtection)){
    		Pattern pattern = Pattern.compile(Consts.Global.Password.PASSWORD_REGEX);
    		if(pattern.matcher(password).find()){
    			return false;
    		}else{
    			return true;
    		}
    		
    	}
		return false;
	}
    
    public static boolean checkReferer(String referer){
		return (StringUtils.isNotBlank(referer));
	}
    public static String ShkEncrypt(int key){
    	long iDate = Long.parseLong(DateHelper.formatDate(new Date(),"yyyyMMdd"));
    	long value = Math.abs(iDate*(key%177)-(iDate*(iDate%117)));
    	long enyKey = Math.abs((value*(iDate%71))-(iDate*(iDate%73))+(key*(iDate%19)));
    	return String.valueOf(enyKey);
    }

	public static void main(String[] args) {
		System.out.println("test--- " + md5("password"));
		System.out.println("--------------"+getFilePath("PasswordUtil.java"));
//		String fi = getAgentId();
//		System.out.println("--------------"+fi);
	}

}
