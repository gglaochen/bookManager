<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>排行榜</title>
</head>
<body>
<a href="/">返回主页</a><br>

<#if orderBy!=1><a href="/book/rank?orderBy=1">日榜</a><#else>日榜</#if>
<#if orderBy!=2><a href="/book/rank?orderBy=2">周榜</a><#else>周榜</#if>
<#if orderBy!=3><a href="/book/rank?orderBy=3">月榜</a><#else>月榜</#if><br>
    <table>
    <tr>
        <th>书名</th><th>作者</th><th>借阅次数</th>
    </tr>
    <#list BookOrder as item>
    <tr>
        <td>${item.bookName}</td>
        <td>${item.author}</td>
        <td>${item.borrowNum}</td>
    </tr>
    </#list>
    </table>
    更新于:${updateTime?string('yyyy-MM-dd')}
<br>
</body>
</html>