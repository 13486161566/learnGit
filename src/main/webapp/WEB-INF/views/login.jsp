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
	function chgimg(){
		var sf = document.getElementById("locale").value;
		changeImg(sf);
	};
	
	function load(){
		console.log(new Date().getTime());
		chgimg();
	}
	
	function changeImg(sf){
		tmp = new Date().getTime();
		document.getElementById("imgcode").src="<%=bathPath%>/chinamobile/ImgDisp.do?sf="+sf+"&tmp="+tmp;
	};
	
	function localeChange(obj){
		changeImg(obj);
	};
</script>
<style>

</style>
</head>
<body onload="load()">
<div class="out">
	<form action="<%=bathPath%>/chinamobile/login.do" method="post">
		<table>
			<tr>
				<td>手机号：</td>
				<td>
					<input name="phone" type="text" placeholder="请输入手机号"/>
					<select name="locale" id="locale" onchange="localeChange(this.value)">
						<option selected value="cq">重庆</option>
						<option value="bj">北京</option>
						<option value="gd">广东</option>
						<option value="sh">上海</option>
						<option value="tj">天津</option>
						<option value="nl">辽宁</option>
						<option value="jx">江苏</option>
						<option value="hb">河北</option>
						<option value="sc">四川</option>
						<option value="sx1">陕西</option>
						<option value="sx2">山西</option>
						<option value="hn">河南</option>
						<option value="jl">吉林</option>
						<option value="hl">黑龙江</option>
						<option value="nm">内蒙古</option>
						<option value="sd">山东</option>
						<option value="ah">安徽</option>
						<option value="hn">湖南</option>
						<option value="gx">广西</option>
						<option value="jx">江西</option>
						<option value="gz">贵州</option>
						<option value="yn">云南</option>
						<option value="xz">西藏</option>
						<option value="gs">甘肃</option>
						<option value="nx">宁夏</option>
						<option value="zj">浙江</option>
						<option value="fj">福建</option>
						<option value="hn">海南</option>
						<option value="hb">湖北</option>
						<option value="qh">青海</option>
						<option value="xj">新疆</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>服务密码：</td>
				<td><input name="fuwumima" type="text" placeholder="请输入服务密码"/></td>
			</tr>
			<tr style="heigth:45px">
				<td>图片验证码：</td>
				<td><input name="validcode" type="text"/><img onclick="chgimg()" id="imgcode" style="width:110px;heigth:55px;" src="ImgDisp.do"/></td>
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