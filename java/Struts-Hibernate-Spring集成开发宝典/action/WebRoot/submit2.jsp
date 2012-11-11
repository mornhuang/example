<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
	<head>
		<script language="javascript">
			var submited = false;
			function checkValid( form )
			{
				if( submited ) return false;
				
				// 用户输入合法性检查
				
				submited = true;
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