<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'clubHomePage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styleindex.css" />
	<script src="${pageContext.request.contextPath }/js/jQuery1.8.2.js" type="text/javascript"></script>
		
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>

    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">
 

  </head>
  
  <body >
  	

<!--头部开始-->
<!--头部开始-->
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
<!--头部结束-->
<!--头部结束-->



<!--banner开始-->

<div class="tem_banner">
  <ul class="slides" style="height:380px;">
    <li style="background:url(${pageContext.request.contextPath }/images/kk.jpg) no-repeat center center; height:380px;"></li>
  </ul>
</div>



    
    
    
    
    <section class="met_section mynews_aside  ">
  <aside>
    
	<!--banner结束-->

      <!--新闻列表-->
  
            <ul>
              <c:forEach var="p" items="${ rightList }" varStatus="status">
            	 	<li><a href="${pageContext.request.contextPath }/findANewsArticle?pageFlag=1&anewsId=${p.anewsId}" target="_self"><b > ${status.count}</b>${p.anewsTitle }</a></li>
              </c:forEach>
            </ul>
          </dd>
        </dl>
      
  </aside>
  <article>
    <div class="met_article">
      <section class="met_article_head">
	      <div >
	      	<h1 ><strong> 官方新闻  </strong></h1>
	      	<h4><strong>最新资讯，应有尽有</strong></h4>
	      </div>
        <div class="met_position"> <a href="${pageContext.request.contextPath }/index" title="首页">首页</a> &gt; </div>
      </section>
      <div class="met_clear"></div>
      <div class="met_module2_list ">
        <div class="">
          <div class="my_news" >
        <ul>
			
              
                
				 <div class="wrapper wrapper-content  animated fadeInRight blog">
        <div class="row">
           
           
             <c:forEach var="p" items="${ topList }">

                <div class="ibox">
                    <div class="ibox-content">
                        <a href="${pageContext.request.contextPath }/findANewsArticle?pageFlag=1&anewsId=${p.anewsId}" class="btn-link">
                            <h2>${p.anewsTitle } &nbsp;<button class="btn btn-danger btn-xs" type="button">置顶</button></h2> 
                        </a>
                        
                        <div class="small m-b-xs">
                          <span class="text-muted"> by </span> <strong>${ p.anewsAdmin.adminName }</strong> &nbsp;&nbsp;&nbsp;&nbsp;<span class="text-muted"><i class="fa fa-clock-o"></i>${ fn:substring(p.anewsDate,0,19) }</span>
                        </div>
                        <p>${ p.anewsHeadContent }......</p>
                        <div class="row">
                             <div class="col-md-6">
                                <button class="btn btn-info btn-xs" type="button">官方新闻</button>
                                
                             </div> 
                            <div class="col-md-6">
                                <div class="small text-right">              
                                     <i class="fa fa-eye"> </i> ${p.viewNumber } 阅读
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                </c:forEach>
           
           
           
           
           
           
           
              <c:forEach var="p" items="${ pageBean.list }">
                <div class="ibox">
                    <div class="ibox-content">
                        <a href="${pageContext.request.contextPath }/findANewsArticle?pageFlag=1&anewsId=${p.anewsId}" class="btn-link">
                            <h2>${p.anewsTitle }</h2> 
                        </a>
                        
                        <div class="small m-b-xs">
                          <span class="text-muted"> by </span> <strong>${ p.anewsAdmin.adminName }</strong> &nbsp;&nbsp;&nbsp;&nbsp;<span class="text-muted"><i class="fa fa-clock-o"></i>${ fn:substring(p.anewsDate,0,19) }</span>
                        </div>
                        <p>${ p.anewsHeadContent }......</p>
                        <div class="row">
                             <div class="col-md-6">
                                <button class="btn btn-info btn-xs" type="button">官方新闻</button>
                                
                             </div> 
                            <div class="col-md-6">
                                <div class="small text-right">              
                                     <i class="fa fa-eye"> </i> ${p.viewNumber } 阅读
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
               
        </div>
    </div>
				
             
             
            </ul> 
            
            
            
       </div>
        </div>
      </div>
    </div>
  </article>
   
 <!--分页 -->
		<div style="width:380px;margin:0 auto;margin-top:10px;">
			<ul class="pagination" style="text-align:center; margin-top:10px;">
				<!-- 页数为第一页时，上一页按钮失效 -->
				<c:if test="${ pageBean.currPage == 1 }">
					<li class='disabled'>
						<a href="${pageContext.request.contextPath }/findANewsForHomePage?currPage=1#">&laquo;</span></a>
					</li>
				</c:if>
				<c:if test="${ pageBean.currPage != 1 }">
					<li>
						<a href="${pageContext.request.contextPath }/findANewsForHomePage?currPage=${ pageBean.currPage-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
					</li>
				</c:if>
				
				<!-- 当点击的页数是当前页时无效,不是当前页则可以跳转 -->
				<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
					<c:if test="${ pageBean.currPage == i }">
						<li class="active"><a href="{pageContext.request.contextPath }/findANewsForHomePage?currPage=${ i }#">${ i }</a></li>
					</c:if>
					
					<c:if test="${ pageBean.currPage != i }">
						<li><a href="${pageContext.request.contextPath }/findANewsForHomePage?currPage=${ i }">${ i }</a></li>
					</c:if>	
				</c:forEach>
				
				<!-- 当页数为最后一页时，下一页按钮失效 -->
				<c:if test="${ pageBean.currPage == pageBean.totalPage }">
					<li class='disabled'>
						<a href="${pageContext.request.contextPath }/findANewsForHomePage?currPage=${ pageBean.totalPage }#" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
				<c:if test="${ pageBean.currPage != pageBean.totalPage }">
					<li>
						<a href="${pageContext.request.contextPath }/findANewsForHomePage?currPage=${ pageBean.currPage + 1 }" aria-label="Next">
							<span aria-hidden="true">&raquo;</span>
						</a>
					</li>
				</c:if>
			</ul>
		</div>  
	
		<!-- 分页结束=======================        -->         
         
			
          </article>
        <div class="met_clear"></div>
</section>
<div class="met_clear"></div>      
         
		
<!--新闻列表结束-->
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   

<!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>






</body>
</html>
