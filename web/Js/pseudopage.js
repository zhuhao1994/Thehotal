/**
 * 上一页id=up
 * 下一页id=down
 * id=countpage 的为总页数 class设置为 总页数
 * 在table 每行tr标签中设置 id=#mt{s.count} s为迭代状态
 */
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