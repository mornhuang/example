package com.itsz.sht.struts.action;

import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.SessionUtil;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.request.EnquireAccountListRequestModel;
import com.itsz.sht.common.model.response.MISAccountListResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.struts.form.ListSelectRTQForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.AccountListDetail;

public class MainAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ListSelectServiceRequestModel listSelectRTQModel = new ListSelectServiceRequestModel();
		request2Model(request, listSelectRTQModel);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		listSelectRTQModel.setClientId(user.getTradeInfoObject().getCustCode());
		EnquireAccountListRequestModel accountModel = this.fillValue(request);
		MISAccountListResponseModel misAccountResp = facade.enquireAccountList(accountModel);
		this.fillMisAccListSession(request, sortAccountList(misAccountResp));
		ProcessBean processBean = new ProcessBean(null, null, mapping, form, request, response);
		return vp.processMain(processBean);
		//return mapping.findForward("success");
	}

	private EnquireAccountListRequestModel fillValue(HttpServletRequest request) {
		EnquireAccountListRequestModel accountModel = new EnquireAccountListRequestModel();
		DataModelUtil.request2Model(request, accountModel);
		accountModel.setCustCode(((UserObject) request.getSession()
				.getAttribute(Consts.Profile.USER)).getTradeInfoObject()
				.getCustCode());
		return accountModel;
	}

	private void fillMisAccListSession(HttpServletRequest request,
			MISAccountListResponseModel misAccountResp) {
		UserObject user = (UserObject) request.getSession().getAttribute(Consts.Profile.USER);
		if (user != null) {
			if (user.getTradeInfoObject() != null) {
				user.getTradeInfoObject().setMisAccountList(misAccountResp.getMisAccountListResponse());
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private MISAccountListResponseModel sortAccountList(MISAccountListResponseModel misAccountResp) {
		Collection<AccountListDetail> acList = misAccountResp.getMisAccountListResponse().getAccountListDetail();
		LinkedList<AccountListDetail> newAcList = new LinkedList<AccountListDetail>(); 
		for (AccountListDetail acDetail : acList) {
			if (Consts.Epayment.FundTransfer.ACTYPE_ONLINE.equals(acDetail.getOnline())) {
				newAcList.addFirst(acDetail);
			} else {
				newAcList.addLast(acDetail);
			}
		}
		misAccountResp.getMisAccountListResponse().setAccountListDetail(newAcList);
		return misAccountResp;
	}
	
	public static void request2Model(HttpServletRequest request,AbstractRequestModel destModel) {
		String clv = request.getParameter(Consts.Global.Common.CLV_NAME);
		if(StringUtils.isBlank(clv)){
			clv = (String)request.getSession().getAttribute(Consts.Global.Common.CLV_NAME);
		}
		destModel.setChannelId(CLVSplitUtil.getChannelId(clv));
		destModel.setChannelType(CLVSplitUtil.getChannelType(clv));
		destModel.setLanguage(CLVSplitUtil.getLanguage(clv));
		destModel.setVersionId(String.valueOf(CLVSplitUtil.getVersionID(clv)));
	}
}
