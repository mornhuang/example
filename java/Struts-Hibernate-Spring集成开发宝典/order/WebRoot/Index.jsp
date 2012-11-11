<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<html>
<head>
	<title><bean:message key="index.home"/></title>
</head>
<body>
	<h3><bean:message key="index.home"/></h3>

	<table width="730" border="0">
		<tr>
			<td>
				<table width="100%" border="1" bgcolor="#ffffcc">
					<tr>
						<td>
							<font size="-1">
								<bean:message key="index.prompt" />
							</font>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<html:link action="/PlaceOrder.do">
					<bean:message key="index.placeorder" />
				</html:link>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
				<html:link action="/QueryOrder.do">
					<bean:message key="index.findorder" />
				</html:link>
			</td>
		</tr>				
		
	</table>	
</body>
</html>