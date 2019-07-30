<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>用户中心</title>
<meta name="description" content="1" />
<meta name="keywords" content="2" />
<meta name="renderer" content="webkit"></meta>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"></meta>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath }/js/jQuery1.8.2.js" type="text/javascript"></script>
<!-- 		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
 -->		
 <script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
 
<!--[if IE]><script src="${pageContext.request.contextPath }/js/html5.js" type="text/javascript"></script><![endif]-->

<script type="text/javascript">
	function judgeUser() {
		var userType =$("input[name = 'userType']:checked").val(); 
		if(userType == 'club') {
			//loginform.action="/项目名/跳转路径";     //注意这块的路径是绝对路径
			//loginform.submit();表单进行提交，结果就跳转到相应的action对应的页面中
			loginForm.action="${pageContext.request.contextPath}/clubLogin";
			loginForm.submit();
		} 
		if(userType == 'coach') {
			loginForm.action="${pageContext.request.contextPath}/coachLogin";
			loginForm.submit();
		} 
		if(userType == 'player') {
			loginForm.action="${pageContext.request.contextPath}/playerLogin";
			loginForm.submit();
		} 
		if(userType == 'tourist') {
			loginForm.action="${pageContext.request.contextPath}/touristLogin";
			loginForm.submit();
		} 
		if(userType == 'admin') {
			loginForm.action="${pageContext.request.contextPath}/adminLogin";
			loginForm.submit();
		} 
	}
</script>

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


<div class="container"  style="width:100%;height:560px;background:#FF2C4C url('${pageContext.request.contextPath }/imgs/loginBackgd.jpg') no-repeat;">
<div class="row"> 
	<div class="col-md-7">
		<!--<img src="${pageContext.request.contextPath}/image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
	</div>
	
	<div class="col-md-5">
				<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
				<font color="#3164af" size="5px">用户登录</font>USER LOGIN<br/>
				<span style="color:red">${ msg }</span>
				<div>&nbsp;</div>
<form class="form-horizontal" id="loginForm" method="post">
 
  <!-- 隐藏的一个input,传method值 -->
  <input type="hidden" name="method" value="login">
 
 <div class="form-group">
    <label for="username" class="col-sm-2 control-label">账号</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="username" name="username" value="${ cookie.remember.value }" placeholder="请输入用户名">
      
    </div>
  </div>
   <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label><!-- <a href="">忘记密码？</a> -->
    <div class="col-sm-6">
      <input type="password" class="form-control" name="password" id="inputPassword3" placeholder="请输入密码">
    </div>
  </div>
  <%--  <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
    <div class="col-sm-3">
      <input type="text" class="form-control" name="code" id="code" placeholder="请输入验证码">
    </div>
    <div class="col-sm-3">
      <img id="img1" src="${pageContext.request.contextPath}/CheckImgServlet"/>
    </div> --%>
     <%-- <div class="form-group">
        <!-- <label for="inputPassword3" class="col-sm-2 control-label">验证码</label> -->
   <!--  <div class="col-sm-3">
      <input type="text" class="form-control" id="inputPassword3" name="code" placeholder="请输入验证码">
    </div> -->
    <div class="col-sm-3">
      <img id="img1" src="${pageContext.request.contextPath}/CheckImgServlet" />
    </div>
    
  </div> --%>
  <!--  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox" name="autoLogin" value="true"> 自动登录
        </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>
          <input type="checkbox" name="remember" value="true"> 记住用户名
        </label>
      </div>
    </div>
  </div> -->
  
  
   <!-- <div class="form-group opt">   -->
			 <label for="inlineRadio1" class="col-sm-2 control-label">类型</label>  
			  <!-- <div class="col-sm-8"> -->
			     <div class="form-group opt">  
			    <label class="radio-inline">
			  <input type="radio" name="userType" id="inlineRadio1" value="club" checked> 俱乐部
			</label>
			<label class="radio-inline">
			  <input type="radio" name="userType" id="inlineRadio2" value="coach"> 教练
			</label>
			<label class="radio-inline">
			  <input type="radio" name="userType" id="inlineRadio3" value="player"> 球员
			</label>
			<!-- <label class="radio-inline">
			  <input type="radio" name="userType" id="inlineRadio4" value="tourist"> 游客
			</label> -->
 </div>
  <label for="inlineRadio1" class="col-sm-2 control-label"></label>  
 <div class="form-group opt">  
			   <!--  <label class="radio-inline">
			  <input type="radio" name="userType" id="inlineRadio1" value="club" checked> 俱乐部
			</label>
			<label class="radio-inline">
			  <input type="radio" name="userType" id="inlineRadio2" value="coach"> 教练
			</label> -->
			<label class="radio-inline">
			  <input type="radio" name="userType" id="inlineRadio3" value="admin"> 管理员
			</label>
			<label class="radio-inline">
			  <input type="radio" name="userType" id="inlineRadio4" value="tourist"> 游客
			</label>
 </div>
 

 
 <div class="col-sm-8" style="height: 10px">
 </div> 
 
 <%-- <li><a href="${ pageContext.request.contextPath }/UserServlet?method=registUI">
	<button type="button" class="btn btn-info" id="add" name="add">注册</button>
</a></li> --%>
 
 <div class="form-group" >
    <div class="col-sm-offset-2 col-sm-10">
    <button type="button" class="btn btn-info" id="add" name="add" onclick="judgeUser()">登   录</button>
    <a href="${pageContext.request.contextPath }/clubRegistUI">
    	<button type="button" class="btn btn-info" id="add" name="add">俱乐部注册</button>
    </a>
     <a href="${pageContext.request.contextPath }/touristRegistUI">
    	<button type="button" class="btn btn-info" id="add" name="add">游客注册</button>
    </a>
    <%-- <input type="submit"  width="100" value="登录" name="submit" border="0"
    style="background: url('${pageContext.request.contextPath}/images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;"> --%>
    </div>
  </div>
</form>
</div>			
	</div>
</div>
</div>	


</body>
</html>
