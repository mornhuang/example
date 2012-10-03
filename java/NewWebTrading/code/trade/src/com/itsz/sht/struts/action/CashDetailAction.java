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
import com.itsz.sht.common.model.request.CashDetailRequestModel;
import com.itsz.sht.common.model.response.CashDetailResponseModel;
import com.itsz.sht.common.user.TradeInfoObject;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.CashDetailActionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/** 
 * MyEclipse Struts
 * Creation date: 08-18-2005
 * 
 * XDoclet definition:
 * @struts:action path="/CashDetail" name="cashDetailForm" input="/form/cashDetail.jsp" scope="request"
 */
public class CashDetailAction extends ITSZAction {
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
        log_debug.info("call cashDetail executeAction-->view provider");
        CashDetailRequestModel cashDetailRequestModel=new CashDetailRequestModel();
        DataModelUtil.request2Model(request, cashDetailRequestModel);
		DataModelUtil.form2Model(request,(CashDetailActionForm) form, cashDetailRequestModel, response);
        HttpSession session = request.getSession();
        UserObject user = (UserObject)session.getAttribute(Constants.USER);
        if (user != null) {
            TradeInfoObject trade = user.getTradeInfoObject();         
            cashDetailRequestModel.setMarketCode(trade.getMarketCode());
        }
        CashDetailResponseModel cashDetailResponseModel=facade.enquireCashDetail(cashDetailRequestModel);
        ProcessBean processBean = new ProcessBean(cashDetailResponseModel,null, mapping, (CashDetailActionForm) form, request,response);
        return vp.processCashDetail(processBean);
    }
}