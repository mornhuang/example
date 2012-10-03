package com.itsz.sht.struts.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.common.model.request.EnquireRTQRequestModel;
import com.itsz.sht.common.model.response.EnquireRTQResponseModel;
import com.itsz.sht.struts.form.EnquireRTQForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: EnquireDelayRTQAction.java,v 1.6 2011/04/12 03:04:02 lpchen Exp $
 * 
 * @Project:portal
 * @File:EnquireDelayRTQAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-5-13
 */
public class EnquireDelayRTQAction extends ITSZAction {
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		EnquireRTQForm rtqForm = (EnquireRTQForm) form;
		EnquireRTQRequestModel rtqRequest = this.fillValue(request, rtqForm, response);
		EnquireRTQResponseModel rtqinfo = facade.enquireRTQInfo(rtqRequest);
		ProcessBean processBean = new ProcessBean(rtqinfo, null, mapping, request, response);
		return vp.processEnquireRTQ(processBean);
	}

	private EnquireRTQRequestModel fillValue(HttpServletRequest request,
			EnquireRTQForm form, HttpServletResponse response) {
		EnquireRTQRequestModel rtqRequest = new EnquireRTQRequestModel();
		Vector<String> v = new Vector<String>();
		v.add(form.getInstrCode());
		DataModelUtil.form2Model(request, form, rtqRequest, response);
		rtqRequest.setCode(v);
		rtqRequest.setDelayTime(String.valueOf(Consts.Qs.DELAY_TIME));
		rtqRequest.setCustomerId(PropertyConfig.getCommonProperty(Consts.Qs.RTQ_CUSTOMERID));
		rtqRequest.setSeqn(Consts.Qs.SEQN);
		rtqRequest.setServiceType(Consts.Qs.ST_STOCK_FREE);
		rtqRequest.setSubType(Consts.Qs.FREE_QUOT_REAL_TIME);
		rtqRequest.setType(Consts.Qs.ENQ_TYPE);
		return rtqRequest;
	}
}
