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
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">

<script type="text/javascript">
	var i = "0";
	function deletePlayerByCoach(pid) {
		i = pid;
	}
	function deletePlayerByCoachButton() {
		location.href = "${pageContext.request.contextPath}/deletePlayerByCoach?pid="+i;
	}
</script>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
		
            <c:forEach var="p" items="${ pageBean.list }">
	            <div class="col-sm-4">
	                <div class="contact-box">
	                    <a href="${pageContext.request.contextPath}/PlayerGetInformationAction?pid=${p.pid}">
	                        <div class="col-sm-4">
	                            <div class="text-center">
	                                <img alt="image" style="width: 60px;height: 60px" class="img-circle m-t-xs img-responsive" src="${p.pPhoto}">
	                                <div class="m-t-xs font-bold" style="margin-left: -12px">${p.pPosition}</div>
	                            </div>
	                            <div class="text-center">
		                            <c:if test="${not empty delete }"> 
		                             <a href="javascript:void(0)" onclick="deletePlayerByCoach('${p.pid}')"  class="btn btn-danger btn-sm" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
					data-target="#myModal">解除球员</a> 
		                            	<!-- <button type="button" class="btn btn-danger btn-sm">解除球员</button> -->
		                             </c:if> 
		                        </div>
	                        </div>
	                        <div class="col-sm-8">
	                            <h3><strong>${p.pRealName }</strong></h3>
	                            <p><i class="fa fa-map-marker"></i> ${p.pAddress }</p>
	                            <address>
	                            <strong>个人简介:</strong><br>
	                           	${p.pIntroduction }<br>
	                          <!--   Weibo:<a href="">http://weibo.com/xxx</a><br> -->
	                            <abbr title="Phone"><strong>Tel:</strong></abbr> 
	                             <c:if test="${  (empty existClub)&&(empty existCoach) }">
	                           		 ${ fn:substring(p.pTelePhone,0,5)}xxxxxx
	                           	 </c:if>
	                           	 <c:if test="${  (not empty existClub)||( not empty existCoach) ||(not empty existPlayer)}">
	                           		  ${p.pTelePhone }                  	 
	                           	 </c:if>
	                        </address>
	                        
	                        </div>
	                        <div class="clearfix"></div>
	                    </a>
	                </div>
	            </div>
			</c:forEach>
			
			<c:if test="${ empty totalPlayer }">
				<c:if test="${not empty delete }">
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
										<a href="${pageContext.request.contextPath }/showAllPlayer?code=1&currPage=${ pageBean.currPage-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
									</li>
								</c:if>
								
								<!-- 当点击的页数是当前页时无效,不是当前页则可以跳转 -->
								<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
									<c:if test="${ pageBean.currPage == i }">
										<li class="active"><a href="#">${ i }</a></li>
									</c:if>
									
									<c:if test="${ pageBean.currPage != i }">
										<li><a href="${pageContext.request.contextPath }/showAllPlayer?code=1&currPage=${ i }">${ i }</a></li>
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
										<a href="${pageContext.request.contextPath }/showAllPlayer?code=1&currPage=${ pageBean.currPage + 1 }" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:if>
							</ul>
						</div>  
					</c:if>
					<c:if test="${ empty delete }">
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
										<a href="${pageContext.request.contextPath }/showAllPlayer?code=0&currPage=${ pageBean.currPage-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
									</li>
								</c:if>
								
								<!-- 当点击的页数是当前页时无效,不是当前页则可以跳转 -->
								<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
									<c:if test="${ pageBean.currPage == i }">
										<li class="active"><a href="#">${ i }</a></li>
									</c:if>
									
									<c:if test="${ pageBean.currPage != i }">
										<li><a href="${pageContext.request.contextPath }/showAllPlayer?code=0&currPage=${ i }">${ i }</a></li>
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
										<a href="${pageContext.request.contextPath }/showAllPlayer?code=0&currPage=${ pageBean.currPage + 1 }" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:if>
							</ul>
						</div>  
					</c:if>
	</c:if>
		<!-- 分页结束=======================        -->
         
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
					<!--  <h4 class="modal-title" id="myModalLabel">请您确定删除文章</h4> -->
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
					 <h3 class="modal-title" id="myModalLabel">请您确认是否解除该球员？</h3>
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 				
 --%>				
 				</div>
				<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">算了</button>
 				    <button class="btn btn-danger" onclick="deletePlayerByCoachButton()" id="deletePlayerByCoachBt" type="button">确定解除</button> 
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
