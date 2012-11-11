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


<f:loadBundle basename="com.sun.cb.jsf.messages.CBMessages" var="CBMessages"/>

<f:view>
    <h:form id="OrderForm">
        
        <center>
            <h:outputText  escape="false" value="#{CBMessages.OrderInstructions}"/>
            <br>
            <h:dataTable  id="table"
                          columnClasses="list-column-center,list-column-right,
                          list-column-center, list-column-right"
                          headerClass="list-header"
                          footerClass="list-column-right"
                          rowClasses="list-row"
                          styleClass="list-background-grid"
                          value="#{CoffeeBreakBean.cart.items}"
                          var="sci">
                
                <f:facet name="header">
                    <h:outputText value="#{CBMessages.OrderForm}"/>
                </f:facet>
                
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Coffee"/>
                    </f:facet>
                    <h:outputText id="coffeeName" value="#{sci.item.coffeeName}"/>
                </h:column>
                
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Price"/>
                    </f:facet>
                    <h:outputText id="retailPricePerPound" value="#{sci.item.retailPricePerPound}">
                        <f:convertNumber type="currency" currencySymbol="$"/>
                    </h:outputText>      
                </h:column>
                
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Quantity"/>
                    </f:facet>
                    <h:inputText id="quantity"
                                 required="true"
                                 size="3"
                                 value="#{sci.pounds}">
                        <f:converter converterId="javax.faces.BigDecimal" />
                    </h:inputText>
                </h:column>
                
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Total" />
                    </f:facet>
                    <h:outputText value="#{sci.price}">
                        <f:convertNumber type="currency" currencySymbol="$"/>
                    </h:outputText>
                    <f:facet name="footer">
                        <h:outputText value="#{CoffeeBreakBean.cart.total}">
                            <f:convertNumber type="currency" currencySymbol="$"/>
                        </h:outputText>
                    </f:facet>
                </h:column>
                
            </h:dataTable>
            <br>
            <h:commandButton id="clear"
                            action="#{CoffeeBreakBean.clear}"
                            value="#{CBMessages.Clear}"  />
            
            <h:commandButton id="checkoutLink"
                            value="#{CBMessages.Checkout}"
                            action="#{CoffeeBreakBean.checkout}" />
            
            <h:commandButton id="create"
                            action="#{CoffeeBreakBean.update}"
                            value="#{CBMessages.Update}"  />
        </center>
    </h:form>
</f:view>




