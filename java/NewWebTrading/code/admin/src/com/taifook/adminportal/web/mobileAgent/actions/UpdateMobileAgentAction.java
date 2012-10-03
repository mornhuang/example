package com.taifook.adminportal.web.mobileAgent.actions;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.MobileAgentDAO;
import com.taifook.adminportal.exceptions.SocketTransferException;
import com.taifook.adminportal.model.CsSetParameter;
import com.taifook.adminportal.model.CsSetParameterKey;
import com.taifook.adminportal.web.mobileAgent.forms.MobileAgentForm;

/**
 * $Id: UpdateMobileAgentAction.java,v 1.2 2010/11/09 04:31:54 kyzou Exp $
 * @Project:admin-portal
 * @File:UpdateMobileAgentAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-27
 */
public class UpdateMobileAgentAction extends Action {
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Log log = LogFactory.getLog(this.getClass()); 
		String forward=Constants.SUCCESS;
		MobileAgentForm updateForm=(MobileAgentForm)form;
		CsSetParameter updateParameter=new CsSetParameter();
		CsSetParameterKey key=new CsSetParameterKey(updateForm.getParamName(),updateForm.getParamValue());
		updateParameter.setId(key);
		updateParameter.setDescription(updateForm.getDescription());
		updateParameter.setUpdateTime(new Date());		
		try {
			((MobileAgentDAO)ServiceManager.getInstance()
					                       .getService("com.taifook.adminportal.service.MobileAgentService"))
			                               .update(updateParameter);
			request.setAttribute(Constants.RETURN_BACK_URL,"../queryMobileAgent.do?paramName="+updateForm.getParamName()+"&paramValue="+updateForm.getParamValue());
//			new ClientSocketService().startSend(new SocketMessage(Constants.NOTIFY_PARAMETER,Constants.UPDATE_ACTION,updateParameter,new SynParameterCallBack()));
		} catch (SocketTransferException e) {
			log.error("UpdateMobileAgentAction-executeAction:update Exception!");
			log.error(e.getMessage());		
			request.setAttribute(Constants.GLOBAL_ERROR,"update MobileAgent to database success! but "+e.getMessage());			
			forward=Constants.FAILURE;
		}catch (Exception e) {
			log.error("UpdateMobileAgentAction-execute:update Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,"update MobileAgent fail! "+e.getMessage());
			forward=Constants.FAILURE;
		}
		return mapping.findForward(forward);
	}
}
