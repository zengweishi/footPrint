<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html>

<head>

    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title> - 文章列表</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">

<script type="text/javascript">
	var i = "0";
	function deleteTNewsBt(newsId) {
		i = newsId;
	}
	function deleteTNews() {
		//code=0代表在全部日记界面，删除后跳到全部日记
		location.href = "${pageContext.request.contextPath}/deleteTNews?newsId="+i;
	}
</script>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight blog" >
        <div class="row">
            <!-- <div class="col-lg-4"> -->
            <c:forEach var="p" items="${ cnewsList }">
                <div class="ibox">
                    <div class="ibox-content">
                        <a href="${pageContext.request.contextPath }/showCNewsArtical?newsId=${p.newsId}" class="btn-link">
                            <h2>${p.newsTitle }</h2> 
                        </a>
                        
                        <div class="small m-b-xs">
                          <span class="text-muted"> by </span> <strong>${ p.newsClub.clubName }</strong> &nbsp;&nbsp;&nbsp;&nbsp;<span class="text-muted"><i class="fa fa-clock-o"></i>${ fn:substring(p.newsDate,0,19) }</span>
                        </div>
                        <p>${ p.newsHeadContent }......</p>
                        <div class="row">
                             <div class="col-md-6">
                                <button class="btn btn-default btn-xs" type="button">俱乐部动态</button>
                                
                               
                             </div> 
                            <div class="col-md-6">
                                <div class="small text-right">
                                    <i class="fa fa-eye"> </i> ${p.viewNumber } 阅读
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
                
                
                 <c:forEach var="p" items="${ shareList }">
                <div class="ibox">
                    <div class="ibox-content">
                        <a href="${pageContext.request.contextPath }/showShareArtical?shareId=${p.shareId}" class="btn-link">
                            <h2>${p.shareTitle }</h2> 
                        </a>
                        <div class="small m-b-xs">
                          <span class="text-muted"> by </span> <strong>${ p.shareCoach.coachRealName }</strong> &nbsp;&nbsp;&nbsp;&nbsp;<span class="text-muted"><i class="fa fa-clock-o"></i>${ fn:substring(p.shareDate,0,19) }</span>
                        </div>
                        <p>${ p.shareHeadContent }......</p>
                        <div class="row">
                             <div class="col-md-6">
                                <button class="btn btn-info btn-xs" type="button">教练分享</button>
                             	<%-- <span class="text-muted"><i class="fa fa-clock-o"></i>${ fn:substring(p.newsDate,0,19) }</span> --%>
                               <!--  <h5>标签：</h5>-->
                               	<%-- <a href="${pageContext.c }">
                                	<button class="btn btn-danger btn-xs" type="button">删除</button> 
                                </a>
 --%>
 							
                             </div> 
                            <div class="col-md-6">
                                <div class="small text-right">
                                    <i class="fa fa-eye"> </i> ${p.viewNumber } 阅读
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
                
                
                <c:forEach var="p" items="${ diaryList }">
                <div class="ibox">
                    <div class="ibox-content">
                        <a href="${pageContext.request.contextPath }/findDiaryArtical?diaryId=${p.diaryId}" class="btn-link">
                            <h2>${p.diaryTitle }</h2> 
                        </a>
                        
                        <div class="small m-b-xs">
                          <span class="text-muted"> by </span> <strong>${ p.diaryPlayer.pRealName }</strong> &nbsp;&nbsp;&nbsp;&nbsp;<span class="text-muted"><i class="fa fa-clock-o"></i>${ fn:substring(p.diaryDate,0,19) }</span>
                        </div>
                        <p>${ p.diaryHeadContent }......</p>
                        <div class="row">
                             <div class="col-md-6">
                                <button class="btn btn-success btn-xs" type="button">球员日记</button>

                             </div> 
                            <div class="col-md-6">
                                <div class="small text-right">
                                     <i class="fa fa-eye"> </i> ${p.viewNumber } 阅读
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
                
                
                <c:forEach var="p" items="${ tnewsList }">
                <div class="ibox">
                    <div class="ibox-content">
                        <a href="${pageContext.request.contextPath }/findTNewsArticle?newsId=${p.newsId}" class="btn-link">
                            <h2>${p.newsTitle }</h2> 
                        </a>
                        
                        <div class="small m-b-xs">
                          <span class="text-muted"> by </span> <strong>${ p.newsTourist.tName }</strong> &nbsp;&nbsp;&nbsp;&nbsp;<span class="text-muted"><i class="fa fa-clock-o"></i>${ fn:substring(p.newsDate,0,19) }</span>
                        </div>
                        <p>${ p.newsHeadContent }......</p>
                        <div class="row">
                             <div class="col-md-6">
                                <button class="btn btn-default btn-xs" type="button">游客资讯</button>
                               
                             </div> 
                            <div class="col-md-6">
                                <div class="small text-right">
                                    <i class="fa fa-eye"> </i> ${p.viewNumber } 阅读
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
                
                
                <c:forEach var="p" items="${ anewsList }">
                <div class="ibox">
                    <div class="ibox-content">
                        <a href="${pageContext.request.contextPath }/findANewsArticle?anewsId=${p.anewsId}" class="btn-link">
                            <h2>${p.anewsTitle }</h2> 
                        </a>
                        
                        <div class="small m-b-xs">
                          <span class="text-muted"> by </span> <strong>${ p.anewsAdmin.adminName }</strong> &nbsp;&nbsp;&nbsp;&nbsp;<span class="text-muted"><i class="fa fa-clock-o"></i>${ fn:substring(p.anewsDate,0,19) }</span>
                        </div>
                        <p>${ p.anewsHeadContent }......</p>
                        <div class="row">
                             <div class="col-md-6">
                                <button class="btn btn-info btn-xs" type="button">官方新闻</button>
                               
                             </div> 
                            <div class="col-md-6">
                                <div class="small text-right">
                                    <i class="fa fa-eye"> </i> ${p.viewNumber } 阅读
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
                
               
                
               
        </div>
    </div>
    
    
    
   

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>


    
    

</body>

</html>
