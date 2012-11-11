package cn.hxex.order.service;

import java.util.List;

import cn.hxex.order.bo.Order;
import cn.hxex.order.dao.IOrderDAO;
import cn.hxex.order.exception.OrderException;
import cn.hxex.order.exception.OrderMinimumAmountException;

/**
 * 订单业务接口
 * 
 * @author galaxy
 * 
 */
public interface IOrderService {

	/**
	 * 保存订单对象
	 * 
	 * @param order
	 *            需要保存的订单对象
	 * @return 保存后的订单对象
	 * @throws OrderException
	 * @throws OrderMinimumAmountException
	 */
	public abstract Order saveNewOrder(Order order) throws OrderException,
			OrderMinimumAmountException;

	/**
	 * 通过订单用户名来查询订单信息
	 * 
	 * @param user
	 *            下订单的用户
	 * @return Order
	 */
	public abstract List findOrderByUser(String user) throws OrderException;

	/**
	 * 通过ID来查询订单信息
	 * 
	 * @param id
	 *            订单ID
	 * @return Order 订单对象
	 */
	public abstract Order findOrderById(int id) throws OrderException;

	/**
	 * 设置IOrderDAO接口对象的实例
	 * 
	 * @param orderDAO
	 *            IOrderDAO接口的实例
	 */
	public abstract void setOrderDAO(IOrderDAO orderDAO);
}
