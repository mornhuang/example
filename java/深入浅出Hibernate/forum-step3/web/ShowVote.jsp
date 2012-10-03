<%@ taglib prefix="ww" uri="webwork" %>
<%@ page contentType="text/html;charset=utf-8"%>
<ww:i18n name="'messages'">

<html>
	<head>
	<link rel="stylesheet" href="style.css" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>
<body>

<table border="0" width="100%" cellspacing="3" cellpadding="0">


<tr>
<ww:text name="'article.title'"/><b><ww:property value="article.title"/></b><br/>
</tr>
<tr valign="top">
<td width=100>
    <ww:text name="'article.createBy'"/><ww:property value="article.createBy.name"/>
    <ww:text name="'article.createTime'"/><ww:property value="article.createTime"/>
    <div>
</td>
<td width="10">
</td>
<td width="1" bgcolor="#69696d">
</td>
<td width="10">
</td>
<td width="" class="doc">
    <ww:text name="'article.body'"/><ww:property value="article.body"/>
    </div>
</td>
</tr>
</table>

    <ww:if test="#session['loginUser']==null">
        <ww:text name="'notLoggedIn'"/>.
    </ww:if>
    <ww:else>
    <form action="vote.action" method="post">

    <div style="border:1 solod">
		<ww:iterator value="article.optionSet" status="status">
            <input type="radio" name="selection" value="<ww:property value="id"/>"
                <ww:if test="#status.first == true ">checked</ww:if>
                ><ww:property value="optionText"/><br>
        </ww:iterator>
    </div>
        <ww:text name="'article.reply'"/>
            <input name="article.title"/>
        <ww:text name="'article.body'"/>
            <textarea name="article.body" rows="10" cols="80"  ></textarea>
        <br/>
        <input type="hidden" name="parentId" value="<ww:property value="article.id"/>"/>
        <input type="submit" value="<ww:text name="'article.reply'"/>"/>
    </form>
    </ww:else>

</body>
</html>
</ww:i18n>
