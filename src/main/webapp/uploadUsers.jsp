<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传信息</title>
<script type="text/javascript" src="/hdk/js/jquery.min.js">
</script>
</head>
<body>
    <form action="${ctx}/hdk/upload/user" method="post" id = "postForm" enctype="multipart/form-data">
		    文件：<input id ="file" type="file" name="file_upload"/><br/>
		    <select id="tableName" name ="tableName">
		    <option value="user" >用户<option/>
		    <option value="project" >项目<option/>
		    <option value="state" >状态<option/>
		    <option value="shops" >门店信息<option/>
		    </select><br/>
		    		    <input type="submit" value="上传"/><br/>
    </form>

		    <div id ="serverResponse"></div>
<script type="text/javascript">

</script>
</body>
</html>