package com.amaker.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.amaker.dao.OrderDao;
import com.amaker.entity.Order;
import com.amaker.entity.OrderDetail;
import com.amaker.util.DBUtil;
/**
 * @author 郭宏志
 * 点餐功能DAO实现类
 */
public class OrderDaoImpl implements OrderDao {

	// 保存点餐信息，放回订单ID
	public int saveOrder(Order o) {
		// 添加SQL语句
		String sql = " insert into ordertbl(orderTime,userId,tableId,personNum)values(?,?,?,?) ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setString(1, o.getOrderTime());
			pstmt.setInt(2, o.getUserId());
			pstmt.setInt(3, o.getTableId());
			pstmt.setInt(4, o.getPersonNum());
			// 执行更新
			pstmt.executeUpdate();
			// 返回订单编号
			String sql2 = " select max(id) as id  from orderTbl ";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql2);
			if(rs.next()){
				int id = rs.getInt(1);
				return id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
		return 0;
	}
	// 保存点餐列表
	public void saveOrderDetail(OrderDetail od) {
		// 添加SQL语句
		String sql = " insert into orderdetailtbl(orderId,menuId,num,remark)values(?,?,?,?) ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, od.getOrderId());
			pstmt.setInt(2, od.getMenuId());
			pstmt.setInt(3, od.getNum());
			pstmt.setString(4, od.getRemark());
			// 执行更新
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
	
	// 更新桌号状态，有人
	public void updateTableStatus(int tableId) {
		// 更新SQL语句
		String sql = " update tableTbl set flag=1 where id = ? ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, tableId);
			// 执行更新
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
	// 更新桌号状态，空桌
	public void updateTableStatus2(int orderId) {
		// 更新SQL语句
		String sql = " update TableTbl set flag = 0 where id = "+
							" ( select tableId from OrderTbl where id = ?) ";
		// 数据库连接工具类
		DBUtil util = new DBUtil();
		// 获得连接
		Connection conn = util.openConnection();
		try {
			// 获得预定义语句
			PreparedStatement pstmt = conn.prepareStatement(sql);
			// 设置参数
			pstmt.setInt(1, orderId);
			// 执行更新
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.closeConn(conn);
		}
	}
}
