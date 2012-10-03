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

<h:messages   globalOnly="true"/>
<br>

<h:form               id="form">

  <h:outputFormat
                   value="#{bundle.CartItemCount}">
    <f:param       value="#{cart.numberOfItems}"/>
  </h:outputFormat>

  <br><br>

  <h:commandLink      id="clear"
                  action="#{showcart.clear}"
               immediate="true"
                rendered="#{cart.numberOfItems > 0}">
    <h:outputText
                   value="#{bundle.ClearCart}"/>
  </h:commandLink>

  &nbsp;

  <h:commandLink      id="buy"
                  action="#{showcart.buy}"
               immediate="true"
                rendered="#{cart.numberOfItems > 0}">
    <h:outputText
                  value="#{bundle.Buy}"/>
  </h:commandLink>

  &nbsp;

  <h:commandLink      id="continue"
                  action="catalog"
               immediate="true">
    <h:outputText
                   value="#{bundle.ContinueShopping}"/>
  </h:commandLink>

  <br><br>

  <h:dataTable        id="items"
	    captionClass="list-caption"
           columnClasses="list-column-center, list-column-left,
                          list-column-right, list-column-center"
             footerClass="list-footer"
             headerClass="list-header"
              rowClasses="list-row-even, list-row-odd"
              styleClass="list-background"
	      	 summary="#{bundle.ShoppingCart}"
                   value="#{cart.items}"
                     var="item">

    <h:column headerClass="list-header-left" >
      <f:facet      name="header">
        <h:outputText
                   value="#{bundle.ItemQuantity}"/>
      </f:facet>
      <h:inputText    id="quantity"
                    size="4"
                   value="#{item.quantity}">
        <f:validateLongRange
                 minimum="1"/>
      </h:inputText>
      <h:message     for="quantity"/>
    </h:column>

    <h:column>
      <f:facet      name="header">
        <h:outputText
                   value="#{bundle.ItemTitle}"/>
      </f:facet>
      <h:commandLink
                  action="#{showcart.details}">
        <h:outputText
                   value="#{item.item.title}"/>
      </h:commandLink>
    </h:column>

    <h:column>
      <f:facet      name="header">
        <h:outputText
                   value="#{bundle.ItemPrice}"/>
      </f:facet>
      <h:outputText
                   value="#{item.item.price}">
        <f:convertNumber
                    type="currency"/>
      </h:outputText>
    </h:column>

    <h:column>
      <h:commandButton
                      id="remove"
                  action="#{showcart.remove}"
                   value="#{bundle.RemoveItem}"/>
    </h:column>

    <f:facet        name="footer">
      <h:panelGroup>
        <h:outputText
                   value="#{bundle.Subtotal}"/>
        <h:outputText
                   value="#{cart.total}">
          <f:convertNumber
                    type="currency"/>
        </h:outputText>
      </h:panelGroup>
    </f:facet>
    <f:facet name="caption">
    	<h:outputText value="#{bundle.Caption}"/>
   </f:facet>
  </h:dataTable>

  <%-- immediate="false" to force validation before updating --%>
  <h:commandLink      id="update"
                  action="#{showcart.update}"
               immediate="false">
    <h:outputText  value="#{bundle.UpdateQuantities}"/>
  </h:commandLink>

</h:form>

</f:view>
