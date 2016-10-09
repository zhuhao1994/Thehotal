<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='../../Js/jquery-1.7.2.js'></script>
<title>入住流程</title>
<style>
#parent {
	margin-top: 100px;
	width: 60%;
	margin-left: 10%;
}

#parent1 {
	
}
#mybgImg{
     filter: url(blur.svg#blur); /* FireFox, Chrome, Opera */
        -ms-filter: blur(10px);    
    
    filter: progid:DXImageTransform.Microsoft.Blur(PixelRadius=10, MakeShadow=false); /* IE6~IE9 */
}
#parent20, #parent21 {
	padding: 5px;
	text-align: center;
	background-color: #e5eecc;
	border: solid 1px #c3c3c3;
}

#parent21 {
	display: none;
	padding: 50px
}
</style>

<script>
	$(document).ready(function() {
		$("#parent20").click(function() {
			$("#parent21").slideToggle("slow");
		});
	});
</script>
</head>
<body>
	<div id="Layer1"
		style="position: absolute; width: 100%; height: 100%; z-index: -1">
		<img src="../../Images/checkInRoom.jpg" height="100%" width="100%" id="mybgImg" />
	</div>
	<div style="margin-left: 15%">
		<jsp:include page="RoomMenu.jsp" />
	</div>
	<!-- 
		页面设计：
		       入住：（以折叠的方式）
		       1.客户：(1)查询客户  (2)录入客户 (3)客户充值
			   2.客房 : (1)显示客房分布状态 (4)点击后显示：客房信息  + 押金
			   3.客户 《-交互-》 客房生成入住记录
	 -->
	 
</body>
</html>