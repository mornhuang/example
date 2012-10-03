<%@ taglib prefix="ww" uri="webwork" %>
<%@ page import="com.redsaga.hibernatesample.step4.User"%>
<%@ page contentType="text/html;charset=utf-8"%>
<ww:i18n name="'messages'">
	<head>
	<link rel="stylesheet" href="style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
    <ww:if test="#session['loginUser']==null">
        <ww:text name="'notLoggedIn'"/>.
    </ww:if>
    <ww:else>
    <form action="newArticle.action" method='post'>
        <ww:text name="'article.title'"/>
            <input name="article.title"/>
        <ww:text name="'article.body'"/>
            <textarea name="article.body" rows="10" cols="80"  ></textarea>
        <br/>
        <input type="hidden" name="boardId" value="<%= request.getParameter("board")%>"/>
        <input type="submit" value="<ww:text name="'new.article.post'"/>"/>
    </form>
    </ww:else>
</ww:i18n>
