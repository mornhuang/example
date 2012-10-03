<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
		<script language="javascript">
			function checkValid( form )
			{
				// 用户输入合法性检查
				
				document.getElementById( "submitbutton" ).disabled = true;
				return true;
			}
		</script>
	</head>
	<BODY>
		<form method="post" onsubmit="return checkValid( this );">
			<INPUT type="submit" id="submitbutton" value="Submit">
		</form>
	</BODY>
</html>