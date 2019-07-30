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
	function deletePlayerDeedBt(playerDeedsId) {
		i = playerDeedsId;
	}
	function deletePlayerDeed() {
		location.href = "${pageContext.request.contextPath}/deletePlayerDeedsById?playerDeedsId="+i;
	}
	
	function createNewPlayerDeed() {
		document.getElementById('createNewPlayerDeedForm').action = "${pageContext.request.contextPath}/createPlayerDeeds";  
        document.getElementById("createNewPlayerDeedForm").submit();  
	}
	
</script>

</head>

<body class="gray-bg">
    <div class="row">
        <div class="col-sm-12">
            <div class="wrapper wrapper-content">
                <div class="row animated fadeInRight">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            <div class="text-center float-e-margins p-md">
                                <span>布局切换：</span>
                                <a href="${pageContext.request.contextPath }/findAllPlayerDeeds#" class="btn btn-xs btn-default" id="lightVersion">浅色</a>
                                <a href="${pageContext.request.contextPath }/findAllPlayerDeeds#" class="btn btn-xs btn-default" id="darkVersion">深色</a>
                               	<a href="javascript:void(0)" onclick="createNewPlayerDeedBt()"  class="btn btn-success btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
					data-target="#myModal">添加新事迹</a> 
												
                            </div>
                            <div class="" id="ibox-content">

                                <div id="vertical-timeline" class="vertical-container light-timeline">
								
                                   
                                   
 								<c:forEach var="p" items="${ playerDeedsList }">
                                    <div class="vertical-timeline-block">
										 <div class="vertical-timeline-content">
                                            <h1>${ fn:substring(p.playerDeedsTitleTime,0,10) }</h1>
                                        </div>
										
                                        <div class="vertical-timeline-icon blue-bg">
                                            <i class="fa fa-file-text"></i>
                                        </div>

                                        <div class="vertical-timeline-content">
                                            <h2>${p.playerDeedsTitle}</h2>
                                            <p>${p.playerDeedsContent}
											</p>
                                           <a href="javascript:void(0)" onclick="deletePlayerDeedBt('${p.playerDeedsId}')"  class="btn btn-default btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
					data-target="#myModal2">删除</a> 
                                            <span class="vertical-date">
                                      			 <br/>
                                        <small>${ fn:substring(p.playerDeedsDate,0,19) }</small>
                                    </span>
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
    </div>
   
   
   

	<!-- Modal -->
	<div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog " role="document" style="z-index: 2041;">
    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					  <h4 class="modal-title" id="myModalLabel">添加新事迹</h4> 
				</div>
				
				
    		<form id="createNewPlayerDeedForm" name="createNewPlayerDeedForm" action="" method="post">
					<div class="modal-body">
						<!-- <textarea class="form-control" rows="9" id="planCoachSum" name="planCoachSum">
						</textarea> -->
						<table class="table table-bordered .table-hover">
							<tr>
								<td>事迹时间</td>
									<td>
										<div class="col-sm-10" >
			                                <input class="form-control layer-date" id="playerDeedsTime" name="playerDeedsTime" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
			                                <label class="laydate-icon"></label>
			                            </div>
		                            </td>
							</tr>
							<tr>
								<td>事迹标题</td>
								<td>
									<div class="input-group" style="margin-left: 14px">
									  	<input type="text" class="form-control" placeholder="标题" id="playerDeedsTitle" name="playerDeedsTitle" aria-describedby="sizing-addon2">
									</div>
								</td>
							</tr>
							<tr>
								<td>事迹内容</td>
								<td>
										<textarea class="form-control" rows="3" id="playerDeedsContent" name="playerDeedsContent">
								</textarea>
								</td>
							</tr>
						</tbody> 
					</table>
	 				</div>
	 				
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
	 				    <button class="btn btn-success" onclick="createNewPlayerDeed()" id="createNewPlayerDeedBt" type="button">完成</button> 
						<nav id="page"> </nav>
					</div>
			</form>
				
				
			</div>
		<!-- </div> -->
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
					 <h3 class="modal-title" id="myModalLabel">请您确认是否删除本次事迹内容？</h3> 
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 				
 --%>				
 				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					<button type="button" class="btn btn-danger" onclick="deletePlayerDeed()" data-dismiss="modal">删除</button>
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
	
	<!-- 引入laydate的JS -->
	<script src="${pageContext.request.contextPath }/js/plugins/layer/laydate/laydate.js"></script>
    <script>
        //外部js调用
        laydate({
            elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });

        //日期范围限制
        var start = {
            elem: '#start',
            format: 'YYYY/MM/DD hh:mm:ss',
            min: laydate.now(), //设定最小日期为当前日期
            max: '2099-06-16 23:59:59', //最大日期
            istime: true,
            istoday: false,
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };
        var end = {
            elem: '#end',
            format: 'YYYY/MM/DD hh:mm:ss',
            min: laydate.now(),
            max: '2099-06-16 23:59:59',
            istime: true,
            istoday: false,
            choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };
        laydate(start);
        laydate(end);
    </script>


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
    
    

</body>

</html>
