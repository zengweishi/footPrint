<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'clubHomePage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styleindex.css" />
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
 --%><script src="${pageContext.request.contextPath }/js/jQuery1.8.2.js" type="text/javascript"></script>
 <!-- 		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
 -->		
 <script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet"> 
 

  </head>
  
 <body>
<!--头部开始-->
<header data-waypointsok="" class="header mysection">
  <div class="tem_inner tem_head mynav mysection_s">
    <h1> <a class="logo_img" href="" title=""> <img src="../images/logo1.png" alt="" title="" /> </a> </h1>
    <nav>
      <ul>
        <li><a href="index.html" title="网站首页">网站首页</a></li>
        <li><a href="about.html" title="关于我们">关于我们</a></li>
        <li><a href="wenhua.html" title="企业文化">企业文化</a></li>
        <li><a href="newslist.html" title="新闻中心">新闻中心</a></li>
        <li><a href="productlist.html" title="产品中心">产品中心</a></li>
        <li><a href="gbook.html" title="在线留言">在线留言</a></li>
        <li><a href="contact.html" title="联系我们">联系我们</a></li>
      </ul>
    </nav>
  </div>
</header>
<!--头部结束-->

<!--banner开始-->

<div class="tem_banner">
  <ul class="slides" style="height:380px;">
    <li style="background:url(../images/news.jpg) no-repeat center center; height:380px;"></li>
  </ul>
</div>
<section class="met_section mynews_aside  ">
 
  <article>
    <div class="met_article">
      <section class="met_article_head">
        <h1>新闻中心</h1>
        <div class="met_position"> <a href="index.html" title="首页">首页</a> &gt; <a href="newslist.html">企业新闻</a> </div>
      </section>
      <div class="met_clear"></div>
      <div class="met_module2_list ">
        <div class="">
          <div class="my_news" >
            <ul>
             
              
            
             
              <li>
                <h2> <a href="news.html" title="挑战自我极限 强化团队协作" target="_self">挑战自我极限 强化团队协作</a> </h2>
                <div class="article_info cl"> 2015年12月8日
                  <p> <a class="reader" href="news.html" target="_self"> <b>看</b> 19 </a> </p>
                </div>
                <div class="article">
                  <div class="article_img"> <a href="news.html" title="挑战自我极限 强化团队协作" target="_self"><img src="../images/123456.jpg" alt="我司移动项目顺利通过验收"></a> </div>
                  <P>2013年10月6日，经过党支部半个多月以来的策划和组织，公司27名员工一早就前往风景秀美的重渡沟，进行为期两天的拓展训练。此次活动全程得到了公司领导的重视和支持。</p>
                </div>
                <div class="readmore"><a href="news.html" target="_self">阅读全文<b></b></a></div>
              </li>
			  
			  
            </ul>
            <div class="met_pager"> <span class="PreSpan">上一页</span> <a href="newslist.html" class="Ahover">1</a> <a href="newslist.html">2</a> <a href="newslist.html" class="NextA">下一页</a> </div>
          </div>
        </div>
      </div>
    </div>
  </article>
  <div class="met_clear"></div>
</section>
<div class="met_clear"></div>
<!--新闻列表结束-->

<!--底部开始-->

<!--底部结束-->
</body>
</html>
