<!DOCTYPE html>
<script src="/js/jquery.min.js"></script>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>我的收藏</title>
</head>
<body>
<a href="/">返回个人中心</a><br>
<#if bookInfos??>
<#if bookInfos?size!=0>
<h2 align="center">我的收藏</h2>
<table border="1" width="800" align="center">
    <tr>
        <td>图片</td>
        <td>书名</td>
        <td>作者</td>
        <td>出版社</td>
        <td>取消收藏</td>
    </tr>
    <#list bookInfos as item>
        <tr>
        <td><#if item.bookImage??>${item.bookImage}<#else>默认图片 </#if></td>
        <td><a href="/book/bookInfo?bookId=${item.bookId}">${item.bookName}</a></td>
        <td>${item.author}</td>
        <td>${item.publish}</td>
        <td><button id="cancle-favour" value="bookId=${item.bookId}&userId=${Session.user.uid}">取消收藏</button></td>
        </tr>
    </#list>
    <#else>
        您还有没有收藏任何图书，快去收藏吧~
    </#if>
    </#if>
</body>
<script type="text/javascript">
    $("#cancle-favour").click(function () {
        var can = confirm("确认取消收藏吗");
        if(can==true) {
            $.ajax({
                url: "/book/deleteFavour",
                data: $("#cancle-favour").val(),
                type: "POST",
                success: function (obj) {
                    alert(obj.message);
                    if(obj.state==1){
                        window.location.reload();
                    }
                }
            })
        }
    });

</script>
</html>