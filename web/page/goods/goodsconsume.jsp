<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <head>
<base href="<%=basePath%>">
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="Css/style.css" />
    <script type="text/javascript" src="Js/jquery.js"></script>
    <script type="text/javascript" src="Js/jquery.sorted.js"></script>
    <script type="text/javascript" src="Js/bootstrap.js"></script>
    <script type="text/javascript" src="Js/ckform.js"></script>
    <script type="text/javascript" src="Js/common.js"></script>

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
   <script>
function jumpTo(maxPage){
    var page = $("#jumpTo").val();
    if(page > maxPage || page < 1){
        alert("对不起，无法到达该页")
    }else{
        $('body').load('GoodsConsumeAction!showpageGoodsConsume?nowpage='+page);
    }
}
function findBycusName() {
	var content = $("#menuname").val();
	window.location.href='GoodsConsumeAction!showpageGoodsConsume?nowpage=1&theKey=cusName&keyContent='+content;
}
function findByuserName() {
	
}
function findByroomName() {
	
}
</script>
    
</head>
<body>
<form class="form-inline definewidth m20" action="GoodsConsumeAction!findbygoodsconsume" method="post">
    <tr>
        <td>
            <select id="ggd" name="ggd">
            <option value="goodsname">按商品名称</option>
            <option value='roomno'/>&nbsp;按房间号</option>
            </select>
        </td>
    </tr>
    <input  type="text" name="nowpage" value="1" style="display: none"/>
    <input type="text" name="keygoodsc" id="keygoodsc" class="abc input-default" value="${requestScope.keygoodsc}">&nbsp;&nbsp; 
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; 
    <button type="button" class="btn btn-success" id="addnew">新增消费记录</button>
</form>
<table class="table table-bordered table-hover definewidth m10" onselectstart="return false">
    <thead>
    <tr>
        <th>消费记录编号</th>
        <th>房间号</th>
        <th>商品名称</th>
        <th>商品消费数量</th>
        <th>商品价格(单价)</th>
        <th>总价格</th>
        <th>消费时间</th>
    </tr>
    </thead>
       <c:forEach items="${requestScope.allGoodsconsume}" var="goodsconxume" >
    	<tr>
    		<td>${goodsconxume.goodsconsumeid}</td>
    		<td>${goodsconxume.guestroom.roomno}</td>
    		<td>${goodsconxume.goods.goodsname}</td>
    		<td>${goodsconxume.goodsnum}</td>
    		<td>${goodsconxume.goods.goodsprice}</td>
    		<td>${goodsconxume.goods.goodsprice*goodsconxume.goodsnum}</td>
    		<td>${goodsconxume.consumetime}</td>
    		
    	</tr>
    </c:forEach>
            </table>
<div class="inline pull-right page" >
		${requestScope.haPage.rowsTotal}条记录 ${requestScope.haPage.page}/${requestScope.haPage.pageTotal}页
		<a class="thenextpage" href='GoodsConsumeAction!showpageGoodsConsume?nowpage=1'>首页</a>
		 <c:if  test="${!(requestScope.haPage.page==1)}">
             <a class="thenextpage" href='GoodsConsumeAction!showpageGoodsConsume?nowpage=${requestScope.haPage.page-1}'>上一页</a>
         </c:if>
<!-- 
         <c:forEach var="p" begin="1" end="${requestScope.haPage.pageTotal}">
             <a href="ps?page=${p}">第${p}页</a>
         </c:forEach>
 -->
 		
         <c:if  test="${!(requestScope.haPage.page==requestScope.haPage.pageTotal)}">
             <a class="thenextpage" href='GoodsConsumeAction!showpageGoodsConsume?nowpage=${requestScope.haPage.page+1}'>下一页</a>
        </c:if>
         &nbsp;&nbsp;&nbsp;<input class="input-default" type="text" id="jumpTo" style="width:30px" />页 
         <input class="btn btn-primary" type="button" value="GO" onclick="jumpTo(${requestScope.haPage.pageTotal})" />
		 <a class="thenextpage" href='GoodsConsumeAction!showpageGoodsConsume?nowpage=${requestScope.haPage.pageTotal}'>最后一页</a>
	</div>
<script>
    $(function () {
		$('#addnew').click(function(){
				window.location.href="page/goods/addgoodsconsume.jsp";
		 });

    });
    if('${sessionScope.term}' == 'goodsname')
	{
		$("#ggd").val('goodsname')
	}else if('${sessionScope.term}' == 'roomno')
	{
		console.log('goodsname');
		$("#ggd").val('roomno')
	}
	/* else if('${sessionScope.term}' == 'goodsprice')
	{
		$("#ggd").val('goodsprice')
	} */
	
  //==跳转分页
	function jumpTo(maxPage) {
		var page = $("#jumpTo").val();
		var content = $("#keygoodsc").val();
		content = encodeURI(encodeURI(content));
		 if('${sessionScope.term}' == 'goodsname')
			 goUrl =  "GoodsConsumeAction!findbygoodsconsume?ggd=goodsname&keygoodsc=" + content+"&nowpage="+page;
		 else if('${sessionScope.term}' == 'roomno')
			 goUrl =  "GoodsConsumeAction!findbygoodsconsume?ggd=roomno&keygoodsc=" + content+"&nowpage="+page;
		 /* else
			 goUrl =  "GoodsConsumeAction!findbygoodsconsume?ggd=roomno&keygoodsc=" + content+"&nowpage="+page;
			 */ 
		 if (page > maxPage || page < 1) {
			alert("对不起，无法到达该页")
		} else {
			window.location.href = goUrl;
		}
	}
  
	//==前后分页
 	  if('${sessionScope.term}' == 'goodsname')
 	{
 		 //更改href的值
 		 var content = $("#keygoodsc").val();
 		
 		  content = encodeURI(encodeURI(content));//转换编码
 		  var thereplace = "findbygoodsconsume?ggd=goodsname&keygoodsc=" + content+"&";
 			// ---修改href的分页路径
 			
 			$(".thenextpage").each(function() {
 				url = $(this).attr("href");
 				var urlcontent = url.replace("showpageGoodsConsume?", thereplace);
 			   $(this).attr("href", urlcontent);
 				 console.log(urlcontent)
 	
 			});
 	} else if('${sessionScope.term}' == 'roomno')
 	{
		 //更改href的值
		 var content = $("#keygoodsc").val();
		  content = encodeURI(encodeURI(content));//转换编码
		  var thereplace = "findbygoodsconsume?ggd=roomno&keygoodsc=" + content+"&";
			// ---修改href的分页路径
			$(".thenextpage").each(function() {
				url = $(this).attr("href");
				var urlcontent = url.replace("showPageGoodsConsume?", thereplace);
			   $(this).attr("href", urlcontent);
	
			});
	} 
</script>

</body>
</html>

