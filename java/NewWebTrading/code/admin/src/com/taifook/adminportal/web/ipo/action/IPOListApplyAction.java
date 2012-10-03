package com.taifook.adminportal.web.ipo.action;

import java.util.List;
import java.util.ArrayList;

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

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.delegate.IPOMaintenanceDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOMaintenanceException;
import com.taifook.adminportal.web.ipo.delegate.IPORequestDelegate;
import com.taifook.adminportal.web.ipo.exception.IPORequestException;
import com.taifook.adminportal.web.ipo.delegate.IPOResultDelegate;
import com.taifook.adminportal.web.ipo.exception.IPOResultException;
import com.taifook.adminportal.web.ipo.dto.IPOResultStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.taifook.adminportal.web.ipo.form.IPORequestForm;
import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.web.ipo.WebActionError;

/**
 *
 * @struts.action
 *    path="/eipo/IPOListApplyAction"
 *    name="IPORequestForm"
 *
 * @struts.action-forward
 *    name="success"
 *    path="/jsp/eipo/ViewIPOApply.jsp"
 *    redirect="false"
 *
 * @struts.action-forward
 *    name="failure"
 *    path="/jsp/eipo/blank.jsp"
 *    redirect="false"
 **/

public class IPOListApplyAction
    extends BaseAction {

  public IPOListApplyAction() {
  }

  Log log = LogFactory.getLog(this.getClass());
  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws
      Exception {

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
    int firstResult = 0;
    if (ipoRequestForm.getFirstResult() > 0) {
      firstResult = ipoRequestForm.getFirstResult();

    }
    int currPage = ipoRequestForm.getCurrPage();
    if (currPage < 1) {
      currPage = 1;
    }
    int fetchSize = 20;

    String ipoCode;
    String accountId = ipoRequestForm.getAccountId();
    if (accountId != null) {
      accountId = accountId.trim();

      try {
        int acctInt = 0;
        acctInt = Integer.parseInt(accountId);

        int acctLen = accountId.length();
        switch (acctLen) {
          case 1:
            accountId = "%000000" + accountId + "%";
          case 2:
            accountId = "%00000" + accountId + "%";
          case 3:
            accountId = "%0000" + accountId + "%";
          case 4:
            accountId = "%000" + accountId + "%";
          case 5:
            accountId = "%00" + accountId + "%";
          default:
            accountId = "%0" + accountId + "%";
        }
      }
      catch (Exception e) {
        log.error(e);
      }
    }
    String andIPOCode = ipoRequestForm.getAndIPOCode();
    String andAccount = ipoRequestForm.getAndAccount();
    String applyType = ipoRequestForm.getApplyType();
    IPORecord ipoRecord = new IPORecord();
    try {
      IPOMaintenanceDelegate ipoMaintenanceDelegate = new
          IPOMaintenanceDelegate();

      List ipoRecordList = (List) ipoMaintenanceDelegate.getAllIPORecord();

      if (ipoRecordList.size() > 0) {
        request.setAttribute("ipoRecordList", ipoRecordList);
      }
      if (ipoMasterId < 1) {
        //ipoRecord = (IPORecord) ipoRecordList.get(0);
        //ipoMasterId = ipoRecord.getIpoMasterId().longValue();
        //ipoCode = ipoRecord.getIpoCode();
        forwardTarget = Constants.SUCCESS;
        return mapping.findForward(forwardTarget);
      }
      else {
        ipoRecord = (IPORecord) ipoMaintenanceDelegate.
            getIPORecord(ipoMasterId);
        ipoCode = ipoRecord.getIpoCode();
      }
      request.setAttribute("currentIpoReord", ipoRecord);

      if ("applyRslt".equals(applyType)) {
        IPOResultDelegate ipoResultDelegate = new IPOResultDelegate();
        IPOResultDelegate ipoResultStatusDelegate = new
            IPOResultDelegate();

        List ipoResultList;
        ArrayList ipoResultStatusList = new ArrayList();
        if ("Y".equals(andIPOCode) && "Y".equals(andAccount) &&
            accountId != null) {
          ipoResultList = (List) ipoResultDelegate.
              getIPOResultCol(
              ipoCode,
              accountId);
        }
        else {
          if ("Y".equals(andAccount) && accountId != null) {
            ipoResultList = (List) ipoResultDelegate.
                getIPOResultByAccount(
                accountId);
          }
          else {
            ipoResultList = (List) ipoResultDelegate.
                getPageIPOResult(
                ipoCode, firstResult, fetchSize);
            ipoResultStatusList = (ArrayList)
                ipoResultStatusDelegate.getIPOResultStatusAndCount(
                ipoCode);
            request.setAttribute("ipoResultStatusList",
                                 ipoResultStatusList);
            int recordNum = 0;
            for (int i = 0; i < ipoResultStatusList.size(); i++) {
              IPOResultStatus ipoResultStatus = new
                  IPOResultStatus();
              ipoResultStatus = (IPOResultStatus)
                  ipoResultStatusList.get(i);
              if (Constants.IPO_TOTAL.equals(ipoResultStatus.
                                             getResultStatus())) {

                recordNum = Integer.parseInt(ipoResultStatus.
                                             getRecordCount());

              }
            }
            int pageNum = 1;
            float mod = recordNum % 20;
            if (mod == 0) {
              pageNum = new Float(recordNum / 20).intValue();
            }
            else {
              pageNum = new Float(recordNum / 20).intValue() + 1;
            }

            //int pageNum = new Float(recordNum / 20).intValue() + 1;
            request.setAttribute("pageNum",
                                 Integer.toString(pageNum));
            request.setAttribute("currPage",
                                 Integer.toString(currPage));
          }
        }
        if (ipoResultList.size() > 0) {
          request.setAttribute("ipoResultList", ipoResultList);
        }
      }
      else {
        IPORequestDelegate ipoRequestDelegate = new IPORequestDelegate();

        List ipoRquestList;
        if ("Y".equals(andIPOCode) && "Y".equals(andAccount) &&
            accountId != null) {
          ipoRquestList = (List) ipoRequestDelegate.getIPORequestCol(
              accountId,
              ipoMasterId);
        }
        else {
          if ("Y".equals(andAccount) && accountId != null) {
            ipoRquestList = (List) ipoRequestDelegate.
                getIPORequestByAccount(
                accountId);
          }
          else {
            //System.out.println("haha,my god!");
            ipoRquestList = (List) ipoRequestDelegate.
                getIPORequestByIPO(
                ipoMasterId);
            // System.out.println("haha,my god2!");

          }
        }
        if (ipoRquestList.size() > 0) {
          request.setAttribute("ipoRquestList", ipoRquestList);
          int recordNum = ipoRquestList.size();

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
      }
      forwardTarget = Constants.SUCCESS;
    }
    catch (Exception bme) {
      errors.add(ActionErrors.GLOBAL_ERROR,
                 new WebActionError(
          "errors.ipoRequestForm.ipoMaintenanceError"));
    }

    //System.out.print("forwardTarget="+forwardTarget);
    return mapping.findForward(forwardTarget);

  }
}
