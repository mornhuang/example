package com.itsz.sht.admin.service.check;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.admin.broadcast.util.BroadcastInfo;
import com.itsz.sht.admin.broadcast.util.BroadcastManagement;
import com.taifook.adminportal.common.Constants;

public class ShowBroadAction extends Action {
	private static Log log=LogFactory.getLog(ShowBroadAction.class);
	
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
	        	               			
			StringBuffer outSb=new StringBuffer();			
			List broadList=BroadcastManagement.getBroadcastlist();	
			outSb.append("********************<strong>Result</strong>***********************<br>");
			if(broadList!=null && broadList.size()>0){
				String id=request.getParameter("id");
				BroadcastInfo bcroadcast=BroadcastManagement.getBroadcastById(id);				
				if(bcroadcast!=null){
					outSb.append("ID            :"+bcroadcast.getId()+"<br>");
					outSb.append("startTime     :"+bcroadcast.getStime()+"<br>");
					outSb.append("endTime       :"+bcroadcast.getEtime()+"<br>");
					outSb.append("contentType   :"+bcroadcast.getContenttype()+"<br>");
					outSb.append("level         :"+bcroadcast.getLevel()+"<br>");
					outSb.append("channels      :"+bcroadcast.getChannels()+"<br>");
					outSb.append("content_zh_TW :"+Convert2Xml(bcroadcast.getContent_zh_TW())+"<br>");
					outSb.append("content_en_US :"+Convert2Xml(bcroadcast.getContent_en_US())+"<br>");
					outSb.append("content_zh_CN :"+Convert2Xml(bcroadcast.getContent_zh_CN())+"<br>");
					outSb.append("lastModifyTime:"+bcroadcast.getLastmodifytime()+"<br>");			
				}else{
					outSb.append("<strong>BroadCast ID:"+id+" Not Found !</strong><br>");
				}
			}else{
				outSb.append("<strong>BroadCast List is Empty!</strong><br>");
			}
			outSb.append("*************************************************");
			
			out=response.getWriter();
			out.write(outSb.toString());
			out.flush();			
		} catch (Exception e) {
//			e.printStackTrace();
			log.error("exception exist:"+e.getMessage());
		}finally{
			try{
				if(out!=null){
					out.close();
				}
			}catch(Exception e){
				log.error("ShowBroadAction--exception:"+e.getMessage());
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