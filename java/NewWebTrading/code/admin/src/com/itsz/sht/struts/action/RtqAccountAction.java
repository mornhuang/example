package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.request.DeleteRTQAccountLastNRequestModel;
import com.itsz.sht.common.model.common.request.DeleteRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.RTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.SaveRTQAccountRequestModel;
import com.itsz.sht.common.model.common.request.UpdateRTQAccountRequestModel;
import com.itsz.sht.common.model.common.response.DeleteRTQAccountLastNResponseModel;
import com.itsz.sht.common.model.common.response.DeleteRTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.RTQAccountResponseModel;
import com.itsz.sht.common.model.common.response.RTQAccountViewResponseModel;
import com.itsz.sht.common.model.common.response.UpdateRTQAccountResponseModel;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.Product;
import com.itsz.sht.struts.form.RtqAccountForm;
import com.taifook.adminportal.common.Constants;

public class RtqAccountAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ActionForward goOperateRtqAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<LabelValueBean> productList =new ArrayList<LabelValueBean>();
		List<Product> prodList=facade.listServiceProduct().getProductList();
		for (Product product : prodList) {
			productList.add(new LabelValueBean(product.getProdId(),product.getProdId()));
		}
		request.setAttribute("productList", productList);
		return mapping.findForward("success");
	}
	
	

	public ActionForward deleteRTQAccountBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RtqAccountForm aform=(RtqAccountForm) form;
		DeleteRTQAccountRequestModel model=new DeleteRTQAccountRequestModel();
		model.setProductId(aform.getProdId());
		model.setrTQLoginIdFrom(aform.getRtqLognId()+aform.getIdFrom());
		model.setrTQLoginIdTo(aform.getRtqLognId()+aform.getIdTo());
		String returnCode="";
		DeleteRTQAccountResponseModel respMode=facade.deleteRTQAccount(model);
		returnCode=respMode.getReturnCode();
	   List<String> undeleteList=respMode.getRtqLoginIdList();
	   if(undeleteList!=null&&undeleteList.size()>0){
		   String returnMessage= "以下RTQ账号被使用不能删除"+undeleteList.toString();
		   request.setAttribute("returnMessage", returnMessage);
	   }
		request.setAttribute("dispalyState", "del");
		   if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
	}
	
	
	
	
	public ActionForward updateRTQAccountBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RtqAccountForm aform=(RtqAccountForm) form;
		UpdateRTQAccountRequestModel model=new UpdateRTQAccountRequestModel();
		model.setProductId(aform.getProdId());
		model.setrTQLoginIdFrom(aform.getRtqLognId()+aform.getIdFrom());
		model.setrTQLoginIdTo(aform.getRtqLognId()+aform.getIdTo());
		model.setrTQLoginPassword(aform.getRtqLognPwd());
		UpdateRTQAccountResponseModel resModel = facade.updateRTQAccount(model);
		String returnCode=resModel.getReturnCode();
		List<String> rtqLognIdList = resModel.getRtqLognIdList();
		request.setAttribute("dispalyState", "changePsd");
		if(rtqLognIdList!=null && rtqLognIdList.size()>0){
			request.setAttribute("returnMessage", "以下RTQ账号未成功修改密码"+rtqLognIdList.toString()+"，请确定账号存在后再进行操作。");
		}
	    if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward deleteRTQAccountLastN(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RtqAccountForm aform=(RtqAccountForm) form;
		DeleteRTQAccountLastNRequestModel model=new DeleteRTQAccountLastNRequestModel();
		model.setProductId(aform.getProdId());
		model.setN(aform.getLastN());
		String returnCode="";
		DeleteRTQAccountLastNResponseModel respMode=facade.deleteRTQAccountLastN(model);
		returnCode=respMode.getReturnCode();
		request.setAttribute("dispalyState", "delLastN");
		List<String> undeleteList=respMode.getRtqLoginIdList();
	    if(undeleteList!=null&&undeleteList.size()>0){
		    String returnMessage= "以下RTQ账号被使用不能删除"+undeleteList.toString();
		    request.setAttribute("returnMessage", returnMessage);
	    }
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward saveRTQAccountBatch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		RtqAccountForm aform=(RtqAccountForm) form;
		SaveRTQAccountRequestModel model=new SaveRTQAccountRequestModel();
		model.setProductId(aform.getProdId());
		model.setrTQLoginIdFrom(aform.getRtqLognId()+aform.getIdFrom());
		model.setrTQLoginIdTo(aform.getRtqLognId()+aform.getIdTo());
		model.setrTQLoginPassword(aform.getRtqLognPwd());
		model.setUserName(user.getLognId());
		String returnCode="";
		String returnMessage="";
		returnCode=facade.saveRTQAccount(model).getReturnCode();
		request.setAttribute("dispalyState", "add");
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			returnMessage="账号存在重复，操作失败！";
			request.setAttribute("returnMessage", returnMessage);
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward listRTQAccountView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List<LabelValueBean> productList =new ArrayList<LabelValueBean>();
		   List<Product> prodList=facade.listServiceProduct().getProductList();
		   for (Product product : prodList) {
			   if(product.getProdId().startsWith("SSTR")){
				   productList.add(new LabelValueBean(product.getProdId(),product.getProdId()));
			   }
		  }
		request.setAttribute("productList", productList);
		RTQAccountViewResponseModel viewModel=	facade.listRTQAccountView();
		String returnCode = "";
		returnCode=viewModel.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("rtqAccountViewList", viewModel.getRtqAccountViewList());
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward listRtqAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RtqAccountForm aform=(RtqAccountForm) form;
		RTQAccountRequestModel reqModel=new RTQAccountRequestModel();
		reqModel.setProductId(aform.getProdId());
		reqModel.setRtqLoginId(aform.getRtqLognId());
		int pageNumber = getPageNumber(request); // get current page
		int pageSize = getPageSize(); // get page size;
		reqModel.setPageSize(pageSize);
		reqModel.setPageNumber(pageNumber);
		RTQAccountResponseModel resMolel=	facade.listRtqAccountByProIdOrLoginId(reqModel);
		String returnCode="";
		returnCode=resMolel.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("page", resMolel.getPage());
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	
	}
}
