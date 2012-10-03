<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Dynamic Style</title>
	<style>
		/* Even row */
		.row1 {background-color:orange;}

		/* Odd row */
		.row0 {background-color:yellow;}
		
		/* Title row */
		.title {background-color:blue;color:white;text-align:center;}
	</style>
</head>
<body>
<div align="center">
	<h3>Dynamic Style</h3>
	<table width="300">
		<tr class="title">
			<th width="100">Day</th>
			<th>Rain Percent</th>
		</tr>
		<c:forEach var="day" items="${weekWeather}" varStatus="loop">
		<tr class="row${loop.count%2}">
			<td align="left">${day.item}</td>
			<td align="right">${day.percent}%</td>
		</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>