<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<base href="<%=basePath%>">
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
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
 
	<form class="form-inline definewidth m20"
		action="GoodsAction!findbygoods" method="post">
		<p onselectstart="return false;">
			<tr>
				<td><select id="ggd" name="ggd">
						<option value="goodsid">按商品编号</option>
						<option value='goodsname' />&nbsp;按商品名称
						</option>
				</select></td>
			</tr>
			<input type="text" name="nowpage" value="1" style="display: none" />
			<input type="text" name="keygoods" id="keygoods" value="${requestScope.keygoods}"
				class="abc input-default">&nbsp;&nbsp;
			<button type="submit" class="btn btn-primary">查询</button>
			&nbsp;&nbsp;
			<button type="button" class="btn btn-success" id="addnew"  >添加商品</button>
	</form>
            <c:if test="${sessionScope.thepath=='goodsPath'}">
				<button  id='addgdConsume' onclick='addgdConsume1()' class="btn btn-success" style="position: relative;margin-top: -40px;margin-left: 640px">
				添加消费记录
				</button>
			</c:if>
  
	<table class="table table-bordered table-hover definewidth m10"  onselectstart="return false">
		<thead>
			<tr>
				<th>商品编号</th>
				<th>商品名称</th>
				<th>商品数量</th>
				<th>商品价格</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach items="${requestScope.allGoods}" var="gds" begin="0">
			<tr class="chosedgoods">
				<td>${gds.goodsid}</td>
				<td>${gds.goodsname}</td>
				<td class="goodNum">${gds.goodscount}</td>
				<td class="gdPrice">${gds.goodsprice}</td>
				<td><a href="GoodsAction!findGoodsbyid?goodsid=${gds.goodsid}">编辑</a>&nbsp&nbsp
				</td>

			</tr>
		</c:forEach>

	</table>
	<div class="inline pull-right page">
		${requestScope.haPage.rowsTotal}条记录
		${requestScope.haPage.page}/${requestScope.haPage.pageTotal}页 <a
			href='GoodsAction!showpageGoods?nowpage=1'>首页</a>
		<c:if test="${!(requestScope.haPage.page==1)}">
			<a
				href='GoodsAction!showpageGoods?nowpage=${requestScope.haPage.page-1}'>上一页</a>
		</c:if>

		<c:if
			test="${!(requestScope.haPage.page==requestScope.haPage.pageTotal)}">
			<a
				href='GoodsAction!showpageGoods?nowpage=${requestScope.haPage.page+1}'>下一页</a>
		</c:if>
		&nbsp;&nbsp;&nbsp;<input class="input-default" type="text" id="jumpTo"
			style="width: 30px" />页 <input class="btn btn-primary" type="button"
			value="GO" onclick="jumpTo()" /> <a
			href='GoodsAction!showpageGoods?nowpage=${requestScope.haPage.pageTotal}'>最后一页</a>
	</div>
	<script type="text/javascript">
	//===弹出确认框==计算总价 =

	    /*
	
	   $("#addgdConsume").click(function(){
		 bool = 1;
		 console.log("bool :" + bool)
	   });
	   alert("22");
		console.log("bool2 :" + bool)
	 
		if(bool == 1)
			 window.location.href="HotelAction2!myAccount?myHaId=yfid";*/
			 
	
	
	///////////////////////////////
	function jumpTo(maxPage){
	    var page = $("#jumpTo").val();
	    if(page > maxPage || page < 1){
	        alert("对不起，无法到达该页")
	    }else{
	    	window.location.href='GoodsAction!showpageGoods?nowpage='+page;
	    }
	}
	function findBycusName() {
		var content = $("#menuname").val();
		window.location.href='GoodsAction!showpageGoods?nowpage=1&theKey=cusName&keyContent='+content;
	}
	
		$('#addnew').click(function(){
				window.location.href="page/goods/addgoods.jsp";
		 });
	if('${sessionScope.term}' == 'goodsid')
	{
		$("#ggd").val('goodsid')
	}else if('${sessionScope.term}' == 'goodsname')
	{
		console.log('goodsid');
		$("#ggd").val('goodsname')
	}
	else if('${sessionScope.term}' == 'goodsprice')
	{
		$("#ggd").val('goodsprice')
	}
	
	//==分页处理==
	//==跳转分页
		function jumpTo(maxPage) {
			var page = $("#jumpTo").val();
			var content = $("#keygoods").val();
			content = encodeURI(encodeURI(content));
			 //if('${sessionScope.term}' == 'goodsprice')
				//goUrl =  "GoodsAction!findbygoods?ggd=goodsprice&keygoods=" + content+"&nowpage="+page;
			 if('${sessionScope.term}' == 'goodsname')
				 goUrl =  "GoodsAction!findbygoods?ggd=goodsname&keygoods=" + content+"&nowpage="+page;
			 else if('${sessionScope.term}' == 'goodsid')
				 goUrl =  "GoodsAction!findbygoods?ggd=goodsid&keygoods=" + content+"&nowpage="+page;
			 else
				 goUrl =  "GoodsAction!findbygoods?ggd=goodsid&keygoods=" + content+"&nowpage="+page;
			 if (page > maxPage || page < 1) {
				alert("对不起，无法到达该页")
			} else {
				window.location.href=goUrl ;
			}
		}

	//==结算商品==
		$(".chosedgoods").click(function(){
			if('${sessionScope.thepath}'=='goodsPath')
			{
				//====选中商品
				var goodsid = $(this).children().first().text();
				if($(this).css("background-color") == 'rgb(245, 245, 245)')
				{
				 $(this).css("background-color","#FFFFFF");
				}else
				{
					$(this).css("background-color","#F5F5F5");
				}
				//====编辑商品数量
			}
			
		})
	   $(".goodNum").click(function(){
		   bool = 1;
		   //1.取出当前td里面的文本内容
	          var td = $(this);
	          var tdText = td.text();
	         //2.清空td里面的文本内容
	          td.html(""); //也可以使用td.empty();
	         //3.建立一个输入框，也就是input标签
	           var input = $("<input>");
	         //4.将输入框的内容设为刚才保存的td里面的文本内容
	           input.attr("value",tdText);
	          //4.5.让文本框能够响应键盘按下的keyup事件，主要是用于处理回车确认
	            input.keyup(function(event){
	                //1.获取当前用户按下的键值
	                   //解决不同浏览器获得事件对象的差异,
	                  // IE用自动提供window.event，而其他浏览器必须显示的提供，即在方法参数中加上event
	                var myEvent = event || window.event;
	                var keyCode = myEvent.keyCode;
	                //2.判断是否是回车按下
	                if(keyCode == 13){
	                    //2.保存当前输入框的内容
	                     var input = $(this);
	                     var inputText = input.val();//这个地方不能用text(),而是用val()
	                    //3.清空td的内容,即去掉输入框
	                    var td = input.parent("td");
	                    td.empty();
	                    //4.将保存的文本内容填充到td中去
	                    td.html(inputText);
	                    //5.让td重新拥有点击事件
	                    td.click(tdClick);
	                }
	            });
	         //5.将输入框加到td中
	           td.append(input);  //也可以用input.appendTo(td);
	         //5.5 让文本框中的文字被高亮选中
	         //需要将jquery对象转化为DOM对象
	          var inputDom = input.get(0);
	          inputDom.select();
	         //6.需要移除td上的点击事件
	         td.unbind("click");
	   })
	  //===计算商品的价格将他发送到后台：商品id + 数量 
	 	var bool = 0;
	    function addgdConsume1()
	    {
	    	  
			 var  sum = 0;
			  // 商品id : 数量
			  var goods = new Array();
			  $(".chosedgoods").each(function(){
				  if($(this).css("background-color") == 'rgb(245, 245, 245)')//如果是选中的商品
					{
					   var goodsId = $(this).children().first().text();
					   var goodsnum = $(this).find(".goodNum").text();
					   var goodsPri = $(this).find(".gdPrice").text();
					   var txt = "商品id:" +goodsId + "  数量 ："+goodsnum + "  单价 ："+goodsPri;
					   //键值对=商品id : 数量
					   goods.push({"goodsid":goodsId,"goodsnum":goodsnum})
					   sum += goodsnum *goodsPri; //总价
					}
				  });
			  var  goodstr = JSON.stringify(goods)
			  //=====弹出=====
			   if (sum != 0)
			    {
				  console.log(" 商品总价"+ sum);
			    //==提交ajax=
			    $.ajax({
					type:"post",
					url:"GoodsAction!addgdconsumes",
					data:{
						"goodss":goodstr,
						"sumPrice":sum
						},
					dataType : "json",
					async:false,
					success:function(data,dataType){
						alert(dataType)
					},
					error:function()
					{
						var cf = confirm("添加消费成功，是否跳转到账单中心");
						if(cf == true)
							 window.location.href="HotelAction2!myAccount?myHaId=yfid&sumPrice="+sum+"&goodstatus=yes";
						else{
							console.log("本页面！");
						}
					}
				});
			    }
			   else{
				   alert("请重新选择!")
			   }
			  //遍历json对象
			  //2.计算商品的总价：弹出提示框（是否结算）
	    } 
	  console.log("bool2 :" + bool);

	</script>
</body>
</html>
