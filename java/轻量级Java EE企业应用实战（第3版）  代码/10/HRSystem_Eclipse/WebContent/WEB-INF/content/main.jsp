<%@ page contentType="text/html; charset=gb2312" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<title>JavaEE简单工作流系统</title>
</head>
<body>
<%@include file="header.jsp"%> 
<table width=780 align="center"
	background="${pageContext.request.contextPath}/images/bodybg.jpg">
  <tr>
	<td colspan="3">请单击后面链接开始使用系统<a href="login.action">登录系统</a></td>
  </tr>
  <tr> 
	<td colspan="3"> 
	<br>
	  <p align="center"><span class="pt9">这仅仅是一个JavaEE的框架程序。应用模拟一个简单的工作流系统。系统包含两个角色，<br>
		普通员工的功能包括员工出勤打卡，员工的人事异动申请，员工查看工资单。<br>
		经理的功能包括管理部门员工，签核申请，每月工资自动结算等。</span></p>	  
	  <p align="center" class="pt9">应用模拟了简单的工作流，使用的轻量级J2EE架构。技术包括：Struts2.1,Spring2.5,Hibernate3.2<br>
	  Quartz,整个应用使用Spring提供的DAO支持操作数据库,同时利用Spring的声明式事务。<br>
	  程序中的权限检查使用Spring的AOP框架支持，也利用了Spring的任务调度抽象<br>
	  Hibernate为底层的数据库访问提供支持,作为O/R Mapping框架使用。</p>
	  <p align="center" class="pt9">本程序的源代码随程序一起发布，版权属于李刚，<a href="http://www.crazyit.org">http://www.crazyit.org</a><br>
	  任何个人可用来参考学习JavaEE架构，规范，但请勿在本程序的基础上修改，用做任何商业用途。<br>
	  本人保留依法追究相关责任的权利。转载和学习请保留此信息。
	  <br>
	</p>
	</td>
  </tr>
</table>
<%@include file="footer.jsp"%> 
</body>
</html>
