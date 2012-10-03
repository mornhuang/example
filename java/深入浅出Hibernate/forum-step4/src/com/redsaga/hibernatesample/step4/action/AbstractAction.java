/*
 * Created on 2005-3-12
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.redsaga.hibernatesample.step4.action;

import com.opensymphony.xwork.ActionContext;
import com.opensymphony.xwork.ActionSupport;

/**
 * @author cao
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AbstractAction extends ActionSupport{
	protected Object get(String name) {
		return ActionContext.getContext().getSession().get(name);
	}

	protected void set(String name, Object value) {
		ActionContext.getContext().getSession().put(name, value);
	}

}
