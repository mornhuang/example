package cn.itsz.newsim.view.process;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;




public class ProcessBean {
	
	private Object object;
	private ViewProcess processor;
	private ActionMapping mapping;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ActionForm form;
	
	public ProcessBean(
		Object object,
		ViewProcess processor,
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
			ViewProcess processor,
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

	
	public ViewProcess getProcessor() {
		return processor;
	}

	public void setProcessor(ViewProcess processor) {
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
