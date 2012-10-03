package com.itsz.sht.struts.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.LanguageUtil;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.model.response.EnquireRTQCharResponse;
import com.itsz.sht.vp.ViewProvider;

/**
 * $Id: EnquireRTQCharAction.java,v 1.1 2010/12/01 06:33:14 kyzou Exp $
 * @Project:portal
 * @File:EnquireRTQCharAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-10-21
 */
public class EnquireRTQCharAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EnquireRTQCharResponse charResponse = new EnquireRTQCharResponse();
		String stock_code = request.getParameter("instrCode");
		//判断输入
		if(StringUtils.isEmpty(stock_code)){
			charResponse.setReturnCode(Consts.Error.Code.RTQ_NULL_STOCKCODE);
			request.setAttribute("charResponse", charResponse);
			return mapping.findForward(Consts.Global.Forward.FAILURE);			
		}
		if(!validInput(stock_code)){
			charResponse.setReturnCode(Consts.Error.Code.RTQ_INVALID_STOCKCODE);
			request.setAttribute("charResponse", charResponse);
			return mapping.findForward(Consts.Global.Forward.FAILURE);
		}
		if(StringUtils.isEmpty(stock_code)){
			stock_code = Consts.Qs.CHART_STOCK_HSI;
			charResponse.setShowRtqButton(Consts.Global.Flag.NEGATIVE);			
		}
		if(StringUtils.isNotEmpty(stock_code)){
			charResponse.setShowRtqButton(Consts.Global.Flag.POSITIVE);
		}
		if(Consts.Qs.CHART_STOCK_HHI.equalsIgnoreCase(stock_code)){
			stock_code = Consts.Qs.CHART_STOCK_CEI;
		}
		if(Consts.Qs.CHART_STOCK_HSI.equalsIgnoreCase(stock_code)){
			stock_code = Consts.Qs.CHART_STOCK_HSI;
			charResponse.setShowRtqButton(Consts.Global.Flag.NEGATIVE);
		}
		charResponse.setStock_code(stock_code);
		if(Consts.Qs.CHART_STOCK_CEI.equalsIgnoreCase(stock_code)){
			stock_code = Consts.Qs.CHART_STOCK_CEI;
			charResponse.setStock_code(Consts.Qs.CHART_STOCK_HHI);
			charResponse.setShowRtqButton(Consts.Global.Flag.NEGATIVE);
		}
		String lang = CLVSplitUtil.getLanguage(request.getParameter(Consts.Global.Common.CLV_NAME));
		lang = LanguageUtil.lang2locale(lang).toString();
		String url = PropertyConfig.getCommonProperty(Consts.Wmt.Commentray.CHART_RTQ_URL);
		if(StringUtils.isBlank(url)){
			log_info.error("Can't get CHART_RTQ_URL with PropertyConfig.getCommonProperty(CHART_RTQ_URL)! the CHART_RTQ_URL is null");
		}
		url = url + "?stock_code="+stock_code+"&access_code=taifook&lang="+lang;
		charResponse.setChar_url(url);
		request.setAttribute("charResponse", charResponse);
		return mapping.findForward(Consts.Global.Forward.SUCCESS);
	}
	
	private boolean validInput(String stock_code){
		if(Consts.Qs.CHART_STOCK_CEI.equalsIgnoreCase(stock_code) || Consts.Qs.CHART_STOCK_HSI.equalsIgnoreCase(stock_code) || Consts.Qs.CHART_STOCK_HHI.equalsIgnoreCase(stock_code)){
			return true;
		}
		if(isNumeric(stock_code)){
			return true;
		}
		return false;
	}

	private boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches()){
			return false;
		}
		return true;
	}
}
