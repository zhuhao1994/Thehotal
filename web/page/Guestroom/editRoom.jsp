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
	<title>editRoom</title>
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
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

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }


    </style>
    <script type="text/javascript">

	function checkroomtel(){
   		var check=false;
		var tel=/^[123456789][123456789]\d{5}$/;
		var roomtelText=$("#roomtel").val();
		if(roomtelText==''){
			$("#roomtelError").html("客房电话不能为空");
			check=false;
		}else if(!tel.test(roomtelText)){
			window.document.getElementById('roomtelError').innerText='请输入七位数的电话号码!';
			check=false;
		}
		else{
			window.document.getElementById('roomtelError').innerText='';
			check=true;
		}
		return check;
	}
	function check(){
		var check1= false; 
		var check1=checkroomprice() && checkroomtel();
		if(check1){
			window.document.getElementById('updateSucc').innerText='添加成功 ';
			$("#updateSucc").fadeOut(3000);
		}
		else{
			$("#updateSucc").fadeIn(10);
			$("#updateSucc").html("请完善客房信息！^-^");
			$("#updateSucc").fadeOut(3000);
		}
		return check1;

	}
    </script>
</head>
<form action="GuestroomAction!updateGuestroom" method="post" onsubmit="return check()">
<table class="table table-bordered table-hover definewidth m10" >
    <tr style="display: none">
        <!-- <td class="tableleft">客房编号</td> -->
        <td><input type="hidden" name="guestroom.roomid" readonly="readonly" value="${requestScope.editRoom.roomid}"/>
        </td>
    </tr>
    <tr>
        <td class="tableleft"  width="250px">房间号</td>
        <td><input type="text" name="guestroom.roomno" id="roomno" value="${requestScope.editRoom.roomno}" readonly="readonly"/>
        <span style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';" id="roomnoError"> </span><br/>
        </td>
    </tr>
    <tr>
    	<td class="tableleft">房间类型</td>
    	<td>
    		<select id="${requestScope.editRoom.roomtype }" class="sel1" name="guestroom.roomtype">
				<option value="单人间" id="op1">单人间</option>
				<option value="标间" id="op2">标间</option>
				<option value="麻将房" id="op3">麻将房</option>
				<option value="电脑房" id="op4">电脑房</option>
			</select>
    	</td>
    </tr> 
    <tr>
    	<td class="tableleft">客房价格</td>
    	<td><input type="text" name="guestroom.roomprice" id="roomprice" value="${requestScope.editRoom.roomprice}" onblur="checkroomprice()"/>
    	<span style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';" id="roompriceError"> </span><br/>
    	</td>
    </tr>  
   <!--  <tr>
        <td class="tableleft">客房状态</td>
        <td><input type="text" readonly="readonly" value="空房"/></td>
    </tr> -->
    <tr>
    	<td class="tableleft">客房电话</td>
    	<td><input type="text" name="guestroom.roomtel" id="roomtel" value="${requestScope.editRoom.roomtel}" onblur="checkroomtel()"/>
    	<span style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';" id="roomtelError"> </span><br/>
    	</td>
    </tr> 
    <tr>
    	<td class="tableleft">客房折扣</td> 
    	<td>
    		<select id="${requestScope.editRoom.roomdiscount}" class="sel2" name="guestroom.roomdiscount">
				<option value="1.0" id="op5">1.0</option>
				<option value="0.9" id="op6">0.9</option>
				<option value="0.8" id="op7">0.8</option>
				<option value="0.7" id="op8">0.7</option>
			</select>
    	</td> 
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" id="update">保存</button>&nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            <nav id="mes">${requestScope.updateCusLog}</nav>
            <span style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';" id="updateSucc"> </span>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>
$("#mes").fadeOut(3000);
	$(document).ready(function(){
		var type=$(".sel1").attr("id");
		//alert(type == "单人间");
		if(type=="单人间"){
			$("#op1").attr("selected","selected");
		}else if(type == "标间"){
			 $("#op2").attr("selected","selected"); 
		}else if(type == "麻将房"){
			$("#op3").attr("selected","selected");
		}else if(type == "电脑房"){
			$("#op4").attr("selected","selected");
		} 
	});

	$(document).ready(function(){
		var discount=$(".sel2").attr("id");
		//alert(type == "单人间");
		if(discount=="1.0"){
			$("#op5").attr("selected","selected");
		}else if(discount == "0.9"){
			$("#op6").attr("selected","selected"); 
		}else if(discount == "0.8"){
			$("#op7").attr("selected","selected");
		}else if(discount == "0.7"){
			$("#op8").attr("selected","selected");
		} 
	});
    $(function () {       
		$('#backid').click(function(){
				window.location.href="GuestroomAction!showPageHa?nowpage=1";
		 });
		
    });
</script>