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
		<%-- var infoPlayer="<%=session.getAttribute("infoPlayer")%>"; 
		var pid = "${infoPlayer.pid}";
		var pRealName = "${infoPlayer.pRealName}"
	function findPlayerDeeds() {
		$.post("${pageContext.request.contextPath}/findPlayerDeedsForInfo",{'pid':pid},function(data) {
			var infoPlayerDeeds = eval(data);
			var html=""; 
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
			$("#playerDeeds").html(html);
			
		});
	}--%>
	var coachId = "${infoCoach.coachId}";
	function findCoachShare() {		
		$.post("${pageContext.request.contextPath}/findShareForInfo",{'coachId':coachId},function(data) {
			var infoShare = eval(data);
			var html ="";
			html+='<div class="wrapper wrapper-content  animated fadeInRight blog" style="margin-top: 0px">'
				+'<div class="row">';
			for(var i=0;i<infoShare.length;++i) {
				 html+='<div class="ibox">'
					+'<div class="ibox-content">'
					+'<a href="${pageContext.request.contextPath }/showShareArtical?shareId='+infoShare[i].shareId+'" class="btn-link">'
					+'<h2>'+infoShare[i].shareTitle+'</h2>'
					+'</a>'
					+'<div class="small m-b-xs">'
					//+'<span class="text-muted"> by </span> <strong>'+pRealName+'</strong>&nbsp;&nbsp;&nbsp;&nbsp;'
					+'<span class="text-muted"><i class="fa fa-clock-o"></i>'
					+infoShare[i].shareDate
					+'</span>'
					+'</div>'
					+'<p>'+infoShare[i].shareHeadContent+'......</p>'
					+'<div class="row">'
					+'<div class="col-md-6">'
					+'<button class="btn btn-info btn-xs" type="button">教练分享</button>&nbsp;&nbsp;&nbsp;&nbsp;'
					//+'<button class="btn btn-info btn-xs" type="button">全部可见</button>'
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
			$("#coachShare").html(html);
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
											<img alt="image" style="width: 90px;height: 90px; " class="img-circle m-t-xs img-responsive" src="${infoCoach.coachPhoto}">
										</div>
										<br>
										<div class="m-t-xs font-bold">
											<h2 style="color: #20B2AA"><strong>${infoCoach.coachName}</strong></h2>
										</div>
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
                                    <dd>${infoCoach.coachRealName}</dd>
									<br>
									<dt>籍贯：</dt>
                                    <dd>${infoCoach.coachAddress}</dd>
									<br>
                                    <dt>联系电话：</dt>
                                    <dd>${infoCoach.coachTelPhone}</dd>
									<br>
									<dt>俱乐部：</dt>
										<dd>
										
		                                    	<c:if test="${ not empty infoCoach.coachClub }">
				                                    	<a href="${pageContext.request.contextPath }/getClubInformation?cid=${infoCoach.coachClub.cid}">
				                                    	${infoCoach.coachClub.clubName}
				                                    	</a>
		                                    	</c:if>
	                                    	
		                                    	<c:if test="${ (empty infoCoach.coachClub)&&(infoCoach.coachId==existCoach.coachId) }">
			                                    		<c:if test="${empty infoCoach.coachApplyId}">
				                                    	<a href="javascript:void(0)" onclick="applyClubByCoachBt()"  class="btn btn-success btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
							data-target="#myModal">申请</a> 
			                                    		</c:if>
							
														<c:if test="${not empty infoCoach.coachApplyId}">
														<a href="${pageContext.request.contextPath }/findCoachApply"  class="btn btn-danger btn-xs" role="button" class="btn btn-danger btn-xs" >查看申请</a> 
			                                    		</c:if>
		                                    	</c:if>
                                    	</dd>	 
									 
                                </dl>
                            </div>
                            <div class="col-sm-7" id="cluster_info">
                                <dl class="dl-horizontal">
								    <br>
                                    <dt>性别：</dt>
                                    <dd>${infoCoach.coachGender}</dd>
									<br>
                                    <dt>生日：</dt>
                                    <dd>${infoCoach.coachBirthday}</dd>
									<br>
									<dt>邮箱：</dt>
                                    <dd>${infoCoach.coachNumber}</dd>
									<br>
									<dt>个人简介：</dt>
                                    <dd>${infoCoach.coachIntroduction}</dd>
									<br>
									
                                   
                                </dl>
                            </div>
                        </div>
                        <br>
                         <div class="row">
                         	<div class="col-sm-12">
                                <dl class="dl-horizontal">
														
                                    <dt>学员：</dt>
                                    	<dd>
                                    		<div class="col-sm-12">
		                                    	 <c:forEach var="p" items="${ infoCoach.coachPlayer }">
			                                    	 <c:if test="${not empty pageFlag }">
			                                    	 	<a href="${pageContext.request.contextPath}/getplayerInformation?pageFlag=1&pid=${p.pid}">
			                                    	 </c:if>
			                                    	 <c:if test="${empty pageFlag }">
			                                    	 	<a href="${pageContext.request.contextPath}/getplayerInformation?pid=${p.pid}">
			                                    	 </c:if>
		                                    	 
		                                    	 	<div class="col-sm-4">
		                                   		            		
		                                   				 <div class="col-sm-12">
											                <div class="contact-box" style="height: 60px;width: 210px">
											                    
											                        <div class="col-sm-4">
											                             <div class="text-center" style="margin-top: -20px;margin-left: -20px"> 
											                                <img alt="image" style="width: 52px;height: 52px; " class="img-circle m-t-xs img-responsive" src="${p.pPhoto}">
											                            </div> 
										
											                        </div>
											                        <div class="col-sm-8">
											                        	<div class="text-center">
											                            	<h3><strong>${p.pRealName }</strong></h3>
											                            	<%-- <p><i class="fa fa-map-marker"></i> ${p.coachAddress }</p> --%>
											                            </div>
											                        </div>
											                        <div class="clearfix"></div>
												                </div>
												            </div>
			                                   			</div>
											          </a>
		
		                                   			
		                                   			
		                                   			
		                                   			
		                                   		</c:forEach>
                                   			</div>
                                   		</dd>
									<br>
                                </dl>
                            </div>
                         </div>
                      
                        <div class="row m-t-sm">
                            <div class="col-sm-12">
                                <div class="panel blank-panel">
                                    <div class="panel-heading">
                                        <div class="panel-options">
                                            <ul class="nav nav-tabs" id="myTab">
                                                <li><a href="javascript:void(0);" onclick="findCoachShare()" id="tab1" data-toggle="tab">教练分享</a>
                                                </li>
                                                <!-- <li class=""><a href="javascript:void(0);" onclick="findPlayerDiary()" id="tab2" data-toggle="tab">成长足记</a>
                                                </li>
												<li><a href="pjavascript:void(0);" id="tab3" onclick="" data-toggle="tab">训练情况</a>
                                                </li>
                                                <li class=""><a href="javascript:void(0);" onclick="" id="tab4" data-toggle="tab">球探报告</a>
                                                </li> -->
                                            </ul>
                                        </div>
                                    </div>

                          <div class="panel-body">
                              <div class="tab-content">
                              
					                          <div class="tab-pane active" id="tab-1">        
					                            <!-- <div class="" id="ibox-content">
					                                <div id="vertical-timeline" class="vertical-container light-timeline"> -->
					 								<div id="coachShare">
						 								<!-- 教练分享 -->
													</div>
					                                   
					                                </div>
					
					                          <!--   </div>
					                        </div>
                         -->
                                            
                                            
                                            <div class="tab-pane active" id="tab-2">
												 <div id="infoDiary">
												 	<!-- 球员日记内容 -->
												 </div>
                                            </div>
                                            
                                            <div class="tab-pane" id="tab-3">
												 
                                            </div>
                                            
                                            <div class="tab-pane" id="tab-4">
												 
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
