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
		<link rel="stylesheet" type="text/css" href="${ctx}/casher/css/global.css"/>
		<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>

<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>  
<script type="text/javascript" src="${ctx}/js/comUtil.js"></script>  
<title>数据通上报</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/casher/css/global.css"/>

		<script src="${ctx}/js/surveyDetails.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/casher/js/global.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/js/surveyList.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="${ctx}/casher/css/swiper.css"></link>
</head>
<body>
		<!-- 顶部菜单  -->
		<div class="swiper-container swiper-container1">
			<div class="swiper-wrapper" id="menu">
				<div class="swiper-slide on">调研概述</div>
				<div class="swiper-slide">商铺信息</div>
				<div class="swiper-slide">收银机信息</div>
				<div class="swiper-slide">打印机信息</div>
				<div class="swiper-slide">其他信息</div>
				<div class="swiper-slide">附件</div>
			</div>
		</div>
		<!-- 内容区 -->
		<div class="swiper-container swiper-container2">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<ul class="iz-list">
						<li>
							<div class="iz-list-title">调研编号：</div>
							<div class="iz-list-content"></div>
						</li>
						<li>
							<div class="iz-list-title">商铺编号：</div>
							<div class="iz-list-content"></div>
						</li>
						<li>
							<div class="iz-list-title">收银机编号：</div>
							<div class="iz-list-content"></div>
						</li>
						<li>
							<div class="iz-list-title">打印机编号：</div>
							<div class="iz-list-content"></div>
						</li>
					</ul>
				</div>
				<div class="swiper-slide">
					<ul class="g-importList">
						<li>
							<div class="g-importList-title">项目名称</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div data-select="" onclick="app.select(this,2,onSelectedProject)" class="on"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">商铺名称</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div id ="shopName" data-select=""  onclick="app.select(this,3,onSelectedProject)" class="on"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">商铺状态</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div id = "shopStation" class="on"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">商铺位置</div>
							<div class="g-importList-content">
								<div id ="shopPostion" class="i-text">L1-001</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">经营分类</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div id ="" class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">经营次分类</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div class="on">未选择</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="swiper-slide">
					<ul class="g-importList">
						<li>
							<div class="g-importList-title">收银机编号</div>
							<div class="g-importList-content">
								<div class="i-import">
									<input type="text" placeholder="请输入" />
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">收银机操作系统</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div class="on">未选择</div>									
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">账户收银系统品牌</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">收银机端接口</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">有无驱动</div>
							<div class="g-importList-content">
								<div class="i-choice">
									<div class="i-choice-row">
										<div class="on"></div>
										<p>是</p>
									</div>
									<div class="i-choice-row">
										<div></div>
										<p>否</p>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="swiper-slide">
					<ul class="g-importList">
				    	<li>
				    		<div class="g-importList-title">打印机编号</div>
				    		<div class="g-importList-content">
				    			<div class="i-import">
									<input type="text" placeholder="请输入" />
								</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">打印机品牌型号</div>
				    		<div class="g-importList-content">
				    			<div class="i-import">
									<input type="text" placeholder="请输入" />
								</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">打印机端口</div>
				    		<div class="g-importList-content">
				    			<div class="i-xiala-list">
									<div class="on">未选择</div>
								</div>
				    		</div>
				    	</li>
				    </ul>
				</div>
				<div class="swiper-slide">
					<ul class="g-importList">
				    	<li>
				    		<div class="g-importList-title" style="width: 3rem;">客户网络情况</div>
				    		<div class="g-importList-content">
				    			<div class="i-choice">
				    				<div class="i-choice-row">
				    					<div class="on"></div>
				    					<p>外网</p>
				    				</div>
				    				<div class="i-choice-row">
				    					<div></div>
				    					<p>wifi</p>
				    				</div>
				    			</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title" style="width: 3rem;">空余电源插口情况</div>
				    		<div class="g-importList-content">
				    			<div class="i-choice">
				    				<div class="i-choice-row">
				    					<div class="on"></div>
				    					<p>有</p>
				    				</div>
				    				<div class="i-choice-row">
				    					<div></div>
				    					<p>无</p>
				    				</div>
				    			</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title" style="width: 3rem;">租户是否有会员系统</div>
				    		<div class="g-importList-content">
				    			<div class="i-choice">
				    				<div class="i-choice-row">
				    					<div class="on"></div>
				    					<p>是</p>
				    				</div>
				    				<div class="i-choice-row">
				    					<div></div>
				    					<p>否</p>
				    				</div>
				    			</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title" style="width: 3rem;">会员信息是否小票上体现</div>
				    		<div class="g-importList-content">
				    			<div class="i-choice">
				    				<div class="i-choice-row">
				    					<div class="on"></div>
				    					<p>是</p>
				    				</div>
				    				<div class="i-choice-row">
				    					<div></div>
				    					<p>否</p>
				    				</div>
				    			</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title" style="width: 3rem;"> </div>
				    		<div class="g-importList-content">
				    			<div class="i-import">
									<input type="text" placeholder="请输入" />
								</div>
				    		</div>
				    	</li>
				    </ul>
				</div>
				<div class="swiper-slide">
					<div class="i-addImg">
						<div class="img" id="imgShow">
							<div onclick="app.fullImg(0)"></div>
							<div onclick="app.fullImg(1)"></div>
						</div>
						<div class="add"><input type="file" id="fileImg" onchange="app.getImgUrl();"/></div>
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