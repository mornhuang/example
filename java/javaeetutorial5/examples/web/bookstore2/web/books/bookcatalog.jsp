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
--%>

<jsp:useBean id="bookDB" class="com.sun.bookstore2.database.BookDB" scope="page" >

  <jsp:setProperty name="bookDB" property="database" value="${bookDBAO}" />


</jsp:useBean>



<c:if test="${!empty param.Add}">

  <c:set var="bid" value="${param.Add}"/>

  <jsp:setProperty name="bookDB" property="bookId" value="${bid}" />
  <c:set var="addedBook" value="${bookDB.book}" />

    <p><h3><font color="red" size="+2"> 

    <fmt:message key="CartAdded1"/> <em>${addedBook.title}</em> <fmt:message key="CartAdded2"/></font></h3>

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





<c:forEach var="book" begin="0" items="${bookDB.books}">

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

    &nbsp;&nbsp;<fmt:message key="By"/> <em>${book.firstName}&nbsp;${book.surname}</em></td></tr>

</c:forEach>



</table>

</center>



