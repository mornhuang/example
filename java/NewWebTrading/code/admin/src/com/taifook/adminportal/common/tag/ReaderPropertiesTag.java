package com.taifook.adminportal.common.tag;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ReaderPropertiesTag extends TagSupport {

	private String var = null;

	private String path = null;

	private String scope = "page";

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return this.path;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getVar() {
		return this.var;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScope() {
		return this.scope;
	}

	public int doStartTag() throws JspException {
		InputStream is = this.pageContext.getServletContext()
				.getResourceAsStream(this.path + ".properties");
		try {
			if (is != null) {
				Properties pros = new Properties();
				pros.load(is);
				if (scope.equals("page")) {
					this.pageContext.setAttribute(var, pros);
				} else if (scope.equals("request")) {
					this.pageContext.getRequest().setAttribute(var, pros);
				} else if (scope.equals("session")) {
					this.pageContext.getSession().setAttribute(var, pros);
				} else if (scope.equals("application")) {
					this.pageContext.getServletContext().setAttribute(
							var,
							pros);
				} else {
					throw new JspException();
				}
			}

		} catch (Exception e) {
			throw new JspException(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new JspException(e);
				}
			}
		}
		return this.SKIP_BODY;
	}

	public int doAfterBody() throws JspException {
		return this.SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return this.EVAL_PAGE;
	}

	public void release() {
		var = null;
		path = null;
	}

}
