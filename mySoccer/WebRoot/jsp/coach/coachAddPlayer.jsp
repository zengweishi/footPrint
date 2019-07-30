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



<%-- <div class="container" style="width:100%;background:url('${pageContext.request.contextPath }/imgs/loginBackgd.jpg');">
 --%>
 <div class="container" style="width:100%;">
 
 <div class="row"> 

	<div class="col-md-2"></div>
	
	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
		<font color="#3164af" size="5px">创建新球员</font>ADD PLAYER
		<form class="form-horizontal" style="margin-top:5px;" action="${pageContext.request.contextPath }/addPlayerByCoach" method="post" enctype = "multipart/form-data">
	

			
			<!-- 这边要有一个隐藏的input,得把method值传过去，父类BaseServlet才能获取到方法名method -->
		<!-- 	 <input type="hidden" name="method" value="regist">      -->
			
			
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">账号名</label>
			    <div class="col-sm-6">
			      <input type="email" class="form-control" class="required" id="pNumber" name="pNumber" placeholder="请输入球员邮箱"><span id="s1"></span>
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
			      <input type="text" class="form-control" class="required" id="password" name="password"  placeholder="请设置密码(初始密码建议123456)">
			    </div><span style="color:red">*</span>
			    
			 <!--  </div>
			   <div class="form-group">
			    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" class="required" id="repassword" name="repassword"  placeholder="请输入确认密码（初始密码建议123456）">
			    </div><span style="color:red">*</span>
			   -->
			  </div> 
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="pRealName" name="pRealName"  placeholder="请输入球员姓名">
			    </div><span style="color:red">*</span>
			  </div>
			 <!-- <div class="form-group">
			    <label for="usercaption" class="col-sm-2 control-label">地址</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="clubCity" name="clubCity"  placeholder="请输入俱乐部地址">
			    </div><span style="color:red">*</span>
			  </div> -->
			  <div class="form-group opt">  
			  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>  
			  <div class="col-sm-6">
			    <label class="radio-inline">
			  <input type="radio" name="pGender" id="inlineRadio1" value="男" checked> 男
			</label>
			<label class="radio-inline">
			  <input type="radio" name="pGender" id="inlineRadio2" value="女"> 女
			</label>
			</div>
			  </div> 	
			 <div class="form-group">
			    <label for="date" class="col-sm-2 control-label">联系电话</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="pTelePhone" name="pTelePhone" placeholder="请输入球员联系电话" >		      
			    </div><span style="color:red">*</span>
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
			  
			<!--   <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户头像</label>
			    <div class="col-sm-6">
					<input type="file" name="clubPhoto"/>
				</div><span style="color:red">*</span>
			  </div> -->
			 
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			    <button type="submit" class="btn btn-info" id="regBut" name="add">添加</button>
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
