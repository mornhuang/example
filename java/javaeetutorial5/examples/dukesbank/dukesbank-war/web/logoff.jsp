<f:view>
    <h:form>
        <jsp:include page="/template/template.jsp"/>
        <center>
            <h3><h:outputText value="#{bundle.Farewell}"/></h3>
            <h:commandLink value="#{bundle.LogonAgain}"
                           action="#{customerBean.logout}"/>
        </center>
    </h:form>
</f:view>






















