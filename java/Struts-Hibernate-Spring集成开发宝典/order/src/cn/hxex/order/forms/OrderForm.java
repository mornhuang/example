package cn.hxex.order.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import cn.hxex.order.bo.Order;

public class OrderForm  extends ActionForm {


	private int orderId;

	private String whoPlacedOrder;

	private String itemDesc_1;
	private double itemPrice_1;

	private String itemDesc_2;
	private double itemPrice_2;

	private String itemDesc_3;
	private double itemPrice_3;

	private Order order;

	/**
	 * Override the reset method from Struts for this custom form
	 * 
	 * @param mapping
	 * @param request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		orderId = 0;

		whoPlacedOrder = null;

		itemDesc_1 = null;
		itemPrice_1 = 0.0;

		itemDesc_2 = null;
		itemPrice_2 = 0.0;

		itemDesc_3 = null;
		itemPrice_3 = 0.0;

		order = null;

	}

	/**
	 * Simple validation
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();

		String mappingName = mapping.getPath();

		if (mappingName.equalsIgnoreCase("/SaveNewOrder")) {
			if (whoPlacedOrder == null
				|| whoPlacedOrder.trim().length() == 0) {
				errors.add(
					Globals.ERROR_KEY,
					new ActionMessage("error.field.required", "Placed by"));
			}

			if (itemPrice_1 + itemPrice_2 + itemPrice_3 <= 0.0) {
				errors.add(
					Globals.ERROR_KEY,
					new ActionMessage("error.order.line.item.price"));
			}
		}

		return errors;
	}

	/**
	 * Default constructor
	 */
	public OrderForm() {
		super();
	}

	/**
	 * @return
	 */
	public String getItemDesc_1() {
		return itemDesc_1;
	}

	/**
	 * @return
	 */
	public String getItemDesc_2() {
		return itemDesc_2;
	}

	/**
	 * @return
	 */
	public String getItemDesc_3() {
		return itemDesc_3;
	}

	/**
	 * @return
	 */
	public double getItemPrice_1() {
		return itemPrice_1;
	}

	/**
	 * @return
	 */
	public double getItemPrice_2() {
		return itemPrice_2;
	}

	/**
	 * @return
	 */
	public double getItemPrice_3() {
		return itemPrice_3;
	}

	/**
	 * @return
	 */
	public Order getOrder() {
		return order;
	}

	/**
	 * @return
	 */
	public String getWhoPlacedOrder() {
		return whoPlacedOrder;
	}

	/**
	 * @param string
	 */
	public void setItemDesc_1(String string) {
		itemDesc_1 = string;
	}

	/**
	 * @param string
	 */
	public void setItemDesc_2(String string) {
		itemDesc_2 = string;
	}

	/**
	 * @param string
	 */
	public void setItemDesc_3(String string) {
		itemDesc_3 = string;
	}

	/**
	 * @param d
	 */
	public void setItemPrice_1(double d) {
		itemPrice_1 = d;
	}

	/**
	 * @param d
	 */
	public void setItemPrice_2(double d) {
		itemPrice_2 = d;
	}

	/**
	 * @param d
	 */
	public void setItemPrice_3(double d) {
		itemPrice_3 = d;
	}

	/**
	 * @param order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * @param string
	 */
	public void setWhoPlacedOrder(String string) {
		whoPlacedOrder = string;
	}

	/**
	 * @return
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @param i
	 */
	public void setOrderId(int i) {
		orderId = i;
	}

}
