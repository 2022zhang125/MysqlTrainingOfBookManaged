<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>图书登录页面</title>
</head>
<script type="text/javascript">
	function reset(){
		document.getElementsByName("username")[0].value = "";
		document.getElementsByName("password")[0].value = "";
	}
	function register(){
		location.href = "mysqltraining/register";
	}
</script>
<body>
<h1>欢迎来到图书登录页面</h1>
<hr>
<form action="/mysqltraining/login" method="post">
	<table border="1px" height="50%">
		<tr>
			<td align="left">
				用户名：<input type="text" name="username" class="username"/><br>
				密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="password" class="password"/><br><br>
				<input type="button" value="重置" style="margin-right: 20px; margin-left: 20px;" onclick="reset()">
				<input type="submit" value="登录" style="margin-right: 30px; margin-left: 20px;">
				<input type="button" value="注册" style="margin-right: 10px; margin-left: 10px;" onclick="register()">
			</td>
		</tr>
	</table>
</form>
</body>
</html>

