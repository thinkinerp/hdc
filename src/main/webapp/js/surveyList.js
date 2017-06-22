/**
 * 加载初始化的项目
 */
//loadCombobox('itemName','project');
//loadCombobox('itemName','project');
//loadCombobox('itemName','project');


var onItemNameSelected = function(){
	
	
	
	$.ajax({ 
		url:ctx + '/project/getSome',
		type:'get',
		data:{

		},
		dataType:'json',
		success:function(rs){
			var items = [];
			$.each(rs , function(index,item){
				items.push(item.proName);
			});
			$('#itemName').attr('data-select',items.join(","));	
		
		},
		error:function(rs){
			
		}
	});
	 $.ajax({ 
	 		url:ctx + '/project/getSome',
	 		type:'get',
			data:{
				isLast:1
					},
	 		dataType:'json',
	 		success:function(rs){
					
	 			//加载最后一次选择的项目信息
	 			 $(".i-itemStyle-title").html(rs[0].proName);
	 			$('#itemName').html(rs[0].proName);
				$('.i-itemStyle-list').html(
						"				<div>" +
						"					<p>项目经理</p>" +
						"					<p>"+rs[0].proManagerPro+"</p>" +
						"					<p>项目版本</p>" +
						"					<p>"+rs[0].proEdition+"</p>" +
						"				</div>" +
						"				<div>" +
						"					<p>项目状态</p>" +
						"					<p>"+rs[0].proStation+"</p>" +
						"					<p>更新时间</p>" +
						"					<p>"+rs[0].updatedAt+"</p>" +
						"				</div>"
				);
	 		},
	  		error:function(rs){
	 		}
	  });		
}

onItemNameSelected();

var loadSurvey = function(){
	var shopSta = '';
	 $.ajax({ 
	 		url:ctx + '/survey/getList',
	 		type:'get',
			data:{
				"proId":$('#itemName').html(),
				'shopName':$('#middle').val(),
				"installStation":delAll('installState' ),
				"eqType":delAll('eqType')
			},
	 		dataType:'json',
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
	 						(undefined == item.installId ? "" : "					<p><a  href = 'javascript:void(0)' onClick='gotoModify("+item.installId+")' target='_self'>详情</a></p>")
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
	 		}
	  });	
	
}
var gotoModify = function(link){
	window.SYP.showAlertAndRedirectWithCleanStack("温馨提示", "创建成功",domainName + "/survey/gotoModify?surveyId=" +link);
	
}
