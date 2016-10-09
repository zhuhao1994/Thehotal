<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <link rel="stylesheet" type="text/css"  href="Css/jquery-ui.min.css">
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>
    <script type="text/javascript" src="theJs/jquery.min.js"></script>
    <script type="text/javascript"  src="theJs/jquery-ui.min.js"></script>


<script type="text/javascript">
//验证用户名
	function ckun() {
		var check=true;
		$("#name").html("");
		var username = $("#username").val();
		var reg = /^\D+$/;
		
		if(reg.test(username)){
			$.ajax({
				type : "POST",
				dataType : "text",
				url : "RegisterAction!findByUsername",
				data : {
					"username" : username,
				},
				success :function(data){
					$("#name").html(data);
					if(data=="用户名存在！"){
						$("#regist").attr("disabled",true);
					}else{
						$("#regist").attr("disabled",false);
					}
				}
				});
		}else if(username==''){
			$("#name").html("用户名不能为空");
			$("#add").attr("disabled",true);
			check=false;
		}else{
			$("#name").html("用户名不能包含数字");
			$("#add").attr("disabled",true);
			check=false;
		}
		
		
		return check;
	}
//验证真实姓名
	function rname(){
		var check =false;
		$("#real").html("");
		var realname=$("#realname").val();
		var reg=/^[\u4E00-\u9FA5]{1,6}$/;//验证中文名称
		if(realname==""){
			$("#real").html("真实姓名不能为空！");
			check=false;
		}else if(!reg.test(realname)){
			$("#real").html("请输入正确的真实姓名！");
			check=false;
		}else{
			$("#real").html("");
			check=true;
		}
		
		return check;
	}
//验证密码	
	function cpass(){
		var	check=false;
		$("#cpass").html("");
		var password=$("#password").val();
		var regs=/^\d{5,}$/;//密码至少为 5位数字 
		if(password==""){
			$("#cpass").html("密码不能为空！");
			check=false;
		}else if(!regs.test(password)){
			$("#cpass").html("密码至少5位！");
			check=false;
		}else{
			$("#cpass").html("");
			check=true;
		}
		
		return check;
	}
//重复确认密码	
	function rpass() {
		var check=false;
		$("#pass").html("");
		var repassword=$("#repassword").val();
		var password=$("#password").val();
		if(repassword==""){
			$("#pass").html("请确认密码！");
			check=false;
		}else if(repassword!=password){
			$("#pass").html("两次输入密码不一致");
			check=false;
		}else{
		$("#pass").html("");
			check=true;
		}
	}
	
	function position(){
		var check=false;
		var position=$("#position").val();
		var regs=/^[\u4E00-\u9FA5]/;
		if(position==""){
			$("#po").html("职位不能为空");
			check=false;
		}else if(!regs.test(position)){
			$("#po").html("请输入正确的职位");
			check=false;
		}else{
			$("#po").html("");
			check=true;
		}
		return check;
	} 
	
	
	
	function sa(){
		var check=false;
		var salary=$("#salary").val();
		var regs=/^[0-9]*$/;
		if(salary==""){
			$("#sal").html("薪资不能为空");
			check=false;
		}if(!regs.test(salary)){
			$("#sal").html("请输入正确的薪资");
			check=false;
		}else{
			$("#sal").html("");
			check=true;
		}
		return check;
	}
	
	$(function() {
		$("#datepicker").datepicker(
				{
					changeMonth : true,
					changeYear : true,
					dateFormat : "yy/mm/dd",
					yearRange : '1940:1998',
					monthNamesShort : [ '1月', '2月', '3月', '4月', '5月', '6月',
							'7月', '8月', '9月', '10月', '11月', '12月' ],
					dayNamesMin : [ '日', '一', '二', '三', '四', '五', '六' ]
				});
	});
	
	
	function check(){
		var check =false;
	 	var check=ckun()&&rname()&&cpass()&&rpass()&&position()&&sa(); 
		return check; 
		
	}

</script>


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

<form action="${pageContext.request.contextPath}/RegisterAction!rigisterUser" method="post" class="definewidth m20" onsubmit="return check()">

<table class="table table-bordered table-hover m10"  >

				<tr>
					<td class="tableleft" width="250px">用户名</td>
					<td><input type="text" name="username" value="" id="username" onblur="ckun()" /> 
					<span id="name" style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';"></span>
					</td>
				</tr>
				<tr>
					<td class="tableleft">真实姓名</td>
					<td>
					<input type="text" name="realname"  value="" id="realname"  onblur="rname()"/> 
						<span id="real" style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';" > </span></td>
				</tr>
				<tr>
					<td class="tableleft">性别</td>
					<td>
					 	<input type="radio" name="usersex" value="男" id="usersex"	checked="checked" />男 
						<input type="radio" name="usersex" value="女" id="usersex"/>女
					</td>
				</tr>
				<tr>
					<td class="tableleft">出生年月</td>
					<td><input type="text" name="userbirth"  value="" id="datepicker" /></td>
				</tr>
				<tr>
					<td class="tableleft">密码</td>
					<td><input type="password" name="password" value="" id="password" onblur="cpass()"/>
						<span id="cpass" style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';"></span>
					</td>
				</tr>

				<tr>
					<td class="tableleft">确认密码</td>
					<td><input type="password" name="repassword"  value=""
						id="repassword" onblur="rpass()" />
						 <span id="pass" style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';"></span></td>
				</tr>
				<tr>
					<td class="tableleft">职位</td>
					<td>
					<input type="text" name="position"  id="position" value="" onblur="position()"/>
					<span id="po" style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';"></span>
					</td>
				
				</tr>
				<tr>
					<td class="tableleft">工资</td>
					<td>
					<input type="text" name="salary" id="salary" value="" onblur="sa()"/>
					<span id="sal" style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';"></span>
					</td>
				
				</tr>
				
				<tr>
					<td class="tableleft"></td>
					<td align="center">
						<button class="btn btn-primary" type="submit" id="regist">提交</button>
						<button class="btn btn-primary" type="button" id="backid">返回列表</button>
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

    });
</script>