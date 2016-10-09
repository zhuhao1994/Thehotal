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
	<form action="HotelAction2!checkOutMyaccount" method="post"
		class="definewidth m20">

		<table class="table table-bordered table-hover m10">
			<tr>
				<td class="tableleft">员工姓名</td>
				<td>
					<input type="text" name="userid" value='${loginUser.username}' readOnly="true"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">客户姓名</td>
				<td>
					<input type="text" name="cusname" value='${requestScope.myAccount.customer.cusname}' readOnly="true" />
				</td>
			</tr>
			<tr>
				<td class="tableleft">客房号</td>
				<td>
					<input type="text" name="roomno" value='${requestScope.myAccount.guestroom.roomno}' readOnly="true"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">客房类别</td>
				<td>
					<input type="text" name="roomType" value='${requestScope.myAccount.guestroom.roomtype}' readOnly="true"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">客房单价</td>
				<td>
					<input type="text" id="theroomPrice" name="roomPrice" value='${requestScope.myAccount.guestroom.roomprice}' readOnly="true"/>
				</td>
			</tr>
			<tr>
				<td class="tableleft">客户押金</td>
				<td>
					<input type="text" name="desposit" value='${requestScope.myAccount.deposit}' readOnly="true"/>
				</td>
			</tr>
			<tr style="display:none">
				<td class="tableleft">客户电话</td>
				<td><input type="text" name="userNumber"  value='${requestScope.myAccount.customer.custel}'/></td>
			</tr>
			<tr>
				<td class="tableleft">入住时间</td>
				<td><input type="text" id="comeinTime" name="comeinTime" value="${requestScope.myAccount.cometime}"  readOnly="true"></td>
			</tr>
			<tr>
				<td class="tableleft">离开时间</td>
				<td><input type="text" id="outTime" name="outTime" value="" class="datepicker" readOnly="true"></td>
			</tr>
		
			<tr>
				<td class="tableleft">备注</td>
				<td><input type="text" name="remark" value="${requestScope.myAccount.remarks}"  ></td>
			</tr>
			<tr>
				<td class="tableleft">住房消费¥</td>
				<td><input type="text" id="roomConsume" name="roomConsume" value=""   readOnly="true"></td>
			</tr>
			<tr>
				<td class="tableleft">物品消费¥</td>
				<td>
					 <c:if test="${requestScope.goodstatus=='yes'}">
					     	 <input id="goodscms" name="goodscms" type="text" value='${requestScope.sumPrice}'></input>
					     	  </c:if>
					   <c:if test="${requestScope.goodstatus!='yes'}">
					 	 <a style="text-decoration:none;" href="GoodsAction!showpageGoods?nowpage=1&thepath=goodsPath">
					 		<input type="button" id ="goodsConsume" name="goodsConsume" value="结算商品"  class="btn btn-primary" />
				  		 </a>
				  	  </c:if>
				</td>
			</tr>
			<!--  <div id="showGoodsConsume" style="position:absolute;left:15%;top:5%;z-index:1;width:500px;heigh:500px">
			</div>-->
			<tr>
				<td class="tableleft">总消费¥</td>
				<td><input type="text" name="allConsume" id="allConsume" value=""  readOnly="true"></td>
			</tr>
			<tr>
				<td class="tableleft">折扣</td>
				<td><input type="text" id="discount" name="discount" value="${requestScope.myAccount.customer.vip.vdiscount}" readOnly="true"></td>
			</tr>
			<tr>
				<td class="tableleft">实际消费¥</td>
				<td><input type="text" id="nowConsume" name="nowConsume" value="" /></td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary">保存</button>&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid"
						id="backid"><a style="text-decoration:none;color:white" href="page/checkInRoom/checkInRoom.jsp">返回主菜单</a></button>
					<nav id='logha'> ${requestScope.createHAsuccess}</nav>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<script>

/*当前时间*/
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var strHours = date.getHours();
    var strMinutes = date.getMinutes();
    var strSeconds = date.getSeconds();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (strHours >= 0 && strHours <= 9) {
    	strHours = "0" + strHours;
    }
    if (strMinutes >= 0 && strMinutes <= 9) {
    	strMinutes = "0" + strMinutes;
    }
    if (strSeconds >= 0 && strSeconds <= 9) {
    	strSeconds = "0" + strSeconds;
    }
    var currentdate =date.getFullYear()+seperator1 + month + seperator1 + strDate
            + " " + strHours + seperator2 + strMinutes
            + seperator2 + strSeconds;
    return currentdate;
} 
var outtime = getNowFormatDate();
$("#outTime").val(outtime);
//********计算时间：住房消费*******
	var comeinTime =  $("#comeinTime").val();
	comeinTime = comeinTime.substring(0,comeinTime.length-2)
	var AfterTime1= new Date(comeinTime.replace(/-/ig,'/'));
	
	console.log("22")
	var nowTime = $("#outTime").val()
	var AfterTime2= new Date(nowTime.replace(/-/ig,'/'));
	var thetiem = "煮点时间："+AfterTime1+ "  离开时间 ："+ AfterTime2;
	var day3 =  AfterTime2.getTime() - AfterTime1.getTime();
	var days=Math.floor(day3/(24*3600*1000));//住房时间长
	//
	var roomprice = $("#theroomPrice").val();
	$("#roomConsume").val(days * roomprice + ".0");
	//商品消费
	var  goodscms = $("#goodscms").val();
	console.log("商品消费："+goodscms)
	if( !isNaN(goodscms))
	{
		var allConsume = parseFloat(days * roomprice) +parseFloat(goodscms) ;
		$("#allConsume").val(allConsume+".0");//总消费
		 var discount=parseFloat($("#discount").val());
		 var nowConsume = allConsume *discount;
		$("#nowConsume").val(nowConsume.toFixed(2))
		console.log("实际消费："+nowConsume)
	}
	//
	
//********计算时间：总消费*******
if('${requestScope.createHAsuccess}' == '消费添加成功！'){
	var re = confirm("消费成功！，是否跳转主页！");
	if(re ==true)
	{
		window.location.href="page/checkInRoom/checkInRoom.jsp";
	}else
	{
		$("#logha").fadeOut(3000);
	}
	
}
 
</script>