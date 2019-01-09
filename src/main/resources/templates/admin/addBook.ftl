<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=8" >
    <title>添加图书</title>
    <link rel="stylesheet" type="text/css" href="/css/reset.css"/>
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/css/thems.css">
    <script type="text/javascript" src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
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
                <span class="name">添加图书</span>
                <!--当前位置-->
                <div class="position">
                    <a href=""><img src="/img/icon5.png" alt=""/></a>
                    <a href="">书库管理</a>
                    <span><img src="/img/icon3.png" alt=""/></span>
                    <a href="">添加图书</a>
                </div>
            </div>
            <div class="search">
            </div>
            <!--查询-->
            <div class="space_hx">&nbsp;</div>
            <#if message??><font color="red">${message}</font><br>
            </#if>
            <!--列表-->
            <form action="/book/toAddBook" id="form-addBook" method="post" enctype="multipart/form-data">
                <table>
                    <tr class="form-group">
                        <td>ISBN</td>
                        <td id="hang">
                            <input type="text" id="isbn" name="isbn" placeholder="请输入图书的唯一识别号" minlength="10" maxlength="13"
                                   autofocus required>
                            <span class="msg-default hidden" id="isbnSpan"></span>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>书名</td>
                        <td id="hang">
                            <input type="text" id="bookName" name="bookName" placeholder="请输入书名"
                                   autofocus required>
                            <span class="msg-default hidden" id="bookNameSpan"></span>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>图书分类</td>
                        <td id="hang">
                            <input type="text" id="bookType" name="bookType" placeholder="请输入中国标准的图书分类号"
                                   autofocus required>
                            <span class="msg-default hidden" id="bookTypeSpan"></span>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>图书书架位置</td>
                        <td id="hang">
                            <input type="text" id="bookLoc" name="bookLoc" placeholder="请输入图书书架位置" onkeyup="keyup()"
                                   autofocus required>
                            <span class="msg-default hidden" id="bookLocSpan"></span>
                        </td>
                    </tr>
                    <tr>
                    <tr class="form-group">
                        <td>作者</td>
                        <td id="hang">
                            <input type="text" id="author" name="author" placeholder="请输入该书作者"
                                   autofocus required>
                            <span class="msg-default hidden" id="authorSpan"></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            图书语种
                        </td>
                        <td>
                            <select name="bookCountry" id="bookCountry"><!--后期改数据库查-->
                                <option value="0" selected="selected">中国</option>
                                <option value="1">英国</option>
                                <option value="2">美国</option>
                                <option value="3">北欧</option>
                                <option value="4">其他地区</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            是否是单行本:
                        </td>
                        <td>
                            <input name="isOffprint" id="isOffprint" type="radio" checked="checked"value="1"/>单行本
                            <input name="isOffprint" id="isOffprint" type="radio" value="0"/>系列书
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>是系列书的第几册</td>
                        <td id="hang">
                            <input type="text" id="orderNum" name="orderNum"
                                   autofocus required>
                            <span class="msg-default hidden" id="orderNumSpan"></span>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>系列书总数</td>
                        <td id="hang">
                            <input type="text" id="seriesNum" name="seriesNum"
                                   autofocus>
                            <span class="msg-default hidden" id="seriesNumSpan"></span>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>出版社</td>
                        <td id="hang">
                            <input type="text" id="publish" name="publish"
                                   autofocus required>
                            <span class="msg-default hidden" id="publishSpan"></span>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>出版日期</td>
                        <td id="hang">
                            <input type="date" id="publishDate" name="publishDate"
                                   autofocus required>
                            <span class="msg-default hidden" id="publishDateSpan"></span>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>价格</td>
                        <td id="hang">
                    <input id="price" class="input easyui-numberbox" min="0.01" value="1"
                           max="1000000" precision="2" type="text" name="price" />
                            <span class="msg-default hidden" id="price"></span>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>库存</td>
                        <td id="hang">
                            <input id="maxNum" class="input easyui-numberbox" min="0" value="1"
                                   max="1000000" precision="0" type="text" name="maxNum" />
                            <span class="msg-default hidden" id="maxNum"></span>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>摘要</td>
                        <td id="hang">
                            <textarea id="info" name="info"
                                      autofocus></textarea>
                            <span class="msg-default hidden" id="infoSpan"></span>
                        </td>
                    </tr>
                    <tr class="form-group">
                        <td>图书封面</td>
                        <td id="hang">
                            <input type="file" id="file" name="file"
                                   autofocus>
                            <span class="msg-default hidden" id="fileSpan"></span>
                        </td>
                    </tr>
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
                <button id="addBT">添加图书</button>
            </form>
            <!--右边底部-->
        </div>
        <!--会议列表-->
    </div>
</div>
<script type="text/javascript">
    $("#addBT").click(function{
            var isOffprint = document.getElementById('isOffprint').value;
            if(isOffprint==1){
                document.getElementById('orderNum').value=1;
                document.getElementById('seriesNum').value=null;
            }
            $.ajax({
                url: "/book/checkType",
                data: "bookType="+$("#bookType"),
                type: "POST",
                dataType: "json",
                success:function (obj) {
                    if(obj.state==0){
                        alert(obj.message);
                    }else {
                        $("#form-addBook").submit();
                    }
                }
            });
        }
    );
    function keyup() {
        document.getElementById("bookLoc").value = document.getElementById("bookLoc").value.replace(/[^0-9-]+/, '');
    }
</script>
</body>
</html>
