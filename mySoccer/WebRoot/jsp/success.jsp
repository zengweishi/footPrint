<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath }/js/jQuery1.8.2.js" type="text/javascript"></script>
<!-- 		<script src="..js/jquery-1.11.3.min.js" type="text/javascript"></script>-->		
 <script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <%-- <!--头部开始-->
<header data-waypointsok="" class="header mysection">
  <div class="tem_inner tem_head mynav mysection_s">
    <h1> <a class="logo_img" href="" title=""> <img src="${pageContext.request.contextPath }/images/logo1.png" alt="" title="" /> </a> </h1>
    <nav>
      <ul>
        <li><a href="index.html" title="网站首页">网站首页</a></li>
        <li><a href="about.html" title="关于我们">关于我们</a></li>
        <li><a href="wenhua.html" title="企业文化">企业文化</a></li>
        <li><a href="newslist.html" title="新闻中心">新闻中心</a></li>
        <li><a href="productlist.html" title="产品中心">产品中心</a></li>
        <li><a href="gbook.html" title="在线留言">在线留言</a></li>
        <li><a href="contact.html" title="联系我们">用户中心</a></li>
      </ul>
    </nav>
  </div>
</header>
<!--头部结束--> --%>
	
	<div class="container">
	<div style=" text-align: center;top: 0;left: 0;right: 0;bottom: 0;">
		<h1 style="color:red"></h1>
		<h1>${ msg }</h1>
		<img src="${ pageContext.request.contextPath }/imgs/successPhoto3.gif" height="300px" width="300px">
				
		<
	</div>
	</div>
  </body>
</html>
