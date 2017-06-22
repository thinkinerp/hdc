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
<script type="text/javascript" >
    var ctx = "${ctx}";	
</script>
<title>数据通上报</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/casher/css/global.css"/>
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
		<script src="${ctx}/casher/js/global.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/js/comUtil.js"></script>  
<script type="text/javascript" src="${ctx}/js/itemList.js"></script>  
</head>
	<body>
		<!-- 选择项目名称 -->
		<div  class="i-itemName">
			<div class="i-itemName-name">项目名称</div>
			<div class="i-itemName-list">
				<div id="itemName" data-select="" onclick="app.select(this,3)"></div>
			</div>
		</div>
		<!-- 项目状态 -->
		<div class="i-itemStyle">
			<div class="i-itemStyle-title">上海新天地时尚</div>
			<div class="i-itemStyle-list">
				<div>
					<p>项目经理</p>
					<p>张三</p>
					<p>项目版本</p>
					<p>1.3</p>
				</div>
				<div>
					<p>项目状态</p>
					<p>维护</p>
					<p>更新时间</p>
					<p>2017.2.27</p>
				</div>
			</div>
		</div>
		<!-- 1 -->
		<div class="i-qk">
			<div class="i-qk-title">海鼎：0</div>
			<div class="i-qk-content">
				<p>项目：0</p>
				<p>开发：0</p>
				<p>销售：0</p>
			</div>
		</div>
		<div class="i-kh">
			<div class="i-kh-title">客户</div>
			<div class="i-kh-content">0</div>
		</div>
		<style type="text/css">
			.i-qk{ display: flex; margin:0 .25rem 0 .25rem; align-items:center; }
			.i-qk-title{margin-right: .6rem; font-weight: bold; width: 2rem;}
			.i-qk-content{ flex:1;}
			.i-qk-content p{   padding: .2rem 0; border-bottom: solid 1px #eee;}
			.i-qk-content p:nth-last-child(1){ border:none;}
			.i-kh{ display: flex; border-top:solid 1px #eee; padding: .2rem .25rem 0 .25rem ;}
			.i-kh-title{margin-right: .6rem; font-weight: bold; width: 2rem;}
			.i-kh-content{ flex:1; }
		</style>
		<!-- 2个下拉 -->
		<div class="i-itemXl">		
			<div class="i-itemXl-list">
				<div data-select="项目1,项目2,项目3,项目4,项目5,3项目,3个" onclick="app.select(this,1)">上海新天地时尚</div>
			</div>
			<div class="i-itemXl-list">
				<div data-select="项目1,项目2,项目3,项目4,项目5,3项目,3个" onclick="app.select(this,1)">上海新天地时尚</div>
			</div>
		</div>
		<!-- 项目详情 -->
		<div class="i-itemDetail">
			<div class="i-itemDetail-area">
				<div class="i-itemDetail-area-title">
					<div>必胜客</div>
					<p>详情</p>
				</div>
				<div class="i-itemDetail-area-content">
					<div class="content-row">
						<p>铺位号</p>
						<p>101</p>
						<!-- i标签 on1无需安装   on2已安装   on3安装失败  on4未安装   on5未开业  on6已拆除 -->
						<p><i class="on1"></i>无需安装</p>
					</div>
					<div class="content-row">
						<p>铺位号</p>
						<p>101</p>
					</div>
					<div class="content-row">
						<p>铺位号</p>
						<p>win7/64</p>
					</div>
				</div>
			</div>
	
		</div>
		<!-- 新建抄表 -->
		<div class="i-addTable">
			<div class="icon"></div>
			<div class="text">新建抄表</div>
		</div>
		
		<script type="text/javascript">

			$(function(){
				/*
				 *	弹窗的使用方法  代码在global.js  app.alert
				*/
				//临时的方法 测试弹窗用
				//function ls(){
				//	alert("点了弹窗按钮");
				//}
				//app.alert("确定要退出吗？")			//只有确定按钮  		点击后关闭弹窗不执行任何操作		
				//app.alert("确定要退出吗？",2)			//有确定取消按钮  	点击2个按钮关闭弹窗都不执行任何操作
				//app.alert("确定要退出吗？",1,ls)		//只有确定按钮		点击后关闭弹窗并执行方法
				//app.alert("确定要退出吗？",2,ls)		//有确定取消按钮		点击确定关闭弹窗执行方法	点击取消弹窗关闭不执行方法
			})
		</script>
	</body>>
</html>