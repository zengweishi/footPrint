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
	function deleteAdminBt(adminId) {
		i = adminId
	}
	function deleteAdmin() {
		location.href = "${pageContext.request.contextPath}/deleteAdmin?adminId="+i;
	}
	
	function updateAdmin(adminId) {
		location.href = "${pageContext.request.contextPath}/jumpToUpdateAdmin?adminId="+adminId;
	}
</script>

</head>

<body class="gray-bg">
    <div class="row">
    
		<!-- <div class="col-sm-1">
		</div> -->
		
        <div class="col-sm-12">
            <div class="wrapper wrapper-content animated fadeInUp">
                <div class="ibox">
                    <div class="ibox-content">
                        <div class="row">
                           
                           
                           
                           
          	<table class="table table-bordered" cellSpacing="1" cellPadding="0" width="100%" align="center" bgColor="#f5fafe" border="0">
				<TBODY>
					<tr>
						<td class="ta_01" align="center" bgColor="#FFFFFF">
							<strong>管理员列表</strong>
						</TD>
					</tr>
					<tr>
						
						<td class="ta_01" align="right">
				
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
										账号
									</td>
										
									<td align="center" >
										密码
									</td>
									<td align="center" >
										用户管理权限
									</td>
									<td align="center" >
										俱乐部权限
									</td>
									<!-- <td align="center" >
										管理员管理权限
									</td> -->
									<td align="center" >
										用户文章权限
									</td>
									<td align="center" >
										官方新闻权限
									</td>
									<td align="center" >
										轮播图权限
									</td>							
										
									<td align="center" >
										管理者
									</td>
									<td align="center">
										管理者电话
									</td>
									<td align="center">
										修改
									</td>
									<td align="center">
										删除
									</td>
									
								</tr>
								<c:forEach var="p" items="${ adminList }" varStatus="status">
										<tr style="height: 50px" onmouseover="this.style.backgroundColor = 'white'"
											onmouseout="this.style.backgroundColor = '#FFFFFF';">
											<td style="CURSOR: hand;"  align="center"
												>
												${ status.count }
											</td>
											
											<td style="CURSOR: hand;"  align="center"
												>
												${ p.adminName }
											</td>
											<td style="CURSOR: hand;"  align="center"
												>
												${ p.adminPassword }
											</td>
											
											<td style="CURSOR: hand;"  align="center"
												>
												<span style="color: red"><strong>${ p.userFlag }</strong></span>
											</td>
											<td style="CURSOR: hand;"  align="center"
												>
												<span style="color: red"><strong>${ p.clubFlag }</strong></span>
											</td>
											
											<%-- <td style="CURSOR: hand;"  align="center"
												>
												${ p.adminerFlag }
											</td> --%>
											<td style="CURSOR: hand;"  align="center"
												>
												<span style="color: red"><strong>${ p.articleFlag }</strong></span>
											</td>
											<td style="CURSOR: hand;"  align="center"
												>
												<span style="color: red"><strong>${ p.newsFlag }</strong></span>
											</td>
											
											<td style="CURSOR: hand;"  align="center"
												>
												<span style="color: red"><strong>${ p.photoFlag }</strong></span>
											<td style="CURSOR: hand;"  align="center"
												>
												${ p.adminRealName }
											</td>
											</td>
											<td style="CURSOR: hand;"  align="center"
												>
												${ p.adminTelePhone }
											</td>
											<td align="center" style="width: 45px"  >	
 													<a href="javascript:void(0)" onclick="updateAdmin('${p.adminId}')"  class="btn btn-info btn-xs" role="button">修改</a> 	
											</td>
											<td align="center" style="width: 45px" >
												<c:if test="${existAdmin.adminId ne p.adminId }">
 													<a href="javascript:void(0)" onclick="deleteAdminBt('${p.adminId}')"  class="btn btn-danger btn-xs" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
					data-target="#myModal">删除</a> 
												</c:if>
												
												<c:if test="${existAdmin.adminId eq p.adminId }">
													本账号
												</c:if>
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
			
		<!-- <div class="col-sm-1">
		</div> -->
		
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
					 <h3 class="modal-title" id="myModalLabel">请您确认是否删除该管理员？</h3>
<%-- 					<a href="${pageContext.request.contextPath }/deleteCNews?" id="deleteCNewsBt" class="btn btn-danger" role="button" >删除</a>  
 				
 --%>				
 				</div>
				<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
 				    <button class="btn btn-danger" onclick="deleteAdmin()" id="deleteAdmin" type="button">确定删除</button> 
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
