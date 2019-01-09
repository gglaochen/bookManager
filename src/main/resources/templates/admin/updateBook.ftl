<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <title>修改图书信息</title>
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
                <span class="name">修改图书信息</span>
                <!--当前位置-->
                <div class="position">
                    <a href=""><img src="/img/icon5.png" alt=""/></a>
                    <a href="">书库</a>
                    <span><img src="/img/icon3.png" alt=""/></span>
                    <a href="">修改图书信息</a>
                </div>
                <!--当前位置-->
            </div>
            <!--查询-->
            <div class="search">
                <span>请输入要修改的图书id：</span>
                <div class="s_text"><input name="searchBookId" type="text" id="searchBookId"></div>
                <button id="bt-search">查询</button>
            </div>
            <!--查询-->
            <div class="space_hx">&nbsp;</div>
            <!--列表-->
            <#if bookInfo??>
                <form id="update-form" action="" method="post">
                <table cellpadding="0" cellspacing="0" class="list_hy">
                <tr>
                    <th scope="col">图书编号</th>
                    <th scope="col">ISBN</th>
                    <th scope="col">图书名称</th>
                    <th scope="col">语种</th>
                    <th scope="col">图书分类</th>
                    <th scope="col">书架位置</th>
                    <th scope="col">作者</th>
                    <th scope="col">单/系列</th>
                    <th scope="col">系列排序</th>
                    <th scope="col">系列总数</th>
                    <th scope="col">出版社</th>
                    <th scope="col">出版日期</th>
                    <th scope="col">价格</th>
                    <th scope="col">库存总数</th>
                </tr>
                <tr>
                <td><input type="text" id="bookId" name="bookId" value="${bookInfo.bookId}" readonly="readonly"
                                                                                            style="border: 0px;width: 20px">
                </td>
                <td><input type="text" id="isbn" name="isbn" value="${bookInfo.ISBN}" style="border: 0px;width: 80px"
                           minlength="10"
                           maxlength="13"
                           autofocus required></td>
                <td><input type="text" id="bookName" name="bookName" value="${bookInfo.bookName}" style="border: 0px;width: 120px" autofocus required>
                </td>
                <td><select name="bookCountry" id="bookCountry" style="border: 0px"><!--后期改数据库查-->
            <option value="0" <#if bookInfo.bookCountry==0>selected="selected"</#if>>中国</option>
            <option value="1" <#if bookInfo.bookCountry==1>selected="selected"</#if>>英国</option>
            <option value="2" <#if bookInfo.bookCountry==2>selected="selected"</#if>>美国</option>
            <option value="3" <#if bookInfo.bookCountry==3>selected="selected"</#if>>北欧</option>
            <option value="4" <#if bookInfo.bookCountry==4>selected="selected"</#if>>其他地区</option>
                </select></td>
                <td><input type="text" id="bookType" name="bookType" value="${bookInfo.bookType}" style="border: 0px;width: 40px" autofocus required>
                </td>
                <td><input type="text" id="bookLoc" name="bookLoc" value="${bookInfo.bookLoc}" style="border: 0px;width: 40px" autofocus required>
                </td>
                <td><input type="text" id="author" name="author" value="${bookInfo.author}" style="border: 0px;width: 90px" autofocus required>
                </td>
                <td><input name="isOffprint" id="isOffprint" type="radio"<#if bookInfo.isOffprint==1 >checked="checked"value="1"</#if>/>单行本
                    <input name="isOffprint" id="isOffprint" type="radio"<#if bookInfo.isOffprint==0 >checked="checked"value="0"</#if>value="0"/>系列书</td>
                <td><input type="text" id="orderNum" name="orderNum" value="${bookInfo.orderNum}" style="border: 0px;width: 40px"  autofocus required>
                </td>
                <td><input type="text" id="seriesNum" name="seriesNum" value="<#if bookInfo.isOffprint==0>${bookInfo.seriesNum}</#if>"
                           style="border: 0px;width: 40px" autofocus></td>
                <td><input type="text" id="publish" name="publish" value="${bookInfo.publish}" style="border: 0px;width: 100px"  autofocus required>
                </td>
                <td><input type="date" id="publishDate"
                           name="publishDate" value="${bookInfo.publishDate?string("yyyy-MM-dd")}" style="width: 120px"
                           autofocus required></td>
                <td><input type="text" id="price" name="price" value="${bookInfo.price}" style="border: 0px;width: 40px"  autofocus required>
                </td>
                <td><input type="text" id="maxNum" name="maxNum" value="${bookInfo.maxNum}" style="border: 0px;width: 40px"  autofocus required>
                </td>
                </tr>
                </table>
                <br>
                <button id="updateBT">修改图书</button>
                </form>
            </#if>
            <#if message??>
                ${message}
            </#if>
            <!--列表-->
            <!--右边底部-->
            <div class="r_foot">
                <div class="r_foot_m">
                    <a href="" class="btn">刷新</a>

                </div>
            </div>
            <!--右边底部-->
        </div>
        <!--会议列表-->
    </div>
</div>
<script type="text/javascript">
    $("#bt-search").click(function(){
            window.location.href="/book/updateBook?bookId="+$("#searchBookId").val();
        }
    );
    $("#updateBT").click(function () {
        var con=confirm("确认修改图书信息吗");
        if (con==true) {
            $.ajax({
                url: "/book/toUpdateBook",
                data: $("#update-form").serialize(),
                type: "POST",
                dataType: "json",
                success: function (obj) {
                    window.location.href = "/book/updateBook?bookId=" + $("#bookId").val();
                }
            })
        }
    })
</script>
</body>
</html>
