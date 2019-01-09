<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
    <title>图书借阅</title>
    <link rel="stylesheet" type="text/css" href="/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/css/thems.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
            //自适应屏幕宽度
            window.onresize=function(){ location=location };

            var main_h = $(window).height();
            $('.hy_list').css('height',main_h-45+'px');

            var search_w = $(window).width()-40;
            $('.search').css('width',search_w+'px');
            //$('.list_hy').css('width',search_w+'px');
        });
    </script>
    <!--框架高度设置-->
</head>

<body onLoad="Resize();">
<div id="right_ctn">
    <div class="right_m">
        <!--会议列表-->
        <div class="hy_list">
            <div class="box_t">
                <span class="name">图书借阅</span>
                <!--当前位置-->
                <div class="position">
                    <a href=""><img src="/img/icon5.png" alt=""/></a>
                    <a href="">借阅总表</a>
                    <span><img src="/img/icon3.png" alt=""/></span>
                    <a href="">借阅图书</a>
                </div>
            </div>
            <div class="search">
                <#--<select id="searchType">-->
                    <#--<option value="1" selected="selected">书名</option>-->
                    <#--<option value="2">ISBN</option>-->
                    <#--<option value="3">作者</option>-->
                <#--</select>-->
                <#--<div class="s_text"><input name="search" id="search" type="text"></div>-->
                <#--<button value="查询图书" id="bt-find">查询图书</button>-->
                <span>图书Id：</span><div class="s_text"><input name="bookId" id="bookId" type="text"></div>
                <span>用户id：</span><div class="s_text"><input name="uid" id="uid" type="text"></div>
                <button value="借阅" id="bt-borrow">借阅</button>
            </div>
            <!--查询-->
            <div class="space_hx">&nbsp;</div>
                <!--列表-->
            <form action="" method="post">
                <table id="showTable" cellpadding="0" cellspacing="0" class="list_hy">
                    <#--<#list bookInfos as item>-->
                        <#--<tr>-->
                        <#--<td>${item.bookId}</td>-->
                        <#--<td>${item.ISBN}</td>-->
                        <#--<td>${item.bookName}</td>-->
                        <#--<td>${item.author}</td>-->
                        <#--<td><#if item.isOffprint==1>单行本<#else>系列书</#if></td>-->
                        <#--<td><#if item.isOffprint!=1>${item.orderNum}</#if></td>-->
                        <#--<td><#if item.isOffprint!=1>${item.seriesNum}</#if></td>-->
                        <#--<td>${item.publish}</td>-->
                        <#--<td>${item.price}</td>-->
                        <#--<td>${item.maxNum}</td>-->
                        <#--<td>${item.borrowNum}</td>-->
                        <#--</tr>-->
                    <#--</#list>-->
                </table>

                <!--右边底部-->
                <div class="r_foot">
                    <div class="r_foot_m">
                        <a href="" class="btn">刷新</a>

                        <!--分页-->
                        <#if page??>
                        <div class="page">
                            <a href="/book/showAllBooks?pageNo=${page.prePage}" class="prev">上一页</a>
                            <#list 1..page.lastPage as t>
                                <a href="/book/showAllBooks?pageNo=${t}"><font <#if pageNo==t>color="#adff2f"</#if>>${t}</font></a>
                            </#list>
                            <a href="/book/showAllBooks?pageNo=${page.nextPage}" class="next">下一页</a>
                        </div>
                        </#if>
                        <!--分页-->
                    </div>
                </div>
            </form>
            <!--右边底部-->
        </div>
        <!--会议列表-->
    </div>
</div>
</body>
<script type="text/javascript">
    $("#bt-borrow").click(function() {
            var con = confirm("确认要借阅吗");
            if (con == true) {
            $.ajax({
                url: "/book/borrowBook",
                data: "userId=" + $("#uid").val() + "&bookId=" + $("#bookId").val(),
                type: "POST",
                dataType: "json",
                success: function (obj) {
                    alert(obj.message);
                    if(obj.state==1){
                        window.location.reload();
                    }
                }
            });
        };
    }
    )
    // $("#bt-find").click(function() {
    //         if($("#search").val()==""){
    //             alert("查询条件不能为空");
    //         }else {
    //             $.ajax({
    //                 url: "/book/findBySearchType",
    //                 data: "search=" + $("#search").val() + "&searchType=" + $("#searchType").val(),
    //                 type: "POST",
    //                 dataType: "json",
    //                 success: function (obj) {
    //                     if (obj.state == 1) {
    //                         // document.getElementById("showTable").innerHTML = "<tr><th>图书编号</th><th>ISBN</th>" +
    //                         //     "<th>图书名称</th><th>作者</th><th>是否单行本</th><th>系列排序</th><th>系列总数</th><th>出版社</th>" +
    //                         //     "<th>价格</th><th>库存总数</th><th>借出数量</th></tr>";
    //                         // obj.data.forEach(item=>{
    //                         //     document.getElementById("showTable").innerHTML = document.getElementById("showTable").innerHTML+"<tr><td>"
    //                         //         +item.bookId+"</td><td>"+item.ISBN+"</td><td>"+item.bookName+"</td><td>"+item.author+"</td><td>"+item.isOffprint+"</td></tr>";
    //                         // })
    //                     }else {
    //                         alert(obj.message);
    //                     }
    //                 }
    //             });
    //         }
    //     }
    // );
</script>
</html>
