package com.manning.ajaxinaction.portal;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

  public void init(FilterConfig config) 
  throws ServletException { }

  public void doFilter(
    ServletRequest request, 
    ServletResponse response,
    FilterChain filterChain) 
  throws IOException, ServletException {
    boolean accept=false;
  	HttpSession session=((HttpServletRequest)request).getSession();
  	User user=(User)(session.getAttribute("user"));
  	if (user==null){
  	  accept=login(request);	
  	}else{
  	  accept=true;	
  	}
  	if (accept){
  	  filterChain.doFilter(request,response);
  	}else{
  	  Writer writer=response.getWriter();
  	  writer.write(JSUtil.getLoginError());
  	  writer.flush();
  	  writer.close();
  	}
  }

  private boolean login(ServletRequest request){
  	String user=request.getParameter("username");
  	String password=request.getParameter("password");
    User userObj=findUser(user,password);
    if (userObj!=null){
   	  HttpSession session=((HttpServletRequest)request).getSession(true);
      session.setAttribute("user",userObj);	
    }
    return (userObj!=null);
  }

  private User findUser(String user, String password) {
    User userObj=null;
  	Connection conn=DBUtil.getConnection();
  	try{
  	  String sql="SELECT id FROM users WHERE username='"
  	  	+user+"' AND password='"+password+"'";
  	  Statement stmt=conn.createStatement();
  	  ResultSet rs=stmt.executeQuery(sql);
  	  if (rs.next()){
  	  	int id=rs.getInt("id");
  	  	userObj=new User(id,user);
  	  }
  	}catch (SQLException sqlex){
  		
  	}
  	return userObj;
  }

  public void destroy() { }

}
