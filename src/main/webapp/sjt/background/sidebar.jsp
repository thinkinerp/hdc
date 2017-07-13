<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


  <div class="page-sidebar nav-collapse collapse"> 
      <h3 style="color:white">欢迎登陆：${sessionScope.userInfo.userName}</h3> 
    <!-- BEGIN SIDEBAR MENU -->
    
    <ul class="page-sidebar-menu">
      <li> 
        
        <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
        
        <div class="sidebar-toggler hidden-phone"></div>
        
        <!-- BEGIN SIDEBAR TOGGLER BUTTON --> 
        
      </li>
      <li class="start "> <a href="upload.jsp"> <i class="icon-th"></i> <span class="title">基本信息导入</span> </a> </li>
      <li class="start "> <a href="report1.jsp"> <i class="icon-th"></i> <span class="title">项目报表</span> </a> </li>
    </ul>    
    <!-- END SIDEBAR MENU --> 
  </div>
