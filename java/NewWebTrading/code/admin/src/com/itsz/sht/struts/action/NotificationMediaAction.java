package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.model.common.request.NotificationMediaListRequestModel;
import com.itsz.sht.common.model.common.response.NotificationMediaListResponseModel;
import com.itsz.sht.dao.hibernate.model.AclUserProfile;
import com.taifook.adminportal.common.Constants;

public class NotificationMediaAction extends ITSZAction {

	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//	NotificationMediaForm nmform = (NotificationMediaForm) form;
		String notfType = (String) request.getParameter("queryType");
		NotificationMediaListRequestModel notificationMediaListRequestModel = new NotificationMediaListRequestModel();
		notificationMediaListRequestModel.setNotfType(notfType);
		NotificationMediaListResponseModel notificationMediaListResponseModel = facade.exportNotificationMedia(notificationMediaListRequestModel);
		request.setAttribute("ba", notificationMediaListResponseModel.getBa());
		request.setAttribute("queryType", notfType);
		String returnCode = notificationMediaListResponseModel.getReturnCode();
		if (returnCode.equals(Consts.AdminPortal.returnResulst.SUCCESS)) {
			return mapping.findForward("success");
		} else {
			return mapping.findForward("failure");
		}
	}

	@Override
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
