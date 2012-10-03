package cn.hxex.order.bo;

/**
 * 订单条目对象
 */
public class OrderLineItem {

	// 主键
	private int id;

	// 价格
	private double lineItemPrice;

	// 订单条目描述信息
	private String description;

	// 所属订单
	private Order order;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLineItemPrice() {
		return lineItemPrice;
	}

	public void setLineItemPrice(double lineItemPrice) {
		this.lineItemPrice = lineItemPrice;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	
}
