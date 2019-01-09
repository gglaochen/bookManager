<!DOCTYPE html>
<script src="/js/jquery.min.js"></script>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>我的借阅</title>
</head>
<body>
<a href="/">返回首页</a><br>
<#if bookBorrow??>
<#if bookBorrow?size!=0>
<h2 align="center">我的借阅</h2>
<table border="1" width="800" align="center">
    <tr>
        <td>书名</td>
        <td>借阅时间</td>
        <td>归还时间</td>
        <td>距离归还时间</td>
        <td>ISBN</td>
        <td>是否丢失</td>
        <td>罚金</td>
        <td>罚金是否已交</td>
        <td>续借（归还期限前五天可续借）</td>
    </tr>
<#list bookBorrow as item>
    <tr>
        <td>${item.bookName}</td>
        <td>${item.borrowDate?string("yyyy-MM-dd")}</td>
        <td><#if item.returnDate??>${item.returnDate?string("yyyy-MM-dd")}<#else>未归还</#if></td>
        <td><#if item.returnDate??>已归还<#elseif item.timeDifference<0>已超时${item.timeDifference*(-1)}天<#else>${item.timeDifference}天</#if></td>
        <td>${item.ISBN}</td>
        <td><#if item.isLost==1>丢失<#else>未丢失</#if></td>
        <td>${item.fine}</td>
    <td><#if item.fine!=0><#if item.isPaid==1>已交<#else>未交</#if><#else>无需缴纳罚金</#if></td>
    <td><#if item.isRenew==1>已续借<#elseif item.timeDifference<5 &&item.timeDifference gt 0><button id="bt-borrowAgain" value="userId=${Session.user.uid}&bookId=${item.bookId}">续借</button></#if></td>
    </tr>
</#list>
    <#else>
    您还未借阅任何图书,快去借阅吧~~
    </#if>
    </#if>
</body>
<script type="text/javascript">
    $("#bt-borrowAgain").click(function () {
        var con = confirm("确认续借吗");
        if(con==true) {
            $.ajax({
                url: "/book/borrowBookAgain",
                data: $("#bt-borrowAgain").val(),
                type: "POST",
                success: function (obj) {
                    alert(obj.message);
                    window.location.reload();
                }
            })
        }
        }
    )
</script>
</html>