//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.parameter.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.common.util.page.Page;
import com.taifook.adminportal.dao.ParameterDAO;
import com.taifook.adminportal.web.parameter.forms.ParameterForm;

/**
 * MyEclipse Struts Creation date: 03-24-2006
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/queryparameter" name="/queryparameterForm"
 *                input="/form//queryparameter.jsp" scope="request"
 *                validate="true"
 * @struts.action-forward name="success" path="/jsp/editparameter.jsp"
 */
public class QueryParameterAction extends BaseParameterAction {

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
		String forward=Constants.SUCCESS;
		Log log = LogFactory.getLog(this.getClass());
		try {
			ParameterForm actionForm = (ParameterForm) form;
			Object[] objects = this.getFiltersForDB(actionForm);
			
			int pageNumber = getPageNumber(request); // get current page
														// number
			int pageSize = getPageSize(); // get page size;			
			
			Page page = ((ParameterDAO) ServiceManager.getInstance()
					.getService(
							"com.taifook.adminportal.service.ParameterService"))
					.findByPage(objects, pageNumber, pageSize);
			
			HttpSession session = request.getSession();
			session.removeAttribute(Constants.RESULT);
			session.setAttribute(Constants.RESULT, page);
			request.setAttribute(Constants.FILTER,actionForm);
			request.setAttribute(Constants.URL_PARAMETER,this.getFiltersForWeb(actionForm));
		} catch (Exception e) {
			log.error(e);
			request.setAttribute(Constants.GLOBAL_ERROR,"query parameter fail! "+e.getMessage());
			forward=Constants.FAILURE;
		}
		return mapping.findForward(forward);
	}
	

}
