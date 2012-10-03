//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_3.8.4/xslt/JavaClass.xsl

package com.itsz.sht.struts.action;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.PrePlaceOrderRequestModel;
import com.itsz.sht.common.model.response.placeorder.PrePlaceOrderResponseModel;
import com.itsz.sht.struts.form.PreInputOrderActionForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;


/** 
 * MyEclipse Struts
 * Creation date: 07-25-2005
 * 
 * XDoclet definition:
 * @struts:action path="/PreInputOrder" name="preInputOrderForm" input="/form/preInputOrder.jsp" scope="request"
 */
public class PreInputOrderAction extends ITSZAction {

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
        log_info.info("call stt pre-inputorder executeAction-->view provider");
 
        PrePlaceOrderRequestModel model=new PrePlaceOrderRequestModel();
        DataModelUtil.form2Model(request, (PreInputOrderActionForm) form, model, response);
  
        PrePlaceOrderResponseModel pporm=facade.prePlaceOrder(model);
        if (Consts.Global.Status.NORMAL.equals(pporm.getReturnCode())) {
			setTokenToSession(request);
		}
		ProcessBean processBean = new ProcessBean(pporm, null, mapping,
												request, response);
        return vp.processPrePlaceOrder(processBean);                
    }

}