<f:view>
    <h:form>
        <jsp:include page="/template/template.jsp"/>
        <center>

            <h:panelGrid border="1" cellpadding="2" cellspacing="0" columns="4"
                         width="500" summary="layout">
                <h:outputText value="#{bundle.AccountName}"/>
                <h:outputText value="#{bundle.AccountId}"/>
                <h:outputText value="#{bundle.AccountBalance}"/>
                <h:outputText value="#{bundle.AccountCredit}"/>

                <h:commandLink action="#{accountHistoryBean.submit}"
                               value="#{transferFundsBean.fromAccount.description}">
                    <f:param name="accountId"
                             value="#{transferFundsBean.fromAccount.accountId}"/>
                </h:commandLink>
                <h:outputText
                      value="#{transferFundsBean.fromAccount.accountId}"/>
                <h:outputText value="#{transferFundsBean.fromAccount.balance}">
                    <f:convertNumber type="currency" currencySymbol="$"/>
                </h:outputText>
                <c:if test="${transferFundsBean.fromAccount.creditLine != 0}">
                    <h:outputText
                          value="#{transferFundsBean.fromAccount.remainingCredit}">
                        <f:convertNumber type="currency" currencySymbol="$"/>
                    </h:outputText>
                </c:if>
                <c:if test="${transferFundsBean.fromAccount.creditLine == 0}">
                    <h:outputText value="0.00">
                        <f:convertNumber type="currency" currencySymbol="$"/>
                    </h:outputText>
                </c:if>

                <h:commandLink action="#{accountHistoryBean.submit}"
                               value="#{transferFundsBean.toAccount.description}">
                    <f:param name="accountId"
                             value="#{transferFundsBean.toAccount.accountId}"/>
                </h:commandLink>
                <h:outputText value="#{transferFundsBean.toAccount.accountId}"/>
                <h:outputText value="#{transferFundsBean.toAccount.balance}">
                    <f:convertNumber type="currency" currencySymbol="$"/>
                </h:outputText>
                <c:if test="${transferFundsBean.toAccount.creditLine != 0}">
                    <h:outputText
                          value="#{transferFundsBean.toAccount.remainingCredit}">
                        <f:convertNumber type="currency" currencySymbol="$"/>
                    </h:outputText>
                </c:if>
                <c:if test="${transferFundsBean.toAccount.creditLine == 0}">
                    <h:outputText value=" "/>
                </c:if>
            </h:panelGrid>

            <h:outputText value="#{bundle.TransferAck}"/>
            <h:outputText value="#{transferFundsBean.transferAmount}">
                <f:convertNumber type="currency" currencySymbol="$"/>.
            </h:outputText>
        </center>
    </h:form>
</f:view>
