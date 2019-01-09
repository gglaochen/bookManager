<!DOCTYPE html>
<html lang="zh">
<head>
    <script src="/js/jquery.min.js"></script>
    <link href="/css/main.css" rel="stylesheet" type="text/css"/>
    <meta charset="UTF-8">
    <title>图书首页</title>
</head>
<body>
<div id="shortcut">
<#if Session["user"]??>
    <div class="collect">
    您好 , 欢迎来到华航图书馆！
    ${user.username}, <a href="/user/logout">退出</a>
    </div>
    <ul>
    <#if user.typeId==7>
    <li class="bd"> <a href="/admin" class="link-regist">管理员界面</a></li>
    </#if>
    <li class="bd"><a href="/personal" class="link-regist">个人中心</a></li>
    <li><a href="/user/myBorrow">我的借阅</a></li>
    <li>消息中心</li>
    </ul>
    <#else>
    <span class="collect">
    您好 , 欢迎来到华航图书馆！<a href="/login" class="link-regist">[请登录]</a>,新用户?
    <a href="/register" class="link-regist">[注册]</a>
    </span>
</#if>
</div>
<br>
<div  align="center">
<input type="text" name="search" id="search">
<input type="button" value="文津搜索" id="find"/>
<a href="/book/rank">排行榜</a>
</div>
</body>
<script type="text/javascript" src="/js/index.js"></script>
</html>