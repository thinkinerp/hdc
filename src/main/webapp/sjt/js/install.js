/**
 * 
 */
//shopStateSeach();

 loadCombobox("installState","install",1);			
 loadCombobox("eqType","equipment_eq_style",1);					

$('#middle').bind('click',function(){
	shopStateSeach();
	 
});
var params = function() {
	  var query = {},
	    search = window.location.search.substring(1),
	    parts = search.split('&'),
	    pairs = [];

	  for (var i = 0, len = parts.length; i < len; i++) {
	    pairs = parts[i].split('=');
	    query[pairs[0]] = (pairs.length > 1 ? decodeURIComponent(pairs[1]) : null);
	  }

	  return query;
	}();
var shopStateSeach = function(){
	var shopSta = '';
	 $.ajax({ 
	 		url:domainName + '/hdk/shops/getSome',
	 		type:'get',
	 		//jsonpCallback:"shops_getSome",
	 		jsonp: "callback",
			data:{
				"proId":$('#itemName').html(),
				'shopNameLike':$('#middle').val(),
				"installStation":delAll('installState'),
				"eqType":delAll('eqType')
			},
	 		dataType:'jsonp',
	 		success:function(rs){
 				$('.i-itemDetail-area').html('');
	 			$.each(rs,function(index,item){
	 				
	 				if("无需安装" == item.installStation){
	 					shopSta = 'on1';
	 				}else if("已安装" == item.installStation){
	 					shopSta = 'on2';
	 				}else if("安装失败" == item.installStation){
	 					shopSta = 'on3';
	 				}else if("未安装" == item.installStation){
	 					shopSta = 'on4';
	 				}else if("未开业" == item.installStation){
	 					shopSta = 'on5';
	 				}else if("已拆除" == item.installStation){
	 					shopSta = 'on6';
	 				}

	 				$('.i-itemDetail-area').append(
	 						"				<div class='i-itemDetail-area-title'>" +
	 						"					<div>"+item.shopName+"("+item.shopPosition+")</div>" +
	 						(undefined == item.installId ? "" : "					<p><a href = 'javascript:void(0)' onClick='gotoModify(\""+item.installId+"\")' target='_self'>详情</a></p>")
	 						 +
	 						"				</div>" +
	 						"				<div class='i-itemDetail-area-content'>" +
	 						"					<div class='content-row'>" +
	 						"						<p>采集点编号</p>" +
	 						"						<p>"+item.eqId+"</p>" +
	 						"						<!-- i标签 on1无需安装   on2已安装   on3安装失败  on4未安装   on5未开业  on6已拆除 -->" +
	 						"						<p><i class='"+shopSta+"'></i>"+item.installStation+"</p>" +
	 						"					</div>" +
	 						"					<div class='content-row'>" +
	 						"						<p>采集接口类型</p>" +
	 						"						<p>"+(undefined == item.eqType?"":item.eqType)+"</p>" +
	 						"					</div>" +
//	 						"					<div class='content-row'>" +
//	 						"						<p>收银机操作系统</p>" +
//	 						"						<p>"+(undefined == item.cashSystem?"":item.cashSystem)+"</p>" +
//	 						"					</div>" +
	 						"				</div>"
	 					
	 				);	
	 			});
	 		},
	  		error:function(rs){
	  			console.log(rs);
	 		}
	  });	
}

var equipmentTypeSeach = function(){
	shopStateSeach();
}
var gotoModify = function(link){
	window.SYP.pageLink("新建详情",domainName + "/hdk/sjt/installDetails.html?installId=" +link+"&userNum="+params['syp_user_num']+"&userName="+params['syp_user_name'],-1);

	//location.href =  "installDetails.html?installId=" +link+"&userNum="+params['syp_user_num']+"&userName="+params['syp_user_name'];
}

var searche = function(){
	shopStateSeach();
}
