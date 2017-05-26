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
<%-- <script type="text/javascript" src="${ctx}/js/syp_v1.js"></script>  --%>
<script type="text/javascript" >
//window.SYP.toggleShowBanner('hidden');
    var ctx = "${ctx}";	
    var surId = "";
    var proId = '';
    var allThing = '<%= null == request.getParameter("allThing") ? "" : request.getParameter("allThing") %>';
    var allObjs = {};
    Date.prototype.Format = function(fmt)   
    { //author: meizz   
      var o = {   
        "M+" : this.getMonth()+1,                 //月份   
        "d+" : this.getDate(),                    //日   
        "h+" : this.getHours(),                   //小时   
        "m+" : this.getMinutes(),                 //分   
        "s+" : this.getSeconds(),                 //秒   
        "q+" : Math.floor((this.getMonth()+3)/3), //季度   
        "S"  : this.getMilliseconds()             //毫秒   
      };   
      if(/(y+)/.test(fmt))   
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
      for(var k in o)   
        if(new RegExp("("+ k +")").test(fmt))   
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
      return fmt;   
    }  

</script>
<title>数据通上报</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/casher/css/global.css"/>

		<script src="${ctx}/casher/js/global.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctx}/js/installDetails.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="${ctx}/casher/css/swiper.css"></link>
		<link rel="stylesheet" href="${ctx}/css/loading.css"></link>

</head>
<body>
		<!-- 顶部菜单  -->
		<div class="swiper-container swiper-container1">
			<div class="swiper-wrapper" id="menu">
				<div class="swiper-slide on">安装概述</div>
				<div class="swiper-slide">商铺信息</div>
				<div class="swiper-slide">收银机信息</div>
				<div class="swiper-slide">打印机信息</div>
				<div class="swiper-slide">采集点信息</div>
				<div class="swiper-slide">其他信息</div>
				<div class="swiper-slide">附件</div>
			</div>
		</div>
		<!-- 内容区 -->
		<div class="swiper-container swiper-container2">
			<div class="swiper-wrapper">
				<!-- 安装详情1(安装概述) -->
				<div class="swiper-slide">
					<ul class="iz-list">
						<li>
							<div class="g-importList-title">安装编号：</div>
							<div class="g-importList-content">
								<div class="i-import">
									<input onblur="checkCode(this,'安装')" id ="installCode" type="text" placeholder="请输入" />
								</div>
							</div>
						</li>
						<li>
							<div class="iz-list-title">商铺编号：</div>
							<div class="iz-list-content" id = "shopCode"></div>
						</li>
						<li>
							<div class="iz-list-title">收银机编号：</div>
							<div class="iz-list-content"  id ="cashCode" ></div>
						</li>
						<li>
							<div class="iz-list-title">打印机编号：</div>
							<div class="iz-list-content" id = "printCode"></div>
						</li>
						<li>
							<div class="iz-list-title">采集设备编号：</div>
							<div class="iz-list-content" id = "equipmentCode"></div>
						</li>
						<li>
							<div class="iz-list-title">安装状态：</div>
							<div class="iz-list-content" id = "installStation"></div>
						</li>
					</ul>

<!-- 					<div class="iz-title">
						<div>安装状态</div>
					</div>
					<div class="i-xiala">
						<div class="i-xiala-list">
							<div id ="installStation" data-select="已安装,未安装" onclick="app.select(this,1)">已安装</div>
						</div>
					</div> -->

					<div class="iz-title">
						<div>数据精准度</div>
					</div>
					<div class="i-xiala">
						<div class="i-xiala-list">
							<div id = 'installData' data-select="正确,不正确" onclick="app.select(this,1)"></div>
						</div>
					</div>
				</div>
				<!--end安装详情1(安装概述)-->
				<!-- 安装详情2(商铺信息) -->
				<div class="swiper-slide">
					<ul class="g-importList">
						<li>
							<div class="g-importList-title">项目名称</div>
							<div id ="proNameDiv" class="g-importList-content">
								<div class="i-xiala-list">
									<div id = "proName" data-select="" onclick="app.select(this,3, Window.selected)" class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">商铺名称</div>
							<div id="shopNameDiv" class="g-importList-content">
								<div class="i-xiala-list">
									<div id = "shopName" data-select="" onclick="app.select(this,3,Window.shopSelected)" class="on">未选择</div>
								</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">商铺状态</div>
							<div class="g-importList-content">
			
									<div class="i-text" id = "shopState" >未选择</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">商铺位置</div>
							<div class="g-importList-content">
								<div class="i-text" id = "shopPosition"></div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">经营分类</div>
							<div class="g-importList-content">
									<div  class="i-text" id = "shopType">未选择</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">经营次分类</div>
							<div class="g-importList-content">
									<div  class="i-text" id = 'shopSecType'>未选择</div>
							</div>
						</li>
						<li>
							<div class="g-importList-title">安装状态</div>
							<div class="g-importList-content">
								<div class="i-xiala-list">
									<div id = "installState" data-select="" onclick="app.select(this,3,onSetupState)">未选择</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<!-- end安装详情2(商铺信息) -->
				<!-- 安装详情3(收银机信息) -->
				<div class="swiper-slide">
					<ul class="g-importList">
						<li>
							<div class="g-importList-title">收银机编号</div>
							<div class="g-importList-content">
								<div class="i-import">
									<input onblur="javascript:setCashidOnInstall(this,'收银机')" id="cashId" type="text" placeholder="请输入" />
								</div>
							</div>
						</li>
						<li>
					<div class="g-importList-title">收银机操作系统</div>
				    		<div class="g-importList-content">
				    			<div class="i-xiala-list">
									<div id= "cashSystem"  data-select=""  onclick="app.select(this,3,null)" >未选择</div>
								</div>
				    		</div>	
						
						</li>
						<li>
					<div class="g-importList-title">账户收银系统品牌</div>
				    		<div class="g-importList-content">
				    			<div class="i-xiala-list">
									<div id= "cashBrand"  data-select=""  onclick="app.select(this,3,null)" >未选择</div>
								</div>
				    		</div>	
						</li>
						<li>
						<div class="g-importList-title">收银机端接口</div>
				    		<div class="g-importList-content">
				    			<div class="i-xiala-list">
									<div id= "cashPort"  data-select=""  onclick="app.select(this,3,null)" >未选择</div>
								</div>
				    		</div>		
							
						</li>
						<li>
							<div class="g-importList-title">有无驱动</div>
							<div class="g-importList-content">
								<div id="printerDriver"  class="i-choice">
									<div    class="i-choice-row">
										<div id = "t"  class="on"></div>
										<p>是</p>
									</div>
									<div class="i-choice-row">
										<div  id="f"   class="off"></div>
										<p>否</p>
									</div>
									
								</div>
							</div>
						</li>
					</ul>
				</div>
				<!-- end安装详情3(商铺信息) -->
				<!-- 安装详情4(打印机信息) -->
				<div class="swiper-slide">
					<ul class="g-importList">
				    	<li>
				    		<div class="g-importList-title">打印机编号</div>
				    		<div class="g-importList-content">
				    			<div class="i-import">
									<input id ="priId" onblur = "javascrip:setpriIdOnInstall(this,'打印机')"type="text" placeholder="请输入" />
								</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">打印机品牌型号</div>
				    		<div class="g-importList-content">
				    			<div class="i-import">
									<input id = "priBrand" type="text" placeholder="请输入" />
								</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">打印机端口</div>
				    		<div class="g-importList-content">
				    			<div class="i-xiala-list">
									<div id= "printerPort"   data-select=""   onclick="app.select(this,3,null)" >未选择</div>
								</div>
				    		</div>
				    	</li>
				    </ul>
				</div>
				<!-- end安装详情4(打印机信息) -->
				<!-- 安装详情5(采集点信息) -->
				<div class="swiper-slide">
					<ul class="g-importList">
				    	<li>
				    		<div class="g-importList-title">采集点编号</div>
				    		<div class="g-importList-content">
				    			<div class="i-import">
									<input id = "eqId" onblur="javascript:seteqIdOnInstall(this,'采集点')" type="text" placeholder="请输入" />
								</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">采集接口类型</div>
				    		<div class="g-importList-content">
				    			<div class="i-choice">
				    				<div class="i-choice-row">
				    					<div id="eqTypeHard" class="on"></div>
				    					<p>硬件</p>
				    				</div>
				    				<div  class="i-choice-row">
				    					<div id="eqTypeSoft"></div>
				    					<p>软件</p>
				    				</div>
				    			</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">采集方式</div>
				    		<div class="g-importList-content">
				    			<div class="i-xiala-list">
									<div id ="eqStyle"  data-select=""  onclick="app.select(this,3,null)">未选择</div>
								</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">软件编号/软件版本</div>
				    		<div class="g-importList-content">
				    			<div class="i-import">
									<input id = "softwareVersion" type="text" placeholder="请输入" />
								</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">安装日期</div>
				    		<div class="g-importList-content">
				    			<div class="i-date">
									<input id = "installTime" type="date" placeholder="未选择" onblur="app.dateVerify(this,1)"/>
								</div>
				    		</div>
				    	</li>
<!-- 				    	<li>
				    		<div class="g-importList-title">必须大于当前</div>
				    		<div class="g-importList-content">
				    			<div class="i-date">
									<input type="date" placeholder="未选择" onchange="app.dateVerify(this,2)"/>
								</div>
				    		</div>
				    	</li> -->
				    </ul>
				</div>
				<!-- end安装详情5(采集点信息) -->
				<!-- 安装详情6(其他信息) -->
				<div class="swiper-slide">
					<ul class="g-importList">
				    	<li>
				    		<div class="g-importList-title">客户网络情况</div>
				    		<div class="g-importList-content">
				    			<div class="i-choice">
				    				<div class="i-choice-row">
				    					<div id="installNetworkHard" class="on"></div>
				    					<p>硬件</p>
				    				</div>
				    				<div class="i-choice-row">
				    					<div id="installNetworkSoft" ></div>
				    					<p>软件</p>
				    				</div>
				    			</div>
				    		</div>
				    	</li>
				    	<li>
				    		<div class="g-importList-title">远程连接方式</div>
				    		<div class="g-importList-content">
				    			<div class="i-import">
									<input id = "installRemote" type="text" placeholder="请输入" />
								</div>
				    		</div>
				    	</li>
				    </ul>
				</div>
				<!-- end安装详情6(其他信息) -->
				<!-- 安装详情7(附件) -->
				<div class="swiper-slide">
					<div class="i-addImg">
						<div class="img" id="imgShow">
							<div onclick="app.fullImg(0)"></div>
							<div onclick="app.fullImg(1)"></div> 
						</div>
						<div class="add"><input name ="fileImg"  type="file" id="fileImg" onchange="app.getImgUrl();"/></div>
					</div>
				</div>
				<!-- 安装详情7(附件) -->
			</div>
		</div>
		<!-- 保存按钮 -->
		<div class="g-ok">
			<div id="save" >保存</div>
		</div>
		
		<script src="${ctx}/casher/js/swiper.js" type="text/javascript" charset="utf-8"></script>
		<script>
 			//var imgs = [];	//2个图片都会在这个数组里
			var files =[];
			var swiper1 = new Swiper('.swiper-container1', {	//菜单区滑块
				paginationClickable: true,
				slidesPerView: 'auto',
				freeMode: true
			});
			
			var swiper2 = new Swiper('.swiper-container2', {	//内容区滑块
				autoHeight: true,
				onSlidePrevStart: function(swiper) {
					upStyle(swiper.activeIndex);
				},
				onSlideNextStart: function(swiper) {
					upStyle(swiper.activeIndex);
				}
			});

			$("#menu div").click(function() {	//顶部菜单点击下面内容区改变
				var index = $(this).index();
				upStyle(index);
			})
			
			function upStyle(index) { //给顶部菜单添加状态
				$("#menu div").eq(index).addClass("on").siblings("div").removeClass("on");
				swiper1.slideTo(index);
				swiper2.slideTo(index, 0);
			}
						
			/*		    	function getImgUrl(){	//本地图片浏览 base64
				var file = document.getElementById("fileImg").files;

/* 	    	    file = file[0];
				var reader = new FileReader(); 
    			reader.readAsDataURL(file); 
				reader.onload = function(e){ 
        			addImg(this.result)
   				}  */
/*    				for(var i = 0 ; i < file.length ; i++){
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
	    	} */
	    	
	    	$("#save").click(function(){ //保存按钮
	    		app.alert("确定要保存吗？",2,submit)
	    	})
	    	
	    	/* function fullImg(index){	//图片全屏查看
	    		var base = imgs[index];
	    		if(base == undefined){ return; }
	    		var dom = [];
	    		dom.push('<div class="fullimg">');
	    		dom.push('<div class="but">');
	    		dom.push('<div class="fanhui"  id="fullimgClose"> < </div>');
	    		dom.push('<div class="shanchu" id="fullimgRemove"></div>');
	    		dom.push('</div>');
	    		dom.push('<div class="img"><img src="'+base+'"/></div>');
	    		dom.push('</div>');
				$('body').append(dom.join(''));
				$("#fullimgClose").click(function(){
					$('.fullimg').remove();
				})
				$("#fullimgRemove").click(function(){
					imgs.splice(index,1);
					imgsShow();
					$('.fullimg').remove();
				})
	    	} */
	    	
	    	$(".i-choice-row div").click(function(){	//单选
	    		$(this).addClass('on').parent('div').siblings('.i-choice-row').find('div').removeClass('on');
	    	})
	    	
    if('{}' != allThing && '' !=allThing){
    	
    	allObjs = JSON.parse(allThing);
		$('#proNameDiv').html(
				'<div class="g-importList-content">'+
				'<div class="i-text" id = "proName" >未选择</div>'+
		'</div>'
		);
		$('#shopNameDiv').html(
				'<div class="g-importList-content">'+
				'<div class="i-text" id = "shopName" >未选择</div>'+
		'</div>'
		);
		
		
		$('#installCode').val(isUndefined(allObjs.shop["installId"]));
		$('#shopCode').html(isUndefined(allObjs.install["shopId"]));
		$('#cashCode').html(isUndefined(allObjs.install["cashId"]));
		$('#printCode').html(isUndefined(allObjs.install["printerId"]));
		$('#equipmentCode').html(isUndefined(allObjs.install["eqId"]));
		$('#installStation').html(isUndefined(allObjs.install["installStation"]));
		$('#installData').html(isUndefined(allObjs.install["installData"]));
		
		if(!!isUndefined(allObjs.install['attachmentUrl'])){
		 files = allObjs.install['attachmentUrl'].split(',');
			
			for(var i = 0 ; i <files.length ; i ++){
				app.addImg(files[i]);
			}
			
			$('.fullimg').remove();
		}
		
		//var i = new Date(isUndefined()).Format("yyyy-MM-dd");
		
		$("#installCode").attr("readonly","readonly"); 

		
		$('#proName').html(isUndefined(allObjs.project.proName));
		$('#installRemote').val(isUndefined(allObjs.install.installRemote));
		$('#installTime').val(allObjs.install["installTime"]);
		// 门店信息		
		$('#shopName').html(isUndefined(allObjs.shop.shopName));
		$('#shopState').html(isUndefined(allObjs.shop.shopMerStation));
		$('#shopPosition').html(isUndefined(allObjs.shop.shopPosition));
		$('#shopType').html(isUndefined(allObjs.shop.shopType));
		$('#shopSecType').html(isUndefined(allObjs.shop.shopSecType));
		$('#installState').html(isUndefined(allObjs.install.installStation));
		
		//收银机信息
		$('#cashId').val(isUndefined(allObjs.cash.cashId));
		$('#cashSystem').html(isUndefined(allObjs.cash.cashSystem));
		
		$('#cashBrand').html(isUndefined(allObjs.cash.cashBrand));
		$('#cashPort').html(isUndefined(allObjs.cash.cashPort));
		if( "是" == isUndefined(allObjs.cash.printerDriver)){
			$('#f').attr('class','off');
			$('#t').attr('class','on');
		}else{
			$('#t').attr('class','off');
			$('#f').attr('class','on');
		}
		
		// 打印机
		
		$('#priId').val(isUndefined(allObjs.printer.printerId));
		$('#priBrand').val(isUndefined(allObjs.printer.printerBrand));
		$('#printerPort').html(isUndefined(allObjs.printer.printerPort));

		// 采集点
		
		$('#eqId').val(isUndefined(allObjs.equipment.eqId));
		if("硬件" == isUndefined(allObjs.equipment.eqType)){
			$('#eqTypeHard').attr("class",'on');
			$('#eqTypeSoft').attr("class",'off');
		}else{
			$('#eqTypeHard').attr("class",'off');
			$('#eqTypeSoft').attr("class",'on');
		}
		$('#eqStyle').html(isUndefined(allObjs.equipment.eqStyle));
		$('#softwareVersion').val(isUndefined(allObjs.equipment.softwareVersion));
		/* $('#installTime').val(isUndefined(allObjs.equipment.installTime)); */
		
		//其他
		if("硬件" == isUndefined(allObjs.install.installNetwork)){
			$('#installNetworkHard').attr("class",'on'	);
			$('#installNetworkSoft').attr("class",'off'	);
		}else{
			$('#installNetworkHard').attr("class",'off'	);
			$('#installNetworkSoft').attr("class",'on'	);
		}
		
		// 附件
		//$('#installNetworkHard').val(isUndefined(allObjs.equipment.installTime));
		
		
    }
	    	
	    	
		</script>
</body>
</html>