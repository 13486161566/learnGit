<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%String bathPath = request.getContextPath(); %>
<title>登录页</title>
<style type="text/css">
.out{margin:10% auto auto auto;width:30%;heigth:30%}
</style>
<script type="text/javascript">
</script>
<style>

</style>
</head>
<body>
<div class="out">
	<form action="<%=bathPath %>/unicom/login.do" method="post">
		<table>
			<tr>
				<td>手机号：</td>
				<td><input name="phone" type="text" placeholder="请输入手机号"/></td>
			</tr>
			<tr>
				<td>服务密码：</td>
				<td><input name="fuwumima" type="text" placeholder="请输入服务密码"/></td>
			</tr>
			<tr>
				<td></td>
				<td><input value="提交" type="submit"/></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>