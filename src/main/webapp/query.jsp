<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=0">
<%@include file="/common/taglibs.jsp"%>
<%@include file="/common/css.jsp" %> 
<script type="text/javascript" >
    var ctx = "${ctx}";	
</script>
<title>数据通-${storeName}</title>
  <link href="${ctx}/css/jsoneditor.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/js/comboboxConfig.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.scrollTo.min.js"></script>
<script type="text/javascript" src="${ctx}/js/formatter.js"></script>
<script src="${ctx}/static/js/style.js"></script>
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/css/style.css">
</head>
<body>
<div class="page_query">
   <div class="query_list">
  <input type="date" value="" class="input_date" id="startTime"><span>至</span><input type="date" value="" class="input_date"  id="endTime"><input type="button" value="查询" id="btn_query" class="btn_default">
  </div>
  
  <div class="clear"></div>
  <table  id="sale_data_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="table_default">
    <thead>
      <tr>
        <th>上报日期</th>
        <th>金额</th>
        <th>状态</th>
        <th>操作人</th>
        <th>操作时间</th>
      </tr>
    </thead>
<!--     <tr>
      <td>2017/04/12</td>
      <td>10000</td>
      <td>成功</td>
      <td>李笑笑</td>
      <td>2017/04/12 16:34</td>
    </tr>
    <tr>
      <td>2017/04/12</td>
      <td>10000</td>
      <td>成功</td>
      <td>李笑笑</td>
      <td>2017/04/12 16:34</td>
    </tr>
    <tr class="tr_error">
      <td>2017/04/12</td>
      <td>10000</td>
      <td>失败</td>
      <td>李笑笑</td>
      <td>2017/04/12 16:34</td>
    </tr> -->
  </table>
</div>

<script>

$(function(){
  var today=new Date().Format("yyyy-MM-dd");;
  var prevday=getPreMonth(today);  
  $("#startTime").val(prevday);
  $("#endTime").val(today);
  $("#btn_query").bind("click",function(){
    var start=$("#startTime").val();  
    var end=$("#endTime").val(); 
    if(start>today)
      {alert("开始时间不能大于今天");} 
    if(end>today)
      {alert("结束时间不能大于今天");}   
    if(start>end)
      {alert("开始时间不能大于结束时间");}
    
    $.ajax({
    	url:ctx + "/data/getSalesDate",
    	type:'post',
    	dataType:'json',
    	data:{
    		startDate:start,
    		endDate:end,
    		page:0,
    		pageSize:1000000
    	},
    	success:function(rs){
    		console.log(rs);		
    		$("#sale_data_table").empty();
    		$("#sale_data_table").append(
  			      "<thead>"+
  			      "<tr>"+
  			        "<th>上报日期</th>"+
  			        "<th>金额</th>"+
  			        "<th>状态</th>"+
  			        "<th>操作人</th>"+
  			        "<th>操作时间</th>"+
  			      "</tr>"+
  			    "</thead>"		
  		);
    		$.each(rs , function(index , item){
    			$("#sale_data_table").append(
    					"<tr "+(1 != item.state? 'class="tr_error"':'')+'>' +
    				    "<td>"+item.salesDate+"</td>"+
    				    "<td>"+item.salesAmount+"</td>"+
    				    "<td>"+(1 == item.state?"成功":"失败")+"</td>"+
    				    "<td>"+item.userId+"</td>"+
    //TODO: 联查用户的名称
    				    "<td>"+item.createdAt+"</td>"+
    				  "</tr>"
    			);
    		});
    	},
    	error:function(rs){
    		console.log(rs);		
    	}
    });    
    
  })
});
var today=new Date().Format("yyyy-MM-dd");;
var prevday=getPreMonth(today);  
var start=$("#startTime").val();  
var end=$("#endTime").val(); 
if(start>today)
  {alert("开始时间不能大于今天");} 
if(end>today)
  {alert("结束时间不能大于今天");}   
if(start>end)
  {alert("开始时间不能大于结束时间");}

$.ajax({
	url:ctx + "/data/getSalesDate",
	type:'post',
	dataType:'json',
	data:{
		startDate:start,
		endDate:end,
		page:0,
		pageSize:1000000
	},
	success:function(rs){
		console.log(rs);		
		$("#sale_data_table").empty();
		
		$("#sale_data_table").append(
			      "<thead>"+
			      "<tr>"+
			        "<th>上报日期</th>"+
			        "<th>金额</th>"+
			        "<th>状态</th>"+
			        "<th>操作人</th>"+
			        "<th>操作时间</th>"+
			      "</tr>"+
			    "</thead>"		
		);
		$.each(rs , function(index , item){
			$("#sale_data_table").append(
					"<tr "+(1 != item.state? 'class="tr_error"':'')+'>' +
				    "<td>"+item.salesDate+"</td>"+
				    "<td>"+item.salesAmount+"</td>"+
				    "<td>"+(1 == item.state?"成功":"失败")+"</td>"+
				    "<td>"+item.userId+"</td>"+
//TODO: 联查用户的名称
				    "<td>"+item.createdAt+"</td>"+
				  "</tr>"
			);
		});
	},
	error:function(rs){
		console.log(rs);		
	}
});    
</script>
</body>
</html>