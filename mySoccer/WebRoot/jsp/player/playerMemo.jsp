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


    <title>我的计划</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">

<script type="text/javascript">
	var i = "0";
	function donePlayerMemoBt(playerMemoId) {
		i = playerMemoId;
	}
	function donePlayerMemo() {
		location.href = "${pageContext.request.contextPath}/setPlayerMemoFlag?playerMemoId="+i;
	}
	
	var j = "0";
	function deletePlayerMemoBt(playerMemoId) {
		j = playerMemoId;
	}
	function deletePlayerMemo() {
		location.href = "${pageContext.request.contextPath}/deletePlayerMemoById?playerMemoId="+j;
	}
</script>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-4">
                <div class="ibox">
                    <div class="ibox-content">
                        <h3>添加计划</h3>
                       
						<p class="small"><i class="fa fa-hand-o-up"></i> 请开始创建您的新计划</p>
                        <div class="input-group">
                           <!-- <input type="text" placeholder="添加新任务" class="input input-sm form-control">
                            <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-white"> <i class="fa fa-plus"></i> 添加新任务</button>
                                </span>-->
                        </div>
                    <form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/writeNewPlayerMemo" method="post">
						<!--<div class="modal-body">-->
							<textarea class="form-control" rows="6"  id="playerMemoContent" name="playerMemoContent">
							</textarea>
		 				<br/>
						<div >
							<button  type="submit"  class="btn btn-success">添加</button>
	
		 				</div>
					</form>
                        
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox">
                    <div class="ibox-content">
                        <h3>最新计划</h3>
                        <p class="small"><i class="fa fa-hand-o-up"></i> 请抓紧完成计划哦</p>
                        <ul class="sortable-list connectList agile-list">
                            <c:forEach var="u" items="${undonePlayerMemoList}">
	                            <li class="success-element">
	                                	${u.playerMemoContent}
	                                <div class="agile-detail">
	                                    <i class="fa fa-clock-o"></i>${ fn:substring(u.playerMemoTime,0,19) }
	                                    <div class="ibox-tools">
	                                    
	                                     <a href="javascript:void(0)" onclick="donePlayerMemoBt('${u.playerMemoId}')"  class="btn btn-success btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
						data-target="#myModal1">完成</a>
										<a href="javascript:void(0)" onclick="deletePlayerMemoBt('${u.playerMemoId}')"  class="btn btn-danger btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
						data-target="#myModal2">删除</a>
	                                    	<!-- <a href="">
	                                    		<button type="button" class="btn btn-primary btn-xs">完成</button>
	                                   	    </a>
	                                   	    <a href="">
	                                   	    	<button type="button" class="btn btn-danger btn-xs">删除</button>	                                    
	                                		</a> -->
	                                	</div>
	                                </div>
	                            </li>  
	                        </c:forEach>
                           
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="ibox">
                    <div class="ibox-content">
                        <h3>已完成</h3>
                        <p class="small"><i class="fa fa-hand-o-up"></i> 恭喜您完成您的预定计划</p>
                        <ul class="sortable-list connectList agile-list">
                           <c:forEach var="d" items="${donePlayerMemoList}">
	                            <li class="success-element">
	                                	${d.playerMemoContent}
	                                <div class="agile-detail">
	                                    <!-- <a href="#" class="pull-right btn btn-xs btn-white">标签</a> -->
	                                    <i class="fa fa-clock-o"></i>${ fn:substring(d.playerMemoTime,0,19) }
	                                     <div class="ibox-tools">
	                                     	<a href="javascript:void(0)" onclick="deletePlayerMemoBt('${d.playerMemoId}')"  class="btn btn-danger btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
						data-target="#myModal2">删除</a>
	                               		</div>
	                                </div>
	                            </li>  
	                        </c:forEach>
                           
                        </ul>
                    </div>
                </div>
            </div>

        </div>


    </div>
    
    
    
         <!-- Modal -->
	<div class="modal fade bs-example-modal-sm" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
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
					 <h3 class="modal-title" id="myModalLabel">请您确认是否完成本次任务？</h3> 
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 				
 --%>				
 				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					<button type="button" class="btn btn-success" onclick="donePlayerMemo()" data-dismiss="modal">完成</button>
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
					 <h3 class="modal-title" id="myModalLabel">请您确认是否删除本次任务？</h3> 
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 				
 --%>				
 				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					<button type="button" class="btn btn-danger" onclick="deletePlayerMemo()" data-dismiss="modal">删除</button>
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
