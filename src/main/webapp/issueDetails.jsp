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
				<link rel="stylesheet" href="${ctx}/casher/css/swiper.css"></link>
</head>

	<body>
		<!-- 顶部菜单  -->
		<div class="swiper-container swiper-container1">
			<div class="swiper-wrapper" id="menu">
				<div class="swiper-slide on">商铺信息</div>
				<div class="swiper-slide">问题概述</div>
				<div class="swiper-slide">问题描述</div>
				<div class="swiper-slide">附件</div>
				<div class="swiper-slide">解决方案</div>
			</div>
		</div>
		<!-- 内容区 -->
		<div class="swiper-container swiper-container2">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<ul class="g-importList">
						<li>
							<div class="g-importList-title">项目名称</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div data-select="项目1,项目2,项目3,项目4,项目5,3项目,3个" onclick="app.select(this,2)" class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">商铺名称</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div data-select="项目1,项目2,项目3,项目4,项目5,3项目,3个" onclick="app.select(this,3)" class="on">未选择</div>
								</div>
							</div>
						</li>

						<li>
							<div class="g-importList-title">商铺位置</div>
							<div class="g-importList-content">
								<div class="i-text">L1-001</div>
							</div>
						</li>
						
					</ul>
				</div>
				<div class="swiper-slide">
					<ul class="g-importList">
						<li>
							<div class="g-importList-title">问题编号</div>
							<div class="g-importList-content">
								<div class="i-text">123897123897</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">问题状态</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div data-select="项目1,项目2,项目3,项目4,项目5,3项目,3个" onclick="app.select(this,2)" class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">跟进方</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div data-select="项目1,项目2,项目3,项目4,项目5,3项目,3个" onclick="app.select(this,3)" class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">问题类型</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">跟进人</div>
							<div class="g-importList-content">
								<div class="i-import">
									<input type="text" placeholder="请输入" />
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">跟进部门</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">问题标签</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-zp">
								<p>照片</p>
								<p>照片</p>
								<p>照片</p>
							</div>
						</li>
					</ul>
				</div>
				<div class="swiper-slide">
					<ul class="g-importList">
						<li>
				    		<div class="g-importList-title">发生时间</div>
				    		<div class="g-importList-content">
				    			<div class="i-date">
									<input type="date" placeholder="未选择" onchange="app.dateVerify(this,1)"/>
								</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">提出时间</div>
				    		<div class="g-importList-content">
				    			<div class="i-date">
									<input type="date" placeholder="未选择" onchange="app.dateVerify(this,1)"/>
								</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">预计解决时间</div>
				    		<div class="g-importList-content">
				    			<div class="i-date">
									<input type="date" placeholder="未选择" onchange="app.dateVerify(this,2)"/>
								</div>
				    		</div>
				    	</li>				    	
						<li>
							<div class="g-importList-title">解决人</div>
							<div class="g-importList-content">
								<div class="i-import">
									<input type="text" placeholder="请输入" />
								</div>
							</div>
						</li>
						<li>
				    		<div class="g-importList-title">解决时间</div>
				    		<div class="g-importList-content">
				    			<div class="i-date">
									<input type="date" placeholder="未选择" onchange="app.dateVerify(this,2)"/>
								</div>
				    		</div>
				    	</li>
					</ul>
				</div>
				<div class="swiper-slide">
					<ul class="g-importList">
				    	<div class="swiper-slide">
							<div class="i-addImg">
								<div class="img" id="imgShow">
									<div onclick="app.fullImg(0)"></div>
									<div onclick="app.fullImg(1)"></div>
								</div>
								<div class="add"><input type="file" id="fileImg" onchange="app.getImgUrl();"/></div>
							</div>
						</div>
				    </ul>
				</div>
				<div class="swiper-slide">
					<div class="g-fa" contenteditable="true">
						<!-- 输入内容 -->
					</div>
				</div>
				
			</div>
		</div>
		<!-- 保存按钮 -->
		<div class="g-ok">
			<div id="save">保存</div>
		</div>
		
		<script src="${ctx}/casher/js/swiper.js" type="text/javascript" charset="utf-8"></script>
		<script>
			swiper1 = new Swiper('.swiper-container1', {	//菜单区滑块
				paginationClickable: true,
				slidesPerView: 'auto',
				freeMode: true
			});
			
			swiper2 = new Swiper('.swiper-container2', {	//内容区滑块
				autoHeight: true,
				onSlidePrevStart: function(swiper) {
					app.upStyle(swiper.activeIndex);
				},
				onSlideNextStart: function(swiper) {
					app.upStyle(swiper.activeIndex);
				}
			});
		</script>
	</body>
</html>