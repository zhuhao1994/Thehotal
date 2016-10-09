<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<script type="text/javascript" src="Js/sct.js"></script>
<!-- 自己定义的js -->
<!-- 图标 -->
<script src="Js/amcharts/amcharts/amcharts.js" type="text/javascript"></script>
<script src="Js/amcharts/amcharts/serial.js" type="text/javascript"></script>
<script src="Js/amcharts/amcharts/pie.js" type="text/javascript"></script>
<script src="Js/amcharts/themes/light.js" type="text/javascript"></script>

<script src="Js/htmlTableToExcele.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#but7").click(function(){
		$("#table1").show();
		$("#chart").hide();
		$("#ytitle").show();
	});
});
	//var keysArr = new Array("月份", "月住房消费", "月总消费");
	function TableToJson(tableid) { //tableid是你要转化的表的表名，是一个字符串，如"example" 
		var keysArr = new Array("month", "monthRoomAccount", "monthAll");
		var rows = document.getElementById(tableid).rows.length; //获得行数(包括thead) 
		var colums = document.getElementById(tableid).rows[0].cells.length; //获得列数 
		var json = "[";
		var tdValue;
		for (var i = 1; i < rows; i++) { //每行 
			json += "{";
			//取  1 3 5列
			for (var j = 1; j <= 5; j += 2) {
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
		//alert(json);
		return json;
	}

	function makeCharts(theme, bgColor, bgImage) {
		$("#chart").css("display","list-item");
		$("#table1").hide();
		$("#ytitle").hide();
		var ytitle=$("#ytitle").text()+"单位:¥";
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
			categoryField : "month",
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
				title : "月住房消费",
				valueField : "monthRoomAccount",
				lineAlpha : 0,
				fillAlphas : 0.8,
				balloonText : "[[category]][[title]]:<b>[[value]]</b>"
			}, {
				type : "line",
				title : "月总消费",
				valueField : "monthAll",
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
			titleField : "month",
			valueField : "monthAll",
			balloonText : "[[title]]月份<br><b>[[value]]</b> ([[percents]]%)",
			legend : {
				align : "center",
				markerType : "circle"
			}
		});
	}
</script>
<!-- <script type="text/javascript" >
$(document).ready(function(){//伪分页
	$("#up").hide();
	var listLength=$("#countpage").attr("class");
	var countpage=Math.ceil(listLength/8);
	if(countpage==1){
		  $("#down").hide(); 
	  }
	$("#countpage").text("共"+countpage+"页");
	$("#ppp").text(1+"/"+countpage+"页");
	var lastcount=listLength-(countpage-1)*8;
	for (var i = 9; i <=listLength; i++) {
		$("#mt"+i).hide();
	}
	var nowpage=1;
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
</script> -->
<!-- <script type="text/javascript" language="javascript">
	var idTmr;
	function getExplorer() {
		var explorer = window.navigator.userAgent;
		//ie  
		if (explorer.indexOf("MSIE") >= 0) {
			return 'ie';
		}
		//firefox  
		else if (explorer.indexOf("Firefox") >= 0) {
			return 'Firefox';
		}
		//Chrome  
		else if (explorer.indexOf("Chrome") >= 0) {
			return 'Chrome';
		}
		//Opera  
		else if (explorer.indexOf("Opera") >= 0) {
			return 'Opera';
		}
		//Safari  
		else if (explorer.indexOf("Safari") >= 0) {
			return 'Safari';
		}
	}
	function method5(tableid) {
		var curTbl = document.getElementById(tableid);
		if (getExplorer() == 'ie') {
			var curTbl = document.getElementById(tableid);
			var oXL = new ActiveXObject("Excel.Application");
			var oWB = oXL.Workbooks.Add();
			var xlsheet = oWB.Worksheets(1);
			var sel = document.body.createTextRange();
			sel.moveToElementText(curTbl);
			sel.select();
			sel.execCommand("Copy");
			xlsheet.Paste();
			oXL.Visible = true;

			try {
				var fname = oXL.Application.GetSaveAsFilename("Excel.xls",
						"Excel Spreadsheets (*.xls), *.xls");
			} catch (e) {
				print("Nested catch caught " + e);
			} finally {
				oWB.SaveAs(fname);
				oWB.Close(savechanges = false);
				oXL.Quit();
				oXL = null;
				idTmr = window.setInterval("Cleanup();", 1);
			}

		} else {
			tableToExcel(tableid)
		}
	}
	function Cleanup() {
		window.clearInterval(idTmr);
		CollectGarbage();
	}
	var tableToExcel = (function() {
		var uri = 'data:application/vnd.ms-excel;base64,', template = '<html><head><meta charset="UTF-8"></head><body><table>{table}</table></body></html>', base64 = function(
				s) {
			return window.btoa(unescape(encodeURIComponent(s)))
		}, format = function(s, c) {
			return s.replace(/{(\w+)}/g, function(m, p) {
				return c[p];
			})
		}
		return function(table, name) {
			if (!table.nodeType)
				table = document.getElementById(table)
			var ctx = {
				worksheet : name || 'Worksheet',
				table : table.innerHTML
			}
			window.location.href = uri + base64(format(template, ctx))
		}
	})()
</script> -->
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
	/* 
	 $(document).ready(function(){
	 $(".day").hide();
	 $(".month").hide();
	 $(".year").hide();
	 $("#but5").hide();
	 $("#but1").click(function(){
	 $(".day").hide();
	 $(".month").hide();
	 $(".year").hide();
	 $(".tablelist").show();
	 $("#but5").hide();
	 $(".message").hide();
	 window.location.href='AccountFindAction!countByRecords';
	 });
	 $("#but2").click(function(){
	 var year=$(".year").val();
	 var month=$(".month").val();
	 var day=$(".day").val();
	 $("#but5").show();
	 $(".year").show();
	 $(".day").hide();
	 $(".month").hide();
	 $(".tablelist").hide();
	 $(".message").text("请选择年");
	 });
	 $("#but3").click(function(){
	 var year=$(".year").val();
	 var month=$(".month").val();
	 var day=$(".day").val();
	 $("#but5").show();
	 $(".year").show();
	 $(".month").show();
	 $(".day").hide();
	 $(".tablelist").hide();
	 $(".message").text("请选择年月");
	 });
	 $("#but4").click(function(){
	 var year=$(".year").val();
	 var month=$(".month").val();
	 var day=$(".day").val();
	 $("#but5").show();
	 $(".day").show();
	 $(".month").show();
	 $(".year").show();
	 $(".tablelist").hide();
	 $(".message").text("请选择年月日");
	 });
	 $("#but5").click(function(){
	 var year=null;
	 var month=null;
	 var day=null;
	 //alert($(".month").is(":hidden"));
	 if(!$(".year").is(":hidden")){
	 year=$(".year").val();
	 }
	 if(!$(".month").is(":hidden")){
	 month=$(".month").val();
	 }
	 if(!$(".day").is(":hidden")){
	 day=$(".day").val();
	 }
	 window.location.href='AccountFindAction!countByRecords?year='+year+"&month="+month+"&day="+day;
	 });
	 }); */
	/* function countAll() {
		window.location.href = 'AccountFindAction!countByRecords';
	}
	function countByYear() {
		var year = $(".year").val();
		window.location.href = 'AccountFindAction!countByRecords?year=' + year;

	}
	function countByMonth() {
		var year = $(".year").val();
		var month = $(".month").val();
		window.location.href = 'AccountFindAction!countByRecords?year=' + year
				+ "&month=" + month;
	}
	function countByXX() {
		var year = $(".year").val();
		var month = $(".month").val();
		var day = $(".day").val();
		window.location.href = 'AccountFindAction!countByRecords?year=' + year
				+ "&month=" + month + "&day=" + day;
	} */
</script>
</head>
<body>
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
			<c:if test="${requestScope.countByRecords.size() !=0}">
			<span  id="ytitle" class="inline pull-left page" style="margin-left: 25px" ><h4 style="text-align: center;">${requestScope.countByRecords.get(0).date.getYear()+1900}年&nbsp;&nbsp;每月账目
			</h4></span>
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
					<tr><td width="55%"><div id="chartdiv1" style="width: 620px; height: 400px;"></div></td>
						<td width="50%"><div id="chartdiv2" style="width: 600px; height: 400px;"></div></td>
					</table>
				</div>
				<table class="table table-bordered table-hover definewidth m10"
					id="table1" onselectstart="return false;">
					<thead>
						<tr class='warning'>
							<th style="align: center">❤ 年</th>
							<th>⊙ 月份</th>
							<th>⊙ 客户数量</th>
							<th>¥ 住房总消费</th>
							<th>¥ 商品总消费</th>
							<th>¥ 合计</th>

						</tr>
					</thead>
					<tbody>
					<c:forEach var="yc" items="${requestScope.countByRecords}" varStatus="s">
						<tr id="mt${s.count}" onclick="findmonth(${yc.date.getYear()+1900},${yc.date.getMonth()+1})">
							<td> ${yc.date.getYear()+1900}</td>
							<td> ${yc.date.getMonth()+1}</td>
							<td>${yc.cusNum}</td>
							<td>${yc.consum}</td>
							<td>${yc.allconsum - yc.consum}</td>
							<td>${yc.allconsum}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<%-- <div class="inline pull-right page" >
					 <span id="countpage" class="${requestScope.countByRecords.size()}" ></span>
		             <span id="ppp"></span>
		             <a id="down">下一页</a>
		             <a id="up" >上一页</a>
			</div> --%>
			</c:if>
			<c:if test="${requestScope.countByRecords.size() ==0}">
				<span id="tipMess">亲：不存在该记录</span>
			</c:if>
		</div>
	</center>
	<script type="text/javascript">
		var d = new Date();
		var nowYear = d.getYear() + 1900;
		var nowMonth = d.getMonth() + 1;
		var nowDay = d.getDate();
		//new YMDselect('year1','month1','day1',nowYear,nowMonth,nowDay);
		new YMDselect('year1', 'month1', 'day1', nowYear, nowMonth, nowDay);
		function findmonth(year,month){
			window.location.href='AccountFindAction!countByRecords?year='+year+'&month='+month;
		}
	</script>
</body>
</html>
