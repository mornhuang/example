<%@ taglib prefix="ww" uri="webwork" %>
<%@ page contentType="text/html;charset=utf-8"%>
<ww:i18n name="'messages'">

<html>
	<head>
	<link rel="stylesheet" href="style.css" type="text/css">
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

  <table>
    <ww:iterator value="article.optionSet" status="status">
        <tr>
        <td>
        <ww:property value="optionText"/>,<ww:text name="'vote.hits'"/> :<ww:property value="agreeNumber"/>
        </td>
        <td>
            <table>
                <tr>
                    <td width="<ww:property value="agreeNumber*10"/>px" style="background-color:blue">&nbsp;</td>
                    <td width="*" ></td>
                </tr>
            </table>
        </td>
    </ww:iterator>
  </table>

</body>
</html>
</ww:i18n>
