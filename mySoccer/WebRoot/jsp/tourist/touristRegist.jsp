<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户中心</title>
<meta name="description" content="1" />
<meta name="keywords" content="2" />
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath }/js/jQuery1.8.2.js" type="text/javascript"></script>
<!-- 		<script src="..js/jquery-1.11.3.min.js" type="text/javascript"></script>-->		
 <script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
 
<!--[if IE]><script src="..js/html5.js" type="text/javascript"></script><![endif]-->

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font .regist {
    color: #3164af;
    font-size: 18px;
    font-weight: normal;
    padding: 0 10px;
}
 </style>
 
</head>
<body>
<!--头部开始-->
<header data-waypointsok="" class="header mysection">
  <div class="tem_inner tem_head mynav mysection_s">
    <h1> <a class="logo_img" href="" title=""> <img src="${pageContext.request.contextPath}/images/mylogo.jpg" alt="" title="" /> </a> </h1>
    <nav>
      <ul>
         <li><a href="${pageContext.request.contextPath }/findClubForHomePage?currPage=1" title="青训机构">青训机构</a></li>
        <li><a href="${pageContext.request.contextPath }/findCNewsForHomePage?currPage=1" title="机构动态">机构动态</a></li>
        <li><a href="${pageContext.request.contextPath }/findANewsForHomePage?currPage=1" title="新闻中心">新闻中心</a></li>
        <li><a href="${pageContext.request.contextPath }/findDiaryForHomePage?currPage=1" title="球员日记">球员日记</a></li>
        <li><a href="${pageContext.request.contextPath }/findShareForHomePage?currPage=1" title="产品中心">教练宝典</a></li>
        <li><a href="${pageContext.request.contextPath }/findTNewsForHomePage?currPage=1" title="其他资讯">其他资讯</a></li>
        
        
         <%-- <c:if test="${ (empty existClub)&&(empty existCoach)&&(empty existPlayer)&&(empty existAdmin)&&(empty existTourist) }">
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
        </c:if> --%>
      </ul>
    </nav>
  </div>
</header>
<!--头部结束-->


<div class="container" style="width:100%;background:url('${pageContext.request.contextPath }/imgs/loginBackgd.jpg');">
<div class="row"> 

	<div class="col-md-2"></div>
	
	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
		<font color="#3164af" size="5px">游客注册</font>CLUB REGISTER
		<form class="form-horizontal" style="margin-top:5px;" action="${pageContext.request.contextPath }/touristRegist" method="post" enctype = "multipart/form-data">
	

			
			<!-- 这边要有一个隐藏的input,得把method值传过去，父类BaseServlet才能获取到方法名method -->
		<!-- 	 <input type="hidden" name="method" value="regist">      -->
			
			
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-6">
			      <input type="email" class="form-control" class="required" id="touristEmail" name="touristEmail" placeholder="请输入游客用户邮箱"><span id="s1"></span>
			    </div><span style="color:red">*</span>
			  </div>
			  <!--  <div class="form-group" >
				    <label for="username" class="col-sm-2 control-label"></label>
				    <div class="col-sm-6">
						<span id="s1"></span>
				    </div>
			  </div> -->
			    
			   <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" class="required" id="touristPassword" name="touristPassword"  placeholder="请输入密码">
			    </div><span style="color:red">*</span>
			    
			  </div>
			   <div class="form-group">
			    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" class="required" id="repassword" name="repassword"  placeholder="请输入确认密码">
			    </div><span style="color:red">*</span>
			   
			  </div>
			 
			 <div class="form-group">
			    <label for="usercaption" class="col-sm-2 control-label">籍贯</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="touristAddress" name="touristAddress"  placeholder="请输入籍贯">
			    </div><span style="color:red">*</span>
			  </div>
			<div class="form-group opt">  
			  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>  
			  <div class="col-sm-6">
			    <label class="radio-inline">
			  <input type="radio" name="touristGender" id="inlineRadio1" value="男" checked> 男
			</label>
			<label class="radio-inline">
			  <input type="radio" name="touristGender" id="inlineRadio2" value="女"> 女
			</label>
			</div>
			  </div> 
				
			 <!--  <div class="form-group">
			    <label for="date" class="col-sm-2 control-label">出生日期</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="birthday"  name="birthday" placeholder="格式为：2000-05-03" >		      
			    </div><span style="color:red">*</span>
			  </div> -->
			  
			 <!--  <div class="form-group">
			    <label for="date" class="col-sm-2 control-label">验证码</label>
			    <div class="col-sm-3">
			      <input type="text" class="form-control"  >
			      
			    </div>
			    <div class="col-sm-2">
			    <img src="./image/captcha.jhtml"/>
			    </div>
			    
			  </div> -->
			  
			  <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户头像</label>
			    <div class="col-sm-6">
					<input type="file" name="touristPhoto"/>
				</div><span style="color:red">*</span>
			  </div>
			 
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			    <button type="submit" class="btn btn-info" id="regBut" name="add">注册</button>
			      <%-- <input type="submit"  id="regBut" width="100" value="注册" border="0"
				    style="background: url('${pageContext.request.contextPath }/images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;"> --%>
			    </div>
			  </div>
			</form>
	</div>
	
	<div class="col-md-2"></div>
  
</div>
</div>
  

  </body>
</html>
