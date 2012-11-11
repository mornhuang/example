<!--

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
-->
<%@ page errorPage="errorpage.jsp" %>

<f:view>
   <f:loadBundle basename="com.sun.bookstore.messages.BookstoreMessages" var="bundle"/>
   <h:form>
        <h:panelGrid columns="1" 
                      footerClass="form-footer"
                      headerClass="form-header"
                      styleClass="main-background"
                      columnClasses="single-column"
                      summary="#{bundle.ChooseLocale}" 
                      title="#{bundle.ChooseLocale}" >
            <h:outputText styleClass="maintitle"
                           value="#{bundle.ChooseLocale}" />
            <h:graphicImage id="mapImage" url="/template/world.jpg" 
                             alt="#{bundle.ChooseLocale}"
                             usemap="#worldMap" />
            <bookstore:map id="worldMap" current="NAmericas" immediate="true"
                   action="bookstore"
                   actionListener="#{localeBean.chooseLocaleFromMap}"  >
                    <bookstore:area id="NAmerica" value="#{NA}" 
                            onmouseover="/template/world_namer.jpg" 
                            onmouseout="/template/world.jpg" 
                            targetImage="mapImage" />
                    <bookstore:area id="SAmerica" value="#{SA}" 
                            onmouseover="/template/world_samer.jpg"
                            onmouseout="/template/world.jpg" 
                            targetImage="mapImage" />
                    <bookstore:area id="Germany" value="#{gerA}" 
                            onmouseover="/template/world_germany.jpg" 
                            onmouseout="/template/world.jpg" 
                            targetImage="mapImage" />
                    <bookstore:area id="France" value="#{fraA}" 
                            onmouseover="/template/world_france.jpg" 
                            onmouseout="/template/world.jpg" 
                            targetImage="mapImage" />
            </bookstore:map>
        </h:panelGrid>
</h:form>
<h:form>
        <h:panelGrid id="links" columns="4" 
                      summary="#{bundle.chooseLocale}" 
                      title="#{bundle.chooseLocale}" >
          <h:commandLink id="NAmerica" action="bookstore"
                          actionListener="#{localeBean.chooseLocaleFromLink}">
             <h:outputText value="#{bundle.English}" />
          </h:commandLink>
          <h:commandLink id="Germany" action="bookstore"
                         actionListener="#{localeBean.chooseLocaleFromLink}" > 
            <h:outputText value="#{bundle.German}" />
          </h:commandLink>
          <h:commandLink id="France" action="bookstore"
                          actionListener="#{localeBean.chooseLocaleFromLink}">
            <h:outputText value="#{bundle.French}" />
          </h:commandLink>
          <h:commandLink id="SAmerica" action="bookstore"
                          actionListener="#{localeBean.chooseLocaleFromLink}">
            <h:outputText value="#{bundle.Spanish}" />
          </h:commandLink>
        </h:panelGrid>
    </h:form>
</f:view>
</html>

