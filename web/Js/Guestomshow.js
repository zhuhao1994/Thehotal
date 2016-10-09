$(document).ready(function() {

	$("#thechose").change(function() {
		var thechose = $("#thechose").val();
		$("#choseFloor").val("整栋");
		if(thechose == "全部") {
			showGustRoom('','');
		} else if(thechose == "入住") {
			showGustRoom('rgb(75, 187, 40)','');
		} else if(thechose == "空闲") {
			showGustRoom('rgb(164, 198, 57)','rgb(28, 163, 243)');
		}
	});

	//查看状态
	function showGustRoom(theRgb,theRgb1) {
		$("section span").each(function() {
//			var theclass = $(this).attr("class");
//			alert(theclass);
			if($(this).css('background-color') == theRgb || $(this).css('background-color') == theRgb1  ) {
				$(this).css('display','none'); 
			}else{
				$(this).css('display','block');
			}
		});
	}
	
	//分楼层
	function changeFoor()
	{
		$("#thechose").val("全部");
		var floor = $("#choseFloor").val();
		console.log(floor);
		$("section span").each(function(){
			var tf = $(this).children().first().text();
			var tf1 = tf.charAt(0);
				$(this).css('display','block')
			if(floor == "一楼" && tf1 != 1)
				$(this).css('display','none')
			if (floor == "二楼" && tf1 != 2)
				$(this).css('display', 'none')
			if (floor == "三楼" && tf1 != 3)
				$(this).css('display', 'none')
			if (floor == "四楼" && tf1 != 4)
				$(this).css('display', 'none')
			if (floor == "五楼" && tf1 != 5)
				$(this).css('display', 'none')
			if (floor == "六楼" && tf1 != 6)
				$(this).css('display', 'none')
			if (floor == "七楼" && tf1 != 7)
				$(this).css('display', 'none')
		})
				
	}
	$("#choseFloor").change(function(){
		changeFoor();
	}); 
});