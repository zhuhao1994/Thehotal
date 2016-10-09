<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>listroom</title>
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
	<form class="form-inline definewidth m20" action="GuestroomAction!findRoomByTerm"
		method="post" >
		<select id="se" name="se">
			<option value="roomno">房间号</option>
			<option value="roomtype">客房类型</option>
			<option value="roomstatus">客房状态</option>
				<!-- <option value="roomprice">客房价格</option> -->
		</select>
		<input  type="text" name="nowpage" value="1" style="display: none"/>
		<input type="text" name="keywords" id="thekeywords" class="abc input-default" placeholder="" value="${requestScope.keywords}">&nbsp;&nbsp;
		<button type="submit" id="bu" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增客房</button>
		<button type="button" class="btn btn-success" >
			<a style="text-decoration:none;color:white" href="roomStatusAction!roomStatus?thePath=roomMenu">☝ 图形展示</a>
		</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10" onselectstart="return false;">
		<thead>
			<tr>
				<th>客房编号</th>
				<th>房间号</th>
				<th>客房类型</th>
				<th>客房价格</th>
				<th>客房状态</th>
				<th>客房电话</th>
				<th>客房折扣</th>
				<th>管理操作</th>
			</tr>
		</thead>
		<tbody id="tb">
		<c:forEach items="${requestScope.listRooms}" var="g" begin="0" varStatus="s">
			<tr>
				<td>${g.roomid}</td>
				<td>${g.roomno}</td>
				<td>${g.roomtype}</td>
				<td>${g.roomprice}</td>
				<td>
					<c:if test="${g.roomstatus eq 0}">空房</c:if>
					<c:if test="${g.roomstatus eq 1}">入住房</c:if>
				</td>
				<td>${g.roomtel}</td>
				<td>${g.roomdiscount}</td>
				<td>
					<a href="GuestroomAction!findTheRoomByRoomid?roomid=${g.roomid} ">编辑</a>&nbsp&nbsp
				</td>
			</tr>

		</c:forEach>
	</table>

	<div class="inline pull-right page" >
		${requestScope.haPage.rowsTotal}条记录 ${requestScope.haPage.page}/${requestScope.haPage.pageTotal}页
		<a class="thenextpage" href='GuestroomAction!showPageHa?nowpage=1'>首页</a>
		 <c:if test="${!(requestScope.haPage.page==1)}">
             <a class="thenextpage" href='GuestroomAction!showPageHa?nowpage=${requestScope.haPage.page-1}'>上一页</a>
         </c:if>

         <c:if test="${!(requestScope.haPage.page==requestScope.haPage.pageTotal)}">
             <a class="thenextpage" href='GuestroomAction!showPageHa?nowpage=${requestScope.haPage.page+1}'>下一页</a>
        </c:if>
         &nbsp;&nbsp;&nbsp;<input class="input-default" type="text" id="jumpTo" style="width:30px" />页 <input class="btn btn-primary" id="jumpTo" type="button" value="GO" onclick="jumpTo(${requestScope.haPage.pageTotal})" />
		 <a class="thenextpage" href='GuestroomAction!showPageHa?nowpage=${requestScope.haPage.pageTotal}'>最后一页</a>
	</div>
	
	<script>
	$(function() {
		$('#addnew').click(function() {
			window.location.href = "page/Guestroom/addRoom.jsp";
		});

	});
	//=========方法选中=====
	if('${sessionScope.term}' == 'roomno')
	{
		console.log('roomno');
		$("#se").val('roomno')
	}else if('${sessionScope.term}' == 'roomtype')
	{
		console.log('roomno');
		$("#se").val('roomtype')
	}
	else if('${sessionScope.term}' == 'roomstatus')
	{
		$("#se").val('roomstatus')
	}
	
	//==分页处理==
	//==跳转分页
	function jumpTo(maxPage) {
		var page = $("#jumpTo").val();
		var content = $("#thekeywords").val();
		content = encodeURI(encodeURI(content));
		 if('${sessionScope.term}' == 'roomstatus')
			goUrl =  "GuestroomAction!findRoomByTerm?se=roomstatus&keywords=" + content+"&nowpage="+page;
		 else if('${sessionScope.term}' == 'roomtype')
			 goUrl =  "GuestroomAction!findRoomByTerm?se=roomtype&keywords=" + content+"&nowpage="+page;
		 else if('${sessionScope.term}' == 'roomno')
			 goUrl =  "GuestroomAction!findRoomByTerm?se=roomno&keywords=" + content+"&nowpage="+page;
		 else 
			 goUrl =  "GuestroomAction!findRoomByTerm?se=roomno&keywords=" + content+"&nowpage="+page;
		if (page > maxPage || page < 1) {
			alert("对不起，无法到达该页")
		} else {
			window.location.href = goUrl;
		}
	}
	//==前后分页
 	 if('${sessionScope.term}' == 'roomstatus')
 	{
 		 //更改href的值
 		 var content = $("#thekeywords").val();
 		  content = encodeURI(encodeURI(content));//转换编码
 		  var thereplace = "findRoomByTerm?se=roomstatus&keywords=" + content+"&";
 			// ---修改href的分页路径
 			$(".thenextpage").each(function() {
 				url = $(this).attr("href");
 				var urlcontent = url.replace("showPageHa?", thereplace);
 			   $(this).attr("href", urlcontent);
 	
 			});
 	}
	
 	 if('${sessionScope.term}' == 'roomtype')
  	{
  		 //更改href的值
  		 var content = $("#thekeywords").val();
  		  content = encodeURI(encodeURI(content));//转换编码
  		  var thereplace = "findRoomByTerm?se=roomtype&keywords=" + content+"&";
  			// ---修改href的分页路径
  			$(".thenextpage").each(function() {
  				url = $(this).attr("href");
  				var urlcontent = url.replace("showPageHa?", thereplace);
  			   $(this).attr("href", urlcontent);
  	
  			});
  	}
	
	function deleteRoom(roomno){
		var  result=window.confirm("确定删除房间号为:"+roomno+"的客房吗？");
			if(result)
			{
				window.location.href="GuestroomAction!deleteGuestroom?roomno="+roomno;
			}
	}

</script>
</body>
</html>


