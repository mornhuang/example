package com.itsz.sht.struts.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.common.util.StringMap;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.response.AclRoleResponseModel;
import com.itsz.sht.common.model.common.response.AclRoleResponseModelList;
import com.itsz.sht.dao.hibernate.model.AclRole;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.struts.form.AclRoleForm;
import com.taifook.adminportal.common.Constants;

public class AclRoleAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
 
	public ActionForward goAddAclRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("success");
	}
	
	
	public ActionForward addAclRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		AclRoleForm aform=(AclRoleForm) form;
		AclRole aclRole=new AclRole();
		aclRole.setSelectFunctionsId(aform.getFunctionId());
		aclRole.setInitBy(user.getLognId());
		aclRole.setInitDate(new Date());
		aclRole.setUpdBy(user.getLognId());
		aclRole.setInitDate(new Date());
		aclRole.setDescr(aform.getDescr());
		aclRole.setRoleId(aform.getRoleId());
		String returnMessage="";
		String returnCode =facade.addAclRole(aclRole);
		
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			returnMessage="Add Failed : Role Id Exist";
			request.setAttribute("returnMessage", returnMessage);
			return mapping.findForward("failure");
		}
		
	}
	
	
	public ActionForward updateAclRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		AclRoleForm aform=(AclRoleForm) form;
		AclRole aclRole=facade.getAclRole(aform.getRoleId()).getAclRole();
		aclRole.setDescr(aform.getDescr());
		aclRole.setSelectFunctionsId(aform.getFunctionId());
		aclRole.setUpdBy(user.getLognId());
		aclRole.setUpdDate(new Date());
		String returnCode =facade.updateAclRole(aclRole);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	
	
	public ActionForward goUpdateAclRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String roleId=request.getParameter("roleId");
		AclRoleForm aform=(AclRoleForm) form;
		AclRoleResponseModel model=facade.getAclRole(roleId);
		AclRole role=model.getAclRole();
		aform.setRoleId(roleId);
		aform.setDescr(role.getDescr());
		String funIdStr="";
		String[] funId= role.getSelectFunctionsId();
		if(funId!=null&&funId.length>0){
			StringBuffer str=new StringBuffer();
			for (String s : funId) {
				str.append(s);
				str.append(",");
			}
			funIdStr= str.toString();
		}
		String returnCode =model.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("functionStr",funIdStr);
			request.setAttribute("aclRoleForm", aform);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward showAclRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String roleId=request.getParameter("roleId");
		AclRoleForm aform=(AclRoleForm) form;
		AclRoleResponseModel model=facade.getAclRole(roleId);
		AclRole role=model.getAclRole();
		aform.setRoleId(roleId);
		aform.setDescr(role.getDescr());
		String funIdStr="";
		String[] funId= role.getSelectFunctionsId();
		if(funId!=null&&funId.length>0){
			StringBuffer str=new StringBuffer();
			for (String s : funId) {
				str.append(s);
				str.append(",");
			}
			funIdStr= str.toString();
		}
		String returnCode =model.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("functionStr",funIdStr);
			request.setAttribute("aclRoleForm", aform);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	
	public ActionForward deleteAclRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclRoleForm aform=(AclRoleForm) form;
		String roleId=request.getParameter("roleId");
		String returnCode =facade.deleteRole(roleId);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			aform.setMessage(returnCode);
			return mapping.findForward("failure");
		}
	}
	

	
	public ActionForward listAclRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclRoleForm aform=(AclRoleForm) form;
		int pageNumber = getPageNumber(request); // get current page
		int pageSize = getPageSize(); // get page size;
		StringMap parmsMap=new StringMap();
		parmsMap.put("roleId", aform.getRoleId());
		AclRoleResponseModelList listAclRole =facade.listAclRole(parmsMap, pageNumber, pageSize);
		String returnCode=listAclRole.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("page", listAclRole.getPage());
			request.setAttribute("message", aform.getMessage());
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
}
