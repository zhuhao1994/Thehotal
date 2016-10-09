<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="Css/style.css" />
<link rel="stylesheet" type="text/css" href="Css/jquery-ui.min.css">
<script type="text/javascript" src="Js/jquery.js"></script>
<script type="text/javascript" src="Js/jquery.sorted.js"></script>
<script type="text/javascript" src="Js/bootstrap.js"></script>
<script type="text/javascript" src="Js/ckform.js"></script>
<script type="text/javascript" src="Js/common.js"></script>
<script type="text/javascript" src="theJs/jquery.min.js"></script>
<script type="text/javascript" src="theJs/jquery-ui.min.js"></script>





<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
<body>

	<div align="center">
		<form action="CustomerAction!updateBalance" method="post"
			class="definewidth m20">
			<table class="table table-bordered table-hover m10">
				<tr>
					<td class="tableleft" width="90px">手机号码</td>
					<td><input type="text" id="tel" name="custel" onblur="ch()">
						<span id="na"></span></td>
				</tr>
				<tr>
					<td class="tableleft" width="90px">充值金额</td>
					<td><input type="text" id="ba" name="balance"></td>
				</tr>
				<tr>
					<td class="tableleft" width="90px"></td>
					<td>
						<button type="button" id="sub" class="btn btn-primary">充值</button>
						<span id="mes"></span>
					</td>

				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {//网页加载完毕事件

		$("#sub").click(function() {
			var custel = $("#tel").val();
			var ba = $("#ba").val();
			var args = {
				"custel" : custel,
				"balance" : ba
			};
			$.ajax({
				type : "POST",
				url : "CustomerAction!updateBalance",
				data : args,
				dataType : "json",
				success : function(data) {
					var mes = data.message;

					$("#mes").fadeIn(10);
					$("#mes").html(mes);
					$("#mes").fadeOut(3000);
					if (mes == "充值成功！") {
						$("#tel").val("");
						$("#na").html("");
						$("#ba").val("");
					}
				},
				error : function() {
					alert("555");
				}
			});

		})

	});

	function ch() {
		var tel = $("#tel").val();
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;

		if (reg.test(tel)) {
			var args = {
				"custel" : tel,
				"time" : new Date()
			};
			$.ajax({
				type : "POST",
				url : "CustomerAction!findCustomerByTel",
				data : args,
				dataType : "json",
				success : function(data) {
					var name = data.cusname;
					$("#na").html(name);
				}
			});
		} else {
			$("#na").html("请输入正确的电话号码");

		}
	}
</script>
</html>