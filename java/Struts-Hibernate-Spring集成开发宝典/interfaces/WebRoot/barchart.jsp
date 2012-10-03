<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Bar Chart</title>
</head>
<body>
<div align="center">
	<h3>Color Bar Chart (horizontal)</h3>
	<table width="60%">
		<c:forEach var="col" items="${weekWeather}">
		<tr>
			<td align="right" width="20%">${col.item}(${col.percent}%)</td>
			<td align="left" width="80%">
				<table width="100%" height="20">
					<tr>
						<td width="${col.percent}%" bgcolor="#003366"></td>
						<td width="${100-col.percent}%"></td>
					</tr>
				</table>
			</td>
		</tr>
		</c:forEach>
	</table>

	<hr />
	<h3>Color Bar Chart (vertical)</h3>
	<table height="300" width="500">
		<tr>
		<c:forEach var="row" items="${weekWeather}">
			<td>
				<table width="100%" height="100%">
					<tr>
						<td height="${100-row.percent}%"></td>
					</tr>
					<tr>
						<td height="${row.percent}%" bgcolor="#003366"></td>
					</tr>
					<tr>
						<td align="center">${row.item}<br>(${row.percent}%)</td>
					</tr>
				</table>
			</td>
		</c:forEach>
		</tr>
	</table>
</div>
</body>	
</html>