<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="org.crazyit.service.* , org.crazyit.business.*"%>
<%@ page import="javax.naming.*"%>
<%
InitialContext ctx = new InitialContext();
//通过JNDI查找获得EJB
Object stub = ctx.lookup("CatServiceBean/local");
CatService cs = (CatService)stub;
Person p = new Person(1 , "孙悟空"); 
//调用EJB的业务方法
Cat[] cats = cs.getCats(new Person(2 , "猪八戒"));
//查看调用EJB业务方法的返回结果
for(int i = 0 ; i < cats.length ; i++)
{
	out.println(cats[i].getName() + "的年龄："
		+ cats[i].getAge() + "<br/>");
}
%>
