package com.itsz.sht.struts.action;

import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.SessionUtil;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.AbstractRequestModel;
import com.itsz.sht.common.model.common.request.ListSelectServiceRequestModel;
import com.itsz.sht.common.model.common.request.RTQProductRequestModel;
import com.itsz.sht.common.model.common.request.UserProfileRequestModel;
import com.itsz.sht.common.model.common.response.ListSelectServiceResponseModel;
import com.itsz.sht.common.model.common.response.RTQProductResponseModel;
import com.itsz.sht.common.model.common.response.UserProfileResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.dao.hibernate.model.UserProduct;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: EnquireRTQProductListAction.java,v 1.14 2011/02/17 07:42:00 kyzou Exp $
 * @Project:NewWebTrading
 * @File:EnquireRTQProductListAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-22
 */
public class EnquireRTQProductListAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		//getExistingServiceProductList
		ListSelectServiceRequestModel listSelectRTQModel = new ListSelectServiceRequestModel();
		form2Model(request, (ITSZForm) form, listSelectRTQModel, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		listSelectRTQModel.setClientId(user.getTradeInfoObject().getCustCode());
		UserProduct existProduct = null;
		UserProductAllotment productAllotment = null;
		ListSelectServiceResponseModel listSelectRTQResp = facade.listSelectService(listSelectRTQModel);
		if(listSelectRTQResp.getExistingServiceProductList()!=null && listSelectRTQResp.getExistingServiceProductList().size()>0){
			for (Iterator iterator = listSelectRTQResp.getExistingServiceProductList().iterator(); iterator.hasNext();) {
				existProduct = (UserProduct) iterator.next();
			}
		}
		if(listSelectRTQResp.getSubscriberServiceProductList()!=null && listSelectRTQResp.getSubscriberServiceProductList().size()>0){
			for (Iterator iterator = listSelectRTQResp.getSubscriberServiceProductList().iterator(); iterator.hasNext();) {
				productAllotment = (UserProductAllotment) iterator.next();
			}			
		}
		if(existProduct!=null){
			request.setAttribute("existProduct", existProduct);
		}
		if(productAllotment!=null){
			request.setAttribute("productAllotment", productAllotment);
		}
		//ChinaDiscountFlag
		UserProfileRequestModel userProfReq = new UserProfileRequestModel();
		form2Model(request, (ITSZForm) form, userProfReq, response);
		userProfReq.setDefaultDebitAccount(user.getTradeInfoObject().getTradingAccount());
		userProfReq.setClientId(user.getTradeInfoObject().getCustCode());
		userProfReq.setClntLoginId(user.getLoginName());
		UserProfileResponseModel userProfResp = facade.findClientUserProfile(userProfReq);
		if(Consts.Global.Status.NORMAL.equalsIgnoreCase(userProfResp.getReturnCode()) || 
				Consts.Global.Status.SUCCESS.equalsIgnoreCase(userProfResp.getReturnCode())){
			request.getSession().setAttribute("ChinaDiscountFlag", userProfResp.getChinaDiscountFlag());
		}
		//getRTQProductList
		RTQProductRequestModel rtqProductRequestModel = new RTQProductRequestModel();
		form2Model(request, (ITSZForm) form, rtqProductRequestModel, response);
		rtqProductRequestModel.setClientId(user.getTradeInfoObject().getCustCode());
		RTQProductResponseModel rtqProductResp = facade.getRTQProductList(rtqProductRequestModel);
		ProcessBean processBean = new ProcessBean(rtqProductResp, null, mapping, request, response);
		return vp.processRTQProductList(processBean);
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
			destModel.setChannelId(SessionUtil.getChannelID(request));
		}
	}
}
