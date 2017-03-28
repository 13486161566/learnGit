<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%String bathPth = request.getContextPath();%>
<title>获取通话记录二次验证</title>
<style type="text/css">
.out{margin:10% auto auto auto;width:30%;heigth:30%}
</style>
</head>
<body>
<div class="out">
	<form action="<%=bathPth %>/chinamobile/crawlInfo.do" method="post">
		<table>
			<tr>
				<td>手机号：</td>
				<td><input name="validateCode" type="text" placeholder="请输手机验证码"/></td>
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