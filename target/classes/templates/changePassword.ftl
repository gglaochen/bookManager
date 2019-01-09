<!DOCTYPE html>
<script src="/js/jquery.min.js"></script>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
</head>
<body>
<form method="post" name="form-login">
    用户名:<input type="text" value="${user.username}" id="userName" readonly="readonly"> <br>
    旧密码：<input type="text" id="oldPassword">
    新密码：<input type="text" id="newPassword">
    确认密码：<input type="text" id="conformPassword">
    <input type="button" value="修改密码" id="bt-change">
</form>
</body>
<script type="text/javascript">
    $("#bt-change").click(function() {
            var con = confirm("确认要修改密码吗");
            if (con == true) {
                $.ajax({
                    url: "/user/toChange",
                    data: "userName=" + $("#userName").val() + "&oldPassword=" + $("#oldPassword").val()
                        + "&newPassword=" + $("#newPassword").val()+ "&conformPassword=" + $("#conformPassword").val(),
                    type: "POST",
                    dataType: "json",
                    success: function (obj) {
                        alert(obj.message);
                        if(obj.state==1){
                            location.href="/login";
                        }
                    }
                });
            }
        }
    );
</script>
</html>