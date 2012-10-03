package com.taifook.adminportal.common.tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class OutWriterTag extends TagSupport {
	private String value;

	private String defaultValue;

	private int length = -1;

	private boolean escapeXml = true;

	private String datatype;

	private String dataformat;
	
	private boolean needEncode=false;

	public String getDataformat() {
		return dataformat;
	}

	public void setDataformat(String dataformat) {
		this.dataformat = dataformat;
	}

	public String getDatatype() {
		return datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDefaultValue() {
		return this.defaultValue;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return this.length;
	}

	public void setEscapeXml(boolean escapeXml) {
		this.escapeXml = escapeXml;
	}

	public boolean getEscapeXml() {
		return this.escapeXml;
	}	

	public boolean getNeedEncode() {
		return needEncode;
	}

	public void setNeedEncode(boolean needEncode) {
		this.needEncode = needEncode;
	}

	public int doStartTag() throws JspException {
		return this.SKIP_BODY;
	}

	public int doAfterBody() throws JspException {
		return this.SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		if (value == null) {
			this.value = this.defaultValue;
		} else {
			if(needEncode){
				try {
					this.value = URLEncoder.encode(this.value, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}else{
				this.value = this.value.trim();
			}
			if (value.length() >= this.length && this.length > -1) {
				this.value = this.value.substring(0, this.length - 1) + "...";
			}

			if (escapeXml) {
				this.value = Convert2Xml(this.value);
			}

			if (this.datatype != null && !this.datatype.equals("")) {
				String t_value = this.value;
				if (this.datatype.equalsIgnoreCase("date")) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat(
								this.dataformat);
						Date t_date = sdf.parse(this.value);
						this.value = sdf.format(t_date);
					} catch (Exception e) {
						this.value = t_value;
					}
				} else if (this.datatype.equalsIgnoreCase("url")) {
					try {
						if (this.dataformat != null
								&& !this.dataformat.trim().equalsIgnoreCase("")) {
							this.value = java.net.URLEncoder.encode(this.value,
									this.dataformat);
						} else {
							this.value = java.net.URLEncoder.encode(this.value);
						}
					} catch (Exception e) {
						this.value = t_value;
					}
				}
			}
		}

		JspWriter out = this.pageContext.getOut();
		try {
			out.write(this.value);
			out.flush();
		} catch (IOException e) {
			throw new JspException(e);
		}
		return this.SKIP_BODY;
	}

	public void release() {
		this.value = null;
		this.length = 0;
	}

	private String Convert2Xml(String value) {
		if (value != null) {
			String[] xmlChar = new String[] { "&", "<", ">", "'", "\"" };
			String[] hexChar = new String[] { "&amp;", "&lt;", "&gt;","&#039;", "&#034;" };
			for (int i = 0; i < xmlChar.length; i++) {
				value = value.replaceAll(xmlChar[i], hexChar[i]);
			}
		}
		return value;
	}

}
