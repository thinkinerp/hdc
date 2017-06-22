<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->

<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->

<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- BEGIN HEAD -->

<head>
<meta charset="utf-8" />
<title>上传信息</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />

<!-- BEGIN GLOBAL MANDATORY STYLES -->

<link href="media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<link href="media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="media/css/style-metro.css" rel="stylesheet" type="text/css"/>
<link href="media/css/style.css" rel="stylesheet" type="text/css"/>
<link href="media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->

<link rel="stylesheet" type="text/css" href="media/css/select2_metro.css" />
<link rel="stylesheet" type="text/css" href="media/css/chosen.css" />

<!-- END PAGE LEVEL STYLES -->

<!--<link rel="shortcut icon" href="media/image/favicon.ico" />-->
<style>
.page-header-fixed .page-container {
	margin-top: 0px;
}
.btn.blue {
	width: 100px;
}
</style>
</head>

<!-- END HEAD -->

<!-- BEGIN BODY -->
<body class="page-header-fixed">


<div class="page-container row-fluid"> 
  
  <!-- BEGIN SIDEBAR -->
  
  <div class="page-sidebar nav-collapse collapse"> 
      <h3 style="color:white">欢迎登陆：${sessionScope.userInfo.userName}</h3> 
    <!-- BEGIN SIDEBAR MENU -->
    
    <ul class="page-sidebar-menu">
      <li> 
        
        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
        
        <div class="sidebar-toggler hidden-phone"></div>
        
        <!-- BEGIN SIDEBAR TOGGLER BUTTON --> 
        
      </li>
      <li class="start "> <a href="upload.html"> <i class="icon-home"></i> <span class="title">基本信息导入</span> </a> </li>
    </ul>
    
    <!-- END SIDEBAR MENU --> 
    
  </div>
  
  <!-- END SIDEBAR --> 
  
  <!-- BEGIN PAGE -->
  
  <div class="page-content"> 
    
    <!-- BEGIN SAMPLE PORTLET CONFIGURATION MODAL FORM-->

    <div id="portlet-config" class="modal hide">
      <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button"></button>
        <h3>portlet Settings</h3>
      </div>
      <div class="modal-body">
        <p>Here will be a configuration form</p>    sdfasefas
      </div>
    </div>
    
    <!-- END SAMPLE PORTLET CONFIGURATION MODAL FORM--> 
    
    <!-- BEGIN PAGE CONTAINER-->
    
    <div class="container-fluid"> 
      
      <!-- BEGIN PAGE HEADER-->
      
      <div class="row-fluid">
        <div class="span12"> 
          
          <!-- BEGIN STYLE CUSTOMIZER -->
          <div style="float:right ">
         <a href="/hdk/user/logout"><h2>退出</h2></a> 
          <!-- END BEGIN STYLE CUSTOMIZER -->
          </div>
          <h3 class="page-title"> 基本信息导入 <small></small> </h3>
          
        </div>
      </div>
      
      <!-- END PAGE HEADER--> 
      
      <!-- BEGIN PAGE CONTENT-->

      <div class="row-fluid">
        <div class="span12">
          <div class="portlet box blue" id="form_wizard_1">
            <div class="portlet-title">
              <div class="caption"> <i class="icon-reorder"></i>上传<span class="step-title"></span> </div>
              <div class="tools hidden-phone"> <a href="javascript:;" class="collapse"></a> </div>
            </div>
            <div class="portlet-body form">
              <form action="/hdk/upload/user" class="form-horizontal" method="post" id ="postForm" enctype="multipart/form-data">
                <div class="form-wizard">
                  <div id="bar" class="progress progress-success progress-striped">
                    <div class="bar"></div>
                  </div>
                  <div class="tab-content">
                    <div class="alert alert-error hide">
                      <button class="close" data-dismiss="alert"></button>
                      You have some form errors. Please check below. </div>
                    <div class="alert alert-success hide">
                      <button class="close" data-dismiss="alert"></button>
                      Your form validation is successful! </div>
                    <div class="tab-pane active" id="tab1">
                      <div class="control-group">
                        <label class="control-label">文件<span class="required">*</span></label>
                        <div class="controls">
                          <input id ="file" type="file" name="file_upload"  class="span6 m-wrap" />
                          <span class="help-inline">请上传文件</span> </div>
                      </div>
                      <div class="control-group">
                        <label class="control-label">选择<span class="required">*</span></label>
                        <div class="controls">
                          <select id="tableName" name ="tableName"  class="span6 m-wrap" >
                            <option value="user" >用户
                            </option>
                            <option value="project" >项目
                            </option>
                            <option value="state" >状态
                             </option>
                            <option value="shops" >门店信息
                             </option>
                          </select>
                          <span class="help-inline">请选择</span> </div>
                      </div>
                    </div>
                  </div>
                  <div class="form-actions clearfix">
                    <input type="submit" class="btn blue button-next" value="上传"/>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      
      <!-- END PAGE CONTENT--> 
      
    </div>
    
    <!-- END PAGE CONTAINER--> 
    
  </div>
  
  <!-- END PAGE --> 
  
</div>

<!-- END CONTAINER --> 

<!-- BEGIN FOOTER -->

<div class="footer">
  <div class="footer-inner">     
    <!--2017 &copy; --> 
    
  </div>
  <div class="footer-tools"> <span class="go-top"> <i class="icon-angle-up"></i> </span> </div>
</div>
<!-- END FOOTER --> 
<script src="media/js/jquery-1.10.1.min.js" type="text/javascript"></script> 
<script src="media/js/bootstrap.min.js" type="text/javascript"></script> 
<script src="media/js/app.js"></script> 
<!-- END PAGE LEVEL SCRIPTS --> 
<script>

		$(document).ready(function() {       
		   App.init();
		   $(".page-content").css("height",$(window).height());
		});

	</script> 

<!-- END JAVASCRIPTS -->

</body>

<!-- END BODY -->
</html>