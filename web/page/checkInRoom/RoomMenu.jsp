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
<title>Insert title here</title>
 <style type="text/css">
        body { font-family:Lucida Sans, Lucida Sans Unicode, Arial, Sans-Serif; font-size:20px; color:#222; margin:0;}
        p { margin:20px; padding:0;}
        
        .demoheader { background-color:#000; color:#fff; padding:10px; margin:0 0 20px; overflow:hidden;}
        .demoheader img, .demoheader div { float:left;}
        .demoheader div { margin:10px 0 0 20px;}
        .demoheader a { color:#78c0ff;}
              #container { width:960px; margin:0px auto; position:relative; list-style:none;}
        #container li { width:280px; height:180px; position:absolute; top:0; left:0; 
               text-align:center; cursor:pointer;
               -moz-border-radius:15px; -webkit-border-radius:15px; border-radius:15px;
               background:#e0e0e0 url(bkg.png) repeat-x scroll left top;}
        #container li a { color:#222; text-decoration:none;}              
        #container li.current { width:300px; height:200px;top:-10px;box-shadow: 0 0 10px #607d8b; background:#f0f0f0 url(bkg-current.png) repeat-x scroll left top; cursor:default;
        }
        #container li.current a { color:#0010a9; text-decoration:none;}
    </style>
<script src='../../Js/jquery-1.7.2.js'></script>
    <script type="text/javascript">
        $(document).ready(function() {
            
            var itemWidth = $("#container li").width();
            // hide 50% of each window
            var itemPosition = itemWidth * 50 / 100;
            // slide each window 60% if its width    
            var itemMove = itemWidth * 60 / 100;        

            // move windows below eachother
            $("#container li").each(function(i) {
                $(this).attr("id", i).css("z-index", 100 - i).css("left", itemPosition * i);
            });

            $("#container li").click(function(e) {
                var currentID = parseInt($(".current").attr("id"));
                var clickedID = parseInt($(this).attr("id"));

            if (currentID != clickedID) {
                e.preventDefault();
                    var currentZ = 99;
                    var current = $(this);
                    setTimeout(function() { $(".current").removeClass("current"); current.css("z-index", currentZ).addClass("current"); }, 500);

                    if (clickedID > currentID) {
                    var i = 1;
                    var total = clickedID - currentID + 1;
                    for (j = clickedID - 1; j >= 0; j = j - 1) {
                        $("#" + j).animate({ "left": "-=" + itemMove * (i) + "px" }, 500);
                        $("#" + j).animate({ "left": "+=" + itemMove * (i) + "px" }, 300);
                        i = i + 1;
                    }
                    var i = 1;
                    setTimeout(function() {
                        for (j = clickedID - 1; j >= 0; j = j - 1) {
                            $("#" + j).css("z-index", total - i);
                            i = i + 1;
                        }
                    }, 500);
                    }
                    else {
                        var i = 1;
                        var total = $("#container li").length;
                        for (j = clickedID + 1; j <= total; j = j + 1) {
                            $("#" + j).animate({ "left": "+=" + itemMove * i + "px" }, 500);
                            $("#" + j).animate({ "left": "-=" + itemMove * i + "px" }, 300);
                            $("#" + j).css("z-index", currentZ - i);
                            i = i + 1;
                        }
                    }
                }
            });
            /*亮改   2016.09.16*/	
        	var firstIndex = 0;
        	var lis = $("#container li");
        //	alert(lis[firstIndex].innerText);
        	lis[firstIndex].click();
        });
    </script>
</head>
<base href="<%=basePath%>">
<body onselectstart="return false;">
   <p style="padding-top:100px">&nbsp;</p>
    <ul id="container" >
        <li class="current"   style="background-color: #eceff1">
        	<p>♚入住<br/><br/>
        		<a href="roomStatusAction!directorAddCs?thePath=roomMenu">☝ 客户录入</a><br>
	        	<a href="CustomerAction!shoaPageHa?nowpage=1&thePath=roomMenu">☝ 客户查询</a><br>
	        	<a href="roomStatusAction!roomStatus?thePath=roomMenu">☝ 客房选择</a>
        	</p>
        </li>
        <li  style="background-color: #cfd8dc;">
        	<p>♔退房<br /><br />
        		<a href="HotelAction2!checkOutShowHa?nowpage=1&searchKey=noCheckOut&roomNo=">☝ 查询账单</a><!-- 编辑账单 -->
        	</p>
        </li>
        <li  style="background-color: #b0bec5;">
       		 <p>
       			 ˇ０ˇ娱乐休息<br /><br />
       			 <a href="http://www.baidu.com">☞百度一下☜</a>
       		 </p>
        </li>
      <li  style="background-color: #90a4ae;">
       		 <p>(⊙_⊙)我的账户<br /><br />
       		 	<a href="page/accountFind/employeePerfomance.jsp">☝ 我的业绩</a><br />
       		 	<a href="page/user/listuser.jsp">☝ 个人信息</a><br />
       		 	<a href="LogAction!myLog">☝ 我的日志</a><br />
       		 </p>
        </li>

    </ul>
</body>
</html>
