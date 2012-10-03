package com.itsz.sht.struts.eipo.action;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.itsz.eipo.webservice.IpoDenominationDto;
import com.itsz.sht.common.model.response.EIPOSubResponseDetailModel;
import com.itsz.sht.exception.WebActionError;
import com.itsz.sht.struts.action.ITSZAction;
import com.itsz.sht.struts.eipo.dao.EIPOMasterEntry;
import com.itsz.sht.struts.eipo.form.AddIPOSubscriptionForm;
import com.itsz.sht.struts.eipo.util.EIPOConstants;
import com.itsz.sht.vp.ViewProvider;
import com.itsz.sht.vp.common.ProcessBean;
import com.taifook.mtss.web.eipo.exception.EIPOServiceProviderException;

/**
 * @author yzhang
 *
 */

/**
 * 
 * @struts.action path="/addIPOSubscription" name="addIpoSubscriptionForm"
 *                scope="request" validate="false"
 * 
 * @struts.action-forward name="success" path="/jsp/eipo/eipo_add_detail.jsp"
 *                        redirect="false"
 * 
 * @struts.action-forward name="failure" path="/ipoMasterEnquiry.do"
 *                        redirect="false"
 */

public class AddIPOSubscriptionAction extends ITSZAction {

	@Override
	public ActionForward executeAction(ViewProvider vp, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		ActionErrors errors = new ActionErrors();
		EIPOSubResponseDetailModel responseModel = new EIPOSubResponseDetailModel();
		AddIPOSubscriptionForm subForm = (AddIPOSubscriptionForm) form;
		try {
			// 算出交易金额
			addIPOSubscriptionDetail(request, new BigDecimal(subForm
					.getAppliedShare()));
		} catch (EIPOServiceProviderException e) {
			errors.add(ActionErrors.GLOBAL_ERROR, new WebActionError(e
					.getMessageKey(), e.getArgs()));
		} catch (Exception e) {
			errors.add(ActionErrors.GLOBAL_ERROR,
					new WebActionError("MCS00600"));
		}
		ProcessBean processBean = new ProcessBean(responseModel, null, mapping,
				request, response);
		return vp.processEIPOSubDetail(processBean);
	}

	// 交易金额 = 手续费 + 面额 + 杂费
	private void addIPOSubscriptionDetail(HttpServletRequest request,
			BigDecimal appliedShare) throws Exception {
		EIPOMasterEntry ipoSubscription = (EIPOMasterEntry) request.getSession()
				.getAttribute(EIPOConstants.SESSION_IPO_SUBSCRIPTION);
		BigDecimal amountPayable = getAmountPayable(ipoSubscription,
				appliedShare); // 对应的申购面额

		if (ipoSubscription != null && amountPayable != null
				&& ipoSubscription.getHandlingCharge() != null) {
			double tradeAmount = new Double(ipoSubscription.getHandlingCharge()
					.doubleValue())
					+ amountPayable.doubleValue()
					+ appliedShare.doubleValue()
					* ipoSubscription.getOfferPrice().doubleValue()
					* ipoSubscription.getMiscFee().doubleValue();

			ipoSubscription.setTradeAmount(new BigDecimal(tradeAmount));
			ipoSubscription.setAppliedShare(appliedShare);
			ipoSubscription.setAmountPayable(amountPayable);

			request.getSession().removeAttribute(
					EIPOConstants.SESSION_IPO_SUBSCRIPTION);
			request.getSession().setAttribute(
					EIPOConstants.SESSION_IPO_SUBSCRIPTION, ipoSubscription);

		} else {
			throw new EIPOServiceProviderException(
					"addIPOSubscriptionDetail Exception.", "MCS00600", null);
		}
	}

	/**
	 * 得到对应的申购面额
	 * 
	 * @param ipoSubscription
	 * @param appliedShare
	 * @return
	 */
	private BigDecimal getAmountPayable(EIPOMasterEntry ipoSubscription,
			BigDecimal appliedShare) {
		BigDecimal amountPayable = null;
		if (null != appliedShare
				&& null != ipoSubscription.getIpoDenominationDtoList()) {
			List list = ipoSubscription.getIpoDenominationDtoList();
			for (int i = 0; i < list.size(); i++) {
				IpoDenominationDto dto = (IpoDenominationDto) list.get(i);
				if (appliedShare.equals(dto.getAppliedShare())) {
					amountPayable = dto.getAmountPayable();
					break;
				}

			}
		}

		return amountPayable;
	}

}
