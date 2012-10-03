<%--
 * Copyright (c) 2003, 2004 Sun Microsystems, Inc.  All rights reserved.  U.S. 
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
 * Copyright (c) 2003, 2004 Sun Microsystems, Inc. Tous droits reserves.
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
<%@ page errorPage="errorpage.jsp" %>


<f:view>
<f:loadBundle basename="com.sun.bookstore.messages.BookstoreMessages" var="bundle"/>
<h:form id="bookcashier" >

<p>
  <h:outputText value="#{bundle.Amount}" />
  <strong>
  <h:outputText value="#{cart.total}">
    <f:convertNumber type="currency"/>
  </h:outputText>
  </strong>
</p>

<p>
  <h:outputText value="#{bundle.Purchase}"/>
</p>

<h:panelGrid      columns="3"
              headerClass="list-header"
               rowClasses="list-row-even, list-row-odd"
               styleClass="list-background"
	          summary="#{bundle.CustomerInfo}"
                    title="#{bundle.Checkout}">

    <%-- Panel header --%>

    <f:facet         name="header">
      <h:outputText value="#{bundle.Checkout}"/>
    </f:facet>

    <%-- Customer name --%>

    <h:outputText value="#{bundle.Name}" />

    <h:inputText       id="name"
                     size="50"
                    value="#{cashier.name}"
                 required="true"
		 requiredMessage="#{customMessages.CustomerName}" > 
        <f:valueChangeListener
                     type="com.sun.bookstore6.listeners.NameChanged" />
    </h:inputText>

    <h:message styleClass="error-message"  for="name"/>

    <%-- Credit card number --%>

    <h:outputText   value="#{bundle.CCNumber}"/>

    <h:inputText       id="ccno"
                     size="19"
		converterMessage="#{customMessages.CreditMessage}"
                 required="true"
		 requiredMessage="#{customMessages.ReqMessage}" >
     <f:converter binding="#{cashier.creditCard}"/>
      <bookstore:formatValidator 
           formatPatterns="9999999999999999|9999 9999 9999 9999|9999-9999-9999-9999"/>
    </h:inputText>

    <h:message styleClass="error-message"  for="ccno"/>  

    <%-- Shipping option --%>

    <h:outputText   value="#{bundle.Shipping}"/>

    <h:selectOneMenu   id="shippingOption"
                 required="true"
                    value="#{cashier.shippingOption}">
      <f:selectItem
                itemValue="2"
                itemLabel="#{bundle.QuickShip}"/>
      <f:selectItem
                itemValue="5"
                itemLabel="#{bundle.NormalShip}"/>
      <f:selectItem
                itemValue="7"
                itemLabel="#{bundle.SaverShip}"/>                    
    </h:selectOneMenu>

    <h:message styleClass="error-message"  for="shippingOption"/>

    <%-- Newsletter subscriptions --%>

    <h:outputText   value="#{bundle.Newsletters}"/>

    <h:selectManyCheckbox
                       id="newsletters"
                   layout="pageDirection"
                    value="#{cashier.newsletters}">
      <f:selectItems
                    value="#{newsletters}"/>
    </h:selectManyCheckbox>

    <h:message styleClass="error-message" for="newsletters"/>



    <h:message styleClass="error-message" for="fanClub" />



</h:panelGrid>
<h:panelGrid columns="2" columnClasses="special-offer, list-column-left" summary="#{bundle.DukeFanClub}" >
<h:selectBooleanCheckbox 
                    id="fanClub"
                    rendered="false"
                    binding="#{cashier.specialOffer}" />
<h:outputLabel for="fanClub" 
		   rendered="false"
		   binding="#{cashier.specialOfferText}"  >    
	<h:outputText id="fanClubLabel"
			  value="#{bundle.DukeFanClub}"
                  />
</h:outputLabel>
</h:panelGrid>
<h:panelGrid columns="1" columnClasses="padding: 10px;" summary="#{bundle.ThanksMsg}">
<h:outputText
    id="thanksMsg"
    rendered="false"
    binding="#{cashier.thankYou}"
    style="font-family: sans-serif; font-size: 12pt; padding: 10px; width: 100%; text-align: right;"
    value="#{bundle.ThanksMsg}" />

<h:commandButton    value="#{bundle.Submit}"
                   action="#{cashier.submit}">
	<f:setPropertyActionListener target="#{cashier.stringProperty}" value=""/>
</h:commandButton>
</h:panelGrid>

</h:form>
</f:view>
