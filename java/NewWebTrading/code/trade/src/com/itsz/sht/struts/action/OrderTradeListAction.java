package com.itsz.sht.struts.action;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.DateHelper;
import com.itsz.sht.common.FormatUtil;
import com.itsz.sht.common.model.request.TradeListRequestModel;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.common.util.FormatConversion;
import com.itsz.sht.struts.form.TradeListForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.TradeListInfo;
import com.taifook.mcs.core.beans.msg.TradeListResponse;

/**
 * $Id: OrderTradeListAction.java,v 1.3 2011/01/14 08:23:29 xlliu Exp $
 * @Project:portal
 * @File:TradeListAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-6-26
 */
public class OrderTradeListAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TradeListRequestModel model = new TradeListRequestModel();
		String pageNo = request.getParameter("pageNo");
		String type = request.getParameter("type");
		if(type != null){
			model.setPageSize("5");
			String radioDate = (String)request.getParameter("radioDate");
			String radioCode = (String)request.getParameter("radioCode");
			DataModelUtil.form2Model(request,(TradeListForm)form, model, response);
			UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
			if (user != null) {
				model.setLoginId(user.getLoginName());
				if(StringUtils.isBlank(model.getAccountId())){
					model.setAccountId(user.getTradeInfoObject().getTradingAccount());
				}
			}
			if(radioDate.equals("true")){
				int day = Integer.parseInt(request.getParameter("day"))+1;
				List dateList = DateHelper.getBeforeOneWeekDate(day);
				if(dateList!=null && dateList.size()>0){
					if(day == 1){
						model.setFromDate(dateList.get(day-1).toString());
					}else{
						model.setFromDate(dateList.get(0).toString());
					}
					model.setToDate(dateList.get(day-1).toString());
				}
			}
			if(!radioCode.equals("true")){
				model.setInstrCode(request.getParameter("instrCode"));
			}
			model.setFromDate(model.getFromDate()+" 00:00:00");
			model.setToDate(model.getToDate()+" 00:00:00");
			HttpSession session = request.getSession();
			session.setAttribute("resultModel", model);
		}
		if(request.getSession().getAttribute("resultModel")!=null){
			model = (TradeListRequestModel)request.getSession().getAttribute("resultModel");
		}
		model.setPageNo(pageNo);
		model = validateRequest(model);
		TradeListResponseModel responseModel = facade.enquireTradeList(model);
		if(responseModel.getResponse().getTradeListInfos().size()>0){
			responseModel = validateResponse(responseModel);
		}
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping, request, response);
		return vp.processOrderTradeList(processBean);
	}
	
	private TradeListRequestModel validateRequest(TradeListRequestModel model){
		String instrCode = model.getInstrCode();
		if(StringUtils.isBlank(instrCode)){
			model.setInstrCode(null);
		}else{
			instrCode = StringUtils.stripStart(instrCode, "0");
			model.setInstrCode(instrCode);
		}
		model.setOrderSide(StringUtils.isBlank(model.getOrderSide())?null:model.getOrderSide());
		model.setFromIdx(StringUtils.isBlank(model.getFromIdx())?null:model.getFromIdx());
		model.setToIdx(StringUtils.isBlank(model.getToIdx())?null:model.getToIdx());
		model.setChannel(StringUtils.isBlank(model.getChannel())?null:model.getChannel());
		return model;
	}
	
	private TradeListResponseModel validateResponse(TradeListResponseModel response){
		List tradeInfoList = new ArrayList();
		for (Iterator iterator = response.getResponse().getTradeListInfos().iterator(); iterator.hasNext();) {
			TradeListInfo info = (TradeListInfo) iterator.next();
			//info.setAmount(new BigDecimal(info.getExecutedPrice().longValue()*info.getExecutedQty().longValue()));
			if (tradeInfoList.contains(info) && info.getMcsOrderId()!=null && info.getMcsOrderId()>0) {
				((TradeListInfo)tradeInfoList.get(tradeInfoList.indexOf(info))).setRemark(Consts.Global.Flag.RE_FLAG);
				info.setRemark(Consts.Global.Flag.RE_FLAG);
			}
			info.setInstrCode(FormatUtil.formatInstrCode(info.getInstrCode()));
			info.setInstrName(info.getInstrName().trim());
			tradeInfoList.add(info);
		}
		//Collections.sort(tradeInfoList, new TradeDateComparator());
		TradeListResponse tResponse = new TradeListResponse();
		tResponse.setTradeListInfos(tradeInfoList);
		response.setResponse(tResponse);
		return response;
	}
}
