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

import com.itsz.sht.admin.service.syndata.SynMobileAgentCallBack;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.MobileAgentDAO;
import com.taifook.adminportal.exceptions.SocketTransferException;
import com.taifook.adminportal.model.CsSetParameter;
import com.taifook.adminportal.model.CsSetParameterKey;
import com.taifook.adminportal.web.mobileAgent.forms.MobileAgentForm;
import com.taifook.common.socket.ClientSocketService;
import com.taifook.common.socket.SocketMessage;

/**
 * $Id: DelMobileAgentAction.java,v 1.3 2011/01/18 04:51:57 kyzou Exp $
 * @Project:admin-portal
 * @File:DelMobileAgentAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-27
 */
public class DelMobileAgentAction extends Action {
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Log log = LogFactory.getLog(this.getClass()); 
		String forward=Constants.SUCCESS;
		MobileAgentForm delForm=(MobileAgentForm)form;
		CsSetParameter delParameter=new CsSetParameter();
		CsSetParameterKey key=new CsSetParameterKey(delForm.getParamName(),delForm.getParamValue());
		delParameter.setId(key);
		delParameter.setDescription(delForm.getDescription());
		delParameter.setUpdateTime(new Date());		
		try {
			((MobileAgentDAO)ServiceManager.getInstance()
					                       .getService("com.taifook.adminportal.service.MobileAgentService"))
			                               .delete(delParameter);
			request.setAttribute(Constants.RETURN_BACK_URL,"../queryMobileAgent.do?paramName="+delForm.getParamName()+"&paramValue="+delForm.getParamValue());
			new ClientSocketService().startSend(new SocketMessage(Constants.NOTIFY_PARAMETER,Constants.DEL_ACTION,delParameter,new SynMobileAgentCallBack()));
		} catch (SocketTransferException e) {
			log.error("delMobileAgentAction-executeAction:update Exception!");
			log.error(e.getMessage());		
			request.setAttribute(Constants.GLOBAL_ERROR,"del MobileAgent to database success! but "+e.getMessage());			
			forward=Constants.FAILURE;
		}catch (Exception e) {
			log.error("delMobileAgentAction-execute:del Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,"del MobileAgent fail! "+e.getMessage());
			forward=Constants.FAILURE;
		}
		return mapping.findForward(forward);
	}
}
