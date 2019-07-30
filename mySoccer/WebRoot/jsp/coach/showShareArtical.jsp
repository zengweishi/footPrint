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


    <title>文章内容</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styleindex.css" />
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">

<script type="text/javascript">
	var existClub = "<%=session.getAttribute("existClub")%>";
	var clubId = "${existClub.cid}";
	var existCoach = "<%=session.getAttribute("existCoach")%>";
	var coachId = "${existCoach.coachId}";
	var existPlayer = "<%=session.getAttribute("existPlayer")%>";
	var playerId = "${existPlayer.pid}";
	var existTourist = "<%=session.getAttribute("existTourist")%>";
	var touristId = "${existTourist.tId}";
	window.onload = function() {
		var existTourist = "<%=session.getAttribute("existTourist")%>";
		var id = "${existTourist.tId}";
		if(!(id.length===0)) {
			var share="<%=request.getAttribute("share")%>"; 
			var shareId = "${share.shareId}";
			$.post("${pageContext.request.contextPath}/findCollection",{'shareId':shareId},function(data) {
				if(data == "1" ) {
					document.getElementById('collection').innerText = "取消收藏";
				}
			});
		}
		
		
		//获取评论
		var share="<%=request.getAttribute("share")%>"; 
		var shareId = "${share.shareId}";
		$.post("${pageContext.request.contextPath}/findCommentsByArticleId",{'shareId':shareId},function(data) {
			
			var jsonObj = eval("("+data+")");
			
			var html = "";
			for(var i = 0;i < jsonObj.length;i++) {
				html+='<div class="social-feed-box">'
					+'<div class="social-avatar">'
					+'<a href="javascript:void(0)" class="pull-left">'
					+'<img alt="image"  src="'
					+jsonObj[i].userPhoto
					+'">'
					+'</a>'
					+'<div class="media-body">'
					if((jsonObj[i].userId == clubId)||(jsonObj[i].userId == coachId)||(jsonObj[i].userId == playerId)||(jsonObj[i].userId == touristId)) {
						html+=' <div class="ibox-tools">'
							+'<a href="javascript:void(0)" onclick="deleteComments(\''+jsonObj[i].commentsId+'\',\''+shareId+'\')" >删除</a>'
							+'</div>'
					}  
					
				html+='<a href="javascript:void(0)">'
					+jsonObj[i].userName
					+'</a>'
					+'<small class="text-muted">'
					+jsonObj[i].commentsTime
					+'</small>'
					+'</div>'
					+'</div>'
					+'<div class="social-body">'
					+'<p style="margin-left: 50px">'
					+jsonObj[i].contents
					+'</p>'
					+'</div>'
					+'</div>'
			}
			$("#msg").html(html);
		});
	}; 
	function change(shareId) {
		
		var type = document.getElementById('collection').innerText;
		
		if(type == "收藏") {
			$.post("${pageContext.request.contextPath}/addCollection",{'shareId':shareId},function(data) {
				document.getElementById('collection').innerText = "取消收藏";
			});
		} else {
			$.post("${pageContext.request.contextPath}/deleteCollection",{'shareId':shareId},function(data) {
				document.getElementById('collection').innerText = "收藏";
			});
		}
	}
	
	
	
	
	function addComments(shareId) {
		var contents = document.getElementById('contents').value;
		if(contents == "") {
			alert("您的评论内容为空")
		} else if((clubId.length == 0)&&(coachId.length == 0)&&(playerId.length == 0)&&(touristId.length == 0)) {
			alert("您还未登录，请先登录")
		} else {
		$.post("${pageContext.request.contextPath}/addComments",{'contents':contents,'shareId':shareId},function(data) {
			if(data == "1") {
				alert("不好意思，您的评论中含敏感词汇")
			}
			$.post("${pageContext.request.contextPath}/findCommentsByArticleId",{'shareId':shareId},function(data) {
			document.getElementById('contents').value = "";
				var jsonObj = eval("("+data+")");
				var html = "";
				for(var i = 0;i < jsonObj.length;i++) {
					html+='<div class="social-feed-box">'
						+'<div class="social-avatar">'
						+'<a href="javascript:void(0)" class="pull-left">'
						+'<img alt="image"  src="'
						+jsonObj[i].userPhoto
						+'">'
						+'</a>'
						+'<div class="media-body">'
						if((jsonObj[i].userId == clubId)||(jsonObj[i].userId == coachId)||(jsonObj[i].userId == playerId)||(jsonObj[i].userId == touristId)) {
							html+=' <div class="ibox-tools">'
								+'<a href="javascript:void(0)" onclick="deleteComments(\''+jsonObj[i].commentsId+'\',\''+shareId+'\')" >删除</a>'
								+'</div>'
						}  
						
					html+='<a href="javascript:void(0)">'
						+jsonObj[i].userName
						+'</a>'
						+'<small class="text-muted">'
						+jsonObj[i].commentsTime
						+'</small>'
						+'</div>'
						+'</div>'
						+'<div class="social-body">'
						+'<p style="margin-left: 50px">'
						+jsonObj[i].contents
						+'</p>'
						+'</div>'
						+'</div>'
				}
				$("#msg").html(html);
			})
		});
			
		}
	}
	
	
	function deleteComments(commentsId,shareId) {
		$.post("${pageContext.request.contextPath}/deleteCommentsById",{'commentsId':commentsId},function(data){
			$.post("${pageContext.request.contextPath}/findCommentsByArticleId",{'shareId':shareId},function(data) {
				
					var jsonObj = eval("("+data+")");
					var html = "";
					for(var i = 0;i < jsonObj.length;i++) {
						//alert(jsonObj[i].contents)
						html+='<div class="social-feed-box">'
							+'<div class="social-avatar">'
							+'<a href="javascript:void(0)" class="pull-left">'
							+'<img alt="image"  src="'
							+jsonObj[i].userPhoto
							+'">'
							+'</a>'
							+'<div class="media-body">'
							
							  if((jsonObj[i].userId == clubId)||(jsonObj[i].userId == coachId)||(jsonObj[i].userId == playerId)||(jsonObj[i].userId == touristId)) {
								html+=' <div class="ibox-tools">'
									+'<a href="javascript:void(0)" onclick="deleteComments(\''+jsonObj[i].commentsId+'\',\''+shareId+'\')" >删除</a>'
									+'</div>'
							}  
							
						html+='<a href="javascript:void(0)">'
							+jsonObj[i].userName
							+'</a>'
							+'<small class="text-muted">'
							+jsonObj[i].commentsTime
							+'</small>'
							+'</div>'
							+'</div>'
							+'<div class="social-body">'
							+'<p style="margin-left: 50px">'
							+jsonObj[i].contents
							+'</p>'
							+'</div>'
							+'</div>'
					}
					$("#msg").html(html);
				});
		});
		
	}

	
</script>

</head>

<body>

	<c:if test="${not empty pageFlag }">
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
		
	</c:if>


    <div class="wrapper wrapper-content  animated fadeInRight article">
        <div class="row">
            <div class="col-lg-10 col-lg-offset-1">
                <div class="ibox">
                    <div class="ibox-content"> 
                     <div class="pull-right">
                     		<c:if test="${not empty existTourist }">
                     			<a href="javascript:void(0)" onclick="change('${share.shareId}')" id="collection"  class="btn btn-default btn-xs" role="button" >收藏</a> 
                     		</c:if>
                     
                     		<c:if test="${ (not empty existCoach)&&(share.shareCoach.coachId == existCoach.coachId) }">
	                     		<c:if test="${empty pageFlag }">
	                     		<a href="${pageContext.request.contextPath }/updateJump?shareId=${share.shareId}">
	                            	<button class="btn btn-info btn-xs" type="button">编辑</button>  
	                            </a>
	                            </c:if>
                            </c:if>
                        </div>  
                        <div class="text-center article-title">
                            <h2>
                                 <strong>   ${share.shareTitle }</strong>
                                </h2>
                        </div>
						<div class="small m-b-xs">
							<c:if test="${not empty pageFlag }">
	                          <span > by </span> <strong><a href="${pageContext.request.contextPath }/getCoachInformation?pageFlag=1&coachId=${share.shareCoach.coachId}">${ share.shareCoach.coachRealName }</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong> <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;${ fn:substring(share.shareDate,0,19) }</span>
							</c:if>
							<c:if test="${empty pageFlag }">
	                          <span > by </span> <strong><a href="${pageContext.request.contextPath }/getCoachInformation?coachId=${share.shareCoach.coachId}">${ share.shareCoach.coachRealName }</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong> <span><i class="fa fa-clock-o"></i>&nbsp;&nbsp;${ fn:substring(share.shareDate,0,19) }</span>
							</c:if>
                        </div>
						 <hr>
							${share.shareContents }
                        <hr>
                        
                         <c:if test="${empty existAdmin }">
	                         <div class="input-group">
	                            <input type="text" id="contents" name="contents" placeholder="请在此发表您的评论..." class="input form-control">
	                            	<span class="input-group-btn">
	                                        <button type="button" onclick="addComments('${share.shareId}')" class="btn btn btn-info btn-sm">发表</button>
	                                </span>
	                        </div>
	                        <hr>
	                        <br>
                        </c:if>
                        
                       <div class="row">
                            <div class="col-lg-12">
                               <div id="msg">
                               
                               </div>
                               </div>
                          </div>
                        
                        
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>
	  

</body>

</html>
