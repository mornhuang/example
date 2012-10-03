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
 * $Id: AddMobileAgentAction.java,v 1.3 2011/01/18 04:51:57 kyzou Exp $
 * @Project:admin-portal
 * @File:AddMobileAgentAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-27
 */
public class AddMobileAgentAction extends Action {
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Log log = LogFactory.getLog(this.getClass()); 
		String forward=Constants.SUCCESS;
		MobileAgentForm addForm=(MobileAgentForm)form;
		addForm.setParamValue(addForm.getParamValue().trim());
		CsSetParameter addParameter=new CsSetParameter();
		CsSetParameterKey key=new CsSetParameterKey(addForm.getParamName(),addForm.getParamValue());
		addParameter.setId(key);
		addParameter.setDescription(addForm.getDescription());
		addParameter.setUpdateTime(new Date());
		try {
			((MobileAgentDAO)ServiceManager.getInstance()
					                       .getService("com.taifook.adminportal.service.MobileAgentService"))
			                               .save(addParameter);
			request.setAttribute(Constants.RETURN_BACK_URL,"../queryMobileAgent.do?paramName="+addForm.getParamName()+"&paramValue="+addForm.getParamValue());
			new ClientSocketService().startSend(new SocketMessage(Constants.NOTIFY_PARAMETER,Constants.ADD_ACTION,addParameter,new SynMobileAgentCallBack()));
		} catch (SocketTransferException e) {
			log.error("addMobileAgentAction-executeAction:add Exception!");
			log.error(e);
			request.setAttribute(Constants.GLOBAL_ERROR,"add MobileAgent to database success! but "+e.getMessage());			
			forward=Constants.FAILURE;
		}catch (Exception e) {
			log.error("addMobileAgentAction-execute:add Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,"add MobileAgent fail! "+e.getMessage());
			forward=Constants.FAILURE;
		}
		return mapping.findForward(forward);
	}
}
