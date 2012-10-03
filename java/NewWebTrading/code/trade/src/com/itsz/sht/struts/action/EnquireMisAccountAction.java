package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EnquireAccountListRequestModel;
import com.itsz.sht.common.model.request.EnquireAccountRequestModel;
import com.itsz.sht.common.model.response.EnquireAccountResponseModel;
import com.itsz.sht.common.model.response.MISAccountEnquiryResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.itsz.sht.vp.common.ServiceStatusEnquiryResponse;
import com.taifook.mcs.core.beans.msg.AccountDetail;

/**
 * 
 * $Id: EnquireMisAccountAction.java,v 1.6 2011/03/24 03:48:20 xlliu Exp $
 * 
 * @Project:portal
 * @File:EnquireAccountAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-6
 */
public class EnquireMisAccountAction extends ITSZAction {

	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EnquireAccountListRequestModel accountModel = new EnquireAccountListRequestModel();
		DataModelUtil.request2Model(request, accountModel);
		UserObject user=(UserObject)request.getSession().getAttribute(Consts.Profile.USER);
		if (user != null && user.getTradeInfoObject() != null) 
			accountModel.setCustCode(user.getTradeInfoObject().getCustCode());
		MISAccountEnquiryResponseModel accResp = facade.enquireMisAccount(accountModel);
		if(accResp.getReturnCode().equals("NORMAL")){
			EnquireAccountRequestModel accountReqModel = this.fillValue(request, 0, filterFromAccounts(accResp.getMisAccountEnquiryResponse().getAccountDetailCol()));
			if(accountReqModel.getOnline()!=null && accountReqModel.getOnline().equals("ON")){
				EnquireAccountResponseModel responseModel = facade.enquireAccountDetail(accountReqModel);
				request.setAttribute("resultResponseModel", responseModel);
			}
		}
		ProcessBean processBean = new ProcessBean(accResp, null, mapping, request, response);
		return vp.processEnquireMisAccount(processBean);
	}
	
	private EnquireAccountRequestModel fillValue(HttpServletRequest request, int i, List list) {
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		DataModelUtil.request2Model(request, accountModel);
		if(list.size()>0){
			AccountDetail alistdetail = (AccountDetail) list.get(i);
			accountModel.setAccountId(alistdetail.getAccountId());
			accountModel.setAccountType(alistdetail.getAccountType().split(",")[0]);
			accountModel.setOnline(alistdetail.getAccountType().split(",")[1]);
		}
		return accountModel;
	}
	
	protected List filterFromAccounts(Collection accounts) {
		List fromAccounts = new ArrayList();
		AccountDetail account = null;
		String accountType = null;
		for (Iterator i = accounts.iterator(); i.hasNext();) {
			account = (AccountDetail) i.next();
			accountType = account.getAccountType();
			if (accountType == null) {
				continue;
			}
			if (isOnlineCashAccount(accountType)) { //online cash a/c
				fromAccounts.add(account);
			} else if (
					isOnlineMarginAccount(accountType)) { //online margin a/c
				fromAccounts.add(account);
			}
		}
		return fromAccounts;
	}
	protected boolean isOnlineCashAccount(String accountType) {
		return accountType.startsWith(Consts.Epayment.FundTransfer.ACTYPE_CASH)
		&& accountType.endsWith(Consts.Epayment.FundTransfer.ACTYPE_ONLINE);
	}

	protected boolean isOnlineMarginAccount(String accountType) {
		return accountType.startsWith(Consts.Epayment.FundTransfer.ACTYPE_MARGIN)
		&& accountType.endsWith(Consts.Epayment.FundTransfer.ACTYPE_ONLINE);
	}
	
	protected String getRemarks(
			HttpServletRequest request,
			ServiceStatusEnquiryResponse serviceStatusEnquiryResponse) {
			Locale locale = getLocale(request);
			if (Locale.TRADITIONAL_CHINESE.equals(locale)) {
				return serviceStatusEnquiryResponse.getRemarks_zh_TW();
			} else if (Locale.SIMPLIFIED_CHINESE.equals(locale)) {
				return serviceStatusEnquiryResponse.getRemarks_zh_CN();
			} else {
				return serviceStatusEnquiryResponse.getRemarks_en_US();
			}
		}
}
