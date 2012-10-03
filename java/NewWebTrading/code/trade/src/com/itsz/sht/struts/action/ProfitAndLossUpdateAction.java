//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.action;

import java.math.BigDecimal;
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
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.ProfitAndLossUpdateRequestModel;
import com.itsz.sht.common.model.response.ProfitAndLossEnqiryResponseModel;
import com.itsz.sht.common.model.response.ProfitAndLossUpdateResponseModel;
import com.itsz.sht.common.user.TradeInfoObject;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.exception.ITSZException;
import com.itsz.sht.struts.form.ProfitAndLossEnquiryActionForm;
import com.itsz.sht.struts.form.ProfitAndLossUpdateActionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.MCSMessage;

/** 
 * MyEclipse Struts
 * Creation date: 08-23-2005
 * 
 * XDoclet definition:
 * @struts:action path="/ProfitAndLossUpdate" name="profitAndLossUpdateForm" input="/form/profitAndLossUpdate.jsp" scope="request"
 */
public class ProfitAndLossUpdateAction extends ITSZAction {	
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
        log_debug.info("call profitAndLossUpdate executeAction-->view provider");
        ProfitAndLossUpdateRequestModel requestModel =new ProfitAndLossUpdateRequestModel();
        DataModelUtil.form2Model(request, (ProfitAndLossUpdateActionForm) form, requestModel, response);
        HttpSession session = request.getSession();     
        UserObject user = (UserObject) session.getAttribute(Constants.USER);
        TradeInfoObject tradeInfo = user.getTradeInfoObject();
        requestModel.setMarketCode(tradeInfo.getMarketCode());
        requestModel.setLoginID(user.getLoginName());
        if(requestModel.getChannelType()==null||requestModel.getLoginID()==null||
        		requestModel.getProfileID()==null ||requestModel.getProfileInfo()==null){
			ITSZException ex=new ITSZException();
	    	ex.setErrorCode(Constants.ERRORCODE_PARAMETER_MCS);
	    	return processException(vp,mapping,ex,request,response);
		}
        ProfitAndLossUpdateResponseModel palurm=facade.profitAndLossUpdate(requestModel);
        ProcessBean processBean = new ProcessBean(palurm, null, mapping,request, response);       
        return vp.processProfitAndLossUpdate(processBean);

    }

}