
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
    <title>左边导航</title>
    <link rel="stylesheet" type="text/css" href="/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
    <script src="/js/jquery.min.js"></script>
    <!--框架高度设置-->
    <script type="text/javascript">
        $(function(){
            $('.sidenav li').click(function(){
                $(this).siblings('li').removeClass('now');
                $(this).addClass('now');
            });

            $('.erji li').click(function(){
                $(this).siblings('li').removeClass('now_li');
                $(this).addClass('now_li');
            });

            var main_h = $(window).height();
            $('.sidenav').css('height',main_h+'px');
        })
    </script>
    <!--框架高度设置-->
</head>

<body>
<div id="left_ctn">
    <ul class="sidenav">
        <li class="now">
            <div class="nav_m">
                <span><a href="/book/ToBorrow" target="main">借阅管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li class="now_li">
                    <span><a href="/book/ToBorrow" target="main">借阅图书</a></span>
                </li>
                <li>
                    <span><a href="/book/borrowAgain" target="main">续借图书</a></span>
                </li>
                <li>
                    <span><a href="/book/returnBook" target="main">归还图书</a></span>
                </li>
            </ul>
        </li>
        <li>
            <div class="nav_m">
                <span><a href="/book/showAllBooks"  target="main">书库管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="/book/showAllBooks" target="main">查看书库</a></span>
                </li>
                <li>
                    <span><a href="/book/addBook" target="main">新增图书</a></span>
                </li>
                <li>
                    <span><a href="/book/updateBook" target="main">修改图书信息</a></span>
                </li>
                <li>
                    <span><a href="/book/deleteBook" target="main">删除图书</a></span>
                </li>
            </ul>
        </li>
        <li>
            <div class="nav_m">
                <span><a href="/user/showAllUsers"  target="main">读者管理</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="/user/showAllUsers" target="main">显示所有读者信息</a></span>
                </li>
                <li>
                    <span><a href="/user/updateUser" target="main">修改读者信息</a></span>
                </li>
                <li>
                    <span><a href="/user/deleteUser" target="main">注销读者信息</a></span>
                </li>
            </ul>
        </li>
        <li>
            <div class="nav_m">
                <span><a>挂失/解禁</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="/user/report" target="main">挂失</a></span>
                </li>
                <li>
                    <span><a href="/user/open" target="main">解禁</a></span>
                </li>
                <li>
                    <span><a href="/user/rebuild" target="main">丢失重办</a></span>
                </li>
            </ul>
        </li>
        <li>
            <div class="nav_m">
                <span><a>押金</a></span>
                <i>&nbsp;</i>
            </div>
            <ul class="erji">
                <li>
                    <span><a href="/cash/support" target="main">提交押金</a></span>
                </li>
                <li>
                    <span><a href="/cash/returnCash" target="main">归还押金</a></span>
                </li>
            </ul>
        </li>
        <li>
            <div class="nav_m">
                <span><a href="/index" target="_blank">退出系统</a></span>
            </div>
        </li>
    </ul>
</div>
</body>
</html>
