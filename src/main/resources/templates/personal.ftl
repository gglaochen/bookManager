<!DOCTYPE html>
<script src="/js/jquery.min.js"></script>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
</head>
<body>
<a href="/">返回首页</a><br>
个人中心<br>
<#if user.img??>
    <img src="${user.img}" style="width: 50px;height: 50px">
    <#else>
    <img src="/img/userDefault.png" style="width: 50px;height: 50px">
</#if>
<br>
修改头像
读者昵称：${user.readername}<br>
读者类别：${userType}<br>
<a href="/user/changePassword">修改密码</a><br>
<a>修改个人资料</a><br>
<a href="/user/myBorrow">我的借阅</a><br>
<a href="/user/myFavourite">我的收藏</a><br>
<a href="/user/myPredeter">我的预定</a><br>
日历<br>
天气<br>
为我推荐<br>
站内消息
</body>
</html>