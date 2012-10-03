package com.taifook.adminportal.web.ipo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.web.ipo.delegate.IPOQtyAmtDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOQtyAmtException;
import com.taifook.adminportal.web.ipo.form.IPOImportForm;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;

/**
 *
 * @struts.action
 *    path="/jsp/eipo/IPOImportQtyAmtAction"
 *    name="IPOImportForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/oprtSc.jsp?page=fileQtyAmtInput.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/oprtErr.jsp"
 *    redirect="false"
 **/

public class IPOImportQtyAmtAction
    extends BaseAction {

    public IPOImportQtyAmtAction() {
    }

    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        String forwardTarget = Constants.FAILURE;
        ActionErrors errors = new ActionErrors();

        IPOImportForm ipoImportForm = (IPOImportForm) form;
        String ipoCrtFilePath = ipoImportForm.getIpoCrtFilePath();

        try {
            IPOQtyAmtDelegate ipoQtyAmtDelegate = new IPOQtyAmtDelegate();
            ipoQtyAmtDelegate.importIPOQtyAmtFile(ipoCrtFilePath);


            forwardTarget = Constants.SUCCESS;
        }
        catch (Exception bme) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                       new WebActionError(
                "errors.ipoInsertForm.ipoMaintenanceError"));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }

        return mapping.findForward(forwardTarget);

    }
}
