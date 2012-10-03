package com.taifook.adminportal.web.ipo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.web.ipo.delegate.IPOResultDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOResultException;
import com.taifook.adminportal.web.ipo.form.IPORequestForm;
import com.taifook.adminportal.web.ipo.dto.IPOResult;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;

/**
 * @struts.action
 *    path="/eipo/IPOResultUpdateAvlAction"
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

public class IPOResultUpdateAvlAction
    extends BaseAction {

    public IPOResultUpdateAvlAction() {
    }

    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String forwardTarget = Constants.FAILURE;
        ActionErrors errors = new ActionErrors();
        IPORequestForm ipoRequestForm = (IPORequestForm) form;

        try {
            String[] applyCodeStr = ipoRequestForm.getApplyCodeStr();
            int appLen = applyCodeStr.length;
            long applyId = 0;
            String statusCode = "3";

            for (int i = 0; i < appLen; i++) {

                applyId = Long.parseLong(applyCodeStr[i]);

                IPOResult ipoResult = new IPOResult();
                IPOResultDelegate ipoResultDelegate = new
                    IPOResultDelegate();

                ipoResult = ipoResultDelegate.getIPOResult(applyId);

                ipoResult.setPrgStatus(statusCode);

                ipoResultDelegate.updateIPOResult(applyId, ipoResult);

            }
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
