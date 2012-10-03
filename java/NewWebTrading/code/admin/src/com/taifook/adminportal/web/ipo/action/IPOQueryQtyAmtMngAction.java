package com.taifook.adminportal.web.ipo.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.taifook.adminportal.web.ipo.delegate.IPOQtyAmtDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOQtyAmtException;
import com.taifook.adminportal.web.ipo.form.IPORequestForm;
import com.taifook.adminportal.web.ipo.dto.IPOQtyAmt;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;


/**
 * @struts.action
 *    path="/eipo/IPOQueryQtyAmtAction"
 *    name="IPORequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/tbmult.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/blank.jsp"
 *    redirect="false"
 **/

public class IPOQueryQtyAmtMngAction extends BaseAction {

    public IPOQueryQtyAmtMngAction() {
    }

    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {


    	String forwardTarget = Constants.FAILURE;
    	ActionErrors errors = new ActionErrors();
    	IPORequestForm ipoRequestForm = (IPORequestForm) form;

        long ipoMasterId =  ipoRequestForm.getIpoMasterId();

    	try {

            IPOQtyAmtDelegate ipoQtyAmtDelegate = new IPOQtyAmtDelegate();

	    	List ipoQtyAmt = (List)ipoQtyAmtDelegate.getIPOQtyAmtRcrd(ipoMasterId);

	    	if (ipoQtyAmt != null ) {
	    		request.setAttribute("listIpoQtyAmt",ipoQtyAmt);
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
