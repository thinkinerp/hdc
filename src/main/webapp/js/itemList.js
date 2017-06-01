/**
 * 
 */
$(function(){
	search();
	
	$('#indexSeek').click(function(){//点击搜索图标
		search();
	});
	$("#proName").keydown(function(e) { //输入完按键盘确定
        if (e.keyCode == 13) {  
        	search();
	       }  
	    });
	var time =  (new Date().getTime());
	$.ajax({
		url:'/hdk/project/getCount',
		type:'get',
		dataType:'json',
		success:function(rs){
			
			$('.i-itemStyle-list').html('');	
			$.each(rs,function(index,item){
				$('.i-itemStyle-list').append(
				"				<div>" +
				"					<p>"+ item.count_type+"项目</p>" +
				"					<p>"+item.pro_count+"</p>" +
				"				</div>"
		);				
//				if('all' == item.count_type){
//					$('.i-itemStyle-list').append(
//							"				<div>" +
//							"					<p>合计项目</p>" +
//							"					<p>"+item.pro_count+"</p>" +
//							"				</div>"
//					);	
//				}else if('实施' == item.count_type){
//					$('.i-itemStyle-list').append(
//							"				<div>" +
//							"					<p>实施项目</p>" +
//							"					<p>"+item.pro_count+"</p>" +
//							"				</div>"
//					);	
//				}else if('待启动' == item.count_type){
//					$('.i-itemStyle-list').append(
//							"				<div>" +
//							"					<p>待启动项目</p>" +
//							"					<p>"+item.pro_count+"</p>" +
//							"				</div>"
//					);	
//				}else if('维护' == item.count_type){
//					$('.i-itemStyle-list').append(
//							"				<div>" +
//							"					<p>维护项目</p>" +
//							"					<p>"+item.pro_count+"</p>" +
//							"				</div>"	
//					);	
//				}else if('停止' == item.count_type){
//					$('.i-itemStyle-list').append(
//							"				<div>" +
//							"					<p>停止项目</p>" +
//							"					<p>"+item.pro_count+"</p>" +
//							"				</div>"	
//					);	
//				}
			});
			
		},
		error:function(rs){
			
		}
	});
})

var search = function(){
	var time =  (new Date().getTime());
	 $.ajax({ 
	 		url: '/hdk/project/getSome',
	 		type:'get',
			data:{
					proNameLike:$('#proName').val(),
					time:time
					},
	 		dataType:'jsonp',
	 		jsonpCallback:"project_"+time+"_getSome",
	 		jsonp: "callback",
	 		success:function(rs){

				 $('.i-itemDetail').html('');
	  			 $.each(rs,function(index,item){
	  				 $('.i-itemDetail').append(
	  						 "			<div class='i-itemDetail-area'>" +
	  						 "<div class='i-itemDetail-area-title'>" +
	  						"					<div>"+item.proName+"</div>" +
	  						"					<p><a href='javascript:void(0)' onclick='gotoModify(\""+item.proId+"\")'>详情</a></p>" +
	  						"				</div>"+
	  						 "<div class='i-itemDetail-area-content'>"+
	  						"					<div class='content-row'>" +
	  						"						<p>项目经理</p>" +
	  						"						<p>"+item.proManagerPro+"</p>" +
	  						"					</div>" +
	  						"					<div class='content-row'>" +
	  						"						<p>项目状态</p>" +
	  						"						<p>"+item.proStation+"</p>" +
	  						"					</div>" +
	  						"					<div class='content-row'>" +
	  						"						<p>更新时间</p>" +
	  						"						<p>"+item.updatedAt+"</p>" +
	  						"					</div>" +
	  						"</div>"+
	  						"</div>"
	  				 );

	  			 });

	 		},
	  		error:function(rs){
	 		}
});	
}


function gotoModify(proId){
	window.SYP.pageLink("调研详情",domainName +'/hdk/itemDetails.html?proId=' + proId,-1);
	//location.href ='/hdk/itemDetails.html?proId=' + proId;
}

function create(){
	location.href =  "/hdk/itemDetails.html";	
}