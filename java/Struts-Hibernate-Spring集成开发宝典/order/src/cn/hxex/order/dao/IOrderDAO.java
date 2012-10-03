package cn.hxex.order.dao;

import java.util.List;

import cn.hxex.order.bo.Order;

/**
 * 操作订单的DAO接口
 * 
 * @author galaxy
 * 
 */
public interface IOrderDAO {

	/**
	 * 通过订单ID来查询订单的信息
	 * 
	 * @param id
	 *            订单的ID
	 * @return Order 订单信息
	 */
	public abstract Order findOrderById(final int id);

	/**
	 * 通过订单用户名来得到订单的信息
	 * 
	 * @param placedBy
	 *            发布订单的用户
	 * @return List 该用户的订单对象
	 */
	public abstract List findOrdersPlaceByUser(final String placedBy);

	/**
	 * 保存订单对象
	 * 
	 * @param order 订单对象
	 */
	public abstract Order saveOrder(final Order order);

}
