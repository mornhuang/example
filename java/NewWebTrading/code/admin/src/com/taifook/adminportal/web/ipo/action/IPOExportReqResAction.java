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

import com.taifook.adminportal.web.ipo.delegate.IPOReqResDelegate;
import com.taifook.adminportal.web.ipo.exception.IPORequestException;
import com.taifook.adminportal.web.ipo.form.IPOExportRequestForm;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.web.ipo.dto.IPOReqRes;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;
//import com.taifook.adminportal.common.util.OracleHibernateUtil;
import com.itsz.common.util.OracleHibernateUtil;

/**
 *
 * @struts.action
 *    path="/eipo/IPOExportReqResAction"
 *    name="IPOExportRequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/download2.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/oprtErr.jsp"
 *    redirect="false"
 **/

public class IPOExportReqResAction
    extends BaseAction {

    public IPOExportReqResAction() {
    }

    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        String forwardTarget = Constants.FAILURE;
        ActionErrors errors = new ActionErrors();
        IPOExportRequestForm ipoExportRequestForm = (IPOExportRequestForm) form;
        //String ipoExportReqResFilePath = ipoExportRequestForm.getIpoRequestFilePath();
        HttpSession session = request.getSession();
        String ipoExportReqResFilePath = session.getAttribute(Constants.TEMP_FILE_PATH).toString();
        long ipoMasterId = (long) ipoExportRequestForm.getIpoMasterId();
        try {

            String ipoCode = "",ipoName="";
            Session bgnSession = OracleHibernateUtil.currentSession();

            IPORecord ipoRecord = (IPORecord) bgnSession.load(IPORecord.class,
                new Long(ipoMasterId));
            ipoCode = ipoRecord.getIpoCode();
            ipoName = "\""+ipoRecord.getIpoName()+"\"";
            OracleHibernateUtil.closeSession();

            ipoExportReqResFilePath = ipoExportReqResFilePath +File.separator+ipoCode+"_LinkInfo.csv";

            IPOReqResDelegate ipoRequestDelegate = new IPOReqResDelegate();
            ipoRequestDelegate.exportFileByIPO(ipoMasterId,
                ipoExportReqResFilePath,ipoName);

            request.setAttribute("ipoLinkInfoExpFilePath", ipoExportReqResFilePath);
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
