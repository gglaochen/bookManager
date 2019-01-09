<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <title>图书信息展示</title>
    <link rel="stylesheet" type="text/css" href="/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/css/thems.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {
            //自适应屏幕宽度
            window.onresize = function () {
                location = location
            };

            var main_h = $(window).height();
            $('.hy_list').css('height', main_h - 45 + 'px');

            var search_w = $(window).width() - 40;
            $('.search').css('width', search_w + 'px');
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
                <span class="name">书库</span>
                <!--当前位置-->
                <div class="position">
                    <a href=""><img src="/img/icon5.png" alt=""/></a>
                    <a href="">书库管理</a>
                    <span><img src="/img/icon3.png" alt=""/></span>
                    <a href="">查看书库</a>
                </div>
                <!--当前位置-->
            </div>
            <!--查询-->
            <div class="search">
                <span>按书名或作者名查询：</span>
                <div class="s_text"><input name="" type="text" id="search"></div>
                <button id="bt-search">查询</button>
            </div>
            <!--查询-->
            <div class="space_hx">&nbsp;</div>
            <!--列表-->
            <#if bookInfos?? && bookInfos?size!=0>
            <form action="" method="post">
                <table cellpadding="0" cellspacing="0" class="list_hy">
                    <tr>
                        <th scope="col">编号</th>
                        <th scope="col">ISBN</th>
                        <th scope="col">图书名称</th>
                        <th scope="col">语种</th>
                        <th scope="col">图书分类</th>
                        <th scope="col">书架位置</th>
                        <th scope="col">作者</th>
                        <th scope="col">是否单行本</th>
                        <th scope="col">系列排序</th>
                        <th scope="col">系列总数</th>
                        <th scope="col">出版社</th>
                        <th scope="col">出版日期</th>
                        <th scope="col">价格</th>
                        <th scope="col">库存总数</th>
                        <th scope="col">借出数量</th>
                    </tr>
                    <#list bookInfos as item>
                        <tr>
                        <td>${item.bookId}</td>
                        <td>${item.ISBN}</td>
                        <td>${item.bookName}</td>
                        <td>${const.getBookCountryName(item.bookCountry)}</td>
                        <td>${item.bookType}</td>
                        <td>${item.bookLoc}</td>
                        <td>${item.author}</td>
                        <td><#if item.isOffprint==1>单行本<#else>系列书</#if></td>
                        <td><#if item.isOffprint!=1>${item.orderNum}</#if></td>
                        <td><#if item.isOffprint!=1>${item.seriesNum}</#if></td>
                        <td>${item.publish}</td>
                        <td>${item.publishDate?string("yyyy-MM-dd")}</td>
                        <td>${item.price?string.currency}</td>
                        <td>${item.maxNum}</td>
                        <td>${item.borrowNum}</td>
                        </tr>
                    </#list>
                </table>
                <#else>
                未查询到图书信息
                </#if>
                <!--列表-->
                <!--右边底部-->
                <div class="r_foot">
                    <div class="r_foot_m">
                        <a href="" class="btn">刷新</a>

                        <!--分页-->
                        <#if search??>
                            <div class="page">
                            <a href="/book/${search}?pageNo=${page.prePage}&returnPage=2" class="prev">上一页</a>
                            <#list 1..page.lastPage as t>
                                <a href="/book/${search}?pageNo=${t}&returnPage=2"><font
                            <#if pageNo==t>color="#adff2f"</#if>>${t}</font></a>
                            </#list>
                        <a href="/book/${search}?pageNo=${page.nextPage}&returnPage=2" class="next">下一页</a>
                            </div>
                            <#else>
                            <div class="page">
                            <a href="/book/showAllBooks?pageNo=${page.prePage}" class="prev">上一页</a>
                            <#list 1..page.lastPage as t>
                                <a href="/book/showAllBooks?pageNo=${t}"><font
                            <#if pageNo==t>color="#adff2f"</#if>>${t}</font></a>
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
<script type="text/javascript">
    $("#bt-search").click(function () {
        window.location.href = "/book/" + $("#search").val() + "?returnPage=2";
    });
</script>
</body>
</html>
