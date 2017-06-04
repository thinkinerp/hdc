/**
 *当点击 input 、输入框的时候隐藏保存按钮
 */
            $('input').focus(function() {
                $("#save").hide();
            });
            $('input').blur(function() {
                $("#save").show();
            });
            $(".g-fa").focus(function() {
                $("#save").hide();
            });
            $(".g-fa").blur(function() {
                $("#save").show();
            });
            $(".i-contenteditable").focus(function() {
                $("#save").hide();
            });
            $(".i-contenteditable").blur(function() {
                $("#save").show();
            });
            $('input').focus(function() {
                $(".i-addTable").hide();
            });
            $('input').blur(function() {
                $(".i-addTable").show();
            }); 
