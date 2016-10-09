<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>显示账目</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="Css/style.css" />
<script type="text/javascript" src="Js/jquery.js"></script>
<script type="text/javascript" src="Js/bootstrap.js"></script>
<script type="text/javascript" src="Js/ckform.js"></script>
<script type="text/javascript" src="Js/common.js"></script>
<script language="javascript" src="Js/YMDClass.js"></script>
<script src="Js/htmlTableToExcele.js" type="text/javascript"></script>
<script type="text/javascript" src="Js/sct.js"></script><!-- 自己定义的js -->
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
<script>
</script>
</head>
<body onselectstart="return false;"><center>
	<form class="form-inline definewidth m20">
		<button type="button" id="but1" class="btn btn-primary">查看以往记录</button>
		<button type="button" id="but2" class="btn btn-primary">查看某年消费统计</button>
		<button type="button" id="but3" class="btn btn-primary">查看月消费统计</button>
		<button type="button" id="but4" class="btn btn-primary">查看日消费统计</button>
		<br/><br/>
		<div class="dateXXX" >
			<span class="message" ></span>
			<select name="year1" style="width: 80px" class="year"></select>
			<select name="month1" class="month" style="width: 80px"></select>
			<select name="day1" class="day" style="width: 80px"></select>
		     <button  type="button" id="but5" class="btn btn-primary" >查看</button>
		</div>
	</form>
	<div class=tablelist>
	<c:if test="${requestScope.countByRecords.size() !=0}">
	<span  id="ytitle" class="inline pull-left page" style="margin-left: 25px" ><h4 style="text-align: center;">
	${countByRecords.get(0).date.getYear()+1900}年 
	${countByRecords.get(0).date.getMonth()+1}月
	${countByRecords.get(0).date.getDate()}号&nbsp;&nbsp;消费情况
	</h4></span>
			<table class="inline pull-right page">
			<tr><td>
			<td><button  onClick ="method5('table1')" class="btn btn-primary">生成excel表</button></td></tr>
			</table>
			<table class="table table-bordered table-hover definewidth m10" id="table1" onselectstart="return false;">
				<thead>
					<tr>
						<th style="align: center">❤ 年</th>
						<th>⊙ 月份</th>
						<th>⊙ 号</th>
						<th>⊙ 客户数量</th>
						<th>¥ 住房总消费</th>
						<th>¥ 商品总消费</th>
						<th>¥ 合计</th>
						
					</tr>
				</thead>
				<c:forEach var="yc" items="${requestScope.countByRecords}"  >
					<tr>
						<td>${yc.date.getYear()+1900}</td>
						<td>${yc.date.getMonth()+1}</td>
						<td>${yc.date.getDate()}</td>
						<td>${yc.cusNum}</td>
						<td>${yc.consum}</td>
						<td>${yc.allconsum - yc.consum}</td>
						<td>${yc.allconsum}</td>
					</tr>
				</c:forEach>
			</table>
	</c:if>
	<c:if test="${requestScope.countByRecords.size() ==0}">
		<span id="tipMess">亲：不存在该记录</span>
	</c:if>
	</div>
<script type="text/javascript">
 var d=new Date();
var nowYear=d.getYear()+1900;
var nowMonth=d.getMonth()+1;
var nowDay=d.getDate(); 
new YMDselect('year1','month1','day1',nowYear,nowMonth,nowDay);
</script>
</body>
</html>
