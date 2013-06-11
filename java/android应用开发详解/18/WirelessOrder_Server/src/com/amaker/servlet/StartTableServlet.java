package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.entity.Order;

/**
 * @author 郭宏志
 * 开桌Servlet，将操作员输入的信息保存到OrderTbl表中
 */
public class StartTableServlet extends HttpServlet {
	// 构造方法
	public StartTableServlet() {
		super();
	}

	// 销毁方法
	public void destroy() {
		super.destroy(); 
	}

	// 响应Get请求
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// 点餐时间
		String orderTime = request.getParameter("orderTime");
		// 操作员编号
		String userId = request.getParameter("userId");
		// 桌号
		String tableId = request.getParameter("tableId");
		// 人数
		String personNum = request.getParameter("personNum");
		// 获得DAO接口
		OrderDao dao = new OrderDaoImpl();
		// 实例化订单类
		Order o = new Order();
		// 设置订单属性
		o.setOrderTime(orderTime);
		o.setPersonNum(Integer.parseInt(personNum));
		o.setUserId(Integer.parseInt(userId));
		o.setTableId(Integer.parseInt(tableId));
		// 返回订单ID
		int id = dao.saveOrder(o);
		
		// 更新餐桌状态为 有人
		dao.updateTableStatus(Integer.parseInt(tableId));
		// 返回ID
		out.print(id);
		out.flush();
		out.close();
	}

	// 响应Post请求
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	// 初始化
	public void init() throws ServletException {
	}

}
