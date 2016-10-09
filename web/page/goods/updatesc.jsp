<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>


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
<!-- 	<form action="GoodsAction!findGoodsbyid" name="update" onsubmit="return check()" method="post" class="definewidth m20">
 --><table class="table table-bordered table-hover m10">
 	<tr>
        <td class="tableleft" width="250px" >商品编号</td>
        <td>
        	<input  value="${requestScope.up.goodsid}" name="goods.goodsid"  readonly="readonly" type="text" />
        	</td>
    </tr>
    <tr>
        <td class="tableleft">名称</td>
        <td><input value="${requestScope.up.goodsname}" id="cname" onblur="checkname()" readonly="readonly" name="goods.goodsname" type="text" />
        	<span id="checktext1" style="color:red;"></span>
        </td>
    </tr>
      <tr>
        <td class="tableleft">数量</td>
        <td><input value="${requestScope.up.goodscount}" id="ccount" onblur="checkcount()" readonly="readonly" name="goods.goodscount" type="text" />
        	<span id="checktext2" style="color:red;"></span>
        </td>
        
    </tr>
    <tr>
        <td class="tableleft">价格</td>
        <td><input value="${requestScope.up.goodsprice}" id="cprice" onblur="checkprice()" readonly="readonly" name="goods.goodsprice" type="text" />
        	<span id="checktext3" style="color:red;"></span>
        </td>
    </tr>
    
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
<script type="text/javascript">
function checkname() {
	var check=false;
	if($('#cname').val()==''){
		window.document.getElementById('checktext1').innerText='温馨提示:您的商品名称不能为空 !';
	}
	else{
		window.document.getElementById('checktext1').innerText='';
	}
}
function checkcount(){
	var check=false;
	var shuzi=/^[0-9]*[1-9][0-9]*$/;
	var goodscheck=window.document.update.ccount;
	if($('#ccount').val()==''){
		window.document.getElementById('checktext2').innerText='温馨提示:您的商品数量不能为空 !';
		check=false;			
		}
	else if(!shuzi.test(goodscheck.value)){
		window.document.getElementById('checktext2').innerText='温馨提示:请输入数字 !';
			
		}else if(goodscheck>5){
		window.document.getElementById('checktext2').innerText='温馨提示:库存不能超过5';
			
		}
	else{
		window.document.getElementById('checktext2').innerText='';
		check=true;
		}
		return check;
}
function checkprice(){
	var check=false;
	var shuzi=/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/;
	var check=window.document.update.cprice;
	if($('#cprice').val()==''){
		window.document.getElementById('checktext3').innerText='温馨提示:您的商品价格不能为空 !';
		check=false;
	}else if(!shuzi.test(check.value))
			{
		window.document.getElementById('checktext3').innerText='温馨提示:您的商品价格格式不正确!';
	}
	else{
		window.document.getElementById('checktext3').innerText='';
		check=true;
		}
	return check;
}

 function check(){
	var check=checkcount()&&checkprice();
	return check;
} 
 $(function () {       
		$('#backid').click(function(){
				window.location.href="GoodsAction!showpageGoods?nowpage=1";
		 });

 });
</script>
</body>
</html>

