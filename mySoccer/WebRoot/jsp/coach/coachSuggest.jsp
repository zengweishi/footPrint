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
	var k = "0";
	function lookSugForCoachBt(sugCoachId) {
		 k = sugCoachId;
		 $.post("${pageContext.request.contextPath}/showSuggestForCoachContent",{'sugCoachId':sugCoachId},function(data) {
			 //alert(data)
			var jsonObj = eval(data);
			$('#msg').html(jsonObj);
		}); 
	} 
	
	function setSugCoachFlag() {
		location.href = "${pageContext.request.contextPath}/setSugForCoachFlag?sugCoachId="+k;
	}


	function deleteSugCoach() {
		location.href = "${pageContext.request.contextPath}/deleteSuggestForCoach?sugCoachId="+k;
	}
	
	
</script>

</head>

<body class="gray-bg">
    <div class="row">
        <div class="col-sm-12">
            <div class="wrapper wrapper-content animated fadeInUp">
                <ul class="notes">
				
				<c:forEach var="u" items="${unMarkList}">
                    <li>
                        <div>
                            <strong>${ fn:substring(u.sugCoachTime,0,10) }</strong>
                            <br/>
                            <strong>${ fn:substring(u.sugCoachTime,11,19) }</strong>
                            <br/>
                            <p>${ fn:substring(u.sugCoachContent,0,50) }</p>
                            <a href="javascript:void(0)" onclick="lookSugForCoachBt('${u.sugCoachId}')"  class="btn btn-default btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
							data-target="#myModal1">查看</a>
                            <%-- <div class="ibox-tools">
	                            <!-- <a href="pin_board.html#"><i class="fa fa-trash-o "></i></a> -->
	                            <a href="javascript:void(0)" onclick="lookSugForCoachBt('${u.sugCoachId}')"  class="btn btn-default btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
							data-target="#myModal1">查看</a>
							</div> --%>
                        </div>
                    </li>
                  </c:forEach>
                  
                </ul>
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
					  <h4 class="modal-title" id="myModalLabel">详情</h4> 
				</div>
				<div class="modal-body">
					<p id="msg"></p>
					 <!-- <table class="table table-bordered .table-hover">
						<tbody id="msg">

						</tbody> 
					</table> -->
					 <!-- <h3 class="modal-title" id="myModalLabel">请您确认是否完成本次任务？</h3>  -->
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 						
 --%>				
 				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					<button type="button" class="btn btn-danger" onclick="deleteSugCoach()" data-dismiss="modal">删除</button>
					<button type="button" class="btn btn-info" onclick="setSugCoachFlag()" data-dismiss="modal">标记</button>
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
