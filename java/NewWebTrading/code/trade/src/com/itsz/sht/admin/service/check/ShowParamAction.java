package com.itsz.sht.admin.service.check;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.admin.parameter.util.ParaManagement;
import com.taifook.adminportal.common.Constants;

public class ShowParamAction extends Action {
	private static Log log=LogFactory.getLog(ShowParamAction.class);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String forward = Constants.SUCCESS;
		PrintWriter out=null;
		try {
	        response.setContentType("text/html;charset=UTF-8");
	        response.setHeader("Pragma", "No-cache");
	        response.setHeader("Cache-Control", "no-cache, must-revalidate");
	        response.setDateHeader("Expires", 0);
			String key=request.getParameter("key");	
			
			StringBuffer outSb=new StringBuffer();
			
			outSb.append("********************<strong>Result</strong>***********************<br>");
			if(ParaManagement.containsKey(key)){
				String value=ParaManagement.getParaValueByKey(key);						
				outSb.append("key  :"+Convert2Xml(key)+"<br>");
				outSb.append("value:"+Convert2Xml(value)+"<br>");				
			}else{
				outSb.append("<strong>Parameter Key:"+Convert2Xml(key)+" Not Found !</strong><br>");
			}
			outSb.append("*************************************************");
			out=response.getWriter();			
			out.write(outSb.toString());
			out.flush();			
		} catch (Exception e) {
//			e.printStackTrace();
		}finally{
			try{
				if(out!=null){
					out.close();
				}
			}catch(Exception e){
				log.error("ShowParamAction--exception:"+e.getMessage());
			}
		}
		return null;
	}
	
	private String Convert2Xml(String value) {
		if (value != null) {
			String[] xmlChar = new String[] { "&", "<", ">", "'", "\"" };
			String[] hexChar = new String[] { "&amp;", "&lt;", "&gt;","&#039;", "&#034;" };
			for (int i = 0; i < xmlChar.length; i++) {
				value = value.replaceAll(xmlChar[i], hexChar[i]);
			}
		}
		return value;
	}
}