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
<form action="UserAction!updateUser" method="post" class="definewidth m20" onsubmit="return check()">
<table class="table table-bordered table-hover m10">
	<tr>
        <td class="tableleft">员工ID</td>
        <td><input type="text" id="userid" readonly="readonly" name="usertable.userid" value="${requestScope.findUserById.userid}"/>
        </td>
    </tr>
    <tr>
        <td class="tableleft">用户名</td>
        <td><input type="text" id="username" onblur="checkusername()" name="usertable.username" value="${requestScope.findUserById.username}"/>
        <span id="check1" style="color: red;">
        </span></td>
    </tr>
    <tr>
        <td class="tableleft">密码</td>
        <td><input type="hidden" id="password" name="usertable.password" value="${requestScope.findUserById.password}"/>
        </td>
    </tr>
    <tr>
        <td class="tableleft">真实姓名</td>
        <td><input type="text" id="realname" name="usertable.realname" onblur="checkrealname()"  value="${requestScope.findUserById.realname}"/>
        <span id="check2" style="color: red;">
        </span></td>
    </tr>
     <tr>
        <td class="tableleft" width="90px">生日</td>
        <td><input id="txtBeginDate" id="userbirth"  name="usertable.userbirth" value="${requestScope.findUserById.userbirth}"/></td>
    </tr>
    <tr>
        <td class="tableleft">性别</td>
   		<td>
   			<c:choose>
   			<c:when test="${requestScope.findUserById.usersex eq '男'}">
   			<input type="radio" id="usersex" checked="checked" name="usertable.usersex" value="男"/>男
   			<input type="radio" id="usersex" name="usertable.usersex" value="女"/>女
   			</c:when>
   			<c:otherwise>
   			<input type="radio" id="usersex" name="usertable.usersex" value="男"/>男
   			<input type="radio" id="usersex" checked="checked" name="usertable.usersex" value="女"/>女
   			</c:otherwise>
   		</c:choose>
   		</td>
    </tr>
    <tr>
        <td class="tableleft">职务</td>
        <td><input type="text" id="position" name="usertable.position" value="${requestScope.findUserById.position}"/></td>
    </tr>
    <tr>
        <td class="tableleft">工资</td>
        <td><input type="text" id="salary" name="usertable.salary" value="${requestScope.findUserById.salary}"/></td>
    </tr>
    <tr>
        <td class="tableleft">业绩</td>
        <td><input type="text" id="achievement" name="usertable.achievement" value="${requestScope.findUserById.achievement}"/></td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" id="add">保存</button> &nbsp;&nbsp;
            <span id="adds" style="color: red;">
        </span>
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
<script type="text/javascript">

$(function () {       
	$('#backid').click(function(){
			window.location.href="UserAction!showPage?nowpage=1";
	 });
});

function checkrealname(){
	var check=false;
	var shizi=/^[\u4E00-\u9FA5]{1,6}$/;//验证中文名称
	var real=$("#realname").val();
	if(real==""){
		$("#check3").html("真实姓名不能为空!");
		check=false;
	}else{
		if(!shizi.test(real)){
			$("#check3").html("请填入正确的姓名!");
			check=false;
	}else{
		$("#check3").html("");
		check=true;
	}
	}
	return check;
}
	/* function check() {
		var check1=false;
		var check1=checkusername()&&checkrealname();
		if(check1){
			$("adds").html("添加成功");
			$("#addSucc").fadeOut(3000);	
		}else{
			$("adds").html("请完善用户的信息！");
		}
		return check1;
	} */
	function check() {
		var check1=false;
		var check1=checkrealname();
		if(check1){
			$("adds").html("添加成功");
			$("#addSucc").fadeOut(3000);	
		}else{
			$("adds").html("请完善用户的信息！");
		}
		return check1;
	}
</script>
<script>
    $(function () {
        $("#txtBeginDate").calendar({
            controlId: "divDate",                                 // 弹出的日期控件ID，默认: $(this).attr("id") + "Calendar"
            speed: 200,                                           // 三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画时长的毫秒数值(如：1000),默认：200
            complement: true,                                     // 是否显示日期或年空白处的前后月的补充,默认：true
            readonly: true,                                       // 目标对象是否设为只读，默认：true
            upperLimit: new Date(),                               // 日期上限，默认：NaN(不限制)
            lowerLimit: new Date("2011/01/01"),                   // 日期下限，默认：NaN(不限制)
        });
    });
</script>
</body>
</html>

