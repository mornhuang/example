//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.parameter.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.admin.service.syndata.SynParameterCallBack;
import com.itsz.sht.common.Consts;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.ParameterDAO;
import com.taifook.adminportal.exceptions.SocketTransferException;
import com.taifook.adminportal.model.CsParameter;
import com.taifook.adminportal.web.parameter.forms.ParameterForm;
import com.taifook.common.socket.ClientSocketService;
import com.taifook.common.socket.SocketMessage;

/** 
 * MyEclipse Struts
 * Creation date: 03-27-2006
 * 
 * XDoclet definition:
 * @struts.action validate="true"
 * @struts.action-forward name="updateparameterothersuccess" path="/success"
 */
public class AddParameterAction extends BaseParameterAction {

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Log log = LogFactory.getLog(this.getClass()); 
		String forward=Constants.SUCCESS;
		ParameterForm addForm=(ParameterForm)form;
		CsParameter addParameter=new CsParameter();
		addParameter.setKey(addForm.getKey().trim());
		addParameter.setValue(addForm.getValue());
		addParameter.setClassid(addForm.getClassid());
		addParameter.setDataType(addForm.getDataType());
		addParameter.setReadonly(Boolean.valueOf(addForm.getReadonly()).booleanValue());
		addParameter.setDescription(addForm.getDescription());
		addParameter.setUpdateTime(new Date());
//		System.out.println("form readonly:"+addForm.getReadonly()+" boolean readonly: "+addParameter.getReadonly());
		
		try {
			((ParameterDAO)ServiceManager.getInstance().getService("com.taifook.adminportal.service.ParameterService"))
			                                           .save(addParameter);
			//notify channel server the parameter add
			request.setAttribute(Constants.RETURN_BACK_URL,"../queryparameter.do?classid="+addForm.getClassid());
			new ClientSocketService().startSend(new SocketMessage(Constants.NOTIFY_PARAMETER, Constants.ADD_ACTION,addParameter,new SynParameterCallBack()));
		} catch (SocketTransferException e) {
			log.error("AddParameterAction-executeAction:Add Exception!");
			log.error(e.getMessage());		
			request.setAttribute(Constants.GLOBAL_ERROR,"add parameter to database success! but "+e.getMessage());			
			forward=Constants.FAILURE;
		} catch (Exception e) {
			log.error("AddParameterAction-executeAction:Add Exception!");
			log.error(e.getMessage());			
			request.setAttribute(Constants.GLOBAL_ERROR,"add parameter fail! "+e.getMessage());			
			forward=Constants.FAILURE;
		}
				
		return mapping.findForward(forward);
	}

}

