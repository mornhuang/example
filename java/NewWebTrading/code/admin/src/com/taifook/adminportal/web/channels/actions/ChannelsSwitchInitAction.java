//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.channels.actions;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.ParameterDAO;
import com.taifook.adminportal.model.CsParameter;
import com.taifook.adminportal.web.parameter.forms.ParameterForm;

/**
 * MyEclipse Struts Creation date: 03-27-2006
 * 
 * XDoclet definition:
 * 
 * @struts.action validate="true"
 */
public class ChannelsSwitchInitAction extends BaseAction {

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Log log = LogFactory.getLog(this.getClass());
		String forward = Constants.SUCCESS;

		try {
			HttpSession session = request.getSession();
			ParameterForm parameterForm = (ParameterForm) form;
			String classid = parameterForm.getClassid();
//			System.out.println(classid);
			List result = ((ParameterDAO) ServiceManager.getInstance()
					.getService(
							"com.taifook.adminportal.service.ParameterService"))
					.loadChannels(classid);
			if (result == null || result.size() < 1) {
				//forward = Constants.FAILURE;
			} else {
				session.removeAttribute(Constants.RESULT);
				session.setAttribute(Constants.RESULT, result);
			}
		} catch (Exception e) {
			log.error("ChannelsSwitchInitAction:load channels Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,
					"init channels status fail! " + e.getMessage());
			forward = Constants.FAILURE;
		}
		return mapping.findForward(forward);
	}

}
