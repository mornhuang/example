package com.taifook.adminportal.web.ipo.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOMaintenanceException;
import com.taifook.adminportal.web.ipo.form.IPORequestForm;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.web.ipo.delegate.IPORequestDelegate;
import com.taifook.adminportal.web.ipo.exception.IPORequestException;
import com.taifook.adminportal.web.ipo.dto.IPORequest;
import com.taifook.adminportal.web.ipo.delegate.IPOResultDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOResultException;
import com.taifook.adminportal.web.ipo.dto.IPOResult;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.taifook.adminportal.web.ipo.WebActionError;

/**
 * @struts.action
 *    path="/eipo/IPOQueryCodeMngAction"
 *    name="IPORequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/updateIPO.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/oprtErr.jsp"
 *    redirect="false"
 **/

public class IPOQueryCodeMngAction extends BaseAction {

    public IPOQueryCodeMngAction() {
    }

    private Log log = LogFactory.getLog(this.getClass());
    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {


    	String forwardTarget = Constants.FAILURE;
    	ActionErrors errors = new ActionErrors();
    	IPORequestForm ipoRequestForm = (IPORequestForm) form;

        long ipoMasterId =  ipoRequestForm.getIpoMasterId();

    	try {
            IPOMaintenanceDelegate ipoMaintenanceDelegate = new
               IPOMaintenanceDelegate();
            IPORecord ipoRecord = ipoMaintenanceDelegate.getIPORecord(
               ipoMasterId);

           if (ipoRecord != null) {
               request.setAttribute("currentIpoReord", ipoRecord);
           }
          forwardTarget = Constants.SUCCESS;
    	}
    	catch (Exception bme) {
    		errors.add(ActionErrors.GLOBAL_ERROR, new WebActionError("errors.ipoRequestForm.ipoMaintenanceError"));
    	}

    	if (!errors.isEmpty()) {
    		saveErrors(request, errors);
    	}
    	return mapping.findForward(forwardTarget);

    }
}
