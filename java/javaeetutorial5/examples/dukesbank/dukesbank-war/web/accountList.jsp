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
 * '
--%>

<f:view>
<h:form>
<jsp:include page="/template/template.jsp"/>
<center>

  <h:dataTable        id="accounts"
            captionClass="list-caption"
           columnClasses="list-column-left, list-column-center,
                          list-column-right, list-column-right"
             footerClass="list-footer"
             headerClass="list-header"
              rowClasses="list-row-even, list-row-odd"
              styleClass="list-background"
                 summary="#{bundle.AccountList}"
                   value="#{customerBean.accounts}"
                     var="ad"> 
<h:column>
  	<f:facet name="header">
		<h:outputText value="#{bundle.AccountName}" />
	</f:facet>
  	<h:commandLink value="#{ad.description}" immediate="true"
            action="#{accountHistoryBean.submit}">
                <f:param name="accountId" value="#{ad.accountId}"/>

      </h:commandLink>
  </h:column>
  <h:column>
  	<f:facet name="header">
		<h:outputText value="#{bundle.AccountId}" />
	</f:facet>
  	<h:outputText value="#{ad.accountId}"/>
  </h:column>
  <h:column>
  	<f:facet name="header">
		<h:outputText value="#{bundle.AccountBalance}" />
	</f:facet>
  	<h:outputText value="#{ad.balance}">
		<f:convertNumber type="currency" currencySymbol="$"/>
	</h:outputText>
  </h:column>
  <h:column>
  	<f:facet name="header">
		<h:outputText value="#{bundle.AccountCredit}" />
	</f:facet>
		<h:outputText value="#{ad.remainingCredit}">
			<f:convertNumber type="currency" currencySymbol="$" />
		</h:outputText>
   </h:column>
</h:dataTable>


</center>
</h:form>
</f:view>
