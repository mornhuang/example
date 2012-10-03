package com.itsz.sht.vp.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.itsz.sht.vp.ViewProcessor;

/**
 * $Id: ProcessBean.java,v 1.1 2010/11/09 03:57:41 kyzou Exp $
 * @Project:portal.head
 * @File:ProcessBean.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-5-21
 */
public class ProcessBean {
	
	private Object object;
	private ViewProcessor processor;
	private ActionMapping mapping;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ActionForm form;
	
	public ProcessBean(
		Object object,
		ViewProcessor processor,
		ActionMapping mapping,
		HttpServletRequest request,
		HttpServletResponse response) {
			this.object = object;
			this.processor=processor;
			this.mapping = mapping;
			this.request = request;
			this.response = response;
	}
	
	public ProcessBean(
			Object object,
			ViewProcessor processor,
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
				this.object = object;
				this.processor=processor;
				this.mapping = mapping;
				this.request = request;
				this.response = response;
				this.form=form;
		}	

	public ActionMapping getMapping() {
		return mapping;
	}

	public void setMapping(ActionMapping mapping) {
		this.mapping = mapping;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	
	public ViewProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(ViewProcessor processor) {
		this.processor = processor;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ActionForm getForm() {
		return form;
	}

	public void setForm(ActionForm form) {
		this.form = form;
	}	
}
