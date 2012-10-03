<%--

 * Copyright (c) 2003 Sun Microsystems, Inc.  All rights reserved.  U.S. 

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

 * Copyright (c) 2003 Sun Microsystems, Inc. Tous droits reserves.

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

 *'

--%>



<%@ include file="/books/parsebooks.jsp" %>



<jsp:useBean id="cart" class="com.sun.bookstore.cart.ShoppingCart" scope="session"/>

  

<c:if test="${!empty param.Add}">

  <x:set var="book" select="$applicationScope:booklist/books/book[@id=$param:Add]" />

  <c:set var="bid" value="${param.Add}"/>

  <c:set var="title">

    <x:out select="$book/title"/>

  </c:set>

  <c:set var="surname">

    <x:out select="$book/surname"/>

  </c:set>

  <c:set var="firstName">

    <x:out select="$book/firstname"/>

  </c:set>

  <c:set var="price">

    <x:out select="$book/price"/>

  </c:set>	

  <c:set var="calendar_year">

    <x:out select="$book/calendar_year"/>

  </c:set>

  <c:set var="description">

    <x:out select="$book/description"/>

  </c:set> 

  <c:set var="inventory">

    <x:out select="$book/inventory"/>

  </c:set>

  <jsp:useBean id="addedBook" class="com.sun.bookstore.database.Book" scope="page" >

    <jsp:setProperty name="addedBook" property="bookId" value='${bid}' />

    <jsp:setProperty name="addedBook" property="surname" value='${surname}' />

    <jsp:setProperty name="addedBook" property="firstName" value='${firstname}' />

    <jsp:setProperty name="addedBook" property="title" value='${title}' />

    <jsp:setProperty name="addedBook" property="price" value='${price}' />

    <jsp:setProperty name="addedBook" property="calendar_year" value='${calendar_year}' />

    <jsp:setProperty name="addedBook" property="description" value='${description}' />

    <jsp:setProperty name="addedBook" property="inventory" value='${inventory}' />

  </jsp:useBean >

  <jsp:useBean id="bid" class="java.lang.String" scope="page" />



  <% cart.add(bid, addedBook); %>



  <p><h3><font color="red" size="+2"> 

  <fmt:message key="CartAdded1"/> <em><x:out select="$book/title"/></em> <fmt:message key="CartAdded2"/></font></h3>

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





  <x:forEach var="book" select="$applicationScope:booklist/books/*">     

    <tr>

    <c:set var="bookId">

      <x:out select="$book/@id"/>

    </c:set>



    <td bgcolor="#ffffaa"> 

        <c:url var="url" value="/books/bookdetails" >

          <c:param name="bookId" value="${bookId}" />

      		<c:param name="Clear" value="0" />

        </c:url>

        <a href="${url}"><strong><x:out select="$book/title"/>&nbsp;</strong></a></td> 

    <td bgcolor="#ffffaa" rowspan=2> 

    <c:set var="price">

      <x:out select="$book/price"/>

    </c:set><fmt:formatNumber value="${price}" type="currency"/> 

    &nbsp;</td> 



    <td bgcolor="#ffffaa" rowspan=2> 

    <c:url var="url" value="/books/bookcatalog" >

      <c:param name="Add" value="${bookId}" />

    </c:url> 

    <p><strong><a href="${url}">&nbsp;<fmt:message key="CartAdd"/>&nbsp;</a></td></tr> 



    <tr> 

    <td bgcolor="#ffffff"> 

    &nbsp;&nbsp;<fmt:message key="By"/> <em><x:out select="$book/firstname"/>&nbsp;<x:out select="$book/surname"/></em></td></tr>

  </x:forEach>



</table>

</center>



