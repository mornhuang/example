
package com.itsz.sht.struts.action;


import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.PPSTransferForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * @struts.action
 *    name="ppsTransferForm"
 *    path="/ppsTransfer"
 *    scope="request"
 *    validate="false"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/transfer/pps_forward.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/ppsTransferInit.do"
 *    redirect="false"
 * 
 * @struts.action-forward
 *    name="suspend"
 *    path="/jsp/transfer/pps_suspend.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="nonoperate"
 *    path="/jsp/transfer/pps_nonoperate.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="inactive"
 *    path="/redirect_main.do"
 *    redirect="false"
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Taifook Securities Groups</p>
 * @author Henry Kwong
 * @version $Id: PPSTransferAction.java,v 1.5 2011/01/04 07:06:23 xlliu Exp $
 */

public class PPSTransferAction extends ITSZAction {
	
	public ActionForward executeAction(
		ViewProvider vp,
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		PPSTransferForm ppsTransferForm = (PPSTransferForm)form;
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null && user.getTradeInfoObject() != null){
			ppsTransferForm.setUser_id(user.getLoginName());
			ppsTransferForm.setLang(getPPSLocale(request));
		}
		ProcessBean processBean = new ProcessBean(null,null, mapping,ppsTransferForm, request,response);
		return vp.processPPSTransfer(processBean);
	}
	
	protected String getPPSLocale(HttpServletRequest request) {
		boolean isChinese = false;
		Locale locale = getLocale(request);
		if (locale != null) {
			if (Locale
				.TRADITIONAL_CHINESE
				.getLanguage()
				.equals(locale.getLanguage())) {
				isChinese = true;
			}

			if (Locale
				.SIMPLIFIED_CHINESE
				.getLanguage()
				.equals(locale.getLanguage())) {
				isChinese = true;
			}
		}
		return (isChinese ? Consts.Epayment.FundTransfer.PPS.LOCALE_CHI : Consts.Epayment.FundTransfer.PPS.LOCALE_ENG);
	}

}
