<%--
 * Copyright (c) 2002 Sun Microsystems, Inc.  All rights reserved.  U.S. 
 * Government Rights - Commercial software.  Government users are subject 
 * to the Sun Microsystems, Inc. standard license agreement and 
 * applicable provisions of the FAR and its supplements.  Use is subject 
 * to license terms.  
 * 
 * This distribution may include materials developed by third parties. 
 * Sun, Sun Microsystems, the Sun logo, Java and J2EE are trademarks 
 * or registered trademarks of Sun Microsystems, Inc. in the U.S. and 
 * other countries.  
 * 
 * Copyright (c) 2002 Sun Microsystems, Inc. Tous droits reserves.
 * 
 * Droits du gouvernement americain, utilisateurs gouvernementaux - logiciel
 * commercial. Les utilisateurs gouvernementaux sont soumis au contrat de 
 * licence standard de Sun Microsystems, Inc., ainsi qu'aux dispositions 
 * en vigueur de la FAR (Federal Acquisition Regulations) et des 
 * supplements a celles-ci.  Distribue par des licences qui en 
 * restreignent l'utilisation.
 * 
 * Cette distribution peut comprendre des composants developpes par des 
 * tierces parties. Sun, Sun Microsystems, le logo Sun, Java et J2EE 
 * sont des marques de fabrique ou des marques deposees de Sun 
 * Microsystems, Inc. aux Etats-Unis et dans d'autres pays.
 * '
--%>

<sql:setDataSource dataSource="jdbc/BookDB"/>

<c:set var="sufficientInventory" value="true" />

<sql:transaction>


  <c:forEach var="item" items="${sessionScope.cart.items}">

    <c:set var="book" value="${item.item}" />

    <c:set var="bookId" value="${book.bookId}" />


      <sql:query var="books" sql="select * from web_bookstore_books where bookId = ?" >

    		<sql:param value="${bookId}" />

  		</sql:query>

      

   		<c:forEach var="book" begin="0" items="${books.rows}">

        <c:set var="inventory" value="${book.inventory}" />

        
        <c:if test="${item.quantity > inventory}">

          <c:set var="sufficientInventory" value="false" />

          <h3><font color="red" size="+2"><fmt:message key="OrderError"/> <i>${bookRow[3]}</i></font></h3>

        </c:if>

 			</c:forEach>

  </c:forEach>



  <c:if test="${sufficientInventory == 'true'}" >

    <c:forEach var="item" items="${sessionScope.cart.items}">

      <c:set var="book" value="${item.item}" />

      <c:set var="bookId" value="${book.bookId}" />



      <sql:query var="books" sql="select * from web_bookstore_books where bookId = ?" >

        <sql:param value="${bookId}" />

      </sql:query>



      <c:forEach var="book" begin="0" items="${books.rows}">	          

        <sql:update var="books" sql="update web_bookstore_books set inventory = inventory - ? where bookId = ?" >

          <sql:param value="${item.quantity}" />

          <sql:param value="${bookId}" />

        </sql:update>		  

     	</c:forEach>
    </c:forEach>

  	<h3><fmt:message key="ThankYou"/> ${param.cardname}.</h3><br>  

    <jsp:useBean id="now" class="java.util.Date" />
    <jsp:setProperty name="now" property="time" value="${now.time + 432000000}" />
    <fmt:message key="ShipDate"/> <fmt:formatDate value="${now}" type="date" dateStyle="full"/>.<br><br>
  </c:if>


</sql:transaction>


<c:remove var="cart" scope="session" />
<c:url var="url" value="/books/bookstore" />
<strong><a href="${url}"><fmt:message key="ContinueShopping"/></a>&nbsp;&nbsp;&nbsp;</strong>  







