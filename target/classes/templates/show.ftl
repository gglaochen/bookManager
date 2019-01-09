<!DOCTYPE html>
<script src="/js/jquery.min.js"></script>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>图书查询</title>
</head>
<body>
<a href="/">返回首页</a><br>
<#if bookInfos?size!=0>
排序方式<select name="selectOrder" id="selectOrder">
    <option value="1"<#if select=1> selected="selected"</#if>>图书编号</option>
    <option value="2"<#if select=2> selected="selected"</#if>>图书名称A-Z</option>
    <option value="3"<#if select=3> selected="selected"</#if>>图书作者A-Z</option>
    <option value="4"<#if select=4> selected="selected"</#if>>出版日期(最近的排在前面)</option>
    <option value="5"<#if select=5> selected="selected"</#if>>出版日期(最久的排在前面)</option>
</select>
<b>${pageNo}</b>/${page.lastPage}页<br>

<table border="1" width="800">
    <tr>
        <td>ISBN</td>
        <td>书名</td>
        <td>作者</td>
        <td>书价</td>
        <td>图书位置</td>
        <td>出版社</td>
        <td>出版日期</td>
        <td>载体形态</td>
    </tr>
    <#list bookInfos as item>
        <tr>
        <td>${item.ISBN}</td>
        <td>
    <a href="/book/bookInfo?bookId=${item.bookId}">${item.bookName}</a></td>
        <td>${item.author}</td>
        <td>${item.price?string.currency}</td>
        <td>${item.bookType}/${item.bookLoc}</td>
        <td>${item.publish}</td>
        <td>${item.publishDate?string('yyyy-MM-dd')}</td>
        <td>
        <#if item.isOffprint==1>
            单行本
        <#else>
            ${item.orderNum}册/${item.seriesNum}册
        </#if>
        </td>
        </tr>
    </#list>
</table>
<#if page.isFirstPage==true><!--是否为首页-->
    首页 上一页
</#if>
<#if page.isFirstPage!=true>
    <a href="${search}?pageNo=${page.firstPage}&orderBy=${orderBy}&select=${select}">首页</a>
    <a href="${search}?pageNo=${page.prePage}&orderBy=${orderBy}&select=${select}">上一页</a>
</#if>
<input type="text" id="TZpageNo" onkeyup="keyup(${page.lastPage})"/>
    <button onclick="TZ()">跳转</button><!--跳转-->
<#if page.isLastPage==true><!--是否为尾页-->
    下一页  尾页
</#if>
<#if page.isLastPage!=true>
    <a href="${search}?pageNo=${page.nextPage}&orderBy=${orderBy}&select=${select}">下一页</a>
    <a href="${search}?pageNo=${page.lastPage}&orderBy=${orderBy}&select=${select}">尾页</a>
</#if>
<br>
<div>年份</div>
<ul>
    <#list years as item>
        <li>
        <a href="javascript:choose();">
        <input type="checkbox" class="yearCheck" value="${item}"
        <#if selectYear??>
        <#if selectYear?contains(item)>
        checked="checked"
        </#if>
        </#if>
    > ${item}
        </a>
        </li>
    </#list>
</ul>
<div>作者</div>
<ul>
    <#list authors as item>
        <li>
        <a href="javascript:choose();">
        <input type="checkbox" class="authorCheck" value="${item}"
        <#if selectAuthor??>
    <#if selectAuthor?contains(item)>checked="checked"</#if>
            </#if>
    > ${item}
        </a>
        </li>
    </#list>
</ul>
<div>语种</div>
<ul>
    <#list countries as item>
        <li>
        <a href="javascript:choose();">
        <input type="checkbox" class="countryCheck" value="${item}"
        <#if selectCountry??>
    <#if selectCountry?contains(item)>checked="checked"</#if>
            </#if>
    > ${item}
        </a>
        </li>
    </#list>
</ul>
<#else>
    抱歉，未搜索到您想要搜索的图书
</#if>
<script type="text/javascript">
    document.getElementById("selectOrder").addEventListener('change', function () {
            var orderName, orderType = 'ASC', select = $("#selectOrder").val();
            if ($("#selectOrder").val() == 1)
                orderName = 'book_id';

            if ($("#selectOrder").val() == 2) {
                orderName = 'book_name';
            }
            if ($("#selectOrder").val() == 3) {
                orderName = 'author';
            }
            if ($("#selectOrder").val() == 4) {
                orderName = 'publish_date';
            }
            if ($("#selectOrder").val() == 5) {
                orderName = 'publish_date';
                orderType = 'DESC';
            }
            order = orderName + " " + orderType;
            window.location.href = "${search}?pageNo=${pageNo}&orderBy=" + order + "&select=" + select;
        }
    );

    function keyup(lastpage) {
        var pageNo = document.getElementById("TZpageNo").value;
        document.getElementById("TZpageNo").value = document.getElementById("TZpageNo").value.replace(/[^0-9-]+/, '');
        if (pageNo > lastpage) {
            document.getElementById("TZpageNo").value = lastpage;
        }
        if (pageNo < 1) {
            document.getElementById("TZpageNo").value = 1;
        }
    }

    function TZ() {
        var pageNo = document.getElementById("TZpageNo").value;
        window.location.href = "${search}?pageNo=" + pageNo + "&orderBy=${orderBy}&select=${select}";
    }

    function choose() {
        var yearArray=[];
        $(".yearCheck").each(function () {
            if(this.checked==true){
            yearArray.push(this.value);
            }
        });
        var yearString = yearArray.join("=");
        var authorArray = [];
        $(".authorCheck").each(function () {
            if(this.checked==true) {
                authorArray.push(this.value);
            }
        });
        var authorString = authorArray.join("=");
        var countryArray = [];
        $(".countryCheck").each(function () {
            if(this.checked==true) {
            countryArray.push(this.value);
            }
        });
        var countryString = countryArray.join("=");
        window.location.href = "${search}?pageNo=${pageNo}&orderBy=${orderBy}&select=${select}&selectYear=" +
            yearString + "&selectAuthor=" + authorString + "&selectCountry=" + countryString;
    }
</script>
</body>
</html>