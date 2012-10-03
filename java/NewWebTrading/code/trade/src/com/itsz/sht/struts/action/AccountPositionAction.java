package com.itsz.sht.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EnquireAccountRequestModel;
import com.itsz.sht.common.model.request.EnquiryPositionRequestModel;
import com.itsz.sht.common.model.response.EnquireAccountResponseModel;
import com.itsz.sht.common.model.response.EnquiryPositionResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.AccountPositionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.AccountListDetail;

public class AccountPositionAction extends ITSZAction {
	public ActionForward executeAction(ViewProvider vp,
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
		AccountPositionForm accountPositionForm = (AccountPositionForm)form;
		int i = Integer.valueOf(accountPositionForm.getIndexId());
		List list = (List) ((UserObject) request.getSession().getAttribute(
				Consts.Profile.USER)).getTradeInfoObject().getMisAccountList()
				.getAccountListDetail();
		//Collections.sort(list, new OnlineAccountSorter());
		EnquireAccountRequestModel accountModel = this.fillValue(request, i, list);
		EnquireAccountResponseModel accResp = facade.enquireAccountDetail(accountModel);
		EnquiryPositionRequestModel positionModel = this.fillValue(request, new EnquiryPositionRequestModel(), i, list);
		EnquiryPositionResponseModel positionResp = facade.enquiryPosition(positionModel);
		accountPositionForm.setAccResp(accResp);
		accountPositionForm.setPositionResp(positionResp);
		ProcessBean processBean = new ProcessBean(null, null, mapping, accountPositionForm, request, response);
		return vp.processAccountPosition(processBean);
	}
	private EnquireAccountRequestModel fillValue(HttpServletRequest request, int i, List list) {
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		DataModelUtil.request2Model(request, accountModel);
		AccountListDetail alistdetail = (AccountListDetail) list.get(i);
		accountModel.setAccountId(alistdetail.getAccountId());
		accountModel.setAccountType(alistdetail.getAccountType());
		accountModel.setOnline(alistdetail.getOnline());
		return accountModel;
	}
	private EnquiryPositionRequestModel fillValue(HttpServletRequest request,
			EnquiryPositionRequestModel positionModel, int i, List list) {
		DataModelUtil.request2Model(request, positionModel);
		AccountListDetail alistdetail = (AccountListDetail) list.get(i);
		UserObject user = (UserObject) request.getSession().getAttribute(Consts.Profile.USER);
		positionModel.setAllowTradeStatusFlag(user.getTradeInfoObject().getAllowTradeStatusFlag());
		positionModel.setAccountId(alistdetail.getAccountId());
		return positionModel;
	}
}

