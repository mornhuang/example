<center>
    <h:panelGrid columns="4" border="0" cellpadding="10" cellspacing="25"
                 width="600" summary="layout">
        <h:commandLink value="#{bundle.AccountList}"
                       action="#{navigation.accountList.action}"/>
        <h:commandLink value="#{bundle.TransferFunds}"
                       action="#{navigation.transferFunds.action}"/>
        <c:set var="accountId" scope="request"
               value="${customerBean.accounts[0].accountId}"/>
        <h:commandLink value="#{bundle.ATM}" action="#{navigation.atm.action}"/>        
        <h:commandLink value="#{bundle.Logoff}"
                       action="#{navigation.logout.action}"/>
    </h:panelGrid>
</center>
