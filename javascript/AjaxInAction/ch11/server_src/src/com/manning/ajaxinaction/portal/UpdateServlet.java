package com.manning.ajaxinaction.portal;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateServlet extends HttpServlet {
  protected void doPost(
    HttpServletRequest request, 
    HttpServletResponse response
  )throws ServletException, IOException{
    String windowId=request.getParameter("ref");
    HttpSession session=request.getSession();
    PortalWindow window=(PortalWindow)(session.getAttribute("window_"+windowId));
    window.setXPos(getIntParam(request,"x"));
    window.setYPos(getIntParam(request,"y"));
    window.setWidth(getIntParam(request,"w"));
    window.setHeight(getIntParam(request,"h"));
    DBUtil.savePortalWindow(window);
    Writer writer=response.getWriter();
    writer.write("Save Complete");
    writer.flush();
  }

  private int getIntParam(HttpServletRequest request, String param) {
    String str=request.getParameter(param);
    int result=Integer.parseInt(str);
	return result;
  }
}
