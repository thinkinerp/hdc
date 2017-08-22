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
      <li  id="sub_user" class="start"> <a href="javascript:void(0)"> <i class="icon-user"></i> <span class="title">用户管理</span> <span class="arrow"></span></a> 
            <ul class="sub-menu">
            <li id="sub_newuser"><a href="newuser.html">新建账号</a></li>
            <li id="sub_pwd"><a href="changepwd.html">修改密码 </a></li>
            </ul>
      </li>
      <li id="sub_upload"><a href="upload.jsp"> <i class="icon-home"></i> <span class="title">基本信息导入</span> </a> </li>
      <li id="sub_report"> <a href="javascript:void(0)"> <i class="icon-th"></i> <span class="title">报表管理</span> <span class="arrow"></span></a> 
            <ul class="sub-menu">
            <li id="sub_report1"><a href="report1.jsp">项目报表 </a></li>
            <li id="sub_report2"><a href="report2.jsp">安装报表 </a></li> 
            <li id="sub_report3"><a href="report3.jsp">问题报表 </a></li>
            </ul>
      </li>
    </ul>    
    <!-- END SIDEBAR MENU --> 
  </div>
