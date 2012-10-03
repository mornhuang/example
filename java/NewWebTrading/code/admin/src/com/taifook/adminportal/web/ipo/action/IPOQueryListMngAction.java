package com.taifook.adminportal.web.ipo.action;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.Globals;
import com.taifook.adminportal.web.ipo.dto.IPORecord;

import com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOMaintenanceException;
import com.taifook.adminportal.web.ipo.form.IPORequestForm;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;

/**
 * @struts.action
 *    path="/eipo/IPOQueryListAction"
 *    name="IPORequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/ipolist.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/oprtErr.jsp"
 *    redirect="false"
 **/

public class IPOQueryListMngAction
    extends BaseAction {

    public IPOQueryListMngAction() {
    }

    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String forwardTarget = Constants.FAILURE;
        ActionErrors errors = new ActionErrors();
        IPORequestForm ipoRequestForm = (IPORequestForm) form;

        try {
            IPOMaintenanceDelegate ipoMaintenanceDelegate = new
                IPOMaintenanceDelegate();

            List ipoRecordList = (List) ipoMaintenanceDelegate.getAllIPORecord();
            IPORecord ipoRecord = new IPORecord();

            if (ipoRecordList.size() > 0) {

                HttpSession session = request.getSession();
                Locale currentLocaleObj = (Locale) session.getAttribute(Globals.
                    LOCALE_KEY);
                String currentLocale = currentLocaleObj.toString();
                for (int i = 0; i < ipoRecordList.size(); i++) {
                    ipoRecord = (IPORecord)ipoRecordList.get(i);
                    if ("zh_TW".equals(currentLocale)) {
                        ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_big());
                        ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_tw());
                    }
                    else {
                        if ("zh_CN".equals(currentLocale)) {
                            ipoRecord.setIpoName_dsply(ipoRecord.getIpoName_gb());
                            ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_cn());
                        }
                        else {
                            ipoRecord.setIpoName_dsply(ipoRecord.getIpoName());
                            ipoRecord.setProp_url_dsply(ipoRecord.getProp_url_en());
                        }
                    }

                }

                request.setAttribute("ipoRecordList", ipoRecordList);
            }

            forwardTarget = Constants.SUCCESS;
        }
        catch (Exception bme) {
            errors.add(ActionErrors.GLOBAL_ERROR,
                       new WebActionError(
                "errors.ipoRequestForm.ipoMaintenanceError"));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }

        return mapping.findForward(forwardTarget);

    }
}
