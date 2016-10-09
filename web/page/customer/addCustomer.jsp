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
     <script>
  		$(function() {
   			 $( "#datepicker" ).datepicker({
     		 changeMonth: true,
      		 changeYear: true,
      		dateFormat:"yy/mm/dd",
      		yearRange: '1940:2016',
      		monthNamesShort:['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
      		dayNamesMin:['日','一','二','三','四','五','六']
    		});
  		});
  		
  	
  		
  		function sf(){
  			$("#idmes").html("");
  			var id=$("#cardid").val();
  			var regIdCard=/^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
  			if(id==""){
  				$("#idmes").html("请输入身份证号！");
  				$("#add").attr("disabled",true);
  			}else if(regIdCard.test(id)){
  				var args={
  						"cardid" :id,
  				};
  				$.ajax({
  					type:"POST",
  					url:"CustomerAction!findCusById",
  					data:args,
  					dataType : "json",
  					async:false,
  					success:function(data){
  						var mes=data.message;
  						if(mes=="客户已存在"){ 							
  							$("#idmes").html(mes);
  							$("#add").attr("disabled",true);
  						}else{
  							
  							$("#idmes").html("");
  							$("#add").attr("disabled",false);
  						}
  						
  					}
  					
  				});
  			}else{
  				$("#idmes").html("请输入正确的身份证号！");
  				$("#add").attr("disabled",true);
  			}
  		}
  		
  		function xm(){
  			
  			var name=$("#cusname").val();
  			var regname=/^[\u4E00-\u9FA5]{2,4}$/;
  			if(name==""){
  				$("#xmmes").html("请输入姓名！");
  				$("#add").attr("disabled",true);
  			}else	if(!regname.test(name)){
  				$("#xmmes").html("请输入正确的姓名！");
  				$("#add").attr("disabled",true);
  			}else{
  				$("#xmmes").html("");
  				$("#add").attr("disabled",false);
  			
  			}
  			
  			
  		}
  		
  		function t(){
  			$("#tmes").html("");
  			var tel=$("#tel").val();
  			var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
  			if(tel==""){
  				$("#tmes").html("电话号码不能为空！");
  				$("#add").attr("disabled",true);
  			
  			}else if(reg.test(tel)){
  				var args={
  						"custel":tel,
  				};
  				$.ajax({
  					type:"POST",
  					url:"CustomerAction!findCustomerByTel",
  					data:args,
  					dataType : "json",
  					async:false,
  					success : function(data) {
  						var mess=data.tell;
  						if(mess=="此电话号码已存在！"){
  							
  						$("#tmes").html(mess);
  						$("#add").attr("disabled",true);
  		  				
  						}else{
  							$("#tmes").html("");
  							$("#add").attr("disabled",false);
  						}
  					}
  				});
  			}else{
  				$("#tmes").html("请输入正确的电话号码");
  				$("#add").attr("disabled",true);
  			}
  		}  		
  		
  		function check(){
			var s=" ";
 			var p=$("#s_province").val();
 			var ci=$("#s_city").val();
 			var co=$("#s_county").val();
 			
 			if((co="市、县级市")&&ci!=("地级市")&&p!=("省份")){
 				
 				var ss=ci+p;
 			}else if((ci=="地级市")&&p!=("省份")){
 				var ss=p;
 			}else if(p=="省份"){
 				var ss="";
 			}else{
 				var ss=p+ci+co;
 			}
 			
 			
  			if(ss==0){
  				$("#address").val(s);
  			}else{
  				$("#address").val(ss);
  			}
  			var check=sf()&&xm()&&t();
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
<form action="CustomerAction!addCustomer" method="post" class="definewidth m20" onsubmit="return check()">

<table class="table table-bordered table-hover m10"  >
   
     <tr>
        <td class="tableleft">身份证号</td>
        <td><input type="text" name="cardid" id="cardid" onblur="sf()"/>
        	<span id="idmes"></span>
        </td>
    </tr>
    <tr>
        <td class="tableleft">客户姓名</td>
        <td><input type="text" name="cusname" id="cusname" onblur="xm()"/>
        	<span id="xmmes"></span>
        	<input type="hidden" name="vid" value="1">
        	 
        </td>
    </tr>
    
    <tr>
        <td class="tableleft">客户性别</td>
        <td>
            <input type="radio" name="cussex" value="男" checked="checked"/> 男
            <input type="radio" name="cussex" value="女"/> 女
          	<input type="hidden" name="balance" value="0">
        	<input type="hidden" name="status" value="0"> 
        </td>
    </tr>
    
    <tr>
        <td class="tableleft">出生年月</td>
        <td><input type="text" name="cusbirth"  value="  --------------请选择--------------  " id="datepicker" readonly="readonly">
        	<span id="birmes"></span>
        
        </td>
    </tr>
    <tr>
        <td class="tableleft">联系电话</td>
        <td><input type="text" name="custel"  id="tel" onblur="t()" />
        	<span id="tmes"></span>
        </td>
    </tr>

     
   
    <tr>
        <td class="tableleft">所在地址</td>
        <td><select id="s_province" name="s_province"   ></select>  
   			 <select id="s_city" name="s_city" ></select>  
    			<select id="s_county" name="s_county"></select>
    			<input type="hidden" name="address"  id="address"  />
   			 <script class="resources library" src="theJs/area.js" type="text/javascript"></script>
    
    <script type="text/javascript">_init_area();</script></td>
    </tr>
 
  
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" id="add" >保存</button>&nbsp;&nbsp;<button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button> 
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="CustomerAction!shoaPageHa?nowpage=1";
		 });

    });
</script>