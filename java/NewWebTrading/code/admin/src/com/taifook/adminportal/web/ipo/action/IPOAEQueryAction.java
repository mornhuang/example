package com.taifook.adminportal.web.ipo.action;

import java.util.Date;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOMaintenanceException;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.form.IPOAddForm;
import com.taifook.adminportal.web.ipo.dto.IPORecord;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.web.ipo.WebActionError;

/**
 * @struts.action
 *   path="/eipo/IPOAEQueryAction"
 *   type="com.taifook.adminportal.web.ipo.action.IPOAEQueryAction"
 *   name="IPORequestForm"

 *
 * @struts.action-forward
 *     name="success"
 *     path="/jsp/eipo/aeQueryApply.jsp"
 *     redirect="false"

 *
 * @struts.action-forward
 *      name="failure"
 *      path="/jsp/eipo/oprtErr.jsp"
 *      redirect="false"

 **/


public class IPOAEQueryAction
    extends BaseAction {

    public IPOAEQueryAction() {
    }
    public ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception {

        String forwardTarget = Constants.FAILURE;
        ActionErrors errors = new ActionErrors();
        HttpSession session = request.getSession();
        try {
            IPOMaintenanceDelegate ipoMaintenanceDelegate = new
                IPOMaintenanceDelegate();
            List ipoRecordList = (List) ipoMaintenanceDelegate.
                getAllIPORecord();
            IPORecord ipoRecord = new IPORecord();
            ArrayList simpleIpoRecordList =new ArrayList();
            for(int i=0;i<ipoRecordList.size();i++)
            {
                IPORecord newIpoRecord = new IPORecord();
                ipoRecord = (IPORecord)ipoRecordList.get(i);
                newIpoRecord.setIpoCode(ipoRecord.getIpoCode());
                newIpoRecord.setStockCode(ipoRecord.getStockCode());
                newIpoRecord.setIpoMasterId(ipoRecord.getIpoMasterId());
                simpleIpoRecordList.add(newIpoRecord);
            }
            session.setAttribute("ipoRecordList", simpleIpoRecordList);
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
