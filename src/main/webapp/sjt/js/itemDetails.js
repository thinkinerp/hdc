/**
 * 
 */
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

if(undefined != params["proId"] && "" != params["proId"]){
	$.ajax({
		url:domainName + "/hdk/project/gotoModify",
		data:{
			proId:params["proId"]
		},
		dataType:'jsonp',
		type:'get',
		jsonp:"callback",
		success:function(res){
			if(undefined !=res && null != res && 'success' == res.message){
				allThing = res;
				loadItem(res);
			}else{
				app.alert("网络出现问题",1);
			}
		},
		error:function(rs){
			console.log(rs);
		}
	});
}

var loadItem = function(allThing){

if(undefined != allThing && null != allThing ){
	
	var allObjs = allThing;
	setValue("proName",allObjs.project.proName);
	setValue("proEdition",allObjs.project.proEdition);
	setValue("proStation",allObjs.project.proStation);
	setValue("proManagerPro",allObjs.project.proManagerPro);
	setValue("proCoordination",allObjs.project.proCoordination);
	setValue("proCoordinationPhone",allObjs.project.proCoordinationPhone);
	setValue("proManagerPro1",allObjs.project.proCoordination);
	setValue("proUpdataTime",allObjs.project.proUpdataTime);
	var sum= 0 ;
	$.each(allObjs.projectProblem,function(index,item){
		 if('商务'==isUndefined(item.problemType) && '海鼎'==isUndefined(item.problemObject)){
			setValue('haidingSale',item.count);
			sum = sum + item.count;
		}else if('技术'==isUndefined(item.problemType) && '海鼎'==isUndefined(item.problemObject)){
			setValue('haidingTec',item.count);
			sum = sum + item.count;
		}else if('运维'==isUndefined(item.problemType) && '海鼎'==isUndefined(item.problemObject)){
			setValue('haidingOperation',item.count);
		}else if('运维'==isUndefined(item.problemType )&& '海鼎'==isUndefined(item.problemObject)){
			setValue('haidingOperation',item.count);
			sum = sum + item.count;
		}else if('其他'==isUndefined(item.problemType )&& '客户'==isUndefined(item.problemObject)){
			setValue('customer',item.count);
			setValue('customerOther',item.count);
			sum = sum + item.count;
		}
	});
	setValue('haiding',sum);
	
	// 项目情况
	if(!!isUndefined(allObjs.project)){
		setValue("contranctCount",isUndefined(allObjs.project.proTodal));
		setValue("installToCount",isUndefined(allObjs.project.proNeed));
		setValue("installedCount",isUndefined(allObjs.project.proAlready));
		setValue("checkedCount",isUndefined(allObjs.project.proCheck));
		setValue("offlindCount",isUndefined(allObjs.project.proNot));
	}

	// 采集接口
	
	if(!!isUndefined(allObjs.equipment)){
		$('#problemObject').html('');
		$.each(allObjs.equipment,function(index,item){
			$('#problemObject').append(
			"						<li>" +
			"							<div class='iz-list-title'>"+item.problemObject+"</div>" +
			"							<div id='hard' class='iz-list-content'>"+item.count+"</div>" +
			"						</li>"
			);
		});
}
	
	if(!!isUndefined(allObjs.cashCount)){
		$('#cashPort').html('');
		$.each(allObjs.cashCount,function(index,item){
			$('#cashPort').append(
					"						<li>" +
					"							<div class='iz-list-title'>"+item.problemObject+"：</div>" +
					"							<div class='iz-list-content'>"+item.count+"</div>" +
					"						</li>"
			);
		});
	}
	
	if(!!isUndefined(allObjs.check)){
      $.each(allObjs.check,function(index,item){
			setValue('checkPercentage',isUndefined( ("" == item.proAlreadyPer? 0 : item.proAlreadyPer)));
			setValue('certainPercentage',isUndefined((undefined == item.count ? 0 :item.count )));
			setValue('installPercnetage',isUndefined(("" ==item.proCheckPer ? 0 : item.proCheckPer)));
      });
   }
}
}

function gotoDetail(t){
	if(1==t){
		location.href = "/hdk/issueList.jsp?problemObject=海鼎"
	}else if(2==t){
		location.href = "/hdk/issueList.jsp?problemObject=客户"
	}
}
