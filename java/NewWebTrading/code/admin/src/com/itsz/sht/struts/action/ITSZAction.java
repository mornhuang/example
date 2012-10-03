package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.service.Facade;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.dao.ParameterDAO;
import com.taifook.adminportal.model.CsParameter;


public abstract class ITSZAction extends DispatchAction {
	public Facade facade;


	public ITSZAction() {
		super();
		facade = (Facade) ServiceLocator.getInstance().getService("facade");
	}

	/**
	 * 
	 * @Time:10:26:43
	 * @param request
	 * @param response
	 */

	public abstract ActionForward executeAction(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * logonAction overwrite this method and return true;
	 * @Time:10:27:59
	 * @return
	 */
	public boolean isLoginActionExecuted() {
		return false;
	}

	/**
	 * struts token required 
	 * @Time:10:28:16
	 * @return
	 */
	public boolean isTokenRequired() {
		return false;
	}

	/**
	 * check is user login or not
	 * @Time:10:29:02
	 * @param request
	 * @throws ITSZException
	 */
	public static void sessionManagement(HttpServletRequest request)
			throws ITSZException {
//		HttpSession session = request.getSession();
//		if (session.getAttribute(Constants.USER) == null) {
//			log_debug
//					.info("@@@@@@@@@@@  User not Exist  id=" + session.getId());
//			ITSZException ex = new ITSZException();
//			ex.setErrorCode(Consts.Error.Code.SESSION_OVERTIME);
//			throw ex;
//		} else {
//			UserObject user = (UserObject) session.getAttribute(Constants.USER);
//			String channelType = user.getChannelType();
//			String loginId = user.getLoginName();
//			if (!UserManagement
//					.allowUser(session.getId(), channelType, loginId)) {
//				log_debug.info("@@@@@@@@@@@  sessionID not Exist  id="
//						+ session.getId());
//				ITSZException ex = new ITSZException();
//				ex.setErrorCode(Consts.Error.Code.SESSION_OVERTIME);
//				throw ex;
//			}
//		}
	}
	
	protected int getPageNumber(HttpServletRequest request){
		  Log log = LogFactory.getLog(this.getClass()); 
		  int pageNumber=Constants.DEFAULT_PAGE_NUMBER;
		  try {
			   String currentPage=request.getParameter(Constants.CURRENT_PAGE);
			   if(currentPage==null||currentPage.trim().length()==0)
				   pageNumber=Constants.DEFAULT_PAGE_NUMBER;
			   else
				   pageNumber=Integer.parseInt(currentPage);
			   if(pageNumber<=0)pageNumber=Constants.DEFAULT_PAGE_NUMBER;
			   log.info("pageNumber:"+pageNumber);
		       } catch (Exception e1) {
		         log.warn("currentpage is Exception!");
		         pageNumber=Constants.DEFAULT_PAGE_NUMBER;
		       }
	     return pageNumber;
   }
	protected int getPageSize(){
		  Log log = LogFactory.getLog(this.getClass()); 
		  int pageSize=Constants.DEFAULT_PAGE_SIZE;
		  try {
			CsParameter pageParameter=(CsParameter)((ParameterDAO)ServiceManager.getInstance()
			          .getService("com.taifook.adminportal.service.ParameterService")).findById(Constants.PAGE_SIZE);
			pageSize=Integer.parseInt(pageParameter.getValue());
			if(pageSize<=0)pageSize=Constants.DEFAULT_PAGE_SIZE;
			log.info("pageSize:"+pageSize);
		    } catch (Exception e) {
			    log.warn("getPageSize is exception!");
			    pageSize=Constants.DEFAULT_PAGE_SIZE;
		    }
		  return pageSize;  
	  }
}
