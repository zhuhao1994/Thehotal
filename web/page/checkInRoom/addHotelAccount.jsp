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
<script>
	$(function() {
		var mydate = new Date();
		var mdyear = mydate.getFullYear(); 
		var mdmonth = mydate.getMonth() + 1;
		var months = new Array();
		temp =0;
		for(m = 0;m < 13;m++)
		 {
			 months[m] = (m+1)+"月";
		 }
		
		$(".datepicker").datepicker(
				{
					changeMonth : true,
					changeYear : true,
					dateFormat : "yy/mm/dd",
					yearRange : '$mdyear:$mdyear',
					monthNamesShort : months,
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
	<form action="roomStatusAction!createHA" method="post"
		class="definewidth m20">

		<table class="table table-bordered table-hover m10">
			<tr>
				<td class="tableleft">◕‿◕员工姓名</td>
				<td>
					<input type="text" name="userid" value='${loginUser.username}' readOnly="true"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">◕‿-客户姓名</td>
				<td>
					<input type="text" name="cusname" value='${sessionScope.chosedCusName}' readOnly="true" />
				</td>
			</tr>
			<tr>
				<td class="tableleft">♟客房号</td>
				<td>
					<input type="text" name="roomno" value='${sessionScope.chosedRoomNo}' readOnly="true"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">¥押金</td>
				<td>
					<input type="text" name="desposit" />
				</td>
			</tr>
			<tr>
				<td class="tableleft">入住时间</td>
				<td><input type="text" name="comeinTime" value="" class="datepicker"></td>
			</tr>
			<tr>
				<td class="tableleft">离开时间</td>
				<td><input type="text" name="outTime" value="" class="datepicker"></td>
			</tr>
			<tr style="display:none">
				<td class="tableleft">☏联系电话</td>
				<td><input type="text" name="userNumber" /></td>
			</tr>
			<tr>
				<td class="tableleft">✍备注</td>
				<td><input type="text" name="remark" value=""  ></td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid"
						id="backid"><a style="text-decoration:none;color:white" href="page/checkInRoom/checkInRoom.jsp">返回主菜单</a></button>
					<nav id="log"> ${requestScope.createHA}</nav>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<script>
	
		if('${requestScope.createHA}' =='恭喜,入住成功！')
		{
			var re = confirm("是否回到主页！？");
			if(re ==true)
			{
				window.location.href="page/checkInRoom/checkInRoom.jsp";
			}else
			{
				$("#log").fadeOut(3000);
			}
			
		}
		$('#backid').click(function() {
			window.location.href = "CustomerAction!shoaPageHa?nowpage=1";
		});

</script>