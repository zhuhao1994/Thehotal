<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>显示账目</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="Css/style.css" />
<script type="text/javascript" src="Js/jquery.js"></script>
<script type="text/javascript" src="Js/bootstrap.js"></script>
<script type="text/javascript" src="Js/ckform.js"></script>
<script type="text/javascript" src="Js/common.js"></script>
<script language="javascript" src="Js/YMDClass.js"></script>
<script type="text/javascript" src="Js/sct.js"></script><!-- 自己定义的js -->
<!-- 图标 -->
<script src="Js/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
<script src="Js/amcharts/amcharts/serial.js" type="text/javascript"></script>
<script src="Js/amcharts/amcharts/pie.js" type="text/javascript"></script>
<!-- theme files. you only need to include the theme you use.
             feel free to modify themes and create your own themes-->
<script src="Js/amcharts/themes/light.js" type="text/javascript"></script>
<script src="Js/htmlTableToExcele.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#but7").click(function(){
		$("#table1").show();
		$("#chart").hide();
		$("#fenye").show();
		$("#ytitle").show();
	});
});
	
	//var keysArr = new Array("月份", "月住房消费", "月总消费");
	function TableToJson(tableid) { //tableid是你要转化的表的表名，是一个字符串，如"example" 
		var keysArr = new Array("year", "yearRoomAccount", "yearAll");
		var rows = document.getElementById(tableid).rows.length; //获得行数(包括thead) 
		var colums = document.getElementById(tableid).rows[0].cells.length; //获得列数 
		var json = "[";
		var tdValue;
		for (var i = 1; i < rows; i++) { //每行 
			json += "{";
			//取  1 3 5列
			for (var j = 0; j <= 4; j += 2) {
				tdName = keysArr[parseInt(j / 2)]; //Json数据的键 
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
		$("#table1").hide();
		$("#fenye").hide();
		$("#ytitle").hide();
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
			categoryField : "year",
			startDuration : 1,

			categoryAxis : {
				gridPosition : "start"
			},
			//y轴坐标名称
			valueAxes : [ {
				title : "每年消费 单位:¥"
			} ],
			graphs : [ {
				type : "column",
				title : "年住房消费",
				valueField : "yearRoomAccount",
				lineAlpha : 0,
				fillAlphas : 0.8,
				balloonText : "[[category]][[title]]:<b>[[value]]</b>"
			}, {
				type : "line",
				title : "年总消费",
				valueField : "yearAll",
				lineThickness : 2,
				fillAlphas : 0,
				bullet : "round",
				balloonText : "[[category]][[title]] :<b>[[value]]</b>"
			} ],
			legend : {
				useGraphSettings : true
			}

		});

		// pie chart
		chart2 = AmCharts.makeChart("chartdiv2", {
			type : "pie",
			theme : theme,
			dataProvider : jQuery.parseJSON(tablejson),
			titleField : "year",
			valueField : "yearAll",
			balloonText : "[[title]]年<br><b>[[value]]</b> ([[percents]]%)",
			legend : {
				align : "center",
				markerType : "circle"
			}
		});
	}
</script>
<script type="text/javascript" >
$(document).ready(function(){//伪分页
	$("#up").hide();
	var listLength=$("#countpage").attr("class");
	var countpage=Math.ceil(listLength/8);
	$("#countpage").text("共"+countpage+"页");
	$("#ppp").text(1+"/"+countpage+"页");
	var lastcount=listLength-(countpage-1)*8;
	for (var i = 9; i <=listLength; i++) {
		$("#mt"+i).hide();
	}
	var nowpage=1;
	if(countpage==1){
		  $("#down").hide(); 
	  }
	$("#down").click(function(){//下一页
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
	$("#up").click(function(){//上一页
		if(nowpage!=1){
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
	
});
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
<script>
</script>
</head>
<body >
		<center>
		<form class="form-inline definewidth m20" class="btn btn-primary">
			<button type="button" id="but1" class="btn btn-primary">查看以往记录</button>
			<button type="button" id="but2" class="btn btn-primary">查看某年消费统计</button>
			<button type="button" id="but3" class="btn btn-primary">查看月消费统计</button>
			<button type="button" id="but4" class="btn btn-primary">查看日消费统计</button>
			<br /> <br />
			<div class="dateXXX">
				<span class="message"></span> <select name="year1"
					style="width: 80px" class="year"></select> <select name="month1"
					class="month" style="width: 80px"></select> <select name="day1"
					class="day" style="width: 80px"></select>
				<button type="button" id="but5" class="btn btn-primary">查看</button>
			</div>
		</form>
		<div class="tablelist" >
			<c:if test="${requestScope.countByRecords !=null}">
				<span  id="ytitle" class="inline pull-left page" style="margin-left: 25px" ><h4 style="text-align: center;">各年账目</h4></span>
				<table border="0" class="inline pull-right page">
					<tr><td>
					 
					</td>
					<td>
					<button type="button" onclick='makeCharts("light", "#FFFFFF","")' style="display: list-item;text-align: left; " class="btn btn-primary">查看统计图</button>
					</td>
					<td>
						<button  onClick ="method5('table1')" class="btn btn-primary">生成excel表</button>
					</td>
					<td>
						<button  id="but7" class="btn btn-primary">查看表格</button>
					</td>
					</tr>
				</table>
				<div id="chart" style="display:none;"><!-- 图显示区 -->
					<table>
					<tr><td><div id="chartdiv1" style="width: 600px; height: 400px;"></div></td>
						<td><div id="chartdiv2" style="width: 600px; height: 400px;"></div></td>
					</table>
				</div>
				<table class="table table-bordered table-hover definewidth m10"
					id="table1" onselectstart="return false;">
		<thead>
			<tr >
				<th style="align: center">❤ 年</th>
				<!-- <th>⊙ 月份</th> -->
				<th>⊙ 客户数量</th>
				<th>¥ 住房总消费</th>
				<th>¥ 商品总消费</th>
				<th>¥ 合计</th>
				
			</tr>
		</thead>
		<c:forEach var="yc" items="${requestScope.countByRecords}" varStatus="s" >
		<tr id="mt${s.count}" onclick="findyear(${yc.date.getYear()+1900})">
					<td>${yc.date.getYear()+1900}</td>
					<%-- <td>❦ ${yc.date.getMonth()+1}</td> --%>
					<td>${yc.cusNum}</td>
					<td>${yc.consum}</td>
					<td>${yc.allconsum - yc.consum}</td>
					<td>${yc.allconsum}</td>
					
		</tr>
		</c:forEach>
	</table>
	<div class="inline pull-right page" id="fenye">
					 <span id="countpage" class="${requestScope.countByRecords.size()}" ></span>
		             <span id="ppp"></span>
		             <a id="down">下一页</a>
		             <a id="up" >上一页</a>
			</div>
	</c:if>
	</div></center>
<script type="text/javascript">
	 var d=new Date();
	var nowYear=d.getYear()+1900;
	var nowMonth=d.getMonth()+1;
	var nowDay=d.getDate(); 
	//new YMDselect('year1','month1','day1',nowYear,nowMonth,nowDay);
	new YMDselect('year1','month1','day1',nowYear,nowMonth,nowDay);
function findyear(year){
	window.location.href='AccountFindAction!countByRecords?year='+year;
}
</script>
</body>
</html>
