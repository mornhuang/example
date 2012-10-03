package com.itsz.sht.struts.action;

import java.util.Iterator;
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
import com.itsz.sht.common.user.AcEnquiryInfo;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.AccountListDetail;

/**
 * 
 * $Id: EnquireAccountDetailAction.java,v 1.3 2011/03/14 08:26:53 pbxie Exp $
 * 
 * @Project:portal
 * @File:EnquireAccountAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-6
 */
public class EnquireAccountDetailAction extends ITSZAction {

	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EnquireAccountRequestModel accountModel = this.fillValue(request);
		EnquireAccountResponseModel accResp = facade.enquireAccountDetailForPs(accountModel);
		ProcessBean processBean = new ProcessBean(accResp, null, mapping, request, response);
		return vp.processEnquireAccoutDetail(processBean);
	}

	private EnquireAccountRequestModel fillValue(HttpServletRequest request) {
		EnquireAccountRequestModel accountModel = new EnquireAccountRequestModel();
		DataModelUtil.request2Model(request, accountModel);
		String accountId = request.getParameter("accountId");
		List list = (List) ((UserObject) request.getSession().getAttribute(
				Consts.Profile.USER)).getTradeInfoObject().getAcEnquiryList(request);
		for(Iterator iter=list.iterator();iter.hasNext();){
			AcEnquiryInfo acInfo=(AcEnquiryInfo)iter.next();
			if(acInfo.getAccountId().equals(accountId)){
				accountModel.setAccountId(acInfo.getAccountId());
				accountModel.setAccountType(acInfo.getAccountType().split(",")[0]);
				accountModel.setOnline(acInfo.getAccountType().split(",")[1]);
				break;
			}
		}
		return accountModel;
	}
}
