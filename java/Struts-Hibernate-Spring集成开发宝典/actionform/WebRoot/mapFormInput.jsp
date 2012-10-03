<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html" prefix="html" %>

<html>
<head>
  <title>Map-backed Form</title>
</head>
<body>
    <h2>Map Form Test</h2>
    <html:form action="/mapForm">
        Java Skill: 
        <html:select property="skill(java)">
            <html:options property="skillLevels"/>
        </html:select><br />
        JSP Skill: 
        <html:select property="skill(jsp)">
            <html:options property="skillLevels"/>
        </html:select><br />
        Struts Skill: 
        <html:select property="skill(struts)">
            <html:options property="skillLevels"/>
        </html:select><br />
        <html:submit/>
    </html:form>
</body>
</html>