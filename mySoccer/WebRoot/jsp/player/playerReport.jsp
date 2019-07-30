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


    <title></title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/load.css" type="text/css" />
    <script src="${ pageContext.request.contextPath }/js/echarts.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageContext.request.contextPath}/js/jquery.form.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">

<%--  $("#deedsTitleTime").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
				$("#deedsTitle").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
				$("#deedsContent").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
				$("#deedsDate").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
  --%>


</style>
</script>
 <script type="text/javascript">
 	function playerReportBt(pid) {
 		
		$.post("${pageContext.request.contextPath}/getPlayerReport",{'pid':pid},function(data){
			var jsonObj = eval('('+data+')');
			if(!(data.length === 1)) {
				document.getElementById("reportMindScore").value=jsonObj.reportMindScore;
				document.getElementById("reportDefensiveScore").value=jsonObj.reportDefensiveScore;
				document.getElementById("reportShotScore").value=jsonObj.reportShotScore;
				document.getElementById("reportDrawstringScore").value=jsonObj.reportDrawstringScore;
				document.getElementById("reportPassBallScore").value=jsonObj.reportPassBallScore;
				document.getElementById("reportSpeedScore").value=jsonObj.reportSpeedScore;
				document.getElementById("reportMind").value=jsonObj.reportMind;
				document.getElementById("reportDefensive").value=jsonObj.reportDefensive;
				document.getElementById("reportShot").value=jsonObj.reportShot;
				document.getElementById("reportDrawstring").value=jsonObj.reportDrawstring;
				document.getElementById("reportPassBall").value=jsonObj.reportPassBall;
				document.getElementById("reportSpeed").value=jsonObj.reportSpeed;
				document.getElementById("reportFeatureList").value=jsonObj.reportFeatureList;
				
			}
			

		});
 	}
 
	function test1() {
		document.getElementById('createPlayerReportForm').action = "${pageContext.request.contextPath}/createPlayerReport";  
        document.getElementById("createPlayerReportForm").submit();  
	}
	
	$(function(){
		 
		  $('#identifyBt').click(function(){//点击按钮提交
			       /*  var imgFile =  document.getElementById("identifyForm");
		  			var fileObj = imgFile[0]
		  			alert(fileObj) */
			          //要提交的表单id为form1
	                
	                
		            $.ajax({
		                 url:"${pageContext.request.contextPath}/identifyPhoto",
		                 data:new FormData($("#identifyForm")[0]),
		                 type:"post",
		                 processData : false,
		                 contentType : false,
		                 async:false, 
		                 success:function(data){//ajax返回的数据
		                	 var json = eval(data);
		                	 var html=""
		                	 for(var i=0;i<json.length;i++) {
		                		 html+=json[i];
		                		
		                	 }
		                	 $("#photoResult").html(html);
		                	 //document.getElementById("photoResult").value = html;
		                 }
		            });     
		        });
		 
		 
		});
	
	
	
	
	
		 
		function test2() {
			var reportPhotoData =  document.getElementById("photoResult").value;
		  	 $.post("${pageContext.request.contextPath}/autoWrite",{'reportPhotoData':reportPhotoData},function(data){
		  	    var jsonList = eval(data);
		  	   /*  alert(data)
		  		alert(jsonList)
		  		alert(jsonList[1].substr(1,jsonList[1].length-1)) */
		  	
		  		
	 			for(var i=0;i<jsonList.length;i++) {
	 				if(jsonList[i].substr(0,1) === "1") {
	 					/* alert("1")
	 					alert(jsonList[i].substr(1,jsonList[i].length-1)) */
	 					document.getElementById("reportMind").value = jsonList[i].substr(1,jsonList[i].length-1);
	 				}
					if(jsonList[i].substr(0,1) === "2") {
						/* alert("2")
						alert(jsonList[i].substr(1,jsonList[i].length-1)) */
	 					document.getElementById("reportDefensive").value = jsonList[i].substr(1,jsonList[i].length-1);		
					}
					if(jsonList[i].substr(0,1) === "3") {
						/* alert("3")
						alert(jsonList[i].substr(1,jsonList[i].length-1)) */
	 					document.getElementById("reportShot").value = jsonList[i].substr(1,jsonList[i].length-1);
					}
					if(jsonList[i].substr(0,1) === "4") {
						/* alert("4")
						alert(jsonList[i].substr(1,jsonList[i].length-1)) */
	 					document.getElementById("reportDrawstring").value = jsonList[i].substr(1,jsonList[i].length-1);
					}
					if(jsonList[i].substr(0,1) === "5") {
						/* alert("5")
						alert(jsonList[i].subString(1,jsonList[i].length-1)) */
	 					document.getElementById("reportPassBall").value = jsonList[i].substr(1,jsonList[i].length-1);
					}
					if(jsonList[i].substr(0,1) === "6") {
						/* alert("6")
						alert(jsonList[i].substr(1,jsonList[i].length-1)) */
	 					document.getElementById("reportSpeed").value = jsonList[i].substr(1,jsonList[i].length-1);
					}
					

	 			}		  		
			}); 
		}
	 
	
	
</script>	

<script type="text/javascript">
/* function createPlayerReport(pid) {
	alert(1)
	 $("#createPlayerReportForm").submit(function (){
		 var ajax_url = "${pageContext.request.contextPath}/createPlayerReport"; //表单目标 
		  var ajax_type = $(this).attr('POST'); //提交方法 
		  var ajax_data = $(this).serialize(); //表单数据 
		 $.ajax({
			 type:ajax_type, //表单提交类型 
			 url:ajax_url, //表单提交目标 
			 data:ajax_data, //表单数据
			 success:function(msg){
				  window.location.href="${pageContext.request.contextPath}/findPlayerReport?pid="+pid;
			  }
			
		 });
	    });
} */
/* function test1(pid) {
	
		  var formParam = $("#createPlayerReportForm").serialize();//序列化表格内容为字符串 

		  $.ajax({ 

		    type:'post',   

		    url:'${pageContext.request.contextPath}/createPlayerReport', 

		    data:formParam, 

		    cache:false, 

		    dataType:'json', 

		    success:function(data){ 
		    	window.location.href="${pageContext.request.contextPath}/findPlayerReport?pid="+pid;
		    } 

		  }); 

} */
/* function test1(pid) {
	alert(1)
	$.post("${pageContext.request.contextPath}/createPlayerReport",$("createPlayerReportForm").serialize(),function(data) {
		  window.location.href="${pageContext.request.contextPath}/findPlayerReport?pid="+pid;
	});
} */
/* function test1(pid) {
	alert(1)
	$("#createPlayerReportForm").submit(function (){
		  alert(2)
		 var ajax_url = "${pageContext.request.contextPath}/createPlayerReport"; //表单目标 
		  var ajax_type = $(this).attr('POST'); //提交方法 
		  var ajax_data = $(this).serialize(); //表单数据 
		 $.ajax({
			 type:ajax_type, //表单提交类型 
			 url:ajax_url, //表单提交目标 
			 data:ajax_data, //表单数据
			 success:function(msg){
				  window.location.href="${pageContext.request.contextPath}/findPlayerReport?pid="+pid;
			  }
			
		 });
	    });
} */
	
</script>

</head>

<body class="gray-bg">
    <div class="row">
		<div class="col-sm-1">
		</div>
        <div class="col-sm-10">
            <div class="wrapper wrapper-content animated fadeInUp">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="m-b-md">
									 <div class="text-center">
										<div align="center">
											<img alt="image" style="width: 90px;height: 90px; " class="img-circle m-t-xs img-responsive" src="${infoPlayer.pPhoto}">
										</div>
										<br>
										<div class="m-t-xs font-bold"><h2 style="color: #20B2AA"><strong>${infoPlayer.pName}</strong></h2></div>
									</div>
                                   
                            </div>
                            <hr>
                        </div>
						<br/>
                        <div class="row">
                            <div class="col-sm-5">
                                <dl class="dl-horizontal">

                                    <dt>姓名：</dt>
                                    <dd>${infoPlayer.pRealName}</dd>
									<br>
                                    <dt>籍贯：</dt>
                                    <dd>${infoPlayer.pAddress}</dd>
									<br>
                                    <dt>电话：</dt>
                                    <dd>${infoPlayer.pTelePhone}</dd>
<!--                                     <dd><a href="project_detail.html#" class="text-navy"> 百度</a>--> 
                                   </dd>
									<br>
                                    <dt>俱乐部：</dt>
	                                    	<dd>
		                                    	<c:if test="${ not empty infoPlayer.pClubId }">
				                                    	<a href="${pageContext.request.contextPath }/getClubInformation?cid=${infoPlayer.pClubId}">
				                                    	${infoPlayer.pClubName}
				                                    	</a>
		                                    	</c:if>
	                                    	
		                                    	<c:if test="${ (empty infoPlayer.pClubId)&&(infoPlayer.pid==existPlayer.pid) }">
			                                    		<c:if test="${empty infoPlayer.pApplyId}">
				                                    	<a href="javascript:void(0)" onclick="applyClubByPlayerBt()"  class="btn btn-success btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
							data-target="#myModal">申请</a> 
			                                    		</c:if>
							
														<c:if test="${not empty infoPlayer.pApplyId}">
														<a href="${pageContext.request.contextPath }/findClubApply"  class="btn btn-danger btn-xs" role="button" class="btn btn-danger btn-xs" >查看申请</a> 
			                                    		</c:if>
		                                    	</c:if>
	                                    	</dd>
									<br>
									<dt>球场位置：</dt>
                                    <dd><button type="button" class="btn btn-info btn-xs">${infoPlayer.pPosition}</button></dd>
									
									 
									 
                                </dl>
                            </div>
                            <div class="col-sm-7" id="cluster_info">
                                <dl class="dl-horizontal">

                                    <dt>性别：</dt>
                                    <dd>${infoPlayer.pGender}</dd>
									<br>
                                    <dt>生日：</dt>
                                    <dd>${infoPlayer.pBirthday}</dd>
									<br>
									<dt>邮箱：</dt>
                                    <dd>${infoPlayer.pNumber}</dd>
									<br>
									<dt>教练：</dt>
	                                    <dd>
	                                    	<a href="${pageContext.request.contextPath }/getCoachInformation?coachId=${infoPlayer.pCoach.coachId}">
	                                    		${infoPlayer.pCoach.coachRealName}  	
	                                    	</a>
	                                    </dd>
									<br>
									
									<dt>个人简介：</dt>
                                    <dd>${infoPlayer.pIntroduction}</dd>
                                   
                                </dl>
                            </div>
                        </div>
                      
                        <div class="row m-t-sm">
                            <div class="col-sm-12">
                                <div class="panel blank-panel">
                                    <div class="panel-heading">
                                        <div class="panel-options">
                                            <ul class="nav nav-tabs" id="myTab">
                                                <li><a href="javascript:void(0);" onclick="findPlayerDeeds()" id="tab1" data-toggle="tab">球员报告</a>
                                                </li>
                                               
                                                
                                               
                                            </ul>
                                        </div>
                                    </div>

                          <div class="panel-body">
                              <div class="tab-content">
                              
					                          <div class="tab-pane active" id="tab-1">        
			<div class="col-sm-12">
                <div class="ibox float-e-margins">
					 <div class="ibox-title">
                        <h5>球员技能描述</h5>
                        <div class="ibox-tools">
                            <a href="javascript:void(0)" onclick="playerReportBt('${infoPlayer.pid}')"  class="btn btn-success btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
						data-target="#myModal">更新球员报告</a>
                        </div>
                    </div>
					<div class="ibox-content timeline">                      
                        	
                        <div class="timeline-item" style="width: 130%">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i>
                                </div>
                                <div class="col-xs-7 content">
                                    <a class="faq-question"><strong>球员特性</strong></a>
                                    	<c:forEach var="p" items="${ reportFeatureList }">
                                    		<button class="btn btn-danger">${p }</button>
                                    		&nbsp;&nbsp;&nbsp;
                                    	</c:forEach>
                                </div>
                            </div>
                        </div>
                        
                         <div class="timeline-item" style="width: 130%">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i>
                                </div>
                                <div class="col-xs-7 content">
                                    <a class="faq-question"><strong>精神属性</strong></a>
                                    <p>${report.reportMind }</p>
                                </div>
                            </div>
                        </div>
                        
                         <div class="timeline-item" style="width: 130%">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i>
                                </div>
                                <div class="col-xs-7 content">
                                    <a class="faq-question"><strong>防守能力</strong></a>
							        <p>${report.reportDefensive }</p>
                                </div>
                            </div>
                        </div>
                       
                       
                         <div class="timeline-item" style="width: 130%">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i>
                                </div>
                                <div class="col-xs-7 content">
                                    <a class="faq-question"><strong>射门能力</strong></a>
                                    <p>${report.reportShot }</p>
                                </div>
                            </div>
                        </div>
                        
                        
                          <div class="timeline-item" style="width: 130%">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i>
                                </div>
                                <div class="col-xs-7 content">
                                    <a class="faq-question"><strong>盘带能力</strong></a>
                                    <p>${report.reportDrawstring }</p>
                                </div>
                            </div>
                        </div>
                          <div class="timeline-item" style="width: 130%">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i>
                                </div>
                                <div class="col-xs-7 content">
                                    <a class="faq-question"><strong>传球能力</strong></a>
                                    <p>${report.reportPassBall }</p>
                                </div>
                            </div>
                        </div>
                          <div class="timeline-item" style="width: 130%">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i>
                                </div>
                                <div class="col-xs-7 content">
                                    <a class="faq-question"><strong>身体素质</strong></a>
                                    <p>${report.reportSpeed }</p>
                                </div>
                            </div>
                        </div>
                        
                        
                       

                    </div>
                </div>
            </div>
           
            
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
						 <div class="ibox-title">
	                        <h5>技能评分情况</h5>
	                    </div>
	                    
	                    <div class="ibox-content timeline">
 	                    	<div id="main" style="width: 800px;height:400px;"></div>
 							<script type="text/javascript">
	                      		var myChart = echarts.init(document.getElementById('main'));
	                      		option = {
	                      			    title: {
	                      			        text: '技能评分图'
	                      			    },
	                      			    tooltip: {},
	                      			    legend: {
	                      			        data: [ '综合技能（Comprehensive Skills）']
	                      			    },
	                      			    radar: {
	                      			        // shape: 'circle',
	                      			        name: {
	                      			            textStyle: {
	                      			                color: '#fff',
	                      			                backgroundColor: '#00C5CD',
	                      			                borderRadius: 3,
	                      			                padding: [3, 5]
	                      			           }
	                      			        },
	                      			        indicator: [
	                      			           { name: '精神属性（Psyche）', max: 100},
	                      			           { name: '防守能力（Defensive）', max: 100},
	                      			           { name: '射门能力（Shot）', max: 100},
	                      			           { name: '盘带能力（Drawstring）', max: 100},
	                      			           { name: '传球能力（PassBall）', max: 100},
	                      			           { name: '身体素质（Body）', max: 100}
	                      			        ]
	                      			    },
	                      			    series: [{
	                      			        name: '预算 vs 开销（Budget vs spending）',
	                      			        type: 'radar',
	                      			        // areaStyle: {normal: {}},
	                      			        data : [
	                      			             {
	                      			                value : [89, 90, 98, 97, 89, 95],
	                      			                name : '综合技能（Comprehensive Skills）',
	                      			                
	                      			                areaStyle: {
	                      			                        normal: {
	                      			                            color: 'rgba(135,206,250)'
	                      			                        }
	                      			                    }
	                      			                
	                      			            }
	                      			        ]
	                      			    }]
	                      			};
	                      		 window.onload = function() {
	                      			var infoPlayer="<%=request.getAttribute("infoPlayer")%>";
	                      			var pid = "${infoPlayer.pid}"
	                      			$.post("${pageContext.request.contextPath}/getReportScore",{'pid':pid},function(data){
	                      				var jsonObj = eval(data);
	                      				myChart.setOption({
	                      				    title: {
	                      				        text: '综合技能（Comprehensive Skills）'
	                      				    },
	                      				    tooltip: {},
	                      				    legend: {
	                      				        data: [ '综合能（Comprehensive Skills）']
	                      				    },
	                      				    radar: {
	                      				        // shape: 'circle',
	                      				        name: {
	                      				            textStyle: {
	                      				                color: '#fff',
	                      				                backgroundColor: '#00C5CD',
	                      				                borderRadius: 3,
	                      				                padding: [3, 5]
	                      				           }
	                      				        },
	                      				        indicator: [
	                      				           { name: '精神属性（Psyche）', max: 100},
	                      				           { name: '防守能力（Defensive）', max: 100},
	                      				           { name: '射门能力（Shot）', max: 100},
	                      				           { name: '盘带能力（Drawstring）', max: 100},
	                      				           { name: '传球能力（PassBall）', max: 100},
	                      				           { name: '身体素质（Body）', max: 100}
	                      				        ]
	                      				    },
	                      				    series: [{
	                      				        name: '预算 vs 开销（Budget vs spending）',
	                      				        type: 'radar',
	                      				      itemStyle: {
	                                              normal: {
	                                                  color : "rgba(0,0,0,0)", // 图表中各个图区域的边框线拐点颜色
	                                                  lineStyle: {
	                                                      color:"#00C5CD" // 图表中各个图区域的边框线颜色
	                                                  },
	                                              }
	                                          },
	                      				        // areaStyle: {normal: {}},
	                      				        data : [
	                      				             {
	                      				                value : jsonObj,
	                      				                 name : '综合技能（Comprehensive Skills）', 
	                      				                
	                      				                areaStyle: {
	                      				                        normal: {
	                      				                            color: 'rgba(135,206,250)'
	                      				                        }
	                      				                    }
	                      				                
	                      				            }
	                      				        ]
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
                        
                                            
                                            
                               
                                         
                                            
                                            
                                        </div>

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		
		<div class="col-sm-1">
		</div>
		
    </div>
    
    
    
    
    
    
 
	
	
	
	<!-- Modal -->
	<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-lg" role="document" style="z-index: 2041;">
    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">更新球员报告</h4>
				</div>
				
						<form id="createPlayerReportForm" name="createPlayerReportForm" action="" method="post">
							  <input type="hidden" name="r_player" value="${infoPlayer.pid }">
							
					<div class="modal-body">
						<!-- <textarea class="form-control" rows="9" id="planCoachSum" name="planCoachSum">
						</textarea> -->
						<table class="table table-bordered .table-hover">
							<tr>
								<td style="width: 9%">技能名称</td>
								<td style="width: 9%">技能评分</td>
								<td style="width: 82%">技能评价</td>
							</tr>
							
							
							<tr>
								<td>精神属性</td>
								<td>
									<div class="input-group">
									  <input type="text" class="form-control" id="reportMindScore" name="reportMindScore" placeholder="" aria-describedby="sizing-addon2">
									</div>
								</td>
								<td><textarea class="form-control" rows="2" id="reportMind" name="reportMind">
						</textarea></td>
							</tr>

							
							<tr>
								<td>防守能力</td>
								<td>
									<div class="input-group">
									  <input type="text" class="form-control" id="reportDefensiveScore" name="reportDefensiveScore" placeholder="" aria-describedby="sizing-addon2">
									</div>
								</td>
								<td><textarea class="form-control" rows="2" id="reportDefensive" name="reportDefensive">
						</textarea></td>
							</tr>
							
							<tr>
								<td>射门能力</td>
								<td>
									<div class="input-group">
									  <input type="text" class="form-control" id="reportShotScore" name="reportShotScore" placeholder="" aria-describedby="sizing-addon2">
									</div>
								</td>
								<td><textarea class="form-control" rows="2" id="reportShot" name="reportShot">
						</textarea></td>
							</tr>
							
							<tr>
								<td>盘带能力</td>
								<td>
									<div class="input-group">
									  <input type="text" class="form-control" id="reportDrawstringScore" name="reportDrawstringScore" placeholder="" aria-describedby="sizing-addon2">
									</div>
								</td>
								<td><textarea class="form-control" rows="2" id="reportDrawstring" name="reportDrawstring">
						</textarea></td>
							</tr>
							
							<tr>
								<td>传球能力</td>
								<td>
									<div class="input-group">
									  <input type="text" class="form-control" id="reportPassBallScore" name="reportPassBallScore" placeholder="" aria-describedby="sizing-addon2">
									</div>
								</td>
								<td><textarea class="form-control" rows="2" id="reportPassBall" name="reportPassBall">
						</textarea></td>
							</tr>
							
							<tr>
								<td>身体素质</td>
								<td>
									<div class="input-group">
									  <input type="text" class="form-control" id="reportSpeedScore" name="reportSpeedScore" placeholder="" aria-describedby="sizing-addon2">
									</div>
								</td>
								<td><textarea class="form-control" rows="2" id="reportSpeed" name="reportSpeed">
						</textarea></td>
							</tr>
							
						</tbody> 
					</table>
					
					<table class="table table-bordered .table-hover">
						<tbody>
							<tr>
								<td style="width: 9%">球员特性</td>
								<td>
									<div class="input-group">
									  <input type="text" style="width: 350%" class="form-control" id="reportFeatureList" name="reportFeatureList" placeholder="各个特性以中文逗号'，'隔开" aria-describedby="sizing-addon2">
									</div>
								</td>
							</tr>
						</tbody>
					</table>
	 				</div>
	 				
					<!-- <div class="modal-footer"> -->
								<div class="modal-footer" >
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
	 				   <button type="reset" id="userAction_save_do_submit" class="btn btn-info" >重置</button>
	 				    <button class="btn btn-success" onclick="test1()" id="createPlayerReport" type="button">完成</button> 
						<nav id="page"> </nav>
					</div>
			</form>
			<div class="modal-footer">
 			<form id="identifyForm" name="identifyForm"  enctype = "multipart/form-data" method="post">
 				<table class="table table-bordered .table-hover">
 					<tr>
 						<td>
	 						<div class="text-center">
	                        <h3><strong>自动识别</strong></h3>
	                        </div>
                    </div> 
 						</td>
 						
 					</tr>
 					<tr>
 						<td>
 							
 							
 							<div class="text-left">
 								友情提示：您可以上传报告内容图片并将图片文字解析到下面的文本框中.
 								如要自动显示到上述各属性框中，那么每一个属性需以属性关键词(精神，防守，射门，盘带，传球，身体素质)开头。
 								例如：精神........。
 								            防守........。

 							</div>
 							<br>
 							<input type="file" name="reportPhoto"/>
 						<!-- <div id="loader" name ="loader" style="width: 20px;height: 20px">
 						</div> -->
 						<button class="btn btn-success" onclick="identifyBt()" id="identifyBt" name="identifyBt" type="button">自动解析</button> 
 						<button class="btn btn-info" onclick="test2()" id="autoWriteBt" name="autoWriteBt" type="button">自动显示</button> 
 						</td>
 					</tr>
 					<tr>
 						<td>
 							<textarea class="form-control" rows="6" id="photoResult" name="photoResult">
							</textarea>
						</td>
					</tr>
 				</table>
 			</form>
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

     <script>
        $(document).ready(function () {

            // Local script for demo purpose only
            $('#lightVersion').click(function (event) {
                event.preventDefault()
                $('#ibox-content').removeClass('ibox-content');
                $('#vertical-timeline').removeClass('dark-timeline');
                $('#vertical-timeline').addClass('light-timeline');
            });

            $('#darkVersion').click(function (event) {
                event.preventDefault()
                $('#ibox-content').addClass('ibox-content');
                $('#vertical-timeline').removeClass('light-timeline');
                $('#vertical-timeline').addClass('dark-timeline');
            });

            $('#leftVersion').click(function (event) {
                event.preventDefault()
                $('#vertical-timeline').toggleClass('center-orientation');
            });


        });
    </script>

  <script>
        $(document).ready(function () {

            $('#loading-example-btn').click(function () {
                btn = $(this);
                simpleLoad(btn, true)

                // Ajax example
                //                $.ajax().always(function () {
                //                    simpleLoad($(this), false)
                //                });

                simpleLoad(btn, false)
            });
        });

        function simpleLoad(btn, state) {
            if (state) {
                btn.children().addClass('fa-spin');
                btn.contents().last().replaceWith(" Loading");
            } else {
                setTimeout(function () {
                    btn.children().removeClass('fa-spin');
                    btn.contents().last().replaceWith(" Refresh");
                }, 2000);
            }
        }
    </script>

    
    

</body>

</html>
