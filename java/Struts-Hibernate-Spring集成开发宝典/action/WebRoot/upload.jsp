<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>


<html>
	<head>
		<title>Struts File Upload Example</title>
	</head>
	<body bgcolor="white">
		<html:form action="/upload" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td align="center" colspan="2">
						<font size="4">Please Enter the Following Details</font>
					</td>
				</tr>
				<tr>
					<td align="left" colspan="2">
						<font color="red"><html:errors /></font>
					</td>
				</tr>
				<tr>
					<td align="right">
						File Name
					</td>
					<td align="left">
						<html:file property="theFile" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="2">
						<html:submit>Upload File</html:submit>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
