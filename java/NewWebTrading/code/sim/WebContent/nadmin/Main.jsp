<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/c.tld" prefix="c" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}"/>
    <title>海通国际证券集团有限公司</title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/zh_CN/style.css" />
    <script type="text/javascript" src="../Script/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="../Script/jquery.cookie.js"></script>
    <script type="text/javascript" src="../Script/jquery-ui.custom.min.js"></script>
    <script type="text/javascript" src="../Script/jquery.fmatter-min.js"></script>
    <script type="text/javascript" src="../Script/taifook.layout.js"></script>
    <script type="text/javascript" src="../Script/sht.locale-zh_CN.js"></script>
    <script type="text/javascript" src="../Script/until.js"></script>
    <script type="text/javascript" src="../Script/admin.main.js"></script>
    <script type="text/javascript" src="../Script/ajax.js"></script>
    <script type="text/javascript" src="../Script/shieldingMouse.js"></script>
    <script type="text/javascript">
    	LOCALE = "zh_CN";
    	var ContextPath = "${pageContext.request.contextPath}";

        function logout(){
            if(window.confirm('是否登出')){
                window.location="${pageContext.request.contextPath}/nadmin/adminLogout.do";
            }
        }
    </script>
</head>
<body class="body">
    <div class="warp">
    	
        <div id="header">
            <ul class="header-menu">
                <li class="header-menu-trading"><a href="${pageContext.request.contextPath}/nadmin/userList.do" target="centerIframe"><span>用户管理</span></a></li>
                <li class="line"></li>
                <li class="header-menu-ipo"><a href="${pageContext.request.contextPath}/nadmin/paramList.do" target="centerIframe"><span>参数设置</span></a></li>
                <li class="line"></li>
                <li class="header-menu-ipo"><a href="admin-info.jsp" target="centerIframe"><span>更改密码</span></a></li>
            </ul>

           <ul class="header-lang">
                <li class="userid" style="margin-right:10px;">${sessionScope.admin.uname}</li>
                <li><a href="javascript:logout();" class="logout">登出</a></li>
                <li><a href="javascript:;" class="en_US">Eng</a></li>
                <li><a href="javascript:;" class="zh_CN active">简</a></li>
                <li><a href="javascript:;" class="zh_TW">繁</a></li>
                <li><a href="javascript:;" class="help"></a></li>
            </ul> 
        </div>
        <div id="container">
            <div class="ui-center">
                <div class="ui-center-content">
                    <iframe src="${pageContext.request.contextPath}/nadmin/userList.do" name="centerIframe" frameborder="0" id="centerIframe">
                    </iframe>
                </div>
            </div>
      </div>
      <div id="footer">
      <span>Copyright &copy;  2011 海通国际证券有限公司.版权所有</span>
        </div>
</body>
</html>
