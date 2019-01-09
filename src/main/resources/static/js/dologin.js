$('#bt-login').click(function() {
        $.ajax({
            url: "user/doLogin",
            data: "username=" + $("#username").val() + "&password=" + $("#password").val(),
            type: "POST",
            dataType: "json",
            success: function (obj) {
                if (obj.state == 1) {
                    Save();
                    location.href = "index";
                } else {
                    alert(obj.message);
                }
            }
        });
    }
);
$(document).ready(function () {
    if ($.cookie("rmbUser") == "true") {
        $("#remember-me").attr("checked", true);
        $("#username").val($.cookie("username"));
        $("#password").val($.cookie("password"));
    }
});
function Save() {
    if ($("#remember-me").prop("checked")) {
        var str_username = $("#username").val();
        var str_password = $("#password").val();
        $.cookie("rmbUser", "true", { expires: 7 }); //存储一个带7天期限的cookie
        $.cookie("username", str_username, { expires: 7 });
        $.cookie("password", str_password, { expires: 7 });
    }
    else {
        $.cookie("rmbUser", "false", { expire: -1 });
        $.cookie("username", "", { expires: -1 });
        $.cookie("password", "", { expires: -1 });
    }
}