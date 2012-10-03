//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.ProfitAndLossEnqiryRequestModel;
import com.itsz.sht.common.model.response.ProfitAndLossEnqiryResponseModel;
import com.itsz.sht.common.user.TradeInfoObject;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.struts.form.ProfitAndLossEnquiryActionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/** 
 * MyEclipse Struts
 * Creation date: 08-23-2005
 * 
 * XDoclet definition:
 * @struts:action path="/ProfitAndLossEnquiry" name="profitAndLossEnquiryForm" attribute="ProfitAndLossEnquiryForm" input="/form/profitAndLossEnquiry.jsp" scope="request"
 */
public class ProfitAndLossEnquiryAction extends ITSZAction {
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
    public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {

        log_debug.info("call profitAndLossEnquiry executeAction-->view provider");     
        ProfitAndLossEnqiryRequestModel requestModel=new ProfitAndLossEnqiryRequestModel();
        DataModelUtil.form2Model(request, (ProfitAndLossEnquiryActionForm) form, requestModel, response);
        HttpSession session = request.getSession();     
        UserObject user = new UserObject();
        user = (UserObject) session.getAttribute(Constants.USER);
        TradeInfoObject tradeInfo = user.getTradeInfoObject();
        requestModel.setMarketCode(tradeInfo.getMarketCode()) ;
        requestModel.setLoginID(user.getLoginName()) ; 
        if(requestModel.getChannelType()==null || requestModel.getLoginID()==null || requestModel.getProfileID()==null ||
        		requestModel.getQuoteType()==null || requestModel.getAccountID()==null){
			ITSZException ex=new ITSZException();
	    	ex.setErrorCode(Constants.ERRORCODE_PARAMETER_MCS);
	    	return processException(vp,mapping,ex,request,response);
		}
        ProfitAndLossEnqiryResponseModel resModel=facade.profitAndLossEnqiry(requestModel);
        ProcessBean processBean = new ProcessBean(resModel, null, mapping,request, response);
        return vp.processProfitAndLossEnquiry(processBean);
    }


}