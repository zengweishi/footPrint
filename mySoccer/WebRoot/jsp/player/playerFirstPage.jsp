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
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styleindex.css" />
    <script src="${ pageContext.request.contextPath }/js/echarts.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>

<%--  $("#deedsTitleTime").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
				$("#deedsTitle").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
				$("#deedsContent").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
				$("#deedsDate").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
  --%>
  <script type="text/javascript">
  	window.onload = function() {
  		document.getElementById("tab1").click();
  	}
  </script>
  
<script type="text/javascript">
	function applyClubByPlayerBt() {
		
	}
</script>
  
<script type="text/javascript">
		var infoPlayer="<%=session.getAttribute("infoPlayer")%>"; 
		var pid = "${infoPlayer.pid}";
		var pRealName = "${infoPlayer.pRealName}"
	function findPlayerDeeds() {
			var box1 = document.getElementById("main");
	    	box1.style.width = '0px';
	    	box1.style.height = '0px';
	    	var box = document.getElementById("main2");
	    	box.style.width = '0px';
	    	box.style.height = '0px';
		$.post("${pageContext.request.contextPath}/findPlayerDeedsForInfo",{'pid':pid},function(data) {
			var infoPlayerDeeds = eval(data);
			var html=""; 
			/* html+='<div class="text-center float-e-margins p-md">'
				+'<span>布局切换：</span>'
				+'<a href="${pageContext.request.contextPath }/findPlayerDeedsForInfo#" class="btn btn-xs btn-default" id="lightVersion">浅色</a>'
				+'<a href="${pageContext.request.contextPath }/findPlayerDeedsForInfo#" class="btn btn-xs btn-default" id="darkVersion">深色</a>'
				+'</div>'; */
			for(var i = 0;i<infoPlayerDeeds.length;++i) {
				html+='<div class="vertical-timeline-block">'
					+'<div class="vertical-timeline-content">'
					+'<h1>'+infoPlayerDeeds[i].playerDeedsTitleTime+'</h1>'
					+'</div>'
					+'<div class="vertical-timeline-icon blue-bg">'
					+'<i class="fa fa-file-text"></i>'
					+'</div>'
					+'<div class="vertical-timeline-content">'
					+'<h2>'+infoPlayerDeeds[i].playerDeedsTitle+'</h2>'
					+'<p>'+infoPlayerDeeds[i].playerDeedsContent+'</p>'
					+' <span class="vertical-date">'
					+'<br/>'
					+'<small>'+infoPlayerDeeds[i].playerDeedsDate+'</small>'
					+'</span>'
					+'</div>'
					+'</div>';
					
			}
			$("#infoDiary").html("");
			$("#main").html("");
			$("#main2").html("");
			$("#playerReport").html("");
			$("#playerDeeds").html(html);
			
		});
	}

	function findPlayerDiary() {
		var box1 = document.getElementById("main");
    	box1.style.width = '0px';
    	box1.style.height = '0px';
    	var box = document.getElementById("main2");
    	box.style.width = '0px';
    	box.style.height = '0px';
		$.post("${pageContext.request.contextPath}/findDiaryForInfo",{'pid':pid},function(data) {
			var infoDiary = eval(data);
			var html = "";
			html+='<div class="wrapper wrapper-content  animated fadeInRight blog" style="margin-top: -60px">'
				+'<div class="row">';
			for(var i=0;i<infoDiary.length;++i) {
				//alert(i)
				 html+='<div class="ibox">'
					+'<div class="ibox-content">'
					+'<a href="${pageContext.request.contextPath }/findDiaryArtical?diaryId='+infoDiary[i].diaryId+'" class="btn-link">'
					+'<h2>'+infoDiary[i].diaryTitle+'</h2>'
					+'</a>'
					+'<div class="small m-b-xs">'
					//+'<span class="text-muted"> by </span> <strong>'+pRealName+'</strong>&nbsp;&nbsp;&nbsp;&nbsp;'
					+'<span class="text-muted"><i class="fa fa-clock-o"></i>'
					+infoDiary[i].diaryDate
					+'</span>'
					+'</div>'
					+'<p>'+infoDiary[i].diaryHeadContent+'......</p>'
					+'<div class="row">'
					+'<div class="col-md-6">'
					+'<button class="btn btn-success btn-xs" type="button">球员日记</button>&nbsp;&nbsp;&nbsp;&nbsp;'
					+'<button class="btn btn-info btn-xs" type="button">全部可见</button>'
					+'</div>'
					+'<div class="col-md-6">'
					+'<div class="small text-right">'
					+'<div> <i class="fa fa-comments-o"> </i> 56 评论 </div>'
					+'<i class="fa fa-eye"> </i> 144 浏览'
					+'</div>'
					+'</div>'
					+'</div>'
					+'</div>'
					+'</div>';  
			}
			html+='</div>'
				+'</div>';
			//alert(html)
			$("#playerDeeds").html("");
			$("#main").html("");
			$("#main2").html("");
			$("#playerReport").html("");
			$("#infoDiary").html(html);
		});
	}
	
	function findPlayerReport() {
		$("#playerDeeds").html("");
		$("#main").html("");
		$("#main2").html("");
		$("#infoDiary").html("");
		var box1 = document.getElementById("main");
    	box1.style.width = '0px';
    	box1.style.height = '0px';
    	var box = document.getElementById("main2");
    	box.style.width = '0px';
    	box.style.height = '0px';
		$.post("${pageContext.request.contextPath}/findPlayerReportForInfo",{'pid':pid},function(data) {
			var jsonObj = eval('('+data+')');
			var feature = jsonObj.reportFeatureList;
			var featureHtml = "";
			if(!(feature.length === 0)) {
				var strs= new Array(); 
				strs=feature.split("，");
				for ( var i=0;i<strs.length ;i++ ) {
					featureHtml += '<button class="btn btn-danger">'+strs[i]+'</button>&nbsp;&nbsp;&nbsp;'
				} 
				
			}
			var headHtml = "";
			headHtml+='<div class="timeline-item" style="width: 130%">'
					+'<div class="row">'
					+' <div class="col-xs-3 date">'
					+'<i class="fa fa-file-text"></i>'
					+' </div>'
					+'<div class="col-xs-7 content">'
					+'<a class="faq-question"><strong>';
					
			var lastHtml = "";
			lastHtml+=' </div>'
					+'</div>'
					+'</div>'
			var html="";
					html+='<div class="wrapper wrapper-content  animated fadeInRight blog" >'
						+'<div class="row">';
					html+='<div class="col-sm-12" style="margin-top: -60px">'
						+'<div class="ibox float-e-margins">'
						+'<div class="ibox-title">'
						+'<h5>球员技能描述</h5>'
						+' </div>'
						+'<div class="ibox-content timeline"> '
						+headHtml
						+'球员特性'+'</strong></a>'
						+featureHtml
						+lastHtml 
						+headHtml
						+'精神属性'+'</strong></a>'
						+jsonObj.reportMind
						+lastHtml
						+headHtml
						+'防守能力'+'</strong></a>'
						+jsonObj.reportDefensive
						+lastHtml
						+headHtml
						+'射门能力'+'</strong></a>'
						+jsonObj.reportShot
						+lastHtml
						+headHtml
						+'盘带能力'+'</strong></a>'
						+jsonObj.reportDrawstring
						+lastHtml
						+headHtml
						+'传球能力'+'</strong></a>'
						+jsonObj.reportPassBall
						+lastHtml
						+headHtml
						+'身体素质'+'</strong></a>'
						+jsonObj.reportSpeed
						+lastHtml
						+'</div></div></div></div></div>';
						
			
					$("#playerReport").html(html);

		
		});
		
		
	}

	
</script>

 <%-- <div class="timeline-item" style="width: 130%">
                            <div class="row">
                                <div class="col-xs-3 date">
                                    <i class="fa fa-file-text"></i>
                                </div>
                                <div class="col-xs-7 content">
                                
                                    <a class="faq-question"><strong>精神属性</strong></a>
                                    <p>${report.reportMind }</p>
                                    
                                </div>
                            </div>
                        </div> --%>

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
									<br>
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
                                    <br>
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
                                                <li><a href="javascript:void(0);" onclick="findPlayerDeeds()" id="tab1" data-toggle="tab">成长时间轴</a>
                                                </li>
                                                <li class=""><a href="javascript:void(0);" onclick="findPlayerDiary()" id="tab2" data-toggle="tab">成长足记</a>
                                                </li>
												<li><a href="javascript:void(0);" id="tab3" onclick="findPracticeScoreForInfo()" data-toggle="tab">训练情况</a>
                                                </li>
                                                <li class=""><a href="javascript:void(0);" onclick="findPlayerReport()" id="tab4" data-toggle="tab">球员报告</a>
                                                </li>
                                                <li class=""><a href="javascript:void(0);" onclick="findReportScore()" id="tab5" data-toggle="tab">技能评分</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                          <div class="panel-body">
                              <div class="tab-content">
                              
					                          <div class="tab-pane active" id="tab-1">        
					                            <div class="" id="ibox-content">
					                                <div id="vertical-timeline" class="vertical-container light-timeline">
					 								<div id="playerDeeds">
						 								<!-- 时间轴内容 -->
													</div>
					                                   
					                                </div>
					
					                            </div>
					                        </div>
                        
                                            
                                            
                                            <div class="tab-pane active" id="tab-2">
												 <div id="infoDiary">
												 	<!-- 球员日记内容 -->
												 </div>
                                            </div>
                                            
                                            <div class="tab-pane active" id="tab-3">
									             <div class="col-sm-12">
									            	<!-- <div class="ibox float-e-margins"> -->
									                    <!-- <div class="ibox-title">
									                        <h5>近10次训练评分走势图(——>)</h5>                       
									                    </div> -->
									                    <div>
									                       <!--  <div class="ibox-content profile-content"> -->
									                        		<div id="main" >
									                        		</div>
									                        		<script type="text/javascript">
									                        	    var jsonObj;
									                        	    var infoPlayer="<%=session.getAttribute("infoPlayer")%>"; 
									                        		var pid = "${infoPlayer.pid}";
									                        	    function findPracticeScoreForInfo() {	
									                        	    	var box1 = document.getElementById("main");
									                        	    	box1.style.width = '800px';
									                        	    	box1.style.height = '400px';
									                        	    	var box = document.getElementById("main2");
									                        	    	box.style.width = '0px';
									                        	    	box.style.height = '0px';
									                        		$('#main').removeAttr('_echarts_instance_');
									                        		 // 基于准备好的dom，初始化echarts实例
									                        	    var myChart = echarts.init(document.getElementById('main'));
									                        	    // 指定图表的配置项和数据
									                        	     var option = {
									                        	    		 title : {
									                        	    	            show:true,//显示策略，默认值true,可选为：true（显示） | false（隐藏）
									                        	    	            text: '近十次训练评分情况——>',
									                        	    		 },
									
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
									                        	    	$("#playerDeeds").html("");
									                        			$("#infoDiary").html("");
									                        			$("#main2").html("");
									                        			$("#playerReport").html("");
									                        	    	$.post("${pageContext.request.contextPath}/getPracticeForInfo",{'pid':pid},function(data) {
									                        				 jsonObj = eval(data);
									                        	        myChart.setOption({
									                        	        	
									                        	        	title : {
								                        	    	            show:true,//显示策略，默认值true,可选为：true（显示） | false（隐藏）
								                        	    	            text: '球员近十次训练评分走势——>',
								                        	    		 },
									                        	        	
									                        	        		xAxis: {
									                        			            type: 'category',
									                        			            boundaryGap: false,
									                        				        data: ['1', '2', '3', '4', '5', '6', '7', '8' ,'9', '10']
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
									                       <!--  </div> -->
									                    </div>
									                <!-- </div> -->
									            
									            	
									                
									            </div>
                                            </div>
                                            
                                            <div class="tab-pane active" id="tab-4">
												 <div id="playerReport">
												 	
												 </div>
                                            </div>
                                            
                                            <div class="tab-pane active" id="tab-5">
				<div class="col-sm-12" style="margin-top: -30px">
               
						<!--  <div class="ibox-title">
	                        <h5>技能评分情况</h5>
	                    </div>
	                     -->
	                    <div class="ibox-content timeline">
 	                    	<div id="main2" ></div>
 							<script type="text/javascript">
 							function findReportScore() {
                    	    	var box1 = document.getElementById("main");
                    	    	box1.style.width = '0px';
                    	    	box1.style.height = '0px';
 								var box = document.getElementById("main2");
                    	    	box.style.width = '800px';
                    	    	box.style.height = '400px';
                    		    $('#main2').removeAttr('_echarts_instance_');
	                      		var myChart = echarts.init(document.getElementById('main2'));
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
	                      	
	                      			var infoPlayer="<%=request.getAttribute("infoPlayer")%>";
	                      			var pid = "${infoPlayer.pid}";
	                      			$("#playerDeeds").html("");
	                      			$("#main").html("");
	                      			$("#infoDiary").html("");
	                      			$("#playerReport").html("");
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
		
		<div class="col-sm-1">
		</div>
		
    </div>
    
    
    
    
    
    
     <!-- Modal -->
	<div class="modal fade bs-example-modal-sm " id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document" style="z-index: 2041;">
    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">申请俱乐部</h4>
				</div>
				<div class="modal-body">
					
					 <form action="${pageContext.request.contextPath }/fuzzyFindClub" method="post">
				        <div class="form-group" style="width: 250px">
				          <input type="text" name="clubFuzzyName" id="clubFuzzyName" class="form-control" placeholder="请输入俱乐部名称..." >
				        </div>
				        <button type="submit" class="btn btn-info">查询</button>
				      </form>
				
 				</div>
				<div class="modal-footer">
				<!-- <button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
 				    <button class="btn btn-success" onclick="receiveCoach()" id="receiveCoach" type="button">确定接收</button> 
					<nav id="page"> </nav> -->
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
					 <h3 class="modal-title" id="myModalLabel">请您确认是否拒绝接收该教练？</h3>
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 				
 --%>				
 				</div>
				<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
 				    <button class="btn btn-danger" onclick="refuseCoach()" id="refuseCoach" type="button">确定拒绝</button> 
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
