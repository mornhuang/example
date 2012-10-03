package com.itsz.sht.admin.service.check;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.admin.channel.util.ChannelInfo;
import com.itsz.sht.admin.channel.util.ChannelInfoManagement;
import com.taifook.adminportal.common.Constants;

public class ShowChannelAction extends Action {
	private static Log log=LogFactory.getLog(ShowChannelAction.class);
	
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
	        
			List channels=ChannelInfoManagement.getChannelInfos();	
			StringBuffer outSb=new StringBuffer();
			
			outSb.append("********************<strong>Result</strong>***********************<br>");
			if(channels!=null && channels.size()>0){
				Iterator it=channels.iterator();
				while(it.hasNext()){
					ChannelInfo channelInfo=(ChannelInfo)it.next();
					outSb.append("code     :"+channelInfo.getCode()+"<br>");
					outSb.append("name     :"+channelInfo.getName()+"<br>");
					outSb.append("state    :"+channelInfo.getState()+"<br>");
					outSb.append("userCount:"+channelInfo.getUsercount()+"<br>");
					outSb.append("<br>");
				}
			}else{
				outSb.append("<strong>Not Found Any Channels</strong><br>");
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
				log.error("ShowChannelAction--exception:"+e.getMessage());
			}
		}
		return null;
	}
}