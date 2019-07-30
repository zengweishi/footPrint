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


    <title>我的计划</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> 
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css" />
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/css/animate.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style2.css?v=4.1.0" rel="stylesheet">

<script type="text/javascript">
	window.onload = function(){
		/* 修改操作回显信息 */
	    var updatePlayer="<%=request.getAttribute("updatePlayer")%>"; 
	    if(updatePlayer !="") {
	        document.getElementById("playerName").value="${updatePlayer.pName}";
	        document.getElementById("playerRealName").value="${updatePlayer.pRealName}";
			document.getElementById("playerPassword").value="${updatePlayer.password}";
			document.getElementById("playerPasswordAgain").value="${updatePlayer.password}"; 	
			document.getElementById("playerTelePhone").value="${updatePlayer.pTelePhone}";
			document.getElementById("playerAddress").value="${updatePlayer.pAddress}";
			document.getElementById("playerLocation").value="${updatePlayer.pPosition}"; 
			document.getElementById("playerBirthday").value="${updatePlayer.pBirthday}"; 	
			document.getElementById("playerIntroduction").value="${updatePlayer.pIntroduction}"; 
	    }
	    
		};
</script>

</head>

<body class="gray-bg" style="height: 800px" >
    <div class="wrapper wrapper-content  animated fadeInRight" >
        <div class="row">   
        	<div class="col-sm-2">
        	</div>     
            <div class="col-sm-8">
                <div class="ibox" >
                    <div class="ibox-content" >
                        <h3>信息管理</h3>
                       
						<p class="small"><i class="fa fa-hand-o-up"></i> 请管理您的用户信息</p>
						<br>
                        <div class="input-group">
                           <!-- <input type="text" placeholder="添加新任务" class="input input-sm form-control">
                            <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-white"> <i class="fa fa-plus"></i> 添加新任务</button>
                                </span>-->
                        </div>
                        
                       <!-- 表单的form中添加：enctype = "multipart/form-data" -->
                    <form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/updatePlayerInformation" enctype = "multipart/form-data" method="post">
						<!--<div class="modal-body">-->
							<div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">球员昵称</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="playerName" name="playerName"  placeholder="请输入球员昵称">
						    	</div>
						    </div>
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">真实姓名</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="playerRealName" name="playerRealName"  placeholder="请输入球员真实姓名">
						    </div>
						     </div>
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">密码</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="playerPassword" name="playerPassword"  placeholder="请输入球员密码">
						    	</div>
						    </div>
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">确认密码</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="playerPasswordAgain" name="playerPasswordAgain"  placeholder="请再次输入球员密码">
						    	 </div>
						    </div>
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">联系电话</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="playerTelePhone" name="playerTelePhone"  placeholder="请输入球员联系电话">
						    </div>
						     </div>
						     
						     <div class="form-group opt">  
								  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>  
									  <div class="col-sm-6">
										    <label class="radio-inline">
										  		<input type="radio" name="playerGender" id="inlineRadio1" value="男" checked> 男
											</label>
											<label class="radio-inline">
										  	<input type="radio" name="playerGender" id="inlineRadio2" value="女"> 女
									</label>
								</div>
							  </div> 	
						     
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">籍贯</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="playerAddress" name="playerAddress"  placeholder="请输入球员籍贯">
						    </div>
						     </div>
						     
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">场上位置</label>
							    <div class="col-sm-6">
							      <input type="text" class="form-control" id="playerLocation" name="playerLocation"  placeholder="请输入球员场上位置">
						    </div>
						     </div> 
						     
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">生日</label>
							    <div class="col-sm-6">
							     <input class="form-control layer-date" id="playerBirthday" name="playerBirthday" placeholder="YYYY-MM-DD" onclick="laydate({istime: true, format: 'YYYY-MM-DD'})">
                                <label class="laydate-icon"></label>
						    </div>
						     </div> 
						    
						    <div class="form-group">
							    <label for="inputEmail3" class="col-sm-2 control-label">球员简介</label>
							    <div class="col-sm-6">
									<textarea class="form-control" rows="4"  id="playerIntroduction" name="playerIntroduction">
							</textarea>
						    </div>
						     </div>
						    
						     <div class="form-group">
							    <label for="username" class="col-sm-2 control-label">球员头像</label>
							    <div class="col-sm-6">
									<input type="file" name="playerPhoto"/>
								</div>
							 </div>
						
										  
		 				<br/>
						<div >
							<button  type="submit"  class="btn btn-info">确认修改</button>
	
		 				</div>
					</form>
                        
                    </div>
                </div>
            </div>
         
			<div class="col-sm-2">
        	</div>
   


    </div>
    
  </div>  
    
        
	
    

    <!-- 全局js -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js?v=3.3.6"></script>



    <!-- 自定义js -->
    <script src="${pageContext.request.contextPath}/js/content.js?v=1.0.0"></script>
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


    <script>
        $(document).ready(function () {
            $('.contact-box').each(function () {
                animationHover(this, 'pulse');
            });
        });
    </script>

    
    

</body>

</html>
