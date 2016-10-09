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
	<table class="table table-bordered table-hover m10">
		<tr>
			<td class="tableleft" width="90px">客户姓名</td>
			<td><input type="text" name="cusname" id="cusname"
				value="${requestScope.c.cusname}" readonly="readonly" /> <input
				type="hidden" name="cusid" value="${requestScope.c.cusid}"></td>
		</tr>

		<tr>
			<td class="tableleft">客户性别</td>
			<td><input type="radio" name="cussex" value="男"
				checked="${requestScope.c.cussex eq '男'?"
				'checked'":''}"  readonly="readonly" /> 男 <input type="radio"
				name="cussex" value="女" checked="${requestScope.c.cussex eq '女'?"
				'checked'":''}" readonly="readonly" /> 女 <input type="hidden"
				name="status" value="${requestScope.c.status}" /> <input
				type="hidden" name="vid" value="${requestScope.c.vip.vid}" /></td>

		</tr>

		<tr>
			<td class="tableleft">身份证号</td>
			<td><input type="text" name="cardid"
				value="${requestScope.c.cardid}" readonly="readonly" /></td>
		</tr>

		<tr>
			<td class="tableleft">出生年月</td>
			<td><input type="text" id="datepicker" name="cusbirth"
				value="${requestScope.c.cusbirth}" readonly="readonly" />
		</tr>

		<tr>
			<td class="tableleft">联系电话</td>
			<td><input type="text" name="custel" id="custel"
				value="${requestScope.c.custel}"  readonly="readonly"/></td>
		</tr>

		<tr>
			<td class="tableleft">所在地址</td>
			<td><input type="text" name="address"
				value="${requestScope.c.address }" readonly="readonly"> <input
				type="hidden" name="balance" value="${requestScope.c.balance}"
				readonly="readonly" /></td>
		</tr>
		<tr>
			<td class="tableleft"></td>
			<td>
				<button type="button" class="btn btn-primary" id="update">修改</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button> 
				<nav id="mes">${requestScope.updateCusLog}</nav>
			</td>
		</tr>

	</table>
</body>
</html>

<script>
$("#mes").fadeOut(3000);
    $(function () {       
		$('#backid').click(function(){
				window.location.href="CustomerAction!shoaPageHa?nowpage=1";
		 });
		$('#update').click(function(){
			window.location.href="CustomerAction!loadCustomer?cusid=${requestScope.c.cusid}";
		
		})

    });
</script>