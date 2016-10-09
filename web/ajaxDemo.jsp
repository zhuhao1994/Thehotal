<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>测试  ajax</title>
</head>
<script type="text/javascript" src="Js/jquery-1.7.2.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#bt").click(function() {
			//1.封装json数据
			var ajson = '{"username":"wupeng","xingming":{"xm1":"wupeng","xm2":"zhuhao"},"password":"123123"}';
			//2.发送json数据
				 var url = 'ajaxLogin.action';  
				           alert("===");  
				           //获取表单值，并以json的数据形式保存到params中  
				           var params = {  
				               loginName:"wu",  
				               password:"peng",  
				           }  
				           //使用$.post方式      
				           $.post(  
				               url,        //服务器要接受的url  
				               params,     //传递的参数       
				               function cbf(data){ //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据  
				                   //alert(data);  
				                   var member = eval("("+data+")");    //包数据解析为json 格式    
				                   alert("欢迎您：  "+member.name);  
				                     
				               },   
				                "json"
				           );  
		});
	});
</script>

<body>
	<center>
		<button id="bt">传入数据</button>
	</center>
</body>
</html>
