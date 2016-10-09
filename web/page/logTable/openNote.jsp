<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="page/logTable/assets/css/openNote.css" rel="stylesheet" media="screen" />
	</head>
	<body>
		<div id="book">
			<canvas id="pageflip-canvas"></canvas>
			<div id="pages">
			  <c:forEach items="${requestScope.mylogs}" var="mylog" varStatus="status">
				<section>
					<div>
						<h2>Note♙:${status.count}</h2>
						<nav id="logid" style="display:none">${mylog.logid}</nav>
						<!--<img  id="delmylog" src="page/logTable/assets/img/del.jpg" style="width:30px;height:30px;position: relative;left:350px;top:-50px"/>  -->
						<p style="font-size:1.2em;font-weight:bold">${mylog.log}</p>
					</div>
				</section>
				</c:forEach>
			</div>
			
		</div>
		<script type="text/javascript" src="page/logTable/pageflip.js"></script>
		<script type="text/javascript" src="Js/jquery.js"></script>
	</body>
<html>
<script>
/********删除便签的功能暂定：！！****/
	 $("#delmylog").dblclick(function(){
		 console.log("4444")
		var logid = $("#logid").text();
		alert(logid)
	} )

</script>