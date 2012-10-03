package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.ForwardMappingUtil;
import com.itsz.sht.common.PortalUtil;
import com.itsz.sht.common.ValidateUtil;
import com.itsz.sht.common.WEB_Constants;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.request.CancelOrderRequestModel;
import com.itsz.sht.common.model.response.BulkCancelOrderResponseModel;
import com.itsz.sht.common.model.response.CancelOrderResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.CancelOrderForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: CancelOrderAction.java,v 1.3 2010/12/21 11:33:43 zxfan Exp $
 * @Project:portal.head
 * @File:CancelOrderAction.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class CancelOrderAction extends ITSZAction {
	
	public boolean isTokenRequired(){
		return true;
	}
	
	/**
	 * 
	 * @Author:Cimenon Saint
	 * @Time:16:22:48
	 * @param vp
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward executeAction(
		ViewProvider vp,
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
			if(!PortalUtil.checkReferer(request.getHeader("referer"))){
				return doWebTimeout(vp,mapping,form,request,response);
			}
			String oldToken = getSessionToken(request);
			if(!checkAndSetToken(request)){
				return doInvalidToken(vp,mapping,form,request,response);
			}
			CancelOrderForm cancelOrderForm=(CancelOrderForm) form;
			CancelOrderRequestModel model = new CancelOrderRequestModel();
			DataModelUtil.form2Model(request, cancelOrderForm, model, response);	
//			model.setChannelType(CLVSplitUtil.getChannelType(cancelOrderForm.getCLV()));
//			model.setLanguage(CLVSplitUtil.getLanguage(cancelOrderForm.getCLV()));
//			model.setVersionId(String.valueOf(CLVSplitUtil.getVersionID(cancelOrderForm.getCLV())));
//			model.setChannelId(SessionUtil.getChannelID(request));
			model.setPassword(cancelOrderForm.getPassword());
			if(!ValidateUtil.isBlankString(cancelOrderForm.getMCSOrderID())){
				model.setMCSOrderID(Long.parseLong(cancelOrderForm.getMCSOrderID()));
			}
			if(!ValidateUtil.isBlankString(cancelOrderForm.getMTSSOrderID())){
				model.setMTSSOrderID(Long.parseLong(cancelOrderForm.getMTSSOrderID()));
			}
			LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession().getAttribute(WEB_Constants.LOGIN_USER_INFO);
			model.setTransactionProtection(loginUserInfo.getTransactionProtection());

			UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
			if (user != null) {
				model.setLoginId(user.getLoginName());
				model.setAccountId(user.getTradeInfoObject().getTradingAccount());
				model.setCustCode(user.getTradeInfoObject().getCustCode());
			}			
			CancelOrderResponseModel responseModel = facade.cancelOrder(model);
			if(isExceptionOccured(responseModel)){
				setOldTokenToSession(request,oldToken);
			}
			ProcessBean processBean = new ProcessBean(responseModel,null, mapping,form, request,response);
			return vp.processCancelOrder(processBean);
	}

	private boolean isExceptionOccured(CancelOrderResponseModel responseModel) {
		return !Consts.Global.Status.NORMAL.equals(responseModel.getReturnCode());
	}
	
	private ActionForward doWebTimeout(		
			ViewProvider vp,
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response){
		BulkCancelOrderResponseModel responseModel = new BulkCancelOrderResponseModel();
		responseModel.setReturnCode(Consts.Error.Code.WEBSITE_TIMEOUT);
		responseModel.setResultForward(ForwardMappingUtil.getForward(Consts.Error.Code.SHOWMSG));
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping,form, request,response);
		return vp.processCancelOrder(processBean);
	}
	
	private ActionForward doInvalidToken(
		ViewProvider vp,
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response){		
			BulkCancelOrderResponseModel responseModel = new BulkCancelOrderResponseModel();
			responseModel.setReturnCode(Consts.Error.Code.ERRORCODE_TOKEN_INVALID);
			responseModel.setResultForward(ForwardMappingUtil.getForward(Consts.Error.Code.SHOWMSG));
			ProcessBean processBean = new ProcessBean(responseModel,null, mapping,form, request,response);
			return vp.processCancelOrder(processBean);
	}
}
