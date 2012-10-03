<html>

<f:view>
    <head>
        <title>
            <h:outputText value="#{bundle.TitleLogonError}"/>
        </title>
    </head>

    <body>
    <%@ include file="/template/banner.jsp" %>
    <h:form>
        <h:outputText value="#{bundle.LogonError}"/>

        <p><h:commandLink action="main" value="#{bundle.LogonAgain}"/></p>
    </h:form>
    </body>
</f:view>
</html>

