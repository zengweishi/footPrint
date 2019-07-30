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
    <script src="${ pageContext.request.contextPath }/js/echarts.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">

<%--  $("#deedsTitleTime").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
				$("#deedsTitle").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
				$("#deedsContent").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
				$("#deedsDate").html("${ fn:substring("+infoPlayerDeeds[i]+".playerDeedsTitleTime,0,10) }");
  --%>

<script type="text/javascript">
	var i = "0";
	function deleteAdPhotoBt(adId) {
		i = adId;
	}
	function deleteAdPhoto() {
		location.href = "${pageContext.request.contextPath}/deleteAdPhotoById?adId="+i;
	}
	
	function test1() {
		//alert(111);添加轮播图
		document.getElementById('addAdPhotoForm').action = "${pageContext.request.contextPath}/createNewAdPhoto";  
        document.getElementById("addAdPhotoForm").submit();  
	}
	
	var j ="0"
	function updateAdPhotoBt(adId) {
		j=adId;
		$.post("${pageContext.request.contextPath}/jumpToUpdateAdPhoto",{'adId':adId},function(data){
			var jsonObj = eval(data);
			document.getElementById("updateAdLink").value=jsonObj;

		});
		
	}
	
	
	function test2() {
		 //var adId="<%=request.getAttribute("updateAdId")%>"; 
			document.getElementById('updateAdPhotoForm').action = "${pageContext.request.contextPath}/updateAdPhoto?adId="+j;  
	        document.getElementById("updateAdPhotoForm").submit();  
	}
	
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
                           
                           
                           
                           
          	<table class="table table-bordered" cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#FFFFFF">
							<strong>轮播图列表</strong>
						</TD>
					</tr>
					<tr>
						
						<td class="ta_01" align="right">
							<a href="javascript:void(0)" onclick="addAdPhotoBt()"  class="btn btn-info btn-sm" role="button" class="btn btn-info btn-xs" data-toggle="modal"
						data-target="#myModal2">添加</a>
						</td>
					</tr>
					<tr>
						<td class="ta_01" align="center" bgColor="#FFFFFF">
							<table class="table table-hover">
								<tr
									style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #FFFFFF">

									<td align="center" >
										序号
									</td>
									<td align="center" >
										图片
									</td>
									<td align="center" >
										链接
									</td>
									<td align="center">
										修改
									</td>
									<td align="center">
										删除
									</td>
								</tr>
								<c:forEach var="p" items="${ adPhotoList }" varStatus="status">
										<tr style="height: 50px" onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#FFFFFF';">
											<td style="CURSOR: hand;"  align="center"
												>
												${ status.count }
											</td>
											<td style="CURSOR: hand;"  align="center">
												<%-- <img alt="image" style="width: 40px;height: 40px; " class="img-circle m-t-xs img-responsive"  src="${p.adPicture}"> --%>
												<img alt="image" style="width: 40px;height: 40px; " src="${p.adPicture}">
											</td>
											<td style="CURSOR: hand;"  align="center"
												>
												<a href="${p.adBackLink }">
													${ p.adBackLink }
												</a>
											</td>
											
											<td align="center" >
												
	 												<a href="javascript:void(0)" onclick="updateAdPhotoBt('${p.adId}')"  class="btn btn-success btn-sm" role="button" class="btn btn-success btn-xs" data-toggle="modal"
						data-target="#myModal3">修改</a> 
												
											</td>
											
											<td align="center" >
												
	 												<a href="javascript:void(0)" onclick="deleteAdPhotoBt('${p.adId}')"  class="btn btn-danger btn-sm" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
						data-target="#myModal">删除</a> 
												
												
											</td>
									
											
										</tr>
										</c:forEach>
							</table>
						</td>
					</tr>
					<%-- <tr align="center">
						<td colspan="7">
							第${ pageBean.currPage }/${ pageBean.totalPage }页 &nbsp; &nbsp; &nbsp;
							总记录数:${ pageBean.totalCount }  &nbsp; 每页显示:${ pageBean.pageSize }
							<c:if test="${ pageBean.currPage != 1 }">
								<a href="${ pageContext.request.contextPath }/AdminProductServlet?method=findByPage&currPage=1">首页</a>|
								<a href="${ pageContext.request.contextPath }/AdminProductServlet?method=findByPage&currPage=${ pageBean.currPage - 1}">上一页</a>|
							</c:if>
							&nbsp; &nbsp;
							
							<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
								<c:if test="${ pageBean.currPage == i }">
									[${ i }]
								</c:if>
								<c:if test="${ pageBean.currPage != i }">
									<a href="${ pageContext.request.contextPath }/AdminProductServlet?method=findByPage&currPage=${ i}">[${ i }]</a>
								</c:if>
							</c:forEach>
							
							&nbsp; &nbsp;
							<c:if test="${ pageBean.currPage != pageBean.totalPage }">
								<a href="${ pageContext.request.contextPath }/AdminProductServlet?method=findByPage&currPage=${ pageBean.currPage + 1}">下一页</a>|
								<a href="${ pageContext.request.contextPath }/AdminProductServlet?method=findByPage&currPage=${ pageBean.totalPage}">尾页</a>|
							</c:if>	
						</td>
					</tr> --%>
					<tr align="center">
						<td colspan="7">
					<div style="width:380px;margin:0 auto;margin-top:10px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
		
						
							</ul>
						</div>  
						</td>
					</tr>
				</TBODY>
			</table>
               
               
                        
                      
                   
                    		</div>
                		</div>
           			</div>
        		</div>
			</div>
			
		<div class="col-sm-1">
		</div>
		
    </div>
    

	
	
	<!-- Modal -->
	<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
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
					 <h3 class="modal-title" id="myModalLabel">请您确认是否删除该轮播图？</h3>
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 				
 --%>				
 				</div>
				<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
 				    <button class="btn btn-danger" onclick="deleteAdPhoto()" id="deleteAdPhoto" type="button">确定删除</button> 
					<nav id="page"> </nav>
				</div>
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
					  <h4 class="modal-title" id="myModalLabel">添加新的轮播图</h4> 
				</div>
	
    		<form id="addAdPhotoForm" name="addAdPhotoForm" action="" method="post" enctype = "multipart/form-data">
					<div class="modal-body">
						<!-- <textarea class="form-control" rows="9" id="planCoachSum" name="planCoachSum">
						</textarea> -->
						<table class="table table-bordered .table-hover">
							<tr>
								<td>图片</td>
								<td>
									<input type="file" name="adPicture"/>
								</td>
							</tr>
			
							<tr>
								<td>链接</td>
								<td><div class="input-group">
									  <input type="text" style="width:480px" class="form-control" id="adLink" name="adLink" placeholder="请输入链接" aria-describedby="sizing-addon2">
									</div>
								</td>
							</tr>
						</tbody> 
					</table>
	 				</div>
	 				
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
	 				    <button class="btn btn-success" onclick="test1()" id="addAdPhoto" type="button">完成</button> 
						<nav id="page"> </nav>
					</div>
			</form>
				
				
			</div>
		<!-- </div> -->
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
					  <h4 class="modal-title" id="myModalLabel">更新轮播图</h4> 
				</div>
	
    		<form id="updateAdPhotoForm" name="updateAdPhotoForm" action="" method="post" enctype = "multipart/form-data">
					<div class="modal-body">
						<!-- <textarea class="form-control" rows="9" id="planCoachSum" name="planCoachSum">
						</textarea> -->
						<table class="table table-bordered .table-hover">
							<tr>
								<td>图片</td>
								<td>
									<input type="file" name="adPicture"/>
								</td>
							</tr>
			
							<tr>
								<td>链接</td>
								<td><div class="input-group">
									  <input type="text"  style="width:480px"  class="form-control" id="updateAdLink" name="adLink" placeholder="" aria-describedby="sizing-addon2">
									</div>
								</td>
							</tr>
						</tbody> 
					</table>
	 				</div>
	 				
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
	 				    <button class="btn btn-success" onclick="test2()" id="updateAdPhoto" type="button">完成</button> 
						<nav id="page"> </nav>
					</div>
			</form>
				
				
			</div>
		<!-- </div> -->
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
