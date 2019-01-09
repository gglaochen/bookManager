<!DOCTYPE html>
<script src="/js/jquery.min.js"></script>
<script src="/js/jquery.cookie.js"></script>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>图书管理系统登录</title>
</head>
<body>
    <form method="post" name="form-login">
        用户名:<input type="text" id="username" name="username" placeholder="请输入用户名" minlength="6" maxlength="12" required><div id="showResult"></div> <br>
        密码:<input type="text" id="password" name="password" placeholder="请输入密码" minlength="3" maxlength="15" required><br>
        <input type="checkbox" id="remember-me">记住我
        <input type="button" value="登录" id="bt-login">
    </form>
</body>
<script type="text/javascript" src="/js/dologin.js"></script>
</html>