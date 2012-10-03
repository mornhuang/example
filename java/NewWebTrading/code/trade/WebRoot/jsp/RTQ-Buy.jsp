<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title><bean:message key="company.name"/></title>
    <base href="${pageContext.request.requestURL}"/>
    <link rel="Stylesheet" type="text/css" href="../Style/blue/core.css" />
    <link rel="Stylesheet" type="text/css" href="../Style/blue/${sessionScope['org.apache.struts.action.LOCALE']}/style.css" />
    <script src="../Script/jquery-1.4.4.min.js" type="text/javascript"></script>
    <script src="../Script/shieldingMouse.js" type="text/javascript"></script>
    <script src="../Script/jquery.cookie.js" type="text/javascript"></script>
    <script src="../Script/taifook.layout.js" type="text/javascript"></script>
    <script src="../Script/jselect.js" type="text/javascript"></script>
    
    <script type="text/javascript">
        var bookProductId = "${productAllotment.prodId}"; 
        var existProductId = "${existProduct}";

        function checkProduct(productId){
			if(bookProductId!="" && productId==bookProductId){
		        $('input[name="selectMonth"]').each(  
		            function()   
		            {  
		            	if($(this).val()=="Next"){
		            		$(this).attr("disabled","disabled");
		            		$(this).attr("checked",false); 
						}
		        });
			}else{
		        $('input[name="selectMonth"]').each(  
		            function()   
		            {  
		            	if($(this).val()=="Next"){
		            		$(this).attr("disabled","");
		            		$(this).attr("checked",false); 
						}
			    });
			}
        }

        function validateInput(){
			if($("input[name='productId']:checked").val()==undefined){
				alert('<bean:message key="message.quote.productId"/>');
				return false;
			}
			if($("input[name='selectMonth']:checked").val()==undefined){
				alert('<bean:message key="message.quote.selectMonth"/>');
				return false;
			}
			return true;
        }
        function formSubmit(){
            if(validateInput()){                
            	$("#initPurchaseRTQForm").submit();
            }
        }
    </script>
</head>

<c:if test="${!empty error}">
<script type="text/javascript">
	alert('<bean:message key="${error}"/>');
</script>
</c:if>
<body>
<form action="../initPurchaseRTQ.do" method="post" id="initPurchaseRTQForm">
    <input type="hidden" name="CLV" id="CLV" value="${sessionScope.CLV}"/>
    <input type="hidden" name="transactionProtection" id="transactionProtection" value="${transactionProtection}"/>
    <c:if test="${existProduct!=null}">
    	<input type="hidden" name="existProductId" id="existProductId" value="${existProduct.id.prodId}"/>
    </c:if>
    <h1 class="page-title">
        <span class="shaddow"><span><bean:message key="label.quote.heading"/></span><b></b></span> <span class="position"><bean:message key="AccountServices"/>
            > <bean:message key="label.quote.heading"/></span>
    </h1>
    <div class="page-content">
        
        <table class="form-table ui-corner-all">
            <tr>
                <td class="title">
                    <bean:message key="label.quote.table"/>
                </td>
            </tr>
            <tr class="form-table-first">
                <td>
                    <table width="100%">
                        <tr>
                            <th colspan="2">
                               <bean:message key="label.quote.productName"/>
                            </th>
                            <th colspan="2">
                                <bean:message key="label.quote.project"/>
                            </th>
                            <th><bean:message key="label.quote.price"/>
                            </th>
                        </tr>
                        <c:if test="${result.productList==null}">
                          <tr>
                            <th>                               
                            </th>
                            <th>
                                <p class="form-notice">
									<strong><bean:message key="lable.quote.norecord"/></strong>
        						</p>
                            </th>
                            <th>
                            </th>
				          </tr>
			            </c:if>
                        <c:forEach var="product" items="${result.productList}">
                            <c:choose>
                              <c:when test="${product.prodId!=null}">
                                <tr class="alternating">
		                            <td>
		                                <c:if test="${productAllotment!=null && productAllotment.prodId==product.prodId}">
		                                    <input name="productId" type="radio" id="productId" value="${product.prodId}" onclick='checkProduct("${product.prodId}");'/>  
		                                	<input name="productPrice_${product.prodId}" type="hidden" id="productPrice_${product.prodId}" value="${product.priceInHkd}"/> 
		                                </c:if>
		                               	<c:if test="${productAllotment==null || productAllotment.prodId!=product.prodId}" >
		                                    <input name="productId" type="radio" id="productId" value="${product.prodId}" onclick='checkProduct("${product.prodId}");'/> 
		                                    <input name="productPrice_${product.prodId}" type="hidden" id="productPrice_${product.prodId}" value="${product.priceInHkd}"/>  
		                                </c:if>
		                                <label for="rdFlow"><bean:message key="Product_${product.prodId}"/></label>
		                            </td>
		                            <td>
		                                <a href="javascript:parent.openCommonDialog('<bean:message key="label.quote.demo"/>','<bean:message key="Product_${product.prodId}_demourl"/>');">
		                                    <bean:message key="label.quote.demo"/></a>
		                            </td>
		                            <td>
		                              <bean:message key="Product_${product.prodId}_desc"/>
		                            </td>
		                            <td>
		                                <a href="javascript:parent.openCommonDialog('<bean:message key="label.quote.detail"/>','<bean:message key="Product_${product.prodId}_detailurl"/>');"><bean:message key="label.quote.detail"/></a>
		                            </td>
		                            <td>
		                                <a href="javascript:parent.openCommonDialog('<bean:message key="label.quote.pricelist"/>','<bean:message key="lable.quote.feesinformation"/>');"><bean:message key="label.quote.pricelist"/></a>
		                            </td>
	                            </tr>
                              </c:when>
                              <c:otherwise>
                                 
                              </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </table>
                </td>
            </tr>      
        </table>

        <p class="form-notice">
            <strong><bean:message key="label.quote.notice"/></strong>
        </p>
        <table class="form-table ui-corner-all">
            <tr>
                <td class="title" colspan="2">
                    <bean:message key="label.quote.set"/>
                </td>
            </tr>
            <c:if test="${existProduct==null}">
	            <tr class="form-table-first">
	                <th rowspan="2">
	                    <bean:message key="label.quote.priceset"/>
	                </th>
	                <td>
	                    <input type="radio" name="selectMonth" id="selectMonth" value="This" 
	                    	<c:if test="${isDisablePurchase==true}">disabled="true"</c:if>
	                    	<c:if test="${isDisablePurchase==false}">checked="true"</c:if>
	                    /><label for="selectMonth">${thisMonth}</label>
	                </td>
	            </tr>
            </c:if>
            <c:if test="${existProduct!=null}">
	            <tr class="form-table-first">
	                <th rowspan="2">
	                    <bean:message key="label.quote.priceset"/>
	                </th>
	                <td>

	                </td>
	            </tr>
            </c:if>
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${productAllotment!=null}">
                            <input type="radio" name="selectMonth" id="selectMonth" value="Next"/><label for="selectMonth">${nextMonth}</label>
                        </c:when>
                        <c:otherwise>
                            <input type="radio" name="selectMonth" id="selectMonth" value="Next"/><label for="selectMonth">${nextMonth}</label>     
                        </c:otherwise>
                    </c:choose>           
                </td>
            </tr>
            <tr class="alternating">
                <th>
                   <bean:message key="label.quote.autores"/>
                </th>
                <td>
                    <c:if test="${productAllotment!=null && productAllotment.alltStatus=='RESRV_AUTO'}">
                    	<input type="checkbox" name="allowRenewal" id="allowRenewal" checked="true"/><label for="ck"><bean:message key="label.quote.selautores"/></label>
                    </c:if>
                    <c:if test="${productAllotment==null || productAllotment.alltStatus!='RESRV_AUTO'}">
                    	<input type="checkbox" name="allowRenewal" id="allowRenewal" /><label for="ck"><bean:message key="label.quote.selautores"/></label>
                    </c:if>                    
                </td>
            </tr>
        </table>
        
        <p class="form-notice">
            <strong><bean:message key="label.quote.autores_desc"/></strong>
        </p>
        <br />
        <!--  
        <h2>
        <bean:message key="label.quote.nottitle"/></h2>
        <ul class="form-number-ul">
            <li><span class="number-yellow">1</span><bean:message key="label.quote.nottitle1"/></li>
            <li><span class="number-yellow">2</span><bean:message key="label.quote.nottitle2"/></li>
            <li><span class="number-yellow">3</span><bean:message key="label.quote.nottitle3"/></li>
        </ul>
        -->
        <p>
            <c:choose>
                <c:when test="${result.productList!=null}">
                	<input name="btnNext" type="button" class="yellow-btn" id="btnNext" value="<bean:message key='button.quote.buyim'/>" onclick="formSubmit();"/>
                </c:when>
                <c:otherwise>
                	<input name="btnNext" type="button" disabled="disabled" class="yellow-btn disabled" id="btnNext" value="<bean:message key='button.quote.buyim'/>" onclick="formSubmit();"/>
                </c:otherwise>
            </c:choose>          
        </p>
    </div>
    </form>
</body>
</html>
