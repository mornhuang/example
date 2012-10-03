package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.FormatUtil;
import com.itsz.sht.common.LoggerFactory;
import com.itsz.sht.common.PortalUtil;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.user.ShkCPResponse;
import com.itsz.sht.common.user.UserManagement;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.vp.ViewProvider;

/**
 * $Id: ShkSubmitAction.java,v 1.1 2011/03/03 07:29:15 xli Exp $
 * @Project:portal-branch41
 * @File:SapReportAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-9-21
 */
public class ShkSubmitAction extends ITSZAction {

	/**
	 * 
	 */
	Log mcsInfo = LoggerFactory.getInstance().getMcsInfo();
	public ActionForward executeAction(
		ViewProvider vp ,
		ActionMapping mapping ,
		ActionForm form ,
		HttpServletRequest request ,
		HttpServletResponse response) {
			HttpSession session = request.getSession();
			UserObject user = (UserObject) session.getAttribute(Constants.USER);
			if (user != null && UserManagement.allowUser(user.getSessionID(),user.getChannelType(),user.getLoginName())) {
				String url = PropertyConfig.getProviderName(Consts.Ps.Shk.URL);
				mcsInfo.info("+++++++++++++++get shkUrl:++++++++++++++++"+url);
				if(StringUtils.isBlank(url)){
					url = Consts.Ps.Shk.URL_DEFAULT_VALUE;
				}
				String shkKey = PropertyConfig.getProviderName(Consts.Ps.Shk.SHK_KEY);
				mcsInfo.info("+++++++++++++++get shkKey:++++++++++++++++"+shkKey);
				if(StringUtils.isBlank(shkKey)){
					shkKey = String.valueOf(Consts.Ps.Shk.SHK_KEY_DEFAULT_VALUE);
				}
				ShkCPResponse shkcpRes = new ShkCPResponse();
				String security = PortalUtil.ShkEncrypt(Integer.parseInt(shkKey));
				String lang = convertLang(user.getLang());//Eng|Big5|GB
				String stockCode = request.getParameter("stockCode");
				if(StringUtils.isNotBlank(stockCode) && !stockCode.equals("null")){
					stockCode = FormatUtil.formatInstrCode(stockCode, 5);
					shkcpRes.setStockcode(stockCode);
				}
				shkcpRes.setUrl(url);
				shkcpRes.setSecurity(security);
				shkcpRes.setLanguage(lang);
				mcsInfo.info("+++++++++++++++post to shk Request: Security="+shkcpRes.getSecurity()
						+";Stockcode="+shkcpRes.getStockcode()
						+";Url="+shkcpRes.getUrl()
						+";Language="+shkcpRes.getLanguage());
				request.setAttribute("shkcpRes", shkcpRes);
			}else{
				ITSZException ex = new ITSZException();
				ex.setErrorCode(Consts.Error.Code.SESSION_OVERTIME);
				request.setAttribute(Consts.Error.Code.ITSZ_EXCEPTION, ex);
				return mapping.findForward("failure");
			}
	    	return mapping.findForward("success");
	}
	
	/**
	 * 
	 */
	public ActionForward processException(
		ViewProvider vp,
		ActionMapping mapping,
		ITSZException exceptionBean,
		HttpServletRequest request,
		HttpServletResponse response){
			
			ITSZException ex = new ITSZException();
			ex.setErrorCode(Consts.Error.Code.SESSION_OVERTIME);
			request.setAttribute(Consts.Error.Code.ITSZ_EXCEPTION, ex);
			return mapping.findForward("failure");
	}

	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:2007-10-9 上午10:43:11
	 * @param lang
	 * @return
	 */
	protected String convertLang(String lang){
		String res = "S";
		if("ENG".equals(lang)){
			res = "E";
		}else if("BIG5".equals(lang)){
			res = "C";
		}
		return res;
	}

}
