//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

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
import com.taifook.adminportal.model.CsBroadcast;
import com.taifook.adminportal.web.broadcast.forms.BroadcastForm;
import com.taifook.common.socket.ClientSocketService;
import com.taifook.common.socket.SocketMessage;

/**
 * MyEclipse Struts Creation date: 03-29-2006
 * 
 * XDoclet definition:
 * 
 * @struts.action input="/jsp/editbroadcast.jsp" validate="true"
 * @struts.action-forward name="success" path="/success.do"
 * @struts.action-forward name="fail" path="/fail.do"
 */
public class UpdateBroadcastAction extends BaseBroadcastAction {

	// --------------------------------------------------------- Instance
	// Variables

	// --------------------------------------------------------- Methods

	/**
	 * Method execute
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Log log = LogFactory.getLog(this.getClass());
		String forward = Constants.SUCCESS;
		BroadcastForm broadcastForm = (BroadcastForm) form;

		if (checkInputDate(broadcastForm)) {
			try {
				Long seqno = new Long(Long.parseLong(request
						.getParameter("seqno")));
				/*
				 * CsBroadcast
				 * theboradcast=(CsBroadcast)((BroadcastDAO)ServiceManager.getInstance()
				 * .getService("com.taifook.adminportal.service.BroadcastService"))
				 * .findById(seqno);
				 */
				CsBroadcast boradcast = new CsBroadcast();
//				System.out.println(boradcast.getStarttime());
				boradcast = this.getBroadcast(broadcastForm, boradcast);
				boradcast.setSeqno(seqno);

				((BroadcastDAO) ServiceManager.getInstance().getService(
						"com.taifook.adminportal.service.BroadcastService"))
						.update(boradcast);
				log.info("update BroadcastAction:update broadcast");
				forward = Constants.SUCCESS;

				// notify channel server the broadcast update				
				request.setAttribute(Constants.RETURN_BACK_URL,"../showbroadcastinit.do?");				
				new ClientSocketService().startSend(new SocketMessage(Constants.NOTIFY_BROADCAST, Constants.UPDATE_ACTION,boradcast,new SynBroadCastCallBack()));

			} catch (SocketTransferException e) {
				log.error("UpdateBroadcastAction:Update broadcast Exception!");
				log.error(e.getMessage());
				request.setAttribute(Constants.GLOBAL_ERROR,
						"Update broadcast to database success! but "
								+ e.getMessage());
				forward = Constants.FAILURE;
			}catch (Exception e) {
				log.error("UpdateBroadcastAction:update broadcast Exception!");
				log.error(e);
				request.setAttribute(Constants.GLOBAL_ERROR,
						"update broadcast fail! " + e.getMessage());
				forward = Constants.FAILURE;
			}
		} else // input Date is not Right;
		{
			request.setAttribute(Constants.GLOBAL_ERROR,
					"sorry! the startTime is big than endTime.");
			forward = Constants.FAILURE;
		}

		return mapping.findForward(forward);
	}

}
