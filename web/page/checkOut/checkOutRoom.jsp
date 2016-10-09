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
<title>显示账目</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	<form class="form-inline definewidth m20" action="HotelAction2!checkOutShowHa">
		<select id="searchKey" name="searchKey">
			<option id="noCheckOut" value="noCheckOut">未退房</option>
			<option id="allha" value="allha">全部</option>
			<option id="checked" value="checked">已退房</option>
		</select>
		<input type="text" name="nowpage" value="1" style="display: none" />
		<input type="number" name="roomNo" id="theRoomNo"
			class="abc input-default" placeholder="请输入房号" value="${requestScope.roomNo}">&nbsp;&nbsp;
	
		<button type="submit" class="btn btn-primary" id="byUserName">查询</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10" onselectstart="return false;">
		<thead>
			<tr>
				<th>▲ 账目编号</th>
				<th style="align: center">❤ 押金</th>
				<th>⊙ 时间</th>
				<th>¥ 住房消费</th>
				<th>¥ 总消费</th>
				<th>✍ 备注</th>
				<th>⊕ 详情</th>
				<th>账单状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<c:forEach var="ha" items="${requestScope.allHotelAccount}">
			<tr>
				<td>❦ ${ha.hid}</td>
				<td>¥ ${ha.deposit}</td>
				<td>${ha.cometime}☚☛${ha.leavetime}</td>
				<td>¥ ${ha.consume}</td>
				<td>¥ ${ha.allconsume}</td>
				<td>${ha.remarks}</td>
				<td>
					<select>
							<option>◕‿◕ 客户:${ha.customer.cusname}</option>
							<option>&nbsp; ▩ &nbsp;&nbsp;客房：${ha.guestroom.roomtype}</option>
							<option>◕‿◕ 代理人：${ha.usertable.username}</option>
					</select>
				</td>
				<td>
				 <c:if test="${(ha.accuntstatus==0)}">
				  	 未结账
				 </c:if>
				  <c:if test="${(ha.accuntstatus==1)}">
				  	 已结账
				 </c:if>
				</td>
				<td>
				 <c:if test="${(ha.accuntstatus==0)}">
				  	<a style="text-decoration:none;" href="HotelAction2!myAccount?myHaId=${ha.hid}">结账</a>
				 </c:if>
				 <c:if test="${(ha.accuntstatus==1)}">
				  	<a style="text-decoration:none;" href="#">查看账单</a>
				 </c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="inline pull-right page" >
		${requestScope.haPage.rowsTotal}条记录 ${requestScope.haPage.page}/${requestScope.haPage.pageTotal}页
		<a  id="testfirst" class="thenextpage" href='HotelAction2!checkOutShowHa?nowpage=1' >首页</a>
		 <c:if test="${!(requestScope.haPage.page==1)}">
             <a  class="thenextpage" href='HotelAction2!checkOutShowHa?nowpage=${requestScope.haPage.page-1}'>上一页</a>
         </c:if>
         <c:if test="${!(requestScope.haPage.page==requestScope.haPage.pageTotal)}">
             <a class="thenextpage"  href='HotelAction2!checkOutShowHa?nowpage=${requestScope.haPage.page+1}'>下一页</a>
        </c:if>
         &nbsp;&nbsp;&nbsp;<input class="input-default" type="text" id="jumpTo" style="width:30px" />页 <input class="btn btn-primary" type="button" value="GO" onclick="jumpTo(${requestScope.haPage.pageTotal})" />
		 <a  class="thenextpage" href='HotelAction2!checkOutShowHa?nowpage=${requestScope.haPage.pageTotal}'>最后一页</a>
	</div>
<script>
function jumpTo(maxPage) {
	var page = $("#jumpTo").val();
	goUrl = 'HotelAction2!checkOutShowHa?nowpage=' + page;
	if (page > maxPage || page < 1) {
		alert("对不起，无法到达该页")
	} else {
		window.location.href = goUrl;
	}
}
if('${sessionScope.searchKey}' == 'noCheckOut')
{
	$("#searchKey").val('noCheckOut')
}else if('${sessionScope.searchKey}' == 'allha')
{
	console.log('cusname');
	$("#searchKey").val('allha')
}
else if('${sessionScope.searchKey}' == 'checked')
{
	$("#searchKey").val('checked')
}
</script>
</body>
</html>
