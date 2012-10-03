<%@ taglib prefix="ww" uri="webwork" %>
<%@ page contentType="text/html;charset=utf-8"%>
<ww:i18n name="'messages'">
<html>
<body>
	<head>
	<link rel="stylesheet" href="style.css" type="text/css">
	</head>
<table cellspacing="0" cellpadding="0" border="0">
    <tr align='left'>
        <th><ww:property value="board.name"/>
        <ww:if test="#session['loginUser']!=null">
            [<a href="#" onclick="window.open('newArticle.jsp?board=<ww:property value="board.id"/>','ArticleDetail');return false;"><ww:text name="'new.article'"/></a>]&nbsp;
            [<a href="#" onclick="window.open('newVote.jsp?board=<ww:property value="board.id"/>','ArticleDetail');return false;"><ww:text name="'new.vote'"/></a>]
        </ww:if>
        </th>
    </tr>
    <tr>
        <td>
	<table cellspacing="10" class="listview">
		<ww:iterator value="Articles" status="status">
        <li><span style="color:gray"><ww:property value="treeIndex"/></span>
        <ww:if test=" class.name=='com.redsaga.hibernatesample.step3.Vote'">
            [<ww:text name="'POLL'"/>]
        </ww:if>
            <a target="ArticleDetail" href="showArticle.action?articleId=<ww:property value="id"/>">
					<b><ww:property value="title"/></b>&nbsp;(<ww:property value="createBy.name"/>)
				</a>
        <ww:if test=" class.name=='com.redsaga.hibernatesample.step3.Vote'">
            &nbsp;[<a target="ArticleDetail" href="voteResult.action?articleId=<ww:property value="id"/>"><ww:text name="'poll.result'"/></a>]

        </ww:if>
        </li>
		</ww:iterator>
	</table>

  </td>
    </tr>
</table>

</body>
</html>
</ww:i18n>