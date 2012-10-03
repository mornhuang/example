package cn.hxex.order.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cn.hxex.order.bo.Order;
import cn.hxex.order.forms.OrderForm;

/**
 * This class will find an order and display it.
 * 
 * @author galaxy
 *
 */
public class FindOrderAction extends BaseAction {
	/**
	 * Default constructor
	 */
	public FindOrderAction() {
		super();
	}

	/**
	 * Implementation for Struts execute
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response)
		throws java.lang.Exception {

		// Assemble the order from the form

		OrderForm oForm = (OrderForm) form;

		// delegate the save to the service layer and further upstream
		Order order = getOrderService().findOrderById(oForm.getOrderId());

		oForm.setOrder(order);

		return mapping.findForward("success");

	}
}
