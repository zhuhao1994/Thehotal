<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<script type="text/javascript" src="Js/jquery-1.7.2.js">
</script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript">
	
</script>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="Css/style.css" />
<script type="text/javascript" src="Js/jquery.js"></script>
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
		action="CustomerAction!queryByTerm" method="post">
		<select id="se" name="se">
			<option id="cusname" value="cusname">客户姓名</option>
			<option id="cardid" value="cardid">身份证号</option>
			<option id="status" value="status">入住状态</option>
		</select> <input type="text" name="nowpage" value="1" style="display: none" />
		<input type="text" name="keywords" class="abc input-default"
			id="keywords" value="${requestScope.keywords}">&nbsp;&nbsp;
		<button id="bu" type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">添加客户</button>
		<c:if test="${sessionScope.thePath=='roomMenu'}">
			<script>
    		var thehtml = "&nbsp;&nbsp;<button  id='findLast' class='btn btn-primary'><a style='color:white;text-decoration : none' href='page/checkInRoom/checkInRoom.jsp'>返回上一级</a></button>";
    		$("#addnew").after(thehtml);
    		var thehtml = "&nbsp;&nbsp;<button  id='findnext' class='btn btn-primary'><a style='color:white;text-decoration : none' href='roomStatusAction!roomStatus?thePath=roomMenu'>next(查询客房)</a></button>";
    		$("#findLast").after(thehtml);
    	</script>
		</c:if>
	</form>
	<table class="table table-bordered table-hover definewidth m10"  onselectstart="return false">
		<thead>
			<tr>
				<th>客户编号</th>
				<th>客户姓名</th>
				<th>客户性别</th>
				<th>身份证号</th>
				<th>出生年月</th>
				<th>联系电话</th>
				<th>账户余额</th>
				<th>所在地址</th>
				<th>入住状态</th>
				<th>会员等级</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="tb">
			<c:forEach items="${requestScope.showCus}" var="c">
				<tr>
					<td>${c.cusid}</td>
					<td>${c.cusname}</td>
					<td>${c.cussex}</td>
					<td>${c.cardid}</td>
					<td>${c.cusbirth}</td>
					<td>${c.custel}</td>
					<td>${c.balance}</td>
					<td>${c.address}</td>
					<td><c:if test="${c.status eq 0}">未入住</c:if> <c:if
							test="${c.status eq 1}">入住</c:if></td>
					<td>${c.vip.vrank}</td>
					<td><a href="CustomerAction!loadCustomer?cusid=${c.cusid}">编辑</a></td>

				</tr>
			</c:forEach>
	</table>
	<div class="inline pull-right page">
		${requestScope.haPage.rowsTotal}条记录
		${requestScope.haPage.page}/${requestScope.haPage.pageTotal}页 <a
			class="thenextpage" href='CustomerAction!shoaPageHa?nowpage=1'>首页</a>
		<c:if test="${!(requestScope.haPage.page==1)}">
			<a class="thenextpage"
				href='CustomerAction!shoaPageHa?nowpage=${requestScope.haPage.page-1}'>上一页</a>
		</c:if>

		<c:if
			test="${!(requestScope.haPage.page==requestScope.haPage.pageTotal)}">
			<a class="thenextpage"
				href='CustomerAction!shoaPageHa?nowpage=${requestScope.haPage.page+1}'>下一页</a>
		</c:if>
		&nbsp;&nbsp;&nbsp;<input class="input-default" type="text" id="jumpTo"
			style="width: 30px" />页 <input class="btn btn-primary" id="jumpTo"
			type="button" value="GO"
			onclick="jumpTo(${requestScope.haPage.pageTotal})" /> <a
			class="thenextpage"
			href='CustomerAction!shoaPageHa?nowpage=${requestScope.haPage.pageTotal}'>最后一页</a>
	</div>
	<!-- js代码部分 -->
	<script>
/**替换元素**/
  $("#se").change(function(){
	var se = $("#se").val();
	if(se =="status")
		{
		
		 $("#keywords").replaceWith("<select id='keywords' name='keywords' selected='selected'> <option  value='入住'>入住</option> <option   value='未入住'>未入住</option></select>");
		}
	else
		{
		 $("#keywords").replaceWith("<input type='text' name='keywords' class='abc input-default' id='keywords' value='${requestScope.keywords}'>");
		}
  });
/*******选择客户 ：行点击事件***/
 $("table tr").dblclick(function()
{
	var custmerId = $(this).find("td:first").text();//客户id
	var custmerName = $(this).find("td:nth-child(2)").text();//客户姓名
  	   $.ajax({
 	   type:"POST",//请求方式
 	   url:"roomStatusAction!choseCustmer",//发送请求地址
 	   dataType:"text",
  	   data:{
  		   cusId:custmerId,
  		   cusName:custmerName
  		   },
 	   success:function(data){
 		   alert("已选择该客户："+data)
 	   },
  	 	error:function(){
  	 		alert("选择用户失败")
  	  	}
    });
}) 
 
/*********************************/
//添加客户
	$('#addnew').click(function(){
			window.location.href="page/customer/addCustomer.jsp";
	 });
	
	 /*********自动跳转***********/
	 if('${requestScope.autoSearch}' =="autoSearch")
	    {
			var autocontent = $("#keywords").val();
		    autocontent = encodeURI(encodeURI(autocontent));
			var autourl  = "CustomerAction!queryByTerm?se=cusname&keywords=" + autocontent+"&nowpage=1";
			window.location.href = autourl;
		}
//=========方法选中=====
	if('${sessionScope.term}' == 'cusname')
	{
		console.log('cusname');
		$("#se").val('cusname');
		 $("#keywords").replaceWith("<input type='text' name='keywords' class='abc input-default' id='keywords' value='${requestScope.keywords}'>");
		 $("#keywords").val('${requestScope.keywords}');
	}else if('${sessionScope.term}' == 'cardid')
	{
		console.log('cusname');
		$("#se").val('cardid');
		 $("#keywords").replaceWith("<input type='text' name='keywords' class='abc input-default' id='keywords' value='${requestScope.keywords}'>");
		 $("#keywords").val('${requestScope.keywords}');
	}
	else if('${sessionScope.term}' == 'status')
	{
		$("#se").val('status');
		 $("#keywords").replaceWith("<select id='keywords' name='keywords'> <option  value='入住'>入住</option> <option   value='未入住'>未入住</option></select>");
		 $("#keywords").val('${requestScope.keywords}');	
	}
	
//==分页处理==
	//==跳转分页
		function jumpTo(maxPage) {
			var page = $("#jumpTo").val();
			var content = $("#keywords").val();
			content = encodeURI(encodeURI(content));
			 if('${sessionScope.term}' == 'status')
				goUrl =  "CustomerAction!queryByTerm?se=status&keywords=" + content+"&nowpage="+page;
			 else if('${sessionScope.term}' == 'cardid')
				 goUrl =  "CustomerAction!queryByTerm?se=cardid&keywords=" + content+"&nowpage="+page;
			 else if('${sessionScope.term}' == 'cusname')
				 goUrl =  "CustomerAction!queryByTerm?se=cusname&keywords=" + content+"&nowpage="+page;
			 else
				goUrl =  "CustomerAction!queryByTerm?se=cusname&keywords=" + content+"&nowpage="+page;
			if (page > maxPage || page < 1) {
				alert("对不起，无法到达该页")
			} else {
				window.location.href = goUrl;
			}
		}
	//==前后分页
	 	 if('${sessionScope.term}' == 'status')
	 	{
	 		 //更改href的值
	 		 var content = $("#keywords").val();
	 		  content = encodeURI(encodeURI(content));//转换编码
	 		  var thereplace = "queryByTerm?se=status&keywords=" + content+"&";
	 			// ---修改href的分页路径
	 			$(".thenextpage").each(function() {
	 				url = $(this).attr("href");
	 				var urlcontent = url.replace("shoaPageHa?", thereplace);
	 			   $(this).attr("href", urlcontent);
	 	
	 			});
	 	}
</script>
</body>
</html>
