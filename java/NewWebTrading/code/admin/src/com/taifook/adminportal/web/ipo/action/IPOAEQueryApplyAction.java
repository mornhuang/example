package com.taifook.adminportal.web.ipo.action;

import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.taifook.adminportal.web.ipo.dto.IPORecord;
import com.taifook.adminportal.web.ipo.dto.IPORequest;
import com.taifook.adminportal.web.ipo.delegate.IPORequestDelegate;
import com.taifook.adminportal.web.ipo.exception.IPORequestException;
import com.taifook.adminportal.web.ipo.form.IPOCancelForm;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.web.ipo.delegate.IPOReqResDelegate;
import com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOMaintenanceException;
import com.taifook.adminportal.web.ipo.delegate.IPORequestDelegate;
import com.taifook.adminportal.web.ipo.exception.IPORequestException;
import com.taifook.adminportal.web.ipo.delegate.IPOResultDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOResultException;
import com.taifook.adminportal.web.ipo.dto.IPOResultStatus;
import com.taifook.adminportal.web.ipo.form.IPORequestForm;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.web.ipo.WebActionError;
import com.taifook.adminportal.dto.User;
import com.taifook.adminportal.common.Constants;

/**
 *
 * @struts.action
 *    path="/eipo/IPOAEQueryApplyAction"
 *    name="IPORequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/aeQueryApply.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/blank_ae.jsp"
 *    redirect="false"
 **/

public class IPOAEQueryApplyAction
    extends BaseAction {

  public IPOAEQueryApplyAction() {
  }

  private Log log = LogFactory.getLog(this.getClass());
  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {

    String forwardTarget = Constants.FAILURE;
    ActionErrors errors = new ActionErrors();
    IPORequestForm ipoRequestForm = (IPORequestForm) form;
    HttpSession session = request.getSession();
    User user = (User) session.getAttribute(com.taifook.adminportal.common.
                                            Constants.SESSION_USER);
    String aeCode = user.getUserid();

    long ipoMasterId = 0;
    try {
      ipoMasterId = ipoRequestForm.getIpoMasterId();
    }
    catch (Exception e) {
      log.error(e);
    }
    int firstResult = 0;
    if (ipoRequestForm.getFirstResult() > 0) {
      firstResult = ipoRequestForm.getFirstResult();
    }
    int currPage = ipoRequestForm.getCurrPage();
    if (currPage < 1) {
      currPage = 1;
    }
    int fetchSize = 20;

    // String ipoCode;
    String accountId = ipoRequestForm.getAccountId();
    if (accountId != null) {
      accountId = accountId.trim();
    }

    String andIPOCode = ipoRequestForm.getAndIPOCode();
    String andAccount = ipoRequestForm.getAndAccount();
    String applyType = ipoRequestForm.getApplyType();
    IPORecord ipoRecord = new IPORecord();
    try {
      /*
                   IPOMaintenanceDelegate ipoMaintenanceDelegate = new
          IPOMaintenanceDelegate();
           List ipoRecordList = (List) ipoMaintenanceDelegate.getAllIPORecord();
                   if (ipoRecordList.size() > 0) {
          request.setAttribute("ipoRecordList", ipoRecordList);
                   }*/
      /*
                   if (ipoMasterId < 1) {
          ipoRecord = (IPORecord) ipoRecordList.get(0);
          ipoMasterId = ipoRecord.getIpoMasterId().longValue();
          ipoCode = ipoRecord.getIpoCode();
                   }
                   else {
          ipoRecord = (IPORecord) ipoMaintenanceDelegate.
              getIPORecord(ipoMasterId);
          ipoCode = ipoRecord.getIpoCode();
                   }
       */
      //System.out.println("my son begin!");
      IPOReqResDelegate ipoReqResDelegate = new IPOReqResDelegate();

      ArrayList acList = new ArrayList();
      acList = (ArrayList) session.getAttribute("acList");

      List aeRequestList;
      if ("Y".equals(andIPOCode) && "Y".equals(andAccount) &&
          accountId != null) {
        aeRequestList = (List) ipoReqResDelegate.getIPORequestOrder(
            aeCode, accountId,
            ipoMasterId, acList);

      }
      else {
        if ("Y".equals(andAccount) && accountId != null) {
          aeRequestList = (List) ipoReqResDelegate.getIPORequestOrder(
              aeCode, accountId, acList);
        }
        else {
          //System.out.println("my son do!");
          aeRequestList = (List) ipoReqResDelegate.getIPORequestOrder(
              aeCode, ipoMasterId, acList);

        }
      }
      //System.out.println("my son!");
      if (aeRequestList.size() > 0) {
        //System.out.println("my son1!");
        request.setAttribute("ipoRquestList", aeRequestList);
        int recordNum = aeRequestList.size();

        int pageNum = 1;
        float mod = recordNum % 20;
        if (mod == 0) {
          pageNum = new Float(recordNum / 20).intValue();
        }
        else {
          pageNum = new Float(recordNum / 20).intValue() + 1;
        }

        //int pageNum = new Float(recordNum / 20).intValue() + 1;
        request.setAttribute("pageNum", Integer.toString(pageNum));

      }
      forwardTarget = Constants.SUCCESS;
    }
    catch (Exception bme) {
      errors.add(ActionErrors.GLOBAL_ERROR,
                 new WebActionError(
          "errors.adminQueryAeAc.error"));
      log.debug(bme);
    }

    if (!errors.isEmpty()) {
      saveErrors(request, errors);
    }
    //System.out.println("my son2!=" + forwardTarget);
    return mapping.findForward(forwardTarget);

  }
}
