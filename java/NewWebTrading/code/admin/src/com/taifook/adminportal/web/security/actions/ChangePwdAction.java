package com.taifook.adminportal.web.security.actions;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;


/** 
 * MyEclipse Struts
 * Creation date: 05-15-2006
 * 
 * XDoclet definition:
 * @struts.action path="/changepwd" name="ChangePwdForm" scope="request"
 * @struts.action-forward name="success" path="/jsp/changepassword.jsp"
 * @struts.action-forward name="fail" path="/jsp/fail.jsp"
 */
public class ChangePwdAction extends BaseAction{
	
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HttpSession session = request.getSession();
//		Log log = LogFactory.getLog(this.getClass());
//		log.info("execute change pwssword......");
		String forward = null;
//		boolean isSucess = false;
//				
//		ChangePwdForm changePwdForm = (ChangePwdForm) form;
//		String userid = changePwdForm.getUserId();
//		String oldPwd = changePwdForm.getOldPwd();
//		String newPwd = changePwdForm.getNewPwd();
//		ActionErrors errors = new ActionErrors();
//		try{
//		isSucess = ((SecurityDAO) ServiceManager.getInstance().getService(
//				"com.taifook.adminportal.service.SecurityService"))
//				.changePwd(userid,oldPwd,newPwd);
//		if (isSucess) {
//			forward=Constants.SUCCESS;
//		} else {
//			forward=Constants.FAILURE;
//		}
//		}catch(Exception e){
//			//errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.changepassword_fail"));
//			request.setAttribute(Constants.GLOBAL_ERROR,e.getMessage());
//			forward=Constants.FAILURE;
//		}
//		
//		if (!errors.isEmpty()) {
//			this.saveErrors(request, errors);
//		}
		return mapping.findForward(forward);
	}

}
