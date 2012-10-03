package com.taifook.adminportal.common.tag;

import java.util.StringTokenizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class ContainsSplitTag extends TagSupport {

	private String target = null;

	private String compare = null;

	private String delimiter = null;

	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCompare() {
		return this.compare;
	}

	public void setCompare(String compare) {
		this.compare = compare;
	}

	public String getDelimiter() {
		return this.delimiter;
	}

	public void setDelimiter(String splitStr) {
		this.delimiter = splitStr;
	}

	public int doStartTag() throws JspException {
		if (isContains()) {
			return this.EVAL_BODY_INCLUDE;
		} else {
			return this.SKIP_BODY;
		}
	}

	public int doAfterBody() throws JspException {
		return this.SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return this.EVAL_PAGE;
	}

	public void release() {
		this.target = null;
		this.compare = null;
		this.delimiter = null;
	}

	public boolean isContains() {
		if (this.delimiter == null) {
			return this.target.indexOf(this.compare) > -1 ? true : false;
		} else {
			StringTokenizer targetToken = new StringTokenizer(this.target, this.delimiter);
			StringTokenizer compareToken = new StringTokenizer(this.compare, this.delimiter);
			String[] compareStrs = new String[compareToken.countTokens()];

			int index = 0;
			while (compareToken.hasMoreTokens()) {
				compareStrs[index++] = compareToken.nextToken().trim();
			}

			String tempStr;
			while (targetToken.hasMoreTokens()) {
				tempStr = targetToken.nextToken().trim();
				for (index = 0; index < compareStrs.length; index++) {
					String str=compareStrs[index];
					if (tempStr.equals(compareStrs[index])) {
						return true;						
					}
				}
			}
			return false;
		}
	}

}
