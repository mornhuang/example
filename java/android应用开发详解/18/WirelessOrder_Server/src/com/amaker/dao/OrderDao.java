package com.amaker.dao;

import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
public interface OrderDao {
	// 保存开桌信息
	public int saveOrder(Order o );
	// 保存点菜列表信息
	public void saveOrderDetail(OrderDetail od);
	// 更新桌号状态，有人
	public void updateTableStatus(int tableId);
	// 更新桌号状态，空位
	public void updateTableStatus2(int orderId);
}
