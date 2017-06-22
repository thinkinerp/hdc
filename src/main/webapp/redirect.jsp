<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">

<%@include file="/common/taglibs.jsp" %>
<link rel="stylesheet" href="${ctx}/bootstrap-3.3.5/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/bootstrap/js/jquery-1.8.3.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${ctx}/bootstrap/js/jquery.form.js" charset="UTF-8"></script>
<script src="${ctx}/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/bootstrap/bootstrap-table/bootstrap-table.min.css">
<%-- <link rel="stylesheet" href="${ctx}/bootstrap/bootstrap-table/extensions/sticky-header/bootstrap-table-sticky-header.css"> --%>
<script src="${ctx}/bootstrap/bootstrap-table/bootstrap-table.min.js"></script>
<script src="${ctx}/bootstrap/bootstrap-table/bootstrap-table.js"></script>
<script src="${ctx}/bootstrap/bootstrap-table/bootstrap-table-locale-all.js"></script>
<script src="${ctx}/bootstrap/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<%-- <script src="${ctx}/bootstrap/bootstrap-table/extensions/sticky-header/bootstrap-table-sticky-header.min.js"></script> --%>
<script src="${ctx}/bootstrap/bootstrap-table/extensions/export/bootstrap-table-export.min.js"></script>
<script src="${ctx}/bootstrap/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
    <script src="${ctx}/js/bootstrap3/bootstrap-dialog.min.js"></script>
<script type="text/javascript" >
    var ctx = "${ctx}";	
</script>
<title>跳转</title>
</head>
<body>
<script type="text/javascript">


BootstrapDialog.show({
		title: '提示',
		message: '您的微信号（${openid}）已经与门店（${oldStoreName}）绑定，您本次扫码对应的门店为${newStoreName},是否绑定新门店？',
		buttons: [{
			label: '是',
			action: function(dialog) {
				window.location.href =ctx + '${redirectUrl}';
			}
		},{
				label: '否',
				action: function(dialog) {
					window.location.href = ctx + '/error.jsp?msg=请退出后重新扫码';
					
				}      	
			}
		]
});


</script>
</body>
</html>