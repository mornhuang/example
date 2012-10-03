package com.taifook.adminportal.common.tag;

import java.io.IOException;
import java.net.URL;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class ShowPageSplitTag extends TagSupport {

	private int currentPage;

	private int maxPage;

	private int pageSize;

	private int totalSize;

	private String url;

	private String parameters = "";

	private String currentPageName = "currentpage";

	public void setcurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getMaxPage() {
		return this.maxPage;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getTotalSize() {
		return this.getTotalSize();
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;		
	}

	public String getParameters() {
		return this.parameters;
	}

	public void setCurrentPageName(String currentPageName) {
		this.currentPageName = currentPageName;
	}

	public String getCurrentPageName() {
		return this.currentPageName;
	}

	public int doStartTag() throws JspException {
		return this.SKIP_BODY;
	}

	public int doAfterBody() throws JspException {
		return this.SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		try {
			StringBuffer sb = new StringBuffer();

			sb
					.append("<table width='100%' border='0' cellspacing='0' style='font-weight:600;color:#9A9DAF;'>");
			//
			sb.append("<tr> ");
			sb.append("<td width='34%'><div align='right'>total records:"
					+ this.totalSize + "&nbsp;&nbsp;</div></td>");
			//sb.append("<td width='10%'><div >pages:"
			//		+ this.maxPage + "&nbsp;&nbsp;</div></td>");
			//sb.append("<td width='10%'><div >"
			//		+ this.pageSize + "/page&nbsp;&nbsp;</div></td>");			
			//
			if (currentPage <= 1) {
				sb.append("<td width='8%'>[first]</td>");
			} else {
				sb.append("<td width='8%'><a href='" + url + "?"
						+ currentPageName + "=1" + "&" + parameters
						+ "'>[first]</a></td>");
			}
			//
			if (currentPage <= 1) {
				sb.append("<td width='8%'>[prev]</td>");
			} else {
				sb.append("<td width='8%'><a href='" + url + "?"
						+ currentPageName + "=" + (currentPage - 1) + "&"
						+ parameters + "'>[prev]</a></td>");
			}
			//
			if (currentPage >= maxPage) {
				sb.append("<td width='8%'>[next]</td>");
			} else {
				sb.append("<td width='8%'><a href='" + url + "?"
						+ currentPageName + "=" + (currentPage + 1) + "&"
						+ parameters + "'>[next]</a></td>");
			}
			//

			if (currentPage >= maxPage) {
				sb.append("<td width='8%'>[last]</td>");
			} else {
				sb.append("<td width='8%'><a href='" + url + "?"
						+ currentPageName + "=" + (maxPage) + "&" + parameters
						+ "'>[last]</a></td>");
			}
			//
			sb.append("<td width='34%'><font color='#FF0000'>current:</font>");
			sb
					.append("<select name='select' class=style_pages onchange=\"window.location.href='"
							+ url
							+ "?"
							+ currentPageName
							+ "=\'+this.options[this.selectedIndex].value+\'"
							+ "&" + parameters + "'\">");
			for (int i = 1; i <= this.maxPage; i++) {
				if (i == this.currentPage) {
					sb.append("<option value='" + i + "' selected>" + i
							+ "</option>");
				} else {
					sb.append("<option value='" + i + "'>" + i + "</option>");
				}
			}
			sb.append("</select>");
			sb.append("page</td></tr></table>");
			JspWriter out = this.pageContext.getOut();
			out.write(sb.toString());
			out.flush();

		} catch (IOException e) {
			throw new JspException(e);
		}
		return this.EVAL_PAGE;
	}

	public void release() {

	}
}
