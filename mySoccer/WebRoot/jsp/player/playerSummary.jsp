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
 
    <script src="${ pageContext.request.contextPath }/js/echarts.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">

<script type="text/javascript">
	var i ="0";
	function writePlayerSumBt(summaryId) {
		i = summaryId;
		//alert(summaryId)
	}

	
	
	function writePlayerSum1() {
		document.getElementById('writePlayerSum').action = "${pageContext.request.contextPath}/writePlayerSumByPlayer?summaryId="+i;  
        document.getElementById("writePlayerSum").submit();  
	}
	

	
	
	

    // 使用刚指定的配置项和数据显示图表。
   // myChart.setOption(option);
	
</script>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content">
       <!--  <div class="row animated fadeInRight"> -->
		
            <div class="col-sm-3">
            
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

			
			 <div class="col-sm-3">
            
               <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>训练评分</h5>                       
                    </div>
                    <div>
                      <!--  <div class="ibox-content no-padding border-left-right">
                            <img alt="image" class="img-responsive" src="img/profile_big.jpg">
                        </div> -->
                        <div class="ibox-content profile-content">
                        	<h1 style="color: red;">${playerSummary.summaryScore } </h1>
                        </div>
                    </div>
                </div>
                
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>教练评价</h5>                       
                    </div>
                    <div>
                      <!--  <div class="ibox-content no-padding border-left-right">
                            <img alt="image" class="img-responsive" src="img/profile_big.jpg">
                        </div> -->
                        <div class="ibox-content profile-content">
                        		${playerSummary.summaryContent }
                        </div>
                    </div>
                </div>
                
                
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>教练建议</h5>                       
                    </div>
                    <div>
                      <!--  <div class="ibox-content no-padding border-left-right">
                            <img alt="image" class="img-responsive" src="img/profile_big.jpg">
                        </div> -->
                        <div class="ibox-content profile-content">
                        		${playerSummary.summarySuggest }
                        </div>
                    </div>
                </div>
                
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>自我总结</h5> 
                        <div class="ibox-tools">
                       
	                        <a href="javascript:void(0)" onclick="writePlayerSumBt('${playerSummary.summaryId}')"  class="btn btn-default btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
						data-target="#myModal">编辑</a>
                           
                        </div>                      
                    </div>
                    <div>
                      <!--  <div class="ibox-content no-padding border-left-right">
                            <img alt="image" class="img-responsive" src="img/profile_big.jpg">
                        </div> -->
                        <div class="ibox-content profile-content">
                        		${playerSummary.summaryPlayerSum }
                        </div>
                    </div>
                </div>
                
           </div>
           
            <div class="col-sm-6">
            	<div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>过去七次训练评分走势图(——>)</h5>                       
                    </div>
                    <div>
                        <div class="ibox-content profile-content">
                        		<div id="main" style="width: 500px;height:450px;"></div>
                        		<script type="text/javascript">
                        		 // 基于准备好的dom，初始化echarts实例
                        	    var myChart = echarts.init(document.getElementById('main'));
                        	    // 指定图表的配置项和数据
                        	     var option = {

                        	    	    xAxis: {
                        	    	        type: 'category',
                        	    	        data: ['1', '2', '3', '4', '5', '6', '7']
                        	    	    },
                        	    	    yAxis: {
                        	    	        type: 'value'
                        	    	    },
                        	    	    series: [{
                        	    	    	
                        	    	        data: [1,2,3],
                        	    	        type: 'line'
                        	    	    }]
                        	    	};  
                        	    
                        	    var jsonObj;
                        	    
                        	    window.onload = function() {
                        			var planId="<%=request.getAttribute("planId")%>"; 
                        			
                        	    	$.post("${pageContext.request.contextPath}/getPracticeScore",{'planId':planId},function(data) {
                        				 jsonObj = eval(data);
                        				
                        	        
                        	        myChart.setOption({
                        	        		xAxis: {
                        			            type: 'category',
                        			            boundaryGap: false,
                        				        data: ['1', '2', '3', '4', '5', '6', '7']
                        				    },
                        				    yAxis: {
                        				        type: 'value'
                        				    },
                        			        series: [{
                        			        	name:'流入总数',

                        			        	stack: '总量',
                        			        	areaStyle: {
                        			        	normal: {
                        			        	color: '#8cd5c2' //改变区域颜色
                        			        	}
                        			        	},
                        			        	itemStyle : { 
                        			        	normal : { 
                        			        	color:'#8cd5c2', //改变折线点的颜色
                        			        	lineStyle:{ 
                        			        	color:'#8cd5c2' //改变折线颜色
                        			        	} 
                        			        	} 
                        			        	},
                        			            data: jsonObj,
                        			        	type: 'line'
                        			        }]
                        		        });
                        		   	}); 
                        	    }
                        		</script>
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
					  <h4 class="modal-title" id="myModalLabel">自我总结</h4> 
				</div>
				
				
    		<form id="writePlayerSum" name="writePlayerSum" action="" method="post">
					<div class="modal-body">
						<textarea class="form-control" rows="9" id="summaryPlayerSum" name="summaryPlayerSum">
						</textarea>
	 				</div>
	 				
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
	 				    <button type="button" class="btn btn-success" onclick="writePlayerSum1()" >完成</button>
<!-- 	 				    <button class="btn btn-success" onclick="writePlayerSum()" id="writePlayerSum" type="button">完成</button> 
 -->						<nav id="page"> </nav>
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
		<!-- </div> -->
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
