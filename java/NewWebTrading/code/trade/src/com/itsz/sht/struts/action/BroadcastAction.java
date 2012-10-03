//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.admin.broadcast.util.Broadcast;
import com.itsz.sht.admin.broadcast.util.BroadcastManagement;
import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.Constants;
import com.itsz.sht.common.util.LangUtil;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.struts.form.BroadcastActionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;



/**
 * MyEclipse Struts Creation date: 08-17-2005
 * 
 * XDoclet definition:
 * 
 * @struts:action path="/Broadcast" name="broadcastForm"
 *                attribute="BroadcastForm" input="/form/broadcast.jsp"
 *                scope="request"
 */
public class BroadcastAction extends ITSZAction {
    private Log log_info = LogFactory.getLog(Constants.LOG_INFO_COMMON);
	private Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
    /*
     * (non-Javadoc)
     * 
     * @see com.itsz.web.ITSZAction#executeAction(com.itsz.view.provider.ViewProvider,
     *      org.apache.struts.action.ActionMapping,
     *      org.apache.struts.action.ActionForm,
     *      javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        log_info.info("call broadcast executeAction-->view provider");
        BroadcastActionForm actionform = (BroadcastActionForm) form;
        String clv = actionform.getCLV();
        String language=CLVSplitUtil.getLanguage(clv);
        String channelType=CLVSplitUtil.getChannelType(clv);              
        if (channelType == null ) {
            ITSZException ex = new ITSZException();
            ex.setErrorCode(Constants.ERRORCODE_PARAMETER_MCS);       
            return processException(vp, mapping, ex,request, response);                   
        }       
        String lang = LangUtil.mcs2admin(language);
        List broadcast = BroadcastManagement.getCurrentBroadcast(lang,channelType);
        if(broadcast==null){
            ITSZException ex = new ITSZException();
            ex.setErrorCode(Constants.ERRORCODE_CONNECT_LIMITED);
            log_debug.info("can't get broadcast");
            return processException(vp, mapping,ex, request, response);
        }
        ProcessBean processBean = new ProcessBean(broadcast, null, mapping, request, response);
        return vp.processBroadcast(processBean);
                
    }
    public static void main(String[] args) {
        List broadcast = BroadcastManagement.getCurrentBroadcast("en_US","STT");
        for(int i=0;i<broadcast.size();i++){
            Broadcast bc = (Broadcast)broadcast.get(i);
            System.out.println(bc.getContent());
        }
    }

}