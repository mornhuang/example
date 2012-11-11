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

<p><fmt:message key="Amount"/>

<strong><fmt:formatNumber value="${sessionScope.cart.total}" type="currency"/></strong>

</strong>

<p><fmt:message key="Purchase"/>

<c:url var="url" value="/books/bookreceipt" />

<form action="${url}" method="post">

<table summary="layout">

<tr>

<td><strong><fmt:message key="Name"/></strong></td>

<td><input type="text" name="cardname" value="Gwen Canigetit" size="19"></td>

</tr>

<tr>

<td><strong><fmt:message key="CCNumber"/></strong></td>

<td><input type="text" name="cardnum" value="xxxx xxxx xxxx xxxx" size="19"></td>

</tr>

<tr>

<td></td>

<td><input type="submit" value="<fmt:message key="Submit"/>"></td>

</tr>

</table>

</form>
