<%--
 * Copyright (c) 2004 Sun Microsystems, Inc.  All rights reserved.  U.S. 
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
 * Copyright (c) 2004 Sun Microsystems, Inc. Tous droits reserves.
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
<%@ page errorPage="errorpage.jsp" %>

<f:view>
<f:loadBundle var="bundle" basename="com.sun.bookstore.messages.BookstoreMessages"/>

<h:form>

<h2>
  <h:outputText value="#{selected.title}"/>
</h2>
<h:outputText value="#{bundle.By}"/>
<em><h:outputText value="#{selected.firstName} #{selected.surname}"/></em>
(<h:outputText value="#{selected.calendar_year}"/>)
<br /><br />

<h4>
  <h:outputText value="#{bundle.Critics}"/>
</h4>
<blockquote>
  <h:outputText value="#{selected.description}"/>
</blockquote>

<h4>
  <h:outputText value="#{bundle.ItemPrice}"/>
  <h:outputText value="#{selected.price}">
    <f:convertNumber type="currency"/>
  </h:outputText>
</h4>

<h:commandLink         id="add"
                   action="#{details.add}"
                immediate="true">
  <h:outputText     value="#{bundle.CartAdd}"/>
</h:commandLink>
&nbsp;
<h:commandLink         id="continue"
                   action="catalog"
                immediate="true">
  <h:outputText     value="#{bundle.ContinueShopping}"/>
</h:commandLink>

</h:form>
</f:view>
