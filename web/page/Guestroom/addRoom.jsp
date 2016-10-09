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
	<title>addRoom</title>
	
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
    	function checkroomno(){
    		var check=true;
    		var shuzi=/^[0-9]*[1-9][0-9]*$/;
    		var roomnoText=$("#roomno").val();
    		if(shuzi.test(roomnoText)){
				/* var args = {
						"guestroomno" : roomnoText,
				}; */ 
				$.ajax({
					type : "POST",
					url : "GuestroomAction!findRoomno",
					data : {
						"guestroomno" : roomnoText,
					},
					dataType : "text",
					success : function(data) {
						$("#roomnoError").html(data);
						if(data=="客房已存在!"){
							$("#add").attr("disabled",true);
						}
						else{
							$("#add").attr("disabled",false);
						}
					}
				});
			}else if(roomnoText==''){
				$("#roomnoError").html("房间号不能为空");
				$("#add").attr("disabled",true);
				check=false;
			}
			else {
				$("#roomnoError").html("不能包含英文和汉字，请输入正确的房间号");
				$("#add").attr("disabled",true);
				check=false;
			}
    		return check;
    	}
    	function checkroomprice(){
    		var check=false;
    		var shuzi=/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/;
    		var roompriceText=$("#roomprice").val();
    		if(roompriceText==''){
				$("#roompriceError").html("客房价格不能为空");
				check=false;
			}else if(!shuzi.test(roompriceText)){
				window.document.getElementById('roompriceError').innerText='价格格式不正确哦宝宝!';
				check=false;
			}
    		else{
				window.document.getElementById('roompriceError').innerText='';
				check=true;
			}
    		return check;
    	}
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
    		var check1=checkroomno() && checkroomprice() && checkroomtel();
    		if(check1){
    			$("#addSucc").html("添加成功");
				$("#addSucc").fadeOut(3000);
    		}
    		else{
    			$("#addSucc").fadeIn(10);
    			$("#addSucc").html("请完善客房信息！^-^");
				$("#addSucc").fadeOut(3000);
    		}
    		return check1;

    	}
    	/* $(document).ready(function() {//网页加载完毕事件
    		$("#add").click(function(){
    			var roomnoText=$("#roomno").val();
    			var roomtype=$("#roomtype").val();
    			var roompriceText=$("#roomprice").val();
    			var roomtelText=$("#roomtel").val();
    			var roomdiscount=$("#roomdiscount").val();
    			var args={
    					"guestroomno":roomnoText,
    					"guestroomtype":roomtype,
    					"guestroomprice":roompriceText,
    					"guestroomtel":roomtelText,
    					"guestroomdiscount":roomdiscount
    			};
    			$.ajax({
    				type : "POST",
    				url :"GuestroomAction!addGuestroom",
    				data : args,
    				dataType : "json",
    				success : function(data) {
    					var mes = data.message;
    					//alert(mes);
    					$("#addSucc").fadeIn(10);
    					$("#addSucc").html(mes);
    					$("#addSucc").fadeOut(3000);
    					if(mes=="添加成功！"){
    						
    						$("#roomno").val("");
    						$("#roomtype").val("");
    						$("#roomprice").val("");
    						$("#roomtel").val("");
    						$("#roomdiscount").val("");
    						$("#roomnoError").html("");
    					}
    				}
    			});
    		})
    	}); */
    </script>
</head>
<body>
<form action="GuestroomAction!addGuestroom" method="post" onsubmit="return check()">
<table class="table table-bordered table-hover definewidth m10">
    <!-- <tr>
        <td width="10%" class="tableleft" >客房编号</td>
        <td><input type="text" name="guestroom.roomid" readonly="readonly"/></td>
    </tr> -->
    <tr>
        <td class="tableleft"  width="250px">房间号</td>
        <td><input type="text" name="guestroom.roomno" id="roomno" onblur="checkroomno()"/>
        <span style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';" id="roomnoError">
        </span><br/></td>
        
    </tr>
    <tr>
    	<td class="tableleft">房间类型</td>
    	<td>
    		<select name="guestroom.roomtype" id="roomtype">
				<option value="单人间">单人间</option>
				<option value="标间">标间</option>
				<option value="麻将房">麻将房</option>
				<option value="电脑房">电脑房</option>
			</select>
    	</td>
    </tr> 
    <tr>
    	<td class="tableleft">客房价格</td>
    	<td><input type="text" name="guestroom.roomprice" id="roomprice" onblur="checkroomprice()"/>
    	<span style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';" id="roompriceError"> </span><br/>
    	</td>
    </tr>  
   <!--  <tr>
        <td class="tableleft">客房状态</td>
        <td><input type="text" readonly="readonly" value="0" name="guestroom.roomstatus"/></td>
    </tr> -->
    <tr>
    	<td class="tableleft">客房电话</td>
    	<td><input type="text" name="guestroom.roomtel" id="roomtel" onblur="checkroomtel()"/>
    	<span style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';" id="roomtelError"> </span><br/>
   		</td>
    </tr> 
    <tr>
    	<td class="tableleft">客房折扣</td> 
    	<td>
    		<select name="guestroom.roomdiscount" id="roomdiscount">
				<option value="1.0">1.0</option>
				<option value="0.9">0.9</option>
				<option value="0.8">0.8</option>
				<option value="0.7">0.7</option>
			</select>
    	</td> 
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" id="add">保存</button>&nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            <span style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';" id="addSucc"> </span>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="GuestroomAction!showPageHa?nowpage=1";
		 });

    });
</script>
