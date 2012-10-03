//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.taifook.adminportal.web.security.actions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.common.BaseAction;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.common.right.RightsManager;
import com.taifook.adminportal.dao.SecurityDAO;
import com.taifook.adminportal.dto.User;
import com.taifook.adminportal.web.security.forms.LoginForm;


/**
 * MyEclipse Struts Creation date: 03-24-2006
 * 
 * XDoclet definition:
 * 
 * @struts.action path="/login" name="/loginForm" input="/login.jsp"
 *                scope="request" validate="true"
 * @struts.action-forward name="success" path="/main.do"
 * @struts.action-forward name="fail" path="/fail.do"
 */
public class LoginAction extends BaseAction {
	Log log = null;

	public ActionForward executeAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String forward = null;

		log = LogFactory.getLog(this.getClass());
		LoginForm loginForm = (LoginForm) form;
		HttpSession session = request.getSession();
		String userid = loginForm.getUserid();
		String pwd = loginForm.getPassword();
		ActionErrors errors = new ActionErrors();

		/* ��ע�ʹ�������ڲ���?,��������,��ʵ������ʱ����ע��ȥ�� */

		if (userid.equals("") || userid == null) {
			forward = Constants.FAILURE;
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(
					"error.login.user_empty"));
		} else {
			try {
				User loginUser = ((SecurityDAO) ServiceManager
						.getInstance()
						.getService(
								"com.taifook.adminportal.service.SecurityService"))
						.userLogin(userid, pwd);
				if (loginUser != null) {
					loginUser.setRightsManager(new RightsManager(loginUser
							.getPowerStr()));// set User Right
					session.setAttribute(Constants.SESSION_USER, loginUser);
					
					// =======================added for
					// queryAcByAe=================================
					String aeCode = loginUser.getUserid();
					ArrayList acList = new ArrayList();
					try {
						acList = (ArrayList) ((SecurityDAO) ServiceManager.getInstance().getService("com.taifook.adminportal.service.SecurityService"))
								.queryAcByAe(aeCode);
					} catch (Exception e) {
						log
								.info((new SimpleDateFormat(
										"yyyy-MM-dd HH:mm:ss  "))
										.format(new Date())
										+ loginUser.getUserid()
										+ " query ac error! ");
						// throw new Exception();
					}
					session.setAttribute("acList", acList);
					// ==========================end===================================
				}
				
				createSessionUserTempFilePath(session);// create user temp directory
				forward = Constants.SUCCESS;
				log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss  "))
						.format(new Date())
						+ loginUser.getUserid() + " login success!");
			} 
			catch (Exception e) {
				forward = Constants.FAILURE;
				log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
						.format(new Date())
						+ e.getMessage());
				request.setAttribute(Constants.GLOBAL_ERROR, e.getMessage());
			}
		}

		if (!errors.isEmpty()) {
			this.saveErrors(request, errors);
		}

		// ����}�д������ڲ���,��������,��ʵ������ʱ����ע���q�?
		// forward ="test";
		
		/*  forward = Constants.SUCCESS; User loginUser = new User();
		  loginUser.setUserid("admin"); loginUser.setPowerStr("501,601");
		  loginUser.setRightsManager(new
		  RightsManager(loginUser.getPowerStr()));
		  session.setAttribute(Constants.SESSION_USER, loginUser);
		  createSessionUserTempFilePath(session);*/
		 

		return mapping.findForward(forward);
	}

	private boolean createSessionUserTempFilePath(HttpSession session) {
		// �����û���ʱĿ¼
		String tempFileStr = session.getServletContext().getRealPath(
				Constants.TEMP_FILE_PATH + "/" + session.getId());
		File tempFilePath = new File(tempFileStr);
		if (!tempFilePath.exists()) {
			tempFilePath.mkdirs();

			log.info((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss  "))
					.format(new Date())
					+ " created user temp directory: "
					+ tempFilePath.toString());
		}
		session.setAttribute(Constants.TEMP_FILE_PATH, tempFilePath);

		return true;
	}

}
