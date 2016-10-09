<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
    <title></title>
     <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <link href="Css/lyz.calendar.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
	<script type="text/javascript" src="Js/jquery-1.5.1.js"></script>
	<script src="Js/lyz.calendar.min.js" type="text/javascript"></script>
    <style type="text/css">
        body {
        	font-size: 12px;
			font-family: "微软雅黑", "宋体", "Arial Narrow";
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
<form action="" method="post" class="definewidth m20" >
<table class="table table-bordered table-hover m10">
	
    <tr>
        <td class="tableleft">用户名</td>
        <td><input type="text" id="username" onblur="checkusername()" name="usertable.username" value="${requestScope.upt.username}" readonly="readonly"/>
        	<input type="hidden" id="userid" readonly="readonly" name="usertable.userid" value="${requestScope.upt.userid}" />
        </td>
    </tr>

    <tr>
        <td class="tableleft">真实姓名</td>
        <td><input type="text" id="realname" name="usertable.realname" onblur="checkrealname()"  value="${requestScope.upt.realname}" readonly="readonly"/>
        <input type="hidden" id="password" name="usertable.password" value="${requestScope.upt.password}"/>
        </td>
    </tr>
     <tr>
        <td class="tableleft" width="90px">生日</td>
        <td><input  id="userbirth"  name="usertable.userbirth" value="${requestScope.upt.userbirth}" readonly="readonly"/></td>
    </tr>
    <tr>
        <td class="tableleft">性别</td>
   		<td>
   			<c:choose>
   			<c:when test="${requestScope.upt.usersex eq '男'}">
   			<input type="radio" id="usersex" checked="checked" name="usertable.usersex" value="男" readonly="readonly"/>男
   			<input type="radio" id="usersex" name="usertable.usersex" value="女" readonly="readonly"/>女
   			</c:when>
   			<c:otherwise>
   			<input type="radio" id="usersex" name="usertable.usersex" value="男" readonly="readonly"/>男
   			<input type="radio" id="usersex" checked="checked" name="usertable.usersex" value="女" readonly="readonly"/>女
   			</c:otherwise>
   		</c:choose>
   		</td>
    </tr>
    <tr>
        <td class="tableleft">职务</td>
        <td><input type="text" id="position" name="usertable.position" value="${requestScope.upt.position}" readonly="readonly"/></td>
    </tr>
    <tr>
        <td class="tableleft">工资</td>
        <td><input type="text" id="salary" name="usertable.salary" value="${requestScope.upt.salary}" readonly="readonly"/></td>
    </tr>
    
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="button" class="btn btn-primary" id="update">修改</button> &nbsp;&nbsp;
        
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>

    $(function () {       
		$('#backid').click(function(){
				window.location.href="UserAction!showPage?nowpage=1";
		 });
		$('#update').click(function(){
			window.location.href="UserAction!findUserById?userid=${requestScope.upt.userid}";
		
		})

    });
</script>