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
 
 <script type="text/javascript">
 	function findClub() {
 		var contents = document.getElementById('clubFuzzyName').value;
 		if(contents == "") {
 			alert("对不起，您的输入为空！")
 		} else {
 			document.getElementById('findClubForm').action = "${pageContext.request.contextPath }/fuzzyFindClub?pageFlag=1";  
 	        document.getElementById("findClubForm").submit();  
 		}
 	}
 </script>

  </head>
  
  <body>
  
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


<!--banner开始-->

<div class="tem_banner">
  <ul class="slides" style="height:380px;">
    <li style="background:url(${pageContext.request.contextPath }/images/aa.png) no-repeat center center; height:380px;"></li>
  </ul>
</div>

<div class="gray-bg">
  <div class="ibox-content">
	    <div class="input-group ">
	    	<div class="ibox-tools">
	    		  <form id="findClubForm" action="" method="post">
						<input type="text" name="clubFuzzyName" id="clubFuzzyName" style="width:200px" class="form-control" placeholder="" aria-describedby="sizing-addon2">
						&nbsp;&nbsp;
					   <button type="button" onclick="findClub()" class="btn btn-info btn-sm">搜 索</button>
				  </form> 
		    </div>
		</div>
	</div>
 </div>

<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
        
         <c:forEach var="p" items="${ clubList }">
	            <div class="col-sm-4">
	                <div class="contact-box" style="height: 180px">
	                    <a href="${pageContext.request.contextPath}/getClubInformation?pageFlag=1&cid=${p.cid}">
	                        <div class="col-sm-4">
	                            <div class="text-center">
	                                <img alt="image" style="width: 60px;height: 60px; " class="img-circle m-t-xs img-responsive" src="${p.clubPhoto}">
	                                <div class="m-t-xs font-bold" style="margin-left: -12px"></div>
	                            </div>
	                           <%--  <div class="text-center">
		                            <c:if test="${not empty delete }"> 
		                             <a href="javascript:void(0)" onclick="deletePlayerByCoach('${p.pid}')"  class="btn btn-danger btn-sm" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
					data-target="#myModal">解除球员</a> 
		                            	<!-- <button type="button" class="btn btn-danger btn-sm">解除球员</button> -->
		                             </c:if> 
		                        </div> --%>
	                        </div>
	                        <div class="col-sm-8">
	                            <h3><strong>${p.clubName }</strong></h3>
	                            <p><i class="fa fa-map-marker"></i> ${p.clubCity }</p>
	                            <address>
	                            <strong>俱乐部简介:</strong><br>
	                           ${ fn:substring(p.clubIntroduction,0,30)}......<br>
	                          <!--   Weibo:<a href="">http://weibo.com/xxx</a><br> -->
	                            <abbr title="Phone"><strong>Tel:</strong></abbr> 
	                           		  ${p.clubPhone }                  	 
	                           	 
	                        </address>
	                        
	                        </div>
	                        <div class="clearfix"></div>
	                    </a>
	                </div>
	            </div>
			</c:forEach>
			
			<c:if test="${empty clubList }">
		
            <c:forEach var="p" items="${ pageBean.list }">
	            <div class="col-sm-4">
	                <div class="contact-box" style="height: 180px">
	                    <a href="${pageContext.request.contextPath}/getClubInformation?pageFlag=1&cid=${p.cid}">
	                        <div class="col-sm-4">
	                            <div class="text-center">
	                                <img alt="image" style="width: 60px;height: 60px; " class="img-circle m-t-xs img-responsive" src="${p.clubPhoto}">
	                                <div class="m-t-xs font-bold" style="margin-left: -12px"></div>
	                            </div>
	                           <%--  <div class="text-center">
		                            <c:if test="${not empty delete }"> 
		                             <a href="javascript:void(0)" onclick="deletePlayerByCoach('${p.pid}')"  class="btn btn-danger btn-sm" role="button" class="btn btn-danger btn-xs" data-toggle="modal"
					data-target="#myModal">解除球员</a> 
		                            	<!-- <button type="button" class="btn btn-danger btn-sm">解除球员</button> -->
		                             </c:if> 
		                        </div> --%>
	                        </div>
	                        <div class="col-sm-8">
	                            <h3><strong>${p.clubName }</strong></h3>
	                            <p><i class="fa fa-map-marker"></i> ${p.clubCity }</p>
	                            <address>
	                            <strong>俱乐部简介:</strong><br>
	                           ${ fn:substring(p.clubIntroduction,0,30)}......<br>
	                          <!--   Weibo:<a href="">http://weibo.com/xxx</a><br> -->
	                            <abbr title="Phone"><strong>Tel:</strong></abbr> 
	                           		  ${p.clubPhone }                  	 
	                           	 
	                        </address>
	                        
	                        </div>
	                        <div class="clearfix"></div>
	                    </a>
	                </div>
	            </div>
			</c:forEach>
			
			
			
			<c:if test="${ empty totalPlayer }">
				<c:if test="${not empty delete }">
							 <!--分页 -->
						<div style="width:380px;margin:0 auto;margin-top:10px;">
							<ul class="pagination" style="text-align:center; margin-top:10px;">
								<!-- 页数为第一页时，上一页按钮失效 -->
								<c:if test="${ pageBean.currPage == 1 }">
									<li class='disabled'>
										<a href="${pageContext.request.contextPath }/findClubForHomePage?code=1&currPage=1#">&laquo;</span></a>
									</li>
								</c:if>
								<c:if test="${ pageBean.currPage != 1 }">
									<li>
										<a href="${pageContext.request.contextPath }/findClubForHomePage?code=1&currPage=${ pageBean.currPage-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
									</li>
								</c:if>
								
								<!-- 当点击的页数是当前页时无效,不是当前页则可以跳转 -->
								<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
									<c:if test="${ pageBean.currPage == i }">
										<li class="active"><a href="${pageContext.request.contextPath }/findClubForHomePage?code=1&currPage=${ i }#">${ i }</a></li>
									</c:if>
									
									<c:if test="${ pageBean.currPage != i }">
										<li><a href="${pageContext.request.contextPath }/findClubForHomePage?code=1&currPage=${ i }">${ i }</a></li>
									</c:if>	
								</c:forEach>
								
								<!-- 当页数为最后一页时，下一页按钮失效 -->
								<c:if test="${ pageBean.currPage == pageBean.totalPage }">
									<li class='disabled'>
										<a href="${pageContext.request.contextPath }/findClubForHomePage?code=1&currPage=${ pageBean.totalPage }#" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:if>
								<c:if test="${ pageBean.currPage != pageBean.totalPage }">
									<li>
										<a href="${pageContext.request.contextPath }/findClubForHomePage?code=1&currPage=${ pageBean.currPage + 1 }" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:if>
							</ul>
						</div>  
					</c:if>
					<c:if test="${ empty delete }">
							 <!--分页 -->
						<div style="width:380px;margin:0 auto;margin-top:10px;">
							<ul class="pagination" style="text-align:center; margin-top:10px;">
								<!-- 页数为第一页时，上一页按钮失效 -->
								<c:if test="${ pageBean.currPage == 1 }">
									<li class='disabled'>
										<a href="${pageContext.request.contextPath }/findClubForHomePage?code=0&currPage=1#">&laquo;</span></a>
									</li>
								</c:if>
								<c:if test="${ pageBean.currPage != 1 }">
									<li>
										<a href="${pageContext.request.contextPath }/findClubForHomePage?code=0&currPage=${ pageBean.currPage-1 }" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
									</li>
								</c:if>
								
								<!-- 当点击的页数是当前页时无效,不是当前页则可以跳转 -->
								<c:forEach var="i" begin="1" end="${ pageBean.totalPage }">
									<c:if test="${ pageBean.currPage == i }">
										<li class="active"><a href="${pageContext.request.contextPath }/findClubForHomePage?code=0&currPage=${ i }#">${ i }</a></li>
									</c:if>
									
									<c:if test="${ pageBean.currPage != i }">
										<li><a href="${pageContext.request.contextPath }/findClubForHomePage?code=0&currPage=${ i }">${ i }</a></li>
									</c:if>	
								</c:forEach>
								
								<!-- 当页数为最后一页时，下一页按钮失效 -->
								<c:if test="${ pageBean.currPage == pageBean.totalPage }">
									<li class='disabled'>
										<a href="${pageContext.request.contextPath }/findClubForHomePage?code=0&currPage=${pageBean.totalPage}#" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:if>
								<c:if test="${ pageBean.currPage != pageBean.totalPage }">
									<li>
										<a href="${pageContext.request.contextPath }/findClubForHomePage?code=0&currPage=${ pageBean.currPage + 1 }" aria-label="Next">
											<span aria-hidden="true">&raquo;</span>
										</a>
									</li>
								</c:if>
							</ul>
						</div>  
					</c:if>
	</c:if>
		<!-- 分页结束=======================        -->
		
		</c:if>
         
        </div>
    </div>



<!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>





<!-- <section class="met_section mynews_aside  ">
  <aside>
    
	banner结束

      新闻列表
  
            <ul>
              <li><a href="news.html" target="_self"><b >1</b>我司移动项目顺利通过验收</a></li>
              <li><a href="news.html" target="_self"><b >2</b>谷歌将自主开发服务器处理器</a></li>
              <li><a href="news.html" target="_self"><b >3</b>曼联欲进军中国网游市场</a></li>
              <li><a href="news.html" target="_self"><b >4</b>谷歌拟将台湾数据中心扩大一倍</a></li>
              <li><a href="news.html" target="_self"><b >5</b>挑战自我极限 强化团队协作</a></li>
              <li><a href="news.html" target="_self"><b >6</b>上海自贸区跨境通试运行 建国内唯一跨国电子交易平台</a></li>
              <li><a href="news.html" target="_self"><b >7</b>西方国家为什么不允许“人肉搜索”</a></li>
              <li><a href="news.html" target="_self"><b >8</b>百度推荐全面开放 助推网站流量增长</a></li>
              <li><a href="news.html" target="_self"><b >9</b>互联助力阿里巴巴产业带</a></li>
            </ul>
          </dd>
        </dl>
      
  </aside>
  <article>
    <div class="met_article">
      <section class="met_article_head">
        <h1>新闻中心</h1>
        <div class="met_position"> <a href="index.html" title="首页">首页</a> &gt; <a href="newslist.html">企业新闻</a> </div>
      </section>
      <div class="met_clear"></div>
      <div class="met_module2_list ">
        <div class="">
          <div class="my_news" >
            <ul>
              <li>
                <h2> <a href="news.html" title="我司移动项目顺利通过验收" target="_self">我司移动项目顺利通过验收</a> </h2>
                <div class="article_info cl"> 2015年12月8日
                  <p> <a class="reader" href="news.html" target="_self"> <b>看</b> 20 </a> </p>
                </div>
                <div class="article">
                  <div class="article_img"> <a href="news.html" title="我司移动项目顺利通过验收" target="_self"><img src="../images/123456.jpg" alt="我司移动项目顺利通过验收"></a> </div>
                  <P>2011年11月25日，我司互联网信息服务有限公司在与韶关移动分公司洽谈后，移动对我司在移动通信行业的应用产品表示强烈的合作意向，并成功成为移动的采购项目</p>
                </div>
                <div class="readmore"><a href="news.html" target="_self">阅读全文<b></b></a></div>
              </li>
              <li>
                <h2> <a href="news.html" title="谷歌将自主开发服务器处理器" target="_self">谷歌将自主开发服务器处理器</a> </h2>
                <div class="article_info cl"> 2015年12月8日
                  <p> <a class="reader" href="news.html" target="_self"> <b>看</b> 34 </a> </p>
                </div>
                <div class="article">
                  <div class="article_img"> <a href="news.html" title="谷歌将自主开发服务器处理器" target="_self"><img src="../images/20131216_131931.jpg" alt="我司移动项目顺利通过验收"></a> </div>
                  <P>据外媒报道，有知情人士透露，谷歌正计划通过arm架构对自己的服务器处理器进行重新设计。这一行为被视为对英特尔市场地位的挑战。</p>
                </div>
                <div class="readmore"><a href="news.html" target="_self">阅读全文<b></b></a></div>
              </li>
              <li>
                <h2> <a href="news.html" title="曼联欲进军中国网游市场" target="_self">曼联欲进军中国网游市场</a> </h2>
                <div class="article_info cl"> 2015年12月8日
                  <p> <a class="reader" href="news.html" target="_self"> <b>看</b> 67 </a> </p>
                </div>
                <div class="article">
                  <div class="article_img"> <a href="news.html" title="曼联欲进军中国网游市场" target="_self"><img src="../images/123456.jpg" alt="我司移动项目顺利通过验收"></a> </div>
                  <P>世界最富盛名的足坛豪门之一曼彻斯特联足球俱乐部（以下简称“曼联”）被曝将进军中国游戏领域，近期正密集接洽多家游戏巨头，包括巨人网络、九城、网龙等在内的多家游戏巨头已收到来自大洋彼岸的合作邀请。看起来，由恒大成功引爆的资本与球队的核裂变效应已走出国门，对足球业产生实质影响。</p>
                </div>
                <div class="readmore"><a href="news.html" target="_self">阅读全文<b></b></a></div>
              </li>
              <li>
                <h2> <a href="news.html" title="谷歌拟将台湾数据中心扩大一倍" target="_self">谷歌拟将台湾数据中心扩大一倍</a> </h2>
                <div class="article_info cl"> 2015年12月8日
                  <p> <a class="reader" href="news.html" target="_self"> <b>看</b> 30 </a> </p>
                </div>
                <div class="article">
                  <div class="article_img"> <a href="news.html" title="谷歌拟将台湾数据中心扩大一倍" target="_self"><img src="../images/20131216_131931.jpg" alt="我司移动项目顺利通过验收"></a> </div>
                  <P>谷歌(Google)将对其在台湾建造的一个数据中心追加一倍投资。该数据中心是谷歌在亚洲设立的首批两个数据中心之一。亚洲互联网使用的迅猛增长，超越了这家美国互联网集团几年前最初规划该数据中心时的预期。</p>
                </div>
                <div class="readmore"><a href="news.html" target="_self">阅读全文<b></b></a></div>
              </li>
              <li>
                <h2> <a href="news.html" title="挑战自我极限 强化团队协作" target="_self">挑战自我极限 强化团队协作</a> </h2>
                <div class="article_info cl"> 2015年12月8日
                  <p> <a class="reader" href="news.html" target="_self"> <b>看</b> 19 </a> </p>
                </div>
                <div class="article">
                  <div class="article_img"> <a href="news.html" title="挑战自我极限 强化团队协作" target="_self"><img src="../images/123456.jpg" alt="我司移动项目顺利通过验收"></a> </div>
                  <P>2013年10月6日，经过党支部半个多月以来的策划和组织，公司27名员工一早就前往风景秀美的重渡沟，进行为期两天的拓展训练。此次活动全程得到了公司领导的重视和支持。</p>
                </div>
                <div class="readmore"><a href="news.html" target="_self">阅读全文<b></b></a></div>
              </li>
            </ul>
            <div class="met_pager"> <span class="PreSpan">上一页</span> <a href="newslist.html" class="Ahover">1</a> <a href="newslist.html">2</a> <a href="newslist.html" class="NextA">下一页</a> </div>
          </div>
        </div>
      </div>
    </div>
  </article>
  <div class="met_clear"></div>
</section>
<div class="met_clear"></div>
新闻列表结束

底部开始
<div class="met_clear"></div>
<footer class="tem_footer">
  <div class="my_inner">
    <div class="tem_footer_1 lf">
      <h1>联系我们</h1>
      <ul>
        <li>地址: 合肥市黄山路西环中心广场</li>
        <li>电话: 13931140805 15075171310</li>
        <li style="padding-right:72px;">QQ:<a href="mailto:2212322343@qq.com"> 2212322343@qq.com</a></li>
        <li>手机：13931140805 15075171310</li>
        <li>Email: 2212322343@qq.com 2050001400@qq.com</li>
      </ul>
    </div>

    <div class="tem_footer_2 lf">
      <div class="tem_footer_link">
        <h1>友情链接</h1>

        <div class="tem_img"> <a href="" title="" target="_blank"><img src="../images/links.jpg" title="" alt=""></a> <a href="" title="" target="_blank"><img src="../images/links.jpg" title="" alt=""></a> <a href="" title="" target="_blank"><img src="../images/links.jpg" title="" alt=""></a> <a href="" title="" target="_blank"><img src="../images/links.jpg" title="" alt=""></a> <a href="" title="" target="_blank"><img src="../images/links.jpg" title="" alt=""></a> </div>

        <div class="tem_txt"> 更多模板：<a href="http://www.mycodes.net/" target="_blank">源码之家</a> </div>
		
		<div class="tem_footer_3">
    
        <ul>
           <li><a href="about.html" title="关于我们">关于我们</a></li>
           <li><a href="wenhua.html" title="企业文化">企业文化</a></li>
           <li><a href="newslist.html" title="新闻中心">新闻中心</a></li>
           <li><a href="productlist.html" title="产品中心">产品中心</a></li>
           <li><a href="gbook.html" title="在线留言">在线留言</a></li>
           <li><a href="contact.html" title="联系我们">联系我们</a></li>
        </ul>
   
  </div>
 </div>
</div>
  
   
    <div class="met_clear"></div>

</footer> -->
<!--底部结束-->
</body>
</html>
