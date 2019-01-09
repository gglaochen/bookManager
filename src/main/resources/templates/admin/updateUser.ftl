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
                    <a href="">读者管理</a>
                    <span><img src="/img/icon3.png" alt=""/></span>
                    <a href="">修改读者信息</a>
                </div>
                <!--当前位置-->
            </div>
            <!--查询-->
            <div class="search">
                <span>请输入要修改的读者id：</span>
                <div class="s_text"><input name="searchUserId" type="text" id="searchUserId"></div>
                <button id="bt-search">查询</button>
            </div>
            <!--查询-->
            <div class="space_hx">&nbsp;</div>
            <!--列表-->
            <#if readerInfo??>
                <form id="update-form" action="" method="post">
                <table cellpadding="0" cellspacing="0" class="list_hy">
                <tr>
                    <th scope="col">用户id</th>
                    <th scope="col">用户名</th>
                    <th scope="col">昵称</th>
                    <th scope="col">性别</th>
                    <th scope="col">电话</th>
                    <th scope="col">押金</th>
                    <th scope="col">是否有期限</th>
                    <th scope="col">有效期至</th>
                    <th scope="col">状态</th>
                </tr>
                <tr>
                <td><input type="text" id="readerId" name="readerId" value="${readerInfo.uid}" readonly="readonly"
                                                                                            style="border: 0px;width: 20px">
                </td>
                <td><input type="text" id="username" name="username" value="${readerInfo.username}" style="border: 0px;width: 80px"
                           minlength="3"
                           maxlength="15"
                           autofocus required></td>
                <td><input type="text" id="readername" name="readername" value="${readerInfo.readername}" style="border: 0px;width: 120px" autofocus required>
                </td>
                <td><input name="sex" id="sex" type="radio"<#if readerInfo.sex==1 >checked="checked" value="1"</#if>/>男
            <input name="sex" id="sex" type="radio"<#if readerInfo.sex=0 >checked="checked" value="0"</#if>value="0"/>女</td>
                </td>
                <td><input type="text" id="phone" name="phone" value="${readerInfo.phone}" style="border: 0px;width: 80px" autofocus required>
                </td>
                <td><input type="text" id="cashPledge" name="cashPledge" value="${readerInfo.cashPledge}" style="border: 0px;width: 40px" autofocus required>
                </td>
                <td><input name="isPeriod" id="isPeriod" type="radio"<#if readerInfo.isPeriod==1 >checked="checked" value="1"</#if>/>是
            <input name="isPeriod" id="isPeriod" type="radio"<#if readerInfo.isPeriod==0 >checked="checked" value="0"</#if>value="0"/>否</td>
                <td><input type="date" id="periodValidity"
                           name="periodValidity"<#if readerInfo.periodValidity??> value="${readerInfo.periodValidity?string("yyyy-MM-dd")}"</#if> style="width: 120px"
                           autofocus required></td>
                <td><select name="status" id="status" style="border: 0px"><!--后期改数据库查-->
            <option value="0" <#if readerInfo.status==0>selected="selected"</#if>>正常</option>
            <option value="1" <#if readerInfo.status==1>selected="selected"</#if>>过期</option>
            <option value="2" <#if readerInfo.status==2>selected="selected"</#if>>挂失</option>
            <option value="3" <#if readerInfo.status==3>selected="selected"</#if>>注销</option>
                </select>
                </td>
                </tr>
                </table>
                <br>
                <button id="updateBT">修改读者</button>
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
           window.location.href="/user/updateUser?userId="+$("#searchUserId").val();
        }
    );
    $("#updateBT").click(function () {
        var con = confirm("确认要修改读者信息吗");
        if(con==true){
        $.ajax({
            url:"/user/toUpdateReader",
            data:$("#update-form").serialize(),
            type:"POST",
            dataType: "json",
            success:function (obj) {
                window.location.href="/user/updateUser?userId="+$("#readerId").val();
            }
        })
    }}
    )
</script>
</body>
</html>
