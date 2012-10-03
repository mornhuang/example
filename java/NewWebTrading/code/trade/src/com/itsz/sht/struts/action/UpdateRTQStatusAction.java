package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.List;

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
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.common.model.common.request.UpdateUserProductRequestModel;
import com.itsz.sht.common.model.common.response.UserProductResponseModel;
import com.itsz.sht.common.model.request.VerifyPasswordRequestModel;
import com.itsz.sht.common.model.response.VerifyPasswordResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.dao.hibernate.model.UsrProdId;
import com.itsz.sht.model.UserProductRequest;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.struts.form.UpdateRTQStatusForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: UpdateRTQStatusAction.java,v 1.7 2011/02/17 08:31:47 kyzou Exp $
 * @Project:NewWebTrading
 * @File:UpdateRTQStatusAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-17
 */
public class UpdateRTQStatusAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UpdateRTQStatusForm initForm = (UpdateRTQStatusForm) form;
		UserProductResponseModel updateRTQStatusResp = new UserProductResponseModel();
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);		
		UpdateUserProductRequestModel updateRTQStatusModel = new UpdateUserProductRequestModel();
		UserProductRequest userProduct = new UserProductRequest();
		userProduct.setId(new UsrProdId(user.getTradeInfoObject().getCustCode(),initForm.getProductId()));
		userProduct.setAllwRenl(initForm.getAllwRenl()!=null?initForm.getAllwRenl():Consts.Global.Flag.NEGATIVE);
		List<UserProductRequest> userProductRequestList = new ArrayList<UserProductRequest>();
		userProductRequestList.add(userProduct);
		updateRTQStatusModel.setUserProductRequestList(userProductRequestList);
		form2Model(request, (UpdateRTQStatusForm) form, updateRTQStatusModel, response);
		
        VerifyPasswordRequestModel verifyPassReq = new VerifyPasswordRequestModel();
        DataModelUtil.form2Model(request, initForm, verifyPassReq, response);
        verifyPassReq.setLoginId(user.getLoginName());
        verifyPassReq.setPassword(initForm.getPassword());
        boolean canApply = true;
		VerifyPasswordResponseModel verifyPassResp = facade.verifyPassword(verifyPassReq);
		if(Consts.Global.Status.NORMAL.equalsIgnoreCase(verifyPassResp.getReturnCode()) || 
				   Consts.Global.Status.SUCCESS.equalsIgnoreCase(verifyPassResp.getReturnCode())){
			canApply = verifyPassResp.getPasswordMatch().equals(Consts.Global.Flag.POSITIVE)?true:false;;
		}else{
			canApply = false;
		}
		if(!canApply){
			updateRTQStatusResp.setReturnCode(Consts.Error.Code.TRAD_ERROR_WRONGPASS);
			ProcessBean processBean = new ProcessBean(updateRTQStatusResp, null, mapping, request, response);
			return vp.processUpdateRTQStatus(processBean);
		}
		
		updateRTQStatusResp = facade.updateUserProductStatus(updateRTQStatusModel);
		ProcessBean processBean = new ProcessBean(updateRTQStatusResp, null, mapping, request, response);
		return vp.processUpdateRTQStatus(processBean);
	}
	
	public static void form2Model(HttpServletRequest request,
			ITSZForm origForm, AbstractRequestModel destModel, HttpServletResponse response) {
		try {
			BeanCopyUtils.copyProperties(destModel, origForm);
			if(StringUtils.isBlank(origForm.getCLV())) origForm.setCLV(CLVSplitUtil.transNullCLV(request,response,origForm,""));
		} catch (Exception e) {
//			e.printStackTrace();
		}finally{
			destModel.setChannelType(CLVSplitUtil.getChannelType(origForm.getCLV()));
			destModel.setLanguage(CLVSplitUtil.getLanguage(origForm.getCLV()));
			destModel.setVersionId(String.valueOf(CLVSplitUtil.getVersionID(origForm.getCLV())));
			destModel.setChannelId(CLVSplitUtil.getChannelId(origForm.getCLV()));
		}
	}

}
