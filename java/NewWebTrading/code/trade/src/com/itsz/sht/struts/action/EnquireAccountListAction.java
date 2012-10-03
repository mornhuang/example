package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EnquireAccountListRequestModel;
import com.itsz.sht.common.model.response.MISAccountListResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.MISAccountListResponse;

/**
 * $Id: EnquireAccountListAction.java,v 1.1 2010/12/01 06:33:14 kyzou Exp $
 * @Project:portal
 * @File:EnquireAccountList.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-3-17
 */
public class EnquireAccountListAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		MISAccountListResponseModel misAccountResp=new MISAccountListResponseModel();
		MISAccountListResponse list = null;
		UserObject user=(UserObject)request.getSession().getAttribute(Consts.Profile.USER);
		if(user!=null){
			if(user.getTradeInfoObject()!=null){
				list=(MISAccountListResponse) user.getTradeInfoObject().getMisAccountList();
			}
		}
		if(list!=null){
			MISAccountListResponse  misAccountList = list;
			misAccountResp.setMisAccountListResponse(misAccountList);
			misAccountResp.setReturnCode(Consts.Global.Status.NORMAL);
			misAccountResp.setResultForward(Consts.Global.Forward.SUCCESS);
		}else{
			EnquireAccountListRequestModel accountModel=this.fillValue(request);
			misAccountResp = facade.enquireAccountList(accountModel);
			this.fillMisAccListSession(request,misAccountResp);
		}
		ProcessBean processBean=new ProcessBean(misAccountResp,null,mapping,request,response);
		return vp.processEnquireAccountList(processBean);
	}

	private EnquireAccountListRequestModel fillValue(HttpServletRequest request){
		EnquireAccountListRequestModel accountModel=new EnquireAccountListRequestModel();
		DataModelUtil.request2Model(request, accountModel);
		accountModel.setCustCode(((UserObject)request.getSession().getAttribute(Consts.Profile.USER)).getTradeInfoObject().getCustCode());
		return accountModel;
	}

	private void fillMisAccListSession(HttpServletRequest request,MISAccountListResponseModel misAccountResp){
		UserObject user=(UserObject)request.getSession().getAttribute(Consts.Profile.USER);
		if(user!=null){
			if(user.getTradeInfoObject()!=null){
				user.getTradeInfoObject().setMisAccountList(misAccountResp.getMisAccountListResponse());
			}}
	}

}
