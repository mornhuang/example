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
    <script src="${pageContext.request.contextPath}/Script/shieldingMouse.js" type="text/javascript"></script>
</head>
<body class="body">
    <h1 class="page-title">
        <span class="shaddow"><span>用户列表</span><b></b></span>
    </h1>
    <div class="page-content">
		 <div class="register-demo">
            <table class="form-table ui-corner-all">
                <tr>
                    <td colspan="5" class="title">
                     <div style="float:left;width:200px;"> 用户列表</div>
                     <div class="south-div-filter" style="float:right;width:120px;">
                     	<form action="#" id="searchForm">
                        <span>查找</span>
                        <input type="text" class="filter" name="keyword" id="key"/>
                        <input type="text" style="display:none;"/>
                        <a href="javascript:checkBeforeSubmitSearch(false);" class="go"></a>
                        </form>
                    </div>
                    </td>
                </tr>
                <tr class="form-table-first">
                    <th style="width:20%">登入名称</th>
                    <th style="width:20%">姓名</th>
                    <th style="width:25%">手机号码</th>
                    <th style="text-align:center;width:10%">海通客户</th>
                    <th style="text-align:center;width:25%">操作</th>
                </tr>
                <c:set var="uList" value="${userResponse.userProfiles}" scope="request"/>
                <c:if test="${uList!=null}">
                <c:forEach items="${uList}" var="u" varStatus="st">
	                <tr <c:if test="${st.index%2==0}">class="alternating"</c:if> id="st${u.loginId}">
	                    <td>${u.loginId}</td>
	                    <td>${u.username}</td>
	                    <td>${u.telephone}</td>
	                    <td align="center"><c:if test="${u.client==false}">否</c:if>
	                    <c:if test="${u.client==true}">是</c:if>
	                    </td>
	                    <td align="center"><a href="javascript:;" onclick="editUser('${u.loginId}');" class="user_edit">编辑</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/nadmin/userDelete.do?loginId=${u.loginId}" onclick="return delConfirm();">删除</a></td>
	                </tr>
                </c:forEach>
                </c:if>
                <c:if test="${uList==null}">
                	<tr class="alternating">
	                    <td colspan="5" align="center">用户不存在</td>
	                </tr>
                </c:if>
                <tr>
                	<td style="border-top:1px solid #ddd;">
                		<a href="user-add.jsp">+&nbsp;新增用户</a>
                	</td>
                    <td colspan="4" style="border-top:1px solid #ddd;">
                    	当前:${userResponse.currentPage}/${userResponse.totalPage}  每页:${userResponse.pageSize}&nbsp;&nbsp;
                    	<c:if test="${1>=userResponse.currentPage}">
                    	首页 &nbsp; 上页</c:if>
                    	<c:if test="${userResponse.currentPage>1}">
                    	<a href="${pageContext.request.contextPath}/nadmin/userList.do?pageNo=1">首页</a>&nbsp;<a href="${pageContext.request.contextPath}/nadmin/userList.do?pageNo=${userResponse.prevPage}">上页</a>
                    	</c:if>
                        <c:if test="${userResponse.totalPage>userResponse.currentPage}">
                    	&nbsp;<a href="${pageContext.request.contextPath}/nadmin/userList.do?pageNo=${userResponse.nextPage}">下页</a>&nbsp;<a href="${pageContext.request.contextPath}/nadmin/userList.do?pageNo=${userResponse.totalPage}">末页</a>
                        </c:if>
                         <c:if test="${userResponse.currentPage==userResponse.totalPage}">
                         	下页 &nbsp; 末页
                         </c:if>
                        
                    </td>
                </tr>
            </table>
        </div>       
    </div>
    <div id="userEditDialog" class="trading-status-pop ui-pop-padding" title='编辑用户'>
    	<form action="#" method="post" id="registerForm">
            <table class="form-table ui-corner-all">
                <tr class="form-table-first">
                    <th>
                        登入名称
                    </th>
                    <td id="loginId">
                        
                    </td>
                </tr>
          <tr class="alternating">
			<th>登入密码</th>
			<td><input id="passWord" name="passWord" type="password" class="form-input"/></td>
		  </tr>
                <tr>
                    <th>
                        地区号码
                    </th>
                    <td>
                        <select id="addNo" name="addNo">
                            <option value="86">86</option>
                            <option value="852" selected>852</option>
                            <option value="853">853</option>
                        </select>
                        (如：中国大陆：86; 香港：852; 澳门: 853)
                    </td>
                </tr>
                <tr class="alternating">
                    <th>
                        手机号码
                    </th>
                    <td>
                        <input type="text" class="form-input" id="telephone" name="telephone"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        电邮地址
                    </th>
                    <td>
                        <input type="text" class="form-input" id="email" name="email"/>
                    </td>
                </tr>
                <tr class="alternating">
                    <th>
                        用户姓名
                    </th>
                    <td>
                        <input type="text" class="form-input" id="username" name="username"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        海通国际客户
                    </th>
                    <td>
                        <p>
                            <label for="rbYes">
                                <input type="radio" name="isClient" id="rbYes" value="true" />是</label>, 客户号码:<input type="text"
                                    class="form-input" id="clientNo" name="clientNo"/></p>
                        <p>
                            <label for="rbNo">
                                <input type="radio" name="isClient" id="rbNo" value="false"/>否</label></p>
                    </td>
                </tr>
                <tr class="alternating">
                    <th>
                    </th>
                    <td>
                        <input type="button" class="form-button" value="送出" onclick="checkBeforeSubmit(false)"/>
                        <input type="button" class="form-button" value="关闭" onclick="closeDialog('userEditDialog')" />
                    </td>
                </tr>
            </table>
            </form>
    </div>
</body>
</html>
<script>
var ContextPath = "${pageContext.request.contextPath}";
$(admin_init);
</script>