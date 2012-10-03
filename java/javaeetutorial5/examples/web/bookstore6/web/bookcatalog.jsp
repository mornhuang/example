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

<f:view>
<f:loadBundle var="bundle" basename="com.sun.bookstore.messages.BookstoreMessages"/>

<h:messages/>
<br>

<h:form               id="form">

  <h:commandLink      id="check"
                  action="cart"
               immediate="true"
                rendered="#{cart.numberOfItems > 0}">
    <h:outputText
                   value="#{bundle.CartCheck}"/>
  </h:commandLink>

  <h:outputText   escape="false"
                   value="&nbsp;"
                rendered="#{cart.numberOfItems > 0}"/>

  <h:commandLink      id="clear"
                  action="#{catalog.clear}"
               immediate="true"
                rendered="#{cart.numberOfItems > 0}">
    <h:outputText
                   value="#{bundle.ClearCart}"/>
  </h:commandLink>

  <h:outputText   escape="false"
                   value="&nbsp;"
                rendered="#{cart.numberOfItems > 0}"/>

  <h:commandLink      id="buy"
                  action="#{catalog.buy}"
               immediate="true"
                rendered="#{cart.numberOfItems > 0}">
    <h:outputText
                  value="#{bundle.Buy}"/>
  </h:commandLink>

  <h:outputText  escape="false"
               rendered="#{cart.numberOfItems > 0}"
                  value="<br><br>"/>

    <table border="1" class="list-background" 
  	 summary="Add books from the catalog to your shopping cart.">
      <thead>
	<tr>
	   <th colspan="6" class="list-header">
	   	<h:outputText value="#{bundle.Choose}"/></th>
	</tr>
	<tr>
	   <th class="list-header">
	   	<h:outputText value = "#{bundle.Quantities}" /></th>
	   <th class="list-header">
                <h:outputText value="#{bundle.ItemTitle}"/></th>
           <th class="list-header">
	        <h:outputText value="#{bundle.By}"/></th> 
	   <th class="list-header">
	   	<h:outputText value="#{bundle.ItemPrice}"/></th>
          <th class="list-header">
	        <h:outputText value="#{bundle.Inventory}"/></th>
	  <th class="list-header">
	   	<h:outputText value="#{bundle.CartAdd}"/></th>
	</tr>
      </thead>
      <tfoot>
      	<tr>
		<td colspan="6" class="list-header-left">
			<h:outputText  value="#{bundle.Total} #{catalog.totalBooks}" />
		</td>
	</tr> 
      </tfoot>
	
                        
        <c:forEach items="#{dbao.books}" var="book" varStatus="stat">
           <c:set var="book" scope="request" value="${book}"/> 
	   <tr class="${(stat.index % 2) == 0 ? "list-row-even" : "list-row-odd"}">
		<td class="list-column-center">
			${catalog.bookQuantity}
		</td>
		<td class="list-column-left">
			<h:commandLink action="#{catalog.details}" 
                                       value="#{book.title}">
			   <f:setPropertyActionListener target="#{requestScope.book}"
                                                         value="#{book}"/>
			</h:commandLink>
		</td>
		<td class="list-column-left">
			<h:outputText id="firstName" value="#{book.firstName} #{book.surname}"/>
		</td>
		<td class="list-column-right">
			<h:outputText id="price" value="#{book.price}">
				<f:convertNumber type="currency"/>
			</h:outputText>
		</td>
		<td class="list-column-right">
			<h:outputText id="inventory" value="#{book.inventory}">
			</h:outputText>
		</td>
		<td class="list-column-center">
			<h:commandButton id="add"
				action="#{catalog.add}"
				value="#{bundle.CartAdd}">
                            <f:setPropertyActionListener target="#{requestScope.book}"
                                                         value="#{book}"/>
                        </h:commandButton>
                </td>
	   </tr>
           <c:remove var="book" scope="request"/>
        </c:forEach>

   </table>

</h:form>

</f:view>
