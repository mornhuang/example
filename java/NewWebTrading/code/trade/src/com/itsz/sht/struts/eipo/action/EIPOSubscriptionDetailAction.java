package com.itsz.sht.struts.eipo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.eipo.webservice.EIPOResponse;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.EIPOSubscriptionDetailRequestModel;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.eipo.form.EIPOSubscriptionDetailForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class EIPOSubscriptionDetailAction extends ITSZAction{

	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
		EIPOSubscriptionDetailRequestModel model = new EIPOSubscriptionDetailRequestModel();
		DataModelUtil.form2Model(request,(EIPOSubscriptionDetailForm) form, model, response);
		EIPOResponse responseModel = facade.getEIPOSubscriptionDetail(model);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
		return vp.processEIPOSubscriptionDetail(processBean);
	}

}
