<f:view>
    <h:form>
        <jsp:include page="/template/template.jsp"/>
        <h:messages styleClass="error-message"/>
        <center>
            <h:panelGrid columns="2">
                <h:outputText value="#{bundle.AccountFrom}"/>

                <h:selectOneMenu
                      value="#{transferFundsBean.fromAccountId}"
                      required="true" requiredMessage="#{bundle.AccountError}">
                    <c:forEach var="accounts" items="#{customerBean.accounts}">
                        <f:selectItem
                              itemLabel="#{accounts.description} #{accounts.balance}"
                              itemValue="#{accounts.accountId}"/>
                    </c:forEach>
                </h:selectOneMenu>
                <h:outputText value="#{bundle.AccountTo}"/>
                <h:selectOneMenu
                      value="#{transferFundsBean.toAccountId}"
                      required="true" requiredMessage="#{bundle.AccountError}">
                    <c:forEach var="accounts" items="#{customerBean.accounts}">
                        <f:selectItem
                              itemLabel="#{accounts.description} #{accounts.balance}"
                              itemValue="#{accounts.accountId}"/>
                    </c:forEach>
                </h:selectOneMenu>
                <h:outputText value="#{bundle.TransferAmount}"/>
                <h:inputText size="15"
                             value="#{transferFundsBean.transferAmount}"
                             converterMessage="#{bundle.AmountError}">
                    <f:converter converterId="javax.faces.BigDecimal"/>
                </h:inputText>
                <h:commandButton value="#{bundle.Submit}"
                                 action="#{transferFundsBean.submit}">
                </h:commandButton>
            </h:panelGrid>
        </center>
    </h:form>
</f:view>
