document.getElementsByTagName("html")[0].style.fontSize=Math.floor(document.documentElement.clientWidth*100000/750)/1000+"px";
var imgs = [];	//2个图片都会在这个数组里
var swiper1 = '';
var swiper2 = '';
var app ={
	listdata:'', //选择列表数据
	put:'', 	//选择后要显示的位置
	selecttype:'',	//选择类型   1只能选择   2可以选择也可以输入 输入匹配   3可以选择可以输入 输入匹配 并可选择没匹配项
	selectOverFun:'',  //选择后要执行的方法
	alert:function(msg,style,fun){ //消息内容    状态(1为只有确定按钮 2为是否按钮)  fun点确定后要执行的方法
		var html = [];
		if(undefined != $('#g-popup').attr("id")){
			return;
		}	
		html.push('<div class="g-popup" id="g-popup">');
		html.push('<div class="g-popup-main">');
		html.push('<div class="g-popup-main-title">提示</div>');
		html.push('<div class="g-popup-main-msg">'+msg+'</div>');
		html.push('<div class="g-popup-main-but">');
		if(style == 1 || style == undefined){
			html.push('<div class="on3" id="g-popupOk">确认</div>');	
		}else if(style == 2){
			html.push('<div class="on1" id="g-popupNo">否</div>');
			html.push('<div class="on2" id="g-popupOk">是</div>');
		}
		html.push('</div>');
		html.push('</div>');
		html.push('</div>');
		$("body").append(html.join(''));
		$("#g-popupOk").click(function(){
			$("#g-popup").remove();		
			if(fun != undefined){
				fun();
			}
		})
		$("#g-popupNo").click(function(){
			$("#g-popup").remove();
		})
	},
	select:function(obj,type,fun){ //obj(有data-select的那个标签,传this 例如 app.select(this))    type: 1只能选择   2可以选择也可以输入 输入匹配   3可以选择可以输入 输入匹配 并可选择没匹配项
		app.put = obj;
		app.selecttype = type;
		if(fun != undefined && fun != '' && fun != null){	
			app.selectOverFun = fun;
		}
		app.listdata = $(app.put)[0].dataset.select;
		var alertTitle = $(app.put).attr('alertTitle');
		var content = $(app.put)[0].innerHTML;
		app.listdata = app.listdata.split(",");
        var dom = [];
        dom.push('<div class="g-select">');
        dom.push('<div style="left:0; bottom:0; height:.8rem; width: 100%; background:white; font-size: .36rem; display: flex; justify-content: center; align-items: center;">');
        dom.push(alertTitle);
        dom.push('</div>');
        
        
        if(app.selecttype != 1){	//如果类型不等于1  显示搜索框
	        dom.push('<div class="g-select-seek">');
	        dom.push('<input placeholder="请输入" type="text" oninput="app.selectSeek(this)"/>');
	        dom.push('<div></div>');//搜索按钮
	        dom.push('</div>');
        }
        dom.push('<ul class="g-select-list" id="g-select-list">');    //选项列表  选中状态给li添加class="on"
        for(var i = 0; i<app.listdata.length; i++){
        	if(app.listdata[i] == content){
       			dom.push('<li class="on">'+app.listdata[i]+'</li>');
       		}else{
       			dom.push('<li>'+app.listdata[i]+'</li>');
       		}
        }
        dom.push('</ul>');
        dom.push('<div class="g-select-back" onclick="app.selectBack()">退出</div>');
        dom.push('</div>');
        $("body").append(dom.join(''));
        app.selectClick();     
    },
    selectSeek:function(obj){
    	var con = $(obj).val();		//输入的内容
    	var dom = [];
    	if(app.selecttype == 3){	//可以选择可以输入 输入匹配 并可选择没匹配项
    		dom.push('<li>'+con+'</li>');
    	}
    	if(con != '' && con != null && con != undefined){	//输入内容不为空		显示匹配的数据
    		for(var i = 0; i<app.listdata.length; i++){
				if(app.listdata[i].indexOf(con) >= 0 ){
				    dom.push('<li>'+app.listdata[i]+'</li>');
				}
			} 
    	}else{	//为空显示所有数据
    		for(var i = 0; i<app.listdata.length; i++){
	       		dom.push('<li>'+app.listdata[i]+'</li>');
	        }   		
    	}
    	$("#g-select-list").html(dom.join(''));
    	app.selectClick();
    },
    selectBack:function(){
    	$(".g-select").remove();
    },
    selectClick:function(){
    	$("#g-select-list li").click(function(){
        	$(this).addClass('on').siblings('li').removeClass('on');
        	var ls = $(this).html();
        	setTimeout(function(){
        		$(app.put).html(ls);
        		$(app.put).removeClass('on');
        		$(".g-select").remove();
        		if(app.selectOverFun != undefined && app.selectOverFun != '' && app.selectOverFun != null){
					app.selectOverFun();
				}
        	},300)
        })
    },
    dateVerify:function(obj,type){	//1为不能选择大于当前日期    2为必须大于当前日期
    	var selectDate = $(obj).val();
    	selectDate = new Date(selectDate).getTime();
		var myDate = new Date();
		myDate = myDate.getTime(myDate);    
		if(type == 1 && selectDate > myDate){
			app.alert('选择日期不能大于当前日期');
			$(obj).val('');
		}
		if(type == 2 && selectDate < myDate){
			app.alert('选择日期必须大于当前日期');
			$(obj).val('');
		}
    },
	saveDate:function(){
		alert('确定保存数据成功！');
	},
	upStyle:function(index){
		$("#menu div").eq(index).addClass("on").siblings("div").removeClass("on");
		swiper1.slideTo(index);
		swiper2.slideTo(index, 0);
	},
	getImgUrl:function(){
		var file = document.getElementById("fileImg").files;
		file = file[0];
		var reader = new FileReader(); 
		reader.readAsDataURL(file); 
		reader.onload = function(e){ 
			app.addImg(this.result)
		} 
	},
	addImg:function(base){
		imgs.push(base)
		if(imgs.length >= 3){
			imgs.splice(0,1);
		}
		app.imgsShow();
	},
	imgsShow:function(){	//显示图片
		$("#imgShow").find('div').find('img').remove();
		for(var i = 0; i<imgs.length; i++){
			$("#imgShow").find('div').eq(i).html('<img src="'+imgs[i]+'"/>');
			$("#imgShow").find('div').eq(i).show();
		}	
	},
	fullImg:function(index){	//图片全屏查看
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
			app.imgsShow();
			$('.fullimg').remove();
		})
	},
	upInstallList:function(){
		var val = $(app.put).html();	//选择的值
		alert(val);
		// ajax
		// if ajax.result = success{
		//		
		// }
	},
	indexSeek:function(){
		alert('搜索')
	},
	selectItems:function(obj){
		var dom = [];
		if(obj.disabled){
			dom.push('<section><input disabled type="text" placeholder="'+obj.placeholder+'" value="'+obj.value+'" />');
		}else{
			dom.push('<section><input type="text" placeholder="'+obj.placeholder+'"  value="'+obj.value+'"/>');
		}
		dom.push('<p></p>');
		dom.push('</section>');
		dom.push('<ul class="i-itemName-list-drop">');
		for(var i = 0; i<obj.list.length; i++){
			dom.push('<li>'+obj.list[i]+'</li>');
		}
		dom.push('</ul>');
		$('#'+obj.id).append(dom.join(''));
		$('#'+obj.id).find('section').click(function(){
			event.stopPropagation();
			$('#'+obj.id).find('input').focus();
			$('#'+obj.id).find('ul').show();
			$('html,body').css('height','100%');
			$('html,body').css('overflow','hidden');
			app.dropHide();
		})
		$('#'+obj.id).find('li').click(function(){
			$('#'+obj.id).find('input').val($(this).html());
			$('#'+obj.id).find('ul').hide();
			$('html,body').css('height','auto');
			$('html,body').css('overflow','visible');
		})
	},
	dropHide:function(){
		$('body').click(function(){
			$('.i-itemName-list-drop').hide();
			$('html,body').css('height','auto');
			$('html,body').css('overflow','visible');
		})
	}
	
}

$(function(){
	$("#menu div").click(function() {	//顶部菜单点击下面内容区改变
		var index = $(this).index();
		app.upStyle(index);
	})
	
	$("#save").click(function(){ //保存按钮
		app.alert("确定要保存吗？",2,app.saveDate)
	})
	
	$(".i-choice-row div").click(function(){	//单选
		$(this).addClass('on').parent('div').siblings('.i-choice-row').find('div').removeClass('on');
	})
})