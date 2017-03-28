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
a{font-size:1.5em;}
</style>
<script type="text/javascript">
</script>
<style>

</style>
</head>
<body>
<div class="out">
		<table>
			<tr>
				<td>运营商选择</td>
			</tr>
			<tr>
				<td><a href="<%=bathPath %>/unicomlogin.jsp" >联&nbsp;&nbsp;通</a></td>
			</tr>
			<tr>
				<td><a href="<%=bathPath %>/chinamobile/index.do" >移&nbsp;&nbsp;动</a></td>
			</tr>
		</table>
</div>
</body>
</html>