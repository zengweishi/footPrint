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
    
    <title>My JSP 'test2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" type="text/css" />
		 <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
		<link href="${ pageContext.request.contextPath }/css/font-awesome.min.css" rel="stylesheet" />
		<script src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- <style type="text/css">
    .modal.fade.in{
        top:300px;  
    }
</style> -->

  </head>
  
  <body>
  	
		  <div class="input-group">
			  <span class="input-group-addon" id="sizing-addon2">标题：</span>
			  <input type="text" id="anewsTitle" name="anewsTitle" class="form-control" placeholder="请输入标题..." aria-describedby="sizing-addon2">
		  </div>
	
		  <div id="editor">
		     <p>开始编辑正文，请输入内容...</p>
		  </div>
		  
		  <div style="margin-top: 20px;margin-left: 20px">
		  	<%-- <c:if test="${ empty share }"> --%>
		    <!-- Indicates a successful or positive action -->
		    
		 		<button type="button" value="submit" id="release" onclick="doPost('${pageContext.request.contextPath }/updateANews')"  name="release" class="btn btn-success">更新文章</button>
		 
		 	<!-- <div class="modal-body" style="margin-top: 20px;margin-left: 20px"> -->
					
			
	 		<!-- </div> -->
		  <%-- </c:if> --%>
		 <%--  <c:if test="${ not empty share }">
		    <!-- Indicates a successful or positive action -->
		 	<button type="button" value="submit" id="release" onclick="doPost('${pageContext.request.contextPath }/updateShareArtical')"  name="release" class="btn btn-success">更新分享</button>
		  </c:if> --%>
 <%-- <a href="javascript:void(0)"  id="release" onclick="doPost('${pageContext.request.contextPath }/writeCNews')" class="btn btn-primary" role="button" class="btn btn-primary btn-sm" data-toggle="modal"
					data-target="#myModal">发布新文章</a>   --%>
 		</div>
 		<div class="modal-body" style="margin-top: -3px;margin-left: 5px">
 		
 		</div>
	
    <!-- 注意， 只需要引用 JS，无需引用任何 CSS ！！！-->
    <script type="text/javascript" src="${ pageContext.request.contextPath }/js/wangEditor.js"></script>
    <script type="text/javascript">
        var E = window.wangEditor
        var editor = new E('#editor')
        // 或者 var editor = new E( document.getElementById('editor') )
       // 下面两个配置，使用其中一个即可显示“上传图片”的tab。但是两者不要同时使用！！！
     	//editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
     	 editor.customConfig.uploadFileName = 'clubImg';
	    editor.customConfig.uploadImgServer = '${pageContext.request.contextPath}/imgANewsUpload';
      
      //配置onfocus函数之后，用户点击富文本区域会触发onfocus函数执行。
	     
        editor.create()
        
         window.onload = function(){
	
		var updateANews="<%=request.getAttribute("updateANews")%>"; 
		document.getElementById("anewsTitle").value="${updateANews.anewsTitle}";
		//document.getElementById("editor").setContent("${cnews.newsContents}"); 
		//$("#editor").text("${cnews.newsContents}");
		editor.txt.html("${updateANews.anewsContents}");//牛逼啊这个方法，将数据回显到编辑器
		};
      
	   
		function doPost(url){
			//alert(diaryPermission);
			var anewsContents = editor.txt.html();
		    var anewsTitle = document.getElementById("anewsTitle").value;
		    var id = "${updateANews.anewsId}";
		  
			$.post(url,{'anewsContents':anewsContents,'anewsTitle':anewsTitle,'id':id},function(data) {   
				window.location.href="${pageContext.request.contextPath}/findANewsArticle?anewsId=${updateANews.anewsId}";
			});
  
			
		}
		
		
		
			
        
    </script>
  </body> 

</html>
