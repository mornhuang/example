<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>


<html>
<head>
	<title><bean:message key="index.placeorder"/></title>
</head>
<body>
	<html:form action="SaveNewOrder">			

	<h3><bean:message key="index.placeorder"/></h3>

		<table width="730" border="0">
			<tr>
				<td>
					<table width="100%" border="1" bgcolor="#ffffcc">
						<tr>
							<td>
								<font size="-1">
									<bean:message key="neworder.prompt" />
								</font>
							</td>
						</tr>
					</table>

					<table width="100%" border="0">
						<tr>
							<td colspan="2">
								<html:errors />
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="neworder.placedby"/>
							</td>
							<td>
								<html:text property="whoPlacedOrder" 
									size="25" maxlength="50" styleClass="textBox" 
									tabindex="1" />
							</td>
						</tr>
					</table>
					<br>
					<table width="100%" border="0">
						<tr bgcolor="ccccff">
							<td>
								&nbsp;
							</td>								
							<td>
								<b><bean:message key="neworder.itemdesc"/></b>
							</td>
							<td>
								<b><bean:message key="neworder.price"/></b>
							</td>
						</tr>
						<tr bgcolor="eeeeee">
							<td>
								1.
							</td>
							<td>
								<html:text property="itemDesc_1" size="25" 
									maxlength="50" styleClass="textBox" 
									tabindex="1" />
							</td>
							<td>
								<html:text property="itemPrice_1" size="25" 
									maxlength="50" styleClass="textBox" 
									tabindex="2" />
							</td>
						</tr>
						<tr bgcolor="eeeeee">
							<td>
								2.
							</td>
							<td>
								<html:text property="itemDesc_2" size="25" 
									maxlength="50" styleClass="textBox" 
									tabindex="3" />
							</td>
							<td>
								<html:text property="itemPrice_2" size="25" 
									maxlength="50" styleClass="textBox" 
									tabindex="4" />
							</td>
						</tr>
						<tr bgcolor="eeeeee">
							<td>
								3.
							</td>
							<td>
								<html:text property="itemDesc_3" size="25" 
									maxlength="50" styleClass="textBox" 
									tabindex="5" />
							</td>
							<td>
								<html:text property="itemPrice_3" size="25" 
									maxlength="50" styleClass="textBox" 
									tabindex="6" />
							</td>
						</tr>
					</table>
					<table width="100%">
						<tr>
							<td align="center">
								<br>
								<html:submit styleClass="normal">
									<bean:message key="neworder.saveorder" />
								</html:submit>
							</td>
						</tr>
					</table>
					<table width="100%">
						<tr>
							<td align="center">
								<br><br>
								<html:link action="/Index.do">
									<bean:message key="index.home"/>
								</html:link>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		
	</html:form>
</body>
</html>