package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.auto.AutoPurchaseConstants;
import com.itsz.sht.common.auto.AutoPurchaseError;
import com.itsz.sht.common.auto.Counters;
import com.itsz.sht.common.auto.DistributeUtils;
import com.itsz.sht.common.auto.ProcessStatus;
import com.itsz.sht.common.model.common.request.ExecuteMemoDebitRequestModel;
import com.itsz.sht.common.model.common.request.ReleaseRTQAccountAssignmentRequestModel;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.UserProductAllotment;
import com.itsz.sht.struts.form.RtqAccountForm;

public class ProcessAutoAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
	
	
	public ActionForward exeAutoEnrollment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		String returnCode="";
		returnCode=facade.exeAutoEnrollment(user.getLognId()).getReturnCode();
		request.setAttribute("message", returnCode);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS) || returnCode.equals(Consts.Error.Code.ADMINPROTAL_EXECUTEAUTOENROLLMENT_SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	
	public ActionForward showDataForMemoDebit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProcessStatus pStatus = ProcessStatus.getStatus("MemoDebitForAutoEnrollment", DistributeUtils.getDistributedHashtable("MemoDebitForAutoEnrollment"));
		if (!pStatus.isReseted()) {
			return mapping.findForward("showMenoDebit");
		}
		List<UserProductAllotment>  userProductAllotmentList= 	facade.findMemoDebitUserProductAllotment();
		if(userProductAllotmentList!=null&&userProductAllotmentList.size()>0){
			request.setAttribute("userProductAllotmentList", userProductAllotmentList);
		}
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward showMemoDebitStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
	    ProcessStatus pStatus = ProcessStatus.getStatus("memoDebit", DistributeUtils.getDistributedHashtable("memoDebit"));
		if ("release".equals(request.getParameter("action")))
		{	
			pStatus.reset();
		}
        Counters counters = pStatus.getCounters();
        int total = pStatus.getTotalRecord(), success = 0, lackMoney = 0,  sysError = 0;
        if (counters != null) {
            success = counters.getValue(AutoPurchaseConstants.PurchaseStatus.ST_SUCCESS);
            lackMoney = counters.getValue(AutoPurchaseConstants.PurchaseStatus.ST_NOT_ENOUGH_MONEY);
            sysError = counters.getValue(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
            request.setAttribute("success", success);
            request.setAttribute("lackMoney", lackMoney);
            request.setAttribute("sysError", sysError);
            request.setAttribute("counterTotail", counters.getTotal());
        }
        if (!pStatus.getErrors().isEmpty()){
        	Map errorMap = pStatus.getErrors();
        	if(errorMap.containsKey("SYSERROR")){
				Map sysErrorMap = (HashMap)errorMap.get("SYSERROR");
				errorMap.remove("SYSERROR");
				List<AutoPurchaseError> autoPurchaseErrorList=new ArrayList<AutoPurchaseError>();
				for (Iterator iter1 = sysErrorMap.entrySet().iterator(); iter1.hasNext();) {
				Map.Entry item1 = (Map.Entry) iter1.next();
                String sysErrorId = (String) item1.getKey();
                String sysEx = (String) item1.getValue();
                AutoPurchaseError autoError=new AutoPurchaseError(sysErrorId,sysEx);
                autoPurchaseErrorList.add(autoError);
				}
				for (Iterator iter2 = errorMap.entrySet().iterator(); iter2.hasNext();) {
					Map.Entry item2 = (Map.Entry) iter2.next();
					 String sysErrorId = (String) item2.getKey();
		                String sysEx = (String) item2.getValue();
		                AutoPurchaseError autoError=new AutoPurchaseError(sysErrorId,sysEx);
		                autoPurchaseErrorList.add(autoError);
				}
				autoPurchaseErrorList.size();
				request.setAttribute("autoPurchaseErrorList", autoPurchaseErrorList);
	        }      
		} 
	    request.setAttribute("pStatus",pStatus );
	    request.setAttribute("total", total);
	    request.setAttribute("isProcessing",pStatus.isProcessing());
	    request.setAttribute("isReseted",pStatus.isReseted() );
	    request.setAttribute("startTime",pStatus.getStartTime().toString());
	    request.setAttribute("endTime",pStatus.getEndTime().getTime()==0?"...":pStatus.getEndTime().toString());
	    request.setAttribute("elapseString",pStatus.getElapseString());
	    request.setAttribute("averageTime",pStatus.getAverageTime() );
        return mapping.findForward("success");
	}
	
	
		
	public ActionForward exeMemoDebit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		ExecuteMemoDebitRequestModel reqModel=new ExecuteMemoDebitRequestModel();
		reqModel.setActionBy(user.getLognId());
		String returnCode=facade.exeMemoDebit(reqModel).getReturnCode();
		request.setAttribute("message", returnCode);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS) || returnCode.equals(Consts.Error.Code.ADMINPROTAL_MEMO_DEBIT_SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}
	

	
	public ActionForward releaseRTQAccountAssignment(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ReleaseRTQAccountAssignmentRequestModel reqModel=new ReleaseRTQAccountAssignmentRequestModel();
		String returnCode="";
		returnCode=facade.releaseRTQAccountAssignment(reqModel).getReturnCode();
		request.setAttribute("message", returnCode);
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS) || returnCode.equals(Consts.Error.Code.ADMINPROTAL_RELEASERTQACCOUNT_SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward showProcessAuto(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProcessStatus pStatus = ProcessStatus.getStatus("AutoPurchase", DistributeUtils.getDistributedHashtable("AutoPurchase"));
		if (!pStatus.isReseted()){
			return mapping.findForward("showAuto");
		}
		ProcessStatus pStatusMe = ProcessStatus.getStatus("memoDebit", DistributeUtils.getDistributedHashtable("memoDebit"));
		if (!pStatusMe.isReseted()){
			return mapping.findForward("showMemoDebit");
		}
		return mapping.findForward("success");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward showAutoPurchaseStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ProcessStatus pStatus = ProcessStatus.getStatus("AutoPurchase", DistributeUtils.getDistributedHashtable("AutoPurchase"));
		if ("release".equals(request.getParameter("action")))
		{	
			pStatus.reset();
		}
        Counters counters = pStatus.getCounters();
        int total = pStatus.getTotalRecord(), success = 0, lackMoney = 0,  sysError = 0;
        if (counters != null) {
            success = counters.getValue(AutoPurchaseConstants.PurchaseStatus.ST_SUCCESS);
            lackMoney = counters.getValue(AutoPurchaseConstants.PurchaseStatus.ST_NOT_ENOUGH_MONEY);
            sysError = counters.getValue(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR);
            request.setAttribute("success", success);
            request.setAttribute("lackMoney", lackMoney);
            request.setAttribute("sysError", sysError);
            request.setAttribute("counterTotail", counters.getTotal());
        }
        if (!pStatus.getErrors().isEmpty()){
        	Map errorMap = pStatus.getErrors();
        	if(errorMap.containsKey("SYSERROR")){
				Map sysErrorMap = (HashMap)errorMap.get("SYSERROR");
				errorMap.remove("SYSERROR");
				List<AutoPurchaseError> autoPurchaseErrorList=new ArrayList<AutoPurchaseError>();
				for (Iterator iter1 = sysErrorMap.entrySet().iterator(); iter1.hasNext();) {
					Map.Entry item1 = (Map.Entry) iter1.next();
	                String sysErrorId = (String) item1.getKey();
	                String sysEx = (String) item1.getValue();
	                AutoPurchaseError autoError=new AutoPurchaseError(sysErrorId,sysEx);
	                autoPurchaseErrorList.add(autoError);
				}
				for (Iterator iter2 = errorMap.entrySet().iterator(); iter2.hasNext();) {
					Map.Entry item2 = (Map.Entry) iter2.next();
					String sysErrorId = (String) item2.getKey();
	                String sysEx = (String) item2.getValue();
	                AutoPurchaseError autoError=new AutoPurchaseError(sysErrorId,sysEx);
	                autoPurchaseErrorList.add(autoError);
				}
				autoPurchaseErrorList.size();
				request.setAttribute("autoPurchaseErrorList", autoPurchaseErrorList);
	        }      
		} 
	    request.setAttribute("pStatus",pStatus );
	    request.setAttribute("total", total);
	    request.setAttribute("isProcessing",pStatus.isProcessing());
	    request.setAttribute("isReseted",pStatus.isReseted() );
	    request.setAttribute("startTime",pStatus.getStartTime().toString());
	    request.setAttribute("endTime",pStatus.getEndTime().getTime()==0?"...":pStatus.getEndTime().toString());
	    request.setAttribute("elapseString",pStatus.getElapseString());
	    request.setAttribute("averageTime",pStatus.getAverageTime() );
        return mapping.findForward("success");
	}
	
	public ActionForward updateMisDayEndProcessingFlag(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
		RtqAccountForm rtqAccForm=(RtqAccountForm) form;
		String returnMessage="";
		boolean suceess=facade.updateMisDayEndProcessingFlag(rtqAccForm.getDayEndFlag());
		if(suceess){
			returnMessage="Update Success!";
		}else{
			returnMessage="Update failure!";
		}
		request.setAttribute("returnMessage", returnMessage);
		return mapping.findForward("success");
	}
	
	public ActionForward showMisDayEndProcessingFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String dayEndFlag=	facade.getMisDayEndProcessingFlag();
		RtqAccountForm rtqAccForm=(RtqAccountForm) form;
		rtqAccForm.setDayEndFlag(dayEndFlag);
		return mapping.findForward("success");
	}
}
