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
        <span class="shaddow"><span>编辑参数</span><b></b></span>
    </h1>
    <div class="page-content">
    <div class="register-demo">
    <c:set var="p" value="${paramReponse}" scope="request"/>
        <form action="#" id="paramForm">
        <table class="form-table ui-corner-all">
            <tr>
                <td colspan="2" class="title">
                    编辑参数
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                    参数名称
                </th>
                <td>
                   	 ${p.pname }<input type="hidden" name="pname" value="${p.pname}"/>
               </td>
            </tr>
            <tr class="alternating">
                <th>
                    参数数值
                </th>
                <td>
                    <input type="text" class="form-input" id="pvalue" name="pvalue" value="${p.pvalue }"/>
                </td>
            </tr>
            <tr>
                <th>
                  参数描述
                </th>
                <td>
                    <textarea style="width:200px;height:50px;overflow:auto;" class="form-input" id="pdesc" name="pdesc" >${p.pdesc}</textarea>
                </td>
            </tr>
                <th style="border-top:1px solid #ddd;">&nbsp;
                </th>
                <td style="border-top:1px solid #ddd;">
                    <input type="button" class="form-button" value="送出"  onclick="checkBeforeSubmitParam(false)" />
                    <input type="button" class="form-button" value="返回" onclick="location.href='${pageContext.request.contextPath}/nadmin/paramList.do'" />
                </td>
            </tr>
        </table>
        </form>
    </div>
    </div>
</body>
</html>
<script>
var ContextPath = "${pageContext.request.contextPath}";
</script>