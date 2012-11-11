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
 *
--%>


<center>
<f:loadBundle basename="com.sun.cb.jsf.messages.CBMessages" var="CBMessages"/>
<f:view>
    <h:form id="checkoutAck" >
        <center>
            <h:outputText value="#{CBMessages.OrderConfirmed}"/>
            
            <h:dataTable  id="checkOut"
                          columnClasses="list-column-center,list-column-center"
                          headerClass="list-header"
                          rowClasses="list-row"
                          styleClass="list-background-grid"
                          value="#{checkoutFormBean.orderConfirmations.items}"
                          var="oc">
                
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="#{CBMessages.ShipDate}"/>
                    </f:facet>
                    <h:outputText id="coffeeName"
                                  value="#{oc.confirmationBean.shippingDate}">
                        <f:converter converterId="XMLGregorianCalendarConverter" />
                        <!--f:convertDateTime type="date" dateStyle="full" /-->
                    </h:outputText>
                </h:column>
                
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="#{CBMessages.Coffee}"/>
                    </f:facet>
                    <h:dataTable id="lineItemCoffee"
                                 value="#{oc.orderBean.lineItems}"
                                 var="lineItem">
                        <h:column>
                            <h:outputText value="#{lineItem.coffeeName}" />
                        </h:column>
                    </h:dataTable>
                </h:column>
                
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="#{CBMessages.Pounds}"/>
                    </f:facet>
                    <h:dataTable id="lineItemPounds"
                                 value="#{oc.orderBean.lineItems}"
                                 var="lineItem">
                        <h:column>
                            <h:outputText value="#{lineItem.pounds}" />
                        </h:column>
                    </h:dataTable>
                </h:column>
            </h:dataTable>
            
            <h:commandLink action="#{CoffeeBreakBean.continueShopping}">
                <h:outputText value="#{CBMessages.ContinueShopping}"/>
            </h:commandLink>
        </center>
    </h:form>
</f:view>

