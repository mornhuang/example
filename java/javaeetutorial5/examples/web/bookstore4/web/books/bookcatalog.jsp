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


<jsp:useBean id="cart" class="com.sun.bookstore.cart.ShoppingCart" scope="session"/>


<sql:setDataSource dataSource="jdbc/BookDB"/>


<c:if test="${!empty param.Add}">

  <c:set var="bid" value="${param.Add}"/>



  <sql:query var="books" >

    select * from web_bookstore_books where bookId = ?

    <sql:param value="${bid}" />

  </sql:query>



  <c:forEach var="bookRow" begin="0" items="${books.rowsByIndex}"> 

    <jsp:useBean id="bid"  type="java.lang.String" />

    <jsp:useBean id="bookRow"  type="java.lang.Object[]" />

    <jsp:useBean id="addedBook" class="com.sun.bookstore.database.Book" scope="page" >

      <jsp:setProperty name="addedBook" property="bookId" value="${bookRow[0]}" />

      <jsp:setProperty name="addedBook" property="surname" value="${bookRow[1]}" />

      <jsp:setProperty name="addedBook" property="firstName" value="${bookRow[2]}" />

      <jsp:setProperty name="addedBook" property="title" value="${bookRow[3]}" />

      <jsp:setProperty name="addedBook" property="price" value="${bookRow[4]}" />

      <jsp:setProperty name="addedBook" property="calendar_year" value="${bookRow[6]}" />

      <jsp:setProperty name="addedBook" property="description" value="${bookRow[7]}" />

      <jsp:setProperty name="addedBook" property="inventory" value="${bookRow[8]}" />

    </jsp:useBean>

    <% cart.add(bid, addedBook); %>

    <p><h3><font color="red" size="+2"> 

    <fmt:message key="CartAdded1"/> <em>${bookRow[3]}</em> <fmt:message key="CartAdded2"/></font></h3>

  </c:forEach>

</c:if>



<c:if test="${sessionScope.cart.numberOfItems > 0}">     

  <c:url var="url" value="/books/bookshowcart" >

    <c:param name="Clear" value="0" />

    <c:param name="Remove" value="0" />

  </c:url>

<p><strong><a href="${url}"><fmt:message key="CartCheck"/></a>&nbsp;&nbsp;&nbsp;

    <c:url var="url" value="/books/bookcashier" />

    <a href="${url}"><fmt:message key="Buy"/></a></p></strong>

</c:if>



<br>&nbsp;

<br>&nbsp;

<h3><fmt:message key="Choose"/></h3>

<center>

<table summary="layout">



<sql:query var="books">

  select * from web_bookstore_books

</sql:query> 



<c:forEach var="book" begin="0" items="${books.rows}">

    <tr>

    <c:set var="bookId" value="${book.bookId}" />

    <td bgcolor="#ffffaa"> 

        <c:url var="url" value="/books/bookdetails" >

          <c:param name="bookId" value="${bookId}" />

        </c:url>

        <a href="${url}"><strong>${book.title}&nbsp;</strong></a></td> 

    <td bgcolor="#ffffaa" rowspan=2> 

    <fmt:formatNumber value="${book.price}" type="currency"/> 

    &nbsp;</td> 



    <td bgcolor="#ffffaa" rowspan=2> 

    <c:url var="url" value="/books/bookcatalog" >

      <c:param name="Add" value="${bookId}" />

    </c:url> 

    <p><strong><a href="${url}">&nbsp;<fmt:message key="CartAdd"/>&nbsp;</a></td></tr> 



    <tr> 

    <td bgcolor="#ffffff"> 

    &nbsp;&nbsp;<fmt:message key="By"/> <em>${book.first_name}&nbsp;${book.surname}</em></td></tr>

</c:forEach>



</table>

</center>



