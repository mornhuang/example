package com.itsz.sht.struts.action;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.common.CLVSplitUtil;
import com.itsz.sht.common.DateHelper;
import com.itsz.sht.common.SessionUtil;
import com.itsz.sht.common.beanutil.BeanCopyUtils;
import com.itsz.sht.common.model.common.LoginUserInfo;
import com.itsz.sht.common.model.request.FundDepositRequestModel;
import com.itsz.sht.common.model.response.FundDepositResponseModel;
import com.itsz.sht.struts.form.CheckFundDepositForm;
import com.itsz.sht.struts.form.ITSZForm;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;

public class CheckFundDepositAction extends ITSZAction{

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		CheckFundDepositForm depositForm=(CheckFundDepositForm)form;
		HttpSession session=request.getSession();
		
		try {
			depositForm.setDepositMeansA(new String(depositForm.getDepositMeansA().getBytes("ISO-8859-1"),"utf-8"));
			depositForm.setDepositMeansB(new String(depositForm.getDepositMeansB().getBytes("ISO-8859-1"),"utf-8"));
			FundDepositRequestModel fundDepositRequestModel=new FundDepositRequestModel();
			form2Model(request, depositForm, fundDepositRequestModel, response,session);
			FundDepositResponseModel fundDepositResponseModel=facade.fundDeposit(fundDepositRequestModel);
			ProcessBean processBean = new ProcessBean(fundDepositResponseModel, null, mapping, request, response);
			return vp.processCheckFundDeposit(processBean);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return null;
	}

	public static void form2Model(HttpServletRequest request,
			ITSZForm origForm, FundDepositRequestModel destModel, HttpServletResponse response,HttpSession session) {
		try {
			BeanCopyUtils.copyProperties(destModel, origForm);
			if(StringUtils.isBlank(origForm.getCLV())) origForm.setCLV(CLVSplitUtil.transNullCLV(request,response,origForm,""));
		} catch (Exception e) {
//			e.printStackTrace();
		}finally{
			destModel.setChannelType(CLVSplitUtil.getChannelType(origForm.getCLV()));
			destModel.setLanguage(CLVSplitUtil.getLanguage(origForm.getCLV()));
			destModel.setVersionId(String.valueOf(CLVSplitUtil.getVersionID(origForm.getCLV())));
			destModel.setChannelId(SessionUtil.getChannelID(request));
			destModel.setAccountName(((LoginUserInfo)session.getAttribute("loginUserInfo")).getCustName());
			CheckFundDepositForm cform=(CheckFundDepositForm)origForm;
			destModel.setAccountNo(cform.getAccountNumber());
			destModel.setAmount(new BigDecimal(cform.getAmountN()));
			destModel.setBank(cform.getDepositMeansB());
			destModel.setBankAcc(cform.getBankNumber());
			destModel.setCurrency(cform.getAmount());
			String dateTime=cform.getDepositDate()+" "+cform.getDepositsTimeH()+":"+cform.getDepositsTimeM();
			destModel.setDepositDate(DateHelper.formatDate8(dateTime));
			destModel.setDepositTime(cform.getDepositsTimeH()+":"+cform.getDepositsTimeM());
			destModel.setReceiveDate(new Date());
			destModel.setReference(cform.getReceiptNo());
			destModel.setSource(cform.getDepositMeansA());
			
		}
	}
}
