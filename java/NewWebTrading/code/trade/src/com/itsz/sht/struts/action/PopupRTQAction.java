package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.common.model.common.request.AccessRTQRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.response.AccessRTQResponseModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class PopupRTQAction extends ITSZAction {

	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ListSelectServiceRequestModel listSelectRTQModel = new ListSelectServiceRequestModel();
		request2Model(request, listSelectRTQModel);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		listSelectRTQModel.setClientId(user.getTradeInfoObject().getCustCode());
		ListSelectServiceResponseModel responseModel = facade.listSelectService(listSelectRTQModel);
		if(responseModel.getExistingServiceProductList()!=null && responseModel.getExistingServiceProductList().size()>0){
			AccessRTQRequestModel accessRTQModel = new AccessRTQRequestModel();
			request2Model(request, accessRTQModel);
			accessRTQModel.setClientId(user.getTradeInfoObject().getCustCode());
			accessRTQModel.setClientIp(request.getRemoteAddr());
			AccessRTQResponseModel accessRTQResp = facade.accessRTQ(accessRTQModel);
			if(accessRTQResp!=null && !accessRTQResp.getReturnCode().equals("NORMAL")){//非正常情况（账号不足分配、产品不可用）
				request.setAttribute("productId", responseModel.getExistingServiceProductList().get(0).getId().getProdId());
				ProcessBean processBean = new ProcessBean(accessRTQResp, null, mapping, form, request, response);
				return vp.processStockRTQ(processBean);
			}
		}
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, form, request, response);
		return vp.processPopupRTQ(processBean);
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
