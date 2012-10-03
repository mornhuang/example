<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <base href="${pageContext.request.requestURL}">
    <title><bean:message key="company.name"/></title>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/zh-hk/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery-ui.custom.min.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
</head>

<script type="text/javascript">
	function setPage(total_size,order_book_page,instrCode) {
		
		var url = "${pageContext.request.contextPath}/marginRatios.do?CLV=${sessionScope.CLV}&total_size=" + total_size + "&order_book_page=" + order_book_page
			 + "&instrCode=" + instrCode;
		window.location = url;
	}
	function pageSubmit(el,size){
		var order_book_page=$("#pageMarginSel").val();
		var url = "${pageContext.request.contextPath}/marginRatios.do?CLV=${sessionScope.CLV}&total_size=" + size + "&order_book_page="+order_book_page;
		window.location = url;
	}
</script>

<body>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="MarginRatio"/></span><b></b></span> <span class="position"><bean:message key="Transactions"/> >
            <bean:message key="MarginRatio"/></span>
    </h1>
    <div class="page-content">
        <table class="form-table ui-corner-all multi-column">
            <tr>
                <td class="title" colspan="3">
                  <bean:message key="MarginRatio"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <th>
                  <bean:message key="stockCode"/>
                </th>
                <th>
                    <bean:message key="CompanyName"/>
                </th>
                <th>
                    <bean:message key="MRatio"/>
                </th>
            </tr>
            <c:if test="${size==0}">
            	<tr class="alternating" ><td colspan="3"><bean:message key="SearchRatioFail"/></td></tr>
            </c:if>
            <c:forEach var="iter" items="${ModelList}" varStatus="st">
				<tr <c:if test="${st.index%2==0}">class="alternating"</c:if>>
                <td> ${iter.instrCode} </td>
                <td> ${iter.instrName} </td>
                <td> ${iter.marginRatio}% </td>
              </tr>
            </c:forEach>
           

  
            <tr>
                <td colspan="3">
                    <table class="table-pop-pager">
                        <tr>
                            <td>
                                  <c:if test="${ipage==1}">
							              <a href="javascript:return void;" class="first disabled"></a><a href="javascript:return void;" class="prev disabled"></a>
							        </c:if>
							        <c:if test="${ipage!=1}">
							              <a href="javascript:setPage('${size}',1,'<%=request.getAttribute("instrCode")%>');" class="first"></a>
							              <a href="javascript:setPage('${size}','${previous_page}','<%=request.getAttribute("instrCode")%>');" class="prev"></a>
							        </c:if>
							             
							        <span class="pages"><bean:message key="first"/>${ipage}<bean:message key="page"/> <bean:message key="Total"/>${totalPage}<bean:message key="page"/></span> 
							             
							         <c:if test="${ipage==totalPage}">
							              <a href="javascript:return void" class="next disabled"></a><a href="javascript:return void" class="last disabled"></a>
							         </c:if>
							         <c:if test="${ipage!=totalPage}">
							             <a href="javascript:setPage('${size}','${next_page}','<%=request.getAttribute("instrCode")%>');" class="next"></a>
							             <a href="javascript:setPage('${size}','${totalPage}','<%=request.getAttribute("instrCode")%>');" class="last"> </a>
							         </c:if>
                               
                               <select name="select" id="pageMarginSel" onChange="javascript:pageSubmit(this,'${size}');">
					             <c:forEach var="iter" begin="1" end="${totalPage}" step="1">
					             	<c:choose>
						             	<c:when test="${iter == ipage}">
						             		<option selected="selected">${iter}</option>
						             	</c:when>
						             	<c:otherwise>
											<option>${iter}</option>
						             	</c:otherwise>
					             	</c:choose>
					             </c:forEach>
           					 </select>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
        <input type="button" class="short-button" value="<bean:message key="button.common.return"/>" onclick="javascript:window.location = '${pageContext.request.contextPath}/jsp/Margin.jsp'" />
    </div>
</body>
</html>