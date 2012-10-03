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
import com.itsz.sht.common.model.response.EnquireAccountResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.AccountListDetail;

/**
 * 
 * $Id: EnquireAccountAction.java,v 1.3 2010/12/14 06:39:16 zxfan Exp $
 * 
 * @Project:portal
 * @File:EnquireAccountAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-6
 */
public class EnquireAccountAction extends ITSZAction {

	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EnquireAccountRequestModel accountModel = this.fillValue(request);
		EnquireAccountResponseModel accResp = facade.enquireAccountDetail(accountModel);
		ProcessBean processBean = new ProcessBean(accResp, null, mapping, request, response);
		return vp.processEnquireAccout(processBean);
	}

	private EnquireAccountRequestModel fillValue(HttpServletRequest request) {
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		DataModelUtil.request2Model(request, accountModel);
		int i = Integer.valueOf(request.getParameter("indexId"));
		List list = (List) ((UserObject) request.getSession().getAttribute(
				Consts.Profile.USER)).getTradeInfoObject().getMisAccountList()
				.getAccountListDetail();
		AccountListDetail alistdetail = (AccountListDetail) list.get(i);
		accountModel.setAccountId(alistdetail.getAccountId());
		accountModel.setAccountType(alistdetail.getAccountType());
		accountModel.setOnline(alistdetail.getOnline());
		request.setAttribute("indexId", i);
		return accountModel;
	}
}
