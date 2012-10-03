<%@ taglib prefix="ww" uri="webwork" %>
<%@ page import="com.redsaga.hibernatesample.step3.User"%>
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
    <form action="newVote.action" method="post">
        <ww:text name="'vote.title'"/>
            <input name="vote.title"/><br/>
        <ww:text name="'vote.options'"/><br/>
        <textarea name="options" rows="4" cols="40"  ></textarea><br/>

        <ww:text name="'vote.body'"/><br/>
            <textarea name="vote.body" rows="5" cols="80"  ></textarea>
        <br/>
        <input type="hidden" name="boardId" value="<%= request.getParameter("board")%>"/>
        <input type="submit" value="<ww:text name="'new.vote.post'"/>"/>
    </form>
    </ww:else>
</ww:i18n>
