//Created by MyEclipse Struts
//XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

package com.itsz.web.rtq.action;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.common.Constants;
import com.itsz.web.rtq.util.RTQInfo;
import com.itsz.web.rtq.util.StreamRtqForward;

/** 
 * MyEclipse Struts
 * Creation date: 05-25-2006
 * 
 * XDoclet definition:
 * @struts.action
 */

public class SelectQuotationAction extends Action {
	private static Log log_info = LogFactory.getLog(Constants.LOG_INFO_RTQ);
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log_info.info("call select quotation executeAction-->view provider");
		
		Map rtqInfo=(Map) request.getSession().getAttribute("rtq");
		//RTQInfo rtqInfo = (RTQInfo) request.getSession().getAttribute(Constants.RTQ_INFO);
		String provider = (String) rtqInfo.get("rtqProdName");
		String forward = "errorRTQ";
		
		try {
			StreamRtqForward sf = new StreamRtqForward();
			
			if (Constants.PROVIDER_QS2.equals(provider)) {
								
				forward = sf.qs2AppletRTQ(request, rtqInfo);
				
			}else if(Constants.PROVIDER_POWER_TICKER.equals(provider)){
				request.setAttribute("powerticker",rtqInfo);
				forward = "powerticker";
			}else if(Constants.PROVIDER_ETNET.equals(provider)){
				System.out.println("etnet come into succusee");
				forward = "etnet";
			}
		} catch (Exception e) {
			forward = "errorRTQ";
		}
		return mapping.findForward(forward);
	}
}
