package com.manning.ajaxinaction.portal;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SelectServlet extends HttpServlet {
  protected void doPost(
    HttpServletRequest request, 
	HttpServletResponse response
  ) throws ServletException, IOException {
    HttpSession session=request.getSession();
  	User user=(User)(session.getAttribute("user"));
    StringBuffer jsBuf=new StringBuffer();
  	if (user==null){
  	  jsBuf.append(JSUtil.logout());	
  	}else{
  	  List windows=DBUtil.getPortalWindows(user);
  	  jsBuf.append(JSUtil.initUI());
  	  for (Iterator iter=windows.iterator();iter.hasNext();){
  	    PortalWindow window=(PortalWindow)(iter.next());
  	    session.setAttribute("window_"+window.getId(),window);
  	  	jsBuf.append(JSUtil.initWindow(window));  
  	  }
  	}
    Writer writer=response.getWriter();
    writer.write(jsBuf.toString());
    writer.flush();
  }
}
