package cn.hxex.order.service.spring;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.hxex.order.bo.Order;
import cn.hxex.order.bo.OrderLineItem;
import cn.hxex.order.dao.IOrderDAO;
import cn.hxex.order.exception.OrderException;
import cn.hxex.order.exception.OrderMinimumAmountException;
import cn.hxex.order.service.IOrderService;

/**
 * 业务逻辑接口的实现类
 * 
 * @author galaxy
 * 
 */
public class OrderServiceSpringImpl implements IOrderService {

	private static final double ORDER_MINIMUM = 100.0;

	private IOrderDAO orderDAO;

	/**
	 * 保存新的订单信息
	 */
	public Order saveNewOrder(Order order) throws OrderException,
			OrderMinimumAmountException {

		// do some business logic
		if (order != null && order.getTotal() == 0) {

			double total = 0.0;

			Set items = order.getOrderLineItems();
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				OrderLineItem item = (OrderLineItem) iter.next();
				total += item.getLineItemPrice();
			}

			if (total < OrderServiceSpringImpl.ORDER_MINIMUM) {
				throw new OrderMinimumAmountException(
						"Order did not exceed the order minimum");
			} else {
				order.setTotal(total);
			}
		}

		Order savedOrder = null;
		try {
			savedOrder = getOrderDAO().saveOrder(order);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw new OrderException("Could not save order " + e.toString());
		}

		return savedOrder;
	}

	/**
	 * 通过订单用户来查找订单的信息
	 * 
	 * @param user
	 *            下订单的用户名
	 * @return Order 订单的信息
	 */
	public List findOrderByUser(String user) throws OrderException {

		List orders = null;
		try {
			orders = getOrderDAO().findOrdersPlaceByUser(user);
		} catch (RuntimeException e) {
			// should really use a logger instead of System.out
			System.out.println("Could not locate order by user "
					+ e.getMessage());
			throw new OrderException("Could not locate order by user "
					+ e.getMessage());
		}
		return orders;
	}

	/**
	 * 通过订单ID来取得订单的信息
	 * 
	 * @param id
	 * @return Order
	 */
	public Order findOrderById(int id) throws OrderException {

		Order order = null;
		try {
			order = getOrderDAO().findOrderById(id);
		} catch (RuntimeException e) {
			// should really use a logger instead of System.out
			System.out
					.println("Could not locate order by ID " + e.getMessage());
			throw new OrderException("Could not locate order by ID "
					+ e.getMessage());
		}
		return order;
	}

	/**
	 * @return
	 */
	public IOrderDAO getOrderDAO() {
		return orderDAO;
	}

	/**
	 * 设置IOrderDAO接口的实例
	 * 
	 * @param orderDAO
	 */
	public void setOrderDAO(IOrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

}
