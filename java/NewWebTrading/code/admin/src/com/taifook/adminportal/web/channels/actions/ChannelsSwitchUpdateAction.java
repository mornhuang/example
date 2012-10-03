//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.channels.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.admin.service.syndata.SynChannelCallBack;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.ParameterDAO;
import com.taifook.adminportal.exceptions.SocketTransferException;
import com.taifook.adminportal.model.CsParameter;
import com.taifook.adminportal.web.channels.forms.ChannelsSwitchUpdateForm;
import com.taifook.adminportal.web.parameter.forms.ParameterForm;
import com.taifook.common.socket.ClientSocketService;
import com.taifook.common.socket.SocketMessage;

/**
 * MyEclipse Struts Creation date: 03-28-2006
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 * @struts.action-forward name="success" path="/jsp/success.jsp"
 */
public class ChannelsSwitchUpdateAction extends BaseAction {

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Log log = LogFactory.getLog(this.getClass()); 
		String forward=Constants.SUCCESS;
		ParameterForm updateForm=(ParameterForm)form;
		CsParameter updateParameter=new CsParameter();
		updateParameter.setKey(updateForm.getKey());
		updateParameter.setReadonly(true);
		updateParameter.setValue(updateForm.getValue());
		updateParameter.setDataType(updateForm.getDataType());
		updateParameter.setClassid(updateForm.getClassid());
		updateParameter.setDescription(updateForm.getDescription());
//		System.out.println(updateForm.getKey()+"    "+updateForm.getValue());
		//updateParameter.setDescription(updateForm.getDescription());
		updateParameter.setUpdateTime(new Date());
		
		try {
			((ParameterDAO)ServiceManager.getInstance().getService("com.taifook.adminportal.service.ParameterService"))
			                                           .update(updateParameter);
			//notify channel server the parameter update			
			new ClientSocketService().startSend(new SocketMessage(Constants.NOTIFY_CHANNELSTATUS,Constants.UPDATE_ACTION,updateParameter,new SynChannelCallBack()));			
			
		} catch (SocketTransferException e) {
			log.error("UpdateChannelStatusAction-excuteAction:Update ChannelStatus Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,
					"Update Channel Status to database success! but "
							+ e.getMessage());
			forward = Constants.FAILURE;
		}catch (Exception e) {
			log.error("UpdateChannelStatusAction-excuteAction:Update ChannelStatus Exception!");
			log.error(e);
			request.setAttribute(Constants.GLOBAL_ERROR,"update channel status fail! "+e.getMessage());
			forward=Constants.FAILURE;
		}

		return mapping.findForward(forward);

	}

}
