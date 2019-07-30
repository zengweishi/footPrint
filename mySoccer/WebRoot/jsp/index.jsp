<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>首页</title>
<meta name="description" content="" />
<meta name="keywords" content="" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath }/js/jQuery1.8.2.js" type="text/javascript"></script>
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
    <h1> <a class="logo_img" href="${pageContext.request.contextPath }/index" title=""> <img src="${pageContext.request.contextPath}/images/mylogo.jpg" alt="" title="" /> </a> </h1>
    <nav>
      <ul>
        <li><a href="${pageContext.request.contextPath }/findClubForHomePage?currPage=1" title="青训机构">青训机构</a></li>
        <li><a href="${pageContext.request.contextPath }/findCNewsForHomePage?currPage=1" title="机构动态">机构动态</a></li>
        <li><a href="${pageContext.request.contextPath }/findANewsForHomePage?currPage=1" title="新闻中心">新闻中心</a></li>
        <li><a href="${pageContext.request.contextPath }/findDiaryForHomePage?currPage=1" title="球员日记">球员日记</a></li>
        <li><a href="${pageContext.request.contextPath }/findShareForHomePage?currPage=1" title="产品中心">教练宝典</a></li>
        <li><a href="${pageContext.request.contextPath }/findTNewsForHomePage?currPage=1" title="其他资讯">其他资讯</a></li>
        
        
         <c:if test="${ (empty existClub)&&(empty existCoach)&&(empty existPlayer)&&(empty existAdmin)&&(empty existTourist) }">
        	<li><a href="${pageContext.request.contextPath }/jsp/userLogin.jsp" title="用户中心">用户中心</a></li>
        </c:if>
        <c:if test="${ not empty existClub }">
        	<li><a href="${pageContext.request.contextPath }/jsp/clubHomePage.jsp" title="用户中心">用户中心</a></li>
        </c:if>
        <c:if test="${ not empty existCoach }">
        	<li><a href="${pageContext.request.contextPath }/jsp/coach/coachHomePage.jsp" title="用户中心">用户中心</a></li>
        </c:if>
        <c:if test="${ not empty existPlayer }">
        	<li><a href="${pageContext.request.contextPath }/jsp/player/playerHomePage.jsp" title="用户中心">用户中心</a></li>
        </c:if>
        <c:if test="${ not empty existAdmin }">
        	<li><a href="${pageContext.request.contextPath }/jsp/admin/adminHomePage.jsp" title="用户中心">用户中心</a></li>
        </c:if>
         <c:if test="${ not empty existTourist }">
        	<li><a href="${pageContext.request.contextPath }/jsp/tourist/touristHomePage.jsp" title="用户中心">用户中心</a></li>
        </c:if>
       
      </ul>
    </nav>
  </div>
</header>
<!--头部结束-->

<!--中间banner部分开始-->
<link media="screen" href="${pageContext.request.contextPath}/css/all.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/all.js"></script>
<div id="home_banner" class="mysection"> <a onClick="pgvSendClick({hottag:'HRTENCENT.HOME.BANNER.BANNER69'});" href="" id="big_a">
  
 	 <div id="big_img" style="background-image:url(${indexAdPhotoList[0].adPicture});height:890px;"></div>
 
  </a>
  <div class="relative">
    <div id="small_img">
      <div class="maxwidth">
        <div id="small_imgs"> 
        
        	<c:forEach var="p" items="${indexAdPhotoList }">
        		<a href="${p.adLink }" class="item">
		          <div title="" class="img" b="${p.adPicture }" l="" theid="69"> <img src="${p.adPicture }" alt=""/> </div>
		         </a> 
        	</c:forEach>
        	
	        
	          
           </div>
        <div class="clr"></div>
      </div>
    </div>
  </div>
</div>
<!--中间banner部分结束-->





   <br/>  
<div>                 
   <div  class="text" style="text-align:center;">
    	 <h1 style="color: #20B2AA"><strong>优秀俱乐部</strong></h1>
   </div>
   <hr />           

   <ul>
      <c:forEach var="p" items="${ mainPageClubList }">
	            <div class="col-sm-4">
	                <div class="contact-box" style="height: 180px">
	                    <a href="${pageContext.request.contextPath}/getClubInformation?pageFlag=1&cid=${p.cid}">
	                        <div class="col-sm-4">
	                            <div class="text-center">
	                                <img alt="image" style="width: 60px;height: 60px; " class="img-circle m-t-xs img-responsive" src="${p.clubPhoto}">
	                               <!--  <div class="m-t-xs font-bold" style="margin-left: -10px">俱乐部</div> -->
	                            </div>
	                        
	                        </div>
	                        <div class="col-sm-8">
	                            <h3><strong>${p.clubName }</strong></h3>
	                            <p><i class="fa fa-map-marker"> </i> ${p.clubCity }</p>
	                            <address>
	                            <strong>俱乐部简介:</strong><br>
	                           ${ fn:substring(p.clubIntroduction,0,30)}......<br>
	                          <!--   Weibo:<a href="">http://weibo.com/xxx</a><br> -->
	                            <abbr title="Phone"><strong>Tel:</strong></abbr> 
	                           		  ${p.clubPhone }                  	 
	                           	 
	                        </address>
	                        
	                        </div>
	                        <div class="clearfix"></div>
	                    </a>
	                </div>
	            </div>
			</c:forEach>
   
   </ul>
 
</div>



<div>               
   <div  class="text" style="text-align:center;">
    	 <h1 style="color: #20B2AA"><strong>教练榜样</strong></h1>
   </div>
   <hr />           

   <ul>
      <c:forEach var="p" items="${mainPageCoachList}">
	            <div class="col-sm-4">
            	<a href="${pageContext.request.contextPath}/getCoachInformation?pageFlag=1&coachId=${p.coachId}">    
	                <div class="contact-box" style="height: 180px">	                   
	                        <div class="col-sm-4">
	                            <div class="text-center">
	                                <img alt="image" style="width: 60px;height: 60px" class="img-circle m-t-xs img-responsive" src="${p.coachPhoto}">
	                                <div class="m-t-xs font-bold" style="margin-left: -12px"></div>
	                            </div>
	                           
	                        </div>
	                        <div class="col-sm-8"> 
	                         
	                            <h3><strong>${p.coachRealName }</strong></h3>
	                            <p><i class="fa fa-map-marker"></i> ${p.coachAddress }</p>
	                            <address>
	                            <strong>个人简介:</strong><br>
	                            ${ fn:substring(p.coachIntroduction,0,30)}......<br>
	                          
	                          <!--   Weibo:<a href="">http://weibo.com/xxx</a><br> -->
	                            <abbr title="Phone"><strong>Tel:</strong></abbr> 	                           	 
	                           		  ${p.coachTelPhone }                  	 	                           	
	                        </address>
	                        
	                        </div>
	                        <div class="clearfix"></div>
	                    </a>
	                </div>
	            </div>
			</c:forEach>
   
   </ul>
 
</div>

<div>
</div>

 <br/>  
<div>                 
   <div  class="text" style="text-align:center;">
    	 <h1 style="color: #20B2AA"><strong>球员标兵</strong></h1>
   </div>
   <hr />           

   <ul>
      <c:forEach var="p" items="${ mainPagePlayerList }">
	            <div class="col-sm-4">
	                <div class="contact-box" style="height: 180px">
	                    <a href="${pageContext.request.contextPath}/getplayerInformation?pageFlag=1&pid=${p.pid}">
	                        <div class="col-sm-4">
	                            <div class="text-center">
	                                <img alt="image" style="width: 60px;height: 60px; " class="img-circle m-t-xs img-responsive" src="${p.pPhoto}">
	                               <!--  <div class="m-t-xs font-bold" style="margin-left: -10px">俱乐部</div> -->
	                            </div>
	                        
	                        </div>
	                        <div class="col-sm-8">
	                            <h3><strong>${p.pRealName }</strong></h3>
	                            <p><i class="fa fa-map-marker"> </i> ${p.pAddress }</p>
	                            <address>
	                            <strong>个人简介:</strong><br>
	                           ${ fn:substring(p.pIntroduction,0,30)}......<br>
	                          <!--   Weibo:<a href="">http://weibo.com/xxx</a><br> -->
	                            <abbr title="Phone"><strong>Tel:</strong></abbr> 
	                           		  ${p.pTelePhone }                  	 
	                           	 
	                        </address>
	                        
	                        </div>
	                        <div class="clearfix"></div>
	                    </a>
	                </div>
	            </div>
			</c:forEach>
   
   </ul>
 
</div>
                      
                        





 <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>



    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>



    <script>
        $(document).ready(function () {
            $('.contact-box').each(function () {
                animationHover(this, 'pulse');
            });
        });
    </script>

</body>
</html>