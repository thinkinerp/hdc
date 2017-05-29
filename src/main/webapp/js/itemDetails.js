/**
 * 
 */
if(allThing != '{}' && allThing !='' && allThing !='null' ){
	
	console.log(allThing)
	var allObjs = JSON.parse(allThing);
	
	console.log(allObjs);
	
	setValue("proName",allObjs.project.proName);
	setValue("proEdition",allObjs.project.proEdition);
	setValue("proStation",allObjs.project.proStation);
	setValue("proManagerPro",allObjs.project.proManagerPro);
	setValue("proManagerPro1",allObjs.project.proManagerPro);
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
			setValue('checkPercentage',item.proAlready+ "%");
			setValue('certainPercentage',item.count+ "%");
			setValue('installPercnetage',item.proCheck+ "%");
      });
   }
	
}


function gotoDetail(t){
	if(1==t){
		location.href = ctx + "/issueList.jsp?problemObject=海鼎"
	}else if(2==t){
		location.href = ctx + "/issueList.jsp?problemObject=客户"
	}
}
