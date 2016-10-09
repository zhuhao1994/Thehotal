<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
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
  
</head>
<body>
<%-- <s:debug></s:debug> --%>
<form class="form-inline definewidth m20" action="UserAction!findTheUserById" method="post">
    <select id="userta" name="userta">
    <option>查询类型</option>
    <option id="userid" value="userid">员工ID</option>
    <option id="username" value="username">用户名</option>
    <option id="position" value="position">职务</option>
    </select>
    <input  type="text" name="nowpage" value="1" style="display: none"/>
    <input type="text" name="usert" id="usert" class="abc input-default" value="${requestScope.type}">&nbsp;&nbsp;
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; 
    <button type="button" class="btn btn-success" id="addnew">添加</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>员工ID</th>
        <th>用户名</th>
        <!-- <th>密码</th> -->
        <th>真实姓名</th>
        <th>生日</th>
        <th>性别</th>
        <th>职务</th>
        <th>工资</th>
        <th colspan="2" align="center">管理</th>
    </tr>
    </thead>
    <tbody id="tb">
    <c:forEach items="${requestScope.showUsers}" var="u" varStatus="s" >
										<tr>
											<td>${u.userid}</td>
											<td>${u.username}</td>
											<%-- <td>${u.password}</td> --%>
											<td>${u.realname}</td>
											<td>${u.userbirth}</td>
											<td>${u.usersex}</td>
											<td>${u.position}</td>
											<td>${u.salary}</td>
											<td><a href="UserAction!findUserById.action?userid=${u.userid}">编辑</a></td>
										</tr>
					</c:forEach>
		</table>
	    <div class="inline pull-right page" >
		${requestScope.haPage.rowsTotal}条记录 ${requestScope.haPage.page}/${requestScope.haPage.pageTotal}页
		<a class="thenextpage" href='UserAction!showPage?nowpage=1'>首页</a>
		 <c:if test="${!(requestScope.haPage.page==1)}">
             <a class="thenextpage" href='UserAction!showPage?nowpage=${requestScope.haPage.page-1}'>上一页</a>
         </c:if>
 		
         <c:if test="${!(requestScope.haPage.page==requestScope.haPage.pageTotal)}">
             <a class="thenextpage" href='UserAction!showPage?nowpage=${requestScope.haPage.page+1}'>下一页</a>
        </c:if>
         &nbsp;&nbsp;&nbsp;
         <input class="input-default" type="text" id="jumpTo" style="width:30px" />页
          <input class="btn btn-primary" type="button" value="GO" onclick="jumpTo(${requestScope.haPage.pageTotal})" />
		 <a class="thenextpage" href='UserAction!showPage?nowpage=${requestScope.haPage.pageTotal}'>最后一页</a>
	</div>
</body>
</html>
  <script>
    $(function () {
		$('#addnew').click(function(){
				window.location.href="page/checkInRoom/register.jsp";
		 });
		if('${requestScope.autoSearch}' =="autoSearch")
	    {
			var autocontent = $("#usert").val();
		    autocontent = encodeURI(encodeURI(autocontent));
			var autourl  = "UserAction!findTheUserById?userta=username&usert=" + autocontent+"&nowpage=1";
			window.location.href = autourl;
		}

    });
    //方法选中
    if('${sessionScope.id}' =='userid'){
    	console.log('userid');
    	$("#userta").val('userid')
    }else if('${sessionScope.id}' =='username'){
    	console.log('userid');
    	$("#userta").val('username')
    }else if('${sessionScope.id}' =='position'){
    	$("#userta").val('position')
    }
    //分页处理
       //跳转分页
    function jumpTo(maxPage){
        var page = $("#jumpTo").val();
        var content=$("#usert").val();
        content=encodeURI(encodeURI(content));//转换编码
        if('${sessionScope.id}'=='userid'){
        	goUrl="UserAction!findTheUserById?userta=userid&usert="+content+"&nowpage="+page;
        	
        }else if('${sessionScope.id}'=='username'){
        	goUrl="UserAction!findTheUserById?userta=username&usert="+content+"&nowpage="+page;
        
        }else if('${sessionScope.id}'=='position'){
        	goUrl="UserAction!findTheUserById?userta=position&usert="+content+"&nowpage="+page;
        }else
        	goUrl="UserAction!findTheUserById?userta=userid&usert="+content+"&nowpage="+page;
        if(page > maxPage || page < 1){
            alert("对不起，无法到达该页")
        }else{
        	window.location.href = goUrl;
        }
    }
  //前后分页
   if('${sessionScope.id}' == 'position')
	 	{
	 		 //更改href的值
	 		 var content = $("#usert").val();
	 		  content = encodeURI(encodeURI(content));//转换编码
	 		  var thereplace = "findTheUserById?userta=position&usert=" + content+"&";
	 			// ---修改href的分页路径
	 			$(".thenextpage").each(function() {
	 				url = $(this).attr("href");
	 				var urlcontent = url.replace("showPage?", thereplace);
	 			   $(this).attr("href", urlcontent);
	 	
	 			});
	 	}
    function findById() {
		var userid = $("#userid").val();
		window.location.href='UserAction!showUser';
	}
    function deleteById(id){
    	var result=window.confirm("确定要删除员工ID为:"+id+"的用户吗？");
    	if(result){
    		window.location.href="UserAction!deleteUser?id="+id;
    	}
    }

</script>