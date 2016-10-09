<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>员工业绩</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="Css/style.css" />
<script type="text/javascript" src="Js/Json.js"></script>
<script type="text/javascript" src="Js/jquery.js"></script>
<script type="text/javascript" src="Js/bootstrap.js"></script>
<script type="text/javascript" src="Js/ckform.js"></script>
<script type="text/javascript" src="Js/common.js"></script>
<script language="javascript" src="Js/YMDClass.js"></script>
<script src="Js/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
<script src="Js/amcharts/amcharts/serial.js" type="text/javascript"></script>
<script src="Js/amcharts/amcharts/pie.js" type="text/javascript"></script>
<script src="Js/amcharts/themes/light.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".day").hide();
	$(".month").hide();
	$("#dateShow").hide();
	//分页数据
	var nowPage=1;// 当前页码
	var pageTotal;// 总页码
	var rows;// 总条数
	var rowsTotal;//每页显示条数 
	$("#parentid").click(function() {
		var st=$("#parentid").val();//获取选中项的值
		//alert(st);
		if(st==1){//选择各年
			$(".day").hide();
			$(".month").hide();
			$(".year").hide();
			$("#dateShow").hide();
		}else if(st==2){//选择某年
			$(".year").show();
			var year = $(".year").val();
			var month = $(".month").val();
			var day = $(".day").val();
			$(".day").hide();
			$(".month").hide();
			$("#dateShow").show();
			
		}else if(st==3){//选择某年某月
			var year = $(".year").val();
			var month = $(".month").val();
			var day = $(".day").val();
			$(".year").show();
			$(".month").show();
			$(".day").hide();
			$("#dateShow").show();
			
		}else if(st==4){//选择某年某月某日
			var year = $(".year").val();
			var month = $(".month").val();
			var day = $(".day").val();
			$(".year").show();
			$(".day").show();
			$(".month").show();
			$("#dateShow").show();
		}
	});
	$("#but5").click(function() {//查找 显示第一页//显示
		var nowPage=1;
		ajaxAfter(nowPage);
		$("#toExcel").show();
	});
	//请求调用函数；
	function ajaxAfter(nowPage){//ajax 传值给后台
    	$("#TB").empty();
		var year = null;
		var month = null;
		var day = null;
		//表头
		var tabletitle="<thead><tr><th>员工编号</th><th>员工</th>";
		if (!$(".year").is(":hidden")) {
			year = $(".year").val();
			tabletitle+="<th>年</th>";
		}
		if (!$(".month").is(":hidden")) {
			month = $(".month").val();
			tabletitle+="<th>月</th>";
		}
		if (!$(".day").is(":hidden")) {
			day = $(".day").val();
			tabletitle+="<th>日</th>";
		}
			tabletitle+="<th>客户数量</th><th>住房总消费/¥</th><th>商品总消费/¥</th><th>合计/¥</th></tr></thead>";
			var params = {
			//username:$("#input1").val(),
			nowPage : nowPage,
			year : year,
			month : month,
			day : day
		};
		var url = 'AccountFindAction!allempPf';
		$.post(url, //服务器要接受的url  
		params, //传递的参数       
		function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据  
			//alert(data.length);//2016-06
			//var xx=data[1].username;
			//var cc=JSON.stringify(data[1]);//json对象转化为字符串
			//var dt=Date.parse(xx)//日期转化为字符串
			//var da=new Date(dt);
			//alert( da.getDate() );//获取日期
		  if(data.length>1){//第一条放的分页数据；所以从第一条判断
	           /*  $("#divtable").empty();
	            $("#toExcel").show();
	 			$("#butPic").show();
	 			$("#showtable").show(); */
	 			//解析分页数据data[0]封装的分页信息
	 			nowPage=data[0].nowPage;// 当前页码
				pageTotal=data[0].pageTotal;// 总页码
				rows=data[0].rows;// 总条数
				rowsTotal=data[0].rowsTotal;//每页显示条数
	 			//生成表格
	 			var s=printHtml(data,tabletitle,year,month,day);
				//生成分页nowPage
				var htmlPage=ajaxPage(nowPage,pageTotal,rows,rowsTotal);
	 			//alert(data.length);
	 			$("#TB").append(s+htmlPage);
	 			$("#firstPage").click(function(){
	 				if(nowPage!=1){
	 					nowPage=1;
	 					$("#TB").empty();
	 					ajaxAfter(nowPage);
	 				}
	 			});
	 			$("#upPage").click(function(){
	 				if(nowPage!=1){
	 					nowPage--;
	 					$("#TB").empty();
	 					ajaxAfter(nowPage); 
	 				}
	 				
	 			});
	 			$("#downPage").click(function(){
	 				if(nowPage!=pageTotal){
	 					nowPage++;
	 					$("#TB").empty();
	 					ajaxAfter(nowPage); 
	 				}
	 			}); 
	 			$("#lastPage").click(function(){
	 				if(nowPage!=pageTotal){
	 					nowPage=pageTotal;
	 					$("#TB").empty();
	 					ajaxAfter(nowPage); 
	 				}
	 			});
	 			$("#goPage").click(function(){//跳到那一页
	 				var goPage=Number($("#jumpTo").val());
	 				//int a=1;
	 				if(parseInt(goPage)==goPage&& goPage>0&&goPage<=pageTotal ){ //判断输入是否为大于1的数字
	 					nowPage=goPage;
	 					$("#TB").empty();
	 					ajaxAfter(nowPage);
	 				}else{//弹出提示提示
	 					alert("请输入大于0小于等于"+pageTotal+"的整数");
	 				}
	 			});
	 			//如果只有一页 ，上一页，下一页。尾页，首页，go都不显示
	 			/* if(pageTotal==1){
	 				$("#firstPage").hide();
	 				$("#upPage").hide();
	 				$("#downPage").hide();
	 				$("#lastPage").hide();
	 				$("#jumpTo").hide();
	 				$("#goPage").hide();
	 			} */
	 			/* //如果当前页是最后一页，不显示下一页
	 			if(nowPage==pageTotal){
	 				$("#downPage").hide();
	 			}
	 			//如果当前是第一页不显示，不显示上一页
	 			if(nowPage==1){
	 				$("#upPage").hide();
	 			} */
			  
		  }else{
			  $("#TB").empty();
			  $("#toExcel").hide();
			  $("#TB").append('<center><h >很抱歉没有业绩<h></center>');
		  } 
		
		}, "json");
	}
	
    function ajaxPage(nowPage,pageTotal,rows,rowsTotal){
    	var htmlPage='<div class="inline pull-right page" >';
    	htmlPage+='<span id="pagemess">'+rowsTotal+'条记录'+ nowPage+'/'+pageTotal+'页</span>';
    	htmlPage+='<a  id="firstPage">首页</a>';
    	htmlPage+='<a  id="upPage">上一页</a>'
    	htmlPage+='<a  id="downPage">下一页</a>';    
    	htmlPage+=' &nbsp;&nbsp;&nbsp;<input class="input-default" type="text" id="jumpTo" style="width:30px" />页 <input class="btn btn-primary" type="button" value="GO" id="goPage" />';
    	htmlPage+='<a  id="lastPage">最后一页</a><div>';
    	return htmlPage;
    	}

    function printHtml(data,tabletitle,year,month,day){
    	//alert(data.length);
    	s='<table id="table1" onselectstart="return false;" class="table table-bordered table-hover definewidth m10">';
		   s+=tabletitle+"<tbody>";
		for (var i = 1; i < data.length; i++) {
			s+="<tr  id='mt"+i+"'>";
		    s+="<td>"+data[i].userid+"</td>";//员工id
		    s+="<td>"+data[i].username+"</td>";
		  	var dt=Date.parse(data[i].date)//日期转化为字符串
			var d=new Date(dt);
		    if(year!=null){
		    	s+="<td>"+(d.getYear()+1900)+"</td>";}
			if(month!=null){
		    	s+="<td>"+(d.getMonth()+1)+"</td>";}
		    if(day!=null){
		    	s+="<td>"+d.getDay()+"</td>";}
		    s+="<td>"+data[i].cusNum+"</td>";
		    s+="<td>"+data[i].roomconsume+"</td>";
		   	s+="<td>"+(data[i].allconsume-data[i].roomconsume)+"</td>"; 
		    s+="<td>"+data[i].allconsume+"</td></tr>";
		    //s+="<tr>";
		}
		  s+="</tbody></table>";
		  return s;
    }
    $("#toExcel").click(function(){
    	var year = null;
		var month = null;
		var day = null;
		//表头
		var tabletitle="<thead><tr><th>员工</th>";
		if (!$(".year").is(":hidden")) {
			year = $(".year").val();
			tabletitle+="<th>年</th>";
		}
		if (!$(".month").is(":hidden")) {
			month = $(".month").val();
			tabletitle+="<th>月</th>";
		}
		if (!$(".day").is(":hidden")) {
			day = $(".day").val();
			tabletitle+="<th>日</th>";
		}
			tabletitle+="<th>客户数量</th><th>住房总消费/¥</th><th>商品总消费/¥</th><th>合计/¥</th></tr></thead>";
			var params = {
			//username:$("#input1").val(),
			rowsTotal : rowsTotal,
			year : year,
			month : month,
			day : day
		};
		var url = 'AccountFindAction!allempPfToExcel';
		$.post(url, //服务器要接受的url  
		params, //传递的参数       
		function cbf(data) {
			if(data.length>0){
				window.location.href=data[0].url;
			}else{
				alert("你不能导出excel");
			}
		}, "json");
    });
});
</script>
</head>
<body>
	<form action="#">
		<table class="table table-bordered table-hover m10" >
			<tr>
				<td style="width: 23%" class="tableleft">
				<span style="font-size: 18px">查询类别:</span>
				<select id="parentid" style="width: 160px" >
						<option value="1">&nbsp;员工所有业绩对比</option>
						<option value='2'>&nbsp;员工某年业绩对比</option>
						<option value='3'>&nbsp;员工某月业绩对比</option>
						<option value='4'>&nbsp;员工某天业绩对比</option>
				</select></td>
				<td width="27%" class="tableleft" id="dateShow"><span style="font-size: 18px">日期:</span><select name="year1" style="width: 80px" class="year"></select>
						<select name="month1" class="month" style="width: 80px"></select> <select
					name="day1" class="day" style="width: 80px"></select></td>
				<td class="tableleft" style="width: 50%"><button type="button" id="but5" class="btn btn-primary">查看</button></td>
			</tr>
		</table>
	</form>
	<div class="dateXXX">
		<table class="inline pull-right page" style="margin-top: -16px">
			<tr>
				<td><button type="button" id="toExcel" class="btn btn-primary"
						style="padding: 2px; margin-right: 10px">导出excel表</button></td>
			</tr>
		</table>
		<script type="text/javascript">
				$("#toExcel").hide();
		</script>
	</div>
	<!--  显示表格 -->
	<div id="TB" ></div>
	<script type="text/javascript">
		var d = new Date();
		var nowYear = d.getYear() + 1900;
		var nowMonth = d.getMonth() + 1;
		var nowDay = d.getDate();
		//new YMDselect('year1','month1','day1',nowYear,nowMonth,nowDay);
		new YMDselect('year1', 'month1', 'day1', nowYear, nowMonth, nowDay);
		</script>
</body>
</html>
