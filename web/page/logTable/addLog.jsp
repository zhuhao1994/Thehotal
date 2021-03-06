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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
   <title>HTML5Sticky - Sticky Notes for the Web !</title>
   <!-- Meta tags -->
   <meta name="description" content="HTML5 sticky notes application. Create sticky notes for the web !" />
   <meta name="keywords" content="HTML5,sticky,notes,stickynote,note,stickies,css3,localstorage,offline,sticky-notes" />
   <meta name="viewport" content="width=device-width, initial-scale=1.0" />
   <link rel="shortcut icon" type="image/x-icon" href="favicon.ico?v=1">
   <!-- For non-Retina iPhone, iPod Touch, and Android 2.1+ devices: -->
   <link rel="apple-touch-icon-precomposed" href="apple-touch-icon-precomposed.png">
   <!-- For first-generation iPad: -->
   <link rel="apple-touch-icon-precomposed" sizes="72x72" href="apple-touch-icon-72x72-precomposed.png">
   <!-- For iPhone 4 with high-resolution Retina display: -->
   <link rel="apple-touch-icon-precomposed" sizes="114x114" href="apple-touch-icon-114x114-precomposed.png">
   <!-- CSS -->
   <link rel="stylesheet" href="assets/css/main.css?version=1" />
   <link rel="stylesheet" href="assets/css/html5sticky.css?version=1" />
   <script src="assets/js/WdatePicker.js"></script>
   <!-- JavaScript -->
   <!--[if IE]><![endif]-->
   <!--[if lt IE 9]>
   <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
   <![endif]-->
   <script src="http://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>
   
    <script src="assets/js/respond.min.js"></script>
    <script src="assets/js/modernizr.custom.23610.js"></script>
    <script src="assets/js/html5sticky.js"></script>
    <script src="assets/js/prettyDate.js"></script>
</head>

<body>
  <div id="main">
    <a href="#" id="addnote">
      <img src="assets/img/add.png" alt="添加新便签" title="添加新便签"></a>
    <a href="#" id="removenotes">
      <img src="assets/img/remove.png" alt="清除所有便签" title="清除所有便签"></a>
    <div class="clear">&nbsp;</div>
  </div>
  <div class="clear">&nbsp;</div>
  </body>
</html>