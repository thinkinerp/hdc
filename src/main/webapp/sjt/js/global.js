var app_isback=true;
var installexArr=new Array();
var surveryexArr=new Array();
document.getElementsByTagName("html")[0].style.fontSize=Math.floor(document.documentElement.clientWidth*100000/750)/1000+"px";
var imgs = [];	//2个图片都会在这个数组里
var swiper1 = '';
var swiper2 = '';
var objid,gs7selarr=[],isgs7=false;
var m_loading = {
		html:function(){
			var html = [];
	        html.push("<div class='m_load'>");
	        html.push("<div class='load2'>");
	        html.push("<span class='loading'>");
	    	html.push("<span class='bar1'></span>");
	    	html.push("<span class='bar2'></span>");
	    	html.push("<span class='bar3'></span>");
	    	html.push("<span class='bar4'></span>");
	    	html.push("<span class='bar5'></span>");
	    	html.push("<span class='bar6'></span>");
	    	html.push("<span class='bar7'></span>");
	    	html.push("<span class='bar8'></span>");
	    	html.push("<span class='bar9'></span>");
	    	html.push("<span class='bar10'></span>");
	    	html.push("<span class='bar11'></span>");
	    	html.push("<span class='bar12'></span>");
		    html.push("</span>");

	        //html.push("<span class='load_text'>加载中...</span>");
	        html.push("</div>");
	        html.push("</div>");
	        $("body").append(html.join(""));
	        $(".xxx").click(function(){
	        	m_loading.remove();
	        });

		},
		remove:function(){
			$(".m_load").remove();

		}
	}

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
	getUrlSearch:function(name){	//获取url里面的参数值 name=参数名 如：getUrlSearch("userid") 返回userid的值
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) return unescape(r[2]); return null;
	},
	select:function(obj,type,fun){ //obj(有data-select的那个标签,传this 例如 app.select(this))    type: 1只能选择   2可以选择也可以输入 输入匹配   3可以选择可以输入 输入匹配 并可选择没匹配项
		/*===隐藏原生标题栏 start1===*/
		// window.SYP.toggleShowBanner('hidden');
		/*===隐藏原生标题栏 end1===*/
			app.put = obj;
			objid=obj.id;
			app.selecttype = type;
			if(fun != undefined && fun != '' && fun != null){	
				app.selectOverFun = fun;
			}
			var bnrtitle=$(document).attr("title");
			app.listdata = $(app.put)[0].dataset.select;
			var content = $(app.put)[0].innerHTML;
			var alertTitle = $(app.put).attr('alertTitle');
			app.listdata = app.listdata.split(",");
			/*===gs7 start===*/
			if(isgs7==true &&objid=="gs7")
			{app.listdata=gs7selarr;}
		    /*===gs7 end===*/
	        var dom = [];
	        /*====弹框返回 start====*/
	        dom.push('<div class="g-select">');
	  //       dom.push('<div class="selectfix">'+bnrtitle+'<div class="icon_return" onclick="app.selectBack()"></div><div class="icon_refresh" onclick="window.location.reload(); "></div>');
			// dom.push('</div>');
			/*====弹框返回 end====*/
	//        dom.push('<div style="left:0; bottom:0; height:.8rem; width: 100%; background:white; font-size: .36rem; display: flex; justify-content: center; align-items: center;">');
	//        dom.push();
	//        dom.push('</div>');
	        if(app.selecttype != 1){	//如果类型不等于1  显示搜索框
		        dom.push('<div class="g-select-seek">');
		        dom.push('<input placeholder="'+alertTitle+'" type="text" oninput="app.selectSeek(this)"/>');
		        dom.push('<div></div>');//搜索按钮
		        dom.push('</div>');
	        }
	        dom.push('<ul class="g-select-list" id="g-select-list">');    //选项列表  选中状态给li添加class="on"
	        for(var i = 0; i<app.listdata.length; i++){
	        	if(app.listdata[i] == content){
	       			dom.push('<li class="on">'+app.listdata[i]+'</li>');
	       		}else{
	       			/*===区分单据 start===*/
	       			if(surveryexArr.length!=0)
	       			{
		       			if(obj.id=="shopName" &&(surveryexArr[i].surveyExist!=0))
		       			{dom.push('<li style="background:#eeebeb;border-bottom:1px solid #fff;">'+app.listdata[i]+'</li>');}
			       		else
		       			{dom.push('<li>'+app.listdata[i]+'</li>');}
	       		    }
	       		    else if(installexArr.length!=0)
	       			{
		       			if(obj.id=="shopName" &&(installexArr[i].installExist!=0))
		       			{dom.push('<li style="background:#eeebeb;border-bottom:1px solid #fff;">'+app.listdata[i]+'</li>');}
			       		else
		       			{dom.push('<li>'+app.listdata[i]+'</li>');}
	       		   }
	       		   else
	       			{dom.push('<li>'+app.listdata[i]+'</li>');}
	       		/*===区分单据 end===*/
	       		}
	        }
	        dom.push('</ul>');

	        //cynthia 按钮样式
	        dom.push('<div class="g-ok" style="z-index:99"><div  onclick="app.selectBack()">退出</div></div>');
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
    	/*===显示原生标题栏 start===*/
  //   	if(app_isback)
		// {window.SYP.toggleShowBanner('show');}
		/*===显示原生标题栏 end===*/
    },
    selectClick:function(){
    	var ls;
    	$("#g-select-list li").click(function(){
        	$(this).addClass('on').siblings('li').removeClass('on');
        	 ls = $(this).html();        	   
        	setTimeout(function(){
        		$(app.put).html(ls);
        		$(app.put).removeClass('on');
        		$(".g-select").remove();
		        /*===显示原生标题栏 start===*/
		  //       if(app_isback)
				// {window.SYP.toggleShowBanner('show');}
				/*===显示原生标题栏 end===*/
        		if(app.selectOverFun != undefined && app.selectOverFun != '' && app.selectOverFun != null){
					app.selectOverFun();
				}
        	},300)
        	/*===问题列表 start===*/

			if(objid=="gs7")
			{//var arrA = app.listdata.split(",");
				//arrA.remove(ls);
				removeByValue(app.listdata,ls);
				gs7selarr=app.listdata;
				isgs7=true;
			}
			var sellidex=$(this).index();			
			if(objid=="gs5" &&selectdeparr.length!=0 && selectdeparr[sellidex]!="")
				{
					$("#gs6").html(selectdeparr[sellidex]);
					$("#gs6").attr("class","i-importtxt");
					$("#gs6").parent("div").attr("class","");
					$("#gs6").unbind("click");
		       }
		      else if(objid=="gs5" && selectdeparr[sellidex]=="")
		      {$("#gs6").html('未选择');
			   $("#gs6").attr("class","on");
			   $("#gs6").parent("div").attr("class","i-xiala-list");
			    $("#gs6").click(function() {
                                app.select(this, 2, gs.selectGs6);
                            })
		      }
	      /*===问题列表 end===*/

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
		
		if((null == file) || ('' == file) || (undefined == file)){
			return ;
		}
		file = file[0];
		app.changeBlobImageQuantity(file);
//		var reader = new FileReader(); 
//		reader.readAsDataURL(file); 
//		reader.onload = function(e){ 
//			app.addImg(this.result)
//		} 
	},
	changeBlobImageQuantity:function(blob){
		//m_loading.html();
		var format = null;
		var quality  = format = ""
		format =  'image/jpeg';
		quality = 0.2; // 经测试0.9最合适
		var fr = new FileReader();
		fr.onload = function(e) {
			var dataURL = e.target.result;
			var img = new Image();
			img.onload = function() {
				var canvas = document.createElement('canvas');
				var ctx = canvas.getContext('2d');
				var oldWidth = img.width;
				var oldHeight = img.height;
				var newWidth = img.width; //window.screen.width;
				var newHeight = Math.floor(oldHeight / oldWidth * newWidth);
				canvas.width = newWidth;
				canvas.height = newHeight;
				ctx.drawImage(img, 0, 0, newWidth, newHeight);
				// ctx.drawImage(img, 0, 0);
				var newDataURL = canvas.toDataURL(format, quality);
				var newBlob = app.convertDataURLToBlob(newDataURL);
				var data = new FormData();
				if(newBlob) {
					var uploadFile = new File([newBlob], blob.name, {
						type: newBlob.type
					});
					
					if( 1048576 < uploadFile.size){
						app.alert("图片过大，无法上传",1);
					}
					
					data.append('files', newBlob, blob.name);
				}				
				$.ajax({
					url:domainName + '/hdk/image/recerverImag',
					data: data,
					cache: false,
					contentType: false,
					processData: false,
					dataType: "json",
					type: 'post',
				}).done(function(result) {
					if("success" ==result.message) {
						//alert(result.fileName);
						imgs.push(result.fileName);
						/*==== updatecynthia start====*/
						// if(imgs.length >= 3){
						// 	imgs.splice(0,1);
						// }						
						$("#imgShow").append('<div ></div>');
						/*==== updatecynthia end====*/
						app.imgsShow(uploadFile.size);
						
					} else {
						app.alert("图片上传失败",1);
					}
					//m_loading.remove();
				});
				
				canvas = null;
			};
			img.src = dataURL;
		};
		fr.readAsDataURL(blob); 
	},
	convertDataURLToBlob:function(dataURL) {
		var arr = dataURL.split(',');
		var code = window.atob(arr[1]);
		var aBuffer = new window.ArrayBuffer(code.length);
		var uBuffer = new window.Uint8Array(aBuffer);
		for(var i = 0; i < code.length; i++) {
			uBuffer[i] = code.charCodeAt(i);
		}
		var Builder = window.WebKitBlobBuilder || window.MozBlobBuilder;
		if(Builder) {
			var builder = new Builder;
			builder.append(aBuffer);
			return builder.getBlob(format);
		} else {
			// return new window.Blob([ uBuffer ]);
			return new window.Blob([aBuffer], {
				type: arr[0].match(/:(.*?);/)[1]
			});
		}
	},
	addImg:function(base){
//		imgs.push(base)
//		if(imgs.length >= 3){
//			imgs.splice(0,1);
//		}
		app.imgsShow();
	},
	imgsShow:function(picSize){	//显示图片

		$("#imgShow").find('div').hide();
		$("#imgShow").find('div').find('img').remove();
		var isIOS = (/iphone|ipad/gi).test(navigator.appVersion);
		for(var i = 0; i<imgs.length; i++){
			if( isIOS){	
				$("#imgShow").find('div').eq(i).html('<img style="-webkit-transform:rotate(90deg)" src="/hdk/upload/'+imgs[i]+'"/>');
			}else{
				$("#imgShow").find('div').eq(i).html('<img src="/hdk/upload/'+imgs[i]+'"/>');
			}
			//updatecynthia start
		    
						//updatecynthia end
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
		var isIOS = (/iphone|ipad/gi).test(navigator.appVersion);
		if( isIOS){	
			dom.push('<div class="img"><img style="-webkit-transform:rotate(90deg)"  src="/hdk/upload/'+base+'"/></div>');
		}else{
			dom.push('<div class="img"><img  src="/hdk/upload/'+base+'"/></div>');
		}
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
function removeByValue(arr, val) {
  for(var i=0; i<arr.length; i++) {
    if(arr[i] == val) {
      arr.splice(i, 1);
      break;
    }
  }
}
function chk_brand(dyjbrand,dyjxh,dyjPort,obj)
{ if((dyjbrand!=""&&dyjbrand!="未选择") || (dyjxh!=""&&dyjxh!="未选择") || (dyjPort!=""&&dyjPort!="未选择") )
				 {
				   if (form_empty({code:$(obj).val(),which:"收银机编号"}))
				   {swiper2.slideTo(2, 0, true);
				   	$("#g-popupOk").bind("click",function(){ $(obj).focus();})           			
				   	return true;}
			     }
}
function chk_print(dyjbrand,dyjxh,dyjPort,obj)
{ if((dyjbrand!=""&&dyjbrand!="未选择") || (dyjxh!=""&&dyjxh!="未选择") || (dyjPort!=""&&dyjPort!="未选择") )
				 { 
				   if (form_empty({code:$(obj).val(), which:"打印机编号"}))
				   {swiper2.slideTo(3, 0, true);
           			$("#g-popupOk").bind("click",function(){ $(obj).focus();})          
				   	return true;}
			     }
}

function chk_equipment(){
	if( ($("#eqTypeHard").attr("class")== 'on' || $("#eqTypeSoft").attr("class")== 'on' ) 
			|| ($("#eqStyle").html() == "" && $("#eqStyle").html() == "未选择" )
			|| ($("softwareVersion").html() == "未选择" &&  $("softwareVersion").html() == "")
			|| ("" == $("#installTime").val() && "未选择" == $("#installTime").val())){
		  if(form_empty({code:$("#eqId").val(), which:"采集点编号"})){
		  	 swiper2.slideTo(4, 0, true);
		  	 $("#g-popupOk").bind("click",function(){   $("#eqId").focus();}) 
			  return true ;
		  }else{
			  return false;
		  }
	}
}

$(function(){
	$("#menu div").click(function() {	//顶部菜单点击下面内容区改变
		var index = $(this).index();
		app.upStyle(index);
	})
	
//	$("#save").click(function(){ //保存按钮
//		app.alert("确定要保存吗？",2,app.saveDate)
//	})
	
	$(".i-choice-row div").click(function(){	//单选
		$(this).addClass('on').parent('div').siblings('.i-choice-row').find('div').removeClass('on');
	})
/*===返回保存===*/
issave();	
/*===返回保存 end===*/
$(".swiper-container2 .swiper-wrapper").css("min-height",$(window).height()-70);	
})
buttonFixed();
function buttonFixed()
{		 var u = navigator.userAgent;
             var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端 		   
  				var winHeight = $(window).height();   //获取当前页面高度
            $(window).resize(function(){
               var thisHeight=$(this).height();
                if(winHeight - thisHeight >50){
                    $(".g-ok,.i-addTable").hide();
                }else{
                    $(".g-ok,.i-addTable").show();
                }

            }); 
            if(isAndroid)
            {
            	$(".wrapbox").css({position:"static"});
            } 
            else
            {$(".wrapbox").css({position:"absolute"});}
 }	
  /*====cynthia返回 start1=====*/
 			var savetxt=false;
			function issave()
			{
				$("input").bind("focus",function(){
					savetxt=true;
				})	
				$(".i-text,.i-xiala-list,.i-choice-row,.i-choice-rowchk").bind("click",function(){
						savetxt=true;
				})
				return savetxt;
			}
			function appback(url)
			{
				if(issave())
				{
					app.alert('单据未保存，是否保存数据？',2,submitback);
					$("#g-popupNo").click(function(){
						//location.href=url;
						//window.SYP.pageLink("调研详情",domainName + "/hdk/sjt/surveylist.html");
						window.SYP.cleanUrlStack(domainName+"/hdk/sjt/"+url);
					})
				}
				else
				{
				//window.SYP.pageLink("调研详情",domainName + "/hdk/sjt/surveylist.html");	
					window.SYP.cleanUrlStack(domainName+"/hdk/sjt/"+url);

				}
			}
 /*====cynthia返回 end1=====*/
