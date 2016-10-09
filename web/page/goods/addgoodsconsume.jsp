<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%--  <%@ taglib prefix ="s" uri="/struts-tags"%> s标签引用 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<base href="<%=basePath%>">
    <title></title>
    <meta charset="UTF-8">
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
</head>
<body>

	<form action="GoodsConsumeAction!addgoodsume" name="add" method="post" onsubmit="return check()" class="definewidth m20">
<table class="table table-bordered table-hover m10">
    <tr>
        <td class="tableleft" width="250px">房间号</td>
        <td><input type="text" id="room" name="goodsconxume.guestroom.roomno" onblur="checkroom()" />
        <span id="checktext1" style="color:red;"></span>
        </td>
    </tr>
   
    <tr>
        <td   class="tableleft">商品名称</td>
        <td><input id="gdsname" type="text" name="goodsconxume.goods.goodsname" onblur="checkid()"/>
        <span id="checktext2" style="color:red;"></span>
        </td>
    </tr>
    
     <tr>
        <td class="tableleft">数量</td>
        <td><input type="text" id="gdscount" name="goodsconxume.goodsnum" onblur="checkcount()"/>
        <span id="checktext3" style="color:red;"></span>
        </td>
    </tr>
  <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" id="tj" class="btn btn-primary">添加</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            <span id="checktext4" style="color:red;"></span>
        </td>
    </tr> 
    
</table>

</form>
<script type="text/javascript">
function checkroom(){
	var check=true;
	var good=$("#room").val();
	if (good!='') {
		$.ajax({
			type : "POST",
			url : "GoodsConsumeAction!findbyroom",
			data : {
				"gds" : good,
			},
			dataType : "text",
			success : function(data) {
				$("#checktext1").html(data);
				if(data=="房间不存在哦!"){
					$("#tj").attr("disabled",true);
				}
				else{
					$("#tj").attr("disabled",false);
				}
			}
		});
	} else {
				$("#checktext1").html("温馨提示:您的房间不能为空哦 !");
				check=false;
	}
	return check;
}

function checkid(){
	var good=$("#gdsname").val();
	var check=true;
	if (good!='') {
		$.ajax({
			type : "POST",
			url : "GoodsConsumeAction!findbyid",
			data : {
				"gds" : good,
			},
			dataType : "text",
			success : function(data) {
				$("#checktext2").html(data);
				if(data=="商品不存在!"){
					$("#tj").attr("disabled",true);
				}
				else{
					$("#tj").attr("disabled",false);
				}
			}
		});
	} else {
				$("#checktext2").html("温馨提示:您输入的商品名称不能为空哦  !");
				check=false;
	}
	return check;
}

function checkcount(){
	var good=$("#gdscount").val();
	var check=false;
	if(good>5){
		$("#checktext3").html("温馨提示:您输入的数量都大过库存啦#^#  !");
		check=false;
	}else{
		$("#checktext3").html("");
		check=true;
	}
	return check;
}
function check(){
	var check1= false; 
	var check1=checkid() && checkcount() && checkroom();
	if(check1){
	window.document.getElementById('checktext4').innerText='添加成功 ';
	$("#checktext4").fadeOut(3000);
	}
	else{
		$("#checktext4").fadeIn(10);
		window.document.getElementById('checktext4').innerText='请完善信息！^-^ ';
		$("#checktext4").fadeOut(3000);
	}
	return check1;
}
$(function () {       
	$('#backid').click(function(){
			window.location.href="GoodsConsumeAction!showpageGoodsConsume?nowpage=1";
	 });

});

</script>
</body>
</html>
