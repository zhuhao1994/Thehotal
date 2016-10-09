<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="Css/style.css" />
<script type="text/javascript" src="Js/jquery.js"></script>
<script type="text/javascript" src="Js/jquery.sorted.js"></script>
<script type="text/javascript" src="Js/bootstrap.js"></script>
<script type="text/javascript" src="Js/ckform.js"></script>
<script type="text/javascript" src="Js/common.js"></script>
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
	background-image: url("Images/checkInRoom.jpg");
	width:100%;
	height:100%;
	background-size:cover;
	
}

.form-signin {
	max-width: 300px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin input[type="text"], .form-signin input[type="password"] {
	font-size: 16px;
	height: auto;
	margin-bottom: 15px;
	padding: 7px 9px;
}
</style>
</head>

<body>
	<div class="container" style="margin-top:150px" >
		<form class="form-signin" action="${pageContext.request.contextPath}/loginAction!loginCheck"  method="post" >
			<h2 class="form-signin-heading">管理系统</h2>
			<input type="text" name="username" class="input-block-level" placeholder="账号" value="王龙"/> 
			<input type="password" name="password"  class="input-block-level" placeholder="密码" value="123456"/>
			
			<p>
				<button class="btn btn-large btn-primary" type="submit" style="float:left;margin-right: 100px">登录</button>
				<h4 style="clear:left" class="form-signin-heading" >${requestScope.failLog}</h4>
			</p>
			
		</form>

	</div>
</body>
</html>
<script>
$(function () {       
	$('#register').click(function(){
			window.location.href="page/checkInRoom/register.jsp";
	 });

});
</script>
