<%@ page import="helloservice.endpoint.HelloService,helloservice.endpoint.Hello" %>
<%  
    String resp = null;
    try {
      Hello hello = new HelloService().getHelloPort();
      resp = hello.sayHello(request.getParameter("username"));

    } catch (Exception ex) {
        resp = ex.toString();
    }
%>
<h2><font color="black"><%=resp%></font></h2>














