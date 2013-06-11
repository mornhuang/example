package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.OrderDao;
import com.amaker.dao.impl.OrderDaoImpl;
import com.amaker.entity.OrderDetail;

public class OrderDetailServlet extends HttpServlet {

	
	public OrderDetailServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String orderId = request.getParameter("orderId");
		String menuId = request.getParameter("menuId");
		String num = request.getParameter("num");
		String remark = request.getParameter("remark");
		
		OrderDao dao = new OrderDaoImpl();
		
		OrderDetail od = new OrderDetail();
		
		od.setMenuId(Integer.parseInt(menuId));
		od.setOrderId(Integer.parseInt(orderId));
		od.setNum(Integer.parseInt(num));
		od.setRemark(remark);
		
		dao.saveOrderDetail(od);
		
		out.print(remark);
		
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}

	
	public void init() throws ServletException {
		
	}

}
