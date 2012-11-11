<f:view>
    <h:form>
        <jsp:include page="/template/template.jsp"/>
        <h:messages styleClass="error-message"/>
        <center>
            <h:panelGrid border="0" cellpadding="2" cellspacing="0" width="500"
                         summary="layout">

                <h:outputText value="#{bundle.Operation}"/>
                <h:selectOneMenu value="#{atmBean.operation}">
                    <f:selectItems value="#{atmBean.operationOptions}"/>
                </h:selectOneMenu>

                <h:outputText value="#{bundle.Account}"/>
                <h:selectOneMenu value="#{atmBean.accountId}">
                    <c:forEach var="accounts" items="#{customerBean.accounts}">
                        <f:selectItem
                              itemLabel="#{accounts.description} #{accounts.balance}"
                              itemValue="#{accounts.accountId}"/>
                    </c:forEach>
                </h:selectOneMenu>

                <h:outputText value=" "/>
                <h:outputText value=" "/>

                <h:outputText value="#{bundle.ATMAmount}"/>
                <h:inputText label="Amount" size="15" id="amount"
                             required="true"
                             value="#{atmBean.amount}"
                             converterMessage="#{bundle.AmountError}">
                    <f:converter converterId="javax.faces.BigDecimal"/>
                </h:inputText>
                <h:commandButton value="#{bundle.Submit}"
                                 action="#{atmBean.submit}"/>
            </h:panelGrid>
        </center>
    </h:form>
</f:view>
