/**
 * 
 */
package com.taifook.adminportal.servlet;

import javax.servlet.ServletException;

import org.apache.struts.action.ActionServlet;

import com.itsz.sht.common.Consts;
import com.itsz.sht.common.ServiceLocator;
import com.itsz.sht.service.Facade;

/**
 * @author cyzeng 2011-04-15
 *
 */
@SuppressWarnings("serial")
public class AdminActionServlet extends ActionServlet {

	/**
	 * 
	 */
	public AdminActionServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		Facade facade = (Facade) ServiceLocator.getInstance().getService("facade");
		getServletContext().setAttribute(Consts.AdminPortal.memoDebitSystem.MEMO_DEBIT_SYSTEM, facade.getMemoDebitSytem());
		super.init();
	}
	
}
