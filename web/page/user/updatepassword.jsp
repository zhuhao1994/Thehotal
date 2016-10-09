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
<form action="UserAction!updatePassword" method="post" class="definewidth m20">
<table class="table table-bordered table-hover m10"  >
	<tr>
		<td class="tableleft" width="250px">用户名</td>
		<td>
			<input type="text" name="username" value="${sessionScope.loginUser.username}" id="username" readonly="readonly"/>
			<input type="hidden" id="userid" readonly="readonly" name="userid" value="${sessionScope.loginUser.userid}" /> 
			<input type="hidden" id="oldpass" readonly="readonly" name="oldpass" value="${sessionScope.loginUser.password}" /> 
			
		</td>
		
	
	</tr>
	<tr>
	
	<td class="tableleft" width="250px">原始密码</td>
	<td>
		<input  type="password" name="password" value="" id="password" onblur="check1()"/>
		<span id="pa" style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';"></span>
	
	</td>
	</tr>
	<tr>
	
	<td class="tableleft" width="250px">重置密码</td>
	<td>
		<input  type="password" name="newpassword" value="" id="newpassword" onblur="check2()"/>
		<span id="newpa" style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';"></span>
	
	</td>
	</tr>
	<tr>
	<td class="tableleft" width="250px">确认密码</td>
	<td>
		<input  type="password" name="repassword" value="" id="repassword" onblur="check3()"/>
		<span id="repa" style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';"></span>
	
	</td>
	</tr>
	<tr>
	<td class="tableleft" width="250px"></td>
		<td>
			<button type="button" id="save" class="btn btn-primary">保存</button>
			<button type="button" id="backid" class="btn btn-primary">返回</button>
			<span id="mes" style="color:tomato;font-weight:bolder;font-size:14px;font-family: '幼圆';"></span>
		</td>
		
	
	</tr>

	



</table>




</form>
<table class="table table-bordered table-hover m10"  >
		

</table>

</body>
</html>

<script type="text/javascript">
    	function check1(){
    		var username=$("#username").val();
    		var password=$("#password").val();
    		var regs=/^\d{5,}$/;
    		
    		if(password==""){
    			$("#pa").html("密码不能为空");
    			$("#save").attr("disabled",true);
    		}else if(regs.test(password)){
    			$.ajax({
    				
    				type : "POST",
					dataType : "text",
					url : "UserAction!findUser",
					data : {
						"username" : username
					},
    				succuss:function(data){
    					if(password!=data){
    						$("#pa").html("密码错误");
    						$("#save").attr("disabled",true);
    					}else{
    						$("#pa").html("");
    						$("#save").attr("disabled",false);
    					}
    				}
    				
    			});
    		}else{
    			$("#pa").html("请输入正确的密码");
    			$("#save").attr("disabled",true);
    		}	
    		
    	}
    	
    	function check2(){
    		var password=$("#newpassword").val();
    		var regs=/^\d{5,}$/;//密码至少为 5位数字 
    		if(password==""){
    			$("#newpa").html("密码不能为空！");
    			$("#save").attr("disabled",true);
    		}else if(!regs.test(password)){
    			$("#newpa").html("密码至少5位！");
    			$("#save").attr("disabled",true);
    		}else{
    			$("#newpa").html("");
    			$("#save").attr("disabled",false);
    			
    		}
    		
    		
    		function check3(){
    			var repassword=$("#repassword").val();
    			var password=$("#newpassword").val();
    			if(repassword==""){
    				$("#repa").html("请确认密码！");
    				$("#save").attr("disabled",true);
    			}else if(repassword!=password){
    				$("#repa").html("两次输入密码不一致");
    				$("#save").attr("disabled",true);
    			}else{
    			$("#repa").html("");
    			$("#save").attr("disabled",false);
    			}
    			
    			
    		}
    	}
    	
    	 $(function () {       
    			$('#backid').click(function(){
    					window.location.href="page/checkInRoom/checkInRoom.jsp";
    			 });
    			
    			
    			$('#save').click(function(){
    				var password=$("#repassword").val();	
    				var userid=$("#userid").val();
    				
    					$.ajax({
    						type : "POST",
    						dataType : "text",
    						url : "UserAction!updatePassword",
    						data : {
    							"userid" : userid,
    							"password":password,
    						},
    						success:function(data){
    							$("#mes").fadeIn(10);
    							$("#mes").html(data);
    							if(data=="修改成功"){
    								$("#password").val("");
    								$("#repassword").val("");
    								$("#newpassword").val("");
    								
    							}
    							$("#mes").fadeOut(3000);
    							
    							
    						}

    				});
    				
    				
    			});
    	    });
    
    
    </script>