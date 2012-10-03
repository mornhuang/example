package com.taifook.adminportal.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.taifook.adminportal.dao.ParameterDAO;
import com.taifook.adminportal.model.CsParameter;

/**
 * <p> * Title: admin_portal           * </p>
 * <p> * Description:                  * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
public abstract class BaseAction extends Action{
	public final ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
			           HttpServletResponse response) throws Exception {
		//check session is vaildate.
		//HttpSession session=request.getSession();
		//if(session.getAttribute("loginid")!=null)
		    
			return executeAction(mapping,form,request,response);
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

	protected String getConstraintForDB(String filter, boolean isChar){
		String[] operators1 =new String[]{">","<","=","%"};
		String[] operators2 =new String[]{">=","<=","<>"};			
		String separator="";		
		String str=filter.trim().toUpperCase();		
		int len=str.length();
		
		if(isChar){
			separator="'";
		}
		
		//(1)��}���ַ�Ĳ����
		if(len>=2){
			String comparetor=str.substring(str.length()-2,str.length());
			for(int index=0;index<operators2.length;index++){
				String operator=operators2[index];
				if(comparetor.equalsIgnoreCase(operator)){
//					System.out.println("db filter: "+operator+separator+str.substring(0,str.length()-2)+separator);
					return operator+separator+str.substring(0,str.length()-2)+separator;					
				}
			}
		}
		
		//(1)��һ���ַ�Ĳ����
		if(len>=1){
			String comparetor=str.substring(str.length()-1,str.length());
			for(int index=0;index<operators1.length;index++){
				String operator=operators1[index];
				if(comparetor.equalsIgnoreCase(operator)){
					if(operator.equals("%")){
//						System.out.println("db filter: "+"like '%"+str.substring(0,str.length()-1)+"%'");
						return "like '%"+str.substring(0,str.length()-1)+"%'";
					}else{
//						System.out.println("db filter: "+operator+separator+str.substring(0,str.length()-1)+separator);
						return operator+separator+str.substring(0,str.length()-1)+separator;	
					}
				}
			}
		}
		
		return "="+separator+str+separator;
		
	}
	
	
    public abstract ActionForward executeAction(ActionMapping mapping,ActionForm form,HttpServletRequest request,
	           HttpServletResponse response) throws Exception;
}

