<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<script type="text/javascript" src="Js/jquery.sorted.js"></script>
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
		$(".year").hide();
		$("#toExcel").hide();
		$("#butPic").hide();
		$("#showtable").hide();
		$("#dateShow").hide();
		//分页数据
		var listLength;
		var countpage;
		var nowpage;
		var lastcount;
		$("#parentid").click(function() {
			var st=$("#parentid").val();//获取选中项的值
			//alert(st);
			if(st==1){
				$("#dateShow").hide();
				$(".day").hide();
				$(".month").hide();
				$(".year").hide();
				$(".picTab").show();
				$("#but5").hide();
				$(".message").hide();
				$("#showtable").hide();
				$("#divtable").empty();
				$("#but5").show();
				$("#toExcel").hide();
				$("#butPic").hide();
			}else if(st==2){
				$("#dateShow").show();
				var year = $(".year").val();
				var month = $(".month").val();
				var day = $(".day").val();
				$("#but5").show();
				$(".year").show();
				$(".day").hide();
				$(".month").hide();
				$(".picTab").hide();
				$(".message").show();
				$("#showtable").hide();
				$("#toExcel").hide();
				$("#butPic").hide();
				$("#divtable").empty();
				
			}else if(st==3){
				$("#dateShow").show();
				var year = $(".year").val();
				var month = $(".month").val();
				var day = $(".day").val();
				$("#but5").show();
				$(".year").show();
				$(".month").show();
				$(".day").hide();
				$(".picTab").hide();
				$(".message").show();
				$("#showtable").hide();
				$("#toExcel").hide();
				$("#butPic").hide();
				$("#divtable").empty();
				
			}else if(st==4){
				$("#dateShow").show();
				var year = $(".year").val();
				var month = $(".month").val();
				var day = $(".day").val();
				$("#but5").show();
				$(".day").show();
				$(".month").show();
				$(".year").show();
				$(".picTab").hide();
				$(".message").show();
				$("#showtable").hide();
				$("#toExcel").hide();
				$("#butPic").hide();
				$("#divtable").empty();
				
			}
		});
		$("#showtable").click(function(){
			$("#divtable").show();
			$("#chart").hide();
		});
	    $("#but5").click(function() {//查找
	    	$(".picTab").show();
	    	$("#divtable").show();
	    	$("#chart").hide();
			var year = null;
			var month = null;
			var day = null;
			//表头
			var tabletitle="<thead><tr><th>员工</th><th>年</th>";
			//分页
			var nowpage=1;
			var page="<div class='inline pull-right page' ></div>";
			var username=$("#input1").val();
			var ytitle=""+username;
			var danwei="年";
			//alert($(".month").is(":hidden"));
			if (!$(".year").is(":hidden")) {
				year = $(".year").val();
				tabletitle+="<th>月</th>";
				ytitle+=year+"年";
				danwei="个月";
			}
			if (!$(".month").is(":hidden")) {
				month = $(".month").val();
				tabletitle+="<th>日</th>";
				ytitle+=month+"月";
				danwei="天";
			}
			if (!$(".day").is(":hidden")) {
				day = $(".day").val();
				ytitle+=month+"日";
			}
				tabletitle+="<th>客户数量</th><th>住房总消费/¥</th><th>商品总消费/¥</th><th>合计/¥</th></tr></thead>";
			var params = {
				username:$("#input1").val(),
				year : year,
				month : month,
				day : day
			};
			var url = 'AccountFindAction!empPf';
			$.post(url, //服务器要接受的url  
			params, //传递的参数       
			function cbf(data) { //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据  
				//alert(data[0]);//2016-06
			  if(data.length!=0){
				  /* $.each(data,function(index,xx){  
					 	alert(index);  
		             });   */
		           $("#divtable").empty();
		            $("#toExcel").show();
		 			$("#butPic").show();
		 			$("#showtable").show();
		             //统计总和：
		             var all=0;
		             for (var i = 0; i < data.length; i++) {
		            	 all+=data[i].allcon;
		             }
		             //class="inline pull-right page"
		            // $("#tjmess").text(ytitle+'工作了'+data.length+danwei+' 业绩总和:'+all+'¥');//&nbsp;&nbsp;&nbsp;
		           var s='<h4 class="inline pull-left page" style="margin-left: 25px"><span class="inline pull-right page" id="ytitle">'+ytitle+'工作了'+data.length+danwei+' 业绩总和:¥'+all+'</span></h4>';
		           s+='<table id="table1" onselectstart="return false;" class="table table-bordered table-hover definewidth m10">';
				   s+=tabletitle+"<tbody>";
				   for (var i = 0; i < data.length; i++) {
					s+="<tr  id='mt"+(i+1)+"'>";
				    s+="<td>"+data[i].emp+"</td>";
				    s+="<td>"+data[i].year+"</td>";
				    if(year!=null){
				    	s+="<td>"+data[i].month+"</td>";}
					if(month!=null){
				    	s+="<td>"+data[i].day+"</td>";}
				    s+="<td >"+data[i].cusNum+"</td>";
				    s+="<td>"+data[i].roomconsum+"</td>";
				    s+="<td>"+data[i].goodconsum+"</td>";
				    s+="<td>"+data[i].allcon+"</td></tr>";
				    //s+="<tr>";
				}
				  s+="</tbody></table>"
				  if(!(year!=null&&month==null)){
				   var cutpage='<div class="inline pull-right page"> ';
					  cutpage+='<a id="countpage" class=""'+data.length+'"" ></a>';
					  cutpage+='<span id="ppp">'+1+'/'+Math.ceil(data.length/8)+'</span>';
					  cutpage+='<a id="down">下一页</a>';
					  cutpage+='<a id="up" >上一页</a></div>';
				  	  s+=cutpage;
				  }
				  	  //alert(s);
					  $("#divtable").append(s);
					 // alert(document.getElementById("table1").rows[2].innerHTML);//rows[i].cells[j].innerHTML
					  //分页
					  $("#up").hide();
					  listLength=data.length;
					  nowpage=1;
					  countpage=Math.ceil(listLength/8);
					  lastcount=listLength-(countpage-1)*8;
					  if(countpage==1){
						  $("#down").hide(); 
					  }
					  for (var i = 9; i <=listLength; i++) {
							$("#mt"+i).hide();
						}
					  
						$("#up").click(function(){//上一页
							if(nowpage!=1){
								//alert(nowpage!=countpage);
								if(nowpage!=countpage){
									for (var i = 1; i <=8; i++) {
										$("#mt"+(nowpage*8-8-i+1)).show();
										$("#mt"+(nowpage*8-8+i)).hide();
									}
								}else{
									for (var i = 1; i <=lastcount; i++) {
										$("#mt"+(nowpage*8-8+i)).hide();
									}
									for (var i = 1; i <=8; i++) {
										$("#mt"+(nowpage*8-8-i+1)).show();
									}
								}
								
									nowpage--;
									$("#ppp").text(nowpage+"/"+countpage+"页");
									if(nowpage==1){
										$("#up").hide();
									}
									$("#down").show(); 
							}
							
						});
						$("#down").click(function(){//下一页
							//listLength=$("#countpage").attr("class");
							//countpage=Math.ceil(listLength/8);
							if(nowpage!=countpage){
								for (var i = 1; i <=8; i++) {
									$("#mt"+(nowpage*8+i)).show();
									$("#mt"+(nowpage*8-i+1)).hide();
								}
							}
							nowpage++;
							$("#ppp").text(nowpage+"/"+countpage+"页");
							if(nowpage==countpage){
								$("#down").hide();
							}
							$("#up").show();
						});
			  }else{
				  $("#divtable").empty();
				  $("#toExcel").hide();
				  $("#butPic").hide();
				  $("#showtable").hide();
				  $("#divtable").append("<h>很抱歉没有你的业绩<h>");
			  } 
			
			}, "json");
		});
	    $("#toExcel").click(function(){
			//alert("11111");
			var year = null;
			var month = null;
			var day = null;
			var cutdateNum=null;
			if (!$(".year").is(":hidden")) {
				year = $(".year").val();
			}
			if (!$(".month").is(":hidden")) {
				month = $(".month").val();
			}
			if (!$(".day").is(":hidden")) {
				day = $(".day").val();
			}
			var params = {
					username:$("#input1").val(),
					year : year,
					month : month,
					day : day
				};
			var url = 'AccountFindAction!dateToexcel';
			$.post(url, //服务器要接受的url  
			params, //传递的参数       
			function cbf(data) { 
				//alert(data[0].url);
				window.location.href=data[0].url;
			}
			,"json");
		});
	});
</script>
<script type="text/javascript">
function TableToJson(tableid) { //tableid是你要转化的表的表名，是一个字符串，如"example" 
	var keysArr = new Array("day", "dayRoomAccount", "dayAll");
	var rows = document.getElementById(tableid).rows.length; //获得行数(包括thead) 
	var colums = document.getElementById(tableid).rows[0].cells.length; //获得列数 
	//alert(rows+"----"+colums);
	var json = "[";
	var tdValue;
	var k=1;
	//判断界面显示的是什么数据！//所有年 从列号1 开始；某年2；某月：3；某日：3
	if (!$(".year").is(":hidden")) {
				k=2;
		}
	if (!$(".month").is(":hidden")) {
		k=3;
	}
	if (!$(".day").is(":hidden")) {
		k=3;
	}
	for (var i = 1; i < rows; i++) { //每行 
		json += "{";
		//取  1 3 5列
		for (var j = k; j <= (k+4); j += 2) {
			tdName = keysArr[parseInt((j-k) / 2)]; //Json数据的键 
			json += "\""; //加上一个双引号 
			json += tdName;
			json += "\"";
			json += ":";
			tdValue = document.getElementById(tableid).rows[i].cells[j].innerHTML;//Json数据的值 
			json += tdValue.substring(0, tdValue.length);
			json += ",";
		}
		json = json.substring(0, json.length - 1);
		json += "}";
		json += ",";
	}
	json = json.substring(0, json.length - 1);
	json += "]";
	return json;
}

function makeCharts(theme, bgColor, bgImage) {
	$("#chart").css("display","list-item");
	$("#divtable").hide();
	//var xvalue;//数据显示的前缀
	var tvalue="年";//图形代表的数据类型
	var username=$("#input1").val();
	var ytitle=""+username;
	if (!$(".year").is(":hidden")) {
		year = $(".year").val();
		//xvalue="月";
		tvalue="月";
		ytitle+=year+"年";
	}
	if (!$(".month").is(":hidden")) {
		//xvalue="号";
		tvalue="日";
		month = $(".month").val();
		ytitle+=month+"月";
	}
	if (!$(".day").is(":hidden")) {
	}
	ytitle+="消费情况 单位:¥"
	//var ytitle=$("#ytitle").text()+"单位:¥";
	var chart1;
	var chart2;
	/* var theme="light";
	var bgColor="FFFFFF";
	var bgImage=""; */
	//调用方法获取 封装后的table的json字符串
	var tablejson = TableToJson("table1");
	if (chart1) {
		chart1.clear();
	}
	if (chart2) {
		chart2.clear();
	}

	// background
	if (document.body) {
		document.body.style.backgroundColor = bgColor;
		document.body.style.backgroundImage = "url(" + bgImage + ")";
	}

	// column chart
	chart1 = AmCharts.makeChart("chartdiv1", {
		type : "serial",
		theme : theme,
		dataProvider : jQuery.parseJSON(tablejson),
		categoryField : "day",
		startDuration : 1,

		categoryAxis : {
			gridPosition : "start"
		},
		//y轴坐标名称
		valueAxes : [ {
			title : ytitle
		} ],
		graphs : [ {
			type : "column",
			title : tvalue+"住房消费",
			valueField : "dayRoomAccount",
			lineAlpha : 0,
			fillAlphas : 0.8,
			balloonText : "[[category]][[title]]:<b>[[value]]</b>"
		}, {
			type : "line",
			title : tvalue+"总消费",
			valueField : "dayAll",
			lineThickness : 2,
			fillAlphas : 0,
			bullet : "round",
			balloonText : "[[category]][[title]] :<b>[[value]]</b>"
		} ],
		legend : {
			useGraphSettings : true
		}

	});
}


</script>
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
</head>
<body>
	<table class="table table-bordered table-hover m10">
    <tr>
    <c:if test="${session.loginUser.position == 'admin'}">
    	<td width="18%" class="tableleft"><span style="font-size: 18px ;margin-left: 10px" >员工名称:</span><input id="input1" type="text" value="" style="width: 80px"  /></td>
    </c:if>
    <c:if test="${session.loginUser.position != 'admin'}">
    	<td width="18%" class="tableleft"><span style="font-size: 18px ;margin-left: 10px" >员工名称:</span><input id="input1" type="text" value="${session.loginUser.username}" style="width: 80px" readOnly="true" /></td>
    </c:if>
        <td width="20%" class="tableleft" >
        	<span style="font-size: 18px">查询类别</span>
	        <select id="parentid" style="width: 100px">
		            <option value="1">&nbsp;各年业绩</option>
		            <option value='2' >&nbsp;某年业绩</option>
		            <option value='3' >&nbsp;某月业绩</option>           
		            <option value='4' >&nbsp;某天业绩</option>           
	          </select>
        </td>
        <td width="30%" class="tableleft" id="dateShow"><span style="font-size: 18px" >日期</span><select name="year1" style="width: 80px" class="year"></select> 
				<select name="month1" class="month" style="width: 80px"></select> 
				<select name="day1" class="day" style="width: 80px"></select>
		</td>
        <td class="tableleft">
            <button type="button" id="but5" class="btn btn-primary">查看</button> &nbsp;&nbsp;
        </td>
    </tr>
</table>
			<div class="dateXXX">
				<table  class="inline pull-right page">
					<tr >
						<td><button type="button" id="showtable" class="btn btn-primary" style="padding: 2px;margin-right: 10px">查看表格</button></td>
						<td><button type="button" id="toExcel" class="btn btn-primary" style="padding: 2px;margin-right: 10px">生成excel表</button></td>
						<td><button type="button" id="butPic" onclick='makeCharts("light", "#FFFFFF","")' style="display: list-item;text-align: left; padding: 2px" class="btn btn-primary">查看统计图</button></td>
					</tr>
				</table>
			</div>
		<center>
		<div class="picTab">
			<!--  显示表格 -->
			<div id="divtable">
			</div>
			<div id="chart" style="display:none;"><!-- 图显示区 -->
					<table >
					<tr><td><div id="chartdiv1" style="width: 600px; height: 400px;"></div></td>
					</table>
			</div>
		</div>
		</center>
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
