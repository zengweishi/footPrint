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
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
	<script src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>

 
	
<script type="text/javascript">
	
	 function findContent(aId) {
		 $.post("${pageContext.request.contextPath}/findAnnouncementContent",{'aId':aId},function(data) {
			 var jsonObj = eval(data);
			
			//alert(jsonObj);
			/* var html="";
			for(var i=0;i<jsonObj.length;i++) {
				html+="<tr><td>"+jsonObj[i].orderNum+"</td><td>"+jsonObj[i].receiverInfo+"</td><td>"+jsonObj[i].price+"</td><td>"+jsonObj[i].customer.cusName+"</td><td><a href='#'/>删除订单</a></td></tr>";
				
			} */
			/* var html = "";
			html+="<tr><td>"+jsonObj+"</td><td>"+jsonObj+"</td></tr>"; */
			$('#msg').html(jsonObj);
			
			
		}); 
	} 
</script>
	
	
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	
		<div class="col-md-12">
		  <c:forEach var="p" items="${ pageBean.list }">
<!-- 			    <div class="row" col-md-6>-->
			    <div class="col-md-6" style="margin-top: 20px;width: 550px">
 				  	<div class="col-sm-6 col-md-4" > 
					    <div class="thumbnail" style="width: 500px">
					      <div class="caption" style="width: 500px" >
					      <h3>${ fn:substring(p.aTime,0,16) }</h3>
					        <%-- <h4>${p.aTime}</h4> --%>
					        <p>${ fn:substring(p.aContent,0,30) }......</p>
					        <p>
					       <a href="javascript:void(0)"  onclick="findContent('${p.aId}')" class="btn btn-primary" role="button" class="btn btn-primary btn-sm" data-toggle="modal"
					data-target="#myModal">查看</a>  
				
					<a href="${pageContext.request.contextPath }/deleteAnnouncement?aId=${p.aId}" class="btn btn-default" role="button">删除</a></p>
					      </div>
					    </div>
				  </div>
			</div>
		</c:forEach>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="z-index: 2041;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">公告内容</h4>
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
					<p id="msg"></p>
				</div>
				<div class="modal-footer">
					<nav id="page"> </nav>
				</div>
			</div>
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
						<a href="${pageContext.request.contextPath }/findAllAnnouncementByClub?currPage=${ pageBean.currPage-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
				
				<!-- 当点击的页数是当前页时无效,不是当前页则可以跳转 -->
				<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
					<c:if test="${ pageBean.currPage == i }">
						<li class="active"><a href="#">${ i }</a></li>
					</c:if>
					
					<c:if test="${ pageBean.currPage != i }">
						<li><a href="${pageContext.request.contextPath }/findAllAnnouncementByClub?currPage=${ i }">${ i }</a></li>
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
						<a href="${pageContext.request.contextPath }/findAllAnnouncementByClub?currPage=${ pageBean.currPage + 1 }" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
			</ul>
		</div>  
	
		<!-- 分页结束=======================        -->
	
	
	
	
	
	
	
  </body>
</html>
