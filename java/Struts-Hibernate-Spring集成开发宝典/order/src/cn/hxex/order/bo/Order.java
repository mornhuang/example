package cn.hxex.order.bo;

import java.util.HashSet;
import java.util.Set;

/**
 * 订单实体对象
 */
public class Order {

	// 主键
	private int id;

	// 订单总价钱
	private double total;

	// 发布订单的用户名
	private String userName;

	// 订单条目
	private Set orderLineItems = new HashSet();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set getOrderLineItems() {
		return orderLineItems;
	}

	public void setOrderLineItems(Set orderLineItems) {
		this.orderLineItems = orderLineItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
