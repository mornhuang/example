package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DateUtil;
import com.itsz.sht.common.auto.AutoPurchaseConstants;
import com.itsz.sht.common.model.common.request.AutoPurchaseRequestModel;
import com.itsz.sht.common.model.common.response.AutoPurchaseResponseModel;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.AutoPurchase;
import com.itsz.sht.struts.form.AutoPurchaseForm;


public class AutoPurchaseAction extends ITSZAction {

	public ActionForward autoPurchaseEnquiry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		AutoPurchaseForm autoPurchaseform = (AutoPurchaseForm)form;
		Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        int maxday = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        String fYear, fMonth, fDay, tYear, tMonth, tDay;
        if(autoPurchaseform.getStartYear()!=null && !autoPurchaseform.getStartYear().equals("")){
	        fYear = autoPurchaseform.getStartYear();
	        fMonth = autoPurchaseform.getStartMonth();
	        fDay = autoPurchaseform.getStartDay();
	        tYear = autoPurchaseform.getEndYear();
	        tMonth = autoPurchaseform.getEndMonth();
	        tDay = autoPurchaseform.getEndDay();
		}else{
            fYear = tYear = String.valueOf(year);
            fMonth = tMonth = String.valueOf(month);
            fDay = tDay = String.valueOf(day);
            autoPurchaseform.setStartYear(String.valueOf(year));
            autoPurchaseform.setEndYear(String.valueOf(year));
            autoPurchaseform.setStartMonth(String.valueOf(month));
            autoPurchaseform.setEndMonth(String.valueOf(month));
            autoPurchaseform.setStartDay(String.valueOf(day));
            autoPurchaseform.setEndDay(String.valueOf(day));
        }
        
        List<LabelValueBean> statusList=new ArrayList<LabelValueBean>();
        statusList.add(new LabelValueBean("ALL STATUS",""));
        statusList.add(new LabelValueBean(AutoPurchaseConstants.PurchaseStatus.ST_SUCCESS, AutoPurchaseConstants.PurchaseStatus.ST_SUCCESS));
        statusList.add(new LabelValueBean(AutoPurchaseConstants.PurchaseStatus.ST_NOT_ENOUGH_MONEY, AutoPurchaseConstants.PurchaseStatus.ST_NOT_ENOUGH_MONEY));
        statusList.add(new LabelValueBean(AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR, AutoPurchaseConstants.PurchaseStatus.ST_SYS_ERROR));
        request.setAttribute("statusList", statusList);
		
		List<LabelValueBean> yearList=new ArrayList<LabelValueBean>();
		for (int i=2010;i<year+1;i++) {
			yearList.add(new LabelValueBean(i+"",String.valueOf(i)));
		}
		List<LabelValueBean> monthList=new ArrayList<LabelValueBean>();
		for (int i=1;i<13;i++) {
			monthList.add(new LabelValueBean(i+"",String.valueOf(i)));
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Integer.parseInt(fYear), Integer.parseInt(fMonth)-1, Integer.parseInt(fDay));
		maxday = DateUtil.getMaximumDayOfMonth(calendar.getTime());
		List<LabelValueBean> fdayList=new ArrayList<LabelValueBean>();
		for (int i=1;i<=maxday;i++) {
			fdayList.add(new LabelValueBean(i+"",String.valueOf(i)));
		}
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 1);
		String startTime = DateUtil.getStringforDate(calendar.getTime());
		
		calendar.set(Integer.parseInt(tYear), Integer.parseInt(tMonth)-1, Integer.parseInt(tDay));
		maxday = DateUtil.getMaximumDayOfMonth(calendar.getTime());
		List<LabelValueBean> tdayList=new ArrayList<LabelValueBean>();
		for (int i=1;i<=maxday;i++) {
			tdayList.add(new LabelValueBean(i+"",String.valueOf(i)));
		}
		calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
		String endTime = DateUtil.getStringforDate(calendar.getTime());
		request.setAttribute("yearList", yearList);
		request.setAttribute("monthList", monthList);
		request.setAttribute("fdayList", fdayList);
		request.setAttribute("tdayList", tdayList);
		 
        String status = autoPurchaseform.getStatus();
        AutoPurchaseRequestModel autoPurchaseRequestModel = new AutoPurchaseRequestModel();
        autoPurchaseRequestModel.setStatus(status==null?"":status);
        autoPurchaseRequestModel.setStartTime(startTime);
        autoPurchaseRequestModel.setEndTime(endTime);
        AutoPurchaseResponseModel res = facade.listAutoPurchase(autoPurchaseRequestModel);
        if(res != null){
        	List<AutoPurchase> autoPurchaseList = res.getAutoPurchaseList();
        	request.setAttribute("autoPurchaseList", autoPurchaseList);
        }
        request.setAttribute("createdBy", user.getLognId());
        request.setAttribute("createdDate", DateUtil.getStringforDate(new Date()));
		return mapping.findForward("success");
	}
	
	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
	
}
