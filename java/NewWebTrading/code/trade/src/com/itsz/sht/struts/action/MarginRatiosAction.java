package com.itsz.sht.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.model.request.MarginFinancingListRequestModel;
import com.itsz.sht.common.model.response.MarginFinancingListResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.struts.form.MarginRationsForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

/**
 * $Id: MarginRatiosAction.java,v 1.4 2010/12/10 08:01:41 bwu Exp $
 * @Project:NewWebTrading
 * @File:MarginRatiosAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2010-12-7
 */
public class MarginRatiosAction extends ITSZAction {
	
	private static final int RECORD_PER_PAGE = 10;
	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		MarginFinancingListRequestModel model = new MarginFinancingListRequestModel();
		MarginRationsForm Marginform=(MarginRationsForm)form;
		String instrumentCode=Marginform.getInstrumentCode();
		if (!StringUtils.isEmpty(instrumentCode)) {
			try{
			instrumentCode = ""+Integer.valueOf(instrumentCode);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		} else {
			instrumentCode = null;
		}
		 int fromIdx=0;
		 int toIdx=RECORD_PER_PAGE;
		 int size = 0, ipage = 1, totalPage=0;
		 if (request.getParameter("total_size") != null) {
			 size = Integer.parseInt(request.getParameter("total_size"));
         }
         if (request.getParameter("order_book_page") != null) {
             ipage = Integer.parseInt(request.getParameter("order_book_page"));
         }
        

		if (size > 0 && ipage > 0) {
			fromIdx = (ipage - 1) * RECORD_PER_PAGE > 0 ? (ipage - 1) * RECORD_PER_PAGE : 0;
			toIdx = fromIdx + RECORD_PER_PAGE;
          }
		 if (instrumentCode != null && 
	         		!"null".equals(instrumentCode)) {
			 	model.setInstrCode(instrumentCode);
	         	instrumentCode = request.getParameter("instrCode");
	    }else{
			model.setFromIdx(fromIdx);
			model.setToIdx(toIdx);		
		}
		 UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null) {
			model.setLoginId(user.getLoginName());
		}
		DataModelUtil.form2Model(request,(ITSZForm) form, model, response);
		MarginFinancingListResponseModel responseModel = facade.enquireMarginRations(model);
		size=responseModel.getMarginFinancingListResponse().getListSize();
		totalPage = size / RECORD_PER_PAGE + (size % RECORD_PER_PAGE > 0 ? 1 : 0);
        totalPage = totalPage > 0 ? totalPage : 1;
        request.setAttribute("size", size);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("ipage", ipage);
        request.setAttribute("previous_page", new Integer(ipage == 1 ? 1 : ipage - 1));
        request.setAttribute("next_page", new Integer(ipage == totalPage ? totalPage : ipage + 1));
        request.setAttribute("instrCode", instrumentCode);
		ProcessBean processBean = new ProcessBean(responseModel,null, mapping, request,response);
		return vp.processMarginRatios(processBean);
	}

}
