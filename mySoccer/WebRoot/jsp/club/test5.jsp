<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test5.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- 引入自定义css文件 style.css ，不放在第一个引进的话部分样式会出问题-->
	<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
	<script src="${ pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${ pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${ pageContext.request.contextPath }/js/echarts.min.js" type="text/javascript"></script>
	
<script type="text/javascript">
var cid = "${infoClub.cid}";
function findClubAnnouncement() {
	$.post("${pageContext.request.contextPath}/getClubAnnouncement",{'cid':cid},function(data) {
		var infoClubAnnouncement = eval(data);
		var html=""; 
		for(var i = 0;i<infoClubAnnouncement.length;++i) {
			html+='<div class="vertical-timeline-block">'
				+'<div class="vertical-timeline-icon blue-bg">'
				+'<i class="fa fa-file-text"></i>'
				+'</div>'
				+'<div class="vertical-timeline-content">'
				+'<p>'+infoClubAnnouncement[i].aContent+'</p>'
				+' <span class="vertical-date">'
				+'<br/>'
				+'<strong>'+infoClubAnnouncement[i].aTime+'</strong>'
				+'</span>'
				+'</div>'
				+'</div>';
				
		}
		$("#clubNews").html("");
		$("#clubAnnouncement").html(html);
		
	});
}
</script>


 <script type="text/javascript">
        var E = window.wangEditor
        var editor = new E('#editor')
     	editor.customConfig.uploadFileName = 'clubImg';
	    editor.customConfig.uploadImgServer = '${pageContext.request.contextPath}/imgUpload';
        editor.create();
        </script>
 <script type="text/javascript"> 
      	//将数据回显到编辑器
        window.onload = function(){
		var cnews="<%=session.getAttribute("cnews")%>"; 
		document.getElementById("newsTitle").value="${cnews.newsTitle}";
		editor.txt.html("${cnews.newsContents}");
 		};
	
		function doPost(url){
			var newsContent = editor.txt.html();
		    var newsTitle = document.getElementById("newsTitle").value;
		    var id = "${cnews.newsId}";
			var i;
			 $.post(url, {'newsContent':newsContent, 'newsTitle':newsTitle,'id':id},function(data) {
			 i = data;
			  if(i=="1") {
    	window.location.href="${pageContext.request.contextPath}/showAllCNews?currPage=1";
			 
			 } 
			  if(i=="2") {
			window.location.href="${pageContext.request.contextPath}/showCNewsArtical?newsId=${cnews.newsId}" ;
			 } 
    });
  
}        
</script>
<script type="text/javascript">
<script type="text/javascript">
var userType = "0";
$(function() {
       //下拉列表绑定事件
 $("#role").change(function() {
     userValue = $(this).val();
    	
 });
});
function judgeUser() {
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
		loginForm.action="${pageContext.request.contextPath}/adminLogin"/;
		loginForm.submit();
	} 
}
</script>

</script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->



  </head>

 
  <body>
<div id="main" style="width: 500px;height:450px;"></div>
               		<script type="text/javascript">
               		 // 基于准备好的dom，初始化echarts实例
               	    var myChart = echarts.init(document.getElementById('main'));
               	    // 指定图表的配置项和数据
               	     var option = {

               	    	    xAxis: {
               	    	        type: 'category',
               	    	        data: ['1', '2', '3', '4', '5', '6', '7']
               	    	    },
               	    	    yAxis: {
               	    	        type: 'value'
               	    	    },
               	    	    series: [{
               	    	    	
               	    	        data: [1,2,3],
               	    	        type: 'line'
               	    	    }]
               	    	};  
               	    
               	    var jsonObj;
               	    var infoPlayer="<%=session.getAttribute("infoPlayer")%>"; 
               		var pid = "${infoPlayer.pid}";d
               	    function findPracticeScoreForInfo() {	  
               	    	$("#playerDeeds").html("");
               			$("#infoDiary").html("");
               	    	$.post("${pageContext.request.contextPath}/getPracticeForInfo",{'pid':pid},function(data) {
               				 jsonObj = eval(data);
               				
               	        
               	        myChart.setOption({
               	        		xAxis: {
               			            type: 'category',
               			            boundaryGap: false,
               				        data: ['1', '2', '3', '4', '5', '6', '7', '8' ,'9', '10']
               				    },
               				    yAxis: {
               				        type: 'value'
               				    },
               			        series: [{
               			        	name:'流入总数',

               			        	stack: '总量',
               			        	areaStyle: {
               			        	normal: {
               			        	color: '#8cd5c2' //改变区域颜色
               			        	}
               			        	},
               			        	itemStyle : { 
               			        	normal : { 
               			        	color:'#8cd5c2', //改变折线点的颜色
               			        	lineStyle:{ 
               			        	color:'#8cd5c2' //改变折线颜色
               			        	} 
               			        	} 
               			        	},
               			            data: jsonObj,
               			        	type: 'line'
               			        }]
               		        });
               		   	}); 
               	    }
               		</script>

    <button onclick="findPracticeScoreForInfo()">点击测试</button>
  </body>
</html>





