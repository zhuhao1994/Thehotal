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
<script type="text/javascript" src="theJs/jquery.min.js"></script>
<script type="text/javascript" src="theJs/jquery-ui.min.js"></script>
<script type="text/javascript">

	function tel() {
		var check=false;
		$("#telmes").html("");
		var rename = $("#cusname").val();
		var retel = $("#retel").val();
		var tel = $("#custel").val();
		
		var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
		if (tel == "") {
			$("#telmes").html("请输入电话号码！");
			check=false;
		} else if (reg.test(tel)) {
			var args={
					"custel":tel,
			};
			
			$.ajax({
				type : "POST",
				url : "CustomerAction!findCustomerByTel",
				data :args,
				dataType : "json",
				async:false,
				success : function(data) {
					var name = data.cusname;
					var custel = data.custel;
					if(name=="用户不存在!"){
						$("#telmes").html("");
						check=true;
					}else if(name==rename){
						$("#telmes").html("");
						check=true;
					}else{
						$("#telmes").html("此手机号码已存在！");
						check=false;
					}
				}
			});
		
		} else {
			
			$("#telmes").html("请输入正确的电话号码！");
			check=false;
			
		}
		
		if(check==false){
			$("#update").attr("disabled",true);
		}else{
			$("#update").attr("disabled",false);
		}
	}
</script>

<script>
	$(function() {
		$("#datepicker").datepicker(
				{
					changeMonth : true,
					changeYear : true,
					dateFormat : "yy/mm/dd",
					yearRange : '1940:2016',
					monthNamesShort : [ '1月', '2月', '3月', '4月', '5月', '6月',
							'7月', '8月', '9月', '10月', '11月', '12月' ],
					dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ]
				});
	});
</script>

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
	<form action="CustomerAction!updateCustomer" method="post"
		class="definewidth m20">
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
					'checked'":''}" readonly="readonly" /> 女 
					
			<input	type="hidden" name="status" value="${requestScope.c.status}" /> 
			<input type="hidden" name="vid" value="${requestScope.c.vip.vid}" /></td>

			</tr>

			<tr>
				<td class="tableleft">身份证号</td>
				<td><input type="text" name="cardid"
					value="${requestScope.c.cardid}" readonly="readonly" /></td>
			</tr>

			<tr>
				<td class="tableleft">出生年月</td>
				<td><input type="text" id="datepicker" name="cusbirth"
					value="${requestScope.c.cusbirth}" readonly="readonly"/>
					 <input type="hidden"	id="retel" value="${requestScope.c.custel}" /></td>
			</tr>

			<tr>
				<td class="tableleft">联系电话</td>
				<td><input type="text" name="custel" id="custel" onblur="tel()"
					value="${requestScope.c.custel}" /> <span id="telmes"></span></td>
			</tr>
		
			<tr>
				<td class="tableleft">所在地址</td>
				<td><input type="text" name="address"
					value="${requestScope.c.address }" readonly="readonly">
					<input type="hidden" name="balance"
					value="${requestScope.c.balance}" readonly="readonly" /></td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" id="update">保存</button>
					<nav id="mes">${requestScope.updateCusLog}</nav>
				</td>
			</tr>
		</table>
	</form>
<script>
	$("#mes").fadeOut(3000);
</script>
</body>
</html>