/**
 * 加载下拉框选项和默认值
 */
function onlyEnglishAndDecimal(field){
	var a = /^[0-9a-zA-Z]*$/g;
	
	return a.test(field);
} 
var checkCode = function(obj,label){
	if(!onlyEnglishAndDecimal($(obj).val())){
		app.alert(label+"编码:" + $(obj).val() +",只能有数字和英文字母组成",1,null);

	}
}
var readOnly = function(id){
	$("#" + id).removeClass('on');
	$("#" + id).parent('div').css('border', 'none');
	$("#" + id).css('background', 'none');
	$("#" + id).attr('readonly', 'readonly');
}
function form_empty(config){
    if(config.code==""  )
    {
    	app.alert(config.which+":"+config.code + ",不能为空",1);
	 return true;
    }
    else if(config.which="商铺名称")
    {if(config.code=="未选择"  )
	{app.alert("请选择"+config.which,1);
	 return true;
	}
    }
    else
    {return false;}
}
function codeUnique(config){
	var dtd = $.Deferred(); 
	
	// if('' == config.code ){
	// 	app.alert(config.which+":"+config.code + ",不能为空",1);
	// 	return dtd.promise();
	// }
	if(!onlyEnglishAndDecimal(config.code)){
		app.alert("编码:" + config.code +",只能有数字和英文字母组成",1);
		return dtd.promise();
		
	}
	if(''!= config.code ){
				$.ajax({	
					   url: domainName + "/hdk/problem/codeUnique",
					   data:{
					   	      tableName:config.tableName
					         ,codeField:config.codeField
					         ,code:config.code
					   },
					   type: "get",
					   dataType:"jsonp",
					   jsonp:"callback",
					   success:function(data){
						   var i = data[0];
						   if(i.count > 0 ){
							   app.alert(config.which+":"+config.code+"重复，请重新编码",1);
						   }else{
							   dtd.resolve();
						   }
					   },
					   error:function(rs){
					   	console.log(rs);
					   }				   
				});
	}
				return dtd.promise();
}


 function loadCombobox(id , table,isAll){
	 var time = (new Date().getTime());
	 $.ajax({ 
		 url: '/hdk/state/getSome',
		 type:'get',
		 data:{
				'ownerTable':table,
				'time':time,
				'isAll':isAll
		 },
	 		//jsonpCallback:"state_"+time+"_getSome",
	 		jsonp: "callback",
		 dataType:'jsonp',
		 success:function(rs){
			 var str = '';
			 var i = 0 ;
			 $.each(rs,function(index,item){
				 
				 if(0  == i){
					 i++;
					 str = str + item.staName;
				 }else{
					 str = str + ","+item.staName;
				 }
				 
				 if(undefined!=item.isDefault&& 1==item.isDefault){
					 if($('#'+id).is('div')){
						 $('#'+id).html(item.staName);
					 }else if($('#'+id).is('input')){
						 $('#'+id).val(item.staName);
					 }
				 }
				 
			 });
			 $('#'+id).attr("data-select",str);
			 state_getSome = null ;
		 },
		 error:function(rs){
		 }
	 });	
 }

 function isUndefined( v ){
	   return (undefined == v?"":v);
}
 
function setValue(id,value){
	 if($('#'+id).is('div')){
		 $('#'+id).html(value);
	 }else if($('#'+id).is('input')){
		 $('#'+id).val(value);
	 }	
}

function getValue(id){
	 if($('#'+id).is('div')){
		return ($('#'+id).html() != $('#'+id).attr('defaultVal'))?$('#'+id).html():"";
	 }else if($('#'+id).is('input')){
		 return ($('#'+id).val() != $('#'+id).attr('defaultVal'))?$('#'+id).val():"";
	 }	
}

 function delAll(id){
	 
	 var exclude = [];
	 
	 exclude.push("全部");
	 exclude.push("安装状态");
	 exclude.push("接口类型");
	 
	 if($('#'+id).is('div')){
		 

			 if( -1 != $.inArray($('#'+id).html(),exclude) ){
				 return '';
			 }else{
				return  ($('#'+id).html() != $('#'+id).attr('defaultVal'))?$('#'+id).html():"";
			 }
		 
		 
	 }else if($('#'+id).is('input')){
		 
		 if(-1 != $.inArray($('#'+id).val(),exclude)){
				 return ''; 
		 }else{
			 return ($('#'+id).html() != $('#'+id).attr('defaultVal'))?$('#'+id).html():"";
		 }
	 }
 }
 
 
 function contains( needle ,list){

	   $.each( list , function(index,i) {
	     if (i == needle) return true;
	   });
	   return false;
 }
 function dellExist(ls1 , ls2){
	 
	 for(var i = 0 ; i < ls1.length ; i++){
		 if($.inArray(ls1[i],ls2)!=-1){
			 ls1.splice(i,1);
		 }
	 }
	 return ls1;
 }
 
 function ajaxLoading(){
	 $('body').append(
	'<div id="loading" class="loading"><img src="/hdk/image/1111.gif" alt=""/>正在加载数据,请稍候...</div>'		 
	 );
	 $("#loading").show();	 
}
function ajaxLoadEnd(){
	$("#loading").hide();    
	$("#loading").remove();    
	
	}
 