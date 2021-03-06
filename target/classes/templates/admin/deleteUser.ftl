<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
    <title>注销读者</title>
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
                <span class="name">注销读者</span>
                <!--当前位置-->
                <div class="position">
                    <a href=""><img src="/img/icon5.png" alt=""/></a>
                    <a href="">读者管理</a>
                    <span><img src="/img/icon3.png" alt=""/></span>
                    <a href="">注销读者</a>
                </div>
            </div>
            <div class="search">
                <span>用户Id：</span><div class="s_text"><input name="userId" id="userId" type="text"></div>
                <button value="注销" id="bt-delete">注销</button>
            </div>
            <!--查询-->
            <div class="space_hx">&nbsp;</div>
            <!--列表-->
            <form action="" method="post">
                <table id="showTable" cellpadding="0" cellspacing="0" class="list_hy">

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
    $("#bt-delete").click(function() {
            var con = confirm("确认要注销用户吗");
            if (con == true) {
                $.ajax({
                    url: "/user/toDeleteUser",
                    data: "userId=" + $("#userId").val(),
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
</script>
</html>
