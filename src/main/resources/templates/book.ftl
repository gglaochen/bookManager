<!DOCTYPE html>
<script src="/js/jquery.min.js"></script>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>图书详情</title>
</head>
<body>
<#if book.bookImage??>
    <img src="${book.bookImage}" alt="图片显示失败" style="width:180px ;height:180px ;">
    <#else>
    默认图片
</#if><br>
${book.bookName}<br>
作者:${book.author}<br>
出版、发行者:${book.publish}<br>
出版发行时间:${book.publishDate?string('yyyy-MM-dd')}<br>
详细信息：<br>
标识号: ISBN:${book.ISBN}<br>
语种:${const.getBookCountryName(book.bookCountry)}<br>
索书号:${book.bookType}/${book.bookLoc}<br>
图书剩余数量:${book.restNum}<br>
摘要：<br>
<#if book.info??>
${book.info}
</#if><br>
<#if Session["user"]??><#if isExist><button value="取消收藏" id="bt-cancelF">取消收藏</button>
   <#else> <button value="加入收藏" id="bt-favour">加入收藏</button></#if>
</#if>
<#if book.restNum==0>
    <button value="预定" id="bt-predeter">预定</button>
</#if>
<#if Session["user"]??>
<input type="text" id="userId" value="${user.uid}"  style="display:none">
</#if>
</body>
<script type="text/javascript">
    $("#bt-cancelF").click(function() {
            var con = confirm("确认取消收藏该图书吗");
            if (con == true) {
                $.ajax({
                    url: "/book/deleteFavour",
                    data: "userId=" + $("#userId").val() + "&bookId="+${book.bookId},
                    type: "POST",
                    dataType: "json",
                    success: function (obj) {
                        location.reload();
                        alert(obj.message);
                    }
                });
            }
        }
    );
    $("#bt-favour").click(function() {
            var con = confirm("确认收藏该图书吗");
            if (con == true) {
                $.ajax({
                    url: "/book/addFavour",
                    data: "userId=" + $("#userId").val() + "&bookId=" + ${book.bookId},
                    type: "POST",
                    dataType: "json",
                    success: function (obj) {
                        location.reload();
                        alert(obj.message);
                    }
                });
            }
        }
    );
    $("#bt-predeter").click(function() {
        $.ajax({
            url: "/book/findGetDate",
            data: "userId=" + $("#userId").val() + "&bookId=" + ${book.bookId},
            type: "POST",
            dataType: "json",
            success: function (obj) {
                if (obj.state==1){
                    alert(obj.message);
                }else {
                    var con = confirm(obj.message);
                    if (con == true) {
                        $.ajax({
                            url: "/book/addPredeter",
                            data: "bookId=${book.bookId}&userId="+$("#userId").val()+"&orderNo="+obj.data,
                            type: "POST",
                            dataType: "json",
                            success: function (obj) {
                                alert(obj.message);
                            }
                        });
                    }
                    }
                }
            })
        });
</script>
</html>