<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- BEGIN HEADER start-->
<div class="header navbar navbar-inverse navbar-fixed-top">
<div class="navbar-inner">
<div class="container-fluid">
  <a class="brand" href="index.html">
          数据通管理平台
    </a>
  <ul class="nav pull-right">
    <!-- BEGIN USER LOGIN DROPDOWN -->
          <li class="dropdown user">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
            <i class="icon-user"></i>
            <span class="username">用户管理</span>
            <i class="icon-angle-down"></i>
            </a>
            <ul class="dropdown-menu">
              <li><a href="newuser.html"><i class="icon-user"></i> 新建账号</a></li>             
              <li><a href="changepwd.html"><i class="icon-lock"></i> 修改密码</a></li>
              <li><a href="/hdk/user/logout"><i class="icon-key"></i> 退出</a> </li>
            </ul>
          </li>
          <!-- END USER LOGIN DROPDOWN -->
   </ul>
</div>
</div>
</div>
<!-- BEGIN HEADER end-->