<f:view>
    <h:form>
        <jsp:include page="/template/template.jsp"/>
        <center>
            <h:messages/>
            <h:outputText value="#{bundle.CustomerString}"/>
            <h:outputText value=" "/>
            <h:outputText value="#{atmBean.operationString}"/>
            <h:outputText value=" "/>
            <h:outputText value="#{atmBean.amount}">
                <f:convertNumber type="currency" currencySymbol="$"/>
            </h:outputText>
            <h:outputText value=" "/>
            <h:outputText value="#{atmBean.prepString}"/>
            <h:outputText value=" "/>

            <h:commandLink action="#{accountHistoryBean.submit}"
                           value="#{atmBean.selectedAccount.description}">
                <f:param name="accountId" value="#{atmBean.accountId}"/>
            </h:commandLink>

            <p>
                <h:outputText value="#{bundle.BalanceString}"/>
                <h:outputText value=" "/>
                <h:outputText value="#{atmBean.selectedAccount.balance}">
                    <f:convertNumber type="currency" currencySymbol="$"/>
                </h:outputText>.
        </center>
    </h:form>
</f:view>






