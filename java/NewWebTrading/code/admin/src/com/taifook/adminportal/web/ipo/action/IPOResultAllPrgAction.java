package com.taifook.adminportal.web.ipo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.web.ipo.delegate.IPOResultDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOResultException;
import com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOMaintenanceException;
import com.taifook.adminportal.web.ipo.form.IPORequestForm;
import com.taifook.adminportal.web.ipo.dto.IPOResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;

/**
 * @struts.action
 *    path="/eipo/IPOResultAllPrgAction"
 *    name="IPORequestForm"
 *    attribute="IPORequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/updateRsltSc.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/oprtErr.jsp"
 *    redirect="false"
 **/

public class IPOResultAllPrgAction
    extends BaseAction {

    public IPOResultAllPrgAction() {
    }
   private Log log = LogFactory.getLog(this.getClass());
   public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String forwardTarget = Constants.FAILURE;
        ActionErrors errors = new ActionErrors();
        IPORequestForm ipoRequestForm = (IPORequestForm) form;

        long ipoMasterId = 0;
        try {
            ipoMasterId = ipoRequestForm.getIpoMasterId();
        }
        catch (Exception e) {
            log.error(e);
        }
        IPOMaintenanceDelegate ipoMaintenanceDelegate = new
            IPOMaintenanceDelegate();
        IPORecord ipoRecord = new IPORecord();

        try {
            ipoRecord = (IPORecord) ipoMaintenanceDelegate.getIPORecord(
                ipoMasterId);
            String ipoCode = ipoRecord.getIpoCode();

            String status = "0";

            IPOResultDelegate ipoResultDelegate = new
                IPOResultDelegate();

            ipoResultDelegate.updateAllIPOResult(ipoCode, status);

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
