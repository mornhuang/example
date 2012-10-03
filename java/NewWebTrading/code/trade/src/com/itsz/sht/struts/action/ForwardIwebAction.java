package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import org.apache.struts.action.*;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.PropertyConfig;
import com.itsz.sht.struts.form.ITSZForm;

public class ForwardIwebAction extends Action {
	  private static Log log_info = LogFactory.getLog(Consts.Log.Debug.COMMON);;
	  private static String defaultLink="http://www.htisec.com";
	  

	  public final ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {
		  
		try{
			ITSZForm actionform = (ITSZForm) form;
	        String clv = actionform.getCLV();
	        String language=CLVSplitUtil.getLanguage(clv);
	        StringBuffer tempLinkKey =new StringBuffer(language);
	        tempLinkKey.append("_");
	        tempLinkKey.append(CLVSplitUtil.getChannelType(clv));
	        tempLinkKey.append("_");
	        tempLinkKey.append("IWEBUrl");
	        log_info.debug(tempLinkKey.toString());
	        String externalLink = PropertyConfig.getCommonProperty(tempLinkKey.toString());
	        log_info.info("externalLink=" +externalLink);
	        externalLink=externalLink==null || externalLink.toString().equals("")?defaultLink:externalLink;
	        response.sendRedirect(externalLink.toString());
	            
	        }
	        
	        catch (Exception ex )
	        {   
//	        ex.printStackTrace();
	            log_info.debug(ex.toString());
	           
	        }
			return null;
		  
	  }

}
