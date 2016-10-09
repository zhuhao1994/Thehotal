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
<script type="text/javascript" src="theJs/showAllHas.js"></script>
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
	<form class="form-inline definewidth m20">
		<input type="text" name="menuname" id="menuname"
			class="abc input-default" placeholder="" value="${requestScope.keyContent}">&nbsp;&nbsp;
		<button type="button" class="btn btn-primary" id="byCustomerName">按客户查询</button>
		<button type="button" class="btn btn-primary" id="byGustName">按客房查询</button>
		<button type="button" class="btn btn-primary" id="byUserName">按前台查询</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10" onselectstart="return false">
		<thead>
			<tr>
				<th>▲ 账目编号</th>
				<th style="align: center">❤ 押金</th>
				<th>⊙ 时间</th>
				<th>¥ 住房消费</th>
				<th>¥ 总消费</th>
				<th>✍ 备注</th>
				<th>⊕ 详情</th>
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
				<td><select>
						<option>◕‿◕ 客户:${ha.customer.cusname}</option>
						<option>&nbsp; ▩ &nbsp;&nbsp;客房：${ha.guestroom.roomtype}</option>
						<option>◕‿◕ 代理人：${ha.usertable.username}</option>
				</select></td>

			</tr>
		</c:forEach>
	</table>
	<div class="inline pull-right page" >
	<script type="text/javascript">
	

	</script>
		${requestScope.haPage.rowsTotal}条记录 ${requestScope.haPage.page}/${requestScope.haPage.pageTotal}页
		<a  id="testfirst" class="thenextpage" href='HotelAction!shoaPageHa?nowpage=1&theKey=yes' >首页</a>
		 <c:if test="${!(requestScope.haPage.page==1)}">
             <a  class="thenextpage" href='HotelAction!shoaPageHa?nowpage=${requestScope.haPage.page-1}&theKey=yes'>上一页</a>
         </c:if>
<!-- 
         <c:forEach var="p" begin="1" end="${requestScope.haPage.pageTotal}">
             <a href="ps?page=${p}">第${p}页</a>
         </c:forEach>
 -->
 		
         <c:if test="${!(requestScope.haPage.page==requestScope.haPage.pageTotal)}">
             <a class="thenextpage"  href='HotelAction!shoaPageHa?nowpage=${requestScope.haPage.page+1}&theKey=yes'>下一页</a>
        </c:if>
         &nbsp;&nbsp;&nbsp;<input class="input-default" type="text" id="jumpTo" style="width:30px" />页 <input class="btn btn-primary" type="button" value="GO" onclick="jumpTo(${requestScope.haPage.pageTotal})" />
		 <a  class="thenextpage" href='HotelAction!shoaPageHa?nowpage=${requestScope.haPage.pageTotal}&theKey=yes'>最后一页</a>
	</div>
</body>
</html>
