package com.taifook.adminportal.web.ipo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import com.taifook.adminportal.web.ipo.form.IPOCancelForm;
import com.taifook.adminportal.web.ipo.delegate.IPOResultDelegate;
import com.taifook.adminportal.web.ipo.dto.IPOResult;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.Locale;
import org.apache.struts.Globals;

/**
 * @struts.action
 *    path="/eipo/IPOQueryCodeAction"
 *    name="IPORequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/queryRslt.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/norslt.jsp"
 *    redirect="false"
 **/

public class IPOQueryResultAction
    extends BaseAction {

    public IPOQueryResultAction() {
    }

    private Log log = LogFactory.getLog(this.getClass());
    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        String forwardTarget = Constants.FAILURE;
        ActionErrors errors = new ActionErrors();
        IPOCancelForm ipoCancelForm = (IPOCancelForm) form;

        long applyId = ipoCancelForm.getApplyId();

        try {

            IPOResultDelegate ipoResultDelegate = new
                IPOResultDelegate();
            IPOResult ipoResult = ipoResultDelegate.getIPOResult(
                applyId);
            if (ipoResult.getApplyId() != null) {
                request.setAttribute("currentIpoResult", ipoResult);
                forwardTarget = Constants.SUCCESS;
            }else {
                forwardTarget = Constants.FAILURE;
            }
        }
        catch (Exception e) {
            forwardTarget = Constants.FAILURE;
            log.error(e);
        }


        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }
        return mapping.findForward(forwardTarget);

    }
}
