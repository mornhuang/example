<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>
<html>
	<head>
		<title>ActionForm Samples</title>
		<link href="css/style.css"  rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<h1>ActionForm Sample</h1>
		<ul>
			<LI><html:link page="/listFormInput.jsp">List-Backed Form</html:link></LI>
		</ul>
		<ul>
			<LI><html:link page="/mapFormInput.jsp">Map-Backed Form</html:link></LI>
		</ul>
		<ul>
			<LI><html:link page="/dynaFormInput.jsp">Dyna Action Form</html:link></LI>
		</ul>
		<ul>
			<LI><html:link page="/lazyDynaFormInput.jsp">Lazy Dyna Action Form</html:link></LI>
		</ul>
	</body>
</html>