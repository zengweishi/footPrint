<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'newAnnouncement.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.bg {
	max-width: 530px;
	padding: 15px;
	margin: 0 auto;
}
</style>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css" type="text/css" />
<script src="${pageContext.request.contextPath }/js/jQuery1.8.2.js" type="text/javascript"></script>
<!-- 		<script src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js" type="text/javascript"></script>
 -->		
 <script src="${pageContext.request.contextPath }/js/bootstrap.min.js" type="text/javascript"></script>
 
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<script type="text/javascript">
	window.onload = function(){
		/* 修改操作回显训练信息 */
	    var updateCoachPlan="<%=session.getAttribute("updateCoachPlan")%>"; 
	    if(updateCoachPlan !="") {
	        document.getElementById("planTime").value="${updateCoachPlan.planTime}";
			document.getElementById("planAddress").value="${updateCoachPlan.planAddress}";
			document.getElementById("planContent").value="${updateCoachPlan.planContent}"; 	
			
	    }
		};
</script>

  </head>
  
  <body>
   <div class="wrapper wrapper-content animated fadeIn">
	   	
    		<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/updateCoachPlan?planId=${updateCoachPlan.planId}" method="post">
		
			<!-- 隐藏字段 -->
			<!-- <input type="hidden" name="method" value="save"> -->
			<table class="table table-bordered" cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
						height="26">
						<STRONG>发布训练计划</STRONG>
					</td>
				</tr>
				
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						训练时间：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
							<!-- <div class="input-group">
							  <input type="text" class="form-control" placeholder="训练时间" id="planTime" name="planTime" aria-describedby="sizing-addon2">
							</div> -->
							<div class="col-sm-10" >
                                <input class="form-control layer-date" id="planTime" name="planTime" placeholder="YYYY-MM-DD hh:mm:ss" onclick="laydate({istime: true, format: 'YYYY-MM-DD hh:mm:ss'})">
                                <label class="laydate-icon"></label>
                            </div>
					</td>
				</tr>
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						训练地点：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
						    <div class="input-group" style="margin-left: 14px">
							  <input type="text" class="form-control" placeholder="训练地点" id="planAddress" name="planAddress" aria-describedby="sizing-addon2">
							</div> 
					</td>
				</tr>
				
				<tr>
					<td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
						训练内容：
					</td>
					<td class="ta_01" bgColor="#ffffff" colspan="3">
							<textarea class="form-control" rows="9" id="planContent" name="planContent">
							</textarea>
					</td>
				</tr>
			
				<tr>
					<td class="ta_01" style="WIDTH: 100%" align="center"
						bgColor="#f5fafe" colSpan="4">
						<button type="submit" id="userAction_save_do_submit" class="btn btn-info">确定</button>
						
						<FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
						<button type="reset" id="userAction_save_do_submit" class="btn btn-info" >重置</button>
					
						
					</td>
				</tr>
			</table>
		</form>
		
		</div>
		
    <!-- 全局js -->
    <script src="${pageContext.request.contextPath }/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath }/js/bootstrap.min.js?v=3.3.6"></script>



    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath }/js/content.js?v=1.0.0"></script>
	<!-- 引入laydate的JS -->
	<script src="${pageContext.request.contextPath }/js/plugins/layer/laydate/laydate.js"></script>
    <script>
        //外部js调用
        laydate({
            elem: '#hello', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
            event: 'focus' //响应事件。如果没有传入event，则按照默认的click
        });

        //日期范围限制
        var start = {
            elem: '#start',
            format: 'YYYY/MM/DD hh:mm:ss',
            min: laydate.now(), //设定最小日期为当前日期
            max: '2099-06-16 23:59:59', //最大日期
            istime: true,
            istoday: false,
            choose: function (datas) {
                end.min = datas; //开始日选好后，重置结束日的最小日期
                end.start = datas //将结束日的初始值设定为开始日
            }
        };
        var end = {
            elem: '#end',
            format: 'YYYY/MM/DD hh:mm:ss',
            min: laydate.now(),
            max: '2099-06-16 23:59:59',
            istime: true,
            istoday: false,
            choose: function (datas) {
                start.max = datas; //结束日选好后，重置开始日的最大日期
            }
        };
        laydate(start);
        laydate(end);
    </script>
  </body>
</html>
