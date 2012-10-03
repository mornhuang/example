<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}"/>
    <title>海通国际证券集团有限公司</title>
    <link rel="Stylesheet" type="text/css" href="${pageContext.request.contextPath}/Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="${pageContext.request.contextPath}/Style/blue/zh_CN/style.css" />
    <script src="${pageContext.request.contextPath}/Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/taifook.layout.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/jselect.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/Script/admin.main.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/Script/shieldingMouse.js"></script>
</head>
<body class="body">
    <h1 class="page-title">
        <span class="shaddow"><span>参数列表</span><b></b></span>
    </h1>
    <div class="page-content">
		 <div class="register-demo">
            <form action="">
            <table class="form-table ui-corner-all">
                <tr>
                    <td colspan="4" class="title">
                      参数列表
                    </td>
                </tr>
                <tr class="form-table-first">
                    <th style="width:120px;">参数名称</th>
                    <th style="width:120px;">参数值</th>
                    <th style="text-align:center;width:270px;">描述</th>
                    <th style="text-align:center;width:90px;">操作</th>
                </tr>
                <c:set var="pList" value="${paramResponse.parameterModels}" scope="request"/>
                <c:if test="${pList!=null}">
                <c:forEach items="${pList}" var="v" varStatus="st">
	                <tr <c:if test="${st.index%2==0}">class="alternating"</c:if>>
	                    <td>${v.pname}</td>
	                    <td>${v.pvalue}</td>
	                    <td>${v.pdesc}</td>
	                    <td align="center">
	                    <a href="${pageContext.request.contextPath}/nadmin/paramQuery.do?pname=${v.pname }" target="centerIframe">编辑</a>
	                    &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/nadmin/paramDel.do?pname=${v.pname }" onclick="return delConfirm();">删除</a></td>
	                </tr>
                </c:forEach>
                <tr>
                	<td style="border-top:1px solid #ddd;">
                		<a href="param-add.jsp">+&nbsp;新增参数</a>
                	</td>
                    <td colspan="3" style="border-top:1px solid #ddd;">
                   		 当前:${paramResponse.currentPage}/${paramResponse.totalPage}  每页:${paramResponse.pageSize}&nbsp;&nbsp;
                    	<c:if test="${1>=paramResponse.currentPage}">
                    	首页 &nbsp; 上页</c:if>
                    	<c:if test="${paramResponse.currentPage>1}">
                    	<a href="${pageContext.request.contextPath}/nadmin/paramList.do?pageNo=1">首页</a>&nbsp;<a href="${pageContext.request.contextPath}/nadmin/paramList.do?pageNo=${paramResponse.prevPage}">上页</a>
                    	</c:if>
                        <c:if test="${paramResponse.totalPage>paramResponse.currentPage}">
                    	&nbsp;<a href="${pageContext.request.contextPath}/nadmin/paramList.do?pageNo=${paramResponse.nextPage}">下页</a>&nbsp;<a href="${pageContext.request.contextPath}/nadmin/paramList.do?pageNo=${paramResponse.totalPage}">末页</a>
                        </c:if>
                         <c:if test="${paramResponse.currentPage==paramResponse.totalPage}">
                         	下页 &nbsp; 末页
                         </c:if>
                    
                    </td>
                </tr>
                </c:if>
            </table>
            </form>
            <p style="text-align:center">系统参数请不要轻意删除, 删除前请确认此参数已废弃。 </p>
        </div>       
    </div>
</body>
</html>