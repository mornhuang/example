package com.taifook.adminportal.web.broadcast.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.admin.service.syndata.SynBroadCastCallBack;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.BroadcastDAO;
import com.taifook.adminportal.exceptions.SocketTransferException;
import com.taifook.adminportal.web.broadcast.forms.BroadcastForm;
import com.taifook.common.socket.ClientSocketService;
import com.taifook.common.socket.SocketMessage;

public class DeleteBroadcastAction extends BaseBroadcastAction {

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		System.out.println("execute delete user actin log......");
		Log log = LogFactory.getLog(this.getClass());
		String forward = Constants.SUCCESS;
		boolean isSuccess = false;
		BroadcastForm actionForm = (BroadcastForm) form;
		try {
			String[] delKeys = request.getParameterValues("key");

			if (delKeys != null && delKeys.length > 0) {
				isSuccess = ((BroadcastDAO) (ServiceManager.getInstance()
						.getService("com.taifook.adminportal.service.BroadcastService")))
						.deleteByKey(delKeys);
				// notify channel server the broadcast del
				request.setAttribute(Constants.RETURN_BACK_URL,
						"../showbroadcastinit.do?"
								+ this.getFiltersForWeb(actionForm));
				new ClientSocketService().startSend(new SocketMessage(Constants.NOTIFY_BROADCAST, Constants.DEL_ACTION,delKeys,new SynBroadCastCallBack()));

				if (!isSuccess) {
					request.setAttribute(Constants.GLOBAL_ERROR,
							"delete broadcast fail! ");
					forward = Constants.FAILURE;
				} else {
					forward = Constants.SUCCESS;
				}
			}
		} catch (SocketTransferException e) {
			log.error("DeleteBroadcastAction:Delete broadcast Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,
					"Delete broadcast from database success! but "
							+ e.getMessage());
			forward = Constants.FAILURE;
		}
		return mapping.findForward(forward);
	}
}
