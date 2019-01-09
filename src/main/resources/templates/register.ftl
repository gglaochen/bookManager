<!DOCTYPE html>
<script src="/js/jquery.min.js"></script>
<script src="/js/jquery.cookie.js"></script>
<html lang="zh">
<head>
    <link href="/css/register.css" rel="stylesheet" type="text/css"/>
    <meta charset="UTF-8">
    <title>图书管理系统注册</title>
</head>
<body>
<a href="/login">直接登录</a>
<form id="form-register" method="post" action="">
    <table>
        <tr class="form-group">
            <td>用户名</td>
            <td id="hang">
                <input type="text" id="username" name="username" placeholder="请输入用户名" minlength="6" maxlength="12"
                       autofocus required>
                <span class="msg-default hidden" id="usernameSpan">用户名长度在6到12位之间</span>
            </td>
        </tr>
        <tr class="form-group">
            <td>
                密码
            </td>
            <td>
                <input type="text" id="password" name="password" placeholder="请输入用户名" minlength="3" maxlength="15"
                       required><span
                        class="msg-default hidden" id="passwordSpan">密码长度在3到15位之间</span>
            </td>
        </tr>
        <tr class="form-group">
            <td>
                确认密码
            </td>
            <td id="hang">
                <input type="text" id="password2" name="password2" required><span class="msg-default hidden"
                                                                                  id="passwordSpan2">必须与密码相同</span>
            </td>
        </tr>
        <tr>
            <td>
                性别:
            </td>
            <td>
                <input name="sex" type="radio" checked="checked"value="1"/>男
                <input name="sex" type="radio" value="0"/>女
            </td>
        </tr>
        <tr>
            <td>
                读者类型
            </td>
            <td>
                <select name="userType" id="userType"><!--后期改数据库查-->
                    <option value="1" selected="selected">学生</option>
                    <option value="2">教师</option>
                    <option value="3">主任</option>
                    <option value="4">校长</option>
                    <option value="5">职工</option>
                    <option value="6">外部人员</option>
                </select>
            </td>
        </tr>
        <tr class="form-group">
            <td id="numType">
                您的学号是
            </td>
            <td id="td"><input type="text" name="memberId" id="memberId" required><span class="msg-default hidden"
                                                                                id="memberIdSpan"></span>
            </td>
        </tr>
        <tr class="form-group">
            <td>
                电话
            </td>
            <td id="td"><input type="text" id="phone" name="phone" placeholder="请输入手机号"
                       pattern="(\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$"
                       required>
                <span class="msg-default hidden" id="phoneSpan">请输入合法的手机号</span></td>
        </tr>
    </table>
    <input type="button" value="注册" id="bt-register">
</form>
</body>
<script type="text/javascript">
    $("#bt-register").click(function () {
        var lengths = 0;
        $(".form-group").each(function () {
            if ($(this).find('span').hasClass('msg-success')) {
                lengths++;
            }
        });

        if (lengths == 5) {
            $.ajax({
                url: "user/doRegister",
                data: $("#form-register").serialize(),
                type: "POST",
                dataType: "json",
                success: function (obj) {
                    if(obj.state==0){
                        alert(obj.message);
                    }else{
                        location.href="login";
                    }
                }
            });
        }
    });
    /*1.对用户名进行验证*/
    $("#username").blur(function(){
    if (this.validity.valueMissing) {
        this.nextElementSibling.innerHTML = '用户名不能为空';
        this.nextElementSibling.className = 'msg-error';
        this.setCustomValidity('用户名不能为空');
    } else if (this.validity.tooShort) {
        this.nextElementSibling.innerHTML = '用户名不能少于6位';
        this.nextElementSibling.className = 'msg-error';
        this.setCustomValidity('用户名不能少于6位');
    } else {
        this.nextElementSibling.innerHTML = '用户名格式正确';
        this.nextElementSibling.className = 'msg-success';
        this.setCustomValidity('');
        var data = document.getElementById("username").value;
        if (!data) {   //用户没有输入任何内容
            return;
        }
        /**发起异步GET请求，询问服务器用户名是否已经存在**/
        $.ajax({
            url: "/user/checkUsername",
            type: "GET",
            data: "username=" + $("#username").val(),
            dataType: "json",
            success: function (obj) {
                $("#usernameSpan").html(obj.message);
                if (obj.state == 0) {
                    $("#usernameSpan").attr("class", "msg-error");
                } else {
                    $("#usernameSpan").attr("class", "msg-success");
                }
            }
        });
    }
    }
    );
    $("#username").focus(function () {
    this.nextElementSibling.innerHTML = '用户名长度在6到12位之间';
    this.nextElementSibling.className = 'msg-default';
    });
    $("#password").focus(function () {
    this.nextElementSibling.innerHTML = '密码长度在3到15位之间';
    this.nextElementSibling.className = 'msg-default';
    });
    $("#password").blur(function () {
    if (this.validity.valueMissing) {
        this.nextElementSibling.innerHTML = '密码不能为空';
        this.nextElementSibling.className = 'msg-error';
        this.setCustomValidity('密码不能为空');
    } else if (this.validity.tooShort) {
        this.nextElementSibling.innerHTML = '密码长度在尽量别少于6位';
        this.nextElementSibling.className = 'msg-error';
        this.setCustomValidity('密码长度在尽量别少于6位');
    } else {
        this.nextElementSibling.innerHTML = '密码格式正确';
        this.nextElementSibling.className = 'msg-success';
        this.setCustomValidity('');
    }

    //如果再次输入密码框中有信息，再去判断两次密码输入是否一致
    if ($("#password2").val()) {
        $("#password2").blur;
    }
    });


    $("#password2").focus(function () {
    this.nextElementSibling.className = 'msg-default hidden';
    });
    $("#password2").blur(function () {
    if (this.validity.valueMissing) {
        this.nextElementSibling.innerHTML = '密码不能为空';
        this.nextElementSibling.className = 'msg-error';
        this.setCustomValidity('密码不能为空');
    } else if (this.validity.tooShort) {
        this.nextElementSibling.innerHTML = '密码长度在尽量别少于6位';
        this.nextElementSibling.className = 'msg-error';
        this.setCustomValidity('密码长度在尽量别少于6位');
    } else {
        this.nextElementSibling.innerHTML = '密码格式正确';
        this.nextElementSibling.className = 'msg-success';
        this.setCustomValidity('');
    }


    //如果两次密码输入不一致，信息提示
    if (this.value !== $("#password").val()) {
        this.nextElementSibling.innerHTML = '两次输入密码不一致';
        this.nextElementSibling.className = 'msg-error';
        this.setCustomValidity('两次输入密码不一致');
    }
    });

    //对成员编号进行认证
    $("#memberId").blur(function () {
        $.ajax({
            url: "/user/checkMemberId",
            type: "GET",
            data: "memberId=" + $("#memberId").val() + "&userType=" + $("#userType").val(),
            dataType: "json",
            success: function (obj) {
                $("#memberIdSpan").html(obj.message);
                if (obj.state == 0) {
                    $("#memberIdSpan").attr("class", "msg-error");
                    $("#memberIdSpan").innerHTML = '不存在或已使用';
                } else {
                    $("#memberIdSpan").attr("class", "msg-success");
                    $("#memberIdSpan").innerHTML = '有效';
                }
            }
        });
    });
    $("#memberId").focus(function () {
        this.nextElementSibling.innerHTML = '请输入合法的成员编号';
        this.nextElementSibling.className = 'msg-default';
    });

    //切换类型时对成员编号进行认证
    window.onload = function () {
        document.getElementById("userType").addEventListener('change', function () {
            if($("#userType").val()==6){
                $("#memberIdSpan").attr('class',"msg-success");
                $("tr:eq(5)").hide();
            }
            else {
                $("#memberIdSpan").attr('class',"msg-default hidden");
                $("tr:eq(5)").show();
            $.ajax({
                url: "/user/checkMemberId",
                type: "GET",
                data: "memberId=" + $("#memberId").val() + "&userType=" + $("#userType").val(),
                dataType: "json",
                success: function (obj) {
                    $("#memberIdSpan").html(obj.message);
                    if (obj.state == 0) {
                        $("#memberIdSpan").attr("class", "msg-error");
                        $("#memberIdSpan").innerHTML = '不存在或已使用';
                    } else {
                        $("#memberIdSpan").attr("class", "msg-success");
                        $("#memberIdSpan").innerHTML = '有效';
                    }
                }
            })
        }}
        );
    };
    /*3.对手机号进行验证*/
    $("#phone").blur(function () {
        if (this.validity.valueMissing) {
            this.nextElementSibling.innerHTML = '手机号不能为空';
            this.nextElementSibling.className = 'msg-error';
            this.setCustomValidity('手机号不能为空');
        } else if (this.validity.patternMismatch) {
            this.nextElementSibling.innerHTML = '手机号格式不正确';
            this.nextElementSibling.className = 'msg-error';
            this.setCustomValidity('手机号格式不正确');
        } else {
            this.nextElementSibling.innerHTML = '手机号格式正确';
            this.nextElementSibling.className = 'msg-success';
            this.setCustomValidity('');

            var data = document.getElementById("phone").value;
            if (!data) {   //用户没有输入任何内容
                return;
            }
            /**发起异步GET请求，验证电话号码是否已经存在**/
            $.ajax({
                url: "user/checkPhone",
                type: "GET",
                data: "phone=" + $('#phone').val(),
                dataType: "json",
                success: function (obj) {
                    $("#phoneSpan").html(obj.message);
                    if (obj.state === 0) {
                        $("#phoneSpan").attr("class", "msg-error");
                    } else {
                        $("#phoneSpan").attr("class", "msg-success");
                    }
                }
            });
        }
    });
    $("#phone").focus(function () {
        this.nextElementSibling.innerHTML = '请输入合法的手机号';
        this.nextElementSibling.className = 'msg-default';
    })
</script>
</html>