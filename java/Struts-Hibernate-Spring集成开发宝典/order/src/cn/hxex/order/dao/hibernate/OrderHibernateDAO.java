package cn.hxex.order.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.hxex.order.bo.Order;
import cn.hxex.order.dao.IOrderDAO;

/**
 * 订单DAO的Hibernate实现，包含了数据处理逻辑
 * 
 * @author galaxy
 *
 */
public class OrderHibernateDAO
	extends HibernateDaoSupport
	implements IOrderDAO {

	/**
	 * 通过ID来得到订单的信息
	 *
	 * @param id 订单的ID
	 * @return Order 订单信息
	 */
	public Order findOrderById(final int id) {

		Order order =
			(Order) getHibernateTemplate().load(Order.class, new Integer(id));
			
		return order;

	}

	/**
	 * 通过下订单的用户得到订单的信息
	 *
	 * @param placedBy 下订单的用户名
	 * @return List 订单信息列表
	 */
	public List findOrdersPlaceByUser(final String placedBy) {

		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {

				StringBuffer sb = new StringBuffer(100);
				sb.append("select distinct order ");
				sb.append("from Order order ");
				sb.append("join order.lineItems lineItems ");
				sb.append("where order.placedBy = :placedBy");

				sb.append("order by order.id");

				Query query = session.createQuery(sb.toString());
				query.setString("placedBy", placedBy);

				List list = query.list();

				return list;

			}
		});
	}

	/**
	 * 保存一个订单信息
	 *
	 * @param order 订单信息
	 */
	public Order saveOrder(Order order) {

		getHibernateTemplate().save(order);

		return order;
	}

}