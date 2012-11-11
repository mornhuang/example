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
    <h:form id="checkoutForm" >
        <center>
        <p><h:outputText value="#{CBMessages.YourOrder}" /> 
        <h:outputText value="#{CoffeeBreakBean.cart.total}" >
            <f:convertNumber type="currency" currencySymbol="$"/>
        </h:outputText>
        <h:outputText value="."/>
        <p>
        <h:outputText value="#{CBMessages.CheckoutInstructions}" />
        
        <h:panelGrid columns="5"
                     headerClass="list-header"
                     rowClasses="list-row"
                     styleClass="list-background"
                     title="CheckOut Form" >
            
            <f:facet name="header">
                <h:outputText value="#{CBMessages.CheckoutForm}"/>
            </f:facet>
            
            <h:outputText id="firstNameLabel" value="#{CBMessages.FirstName}"/>
            <h:inputText id="firstName"
                         value="#{checkoutFormBean.firstName}" 
                         size="15"
                         maxlength="20"
                         required="true"/>
            <h:message for="firstName" /> 
            <h:outputText value="" />
            <h:outputText value="" />
            
            
            <h:outputText id="lastNameLabel" value="#{CBMessages.LastName}" />
            <h:inputText id="lastName"
                         value="#{checkoutFormBean.lastName}" 
                         size="15"
                         maxlength="20"
                         required="true" />
            <h:message for="lastName" />
            <h:outputText value="" />
            <h:outputText value="" />
            
            
            <h:outputText id="emailLabel" value="#{CBMessages.EMail}" />
            <h:inputText id="email"
                         value="#{checkoutFormBean.email}" 
                         size="25"
                         maxlength="125"
                         validator="#{checkoutFormBean.validateEmail}"/>
            <h:message for="email" />
            <h:outputText value="" />  
            <h:outputText value="" />
            
            <h:outputText id="phoneNumberLabel" value="#{CBMessages.PhoneNumber}" />
            <h:inputText id="areaCode"
                         value="#{checkoutFormBean.areaCode}"
                         size="3" 
                         maxlength="3"
                         required="true"/>
            <h:inputText id="phoneNumber"
                         value="#{checkoutFormBean.phoneNumber}"  
                         size="8"
                         maxlength="8"
                         required="true"/>
            <h:message for="areaCode" /> 
            <h:message for="phoneNumber" /> 
            
            
            <h:outputText id="streetLabel" value="#{CBMessages.Street}" />
            <h:inputText id="street" value="#{checkoutFormBean.street}" required="true"/>
            <h:message for="street" />
            <h:outputText value="" />
            <h:outputText value="" />
            
            
            <h:outputText id="cityLabel" value="#{CBMessages.City}" />
            <h:inputText id="city"
                         value="#{checkoutFormBean.city}" 
                         maxlength="25"
                         required="true" />
            <h:message for="city" />
            <h:outputText value="" />
            <h:outputText value="" />
            
            <h:outputText id="stateLabel" value="#{CBMessages.State}" />
            <h:inputText id="state"
                         value="#{checkoutFormBean.state}"
                         size="2" 
                         maxlength="2"
                         required="true" />
            <h:message for="state" />
            <h:outputText value="" />
            <h:outputText value="" />
            
            <h:outputText id="zipLabel" value="#{CBMessages.Zip}" />
            <h:inputText id="zip"
                         value="#{checkoutFormBean.zip}"
                         size="5" 
                         maxlength="5"
                         required="true" >
                <f:validateLength maximum="5" minimum="5" />
                <f:validateLongRange minimum="00000" maximum="99999" />
            </h:inputText>
            <h:message for="zip" />
            <h:outputText value="" />
            <h:outputText value="" />
            
            <h:outputText id="ccoptionLabel" value="#{CBMessages.CCOption}" />
            <h:selectOneMenu id="CCOption">
                <f:selectItem itemValue="0" itemLabel="VISA" />
                <f:selectItem itemValue="1" itemLabel="MasterCard" />
                <f:selectItem itemValue="2" itemLabel="AmericanExpress" />
            </h:selectOneMenu>
            <h:outputText value="" />
            <h:outputText value="" />
            <h:outputText value="" />
            
            <h:outputText id="ccnumberLabel" value="#{CBMessages.CCNumber}" />
            <h:inputText id="CCNumber"
                         value="#{checkoutFormBean.CCNumber}"
                         size="16" 
                         maxlength="16"
                         required="true" />
            <h:message for="CCNumber" />
            <h:outputText value="" />
            <h:outputText value="" />
            
            <h:commandButton value="#{CBMessages.Submit}" 
                             action="#{checkoutFormBean.submit}"/>
            <h:commandButton value="#{CBMessages.Reset}" 
                             action="#{checkoutFormBean.clear}"/>
            
        </h:panelGrid>
        
    </h:form>
</f:view>
