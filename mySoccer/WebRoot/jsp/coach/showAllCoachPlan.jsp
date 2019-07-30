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
	var j = "0";
	
	function lookCoachPlan(planId) {
		i = planId;
		
	
		
		$.post("${pageContext.request.contextPath}/showCoachPlanContent?",{'planId':planId},function(data) {
			var jsonObj = eval(data);
			var html="";
			html+="<tr><td>时间</td><td>"+jsonObj[0].planTime+"</td></tr><tr><td>地点</td><td>"+jsonObj[0].planAddress+"</td></tr><tr><td>内容</td><td>"+jsonObj[0].planContent+"</td></tr>";
			$("#msg").html(html);
		});
	}
	function deleteCoachPlanButton(planId) {
		j = planId;
	}
	function deleteCoachPlan() {
		location.href = "${pageContext.request.contextPath}/deleteCoachPlanByCoach?code=1&planId="+j;
	}
	function updateCoachPlan() {
		location.href = "${pageContext.request.contextPath}/jumpToUpdatePlan?planId="+i;
	}
	function findSummary() {
		location.href = "${pageContext.request.contextPath}/writeSummary?planId="+i;
	}
</script>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
		
          <div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>历史训练 <small></small></h5>
                    </div>
                    <div class="ibox-content">

	                   <div class="row">
						 <c:forEach var="p" items="${ pageBean.list }">
	                            <div class="col-sm-4" >
	                                <div class="panel panel-info" style="height: 180px">
	                                    <div class="panel-heading">
                                    		
                                    			<i class="fa fa-clock-o"></i>&nbsp;&nbsp;${fn:substring(p.planTime,0,19) }      
                                    		
                                    		
                                    		<div class="ibox-tools">
                                    			 <a href="javascript:void(0)" onclick="lookCoachPlan('${p.planId}')"  class="btn btn-default btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
					data-target="#myModal">查看</a> 
												<a href="javascript:void(0)" onclick="deleteCoachPlanButton('${p.planId}')"  class="btn btn-danger btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
					data-target="#myModal2">删除</a>                                	
	                                    	 </div>

	                                    </div>
										<div class="panel-footer">
	                                        	<strong>LOC：</strong>${p.planAddress}
	                                    </div>
	                                    <div class="panel-body" >
	                                        <p> ${ fn:substring(p.planContent,0,60)}......</p>
	                                    </div>
	                                </div>
	                            </div>
						</c:forEach>
	                        </div>
					
					
                    </div>
                </div>
            </div>
        </div>
		
		   </div>
    </div>
    
			
			<c:if test="${ empty totalCoachPlan }">
				<c:if test="${not empty deletePlan }">
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
										<a href="${pageContext.request.contextPath }/showAllCoachPlan?code=1&currPage=${ pageBean.currPage-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
									</li>
								</c:if>
								
								<!-- 当点击的页数是当前页时无效,不是当前页则可以跳转 -->
								<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
									<c:if test="${ pageBean.currPage == i }">
										<li class="active"><a href="${pageContext.request.contextPath }/showAllCoachPlan?code=1&currPage=1#">${ i }</a></li>
									</c:if>
									
									<c:if test="${ pageBean.currPage != i }">
										<li><a href="${pageContext.request.contextPath }/showAllCoachPlan?code=1&currPage=${ i }">${ i }</a></li>
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
										<a href="${pageContext.request.contextPath }/showAllCoachPlan?code=1&currPage=${ pageBean.currPage + 1 }" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:if>
							</ul>
						</div>  
					</c:if>
					<c:if test="${ empty deletePlan }">
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
										<a href="${pageContext.request.contextPath }/showAllCoachPlan?code=0&currPage=${ pageBean.currPage-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
									</li>
								</c:if>
								
								<!-- 当点击的页数是当前页时无效,不是当前页则可以跳转 -->
								<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
									<c:if test="${ pageBean.currPage == i }">
										<li class="active"><a href="#">${ i }</a></li>
									</c:if>
									
									<c:if test="${ pageBean.currPage != i }">
										<li><a href="${pageContext.request.contextPath }/showAllCoachPlan?code=0&currPage=${ i }">${ i }</a></li>
									</c:if>	
								</c:forEach>
								
								<!-- 当页数为最后一页时，下一页按钮失效 -->
								<c:if test="${ pageBean.currPage == pageBean.totalPage }">
									<li class='disabled'>
										<a href="${pageContext.request.contextPath }/showAllCoachPlan?code=1&currPage=${ pageBean.totalPage }#" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:if>
								<c:if test="${ pageBean.currPage != pageBean.totalPage }">
									<li>
										<a href="${pageContext.request.contextPath }/showAllCoachPlan?code=0&currPage=${ pageBean.currPage + 1 }" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:if>
							</ul>
						</div>  
					</c:if>
	</c:if>
		<!-- 分页结束=======================        -->
         
     
    
     <!-- Modal -->
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-lg" role="document" style="z-index: 2041;">
    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					  <h4 class="modal-title" id="myModalLabel">训练安排</h4> 
				</div>
				<div class="modal-body">
					 <table class="table table-bordered .table-hover">
						<tbody id="msg">

						</tbody> 
					</table>
					 <!-- <h3 class="modal-title" id="myModalLabel">请您确认是否解除该球员？</h3> -->
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 				
 --%>				
 				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
 				    <button class="btn btn-success" onclick="findSummary()" id="findSummaryBt" type="button">查看详情</button> 
					<nav id="page"> </nav>
				</div>
			</div>
		</div>
	</div> 
	
	
	
	
	     <!-- Modal -->
	<div class="modal fade bs-example-modal-sm" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document" style="z-index: 2041;">
    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					  <h4 class="modal-title" id="myModalLabel">FOR SURE AGAIN</h4> 
				</div>
				<div class="modal-body">
					 <table class="table table-bordered .table-hover">
						<tbody id="msg">

						</tbody> 
					</table>
					 <h3 class="modal-title" id="myModalLabel">请您确认是否删除本次训练安排？</h3> 
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 				
 --%>				
 				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					<button type="button" class="btn btn-danger" onclick="deleteCoachPlan()" data-dismiss="modal">删除</button>
					<nav id="page"> </nav>
				</div>
			</div>
		</div>
	</div> 
    
    

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>



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
