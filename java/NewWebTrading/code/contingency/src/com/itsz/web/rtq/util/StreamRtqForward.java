/**
 * <p>Title:Channels Server</p>
 * <p>Description:Taifook Securities Trading Terminal</p>
 * @author Hu Xin
 * @version 1.0
 */
package com.itsz.web.rtq.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;

import com.itsz.common.Constants;
import com.itsz.parameter.util.ParameterManager;
import com.itsz.web.rtq.obj.IQLoginProfile;

public class StreamRtqForward {
	
	private static Log log=LogFactory.getLog(StreamRtqForward.class);
	
	public String etnetAppletRTQ(HttpServletRequest request,Map rtqInfo) {
        
		String serverUrl=(String) rtqInfo.get("quoteurl");		
		String uid = (String) rtqInfo.get("uid");	
		String lang = (String) request.getSession().getAttribute(Constants.LANGUAGE);
		lang = LangConvert.etnetConvert(lang);
		String digest = StreamRtqUtil.GenerateMD5Key(uid);
		log.info("etnet request Token URL: " + serverUrl);

		StringBuffer sb = new StringBuffer(serverUrl);
		sb.append("?Company=taifook")
	       .append("&Userid=").append(uid)
	       .append("&Digest=").append(digest)
	       .append("&Lang=").append(lang)
	       .append("&StockCode=").append("");
		IQLoginProfile iq = StreamRtqUtil.IQObtainKey(sb.toString());
		
		log.info("etnet get token result: " + iq.getResult());
		
		if(iq == null || iq.getResult() == null || iq.getResult().equals(Constants.IQ_SERVER_ERROR)){
			request.setAttribute(Constants.IQ_RESULT,iq.getResult());
			return "etnet_failure";
		}

		request.setAttribute("etnet", iq.getResult());
		
		return "etnet_new_popup";
	}
	
	public String etnetOldAppletRTQ(HttpServletRequest request,Map rtqInfo) {
		
		String serverUrl=(String) rtqInfo.get("quoteurl");		
		String uid = (String) rtqInfo.get("uid");	
		String pwd = (String) rtqInfo.get("pwd");	
		String etnet_login_url = ParameterManager.getValue(Constants.ETNET_LOGIN_URL);

		log.info("etnet request Token URL: " + etnet_login_url);
		
		IQLoginProfile iq = StreamRtqUtil.OldIQObtainKey(etnet_login_url.toString(),uid,pwd);
		
		log.info("etnet get token result: " + iq.getResult());//TODO		
		
		if(iq == null || !"0".equals(iq.getResult().trim())){
			request.setAttribute(Constants.IQ_RESULT,iq.getResult());
			return "etnet_failure";
		}
		
		String lang = (String) request.getSession().getAttribute(Constants.LANGUAGE);
		lang = LangConvert.etnetConvert(lang);
		String passport=iq.getPassport();

		Map etnet=new HashMap();
		etnet.put("passport",passport);
		etnet.put("uid",uid);
		etnet.put("quoteurl",serverUrl);
		etnet.put("lang",lang);
		
		request.setAttribute("etnet", etnet);
		
		log.info("etnet request Server URL: " + serverUrl);
		
		return "etnet_popup";
	}
	
	
	public String qs2AppletRTQ(HttpServletRequest request,Map rtqInfo) {
		
		String lang = (String) request.getSession().getAttribute(Constants.LANGUAGE);
		lang =TFStartLangConvert.convert(lang);
		
		String tokenUrl=ParameterManager.getValue(Constants.QS2_TOKEN_URL);
		String serverUrl=(String) rtqInfo.get("quoteurl");		
		String uid = (String) rtqInfo.get("uid");	
		String pwd = (String) rtqInfo.get("pwd");	
		
		
		String domain = "TAIFOOK";
		
		log.info("QS2 request Token URL: " + tokenUrl);
		String token = StreamRtqUtil.QSObtainKey(tokenUrl);
		String eKey = StreamRtqUtil.GenerateMD5Key(uid,pwd,domain,token);
		log.info("etnet get token result: " + token);//TODO	
		log.info("QS2 request Server URL: " + serverUrl);
		Map qpjunior=new HashMap();
		qpjunior.put("token",token);
		qpjunior.put("eKey",eKey);
		qpjunior.put("url","");
		qpjunior.put("uid",uid);
		qpjunior.put("quoteurl",serverUrl);
		qpjunior.put("lang",lang);
		
		request.setAttribute("qpjunior", qpjunior);
		
		return "qs2";
	}
	
	
	
}





