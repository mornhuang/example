package com.taifook.mtss.web.common.ext;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public abstract class AbstractTag extends TagSupport {
	
	private static String[] xmlChar = new String[] { "&", "<", ">", "'", "\"" };
	private static  String[] codeChar = new String[] { "&amp;", "&lt;", "&gt;","&#039;", "&#034;" };
	
	protected String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int doStartTag() throws JspException {
		return TagSupport.SKIP_BODY;
	}

	public int doAfterBody() throws JspException {
		return TagSupport.SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		JspWriter out = this.pageContext.getOut();
		try {
			this.value=processTag();
			out.write(convert2Xml(this.value));
			out.flush();
		} catch (IOException e) {
			throw new JspException(e);
		}
		return TagSupport.SKIP_BODY;
	}

	public void release() {
	}
	
	protected abstract String processTag();

	protected String convert2Xml(String value) {
		if (value != null) {
			for (int i = 0; i < xmlChar.length; i++) {
				value = value.replaceAll(xmlChar[i], codeChar[i]);
			}
		}
		return value;
	}

}
