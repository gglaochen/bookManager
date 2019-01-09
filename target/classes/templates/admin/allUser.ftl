<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
    <title>图书信息展示</title>
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
                <span class="name">用户详情页</span>
                <!--当前位置-->
                <div class="position">
                    <a href=""><img src="/img/icon5.png" alt=""/></a>
                    <a href="">读者管理</a>
                    <span><img src="/img/icon3.png" alt=""/></span>
                    <a href="">显示所有读者信息</a>
                </div>
                <!--当前位置-->
            </div>
            <!--查询-->
            <div class="search">
                <span>按用户名或手机号查询：</span>
                <div class="s_text"><input name="" type="text"></div>
                <button>查询</button>
            </div>
            <!--查询-->
            <div class="space_hx">&nbsp;</div>
            <!--列表-->
            <#if readerInfos??>
            <form action="" method="post">
                <table cellpadding="0" cellspacing="0" class="list_hy">
                    <tr>
                        <th scope="col">用户id</th>
                        <th scope="col">用户名</th>
                        <th scope="col">账户昵称</th>
                        <th scope="col">性别</th>
                        <th scope="col">类别</th>
                        <th scope="col">电话号</th>
                        <th scope="col">押金</th>
                        <th scope="col">注册时间</th>
                        <th scope="col">是否有有效期</th>
                        <th scope="col">有效期至</th>
                        <th scope="col">用户状态</th>
                    </tr>
                    <#list readerInfos as item>
                        <tr>
                        <td>${item.uid}</td>
                        <td>${item.username}</td>
                        <td>${item.readername}</td>
                        <td><#if item.sex=1>男<#else>女</#if></td>
                        <td>${item.typeId}</td>
                        <td>${item.phone}</td>
                        <td>${item.cashPledge?string.currency}</td>
                        <td>${item.regDate?string("yyyy-MM-dd")}</td>
                        <td><#if item.isPeriod=1>是<#else>否</#if></td>
                        <td><#if item.periodValidity??>${item.periodValidity?string("yyyy-MM-dd")}</#if></td>
                        <td>${const.getReaderStatusName(item.status)}</td>
                        </tr>
                    </#list>
                </table>
                </#if>
                <!--列表-->
                <!--右边底部-->
                <div class="r_foot">
                    <div class="r_foot_m">
                        <a href="" class="btn">刷新</a>

                        <!--分页-->
                        <div class="page">
                            <a href="/user/showAllUsers?pageNo=${page.prePage}" class="prev">上一页</a>
                            <#list 1..page.lastPage as t>
                                <a href="/user/showAllUsers?pageNo=${t}"><font <#if pageNo==t>color="#adff2f"</#if>>${t}</font></a>
                            </#list>
                            <a href="/user/showAllUsers?pageNo=${page.nextPage}" class="next">下一页</a>
                        </div>
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
</html>
