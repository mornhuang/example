package cn.hxex.order.action;

import java.util.HashSet;
import java.util.Set;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cn.hxex.order.bo.Order;
import cn.hxex.order.bo.OrderLineItem;
import cn.hxex.order.forms.OrderForm;

/**
 * 保存新的订单信息到数据库中
 * 
 * @author galaxy
 */
public class SaveOrderAction extends BaseAction {

	/**
	 * Default constructor
	 */
	public SaveOrderAction() {
		super();
	}

	/**
	 * Implementation for Struts execute
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.lang.Exception {

		// Assemble the order from the form

		OrderForm oForm = (OrderForm) form;

		Order order = new Order();
		order.setUserName(oForm.getWhoPlacedOrder());

		Set lineItems = new HashSet();

		if (oForm.getItemDesc_1() != null && oForm.getItemDesc_1().length() > 0) {
			OrderLineItem item = new OrderLineItem();
			item.setDescription(oForm.getItemDesc_1());
			item.setLineItemPrice(oForm.getItemPrice_1());
			lineItems.add(item);
			item.setOrder(order);
		}

		if (oForm.getItemDesc_2() != null && oForm.getItemDesc_2().length() > 0) {
			OrderLineItem item = new OrderLineItem();
			item.setDescription(oForm.getItemDesc_2());
			item.setLineItemPrice(oForm.getItemPrice_2());
			lineItems.add(item);
			item.setOrder(order);
		}

		if (oForm.getItemDesc_3() != null && oForm.getItemDesc_3().length() > 0) {
			OrderLineItem item = new OrderLineItem();
			item.setDescription(oForm.getItemDesc_3());
			item.setLineItemPrice(oForm.getItemPrice_3());
			lineItems.add(item);
			item.setOrder(order);
		}

		order.setOrderLineItems(lineItems);

		// delegate the save to the service layer and further upstream
		getOrderService().saveNewOrder(order);

		oForm.setOrder(order);

		ActionErrors errors = new ActionErrors();
		errors.add(Globals.ERROR_KEY, new ActionMessage(
				"message.order.saved.successfully"));
		saveMessages(request, errors);

		return mapping.findForward("success");

	}
}
