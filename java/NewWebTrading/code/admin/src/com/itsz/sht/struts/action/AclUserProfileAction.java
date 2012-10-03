package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.itsz.common.util.StringMap;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.PasswordEncryptUtil;
import com.itsz.sht.common.model.common.response.AclRoleResponseModelList;
import com.itsz.sht.common.model.common.response.AclUserResponseModel;
import com.itsz.sht.common.model.common.response.AclUserResponseModelList;
import com.itsz.sht.dao.hibernate.model.AclRole;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.struts.form.AclUserProfileForm;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.util.page.Page;



public class AclUserProfileAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public ActionForward goAddAclUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		AclRoleResponseModelList  roleModelList=facade.listAclRole();
		String returnCode=roleModelList.getReturnCode();
		List<LabelValueBean> statusList=new ArrayList<LabelValueBean>();
		statusList.add(new LabelValueBean("Normal","Y"));
		statusList.add(new LabelValueBean("Unnormal","N"));
		List<LabelValueBean> roleList=new ArrayList<LabelValueBean>();
		List<AclRole> roles=roleModelList.getAclRoleList();
		for (AclRole aclRole : roles) {
			roleList.add(new LabelValueBean(aclRole.getRoleId(),aclRole.getRoleId()));
		}
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("statusList", statusList);
			request.setAttribute("roleList", roleList);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward addAclUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		AclUserProfileForm aform = (AclUserProfileForm) form;
		AclUserProfile aclUser =new AclUserProfile();
		this.formToBean(aform,aclUser);
		String password = com.itsz.sht.common.LoginControlUtilizer.genPwd(
				Consts.AdminPortal.aclUserProfile.DEFAULT_PWD_LENGTH,
				Consts.AdminPortal.aclUserProfile.DEFAULT_PWD_TYPE);
		aclUser.setPwd(PasswordEncryptUtil.encrypt(password));
		aclUser.setInitBy(user.getLognId());
		aclUser.setInitDate(new Date());
		aclUser.setUpdBy(user.getLognId());
		aclUser.setUpdDate(new Date());
		String returnCode="";
		String returnMessage="";
	returnCode = facade.addAclUser(aclUser);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
		    request.setAttribute("loginId", aclUser.getLognId());
		    request.setAttribute("passWord", password);
		return mapping.findForward("success");
		} else {
			returnMessage="The Login ID you input has been existed!";
			request.setAttribute("returnMessage", returnMessage);
			return mapping.findForward("failure");
		}
	}
	public ActionForward updateAclUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		AclUserProfileForm aform = (AclUserProfileForm) form;
		AclUserProfile aclUser = facade.getAclUser(aform.getLognId()).getAclUserProfile();
		this.formToBean(aform,aclUser);
		aclUser.setUpdBy(user.getLognId());
		aclUser.setUpdDate(new Date());
		String returnCode = facade.updateAclUser(aclUser);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward deleteAclUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		String returnCode = facade.deleteAclUser(loginId);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward showAclUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String loginId = request.getParameter("loginId");
		AclUserResponseModel model= facade.getAclUser(loginId);
		String returnCode = model.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("aclUserProfile", model.getAclUserProfile());
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward goUpdateAclUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		AclUserProfileForm aform = (AclUserProfileForm) form;
		String loginId = request.getParameter("loginId");
		AclUserResponseModel model= facade.getAclUser(loginId);
	    this.beanToForm(model.getAclUserProfile(),aform);
		String returnCode = model.getReturnCode();
		List<LabelValueBean> statusList=new ArrayList<LabelValueBean>();
		statusList.add(new LabelValueBean("Normal","Y"));
		statusList.add(new LabelValueBean("Unnormal","N"));
		List<LabelValueBean> roleList=new ArrayList<LabelValueBean>();
		AclRoleResponseModelList  roleModelList=facade.listAclRole();
		List<AclRole> roles=roleModelList.getAclRoleList();
		for (AclRole aclRole : roles) {
			roleList.add(new LabelValueBean(aclRole.getRoleId(),aclRole.getRoleId()));
		}
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("statusList", statusList);
			request.setAttribute("roleList", roleList);
			request.setAttribute("aclUserProfileForm", aform);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward listAclUserProfile(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		AclUserProfileForm aform = (AclUserProfileForm) form;
		StringMap paramsMap=new StringMap();
		paramsMap.put("loginId", aform.getLognId());
		paramsMap.put("roleId", aform.getRoleId());
		paramsMap.put("userName", aform.getUsrNme());
		int pageNumber = getPageNumber(request); // get current page
		int pageSize = getPageSize(); // get page size;
		AclUserResponseModelList modelList=	facade.listAclUser(paramsMap, pageNumber, pageSize);
	    Page page=modelList.getPage();
		String returnCode = modelList.getReturnCode();
		String returnMessage="";
		 int count=page.getTotalNumberOfElements();
		    if(count==0){
		    	returnMessage="User not found by Login ID ["+aform.getLognId()+"]!";
		    	  request.setAttribute("returnMessage", returnMessage);
		    }
		  
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("page", page);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward loginAclUserProfile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfileForm aform = (AclUserProfileForm) form;
		String loginId=aform.getLognId();
		String pwd=aform.getPwd();
		AclUserResponseModel userModel=	facade.loginAclUserProfile(loginId);
		AclUserProfile user=userModel.getAclUserProfile();
		String returnCode="";
		String loginMessage="";
		if(user!=null){
			if(user.getStatus().equals("Y")){//用户状态为Y才能登录						
				if(PasswordEncryptUtil.encrypt(pwd).equals(user.getPwd())){
					//用户名和密码正确才取功能权限
					HttpSession session = request.getSession(true);
					if(user.getRoleId()!=null&&!"".equals(user.getRoleId())){
					AclRole role=null;
					role=facade.getAclRole(user.getRoleId()).getAclRole();
					if(role!=null){
						user.setAclRole(role);
					  }
					}
					returnCode="SUCCESS";
					session.setAttribute(Consts.AdminPortal.userContext.USERCONTEXT, user);
					ServletContext application = request.getSession().getServletContext();
					List<String> loginList = new ArrayList<String>();
					loginList = (ArrayList<String>)application.getAttribute("loginList");
					if(loginList == null){
						loginList = new ArrayList<String>();
					}
					if(!loginList.contains(user.getLognId())){
						loginList.add(user.getLognId());
						application.setAttribute("loginList", loginList);
					}					
				}else{   //密码不正确
					returnCode="FAILED";
					loginMessage="  Password error!";
				}
			}else{
				returnCode="FAILED"; 
				loginMessage="  Login Id Status Unavail!";
			}
		}else{ //用户不存在
			returnCode="FAILED";
			loginMessage="  Login Id not exist!";
		}
		request.setAttribute("loginMessage", loginMessage);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	
	public ActionForward changePassWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		String returnCode = "";
		String retrunMessage="";
		AclUserProfileForm aform = (AclUserProfileForm) form;
		String oldPwd=PasswordEncryptUtil.encrypt(aform.getPwd());
		String newPwd=PasswordEncryptUtil.encrypt(aform.getNewpwd());
		AclUserResponseModel model= facade.getAclUser(user.getLognId());
		AclUserProfile aclUser=model.getAclUserProfile();
		String pwd=aclUser.getPwd();
		if(pwd!=null&&!("").equals(pwd)){
			if(pwd.equals(oldPwd)){
				aclUser.setPwd(newPwd);
				aclUser.setUpdBy(user.getLognId());
				aclUser.setUpdDate(new Date());
				returnCode=	facade.updateAclUser(aclUser);
				retrunMessage="Change PassWord Success!";
			}else{
				returnCode="FAILED";
				retrunMessage="Old PassWord error!";
			}
		}
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("retrunMessage", retrunMessage);
			return mapping.findForward("success");
		} else {
			request.setAttribute("retrunMessage", retrunMessage);
			return mapping.findForward("failure");
		}
	}
	
	
	public ActionForward resetPassWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		String returnCode = "";
		String retrunMessage="";
		String password="";
		AclUserProfileForm aform = (AclUserProfileForm) form;
		AclUserResponseModel model= facade.getAclUser(aform.getLognId());
		AclUserProfile aclUser=model.getAclUserProfile();
		if(aclUser!=null){
			 password = com.itsz.sht.common.LoginControlUtilizer.genPwd(
						Consts.AdminPortal.aclUserProfile.DEFAULT_PWD_LENGTH,
						Consts.AdminPortal.aclUserProfile.DEFAULT_PWD_TYPE);
				        aclUser.setPwd(PasswordEncryptUtil.encrypt(password));
						aclUser.setUpdBy(user.getLognId());
						aclUser.setUpdDate(new Date());
						returnCode=	facade.updateAclUser(aclUser);
						retrunMessage="Reset PassWord Success!";
		}else{
			retrunMessage="User login id not found!";
		}
		
		
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("newPassWord",password);
			request.setAttribute("retrunMessage", retrunMessage);
			return mapping.findForward("success");
		} else {
			request.setAttribute("retrunMessage", retrunMessage);
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward goChangePassWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		AclUserProfileForm aform = (AclUserProfileForm) form;
		aform.setLognId(user.getLognId());
		String returnCode = "SUCCESS";
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
		   request.setAttribute("aclUserProfileForm", aform);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	
	public ActionForward goResetPassWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		AclUserProfileForm aform = (AclUserProfileForm) form;
		aform.setLognId(user.getLognId());
		String returnCode = "SUCCESS";
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
		   request.setAttribute("aclUserProfileForm", aform);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	
	private void formToBean(AclUserProfileForm aform,AclUserProfile aclUser) {
		
		aclUser.setLognId(aform.getLognId());
		aclUser.setDeptNme(aform.getDeptNme());
		aclUser.setEmailAddr(aform.getEmailAddr());
		aclUser.setRoleId(aform.getRoleId());
		aclUser.setStatus(aform.getStatus());
		aclUser.setUsrNme(aform.getUsrNme());
		aclUser.setInitBy(aform.getInitBy());
		aclUser.setInitDate(aform.getInitDate());
		
	}

	private void beanToForm(AclUserProfile aclUser,AclUserProfileForm aform) {
		aform.setLognId(aclUser.getLognId());
		aform.setDeptNme(aclUser.getDeptNme());
		aform.setEmailAddr(aclUser.getEmailAddr());
		aform.setRoleId(aclUser.getRoleId());
		aform.setStatus(aclUser.getStatus());
		aform.setUsrNme(aclUser.getUsrNme());
		aform.setInitBy(aclUser.getInitBy());
		aform.setInitDate(aclUser.getInitDate());
		
	}
}
