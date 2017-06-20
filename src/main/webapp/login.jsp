<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" href="/hdk/css/style.css" />
<link rel="stylesheet" media="screen" href="/hdk/css/public.css" />
<script type="text/javascript" src="/hdk/js/jquery.min.js"></script>
<title>登陆</title>
</head>
<body>
<div class="loginBox">
	<h2>后台管理员</h2>
	<div class="loginCont">
			<ul class="loginForm">
				<li>
					<input class="fullText" name="phone" id="userName" type="text" placeholder="请输入账号" />
				</li>
				<li>
					<input class="fullText" name="pass" id="pass" type="password" placeholder="请输入密码" />
				</li>
				
				<li>
					<label class="colorGrey" id="colorGrey" style="color:red"></label>
				</li>
				<!-- <li>
					<label class="colorGrey"><input type="checkbox" checked="checked" />2周内自动登录</label>
					<a class="fr colorGrey" href="#">忘记密码？</a>
				</li> -->
				<li>
					<input class="fullBtn" type="button" id="but" value="登录" /> 
				</li>
				<!-- <li class="tar">
					<label class="colorGrey"></label>
					<a class="fr colorGrey" href="#">忘记密码？</a>
				</li> -->
<%-- 				<li class="tar">
					没有账号？<a class="colorGreen" href="<%=basePath %>home/register.jsp">立即注册</a>
				</li>
 --%><!-- 				<li class="tar">
					<a class="fr colorGrey" href="/hdk/user/.jsp">忘记密码？</a>
				</li>
 -->				
			</ul>
			<script type="text/javascript">
				$(function(){
					$('#but').click(function(){
						if( $('#phone').val()=='' ||  $('#pass').val()==''){
							$('#colorGrey').text('账号密码不能为空');
							return;
						}
						$.ajax({
							url:'/hdk/user/login',
							type:"get",
							data:{
								userName:$('#userName').val(),
								userPass:$('#pass').val()
							},
							dataType:"json",
							success:function(rs){
								if("success" == rs.message){
									location.href = "/hdk/sjt/background/upload.jsp";
								}else{
									$('#colorGrey').text(rs.message);
								}
							},
							error:function(rs){
							    console.log(rs);	
							}
						});
					});				
					
					
					$('input').blur(function(){
							$('#colorGrey').text("");
					});
					
				});
			</script>
	</div>
	<!-- <h3>社交账号登录</h3>
	<ul class="ohterLogin">
		<li class="loginQQ">
			<a href="#"></a>
		</li>
		<li class="loginWeixin">
			<a href="#"></a>
		</li>
		<li class="loginWeibo">
			<a href="#"></a>
		</li> -->
	</ul>
</div>
	<div class="wap-hide" style="margin-top: 300px;"></div>

</body>
</html>