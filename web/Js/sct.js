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
	});
