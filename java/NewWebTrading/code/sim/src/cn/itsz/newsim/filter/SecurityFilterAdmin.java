package cn.itsz.newsim.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itsz.newsim.common.Constants;

public class SecurityFilterAdmin extends SecurityFilterBase {
	
	private static final String[] TYPE_ONE = {"login.", "Login", "Logout"};

	@Override
	protected void doMyFilter(ServletRequest req, ServletResponse res,
			FilterChain chain, String uri) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		if (!authorize(request, Constants.AttributeKey.Session.ADMIN) && !isContain(uri, TYPE_ONE)) {
			request.getRequestDispatcher("/nadmin/login.jsp").forward(req, res);
		} else {
			chain.doFilter(request, response);
		}
	}

}
