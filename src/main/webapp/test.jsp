<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
		<meta content="yes" name="apple-mobile-web-app-capable" />
		<meta content="telephone=no" name="format-detection" />
		<meta name="browsermode" content="application">
		<meta name="renderer" content="webkit">
<%@include file="/common/taglibs.jsp"%>
		<link rel="stylesheet" type="text/css" href="${ctx}/casher/css/global.css"/>
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="${ctx}/casher/js/global.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>  
<script type="text/javascript" src="${ctx}/js/comUtil.js"></script>  
<script type="text/javascript">
			var ctx = '${ctx}';
</script>
<body>
					<div class="i-addImg">
						<div class="img" id="imgShow">
							<div onclick="fullImg(0)"></div>
							<div onclick="fullImg(1)"></div> 
						</div>
						<div class="add"><input name ="fileImg" multiple="multiple" type="file" id="fileImg" onchange="getImgUrl();"/></div>
					</div>
					<br/>
					<button id ='save'>save</button>
<script type="text/javascript">
 		var imgs = [];
    	function getImgUrl(){	//本地图片浏览 base64
			var file = document.getElementById("fileImg").files;

				for(var i = 0 ; i < file.length ; i++){
					var reader = new FileReader(); 
	    			reader.readAsDataURL(file[i]); 
					reader.onload = function(e){ 
	        			addImg(this.result);
				}
				}
		}
    	function addImg(base){    //将图片存入imgs数组  如果大于2张那么删除第一张  
    		imgs.push(base)
    		if(imgs.length >= 3){
    			imgs.splice(0,1);
    		}
    		imgsShow();
    	}
    	
    	function imgsShow(){	//显示图片
    		$("#imgShow").find('div').find('img').remove();
    		for(var i = 0; i<imgs.length; i++){
    			$("#imgShow").find('div').eq(i).html('<img src="'+imgs[i]+'"/>');
    		}	
    	}
    	$('#save').bind('click',function(){
    		$.ajax({
    			url: ctx + '/install/test',
    			type:'post',
    			dataType:'json',
    			data:{
    				files:JSON.stringify(imgs)
    			},
    			success:function(res){
    				console.log(res);
    			},
    			error:function(res){
    				console.log(res);
    			}
    			
    		});
    	});
</script>
</html>