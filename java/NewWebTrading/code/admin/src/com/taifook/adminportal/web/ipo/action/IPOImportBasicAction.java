package com.taifook.adminportal.web.ipo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOMaintenanceException;
import com.taifook.adminportal.web.ipo.form.IPOImportForm;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;

/**
 *
 * @struts.action
 *    path="/jsp/eipo/IPOImportBasicAction"
 *    name="IPOImportForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/oprtSc.jsp?page=fileInput.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/oprtErr.jsp"
 *    redirect="false"
 **/

public class IPOImportBasicAction
    extends BaseAction {

    public IPOImportBasicAction() {
    }

    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String forwardTarget = Constants.FAILURE;
        ActionErrors errors = new ActionErrors();
        IPOImportForm ipoImportForm = (IPOImportForm) form;
        String ipoFilePath = ipoImportForm.getIpoFilePath();
        String ipoCrtFilePath = ipoImportForm.getIpoCrtFilePath();

        try {
            IPOMaintenanceDelegate ipoMaintenanceDelegate = new IPOMaintenanceDelegate();
            ipoMaintenanceDelegate.importIPOBasicFile(ipoFilePath);

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
