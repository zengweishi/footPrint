<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'announcement.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
	<!-- 引入注释的这些模态框会出问题无法弹出 -->
	<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath }/js/jQuery1.8.2.js" type="text/javascript"></script>
<!-- 		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
 -->		
 <script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
   --%>
  
		<!-- 引入自定义css文件 style.css ，不放在第一个引进的话部分样式会出问题-->
	<link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">
	

	
	
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body class="gray-bg">
  	
<%-- 		<div class="col-md-12">
		  <c:forEach var="p" items="${ pageBean.list }">
<!-- 			    <div class="row" col-md-6>-->
			    <div class="col-md-6" style="margin-top: 20px;width: 550px">
 				  	<div class="col-sm-6 col-md-4" > 
					    <div class="thumbnail" style="width: 500px">
					      <div class="caption" style="width: 500px" >
					      <h3>${ fn:substring(p.coachRealName,0,16) }</h3>
					        <h4>${p.aTime}</h4>
					        <p>${ fn:substring(p.coachIntroduction,0,10) }......</p>
					        <p>
					       <a href="javascript:void(0)"  onclick="findContent('${p.aId}')" class="btn btn-primary" role="button" class="btn btn-primary btn-sm" data-toggle="modal"
					data-target="#myModal">教练详情</a>  
					 <p><a href="#" class="btn btn-primary" role="button">教练详情</a> </p>
				
					</p>
					      </div>
					    </div>
				  </div>
			</div>
		</c:forEach>
	</div>
	 --%>
	
	
	
	
	
	    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
		
            <c:forEach var="p" items="${ pageBean.list }">
	            <div class="col-sm-4">
            	<a href="${pageContext.request.contextPath}/getCoachInformation?coachId=${p.coachId}">    
	                <div class="contact-box" style="height: 180px">	                   
	                        <div class="col-sm-4">
	                            <div class="text-center">
	                                <img alt="image" style="width: 60px;height: 60px" class="img-circle m-t-xs img-responsive" src="${p.coachPhoto}">
	                                <div class="m-t-xs font-bold" style="margin-left: -12px">教练</div>
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
			
		
							
					
         
        </div>
    </div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		<!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:10px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<!-- 页数为第一页时，上一页按钮失效 -->
				<c:if test="${ pageBean.currPage == 1 }">
					<li class='disabled'>
						<a href="#">&laquo;</span></a>
					</li>
				</c:if>
				<c:if test="${ pageBean.currPage != 1 }">
					<li>
						<a href="${pageContext.request.contextPath }/findAllAnnouncementByClub?code=0&currPage=${ pageBean.currPage-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
				
				<!-- 当点击的页数是当前页时无效,不是当前页则可以跳转 -->
				<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
					<c:if test="${ pageBean.currPage == i }">
						<li class="active"><a href="#">${ i }</a></li>
					</c:if>
					
					<c:if test="${ pageBean.currPage != i }">
						<li><a href="${pageContext.request.contextPath }/findAllAnnouncementByClub?code=0&currPage=${ i }">${ i }</a></li>
					</c:if>	
				</c:forEach>
				
				<!-- 当页数为最后一页时，下一页按钮失效 -->
				<c:if test="${ pageBean.currPage == pageBean.totalPage }">
					<li class='disabled'>
						<a href="#" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
				<c:if test="${ pageBean.currPage != pageBean.totalPage }">
					<li>
						<a href="${pageContext.request.contextPath }/findAllAnnouncementByClub?code=0&currPage=${ pageBean.currPage + 1 }" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
			</ul>
		</div>  
	
		<!-- 分页结束=======================        -->
	
	
	
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
