package com.itsz.sht.struts.action;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.Constants;
import com.itsz.sht.common.Consts;
import com.itsz.sht.common.DataModelUtil;
import com.itsz.sht.common.DateHelper;
import com.itsz.sht.common.FormatUtil;
import com.itsz.sht.common.model.common.TradeDetailEnquiryResult;
import com.itsz.sht.common.model.common.TradeListResult;
import com.itsz.sht.common.model.request.TradeListRequestModel;
import com.itsz.sht.common.model.response.TradeListResponseModel;
import com.itsz.sht.common.user.AcEnquiryInfo;
import com.itsz.sht.common.user.UserObject;
import com.itsz.sht.struts.form.TradeListForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mcs.core.beans.msg.TradeListInfo;

/**
 * $Id: ExportTradeListAction.java,v 1.1 2010/12/01 06:33:14 kyzou Exp $
 * @Project:portal
 * @File:ExportTradeListAction.java
 * @Description:
 * @Author:kyzou
 * @Date:2009-7-23
 */
public class ExportTradeListAction extends ITSZAction {
	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		TradeListRequestModel model = new TradeListRequestModel();
		TradeListResponseModel responseModel = new TradeListResponseModel();
		String[] accountIds = StringUtils.split(request.getParameter("accountId"), Consts.Global.Common.FORM_PARAM_SEPARATOR_CHAR);
		DataModelUtil.form2Model(request,(TradeListForm)form, model, response);
		UserObject user = (UserObject) request.getSession().getAttribute(Constants.USER);
		if (user != null) {
			model.setLoginId(user.getLoginName());
			if(StringUtils.isBlank(model.getAccountId())){
				model.setAccountId(user.getTradeInfoObject().getTradingAccount());
			}
		}
		model = validateRequest(model);
		responseModel = facade.enquireTradeList(model);
		TradeListResult tradeListResult = splitResponse(responseModel,accountIds,user);		
		request.setAttribute("tradeListResult", tradeListResult);
		request.setAttribute("export_date", DateHelper.formatDate1(new Date()));
		ProcessBean processBean = new ProcessBean(responseModel, null,mapping, request,response);
		return vp.processExportTradeList(processBean);
	}
	
	private TradeListResult splitResponse(TradeListResponseModel response,String[] accountIds,UserObject user){
		TradeListResult tradeListResult = new TradeListResult();
		List resultList = new ArrayList();
		for (int i = 0; i < accountIds.length; i++) {
			TradeDetailEnquiryResult tradeDetailResult = new TradeDetailEnquiryResult();
			tradeDetailResult.setAccountId(accountIds[i]);
			tradeDetailResult.setAccountType(getAccountType(accountIds[i],user));
			tradeDetailResult.setTotalEntry(0);
			List tradeInfoList = new ArrayList();
			for (Iterator iterator = response.getResponse().getTradeListInfos().iterator(); iterator.hasNext();) {
				TradeListInfo info = (TradeListInfo) iterator.next();
				info.setAmount(new BigDecimal(info.getExecutedPrice().floatValue()*info.getExecutedQty().floatValue()));
				info.setBusinessDate((StringUtils.substringBefore(info.getBusinessDate(), " ")).trim());				
				if(accountIds[i].equals(info.getAccountId())){
					if (tradeInfoList.contains(info) && info.getMcsOrderId()!=null && info.getMcsOrderId()>0) {
						((TradeListInfo)tradeInfoList.get(tradeInfoList.indexOf(info))).setRemark(Consts.Global.Flag.RE_FLAG);
						info.setRemark(Consts.Global.Flag.RE_FLAG);
					}
					info.setInstrCode(FormatUtil.formatInstrCode(info.getInstrCode()));
					info.setInstrName(info.getInstrName().trim());
					tradeInfoList.add(info);
				}
			}
			//Collections.sort(tradeInfoList, new TradeDateComparator());
			tradeDetailResult.setTradeInfoList(tradeInfoList);
			if(tradeDetailResult.getTradeInfoList()!=null && tradeDetailResult.getTradeInfoList().size()>0){
				tradeDetailResult.setTotalEntry(tradeDetailResult.getTradeInfoList().size());
			}
			resultList.add(tradeDetailResult);
		}
		tradeListResult.setTotalAccount(accountIds.length);
		tradeListResult.setTradeDetailEnquiryResults(resultList);
		return tradeListResult;
	}
	
	private String getAccountType(String accountId,UserObject user){
		if (user != null) {
			Collection acEnquiryCol = user.getTradeInfoObject().getAcEnquiryList(null);
			Iterator c=acEnquiryCol.iterator();
			while(c.hasNext()){
			    AcEnquiryInfo acEnquiry = (AcEnquiryInfo)c.next();
				if(accountId.equals(acEnquiry.getAccountId())){
					return acEnquiry.getAccountType();
				}
			}
		}
		return accountId;
	}
	
	private TradeListRequestModel validateRequest(TradeListRequestModel model){
		model.setInstrCode((StringUtils.isBlank(model.getInstrCode()) || "NULL".equalsIgnoreCase(model.getInstrCode()))?null:StringUtils.stripStart(model.getInstrCode(), "0"));
		model.setOrderSide((StringUtils.isBlank(model.getOrderSide()) || "NULL".equalsIgnoreCase(model.getOrderSide()))?null:model.getOrderSide());
		model.setFromIdx((StringUtils.isBlank(model.getFromIdx()) || "NULL".equalsIgnoreCase(model.getFromIdx()))?null:model.getFromIdx());
		model.setToIdx((StringUtils.isBlank(model.getToIdx()) || "NULL".equalsIgnoreCase(model.getToIdx()))?null:model.getToIdx());
		model.setChannel((StringUtils.isBlank(model.getChannel()) || "NULL".equalsIgnoreCase(model.getChannel()))?null:model.getChannel());
		return model;
	}
	
	/**
	 * 
	 */
//	public ActionForward processException(
//		ViewProvider vp,
//		ActionMapping mapping,
//		ITSZException exceptionBean,
//		HttpServletRequest request,
//		HttpServletResponse response){
//			
//			ITSZException ex = new ITSZException();
//			ex.setErrorCode(Consts.Error.Code.SESSION_OVERTIME);
//			request.setAttribute(Consts.Error.Code.ITSZ_EXCEPTION, ex);
//			return mapping.findForward("failure");
//	}
}
