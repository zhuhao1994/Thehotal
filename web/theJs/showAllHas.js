function jumpTo(maxPage) {
	var page = $("#jumpTo").val();
	var content = $("#menuname").val();
	content = encodeURI(encodeURI(content));
	  var thereplace = "theKey=yes&keyContent=" + content;
	  
//	  if('${sessionScope.theKey}' == 'cusName')
//		   thereplace = "theKey=cusName&keyContent=" + content;
//	  else if('${sessionScope.theKey}' == 'roomType')
//		   thereplace = "theKey=roomType&keyContent=" + content;
//	  else if('${sessionScope.theKey}' == 'userName')
//		   thereplace = "theKey=userName&keyContent=" + content;
	  
		  goUrl = 'HotelAction!shoaPageHa?nowpage=' + page + '&'+thereplace;
	if (page > maxPage || page < 1) {
		alert("对不起，无法到达该页")
	} else {
		window.location.href = goUrl;
	}
}

$(document).ready(function(){
	  changeHref();
	  //按客户查询
	  $("#byCustomerName").click(function(){
     	  var content = $("#menuname").val();
		  content = encodeURI(encodeURI(content));//转换编码
			var urlContent = "HotelAction!shoaPageHa?nowpage=1&theKey=cusName&keyContent=" + content;
			window.location.href = urlContent;
	  });
	  //按房间查询
	  $("#byGustName").click(function(){
     	  var content = $("#menuname").val();
		  content = encodeURI(encodeURI(content));//转换编码
			var urlContent = "HotelAction!shoaPageHa?nowpage=1&theKey=roomType&keyContent=" + content;
			window.location.href = urlContent;
	  });
	  //按用户查询
	  $("#byUserName").click(function(){
     	  var content = $("#menuname").val();
		  content = encodeURI(encodeURI(content));//转换编码
			var urlContent = "HotelAction!shoaPageHa?nowpage=1&theKey=userName&keyContent=" + content;
			window.location.href = urlContent;
	  });
});	

//改变地址：分页地址
function changeHref()
{
	 var content = $("#menuname").val();
	  content = encodeURI(encodeURI(content));//转换编码
	  var thereplace = "theKey=yes&keyContent=" + content;
		// ---修改href的分页路径
		$(".thenextpage").each(function() {
			url = $(this).attr("href");
			var urlcontent = url.replace("theKey=yes", thereplace);
		   $(this).attr("href", urlcontent);
		});
}

