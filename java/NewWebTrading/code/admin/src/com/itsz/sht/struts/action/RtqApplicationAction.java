package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.LabelValueBean;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.request.ChangeRTQApplicationRequestModel;
import com.itsz.sht.common.model.common.request.RTQApplicationResquestModel;
import com.itsz.sht.common.model.common.response.ListRTQApplicationResponseModel;
import com.itsz.sht.common.model.common.response.RTQApplicationResponseModel;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.itsz.sht.dao.hibernate.model.RtqApplication;
import com.itsz.sht.struts.form.RtqApplicationForm;
import com.taifook.adminportal.common.Constants;


public class RtqApplicationAction extends ITSZAction {


	public ActionForward goAddRtqApplication(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RtqApplicationForm rform = (RtqApplicationForm) form;
		List<LabelValueBean> statusList=new ArrayList<LabelValueBean>();
		statusList.add(new LabelValueBean("AVAIL","AVAIL"));
		statusList.add(new LabelValueBean("UNAVAIL","UNAVAIL"));
		request.setAttribute("statusList", statusList);
		request.setAttribute("rtqAppForm", rform);
		return mapping.findForward("success");
			
	}
	

	public ActionForward addRtqApplication(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//	RtqApplicationForm rform = (RtqApplicationForm) form;
		
		  String returnCode="";
			if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
	}
	
	public ActionForward deleteRtqApplication(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//	  String productId=request.getParameter("productId");
		  String returnCode="";
			if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
	}

	public ActionForward goUpdateRtqApplication(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String productId=request.getParameter("productId");
		RtqApplicationForm rform = (RtqApplicationForm) form;
		List<LabelValueBean> statusList=new ArrayList<LabelValueBean>();
		statusList.add(new LabelValueBean("AVAIL","AVAIL"));
		statusList.add(new LabelValueBean("UNAVAIL","UNAVAIL"));
		request.setAttribute("statusList", statusList);
		RTQApplicationResquestModel rtq = new RTQApplicationResquestModel();
		rtq.setProductId(productId);
		RTQApplicationResponseModel resModel=facade.findRTQApplicationByProductId(rtq);
		String returnCode=resModel.getReturnCode();
			if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
				rform.setProdId(resModel.getRtqApplication().getProdId());
				rform.setRtqPrdr(resModel.getRtqApplication().getRtqPrdr());
				rform.setRtqStatus(resModel.getRtqApplication().getRtqStatus());
				rform.setRtqUrl(resModel.getRtqApplication().getRtqUrl());
				rform.setInitBy(resModel.getRtqApplication().getInitBy());
				rform.setInitDate(resModel.getRtqApplication().getInitDate());
				rform.setUpdBy(resModel.getRtqApplication().getUpdBy());
				rform.setUpdDate(resModel.getRtqApplication().getUpdDate());
				request.setAttribute("rtqApplicationForm", rform);
				return mapping.findForward("success");
			} else {
				return mapping.findForward("failure");
			}
	}

	public ActionForward updateRtqApplication(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AclUserProfile user = (AclUserProfile) request.getSession().getAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
		RtqApplicationForm rfrom = (RtqApplicationForm) form;
		ChangeRTQApplicationRequestModel rtq = new ChangeRTQApplicationRequestModel();
		rtq.setProductId(rfrom.getProdId());
		rtq.setrTQProvider(rfrom.getRtqPrdr());
		rtq.setrTQStatus(rfrom.getRtqStatus());
		rtq.setrTQUrl(rfrom.getRtqUrl());
		rtq.setUpdBy(user.getLognId());
		rtq.setUpdDate(new Date());
		String returnCode = facade.updateRTQApplication(rtq).getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward showRtqApplication(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String productId=request.getParameter("productId");
		RTQApplicationResquestModel rtq = new RTQApplicationResquestModel();
		rtq.setProductId(productId);
		RTQApplicationResponseModel resModel=facade.findRTQApplicationByProductId(rtq);
		String returnCode=resModel.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("RtqApplication", resModel.getRtqApplication());
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	public ActionForward listRtqApplication(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ListRTQApplicationResponseModel model=facade.listRTQApplication();
		List<RtqApplication> rtqAppList = model.getRtqApplication();
		String returnCode=model.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			request.setAttribute("rtqAppList", rtqAppList);
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}	
	}

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
}
