<%@ page language="java" contentType="text/html; charset=UTF-8"pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jcarousellite_1.0.1.js"></script>
    <script type="text/javascript" src="../Script/jquery.mousewheel.min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/jselect.js"></script>
    <script language="JavaScript">
	<!--
    
	function activeButton()
	{ 
	  var sum=0;
	  document.getElementById("btn").disabled=true;
	  for (var i=0;i<form1.elements.length;i++)
        {
         var e = form1.elements[i];
         if (e.type == "checkbox"&&e.checked == true)
			{ 
                 sum=sum+1;
			}
		}
		if(sum==4)
		{
			document.getElementById("btn").disabled=false;
		}
	}
		//-->
	</script>
	    <script type="text/javascript">
        $(function () {
            var ckList = $(".page-content input[type=checkbox]");
            ckList.click(function () {
                var allChecked = true;
                ckList.each(function () {
                    if ($(this).attr("checked") == false) {
                        return (allChecked = false);
                    }
                });
                if (allChecked)
                    $(".yellow-btn").removeClass("disabled").attr("disabled", "");
                else
                    $(".yellow-btn").addClass("disabled").attr("disabled", "disabled");
            });
        });
    </script>
</head>
<body>
   <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.ipo.heading1"/></span><b></b></span> <span class="position"><bean:message key="label.ipo.heading1"/> >
            <bean:message key="label.ipo.heading2"/></span>
    </h1>
    <form action="../webIPOQueryQty.do" method="post">
    <div class="page-content">
        <h2>
            (1)<bean:message key="label.ipo5.heading"/></h2>
        <p>
            <bean:message key="message.ipo5.heading"/></p>
        <p>
            <bean:message key="message.ipo5.dot"/></p>
        <br />
        <h2>
            (2)<bean:message key="label.ipo5.head2ing"/></h2>
        <p>
            <bean:message key="label.ipo5.title"/></p>
        <p>
            <input type="checkbox" name="checkbox" id="cb1" />
            <label for="cb1">
                <bean:message key="message.ipo5.row1"/></label></p>
        <p>
            <input type="checkbox" name="checkbox" id="cb2" />
            <label for="cb2">
                <bean:message key="message.ipo5.row2"/></label></p>
        <p>
            <input type="checkbox" name="checkbox" id="cb3" />
            <label for="cb3">
                <bean:message key="message.ipo5.row3"/></label></p>
        <p>
            <input type="checkbox" name="checkbox" id="cb4" />
            <label for="cb4">
                <bean:message key="message.ipo5.row4"/></label></p>
        <p>
            <input id="btn" TYPE="button" class="yellow-btn disabled" disabled="disabled"  onclick="javascript:window.location='eipo_add_step.jsp?CLV=${sessionScope.CLV}'" value="<bean:message key="message.ipo5.button1"/>"/>
            <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo5.button2"/>" onclick="window.location='../webIOPQueryList.do?CLV=${sessionScope.CLV}'" />
            <input type="button" class="graw-short-btn" value="<bean:message key="message.ipo5.button3"/>" onclick="javascript:window.print()" />
        </p>
    </div></form>
</BODY>
</HTML>
