<%@ taglib prefix="ww" uri="webwork" %>
<%@ page contentType="text/html;charset=utf-8"%>
<ww:i18n name="'messages'">
<html>
	<head>
	<link rel="stylesheet" href="style.css" type="text/css">
	</head>	
<body>
<h3><ww:text name="'board.tree'"/></h3>
<table cellspacing="0" cellpadding="0" border="0">
    <tr>
        <th></th>
    </tr>
    <tr>
        <td>

	<table cellspacing="10" class="listview">
		<ww:iterator value="boards" status="status">
			<tr>
				<td>
				<a target="ArticleTree" href="listArticle.action?boardId=<ww:property value="id"/>">
					<b><ww:property value="name"/></b>
				</a>
                    <ww:iterator value="childBoards" status="status">
                    <li>        <a target="ArticleTree" href="listArticle.action?boardId=<ww:property value="id"/>">
                                <b><ww:property value="name"/></b>
                            </a></li>
            		</ww:iterator>
				</td>
			</tr>
		</ww:iterator>
	</table>

  </td>
    </tr>
</table>


</body>
</html>
</ww:i18n>