package com.taifook.adminportal.web.mobileAgent.actions;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.ServiceManager;
import com.taifook.adminportal.common.util.page.Page;
import com.taifook.adminportal.dao.MobileAgentDAO;
import com.taifook.adminportal.dao.ParameterDAO;
import com.taifook.adminportal.model.CsParameter;
import com.taifook.adminportal.web.mobileAgent.forms.MobileAgentForm;

/**
 * $Id: QueryMobileAgentAction.java,v 1.2 2010/11/09 04:31:54 kyzou Exp $
 * @Project:admin-portal
 * @File:QueryMobileAgent.java
 * @Description:
 * @Author:kyzou
 * @Date:2008-8-26
 */
public class QueryMobileAgentAction extends Action {
	public ActionForward execute(ActionMapping mapping,
			   ActionForm form,HttpServletRequest request,
	           HttpServletResponse response) throws Exception {
 
		Log log = LogFactory.getLog(this.getClass());
		String forward = Constants.SUCCESS;

		try {
			MobileAgentForm actionForm = (MobileAgentForm) form;
			Object[] objects = getFiltersForDB(actionForm);
			int pageNumber = getPageNumber(request); // get current page
			int pageSize = getPageSize(); // get page size;
			Page page = ((MobileAgentDAO) ServiceManager.getInstance().getService(
							"com.taifook.adminportal.service.MobileAgentService"))
					.findByPage(objects, pageNumber, pageSize);

			HttpSession session = request.getSession();
			session.removeAttribute(Constants.RESULT);
			session.setAttribute(Constants.RESULT, page);
			request.setAttribute(Constants.FILTER, actionForm);
			request.setAttribute(Constants.URL_PARAMETER,this.getFiltersForWeb(actionForm));
			
			forward = Constants.SUCCESS;
		} catch (Exception e) {
			log.error("QueryMobileAgentAction-executeAction:load the page Exception!");
			log.error(e.getMessage());
			request.setAttribute(Constants.GLOBAL_ERROR,"Query mobile agent fail! " + e.getMessage());
			forward = Constants.FAILURE;
		}
		return mapping.findForward(forward);
	}
	
	protected Object[] getFiltersForDB(MobileAgentForm actionForm) {		
		List filterList=new ArrayList();
		
		if (actionForm.getParamName() != null
				&& !actionForm.getParamName().trim().equals("")) {
			filterList.add("id.paramName ='"+actionForm.getParamName()+"'");
		}else{
			filterList.add("id.paramName='"+Constants.ParamKey.AGENT_BLACK_LIST+"'");
			actionForm.setParamName(Constants.ParamKey.AGENT_BLACK_LIST);
		}

		if (actionForm.getParamValue() != null
				&& !actionForm.getParamValue().trim().equals("")) {
			filterList.add("id.paramValue= '"+actionForm.getParamValue()+"'");
		}

		if (actionForm.getUpdateTime() != null
				&& !actionForm.getUpdateTime().equals("")) {
			filterList.add("to_char(updateTime,\'YYYY-MM-DD HH24:MI:SS\')= '"+actionForm.getUpdateTime()+"'");
		}
		
		return filterList.toArray();
	}
	
	protected String getFiltersForWeb(MobileAgentForm actionForm) {		
		StringBuffer sb=new StringBuffer();
		
		if (actionForm.getParamName()!= null
				&& !actionForm.getParamName().trim().equals("")) {
			sb.append("paramName="+URLEncoder.encode(actionForm.getParamName())+"&");
		}else{
			sb.append("paramName="+URLEncoder.encode(Constants.ParamKey.AGENT_BLACK_LIST)+"&");
		}

		if (actionForm.getParamValue() != null
				&& !actionForm.getParamValue().trim().equals("")) {
			sb.append("paramValue="+URLEncoder.encode(actionForm.getParamValue())+"&");
		}
		
		if (actionForm.getDescription() != null
				&& !actionForm.getDescription().equals("")) {
			sb.append("description="+URLEncoder.encode(actionForm.getDescription())+"&");
		}

		if (actionForm.getUpdateTime() != null
				&& !actionForm.getUpdateTime().equals("")) {
			sb.append("updateTime="+URLEncoder.encode(actionForm.getUpdateTime())+"&");
		}
		
		return sb.toString();
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
			if(pageParameter!=null) pageSize=Integer.parseInt(pageParameter.getValue());
			if(pageSize<=0)pageSize=Constants.DEFAULT_PAGE_SIZE;
			log.info("pageSize:"+pageSize);
		    } catch (Exception e) {
			    log.warn("getPageSize is exception!");
			    pageSize=Constants.DEFAULT_PAGE_SIZE;
		    }
		  return pageSize;  
	}

}
