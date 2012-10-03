<f:view>

<h:form id="form">
<jsp:include page="/template/template.jsp"/>
<h:messages styleClass="error-message"/>
<center>
<h:panelGrid border="1" columns="2"
             columnClasses="list-column-left, list-column-right"
             footerClass="form-footer"
             headerClass="form-header"
             styleClass="list-background"
             summary="#{bundle.AccountDetails}">
    <f:facet name="header">
        <h:outputText
              value="#{accountHistoryBean.selectedAccount.description} #{bundle.AccountDetails}"/>
    </f:facet>
    <h:outputText value="#{bundle.AccountDescription}"
                  styleClass="list-header"/>
    <h:outputText value="#{bundle.AccountAmount}" styleClass="list-header"/>

    <h:outputText value="#{bundle.AccountBeginningBalance}"/>
    <h:outputText value="#{accountHistoryBean.beginningBalance}">
        <f:convertNumber type="currency" currencySymbol="$"/>
    </h:outputText>

    <h:outputText value="#{bundle.AccountCredits}"/>
    <h:outputText value="#{accountHistoryBean.credits}">
        <f:convertNumber type="currency" currencySymbol="$"/>
    </h:outputText>

    <h:outputText value="#{bundle.AccountDebits}"/>
    <h:outputText value="#{accountHistoryBean.debits}">
        <f:convertNumber type="currency" currencySymbol="$"/>
    </h:outputText>

    <h:outputText value="#{bundle.AccountEndingBalance}"/>
    <h:outputText value="#{accountHistoryBean.endingBalance}">
        <f:convertNumber type="currency" currencySymbol="$"/>
    </h:outputText>
</h:panelGrid>

<h:panelGrid columns="3"
             footerClass="form-footer"
             headerClass="form-header"
             styleClass="list-background"
             summary="#{bundle.GetTx}">
    <f:facet name="header">
        <h:outputText value="#{bundle.TxParam}"/>
    </f:facet>
    <h:outputText value="#{bundle.AccountName}" styleClass="header-class"/>
    <h:outputText value="#{bundle.ViewSelect}" styleClass="header-class"/>
    <h:outputText value="#{bundle.SortSelect}" styleClass="header-class"/>

    <h:selectOneMenu value="#{accountHistoryBean.accountId}">
        <c:forEach var="accounts" items="#{customerBean.accounts}">
            <f:selectItem itemLabel="#{accounts.description}"
                          itemValue="#{accounts.accountId}"/>
        </c:forEach>
    </h:selectOneMenu>

    <h:selectOneMenu id="activityView"
                     value="#{accountHistoryBean.activityView}">
        <f:selectItems value="#{accountHistoryBean.AVOptions}"/>
    </h:selectOneMenu>

    <h:selectOneMenu id="sortOption" value="#{accountHistoryBean.sortOption}">
        <f:selectItems value="#{accountHistoryBean.sortOptions}"/>
    </h:selectOneMenu>

    <h:outputText value="#{bundle.GetTx}"/>
    <h:selectOneRadio id="sinceOrRange"
                      value="#{accountHistoryBean.sinceOrRange}"
                      layout="pageDirection" styleClass="align-left">
        <f:selectItem itemLabel="#{bundle.TxSince}" itemValue="since"/>
        <f:selectItem itemLabel="#{bundle.TxRange}" itemValue="range"/>
    </h:selectOneRadio>
    <h:commandButton id="Submit" value="#{bundle.Update}"
                     action="#{accountHistoryBean.submit}"/>

    <%-- First Row --%>
    <h:panelGrid columns="1">
        <h:outputText value=" "/>
        <h:outputText value="#{bundle.DateRange}"
                      rendered="#{param['form:sinceOrRange'] == accountHistoryBean.range}"/>
    </h:panelGrid>
    <h:panelGrid columns="1">
        <h:outputText value="#{bundle.DateSince}"
                      rendered="#{param['form:sinceOrRange'] == accountHistoryBean.since}"/>

        <h:outputText value="#{bundle.DateThrough}"
                      rendered="#{param['form:sinceOrRange'] == accountHistoryBean.range}"/>

    </h:panelGrid>
    <h:outputText value="#{bundle.Year}"/>

    <%-- Second Row --%>
    <h:panelGrid columns="1">
        <h:selectOneMenu id="beginMonth"
                         value="#{accountHistoryBean.beginMonth}"
                         rendered="#{param['form:sinceOrRange'] == accountHistoryBean.range}">
            <f:selectItems value="#{accountHistoryBean.beginMonthOptions}"/>
        </h:selectOneMenu>
        <h:selectOneMenu id="beginDay"
                         value="#{accountHistoryBean.beginDay}"
                         rendered="#{param['form:sinceOrRange'] == accountHistoryBean.range}">
            <f:selectItems value="#{accountHistoryBean.beginDayOptions}"/>
        </h:selectOneMenu>
    </h:panelGrid>

    <%-- Third Row --%>
    <h:panelGrid columns="1">
        <h:selectOneMenu id="sinceMonth"
                         value="#{accountHistoryBean.sinceMonth}"
                         rendered="#{param['form:sinceOrRange'] == accountHistoryBean.since}">
            <f:selectItems value="#{accountHistoryBean.sinceMonthOptions}"/>
        </h:selectOneMenu>
        <h:selectOneMenu id="sinceDay"
                         value="#{accountHistoryBean.sinceDay}"
                         rendered="#{param['form:sinceOrRange'] == accountHistoryBean.since}">
            <f:selectItems value="#{accountHistoryBean.sinceDayOptions}"/>
        </h:selectOneMenu>
        <h:selectOneMenu id="endMonth" value="#{accountHistoryBean.endMonth}"
                         rendered="#{param['form:sinceOrRange'] == accountHistoryBean.range}">
            <f:selectItems value="#{accountHistoryBean.endMonthOptions}"/>
        </h:selectOneMenu>
        <h:selectOneMenu id="endDay" value="#{accountHistoryBean.endDay}"
                         rendered="#{param['form:sinceOrRange'] == accountHistoryBean.range}">
            <f:selectItems value="#{accountHistoryBean.endDayOptions}"/>
        </h:selectOneMenu>
    </h:panelGrid>

    <h:selectOneMenu id="year" value="#{accountHistoryBean.year}">
        <f:selectItems value="#{accountHistoryBean.yearOptions}"/>
    </h:selectOneMenu>


</h:panelGrid>

<c:choose>
    <c:when test="${empty accountHistoryBean.selectedTransactions}">
        <h:outputText value="#{bundle.NoTransactions}"/>
    </c:when>
    <c:otherwise>

        <h:dataTable id="items"
                     captionClass="list-caption"
                     columnClasses="list-column-left, list-column-center,
                          list-column-right, list-column-right"
                     footerClass="list-footer"
                     headerClass="list-header"
                     rowClasses="list-row-even, list-row-odd"
                     styleClass="list-background"
                     summary="#{bundle.TxTitle}"
                     value="#{accountHistoryBean.selectedTransactions}"
                     var="tx">
            <f:facet name="header">
                <h:outputText value="#{bundle.TxTitle}"/>
            </f:facet>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="#{bundle.TxDate}"/>
                </f:facet>
                <h:outputText value="#{tx.timeStamp}">
                    <f:convertDateTime pattern="MMMMM dd, yyyy hh:mm aaa"/>
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="#{bundle.TxDescription}"/>
                </f:facet>
                <h:outputText value="#{tx.description}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="#{bundle.TxAmount}"/>
                </f:facet>
                <h:outputText value="#{tx.amount}">
                    <f:convertNumber type="currency" currencySymbol="$"/>
                </h:outputText>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="#{bundle.TxRunningBalance}"/>
                </f:facet>
                <h:outputText value="#{tx.balance}">
                    <f:convertNumber type="currency" currencySymbol="$"/>
                </h:outputText>
            </h:column>
        </h:dataTable>
    </c:otherwise>
</c:choose>
</center>
</h:form>
</f:view>

