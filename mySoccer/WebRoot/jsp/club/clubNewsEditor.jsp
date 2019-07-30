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
			  <input type="text" id="newsTitle" name="newsTitle" class="form-control" placeholder="请输入标题..." aria-describedby="sizing-addon2">
		  </div>
	
		  <div id="editor">
		     <p>开始编辑正文，请输入内容...</p>
		  </div>
		  
		  <div style="margin-top: 20px;margin-left: 20px">
		  	<c:if test="${ empty cnews }">
		    <!-- Indicates a successful or positive action -->
		 	<button type="button" value="submit" id="release" onclick="doPost('${pageContext.request.contextPath }/writeCNews')"  name="release" class="btn btn-success">发布新文章</button>
		  </c:if>
		  <c:if test="${ not empty cnews }">
		    <!-- Indicates a successful or positive action -->
		 	<button type="button" value="submit" id="release" onclick="doPost('${pageContext.request.contextPath }/updateCNewsArtical')"  name="release" class="btn btn-success">更新文章</button>
		  </c:if>
 <%-- <a href="javascript:void(0)"  id="release" onclick="doPost('${pageContext.request.contextPath }/writeCNews')" class="btn btn-primary" role="button" class="btn btn-primary btn-sm" data-toggle="modal"
					data-target="#myModal">发布新文章</a>   --%>
 </div>
	 <!-- Modal -->
	<div class="modal fade bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document" style="z-index: 2041;">
    <div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<!-- <h4 class="modal-title" id="myModalLabel">发布成功！</h4> -->
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
					<h4 class="modal-title" id="myModalLabel">发布成功！</h4>
					<a href="${pageContext.request.contextPath }/jsp/club/clubNewsEditor.jsp" class="btn btn-info" role="button" >再写一篇</a>  
					<a href="${pageContext.request.contextPath }/showAllCNews?currPage=1" class="btn btn-success" role="button" >查看文章</a>  
				</div>
				<div class="modal-footer">
					<nav id="page"> </nav>
				</div>
			</div>
		</div>
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
	    editor.customConfig.uploadImgServer = '${pageContext.request.contextPath}/imgUpload';
      
      //配置onfocus函数之后，用户点击富文本区域会触发onfocus函数执行。
	     
        editor.create()
        
        window.onload = function(){
	
		var cnews="<%=request.getAttribute("cnews")%>"; 
		document.getElementById("newsTitle").value="${cnews.newsTitle}";
		//document.getElementById("editor").setContent("${cnews.newsContents}"); 
		//$("#editor").text("${cnews.newsContents}");
		editor.txt.html("${cnews.newsContents}");//牛逼啊这个方法，将数据回显到编辑器
 		//editor.txt.html("<p><img src="+"/mySoccer/upload/cae6395679f545bf9b664086c5087702photo1.jpg" +" style="+"max-width:100%;"+"></p>");//牛逼啊这个方法，将数据回显到编辑器
 		};
		
        /* document.getElementById('release').addEventListener('click', function () {
        // 读取 text
        alert(editor.txt.text()) 
        
    }, false)*/	    

		/*  var newsContent;
	    document.getElementById('release').addEventListener('click', function () {
	        // 读取 html
	        newsContent = editor.txt.html();
	        alert(editor.txt.html());
	    }, false)  */
		function doPost(url){
			var newsContent = editor.txt.html();
		    var newsTitle = document.getElementById("newsTitle").value;
		    var id = "${cnews.newsId}";
		    //解决乱码：两编一解：先对js中的中文进行编码，然后到action去解码就可以了
			/* newsContent = encodeURI(newsContent );
			newsContent = encodeURI(newsContent );//要两次
			newsTitle = encodeURI(newsTitle );
			newsTitle = encodeURI(newsTitle ); */
			//$.post(url, {'newsContent':newsContent, 'newsTitle':newsTitle});
			var i;
			 $.post(url, {'newsContent':newsContent, 'newsTitle':newsTitle,'id':id},function(data) {
			 i = data;
			  if(i=="1") {
    	window.location.href="${pageContext.request.contextPath}/showAllCNews?currPage=1";
			 
			 } 
			  if(i=="2") {
		//window.location.href="${pageContext.request.contextPath}/showAllCNews?currPage=1";
		//window.location.href="${pageContext.request.contextPath}/showCNewsArtical?newsId="+${cnews.newsId};
			window.location.href="${pageContext.request.contextPath}/showCNewsArtical?newsId=${cnews.newsId}" ;
			 } 
    });
  
			//location.href="${pageContext.request.contextPath}/writeCNews?newsContent="+newsContent+"&newsTitle="+newsTitle;
			
		}
		
		
		
			
        
    </script>
  </body> 

</html>
