$('#find').click(function () {
    if($('#search').val()==null||$('#search').val()=="") {
        alert("请输入图书名称或作者名称");
    }else {
        window.location.href = "book/" + $('#search').val();
    }
    }
);