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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">

<script type="text/javascript">
	window.onload = function(){
		/* 修改操作回显信息 */
	    var updateClub="<%=request.getAttribute("updateClub")%>"; 
	    if(updateClub !="") {
	    	//alert("${updateClub.clubName}")
	        document.getElementById("clubName").value="${updateClub.clubName}";
			document.getElementById("clubPassword").value="${updateClub.clubPassword}";
			document.getElementById("clubPasswordAgain").value="${updateClub.clubPassword}"; 	
			document.getElementById("clubPhone").value="${updateClub.clubPhone}";
			document.getElementById("clubEmail").value="${updateClub.clubEmail}";
			document.getElementById("clubCity").value="${updateClub.clubCity}"; 	
			document.getElementById("clubIntroduction").value="${updateClub.clubIntroduction}"; 
			document.getElementById("clubPhoto").value="${updateClub.clubPhoto}"; 
	    }
		};
</script>

</head>

<body class="gray-bg" style="height: 700px" >
    <div class="wrapper wrapper-content  animated fadeInRight" >
        <div class="row">   
        	<div class="col-sm-2">
        	</div>     
            <div class="col-sm-8">
                <div class="ibox" >
                    <div class="ibox-content" >
                        <h3>信息管理</h3>
                       
						<p class="small"><i class="fa fa-hand-o-up"></i> 请管理您的用户信息</p>
						<br>
                        <div class="input-group">
                           <!-- <input type="text" placeholder="添加新任务" class="input input-sm form-control">
                            <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-white"> <i class="fa fa-plus"></i> 添加新任务</button>
                                </span>-->
                        </div>
                        
                       <!-- 表单的form中添加：enctype = "multipart/form-data" -->
                    <form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/updateClubInformation" enctype = "multipart/form-data" method="post">
						<!--<div class="modal-body">-->
							<div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">俱乐部全称</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="clubName" name="clubName"  placeholder="请输入俱乐部全称">
						    	</div>
						    </div>
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">俱乐部密码</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="clubPassword" name="clubPassword"  placeholder="请输入俱乐部密码">
						    	</div>
						    </div>
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">再次确认俱乐部密码</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="clubPasswordAgain" name="clubPasswordAgain"  placeholder="请再次输入俱乐部密码">
						    	 </div>
						    </div>
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">俱乐部电话</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="clubPhone" name="clubPhone"  placeholder="请输入俱乐部电话">
						    </div>
						     </div>
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">俱乐部邮箱</label>
							    <div class="col-sm-6">
							      <input type="email" class="form-control" id="clubEmail" name="clubEmail"  placeholder="请输入俱乐部邮箱">
						    </div>
						     </div>
						     
						      <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">俱乐部位置</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="clubCity" name="clubCity"  placeholder="请输入俱乐部位置">
						    </div>
						     </div>
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">俱乐部简介</label>
							    <div class="col-sm-6">
									<textarea class="form-control" rows="4"  id="clubIntroduction" name="clubIntroduction">
							</textarea>
						    </div>
						     </div>
						    
						     <div class="form-group">
							    <label for="username" class="col-sm-2 control-label">俱乐部头像</label>
							    <div class="col-sm-6">
									<input type="file" name="clubPhoto"/>
								</div>
							 </div>
						
										  
		 				<br/>
						<div >
							<button  type="submit"  class="btn btn-info">确认修改</button>
	
		 				</div>
					</form>
                        
                    </div>
                </div>
            </div>
         
			<div class="col-sm-2">
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
