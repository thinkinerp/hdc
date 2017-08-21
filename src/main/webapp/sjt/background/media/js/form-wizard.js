var FormWizard = function () {


    return {
        //main function to initiate the module
        init: function () {
            // if (!jQuery().bootstrapWizard) {
            //     return;
            // }

            // function format(state) {
            //     if (!state.id) return state.text; // optgroup
            //     return "<img class='flag' src='assets/img/flags/" + state.id.toLowerCase() + ".png'/>&nbsp;&nbsp;" + state.text;
            // }

            // $("#country_list").select2({
            //     placeholder: "Select",
            //     allowClear: true,
            //     formatResult: format,
            //     formatSelection: format,
            //     escapeMarkup: function (m) {
            //         return m;
            //     }
            // });

            var form = $('#submit_form');
            var error = $('.alert-error', form);
            var success = $('.alert-success', form);

            form.validate({
                doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
                errorElement: 'span', //default input error message container
                errorClass: 'validate-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                rules: {
                    //account
                    oldpassword: {
                        required: true
                    },
                    password: {
                        required: true
                    },
                    rpassword: {
                        required: true,
                        equalTo: "#password"
                    }
                },

                messages: {  oldpassword: {
        required: "请输入密码"
      },
      password: {
        required: "请输入新密码",
        minlength: "密码长度不能小于 5 个字母"
      },
      rpassword: {
        required: "请输入密码",
        minlength: "密码长度不能小于 5 个字母",
        equalTo: "两次密码输入不一致"
      }
                    
                }

            });
        }

    };

}();
// jQuery.validator.addMethod("stringCheck", function(value, element) {
// return this.optional(element) || /^[u0391-uFFE5w]+$/.test(value);
// }, "只能包括英文字母、数字和下划线");