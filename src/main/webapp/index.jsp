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
<title>数据通上报</title>
  <link href="${ctx}/css/jsoneditor.css" rel="stylesheet" type="text/css">

<script src="${ctx}/static/js/style.js"></script>
<script src="${ctx}/static/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jweixin-1.0.0.js"></script>
<script src="${ctx}/js/scanCode.js"></script>

<link rel="stylesheet" href="${ctx}/static/css/style.css">
</head>
<body>
<div class="page_luru">
  <h1>${storeName}</h1>
  <a href="javascript:void(0)" id = "reBinding" class="a_link1">重新绑定</a>
  <div id="datepicker"></div>
  <div class="query_box">
    <input type="number"  value=""  name="input_num" id="input_num" class="input_txt1" placeholder="请输入上报金额" required/>
    <input type="button" value="上报" id="btn_sbao" class="btn_default">
  </div>
  <div class="clear"></div>
  <h2>上报操作记录</h2>
  <table id="sale_data_table" width="100%" border="0" cellspacing="0" cellpadding="0" class="table_default">
      <thead>
      <tr>
        <th>上报日期</th>
        <th>金额</th>
        <th>状态</th>
        <th>操作人</th>
        <th>操作时间</th>
      </tr>
    </thead>
  <!-- <tr>
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
    <td>10000.00</td>
    <td>失败</td>
    <td>李笑笑</td>
    <td>2017/04/12 16:34</td>
  </tr> -->
</table>
<a href="${ctx}/query.jsp" class="a_more">更多记录</a>
</div>
<script>
function loadData(){
$.ajax({
	url:ctx + "/data/getSalesDate",
	type:'post',
	dataType:'json',
	data:{
		page:0,
		pageSize:4
	},
	success:function(rs){
		console.log(rs);		
		$("#sale_data_table").empty();
		$("#sale_data_table").html(
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
}
loadData();
  function showMessage(msg)
  {
    var msgtxt='<div class="msgbox"><div class="msg_content">'+msg+'</div><div class="msg_foot"><button type="button" class="btn_confirm btn_default">确认</button></div></div>';
    $("body").append(msgtxt);
    $(".btn_confirm").on("click",function(){
      $(this).parents(".msgbox").remove();
    })
  }
  function showerror(error)
	{alert(error);}
  //输入框验证 start
	function check_input()
	{var input_txt1=$.trim($("#input_num").val()).replace(/\s/g, "");	

		if(input_txt1=="")
		{  showMessage("请输入上报金额");
	     $("#input_num").focus();
	     $("#input_num").val("");
		 $('#btn_sbao').attr("disabled",false);
		   return false;
		}
/* 		else if(input_txt1<=0 || isNaN(input_txt1))
		{
		   showerror("请输入大于0的数字");
		   $("#input_num").focus();
		   $("#input_num").val("");
		   return false;
		} */else if(!/^[0-9]*$|^-[0-9]*$/.test(input_txt1)){
			showMessage("您输入的内容存在非数字");
		   $("#input_num").focus();
		   $("#input_num").val("");
		   $('#btn_sbao').attr("disabled",false);
		}else
		  {return true;}
	}
  $(function() {
  	var dateToDisable = new Date();
     $("#datepicker" ).datepicker({maxDate:dateToDisable,changeMonth:true,changeYear: true,dateFormat:'yy-mm-dd',hideIfNoPrevNext: true});
	/*  $(".ui-datepicker-title").append('月'); */
	   var form_ok=true;	
	   
	   
    $("#reBinding").bind('click',function(){
		   
		   //呼叫后台，获得扫码需要的验证码
		   $.ajax({
			   url:ctx + '/data/getSingature',
			   type:'get',
			   dataType:'json',
			   success:function(rs){
				   /* alert(JSON.stringify(rs); */
				   scanCode(rs);
			   },
			   error:function(rs){
				   
			   }
		   });	   
		   
	   }); 
	   
	   
	   
	   
	   $("#btn_sbao").bind("click",function(){
		   $('#btn_sbao').attr("disabled",true);
		 var get_date=$("#datepicker" ).datepicker("getDate");
		 var seldate=get_date.Format("yyyy-MM-dd");	
		 var input_txt1=$.trim($("#input_num").val());
		 if(check_input() && seldate!="")
  		{
			$.ajax({
				url:ctx + "/data/submit",
				type:'post',
				data:{
				    salesDate:seldate,
				    salesAmount:$("#input_num").val(),
				    storeUuid:'',
				    userId:'123'
				},
				success:function(result){
					console.log(result);
					if(result > 0){
		  			showMessage("数据提交成功");
		  			$('#btn_sbao').attr("disabled",false);
		  			$("#input_num").val('');
		  		    $("#datepicker" ).datepicker({maxDate:dateToDisable,changeMonth:true,changeYear: true,dateFormat:'yy-mm-dd',hideIfNoPrevNext: true});
		  		    $("#datepicker" ).datepicker("setDate",new Date());
				
		  		 	loadData();
					}else{
			  			showMessage("数据提交失败");
					}
				},
				error:function(result){
					console.log(result);
		  			showMessage("数据提交失败");
				}
			});
			 

  		}		
	 })
  }); 
  
  

  
  
  
  
  
  
  
  
  
  </script>
</body>
</html>