//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.STTUtil;
import com.itsz.sht.common.WEB_Constants;

import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.request.BulkCancelOrderRequestModel;
import com.itsz.sht.common.model.response.BulkCancelOrderResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.struts.form.BulkOrderCancelActionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.OrderIdInfo;


/**
 * MyEclipse Struts Creation date: 07-25-2005
 * 
 * XDoclet definition:
 * 
 * @struts:action path="/BulkOrderCancel" name="bulkOrderCancelForm"
 *                input="/form/bulkOrderCancel.jsp" scope="request"
 */
public class BulkOrderCancelAction extends ITSZAction {
    private static Log log_info = LogFactory.getLog(Constants.LOG_INFO_MCS);
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_MCS);
    /*
	 * (non-Javadoc)
	 * 
	 * @see com.itsz.web.ITSZAction#executeAction(com.itsz.view.provider.ViewProvider,
	 *      org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public boolean isTokenRequired() {
		return true;
	}
	
    public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        log_info.info("call bulk order cancel executeAction-->view provider");
        BulkCancelOrderRequestModel model=new BulkCancelOrderRequestModel();
        BulkOrderCancelActionForm actionFrom= (BulkOrderCancelActionForm) form;
        DataModelUtil.form2Model(request, actionFrom, model, response);
		try {
		    model.setMcsOrderID( STTUtil.split(actionFrom.getMcsOrderId(),","));
		} catch (Exception e) {
			log_info.error(e);
		}
     
        if(!isOwnAccountID(request,response,model.getAccountId())){
            ITSZException ex = new ITSZException();
            ex.setErrorCode(Constants.ERRORCODE_CONNECT_MCS);         
            log_debug.info(model.getAccountId() + ": the account id is not belong to you");
            return processException(vp, mapping, ex,request, response);
        }
        HttpSession session = request.getSession();
        UserObject user = (UserObject) session.getAttribute(Constants.USER);
        if (user != null) {
            model.setLoginId(user.getLoginName()) ;
        }
        if (model.getChannelType() == null || model.getAccountId() == null || model.getMcsOrderID() == null) {
            ITSZException ex = new ITSZException();
            ex.setErrorCode(Constants.ERRORCODE_PARAMETER_MCS);
            return processException(vp, mapping,ex, request, response);
        }
        LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession().getAttribute(WEB_Constants.LOGIN_USER_INFO);
        if(loginUserInfo!=null){
			model.setTransactionProtection(loginUserInfo.getTransactionProtection());
		}
        BulkCancelOrderResponseModel bcorm=facade.bulkCancelOrder(model);
        ProcessBean processBean = new ProcessBean(bcorm, null, mapping, request, response);
        return vp.processBulkCancelOrder(processBean);

    }    
}