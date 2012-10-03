package com.taifook.adminportal.web.ipo.action;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOMaintenanceException;
import com.taifook.adminportal.web.ipo.form.IPOAddForm;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;

/**
 * @struts.action
 *    path="/eipo/IPOUpdateMngAction"
 *    name="IPOAddForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/updateSc.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/oprtErr.jsp"
 *    redirect="false"
 **/

public class IPOUpdateMngAction
    extends BaseAction {

    public IPOUpdateMngAction() {
    }

    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
        String forwardTarget = Constants.FAILURE;
        ActionErrors errors = new ActionErrors();
        IPOAddForm ipoAddForm = (IPOAddForm) form;

        try {
            String ipoName = ipoAddForm.getIpoName().trim();
//            System.out.println("init="+ipoAddForm.getIpoName_gb());
            String ipoName_gb = ipoAddForm.getIpoName_gb();
            String ipoName_big=ipoAddForm.getIpoName_big();
            /*
            String ipoName_gb = new String(ipoAddForm.getIpoName_gb().trim().getBytes(
                "ISO8859-1"), "UTF-8");
            String ipoName_big = new String(ipoAddForm.getIpoName_big().trim().
                                            getBytes("ISO8859-1"), "UTF-8");
          */
//            System.out.println("ipoName_big="+ipoName_big);
//            System.out.println("ipoName_gb="+ipoName_gb);
            //add by jhu
            String deadLine_dsply = ipoAddForm.getDeadLine_dsply().trim();
            Date deadLine = Timestamp.valueOf(deadLine_dsply);
            //
            String depositDate_dsply = ipoAddForm.getDepositDate_dsply().trim();
            Date depositDate = Timestamp.valueOf(depositDate_dsply);
            String debitDate_dsply_mng = ipoAddForm.getDebitDate_dsply_mng().trim();
            Date debitDate = Timestamp.valueOf(debitDate_dsply_mng);

            String prop_url_cn = ipoAddForm.getProp_url_cn().trim();
            String prop_url_en = ipoAddForm.getProp_url_en().trim();
            String prop_url_tw = ipoAddForm.getProp_url_tw().trim();

            long ipoMasterId = 0;
            ipoMasterId = ipoAddForm.getIpoMasterId().longValue();

            IPORecord ipoRecord = new IPORecord();
            IPOMaintenanceDelegate ipoMaintenanceDelegate = new
                IPOMaintenanceDelegate();

            ipoRecord = ipoMaintenanceDelegate.getIPORecord(ipoMasterId);

            ipoRecord.setIpoName(ipoName);
            ipoRecord.setIpoName_gb(ipoName_gb);
            ipoRecord.setIpoName_big(ipoName_big);
            //add by jhu
            ipoRecord.setDeadLine(deadLine);
            //
            ipoRecord.setDepositDate(depositDate);
            ipoRecord.setDebitDate(debitDate);

            ipoRecord.setProp_url_cn(prop_url_cn);
            ipoRecord.setProp_url_en(prop_url_en);
            ipoRecord.setProp_url_tw(prop_url_tw);

            ipoMaintenanceDelegate.updateIPORecord(ipoMasterId, ipoRecord);

            ipoRecord = ipoMaintenanceDelegate.getIPORecord(ipoMasterId);
            request.setAttribute("currentIpoReord", ipoRecord);

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
