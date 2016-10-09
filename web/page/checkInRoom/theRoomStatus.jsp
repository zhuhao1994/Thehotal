<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>客房---状态</title>
<link rel="stylesheet" type="text/css" href="Css/normalize.css" />
<script src='Js/prefixfree.min.js'></script>
<script src='Js/jquery-1.7.2.js'></script>
<script src='Js/Guestomshow.js'></script>
<style type="text/css">
body{
	background-image: url("Images/checkInRoom.jpg");
	width:100%;
	height:100%;
}
#choseFloor,#thechose,.btn{
	border-radius: 8px;
	font-size: 1em;
	height: 32px;
	font-weight: 800;
 	background-color:#000000;/* IE6和部分IE7内核的浏览器(如QQ浏览器)下颜色被覆盖 */
 	 background-color:rgba(0,0,0,0.2); /* IE6和部分IE7内核的浏览器(如QQ浏览器)会读懂，但解析为透明 */
	color: white;
}
</style>

</head>

<body onselectstart="return false;">
	<div class="htmleaf-container"  >
		<header class="htmleaf-header">
		<h1>
			尚酷客栈<span>馨阳酒店管理系统</span>
		</h1>
		</header>
		<section>
		<div>
			<h3>客房入住状态</h3>
			<!-- 查询出不同的颜色：预定，入住，可用-->
			<nobr>
				<select id="thechose">
					<option style="background-color: #357568;">全部</option>
					<option style="background-color: #157DBA;">入住</option>
					<option style="background-color: #398F1E;">空闲</option>
				</select>
				<select id="choseFloor">
					<option style="background-color: #357568;">整栋</option>
					<option style="background-color: #357568;">一楼</option>
					<option style="background-color: #357568;">二楼</option>
					<option style="background-color: #157DBA;">三楼</option>
					<option style="background-color: #398F1E;">四楼</option>
					<option style="background-color: #398F1E;">五楼</option>
					<option style="background-color: #398F1E;">六楼</option>
					<option style="background-color: #398F1E;">七楼</option>
				</select>
				<c:if test="${sessionScope.thePath=='roomMenu'}">
				 	<button class="btn"   ><a  style="color:white" href="CustomerAction!shoaPageHa?nowpage=1&thePath=roomMenu">《返回上一级</a></button>
				 	<button class="btn"  ><a   style="color:white" href="roomStatusAction!addHotelAccount">交付押金</a></button>
				 	<button class="btn" ><a   style="color:white" href="page/checkInRoom/checkInRoom.jsp">主菜单</a></button>
				</c:if>
			</nobr>
		</div>
		
		<section  >
				<c:forEach var="room" items="${requestScope.theallrooms}">
						<span class="zocial-kf${room.roomno}" >
							<nav>${room.roomno}<br>${room.roomtype}<br><br>
								<nav class="thePrice" style="display:none">RMB${room.roomprice}</nav>
						    </nav>
							<nav id="theroomid" style="display:none">${room.roomid}</nav>
							</span>
						<script>
						$(".zocial-kf"+"${room.roomno}")
						 $(".zocial-kf"+"${room.roomno}").hover(function(){
							 var theContent = "<nav>${room.roomno}<br>${room.roomtype}</nav><br>RMB${room.roomprice}";
							 $(".zocial-kf${room.roomno}").children().find(".thePrice").toggle();
							 
						 });
						</script>
						<c:if test="${!(room.roomstatus == 1)}">
							<script>
									$(".zocial-kf"+"${room.roomno}").css("background-color","#4BBB28");
							</script>
						</c:if>
						 <c:if test="${!(room.roomstatus == 0)}">
							<script>
									$(".zocial-kf"+"${room.roomno}").css("background-color","#1CA3F3");
							</script>
						</c:if>
				</c:forEach>
		</section>
	</div>
<script  type="text/javascript">
$("span").click(function () { 
	var roomid = $(this).find("#theroomid").text();
	var roomno = $(this).attr('class');
	if($(this).css("background-color")=='rgb(28, 163, 243)')
		alert("该房间已被占用，请重新选择")
	else
	{
		roomno = roomno.substring(roomno.length-3,roomno.length);
		$.ajax({
			type:"POST",
			dataType:"text",
			url:"roomStatusAction!choseGustRoom",
			data:{
				roomId:roomid,
				roomNo:roomno
			},
			success:function(data1,data2){
				alert("已选择房间 :" + data1)
			},
			error:function(){
				alert("error!")
			}
		})
	}
});
</script>
</body>
</html>