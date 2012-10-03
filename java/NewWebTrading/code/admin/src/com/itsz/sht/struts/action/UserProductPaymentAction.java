package com.itsz.sht.struts.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.request.ListUserPaymentByManualRequestModel;
import com.itsz.sht.common.model.common.request.UpdateUserPaymentByManualRequestModel;
import com.itsz.sht.common.model.common.response.ListUserPaymentByManualResponseModel;
import com.itsz.sht.struts.form.UserProductPaymentForm;
import com.taifook.adminportal.common.util.page.Page;


public class UserProductPaymentAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	public ActionForward rtqMemoDebitList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserProductPaymentForm uppForm=(UserProductPaymentForm)form;
		Date startDate=null;
		Date endDate=null;
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
		if(uppForm.getStartDate()!=null && !(uppForm.getStartDate().equals(""))){
			try {
				startDate=sdf.parse(uppForm.getStartDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(uppForm.getEndDate()!=null && !(uppForm.getEndDate().equals(""))){
			try {
				endDate=sdf.parse(uppForm.getEndDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		ListUserPaymentByManualRequestModel reqModel=new ListUserPaymentByManualRequestModel();
		reqModel.setStartTime(startDate);
		reqModel.setEndTime(endDate);
		reqModel.setPageNumber(getPageNumber(request));
		reqModel.setPageSize(getPageSize());
		ListUserPaymentByManualResponseModel listUserPaymentByManualResponseModel=facade.getUserProductPaymentListByManual(reqModel);
		Page page =listUserPaymentByManualResponseModel.getPage();
		request.setAttribute("page", page);
		return mapping.findForward("success");
	}

	public ActionForward rtqMemoDebitUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		if(request.getParameterValues("check")==null){
			return mapping.findForward("update");
		}else {
			String usrProdPayIds[]=request.getParameterValues("check");
			for (int i = 0; i < usrProdPayIds.length; i++) {
				usrProdPayIds[i]=usrProdPayIds[i].substring(0, usrProdPayIds[i].length()-1).trim();
			}
			UpdateUserPaymentByManualRequestModel updateManualRequest=new UpdateUserPaymentByManualRequestModel();
			updateManualRequest.setUsrProdPayIds(usrProdPayIds);
			String message="Confirm Success";
			if(facade.updateUserProductPaymentByManual(updateManualRequest).getReturnCode()==Consts.Error.Code.NORMAL){
				message="Confirm Failure";
				request.setAttribute("message", message);
				return mapping.findForward("failure");
			}
			request.setAttribute("message", message);
			return mapping.findForward("success");
		    
		}
	}
}
