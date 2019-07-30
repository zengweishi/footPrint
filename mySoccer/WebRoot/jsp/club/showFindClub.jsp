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
	

	
	<script type="text/javascript">
		var i = "0";
		function applyClubBt(cid) {
			i = cid;
		}
		
		function applyPlayerClub() {
			location.href = "${pageContext.request.contextPath}/applyClubByPlayer?cid="+i;	
			
		}
		
		function applyCoachClub() {
			location.href = "${pageContext.request.contextPath}/applyClubByCoach?cid="+i;
			
			
		}
		
	</script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body class="gray-bg">
  	
	<%-- 	<div class="col-md-12">
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
					 <p><a href="${pageContext.request.contextPath }/deleteCoachFromClub?coachId=${p.coachId}" class="btn btn-danger" role="button">解除教练</a> </p>
				
					</p>
					      </div>
					    </div>
				  </div>
			</div>
		</c:forEach>
	</div> --%>
	
	
	
	
	
	
	
	
	
	
	
	  <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
		
            <c:forEach var="p" items="${ clubList }">
	            <div class="col-sm-4">
            	<a href="${pageContext.request.contextPath}/getClubInformation?cid=${p.cid}">    
	                <div class="contact-box" style="height: 180px">	                   
	                        <div class="col-sm-4">
	                            <div class="text-center">
	                                <img alt="image" style="width: 60px;height: 60px" class="img-circle m-t-xs img-responsive" src="${p.clubPhoto}">
	                                <div class="m-t-xs font-bold" style="margin-left: -12px">俱乐部</div>
	                            </div>
	                           
	                        </div>
	                        <div class="col-sm-8"> 
	                         <div class="ibox-tools">
	                         <c:if test="${existPlayer.pApplyId ne p.cid }">
		                         <a href="javascript:void(0)" onclick="applyClubBt('${p.cid}')"  class="btn btn-success btn-xs" role="button" class="btn btn-success btn-xs" data-toggle="modal"
						data-target="#myModal">申请</a> 
	                         
	                         </c:if>
	                         
	                         <c:if test="${existPlayer.pApplyId==p.cid }">
		                         <a href="javascript:void(0)"  class="btn btn-danger btn-xs" role="button" class="btn btn-success btn-xs" data-toggle="modal"
						data-target="#myModal">申请中</a> 
	                         
	                         </c:if>
	                         
<%-- 									<a href="${pageContext.request.contextPath }/deleteCoachFromClub?coachId=${p.cid}" class="btn btn-success btn-xs" role="button">申请</a>
 --%>		                           
		                        </div>
	                            <h3><strong>${p.clubName }</strong></h3>
	                            <p><i class="fa fa-map-marker"></i> ${p.clubCity }</p>
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
			
		
							
					
         
        </div>
    </div>
	
	
	
	
	
	
	
	

	
	
	
     <!-- Modal -->
	<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document" style="z-index: 2041;">
    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Double Confirm</h4>
				</div>
				<div class="modal-body">
					<!-- <table class="table table-bordered .table-hover">
						<tr>
							<td>编号</td>
							<td>内容</td>
							
						</tr>
						<tbody id="msg">

						</tbody>
					</table> -->
					 <h3 class="modal-title" id="myModalLabel">请您确认是否申请该俱乐部？</h3>
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 				
 --%>				
 				</div>
				<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					<c:if test="${not empty existCoach }">
 				    	<button class="btn btn-success" onclick="applyCoachClub()" id="applyClub" type="button">确定申请</button> 
					
					</c:if>
					<c:if test="${not empty existPlayer }">
						<button class="btn btn-success" onclick="applyPlayerClub()" id="applyClub" type="button">确定申请</button> 
					</c:if>
					<nav id="page"> </nav>
				</div>
			</div>
		</div>
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
