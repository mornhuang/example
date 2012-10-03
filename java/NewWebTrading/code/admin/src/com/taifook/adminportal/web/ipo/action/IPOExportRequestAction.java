package com.taifook.adminportal.web.ipo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.web.ipo.delegate.IPORequestDelegate;
import com.taifook.adminportal.web.ipo.exception.IPORequestException;
import com.taifook.adminportal.web.ipo.form.IPOExportRequestForm;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;
//import com.taifook.adminportal.common.util.OracleHibernateUtil;
import com.itsz.common.util.OracleHibernateUtil;

/**
 *
 * @struts.action
 *    path="/eipo/IPOExportRequestAction"
 *    name="IPOExportRequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/download.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="successxx"
 *    path="/jsp/eipo/rqtoprtSc.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/oprtErr.jsp"
 *    redirect="false"
 **/

public class IPOExportRequestAction
    extends BaseAction {

    public IPOExportRequestAction() {
    }

    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String forwardTarget = Constants.FAILURE;
        ActionErrors errors = new ActionErrors();
        IPOExportRequestForm ipoExportRequestForm = (IPOExportRequestForm) form;
        //String ipoExportRequestFilePath = ipoExportRequestForm.getIpoRequestFilePath();

        HttpSession session = request.getSession();
        String ipoExportRequestFilePath = session.getAttribute(Constants.TEMP_FILE_PATH).toString();

        long ipoMasterId = (long) ipoExportRequestForm.getIpoMasterId();
        try {
            String ipoCode = "";
            Session bgnSession = OracleHibernateUtil.currentSession();

            IPORecord ipoRecord = (IPORecord) bgnSession.load(IPORecord.class,
                new Long(ipoMasterId));
            ipoCode = ipoRecord.getIpoCode();
            OracleHibernateUtil.closeSession();

            ipoExportRequestFilePath = ipoExportRequestFilePath +File.separator+"IPOOrderRequest_"+ipoCode+".dat";

            IPORequestDelegate ipoRequestDelegate = new IPORequestDelegate();
            ipoRequestDelegate.exportIPOQtyAmtFile(ipoMasterId,
                ipoExportRequestFilePath);

            request.setAttribute("ipoExpFilePath", ipoExportRequestFilePath);

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
