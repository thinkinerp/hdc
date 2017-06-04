/**
 * 
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