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



<p><b><fmt:message key="What"/></b></p


<sql:setDataSource dataSource="jdbc/BookDB"/>



<sql:query var="books" sql="select * from web_bookstore_books where bookId = '203'" >
</sql:query>



<c:url var="url" value="/books/bookdetails" />
<blockquote><p><em><a href="${pageContext.request.contextPath}/books/bookdetails?bookId=203">

  <c:forEach var="book" begin="0" items="${books.rows}">

    ${book.title}</a></em>,

  </c:forEach>

 

<fmt:message key="Talk"/></blockquote>

<p> 

<c:url var="url" value="/books/bookcatalog" />

<p><b><a href="${url}?Add="><fmt:message key="Start"/></a></b>




