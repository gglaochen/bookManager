<!DOCTYPE html>
<script src="/js/jquery.min.js"></script>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>我的预定</title>
</head>
<body>
<a href="/">返回个人中心</a><br>
<#if predeter??>
<#if predeter?size!=0>
<h2 align="center">我的预定</h2>
<table border="1" width="800" align="center">
    <tr>
        <td>ISBN</td>
        <td>书名</td>
        <td>预定位次</td>
        <td>已归还图书的归还日期</td>
        <td>操作</td>
    </tr>
    <#list predeter as item>
        <tr>
        <td>${item.ISBN}</td>
        <td>${item.bookName}</td>
        <td><#if item.orderNo==-1>您预定的图书已归还<#else>${item.orderNo}</#if></td>
        <td><#if item.backDate??>${item.backDate?string("yyyy-MM-dd")}</#if></td>
        <td><a href="/user/cancelPredeter?bookId=${item.bookId}">取消预定</a></td>
        </tr>
    </#list>
    <#else>
        您还有没有预定任何图书~
    </#if>
    </#if>
</body>
</html>