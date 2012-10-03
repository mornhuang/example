package cn.hxex.blog.struts.ext;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.RequestProcessor;

public class BlogRequestProcessor extends RequestProcessor {

	protected void processLocale(HttpServletRequest request,
            HttpServletResponse response) {
		super.processLocale( request, response );
		
		try {
			request.setCharacterEncoding( "UTF-8" );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
