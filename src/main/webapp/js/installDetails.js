/**
 *
 */
loadCombobox("cashSystem", "cash_system");
loadCombobox("cashBrand", "cash_brand");
loadCombobox("cashPort", "cash_port");
loadCombobox("eqStyle", "equipment_type");
loadCombobox("printerPort", "printer_port");
loadCombobox("installStation", "install");
var time = new Date().getTime();
$.ajax({
  url: ctx + '/project/getSome',
  type: 'get',
  data: {
    time: time
  },
  jsonpCallback: "project_" + time + "_getSome",
  jsonp: "callback",
  dataType: 'jsonp',
  success: function(rs) {
    var str = '';
    var i = 0;
    $.each(rs, function(index, item) {

      if (0 == i) {
        i++;
        str = str + item.proName;
      } else {
        str = str + "," + item.proName;
      }
    });
    $('#proName').attr("data-select", str);
  },
  error: function(rs) {
    console.log(rs);
  }
});

$.ajax({
  url: ctx + '/state/getSome',
  type: 'get',
  data: {
    'ownerTable': "install"
  },
  jsonpCallback: "state_getSome",
  jsonp: "callback",
  dataType: 'jsonp',
  success: function(rs) {
    /*            alert(JSON.stringify(rs)); */
    var str = '';
    var i = 0;
    $.each(rs, function(index, item) {

      if (0 == i) {
        i++;
        str = str + item.staName;
      } else {
        str = str + "," + item.staName;
      }
    });
    $('#installState').attr("data-select", str);
  },
  error: function(rs) {}
});

Window.selected = function() {
  $.ajax({
    url: ctx + '/shops/selectForCombobox',
    type: 'get',
    data: {
      'proId': $('#proName').html()
    },
    jsonpCallback: "shops_selectForCombobox",
    jsonp: "callback",
    dataType: 'jsonp',
    success: function(rs) {
      var str = '';
      var i = 0;
      var has = new Array();
      $.each(rs, function(index, item) {
        if (-1 == $.inArray(item.shopName, has)) {
          has.push(item.shopName);
          if (0 == i) {
            i++;
            str = str + item.shopName;
          } else {
            str = str + "," + item.shopName;
          }
        }
      });
      $("#shopName").attr("data-select", str);

    },
    error: function(rs) {}
  });
}
Window.shopSelected = function() {
  $.ajax({
    url: ctx + '/shops/selectForCombobox',
    type: 'get',
    data: {
      'shopName': $('#shopName').html()
    },
    jsonpCallback: "shops_selectForCombobox",
    jsonp: "callback",
    dataType: 'jsonp',
    success: function(rs) {
      var str = '';
      var i = 0;
      $.each(rs, function(index, item) {
        $("#shopState").html(item.shopMerStation);
        $('#shopPosition').html(item.shopPosition);
        $('#shopType').html(item.shopType);
        $('#shopSecType').html(item.shopSecType);

        $('#shopCode').html(item.shopId);
      });

    },
    error: function(rs) {}
  });
  //如果这个门店已经调研了，与此门店相关的打印机和收银机信息都已经存在，所以在这样情况下
  //需要将收银机和打印机的信息加载过来。
  $.ajax({
    url: ctx + '/survey/getSome',
    type: 'get',
    data: {
      'shopName': $('#shopName').html(),
      'proName': $('#proName').html()
    },
    dataType: 'json',
    success: function(rs) {
      if (null != rs && undefined != rs && rs.length > 0) {
        loadPrinterAndCasher();
      } else {
        $.each(rs, function(index, item) {
          surId = item.surId
        });
      }
    },
    error: function(rs) {}
  });
}

function loadPrinterAndCasher(surId) {
  $.ajax({
    url: ctx + '/printer/getSome',
    type: 'post',
    data: {
      'surId': surId,
    },
    jsonpCallback: "printer_getSome",
    jsonp: "callback",
    dataType: 'jsonp',
    success: function(rs) {
      if (null != rs && undefined != rs && rs.length > 0) {
        $.each(rs, function(index, item) {
          $('#priId').html(item.printerId);
          $('#priBrand').html(item.printerBrand);
          $('#prinPort').html(item.printerPort);
        });
      }
    },
    error: function(rs) {}
  });
  $.ajax({
    url: ctx + '/cash/getSome',
    type: 'post',
    data: {
      'surId': surId,
    },
    jsonpCallback: "cash_getSome",
    jsonp: "callback",
    dataType: 'jsonp',
    success: function(rs) {
      if (null != rs && undefined != rs && rs.length > 0) {
        $.each(rs, function(index, item) {
          $('#cashId').html(item.cashId);
          $('#cashSystem').html(item.cashSystem);
          $('#cashBrand').html(item.cashBrand);
          $('#cashPort').html(item.cashPort);

          if (undefined == item.printerDriver || "" == item.printerDriver) {
            $('#t').attr("class", "off");
            $('#f').attr("class", "on");
          } else {
            $('#t').attr("class", "on");
            $('#f').attr("class", "off");

          }

        });
      }
    },
    error: function(rs) {}
  });
}


function setCashidOnInstall() {
  $('#cashCode').html($('#cashId').val());
}


function setpriIdOnInstall() {
  $('#printCode').html($('#priId').val());

}

function seteqIdOnInstall() {
  $('#equipmentCode').html($('#eqId').val());

}

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

var submit = function() {
  //  app.saveDate();
  // 在没有调研的情况下要进行一下动作
  //保存收银机

  // 保存打印机

  // 采集点
  if ('{}' != allThing && "" != allThing) {

    $.ajax({
      url: ctx + '/install/modify',
      type: 'post',
      data: {
    	  files:JSON.stringify(imgs),
        'install.installId': $("#installCode").val(),
        'install.id': allObjs.install.id,
        'install.installStation': $('#installStation').html(),
        'install.proId': allObjs.install.proId,
        'install.shopId': $('#shopCode').html(),
        'install.cashId': $('#cashCode').html(),
        'install.printerId': $('#printCode').html(),
        'install.eqId': $('#equipmentCode').html(),
        'install.installData': $('#installData').html(),
        'install.installTime': $('#installTime').val()
          //,'install.installUser':''
          ,
        'install.installNetwork': $('#installNetworkHard').attr('class') == 'on' ? "硬件" : "软件",
        'install.installRemote': $('#installRemote').val()
          //,'install.installEndtime':
          //,'install.install.createdAt':''
          //,'install.updatedAt':''
          //,'install.installRemarks':''
          //收银机
          ,
        "cash.id": allObjs.cash.id,
        'cash.cashId': $("#cashId").val(),
        'cash.cashBrand': $("#cashBrand").html()
          //,'cashRegister':''
          ,
        'cash.cashSystem': $("#cashSystem").html(),
        'cash.cashPort': $("#cashPort").html(),
        'cash.printerDriver': ($("#t").attr("class") == "off" ? "否" : "是"),
        'cash.surId': surId,
        'cash.installId': $("#installCode").val()
          //打印机
          ,
        'printer.id': allObjs.printer.id,
        'printer.printerId': $('#priId').val(),
        'printer.printerBrand': $('#priBrand').val()
          //          ,'printerModel':''
          ,
        'printer.printerPort': $('#printerPort').html(),
        'printer.surId': surId,
        'printer.installId': $("#installCode").val()
          //          ,'createdAt':''
          //          ,'updatedAt':''
          //采集点
          ,
        'equipment.id': allObjs.equipment.id,
        'equipment.eqId': $("#eqId").val(),
        'equipment.eqType': $('#eqTypeHard').attr('class') == "on" ? "硬件" : "软件",
        'equipment.eqStyle': $("#eqStyle").html()
          //          ,'hardwareId':
          ,
        'equipment.softwareVersion': $('#softwareVersion').val(),
        'equipment.proId': allObjs.project.proId,
        'equipment.shopId': $('#shopCode').html(),
        'userName': params.userName,
        'userNum': params.userNums
        //          ,'remark1':''
        //          ,'remark2':''
        //          ,'createdAt':''
        //          ,'updatedAt':''
      },
      success: function(result) {

        location.href = ctx + "/installList.jsp";
      },
      error: function(result) {
        console.log(result)
      }
    });


  } else {
    $.ajax({
      url: ctx + '/install/submit', //用于文件上传的服务器端请求地址
      dataType: 'json', //返回值类型 一般设置为json
      type:'post',
      data: {
    	  files:JSON.stringify(imgs),
        'install.installId': $("#installCode").val(),
          //          ,'install.id':allObjs.install.id,
        'install.installStation': $('#installStation').html(),
        'install.proId': $('#proName').html(),
        'install.shopId': $('#shopCode').html(),
        'install.cashId': $('#cashCode').html(),
        'install.printerId': $('#printCode').html(),
        'install.eqId': $('#equipmentCode').html(),
        'install.installData': $('#installData').html(),
        'install.installTime': $('#installTime').val()
          //,'install.installUser':''
          ,
        'install.installNetwork': $('#installNetworkHard').attr('class') == 'on' ? "硬件" : "软件",
        'install.installRemote': $('#installRemote').val()
          //,'install.installEndtime':
          //,'install.install.createdAt':''
          //,'install.updatedAt':''
          //,'install.installRemarks':''
          //收银机
          //          ,"cash.id":allObjs.cash.id
          ,
        'cash.cashId': $("#cashId").val(),
        'cash.cashBrand': $("#cashBrand").html()
          //,'cashRegister':''
          ,
        'cash.cashSystem': $("#cashSystem").html(),
        'cash.cashPort': $("#cashPort").html(),
        'cash.printerDriver': ($("#t").attr("class") == "off" ? "否" : "是"),
        'cash.surId': surId,
        'cash.installId': $("#installCode").val()
          //打印机
          //          ,'printer.id':allObjs.printer.id
          ,
        'printer.printerId': $('#priId').val(),
        'printer.printerBrand': $('#priBrand').val()
          //          ,'printerModel':''
          ,
        'printer.printerPort': $('#printerPort').html(),
        'printer.surId': surId,
        'printer.installId': $("#installCode").val()
          //          ,'createdAt':''
          //          ,'updatedAt':''
          //采集点
          //          ,'equipment.id':allObjs.equipment.id
          ,
        'equipment.eqId': $("#eqId").val(),
        'equipment.eqType': $('#eqTypeHard').attr('class') == "on" ? "硬件" : "软件",
        'equipment.eqStyle': $("#eqStyle").html()
          //          ,'hardwareId':
          ,
        'equipment.softwareVersion': $('#softwareVersion').val(),
        'equipment.proId': $('#proName').html(),
        'equipment.shopId': $('#shopCode').html(),
        'userName': params.userName,
        'userNum': params.userNum
        //          ,'remark1':''
        //          ,'remark2':''
        //          ,'createdAt':''
        //          ,'updatedAt':''
      },
      success: function(data, status) //服务器成功响应处理函数
      {
        console.log(data);
       location.href = ctx + "/installList.jsp";
      },
      error: function(data, status, e) //服务器响应失败处理函数
      {
        console.log(e);
        console.log(data);
        console.log(status);

      }
    });
  }
}

var onSetupState = function() {
  $('#installStation').html($('#installState').html());
}
