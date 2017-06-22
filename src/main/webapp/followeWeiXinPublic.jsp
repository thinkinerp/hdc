<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<%@include file="/common/taglibs.jsp"%>
<script type="text/javascript" src="${ctx}/js/jweixin-1.0.0.js"></script>
<title>关注公众号</title>
</head>
<body>


<div style="font-color:red;font-size:20px">
请先关注销售数据提报公共号，长按下面的图片，然后在出现的菜单中，<a style="font-color:red">选择识别二维码</a>
</div>


<!-- https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzA3MjU1OTEzNQ==&scene=124#wechat_redirect -->
<!-- <a href="https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI3NDQ2NDI3MA==&scene=124#wechat_redirect">一点关注微信</a> -->

<img alt="长按" src="${ctx}/image/intfouc_public.jpg"  style="width:60%">
<!-- 
<script  type="text/javascript">  
    wx.config({  
        debug: true,  
        appId: '${appId}',  
        timestamp:'${timestamp}',  
        nonceStr:'${nonceStr}',  
        signature:'${signature}',  
        jsApiList : [ 'checkJsApi', 'scanQRCode' ,'onMenuShareAppMessage']  
    });//end_config  
  
    wx.error(function(res) {  
        alert("出错了：" + res.errMsg);  
    });  
	var url = 'www.baid.com';
/*     document.querySelector('#scanQRCode').onclick = function() {  
        wx.scanQRCode({  
            needResult : 1, // 默认为0，扫描结果由微信处理，1则直接返回扫描结果，  
            scanType : [ "qrCode", "barCode" ], // 可以指定扫二维码还是一维码，默认二者都有  
            success : function(res) { 
                alert("扫描成功::扫描码=" + result); 
               url  = res.resultStr; // 当needResult 为 1 时，扫码返回的结果  
            }  
        });  
    };//end_document_scanQRCode          */
    wx.ready(function() {  
        wx.checkJsApi({  
            jsApiList : ['scanQRCode','onMenuShareAppMessage'],  
            success : function(res) {  
            }  
        });  
                    wx.onMenuShareAppMessage({
                        title: '数据上报公众号', // 分享标题
                        desc: '马上来上传数据吧', // 分享描述
                        link: url, // 分享链接
                        imgUrl: '', // 分享图标
                        type: 'link', // 分享类型,music、video或link，不填默认为link
                        dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
                        success: function () { 
                            alert("分享公众好成功");
                        },
                        cancel: function () { 
                            alert("分享公众号失败");
                        }
                    });
    });//end_ready   -->
</script>  
</body>
</html>