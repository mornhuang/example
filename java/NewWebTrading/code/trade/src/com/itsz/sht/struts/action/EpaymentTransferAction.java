package com.itsz.sht.struts.action;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.EpaymentConfig;
import com.itsz.sht.common.MySessionContext;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.user.AcEnquiryInfo;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.common.util.HttpClientUtil;
import com.itsz.sht.common.util.OnlineAccountSorter;
import com.itsz.sht.vp.ViewProvider;
import com.taifook.mcs.core.beans.msg.TradingAccountInfo;
/** 
 * @struts.action
 * 	  name="emptyForm"
 *    path="/ePaymentTransfer"
 *    scope="request"
 *    validate="false"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/transfer/epayment_forward.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/blank.jsp"
 *    redirect="false"
 *
 */

public class EpaymentTransferAction extends ITSZAction {

	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_MCS);
	
	public ActionForward executeAction(ViewProvider vp,ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){
		String forward = Consts.Global.Forward.FAILURE;
		String bankCode = request.getParameter("bankCode");
		String amount = null;
		String defaultAccount = null;
		if (null == bankCode){
			bankCode = request.getAttribute("bankCode").toString();
			amount = request.getAttribute("amount").toString();
			defaultAccount = request.getAttribute("defaultAccount").toString();
		}
		
		log_debug.info("EpaymentTransferAction bankCode: " + bankCode);
		EpaymentConfig epaymentConfig = new EpaymentConfig();
		epaymentConfig.setAuthurl(PropertyConfig.getProviderName(Consts.Epayment.FundTransfer.AUTHURL));
		epaymentConfig.setBankcode1(PropertyConfig.getProviderName(Consts.Epayment.FundTransfer.BANKCODE1));
		epaymentConfig.setBankcode2(PropertyConfig.getProviderName(Consts.Epayment.FundTransfer.BANKCODE2));
		epaymentConfig.setForwardurl(PropertyConfig.getProviderName(Consts.Epayment.FundTransfer.FORWARDURL));
		epaymentConfig.setInternal(PropertyConfig.getProviderName(Consts.Epayment.FundTransfer.INTERNAL));
		epaymentConfig.setPath(PropertyConfig.getProviderName(Consts.Epayment.FundTransfer.PATH));
		if (!bankCode.equalsIgnoreCase(epaymentConfig.getBankcode1()) && 
				!bankCode.equalsIgnoreCase(epaymentConfig.getBankcode2())){
			log_debug.error("bankCode is invalid.");
			forward = Consts.Global.Forward.FAILURE;
			request.setAttribute("error", "errors.logonForm.logonError");
			return mapping.findForward(forward);
		}
		if (StringUtils.isEmpty(amount) || StringUtils.isBlank(amount)){
			amount = "0";
		}
		log_debug.info("EpaymentTransferAction amount: " + amount);
		String forwardUrl = epaymentConfig.getPath() + epaymentConfig.getForwardurl();
		String sessionId=request.getParameter("sessionId");//NPS的请求有的并没有传jsessionId过来,而是传的sessionId
		HttpSession session = MySessionContext.getSession(sessionId);
		if(null==session)
		    session = request.getSession();
		UserObject user = (UserObject) session.getAttribute(Constants.USER);
		String token = getEpaymentToken(request, epaymentConfig, bankCode, amount,defaultAccount,user);
		if (StringUtils.isNotEmpty(token) && StringUtils.isNotBlank(token)){
			request.setAttribute("forwardUrl", forwardUrl);
			request.setAttribute("token", token);
			forward = Consts.Global.Forward.SUCCESS;
		} else {
			log_debug.info("get Token invalid.");
			forward = Consts.Global.Forward.FAILURE;
			request.setAttribute("error", "errors.logonForm.logonError");
		}
		return mapping.findForward(forward);
	}
	public String getEpaymentToken(HttpServletRequest request,EpaymentConfig epaymentConfig,
			String bankCode,String amount,String defaultAccount,UserObject user) {
		String token=null;
		String acs = getAccounts((List)user.getTradeInfoObject().getAcEnquiryList(request));
		String internalUrl = epaymentConfig.getInternal() + epaymentConfig.getAuthurl();
        String forwardUrl = epaymentConfig.getPath() + epaymentConfig.getForwardurl();
        String custCode = user.getTradeInfoObject().getCustCode();
        Locale locale = (Locale)request.getSession().getAttribute(Globals.LOCALE_KEY);
        String lang = locale.toString();
        String loginId = user.getLoginName();
        String channelsCode = user.getChannelType();
        
        log_debug.info("EpaymentDelegate acs: " + acs);
        log_debug.info("EpaymentDelegate custCode: " + custCode);
        log_debug.info("EpaymentDelegate lang: " + lang);
        log_debug.info("EpaymentDelegate loginId: " + loginId);
        log_debug.info("EpaymentDelegate channelsCode: " + channelsCode);
        log_debug.info("EpaymentDelegate amount: " + amount);
        log_debug.info("EpaymentDelegate defaultAccount: " + defaultAccount);
        log_debug.info("EpaymentDelegate internalUrl: " + internalUrl);
        log_debug.info("EpaymentDelegate forwardUrl: " + forwardUrl);
        
        try {
			String message = HttpClientUtil.getInstance().getEpaymentToken(internalUrl, 
					bankCode, acs,custCode,lang,loginId, channelsCode,amount,defaultAccount);
			log_debug.info("epayment Token: " + message);
			if (StringUtils.isNotEmpty(message) && StringUtils.isNotBlank(message)) {
				String[] tmp = message.split("#");
				if (tmp!=null && tmp.length == 2) {
					String responseCode = tmp[0].split("=")[1];
					String respnoseToken = tmp[1].split("=")[1];
					if ("1000".equals(responseCode)) {
						token = respnoseToken;
					}
				}
			}
		} catch (Exception e) {
			log_debug.error(e.getMessage());
		}
		return token;
	}
	
	private String getAccounts(List accounts){
		if (accounts == null) {
			return "";
		}
		
        Collections.sort(accounts, new OnlineAccountSorter());
        
		StringBuilder sbAccounts = new StringBuilder();
		for (int i = 0; i < accounts.size(); i++) {
			Object obj=accounts.get(i);
			if(obj instanceof TradingAccountInfo){
				TradingAccountInfo accountInfo = (TradingAccountInfo) obj;
	            sbAccounts.append(accountInfo.getTradingAccount()).append(",");
			}else if(obj instanceof AcEnquiryInfo){
				AcEnquiryInfo acEnquiryInfo=(AcEnquiryInfo)obj;
				sbAccounts.append(acEnquiryInfo.getAccountId()).append(",");
			}
        }
		return sbAccounts.toString();
	}
	public boolean isAccountOutOfCompany(String accountId) {

        boolean result = true;
        if (accountId != null && accountId.length() > 2) {
            if (Consts.Global.Common.TAIFOOK_SECURITIES.equals(accountId.substring(0, 2))) {
                result = false;
            }
        }
        return result;
    }

}
