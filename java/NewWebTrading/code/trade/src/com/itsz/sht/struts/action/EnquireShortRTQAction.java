package com.itsz.sht.struts.action;

import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EnquireShortRTQRequestModel;
import com.itsz.sht.common.model.response.EnquireShortRTQResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.EnquireShortRTQForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: EnquireShortRTQAction.java,v 1.3 2010/12/29 06:18:10 zxfan Exp $
 * 
 * @Project:portal
 * @File:EnquireShortRTQAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-4-13
 */
public class EnquireShortRTQAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		EnquireShortRTQForm rtqForm = (EnquireShortRTQForm) form;
		EnquireShortRTQRequestModel rtqRequest = this.fillValue(request, rtqForm, response);
		EnquireShortRTQResponseModel rtqinfo = facade.enquireShortRTQInfo(rtqRequest);
		ProcessBean processBean = new ProcessBean(rtqinfo, null, mapping, request, response);
		return vp.processShortEnquireRTQ(processBean);
	}

	private EnquireShortRTQRequestModel fillValue(HttpServletRequest request,
			EnquireShortRTQForm form, HttpServletResponse response) {
		EnquireShortRTQRequestModel rtqRequest = new EnquireShortRTQRequestModel();
		DataModelUtil.form2Model(request, form, rtqRequest, response);
		String codes[] = StringUtils.split(form.getInstrCode(), ",");
		Vector<String> v = new Vector<String>();
		for (int i = 0; i < codes.length; i++) {
			v.add(codes[i]);
		}
		rtqRequest.setInstrCode(v);
		rtqRequest.setQuoteType(Consts.Qs.S_TYPE_DELAY);
		rtqRequest.setCustCode(((UserObject) request.getSession().getAttribute(
				Consts.Profile.USER)).getTradeInfoObject().getCustCode());
		return rtqRequest;
	}

}
