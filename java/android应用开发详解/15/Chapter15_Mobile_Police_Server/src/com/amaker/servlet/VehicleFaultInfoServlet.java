package com.amaker.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amaker.dao.VehicleDao;
import com.amaker.dao.impl.VehicleFaultInfoDaoImpl;
import com.amaker.entity.VehicleFaultInfo;

/**
 * 
 * @author 郭宏志
 * 响应机动车违章信息保存的Servlet
 */
public class VehicleFaultInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// 获得请求参数
		String name = request.getParameter("name");
		String idno = request.getParameter("idno");
		String license = request.getParameter("license");
		String faultRecord = request.getParameter("faultRecord");

		String createTime = request.getParameter("createTime");
		double penalty = Double.parseDouble(request.getParameter("penalty"));
		
		// 封装到实体类中
		VehicleFaultInfo v = new VehicleFaultInfo();

		v.setName(name);
		v.setCreateTime(createTime);
		v.setIdno(idno);

		v.setFaultRecord(faultRecord);
		v.setPenalty(penalty);
		v.setLicense(license);
		// 调用DAO保存信息
		VehicleDao dao = new VehicleFaultInfoDaoImpl();
		if (dao.save(v))
			// 响应保存结果
			out.print("1");
		else
			// 响应保存结果
			out.print("0");

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void init() throws ServletException {
		
	}
	public VehicleFaultInfoServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

}
