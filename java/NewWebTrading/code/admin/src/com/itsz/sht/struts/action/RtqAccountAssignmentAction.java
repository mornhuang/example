package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.request.ListRtqAccountAssRequestModel;
import com.itsz.sht.common.model.common.request.RTQAccountAssignmentRequestModel;
import com.itsz.sht.common.model.common.response.ListRtqAccountAssResponseModel;
import com.itsz.sht.common.model.common.response.RTQAccountAssignmentResponseModel;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.struts.form.RtqAccountAssignmentForm;
import com.taifook.adminportal.common.Constants;



public class RtqAccountAssignmentAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
	
	public ActionForward showRtqAccountAssignment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RtqAccountAssignmentForm afrom=(RtqAccountAssignmentForm)form;
//		String productId=request.getParameter("productId");
//		String rtqLoginId=request.getParameter("rtqLoginId");
//		String clientId=request.getParameter("clientId");
		String retrunCode="";
		if (retrunCode!=null&&retrunCode.equals("SUCCESS")) {
			request.setAttribute("rtqAccAss", afrom);
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward listRtqAccountAssignment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RtqAccountAssignmentForm afrom=(RtqAccountAssignmentForm)form;
		RTQAccountAssignmentRequestModel rmodel=new RTQAccountAssignmentRequestModel();
		rmodel.setProductId(afrom.getProdId());
		int pageNumber = getPageNumber(request); // get current page
		int pageSize = getPageSize(); // get page size;
		rmodel.setPageNumber(pageNumber);
		rmodel.setPageSize(pageSize);
		RTQAccountAssignmentResponseModel repModel=	facade.findRTQAccountAssignmentByProductId(rmodel);
		String retrunCode=repModel.getReturnCode();
		request.setAttribute("page", repModel.getPage());
		request.setAttribute("rtqAccountAssignmentForm", afrom);
		if (retrunCode!=null&&retrunCode.equals("SUCCESS")) {
		
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward queryRtqAccountAssignment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RtqAccountAssignmentForm afrom=(RtqAccountAssignmentForm)form;
		RTQAccountAssignmentRequestModel rmodel=new RTQAccountAssignmentRequestModel();
		rmodel.setProductId(afrom.getProdId());
		int pageNumber = getPageNumber(request); // get current page
		int pageSize = getPageSize(); // get page size;
		rmodel.setPageNumber(pageNumber);
		rmodel.setPageSize(pageSize);
		RTQAccountAssignmentResponseModel repModel=	facade.findRTQAccountAssignmentByProductId(rmodel);
		String retrunCode=repModel.getReturnCode();
		request.setAttribute("page", repModel.getPage());
		if (retrunCode!=null&&retrunCode.equals("SUCCESS")) {
		
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward listRtqAccountAssByProductId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String productId=request.getParameter("productId");
		ListRtqAccountAssRequestModel reqModel=new ListRtqAccountAssRequestModel();
		reqModel.setProductId(productId);
		String returnCode="";
		ListRtqAccountAssResponseModel resModel=facade.listRtqAccountAssByProductId(reqModel);
		returnCode=resModel.getReturnCode();
		if (returnCode!=null&&returnCode.equals("SUCCESS")) {
			request.setAttribute("productId", productId);
			request.setAttribute("rtqAccountAssDtoList", resModel.getRtqAccountAssDtoList());
			return mapping.findForward("success");
		}else{
			return mapping.findForward("failure");
		}
	}
	
	
}