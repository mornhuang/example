<%@ taglib prefix="ww" uri="webwork" %>
<%@ page contentType="text/html;charset=utf-8"%>
<ww:i18n name="'messages'">
	<head>
	<link rel="stylesheet" href="style.css" type="text/css">
	</head>

<ww:text name="'new.article.success'"/>
<script>
var frm = window.parent.frames;
for (i=0; i < frm.length; i++)
    if (frm(i).name=="ArticleTree")
        frm(i).location.reload();

</script>
</ww:i18n>