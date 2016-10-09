<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
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
<table class="table table-bordered table-hover m10">

      
   <tr>
        <td class="tableleft">用户名</td>
        <td><input type="text" id="username"  name="usertable.username" value="${sessionScope.loginUser.username}" readonly="readonly"/>
       </td>
    </tr>
	<tr>
		<td class="tableleft">密码</td>
		<td>
		<input type="password" id="password" name="usertable.password" readonly="true" value="${sessionScope.loginUser.password}"/>
		</td>
		
	</tr>
    <tr>
        <td class="tableleft">真实姓名</td>
        <td><input type="text" id="realname" name="usertable.realname" onblur="checkrealname()"  value="${sessionScope.loginUser.realname}" readonly="readonly"/>
        </td>
    </tr>
    
     <tr>
        <td class="tableleft" width="90px">生日</td>
        <td><input id="txtBeginDate" id="userbirth"  name="usertable.userbirth" value="${sessionScope.loginUser.userbirth}" readonly="readonly"/></td>
    </tr>
    <tr>
        <td class="tableleft">性别</td>
   		<td>
   			<c:choose>
   			<c:when test="${sessionScope.loginUser.usersex eq '男'}">
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
        <td><input type="text" id="position" name="usertable.position" value="${sessionScope.loginUser.position}" readonly="readonly"/></td>
    </tr>
    <tr>
        <td class="tableleft">工资</td>
        <td><input type="text" id="salary" name="usertable.salary" value="${sessionScope.loginUser.salary}" readonly="readonly"/>
    </tr>
  
        
   
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="button" class="btn btn-primary" id="update">修改</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-primary" id="backid">返回</button>
        
        </td>
    </tr>
</table>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="page/checkInRoom/checkInRoom.jsp";
		 });
		
		
		$('#update').click(function(){
			window.location.href="page/user/updatepassword.jsp";
		});
    });
    
</script>