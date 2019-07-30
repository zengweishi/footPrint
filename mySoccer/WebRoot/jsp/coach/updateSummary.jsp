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
	var i ="0";
	function writePlanCoachSumBt(planId) {
		i = planId;
	}
	
	var j="0";
	var k="0";
	
	var flag = 0;
	function writePlanCoachSummaryBt(planId,pid) {
		j = planId;
		k = pid;
		// var btn = document.getElementById("summaryBT");
       // $("#summaryBT").attr("disabled", "true"); 
		//获取并设置button的value值
		/* var val=document.getElementById("summaryBT").value
		if(val == "write") {
			document.getElementById("summaryBT").value = "update";
			flag = 1;
		} */
		//flag = flag+1; //判断是不是第一次点击
		
	}
	
	var s="0";
	function updatePlanCoachSummaryBt(summaryId) {
		s = summaryId;
		//location.href = "${pageContext.request.contextPath}/updateSummaryJump?summaryId="+summaryId;
	}

	function writePlanCoachSum() {
		
		document.getElementById('writePlanCoachSum').action = "${pageContext.request.contextPath}/writeCoachPlanSum?code=0&planId="+i;  
        document.getElementById("writePlanCoachSum").submit();  
        
	}
	
	function writeCoachPlanSummary() {
		if(flag == 0) {

		document.getElementById('writeCoachPlanSummaryForm').action = "${pageContext.request.contextPath}/writeCoachPlanSummary?code=0&planId="+j+"&pid="+k;  
        document.getElementById("writeCoachPlanSummaryForm").submit();  
			
		}
		else {

			document.getElementById('writeCoachPlanSummaryForm').action = "${pageContext.request.contextPath}/updateCoachPlanSummary?code=0&planId="+j+"&pid="+k;   
	        document.getElementById("writeCoachPlanSummaryForm").submit();  
		}
	}
	
	function updateCoachPlanSummary() {
		
		document.getElementById('updateCoachPlanSummaryForm').action = "${pageContext.request.contextPath}/updateCoachPlanSummary?code=0&summaryId="+s;  
        document.getElementById("updateCoachPlanSummaryForm").submit();  
	}
	
	
	<%-- window.onload = function(){
		
		var updateSummary="<%=request.getAttribute("updateSummary")%>"; 
		//alert(updateSummary);
		document.getElementById("updateSummaryContent").value="${updateSummary.summaryContent}";
		document.getElementById("updateSummarySuggest").value="${updateSummary.summarySuggest}";
		document.getElementById("updateSummaryScore").value="${updateSummary.summaryScore}";

		}; --%>
	
</script>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
        <div class="row animated fadeInRight">
		
            <div class="col-sm-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>训练情况</h5>
                    </div>
                    <div>
                      <!--  <div class="ibox-content no-padding border-left-right">
                            <img alt="image" class="img-responsive" src="img/profile_big.jpg">
                        </div> -->
                        <div class="ibox-content profile-content">
                            <h3><strong>${coachPlan.planTime }</strong></h3>
                            <p><i class="fa fa-map-marker"></i> ${coachPlan.planAddress }</p>
                            <h5>训练内容：</h5>
                            <p>${coachPlan.planContent}</p>
							<div><strong>教练：</strong>${coachPlan.planCoach.coachRealName}</div>
                        
                        </div>
                    </div>
                </div>
                
                 <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>综合评价</h5>
                       <div class="ibox-tools">
                       
	                        <a href="javascript:void(0)" onclick="writePlanCoachSumBt('${coachPlan.planId}')"  class="btn btn-default btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
						data-target="#myModal">编辑</a>
                           
                        </div>
                        
                    </div>
                    <div>
                      <!--  <div class="ibox-content no-padding border-left-right">
                            <img alt="image" class="img-responsive" src="img/profile_big.jpg">
                        </div> -->
                        <div class="ibox-content profile-content">
                        		${coachPlan.planCoachSum }
                        </div>
                    </div>
                </div>
                
            </div>

			
			
            <div class="col-sm-8">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>教练总结</h5>
                       <%--  <div class="ibox-tools">
                       		 <a href="javascript:void(0)" onclick="writePlanCoachSumBt('${coachPlan.planId}')"  class="btn btn-default btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
						data-target="#myModal2">编辑</a>
                           
                        </div> --%>
                    </div>
                   
					<div>
						<table class="table table-bordered">
							<tr>
								<td>姓名</td>
								<td>位置</td>
								<td>评价</td>
								<td>建议</td>
								<td>评分</td>
								<td style="width: 15px">编辑</td>
							</tr>
							
							<c:forEach var="p" items="${coachPlayer}">
								<tr>
								<td>${p.pRealName}</td>
								<td>${p.pPosition}</td>
								<c:if test="${not empty summaryList }">
									<c:forEach var="s" items="${summaryList}">
										<c:if test="${p.pid eq s.summaryPlayerId }">
										<!-- 创建一个标记 -->
											<c:set var="tdFlag" scope="page" value="${ p.pid }"></c:set>
											<td>${s.summaryContent }</td>
											<td>${s.summarySuggest }</td>
											<td>${s.summaryScore }</td>
										</c:if>
									</c:forEach>
								</c:if>
								<c:if test="${tdFlag ne p.pid}">
									<td></td>
									<td></td>
									<td></td>
								</c:if>
					
									<%-- <c:forEach var="s" items="${summaryList}">
										<c:if test="${p.pid eq s.summaryPlayerId }">
											<td>完成</td>
										</c:if>
										<c:if test="${p.pid ne s.summaryPlayerId }">
											<td><a href="javascript:void(0)" onclick="writePlanCoachSummaryBt('${coachPlan.planId}','${p.pid} ')"  class="btn btn-default btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
									data-target="#myModal2">编辑</a></td>
										</c:if>
									</c:forEach> --%>
									
								<%-- <td><a href="javascript:void(0)" onclick="writePlanCoachSummaryBt('${coachPlan.planId}','${p.pid} ')" value="write"  class="btn btn-default btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
									data-target="#myModal2">编辑</a></td> --%>
								<td>
									<button type="button" class="btn btn-default btn-xs" id="summaryBT" name="summaryBT" value="write" onclick="writePlanCoachSummaryBt('${coachPlan.planId}','${p.pid} ')"
									 data-toggle="modal" data-target="#myModal2">
										  编辑
									</button>
								</td>
								<%-- <c:if test="${empty summaryList}">
									<td><a href="javascript:void(0)" onclick="writePlanCoachSummaryBt('${coachPlan.planId}','${p.pid} ')"  class="btn btn-default btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
									data-target="#myModal2">编辑</a></td>
								</c:if> --%>
							</tr>
							</c:forEach>
							
							
						</table>
					</div>
					
					
					
                </div>

            </div>
			
			
        </div>
    </div>
    
    
      <!-- Modal -->
	<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog " role="document" style="z-index: 2041;">
    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					  <h4 class="modal-title" id="myModalLabel">训练总评</h4> 
				</div>
				
				
    		<form id="writePlanCoachSum" name="Form1" action="" method="post">
					<div class="modal-body">
						<textarea class="form-control" rows="9" id="planCoachSum" name="planCoachSum">
						</textarea>
	 				</div>
	 				
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
	 				    <button class="btn btn-success" onclick="writePlanCoachSum()" id="writePlanCoachSumBt" type="button">完成</button> 
						<nav id="page"> </nav>
					</div>
			</form>
				
				
			</div>
		</div>
	</div> 
	
	
	<!-- Modal -->
	<div class="modal fade " id="myModal2" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog " role="document" style="z-index: 2041;">
    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					  <h4 class="modal-title" id="myModalLabel">撰写球员评价</h4> 
				</div>
				
				
    		<form id="writeCoachPlanSummaryForm" name="writeCoachPlanSummaryForm" action="" method="post">
					<div class="modal-body">
						<!-- <textarea class="form-control" rows="9" id="planCoachSum" name="planCoachSum">
						</textarea> -->
						<table class="table table-bordered .table-hover">
							<tr>
								<td>评价</td>
								<td><textarea class="form-control" rows="3" id="summaryContent" name="summaryContent">
						</textarea></td>
							</tr>
							<tr>
								<td>建议</td>
								<td><textarea class="form-control" rows="3" id="summarySuggest" name="summarySuggest">
						</textarea></td>
							</tr>
							<tr>
								<td>评分</td>
								<td><div class="input-group">
									  <input type="text" class="form-control" id="summaryScore" name="summaryScore" placeholder="" aria-describedby="sizing-addon2">
									</div>
								</td>
							</tr>
						</tbody> 
					</table>
	 				</div>
	 				
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
	 				    <button class="btn btn-success" onclick="writeCoachPlanSummary()" id="writeCoachPlanSummaryBt" type="button">完成</button> 
						<nav id="page"> </nav>
					</div>
			</form>
				
				
			</div>
		</div>
	</div> 
	
	
		<!-- Modal -->
	<div class="modal fade " id="myModal3" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog " role="document" style="z-index: 2041;">
    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					  <h4 class="modal-title" id="myModalLabel">撰写球员评价</h4> 
				</div>
				
				
    		<form id="updateCoachPlanSummaryForm" name="updateCoachPlanSummaryForm" action="" method="post">
					<div class="modal-body">
						<!-- <textarea class="form-control" rows="9" id="planCoachSum" name="planCoachSum">
						</textarea> -->
						<table class="table table-bordered .table-hover">
							<tr>
								<td>评价</td>
								<td><textarea class="form-control" rows="3" id="updateSummaryContent" name="updateSummaryContent">
						</textarea></td>
							</tr>
							<tr>
								<td>建议</td>
								<td><textarea class="form-control" rows="3" id="updateSummarySuggest" name="updateSummarySuggest">
						</textarea></td>
							</tr>
							<tr>
								<td>评分</td>
								<td><div class="input-group">
									  <input type="text" class="form-control" id="updateSummaryScore" name="updateSummaryScore" placeholder="" aria-describedby="sizing-addon2">
									</div>
								</td>
							</tr>
						</tbody> 
					</table>
	 				</div>
	 				
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
	 				    <button class="btn btn-success" onclick="updateCoachPlanSummary()" id="updateCoachPlanSummaryBt" type="button">完成</button> 
						<nav id="page"> </nav>
					</div>
			</form>
				
				
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
