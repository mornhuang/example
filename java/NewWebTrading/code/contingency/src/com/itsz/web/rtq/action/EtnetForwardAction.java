//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.0.1/xslt/JavaClass.xsl

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

import com.itsz.parameter.util.ParameterManager;
import com.itsz.web.rtq.util.StreamRtqForward;

/** 
 * MyEclipse Struts
 * Creation date: 10-17-2006
 * 
 * XDoclet definition:
 * @struts.action path="/EtnetForward" name="EtnetForwardForm" scope="request"
 */
public class EtnetForwardAction extends Action {
	
	private static Log log=LogFactory.getLog(EtnetForwardAction.class);
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {		
	
		Map rtqInfo=(Map) request.getSession().getAttribute("rtq");
		String forward = "";
		StreamRtqForward sf = new StreamRtqForward();
		String etnet_switch = ParameterManager.getValue("etnet_switch");
		if ("0".equals(etnet_switch)) {
			forward = sf.etnetOldAppletRTQ(request, rtqInfo);
		} else {
			forward = sf.etnetAppletRTQ(request, rtqInfo);
		}
		return mapping.findForward(forward);
	}
}

