package com.itsz.sht.struts.action;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EnquireAccountListRequestModel;
import com.itsz.sht.common.model.response.FundTransferRequestModel;
import com.itsz.sht.common.model.response.FundTransferResponseModel;
import com.itsz.sht.common.model.response.MISAccountEnquiryResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.struts.form.TransferFundActionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.AccountDetail;

/**
 * @struts.action
 *    name="fundTransferForm"
 *    path="/fundTransfer"
 *    scope="request"
 *    validate="true"
 *    input="/fundTransferInit.do"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/transfer/fund_transfer_success.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/fundTransferInit.do"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="suspend"
 *    path="/jsp/transfer/fund_suspend.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="nonoperate"
 *    path="/jsp/transfer/fund_nonoperate.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="inactive"
 *    path="/redirect_main.do"
 *    redirect="false"
 *
 */

public class FundTransferAction extends ITSZAction {
	
	public ActionForward executeAction(
		ViewProvider vp,
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		FundTransferRequestModel model = new FundTransferRequestModel();
		EnquireAccountListRequestModel accountModel = new EnquireAccountListRequestModel();
		DataModelUtil.request2Model(request, accountModel);
		DataModelUtil.form2Model(request,(TransferFundActionForm) form, model, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null) {
			model.setLoginId(user.getLoginName());
			accountModel.setCustCode(user.getTradeInfoObject().getCustCode());
		}
		if(StringUtils.isBlank(model.getFromCcy())||StringUtils.isBlank(model.getToCcy())){
			model.setFromCcy("HKD");
			model.setToCcy("HKD");
		}
		MISAccountEnquiryResponseModel accResp = facade.enquireMisAccount(accountModel);
		if(Consts.Error.Code.CONNECT_MCS.equals(accResp.getReturnCode())){
			return processException(vp,mapping,new ITSZException(accResp.getReturnCode()),request,response);
		}
		Collection<AccountDetail> accountList = accResp.getMisAccountEnquiryResponse().getAccountDetailCol();
		List<AccountDetail> fromAccounts = this.filterFromAccounts(accountList);
		for(int i=0;i<fromAccounts.size();i++){
			if(fromAccounts.get(i).getBankAccountCode()==null){
				break;
			}
			if(fromAccounts.get(i).getBankAccountCode().equals(model.getToAccountId())){
				model.setBank(true);
				break;
			}
		}
		FundTransferResponseModel responseModel = facade.fundTransfer(model);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, (TransferFundActionForm) form, request,response);
		return vp.processFundTranser(processBean);
	}
	
	@SuppressWarnings("unchecked")
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
}
