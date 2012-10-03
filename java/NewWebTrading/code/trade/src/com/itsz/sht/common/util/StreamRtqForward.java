/**
 * <p>Title:Channels Server</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.sht.common.util;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import com.itsz.sht.common.PortalUtil;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.model.request.AaStockRTQRequest;
import com.itsz.sht.common.user.TradeInfoObject;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.model.RTQAccess;


/**
 * 
 * $Id: StreamRtqForward.java,v 1.3 2011/04/19 09:06:39 cyzeng Exp $
 * @Project:portal
 * @File:StreamRtqForward.java
 * @Description:
 * @Author:kyzou
 * @Date:2007-11-23
 */
public class StreamRtqForward {
	 public String aaStockRTQ(HttpServletRequest request,RTQAccess rtqInfo) {
		request.getSession().setAttribute("aastock_forward_url", rtqInfo.getRtqUrl());
		AaStockRTQRequest aaStockRTQRequest = new AaStockRTQRequest();
		aaStockRTQRequest.setBroker("taifook");
		aaStockRTQRequest.setLink(rtqInfo.getRtqUrl());
		aaStockRTQRequest.setPassword(rtqInfo.getRtqLoginPassword());
		aaStockRTQRequest.setUname(rtqInfo.getRtqLoginId());
		aaStockRTQRequest.setVer("1");
		aaStockRTQRequest.setLang(LangUtil.GetRtqLang(request));
		request.setAttribute("aaStockRTQRequest", aaStockRTQRequest);
		String aastock_enabled = PropertyConfig.getCommonProperty("aastock_enabled");
		if(StringUtils.isBlank(aastock_enabled)){
			aastock_enabled = "0";
		}
		request.setAttribute("aastock_enabled", aastock_enabled);
		return "aastock";
	}

	public String newEtnetRTQ(HttpServletRequest request, RTQAccess rtqInfo) {
		request.setAttribute("url", rtqInfo.getRtqUrl());
		request.setAttribute("uid", rtqInfo.getRtqLoginId());
		request.setAttribute("password", rtqInfo.getRtqLoginPassword());
		request.setAttribute("lang",LangUtil.getNewEtnetLang(request));
		request.setAttribute("reqeustStr", PropertyConfig.getCommonProperty("NewETNetRequest"));
		request.setAttribute("demo",PropertyConfig.getCommonProperty("NewETNetDemo").equals("1")?true:false);	
		return "etnet";
	}
	 
	public String etnetAppletRTQ(HttpServletRequest request, RTQAccess rtqInfo) {
		String uid = rtqInfo.getRtqLoginId();
		request.setAttribute("uid",uid);				
		request.getSession().setAttribute("pwd", rtqInfo.getRtqLoginPassword());
		String eKey = StreamRtqUtil.GenerateMD5Key(uid+"taifook");
		request.getSession().setAttribute("Digest", eKey);
		request.setAttribute("lang",LangUtil.GetEtnetLang(request));
		request.setAttribute("quoteUrl",rtqInfo.getRtqUrl());		
		return "etnet";
	}
	 
	private String getRTQLanguage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Locale locale = (Locale) session.getAttribute(Globals.LOCALE_KEY);
		String langCode = PortalUtil.getRTQLanguageCode(locale);
		return langCode;
	}
	
	public String getOnlineAccount(UserObject user,HttpServletRequest request) {
        TradeInfoObject trade = (TradeInfoObject) user.getTradeInfoObject();
        String tradingaccount=""; 
        if(trade != null){
        	tradingaccount = trade.getTradingAccount();       
        }
        return tradingaccount;    
    }

}
