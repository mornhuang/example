package cn.hxex.order.action;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.ActionSupport;

import cn.hxex.order.service.IOrderService;

/**
 * Action»ùÀà
 * 
 * @author galaxy
 */
public abstract class BaseAction extends ActionSupport {

	protected Object getBean( String name ) {
		WebApplicationContext ctx = getWebApplicationContext();
		return ctx.getBean( name );
	}
	
	protected IOrderService getOrderService() {
		return (IOrderService)getBean("orderService");
	}

}
