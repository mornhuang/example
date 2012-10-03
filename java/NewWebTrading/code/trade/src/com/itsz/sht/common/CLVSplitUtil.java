package com.itsz.sht.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import com.itsz.sht.struts.form.ITSZForm;

/**
 * 
 * @Project:portal.head
 * @File:CLVSplitUtil.java
 * @Description:
 * @Author:
 * @Date:2009-5-24
 */
public class CLVSplitUtil {
	private static ChannelsParamInit channelsParamInit;
	
	static{
		channelsParamInit = (ChannelsParamInit)ServiceLocator.getInstance().getService("channelsParamInit");
	}

	public static String transNullCLV(HttpServletRequest request,HttpServletResponse response, ITSZForm tForm,String clv) {
		if (isNullClv(clv)) {
			String modelChannel;
			if(request.getServletPath() == null)
				modelChannel = Consts.Channel.WMT;
			else
				modelChannel = (request.getServletPath()).substring(1, 4);
			String language = request.getParameter("lang");
			if(Consts.Channel.WEB.equalsIgnoreCase(modelChannel)){
				clv = getClvFromCookie(request);
				if(isNullClv(clv)){
					clv = getDefaultClv(response, language);
				}
			}else{
				if (Consts.Global.Language.ENG.equalsIgnoreCase(language)) {//en_US
					clv = Consts.Web.CLV.EN;//"WE25"
				} else if (Consts.Global.Language.GB.equalsIgnoreCase(language)) {//zh_CN
					clv = Consts.Web.CLV.GB;//"WG25"
				} else {
					clv = Consts.Web.CLV.TC;//"WT25"
				}
			}
		}
		return clv;
	}

	private static boolean isNullClv(String clv) {
		return (StringUtils.isBlank(clv) || clv==null || "null".equals(clv));
	}

	private static String getDefaultClv(HttpServletResponse response,
			String language) {
		String clv = Consts.Web.CLV.TC;
		if (Consts.Global.Language.ENG.equalsIgnoreCase(language)) {//en_US
			clv = Consts.Web.CLV.EN;//"ME25"
		} else if (Consts.Global.Language.GB.equalsIgnoreCase(language)) {//zh_CN
			clv = Consts.Web.CLV.GB;//"MG25"
		} else {
			clv = Consts.Web.CLV.TC;//"MT25"
		}
		Cookie ck = new Cookie(Consts.Global.Common.COOKIE_LANGUAGE,clv);
		response.addCookie(ck);
		return clv;
	}

	public static String getClvFromCookie(HttpServletRequest request) {
		String clv=null;
		Cookie[] c = request.getCookies();
		if( c!= null){
			for(int i=0;i<c.length;i++){
				Cookie co = c[i];
				if(Consts.Global.Common.COOKIE_LANGUAGE.equals(co.getName())){
					clv = co.getValue();
					break;
				}
			}
		}
		return clv;
	}

	/**
	 * get channel type from clv
	 * @Time:15:49:07
	 * @param clv
	 * @return
	 */
	public static String getChannelType(String clv) {
		String channleType = Consts.Channel.NWEB;
		if(StringUtils.isNotBlank(clv)){
			String chnType = clv.substring(0, 1);
			channleType = mappingChannelType(chnType);
		}
		return channleType;
	}
	
	public static String getChannelId(String clv) {
		String channleId = Consts.Channel.Id.NWEB;
		if(StringUtils.isNotBlank(clv)){
			String chnType = clv.substring(0, 1);
			channleId = mappingChannelId(chnType);
		}
		return channleId;
	}

	/**
	 * get language from clv
	 * @Time:15:41:32
	 * @param clv
	 * @return
	 */
	public static String getLanguage(String clv) {
		String language = Consts.Global.Language.BIG5;
		if(StringUtils.isNotBlank(clv)){
			String lang = clv.substring(1, 2);
			language = mappingLanguage(lang);
		}
		return language;
	}

	/**
	 * get sync from clv
	 * @Time:15:41:32
	 * @param clv
	 * @return
	 */
	public static String getSync(String clv) {
		String sync = Consts.Global.Sync.SYNC;
		if (StringUtils.isNotBlank(clv) && clv.length() > 4) {
			sync = clv.substring(4, 5);
		}
		return sync;
	}
	
	public static void main(String[] argv) {
		String clv = "WT25A";
		System.out.println(getSync(clv));
	}
	
	/**
	 * get version id from clv
	 * @Time:15:45:25
	 * @param clv
	 * @return
	 */
	public static int getVersionID(String clv) {
		int version = -1;
		if(StringUtils.isNotBlank(clv)){
			String str = clv.substring(2, 4);
			try {
				version = Integer.parseInt(str);
			} catch (Exception e) {
				version = 25;
			}
		}
		return version;
	}

	private static String mappingChannelType(String s) {
		return (String) channelsParamInit.getChannelMap().get(s);
	}
	
	private static String mappingChannelId(String s) {
		return (String) channelsParamInit.getChannelIdMap().get(s);
	}

	private static String mappingLanguage(String s) {
		String value = (String) channelsParamInit.getLanguageMap().get(s);
		if(StringUtils.isNotBlank(value)){
			return value;
		}
		return Consts.Global.Language.BIG5;
	}
}
