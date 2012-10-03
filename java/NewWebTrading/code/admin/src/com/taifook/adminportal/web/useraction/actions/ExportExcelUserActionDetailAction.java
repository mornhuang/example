package com.taifook.adminportal.web.useraction.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.common.util.CompressUtil;
import com.taifook.adminportal.common.util.POIUtil;
import com.taifook.adminportal.common.util.UpDownLoadUtil;
import com.taifook.adminportal.dao.UserActionDAO;
import com.taifook.adminportal.model.CsUseractionlog;
import com.taifook.adminportal.web.useraction.forms.UserActionForm;

public class ExportExcelUserActionDetailAction extends BaseUserActionAction {
	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Log log = LogFactory.getLog(this.getClass());
		HttpSession session = request.getSession();
		String forward = Constants.SUCCESS;
		File file = null;
		File zipFile = null;
		try {
			UserActionForm actionForm = (UserActionForm) form;
			Object[] objects = this.getFiltersForDB(actionForm);

			List result = ((UserActionDAO) ServiceManager
					.getInstance()
					.getService(
							"com.taifook.adminportal.service.UserActionService"))
					.findAll(objects);

			if (result.size() < 1) {
				forward = Constants.FAILURE;
				request.setAttribute(Constants.GLOBAL_ERROR,
						"not data export to excel ! ");
				return mapping.findForward(forward);
			}

			file = new File(session.getAttribute(Constants.TEMP_FILE_PATH)
					.toString()
					+ "/useractiondetail.xls");
			
			if(file.exists()){
				file.delete();
			}
			if (this.export(file, result)) {			
				zipFile = new CompressUtil().compressFile(file);
				UpDownLoadUtil.downLoadFile(zipFile, this.getServlet()
						.getServletConfig(), request, response);
				/*
				 * inStream = new FileInputStream(file); response.reset();
				 * response.setContentType("bin");
				 * response.addHeader("Content-Disposition", "attachment;
				 * filename=\"" + file.getName() + "\""); byte[] b = new
				 * byte[1024]; int len; while ((len = inStream.read(b)) > 0)
				 * response.getOutputStream().write(b, 0, len);
				 */
			} else {
				forward = Constants.FAILURE;
				request.setAttribute(Constants.GLOBAL_ERROR,
						"export to excel fail! ");
				return mapping.findForward(forward);
			}

			forward = Constants.SUCCESS;
		} catch (Exception e) {
			forward = Constants.FAILURE;
			log
					.error("OnlineUserDetailAction-executeAction:load the page Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,
					"export data to excel file fail ! " + e.getMessage());
			return mapping.findForward(forward);
		} finally {
			if (file != null) {
				if (file.exists()) {
					file.delete();
				}
			}
		}
		return null;
	}

	private boolean export(File file, List result) {
		Object[][] data = new Object[result.size()][5];
		String[] title = new String[] { "USER_ID", "ACTION_ID", "CHANNEL_TYPE", "IP","OPERAT_TIME" };
		
		for (int index = 0; index < result.size(); index++) {
			CsUseractionlog log = (CsUseractionlog) result.get(index);
			data[index][0] = log.getUserid();
			data[index][1] = log.getActionid();
			data[index][2] = log.getChannelType();
			data[index][3] = log.getIp();
			data[index][4] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(log.getOperationtime());
		}
		return POIUtil.exportExcel(file, "ChannelServerUserActionDetail",
				title, data);
	}
}
