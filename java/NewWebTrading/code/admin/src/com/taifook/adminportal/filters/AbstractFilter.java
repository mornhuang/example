package com.taifook.adminportal.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.Action;

public abstract class AbstractFilter extends Action implements Filter {

	protected boolean isCheck = true;

	protected ArrayList excludedPaths = null;

	protected String basePath = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		String checkStr = filterConfig.getInitParameter("check");
		excludedPaths = getExcludePaths(filterConfig);

		if (checkStr != null) {
			isCheck = (new Boolean(checkStr)).booleanValue();
		}

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		// TODO Auto-generated method stub		
		filterChain.doFilter(request, response);
	}

	public void destroy() {
		isCheck = false;
		excludedPaths = null;
	}

	// customer defined method
	private ArrayList getExcludePaths(FilterConfig filterConfig) {
		ArrayList list = new ArrayList();
		String paths = filterConfig.getInitParameter("exclude");
		StringTokenizer st = new StringTokenizer(paths, ";");
		while (st.hasMoreTokens()) {
			list.add(st.nextToken());
		}
		return list;
	}

	protected boolean isExcluded(HttpServletRequest httpRequest) {
		String requestPath = httpRequest.getRequestURL().toString();
		String contextPath = httpRequest.getContextPath();

		if (!this.excludedPaths.isEmpty()) {
			if (requestPath.endsWith("/")) {
				requestPath = requestPath
						.substring(0, requestPath.length() - 1);
			}

			if (requestPath.endsWith(contextPath)) {
				return true;
			}

			for (int index = 0; index < this.excludedPaths.size(); index++) {
				if (requestPath.endsWith((String) (this.excludedPaths
						.get(index)))) {
					return true;
				}
			}
		}
		return false;
	}

}
